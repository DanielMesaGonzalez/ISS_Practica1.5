package org.Practica_1_5.proyectoMVC;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class ProductoMapper implements RowMapper<ProductosDTO>{

	public ProductosDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		
     ProductosDTO producto = new ProductosDTO();
     producto.setNombre(rs.getString("Nombre"));
     producto.setId(rs.getInt("id"));
     producto.setPrecio(rs.getFloat("Precio"));
return producto;
}
}