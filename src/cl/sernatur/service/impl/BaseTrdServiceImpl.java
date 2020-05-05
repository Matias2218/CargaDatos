package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTrd;
import cl.sernatur.service.BaseTrdService;


@Transactional
//@Repository

public class BaseTrdServiceImpl implements BaseTrdService {
	
	private Connection con;
	private String sql;
	
	public BaseTrdServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_tr_divisas";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes_semestre"
										+ ", id_mes_trimestre"
										+ ", excursionistas"
										+ ", dv_excursion"
										+ ", dv_transporte"
										+ ", descripcion"
										+ ", id_tipo_activo"
										+ ", codigo_carga";

	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "DELETE FROM " + TABLE_NAME
			+ " WHERE id_anio IS NOT NULL"
			+ " AND id_mes_trimestre IS NOT NULL"
			+ " AND CONCAT(id_anio,SUBSTRING(CONCAT('0',id_mes_trimestre),LENGTH(CONCAT('0',id_mes_trimestre))-1,LENGTH(CONCAT('0',id_mes_trimestre))))::float8"
			+ " BETWEEN " + desde + " AND " + hasta;
		
		return this.con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int agregar(BaseTrd base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_mes_semestre FROM mes_trimestre WHERE id = " + base.getId_trimestre();
		ResultSet rsTrimestre = con.prepareCall(sql).executeQuery();
		rsTrimestre.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'TRD" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + rsTrimestre.getString("id_mes_semestre")
			+ " , " + base.getId_trimestre()
			+ " , " + base.getExcursionistas()
			+ " , " + base.getDv_excursion()
			+ " , " + base.getDv_transporte()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsTrimestre.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
