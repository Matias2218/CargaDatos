package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseSat;
import cl.sernatur.service.BaseSatService;


@Transactional
//@Repository

public class BaseSatServiceImpl implements BaseSatService {
	
	private Connection con;
	private String sql;
	
	public BaseSatServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_sat";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", codigo"
										+ ", anio_creacion"
										+ ", id_anio"
										+ ", id_clase_alojamiento"
										+ ", id_region"
										+ ", id_provincia"
										+ ", id_comuna"
										+ ", numero_habitacion"
										+ ", numero_departamento"
										+ ", numero_cabana"
										+ ", numero_camping"
										+ ", numero_camas"
										+ ", total_unidades_alojamiento"
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
	public int agregar(BaseSat base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region, id_provincia FROM comuna WHERE id = " + base.getId_comuna();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'SAT" + rs.getString("id") + "'"
			+ " , " + base.getCodigo()
			+ " , " + base.getAnio_creacion()
			+ " , " + base.getId_anio()
			+ " , " + base.getId_clase_alojamiento()
			+ " , " + rsComuna.getString("id_region")
			+ " , " + rsComuna.getString("id_provincia")
			+ " , " + base.getId_comuna()
			+ " , " + base.getNumero_habitacion()
			+ " , " + base.getNumero_departamento()
			+ " , " + base.getNumero_cabana()
			+ " , " + base.getNumero_camping()
			+ " , " + base.getNumero_camas()
			+ " , " + base.getTotal_unidades_alojamiento()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsComuna.close();
		return con.prepareCall(sql).executeUpdate();
	}

}
