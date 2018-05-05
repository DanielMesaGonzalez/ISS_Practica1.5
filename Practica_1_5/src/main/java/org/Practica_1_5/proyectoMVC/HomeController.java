package org.Practica_1_5.proyectoMVC;



import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
	private ProductoDAOInterface daop;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/Registro", method = RequestMethod.POST)
	public String registropost(HttpServletRequest request,Locale locale, Model model) {
		HttpSession sesion = request.getSession();
	    String nombre= (String)request.getParameter("username");
	    String apellido= (String)request.getParameter("surname");
	    String email= (String)request.getParameter("email");
	    String clave= (String)request.getParameter("pass");
	    UsuariosDTO user=new UsuariosDTO(nombre, apellido, email,clave);
	    sesion.setAttribute("nusuario", user);
	    request.setAttribute("nusuario", user);
	    dao.InsertaUsuario(user);
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
	}
	
	@RequestMapping(value = "/Productos", method = RequestMethod.GET)
	public String productos(HttpServletRequest request, @RequestParam("producto") String producto,HttpServletRequest req,Locale locale, Model model) {
		HttpSession sesion = request.getSession();
		ProductosDTO db= new ProductosDTO();
		
			List<ProductosDTO> lista=daop.listaProducto();
			req.setAttribute("lista", lista);
			
				return "Tienda";
			}
	
	@RequestMapping(value = "/CambioDatos", method = RequestMethod.GET)
	public String cambioUsuario(HttpServletRequest request,Locale locale, Model model) {
	    String nombre= (String)request.getParameter("username");
	    String apellido= (String)request.getParameter("surname");
	    String email= (String)request.getParameter("email");
	    String clave= (String)request.getParameter("pass");
	    UsuariosDTO userNuevo=new UsuariosDTO(nombre, apellido, email, clave);
	    dao.ModificarDatos(userNuevo);
		return "CambioDatos";
	}
}
