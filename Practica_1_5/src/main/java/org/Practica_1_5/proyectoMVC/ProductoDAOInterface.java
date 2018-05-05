package org.Practica_1_5.proyectoMVC;

import java.util.List;

public interface ProductoDAOInterface {
	public List<ProductosDTO> listaProducto();
	public ProductosDTO BuscarProducto(int IdProducto);

}
