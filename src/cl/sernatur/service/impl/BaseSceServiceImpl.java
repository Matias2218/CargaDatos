package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseSce;
import cl.sernatur.service.BaseSceService;


@Transactional
//@Repository

public class BaseSceServiceImpl implements BaseSceService {
	
	private Connection con;
	private String sql;
	
	public BaseSceServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_sce";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_region"
										+ ", id_paso_r5"
										+ ", id_paso_r4"
										+ ", id_paso_r3"
										+ ", id_paso_r2"
										+ ", id_paso_r1"
										+ ", id_paso"
										+ ", id_region_omt"
										+ ", id_subregion_omt"
										+ ", id_pais"
										+ ", chilenos_salidos"
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
	public int agregar(BaseSce base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region, id_paso_r1, id_paso_r2, id_paso_r3, id_paso_r4, id_paso_r5 FROM paso WHERE id = " + base.getId_paso();
		ResultSet rsPaso = con.prepareCall(sql).executeQuery();
		rsPaso.next();
		
		sql = "SELECT id_region_omt, id_subregion_omt FROM pais WHERE id = " + base.getId_pais();
		ResultSet rsPais = con.prepareCall(sql).executeQuery();
		rsPais.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'SCE" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + rsPaso.getString("id_region")
			+ " , " + rsPaso.getString("id_paso_r5")
			+ " , " + rsPaso.getString("id_paso_r4")
			+ " , " + rsPaso.getString("id_paso_r3")
			+ " , " + rsPaso.getString("id_paso_r2")
			+ " , " + rsPaso.getString("id_paso_r1")
			+ " , " + base.getId_paso()			
			+ " , " + rsPais.getString("id_region_omt")
			+ " , " + rsPais.getString("id_subregion_omt")
			+ " , " + base.getId_pais()
			+ " , " + base.getChilenos_salidos()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsPaso.close();
		rsPais.close();
		return con.prepareCall(sql).executeUpdate();
	}

}
