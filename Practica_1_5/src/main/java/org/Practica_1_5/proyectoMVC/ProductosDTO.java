package org.Practica_1_5.proyectoMVC;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductosDTO implements Serializable{
	
	private String nombre;
	private int id;
	private float precio;
	private int cantidad;
	


	public ProductosDTO(String nombre, int id, float precio, int cantidad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public ProductosDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

	@Override
	public String toString() {
		return "Articulo=" + nombre + ", Codigo=" + id + ", Precio=" + precio + ", Cantidad=" + cantidad + "";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductosDTO other = (ProductosDTO) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
			return false;
		return true;
	}


	
	
	
	
}
	
	