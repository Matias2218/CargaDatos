package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BasePl;
import cl.sernatur.service.BasePlService;


@Transactional
//@Repository

public class BasePlServiceImpl implements BasePlService {
	
	private Connection con;
	private String sql;
	
	public BasePlServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_pl";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_pais"
										+ ", llegadas"
										+ ", descripcion"
										+ ", id_tipo_activo"
										+ ", codigo_carga";

	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "DELETE FROM " + TABLE_NAME
			+ " WHERE id_anio IS NOT NULL"
			+ " AND id_mes IS NOT NULL"
			+ " AND CONCAT(id_anio,SUBSTRING(CONCAT('0',id_mes),LENGTH(CONCAT('0',id_mes))-1,LENGTH(CONCAT('0',id_mes))))::float8"
			+ " BETWEEN " + desde + " AND " + hasta;
		
		return this.con.prepareCall(sql).executeUpdate();
	} 
	
	@Override
	public int agregar(BasePl base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'PL" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + base.getId_pais()
			+ " , " + base.getLlegadas()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
