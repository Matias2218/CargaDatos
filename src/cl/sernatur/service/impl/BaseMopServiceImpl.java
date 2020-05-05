package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseMop;
import cl.sernatur.service.BaseMopService;


@Transactional
//@Repository

public class BaseMopServiceImpl implements BaseMopService {
	
	private Connection con;
	private String sql;
	
	public BaseMopServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_mop";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_peaje"
										+ ", tv01"
										+ ", tv02"
										+ ", tv03"
										+ ", tv04"
										+ ", tv05"
										+ ", tv06"
										+ ", tv07"
										+ ", tv08"
										+ ", tv09"
										+ ", tv10"
										+ ", tv11"
										+ ", tv12"
										+ ", tv13"
										+ ", tv14"
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
	public int agregar(BaseMop base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'MOP" + rs.getString("id") + "'"
			//+ " (SELECT COALESCE(MAX(id),0)+1 FROM " + TABLE_NAME + ")"
			//+ " , CONCAT('MOP_',(SELECT COALESCE(MAX(id),0)+1 FROM " + TABLE_NAME + "))"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + base.getId_peaje()
			+ " , " + base.getTv01()
			+ " , " + base.getTv02()
			+ " , " + base.getTv03()
			+ " , " + base.getTv04()
			+ " , " + base.getTv05()
			+ " , " + base.getTv06()
			+ " , " + base.getTv07()
			+ " , " + base.getTv08()
			+ " , " + base.getTv09()
			+ " , " + base.getTv10()
			+ " , " + base.getTv11()
			+ " , " + base.getTv12()
			+ " , " + base.getTv13()
			+ " , " + base.getTv14()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		return con.prepareCall(sql).executeUpdate();
	}
}
