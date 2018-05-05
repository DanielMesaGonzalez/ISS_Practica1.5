package org.Practica_1_5.proyectoMVC;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class ProductoMapper implements RowMapper<ProductosDTO>{

	public ProductosDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		
     ProductosDTO producto = new ProductosDTO();
     producto.setNombreProducto(rs.getString("NombreProducto"));
     producto.setIdProducto(rs.getInt("IdProducto"));
     producto.setIdProducto(rs.getInt("Cantidad"));
     producto.setIdProducto(rs.getInt("Precio"));
return producto;
}
}