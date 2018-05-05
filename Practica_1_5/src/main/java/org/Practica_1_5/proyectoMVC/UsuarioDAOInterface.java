package org.Practica_1_5.proyectoMVC;

import java.util.List;

public interface UsuarioDAOInterface {
	public void InsertaUsuario(UsuariosDTO usuario);
	public List<UsuariosDTO> leeUsuario();
	public UsuariosDTO BuscarUsuario(String email);
	public void ModificarDatos(UsuariosDTO usuario);

}
