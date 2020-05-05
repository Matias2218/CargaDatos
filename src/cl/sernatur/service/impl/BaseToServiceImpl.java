package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTo;
import cl.sernatur.service.BaseToService;


@Transactional
//@Repository

public class BaseToServiceImpl implements BaseToService {
	
	private Connection con;
	private String sql;
	
	public BaseToServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_to";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_feriado"
										+ ", id_region"
										+ ", id_destino"
										+ ", tasa"
										+ ", descripcion"
										+ ", id_tipo_activo"
										+ ", codigo_carga";

	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "DELETE FROM " + TABLE_NAME
			+ " WHERE id_anio IS NOT NULL"
			+ " AND id_anio BETWEEN " + desde + " AND " + hasta;
		
		return this.con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int agregar(BaseTo base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region FROM to_destino WHERE id = " + base.getId_destino();
		ResultSet rsDestino = con.prepareCall(sql).executeQuery();
		rsDestino.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'TO" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_feriado()
			+ " , " + rsDestino.getString("id_region")
			+ " , " + base.getId_destino()
			+ " , " + base.getTasa()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsDestino.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
