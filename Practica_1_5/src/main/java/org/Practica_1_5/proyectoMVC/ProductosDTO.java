package org.Practica_1_5.proyectoMVC;

import java.io.Serializable;

public class ProductosDTO implements Serializable{
	
	private String NombreProducto;
	private int IdProducto;
	private int Cantidad;
	private int Precio;
	
	public ProductosDTO() {
		NombreProducto="";
		IdProducto=0;
		Cantidad=0;
		Precio=0;
	}
	
	public ProductosDTO(String nombreProducto, int idProducto, int cantidad, int precio) {
		super();
		NombreProducto = nombreProducto;
		IdProducto = idProducto;
		Cantidad = cantidad;
		Precio = precio;
	}
	
	public String getNombreProducto() {
		return NombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		NombreProducto = nombreProducto;
	}
	public int getIdProducto() {
		return IdProducto;
	}
	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public int getPrecio() {
		return Precio;
	}
	public void setPrecio(int precio) {
		Precio = precio;
	}
	
	
}
	
	