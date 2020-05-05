package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;

@Transactional
//@Repository

public class CargaServiceImpl implements CargaService {
	
	private Connection con;
	private String sql;
	
	public CargaServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "carga";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", file_name"
										+ ", file_size"
										+ ", file_type"
										+ ", num_rows"
										+ ", num_cols"
										+ ", id_tipo_carga"
										+ ", id_estado_carga"
										+ ", id_error_carga"
										+ ", id_base"
										+ ", fecha"
										+ ", hora"
										+ ", anio_periodo_inicio"
										+ ", mes_periodo_inicio"
										+ ", anio_periodo_fin"
										+ ", mes_periodo_fin"
										+ ", descripcion"
										+ ", id_tipo_activo";
	
	@Override
	public int agregar(Carga base) throws SQLException {
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ ") VALUES ("
			+ base.getId()
			+ " , '" + base.getNombre() + "'"
			+ " , '" + base.getFile_name() + "'"
			+ " , " + base.getFile_size()
			+ " , '" + base.getFile_type() + "'"
			+ " , " + base.getNum_rows()
			+ " , " + base.getNum_cols()
			+ " , " + base.getId_tipo_carga()
			+ " , " + base.getId_estado_carga()
			+ " , " + base.getId_error_carga()
			+ " , " + base.getId_base()
			+ " , '" + base.getFecha() + "'"
			+ " , '" + base.getHora() + "'"
			+ " , " + base.getAnio_periodo_inicio()
			+ " , " + base.getMes_periodo_inicio()
			+ " , " + base.getAnio_periodo_fin()
			+ " , " + base.getMes_periodo_fin()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int actualizar(Carga base) throws SQLException {
		sql = "UPDATE " + TABLE_NAME + " SET"
			+ " num_rows = " + base.getNum_rows()
			+ " , id_estado_carga = " + base.getId_estado_carga()
			+ " , id_error_carga = " + base.getId_error_carga()
			+ " WHERE id = " + base.getId();
		
		return con.prepareCall(sql).executeUpdate();
	}
}
