package org.Practica_1_5.proyectoMVC;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductosDAO implements ProductoDAOInterface{
	
	
	private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource2) {
	this.jdbcTemplate = new JdbcTemplate(dataSource2);
	}


	@Override
	public List<ProductosDTO> listaProducto() {
		String sql= "select * from productos";
		ProductoMapper mapper= new ProductoMapper();
		@SuppressWarnings("unchecked")
		List<ProductosDTO> productos= this.jdbcTemplate.query(sql, mapper);
		
		return productos ;
	}

	@Override
	public ProductosDTO BuscarProducto(int IdProducto) {
		String sql = "select * from productos where id = ?";
		Object[] parametros = {IdProducto};   //Array de objetos
		ProductoMapper mapper = new ProductoMapper();
		List<ProductosDTO> productos = this.jdbcTemplate.query(sql, parametros, mapper);
		if (productos.isEmpty()) return null;
		else return productos.get(0);
		
	}
	
	


	
	
}
