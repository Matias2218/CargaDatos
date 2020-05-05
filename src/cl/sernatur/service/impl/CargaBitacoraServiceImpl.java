package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;

@Transactional
//@Repository

public class CargaBitacoraServiceImpl implements CargaBitacoraService {
	
	private Connection con;
	private String sql;
	
	public CargaBitacoraServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "carga_bitacora";
	private final String ALL_ID_COLUMNS = "nombre"
										+ ", linea"
										+ ", campo"
										+ ", valor"
										+ ", codigo_carga"
										+ ", descripcion"
										+ ", id_tipo_activo";
	
	@Override
	public int agregar(CargaBitacora base) throws SQLException {
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ ") VALUES ("
			+ "'" + base.getNombre() + "'"
			+ " , " + base.getLinea()
			+ " , '" + base.getCampo() + "'"
			+ " , '" + base.getValor() + "'"
			+ " , " + base.getCodigo_carga()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
		
	}

}
