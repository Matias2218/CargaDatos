package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseJac;
import cl.sernatur.service.BaseJacService;


@Transactional
//@Repository

public class BaseJacServiceImpl implements BaseJacService {
	
	private Connection con;
	private String sql;
	
	public BaseJacServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_jac";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_aeropuerto_origen"
										+ ", id_aeropuerto_destino"
										+ ", id_region_omt_origen"
										+ ", id_pais_origen"
										+ ", id_ciudad_origen"
										+ ", id_region_omt_destino"
										+ ", id_pais_destino"
										+ ", id_ciudad_destino"
										+ ", id_linea_aerea"
										+ ", id_tipo_nacional"
										+ ", pasajeros_llegada"
										+ ", pasajeros_salida"
										+ ", pasajero_km"
										+ ", carga_llegada"
										+ ", carga_salida"
										+ ", carga_km"
										+ ", distancia"
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
	public int agregar(BaseJac base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id FROM aeropuerto WHERE codigo = '" + base.getCodigo_aeropuerto_origen() + "'";
		ResultSet rsAeropuertoOrigen = con.prepareCall(sql).executeQuery();
		rsAeropuertoOrigen.next();
		
		sql = "SELECT id FROM aeropuerto WHERE codigo = '" + base.getCodigo_aeropuerto_destino() + "'";
		ResultSet rsAeropuertoDestino = con.prepareCall(sql).executeQuery();
		rsAeropuertoDestino.next();
		
		sql = "SELECT id_pais FROM ciudad WHERE id = " + base.getId_ciudad_origen();
		ResultSet rsCiudadOrigen = con.prepareCall(sql).executeQuery();
		rsCiudadOrigen.next();
		
		sql = "SELECT id_pais FROM ciudad WHERE id = " + base.getId_ciudad_destino();
		ResultSet rsCiudadDestino = con.prepareCall(sql).executeQuery();
		rsCiudadDestino.next();
		
		sql = "SELECT id_region_omt FROM pais WHERE id = " + rsCiudadOrigen.getString("id_pais");
		ResultSet rsPaisOrigen = con.prepareCall(sql).executeQuery();
		rsPaisOrigen.next();
		
		sql = "SELECT id_region_omt FROM pais WHERE id = " + rsCiudadDestino.getString("id_pais");
		ResultSet rsPaisDestino = con.prepareCall(sql).executeQuery();
		rsPaisDestino.next();
		
		sql = "SELECT id FROM linea_aerea WHERE codigo = '" + base.getCodigo_linea_aerea() + "'";
		ResultSet rsLineaAerea = con.prepareCall(sql).executeQuery();
		rsLineaAerea.next();
		
		if (rsCiudadOrigen.getString("id_pais").equals(rsCiudadDestino.getString("id_pais")))
			base.setId_tipo_nacional(1);
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'JAC" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + rsAeropuertoOrigen.getString("id")
			+ " , " + rsAeropuertoDestino.getString("id")			
			+ " , " + rsPaisOrigen.getString("id_region_omt")
			+ " , " + rsCiudadOrigen.getString("id_pais")
			+ " , " + base.getId_ciudad_origen()
			+ " , " + rsPaisDestino.getString("id_region_omt")
			+ " , " + rsCiudadDestino.getString("id_pais")
			+ " , " + base.getId_ciudad_destino()
			+ " , " + rsLineaAerea.getString("id")
			+ " , " + base.getId_tipo_nacional()
			+ " , " + base.getPasajeros_llegada()
			+ " , " + base.getPasajeros_salida()
			+ " , " + base.getPasajero_km()
			+ " , " + base.getCarga_llegada()
			+ " , " + base.getCarga_salida()
			+ " , " + base.getCarga_km()
			+ " , " + base.getDistancia()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsAeropuertoOrigen.close();
		rsAeropuertoDestino.close();
		rsCiudadOrigen.close();
		rsCiudadDestino.close();
		rsPaisOrigen.close();
		rsPaisDestino.close();
		rsLineaAerea.close();
		return con.prepareCall(sql).executeUpdate();
	}

}