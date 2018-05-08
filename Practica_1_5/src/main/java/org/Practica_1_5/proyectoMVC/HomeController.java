package org.Practica_1_5.proyectoMVC;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UsuarioDAOInterface dao;
	@Autowired
	private ProductoDAOInterface daop;
	
	//private List<ProductosDTO> SesionCarrito = new ArrayList<ProductosDTO>();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/Registro", method = RequestMethod.POST)
	public String registropost(HttpServletRequest request,Locale locale, Model model,HttpServletResponse response) {
		HttpSession sesion = request.getSession();
	    String nombre= (String)request.getParameter("username");
	    String apellido= (String)request.getParameter("surname");
	    String email= (String)request.getParameter("email");
	    String clave= (String)request.getParameter("pass");
	    UsuariosDTO user=new UsuariosDTO(nombre, apellido, email,clave);
	    sesion.setAttribute("nusuario", user);
	    request.setAttribute("nusuario", user);
	    dao.InsertaUsuario(user);//try catch
	    /*TODO:COOKIES
	    Cookie c = new Cookie("emailCookie", email);
	    c.setMaxAge(60*60*24*365*2); 
	    c.setPath("/");
	    response.addCookie(c);
		*/
		return "Tienda";
	}
	
	@RequestMapping(value = "/Registro", method = RequestMethod.GET)
	public String registroget(HttpServletRequest request,Locale locale, Model model) {
		if(request.getSession(false)!=null) {
			request.getSession(false).setMaxInactiveInterval(3);
			return "sesion";
			}
			else {
		    return "caduca";
			}
		
	}
	
	
	@RequestMapping(value = "/Sesion3", method = RequestMethod.POST)
	public String sesion(HttpServletRequest request, @RequestParam("usuario") String usuario,@RequestParam("clave") String passwd,HttpServletRequest req,Locale locale, Model model) {
		HttpSession sesion = request.getSession();
		UsuariosDTO db= new UsuariosDTO();
		
		try {
		if(db.checkAdmin(usuario,passwd)) {
			
			List<UsuariosDTO> lista=dao.leeUsuario();
			req.setAttribute("lista", lista);
			return"admin";
			
			
		}else if (dao.BuscarUsuario(usuario)==null) {
			return "registro";
			}else { 
				UsuariosDTO user=dao.BuscarUsuario(usuario);
		       sesion.setAttribute("nusuario", user);
		       request.setAttribute("nusuario", user);
				
				
				return "Tienda";
			
			}
		}catch(Exception e) {
			return "BBDD_No_Conectada";//Implementar Error.jsp para error en que BD no este conectada o error general en el sistema
		}
	}
	
	@RequestMapping(value = "/Productos", method = RequestMethod.GET)
	public String productos(HttpServletRequest request,HttpServletRequest req,Locale locale, Model model) {
		HttpSession sesion = request.getSession(true);
		//ProductosDTO db= new ProductosDTO();
		
			List<ProductosDTO> lista=daop.listaProducto();//try catch idem
			model.addAttribute("lista", lista);
			sesion.setAttribute("lista", lista);
				return "Tienda";
			}
	
	@RequestMapping(value = "/CambioDatos", method = RequestMethod.GET)
	public String cambioUsuario(HttpServletRequest request,Locale locale, Model model) {

		return "CambioDatos";
	}
	
	@RequestMapping(value = "/cambioOK", method = RequestMethod.POST)
	public String cambioOK(HttpServletRequest request,Locale locale, Model model) {
		HttpSession sesion = request.getSession();
		String nombre= (String)request.getParameter("username");
	    String apellido= (String)request.getParameter("surname");
	    String email= (String)request.getParameter("email");
	    String clave= (String)request.getParameter("pass");
	    
	    UsuariosDTO userNuevo=new UsuariosDTO(nombre, apellido, email, clave);
	    UsuariosDTO oldUser=(UsuariosDTO)sesion.getAttribute("nusuario");
	    
	    try {dao.ModificarDatos(userNuevo,oldUser);//try catch idem
	    sesion.setAttribute("nusuario", userNuevo);
	    model.addAttribute("respuesta", "Cambios realizados con exito");
	    }catch(Exception e) {
	    model.addAttribute("email", "El email ya existe");
	    }
	    
	return "CambioDatos";
	}
	
	@RequestMapping(value = "/añadir", method = RequestMethod.POST)
	public String añadir(HttpServletRequest request,Locale locale, Model model) {
		HttpSession sesion = request.getSession(false);
		if(sesion==null) {
			sesion=request.getSession(true);}
		int art= Integer.parseInt(request.getParameter("articulo"));
		ProductosDTO articulo=daop.BuscarProducto(art);
		
		//Se comprueba que existe una sesion para su carrito y si es null, la inicializamos y añadimos a la sesion
		if(sesion.getAttribute("itemsCarrito")==null) {
			List<ProductosDTO> SesionCarrito = new ArrayList<ProductosDTO>();
			sesion.setAttribute("itemsCarrito", SesionCarrito);
		}
		//Aquí recogemos de la sesion el carrito si lo hubiera(o lo hayamos inicializado)
		List<ProductosDTO> SesionCarrito =(ArrayList<ProductosDTO>) sesion.getAttribute("itemsCarrito");
		
		//Para simplificar y no rellenar de objetos iguales el carrito, comprobamos si contiene ese objeto 
		//y SOLO CAMBIAMOS SU CANTIDAD (actual+1)
		if(SesionCarrito.contains(articulo)){
			
			SesionCarrito.get(SesionCarrito.indexOf(articulo)).setCantidad(SesionCarrito.get(SesionCarrito.indexOf(articulo)).getCantidad()+1);
			}else {
		//En el caso de que no exista ese articulo/objeto lo añadimos y hacemos cantidad=1, entonces este objeto ya nunca entra en este else
	    SesionCarrito.add(articulo);
		SesionCarrito.get(SesionCarrito.indexOf(articulo)).setCantidad(SesionCarrito.get(SesionCarrito.indexOf(articulo)).getCantidad()+1);
			}
		//Finalmente actualizamos carrito en la sesion
		sesion.setAttribute("itemsCarrito", SesionCarrito);
		sesion.setAttribute("tam",SesionCarrito.size());
		
		return "Tienda";
	}
	
	@RequestMapping(value = "/carrito", method = RequestMethod.GET)
	public String carrito(HttpServletRequest request,Locale locale, Model model) {
		HttpSession sesion = request.getSession(true);
		List<ProductosDTO> SesionCarrito=(ArrayList<ProductosDTO>)sesion.getAttribute("itemsCarrito");
		if (SesionCarrito==null) {
			model.addAttribute("noItems","No ha insertado ningún producto en el carrito");
		}else {
		model.addAttribute("items",SesionCarrito);
		//SumaTotal;
		int i=0;
		float suma=0;
		for (i=0;i<SesionCarrito.size();i++) {
			suma=suma+SesionCarrito.get(i).getPrecio()*SesionCarrito.get(i).getCantidad();
				
		}
		model.addAttribute("suma", suma);	
		//itemsCarrito.get(0);
		}
			return "Carrito";
	}
	
	
}
