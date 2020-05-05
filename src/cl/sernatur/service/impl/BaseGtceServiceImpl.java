package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseGtce;
import cl.sernatur.service.BaseGtceService;


@Transactional
//@Repository

public class BaseGtceServiceImpl implements BaseGtceService {
	
	private Connection con;
	private String sql;
	
	public BaseGtceServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_gtce";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_tarjeta"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_region_omt"
										+ ", id_subregion_omt"
										+ ", id_pais"
										+ ", id_gtce_pais"
										+ ", id_region"
										+ ", id_provincia"
										+ ", id_comuna"
										+ ", id_gtce_rubro"
										+ ", id_gtce_subrubro"
										+ ", id_gtce_modalidad"
										+ ", total_uf"
										+ ", numero_transacciones"
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
	public int agregar(BaseGtce base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region_omt, id_subregion_omt, id_pais_prioritario FROM pais WHERE id = " + base.getId_pais();
		ResultSet rsPais = con.prepareCall(sql).executeQuery();
		rsPais.next();
		
		sql = "SELECT id_region, id_provincia FROM comuna WHERE id = " + base.getId_comuna();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'GTCE" + rs.getString("id") + "'"
			+ " , " + base.getId_tarjeta()
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + rsPais.getString("id_region_omt")
			+ " , " + rsPais.getString("id_subregion_omt")
			+ " , " + base.getId_pais()
			+ " , " + rsPais.getString("id_pais_prioritario")
			+ " , " + rsComuna.getString("id_region")
			+ " , " + rsComuna.getString("id_provincia")
			+ " , " + base.getId_comuna()
			+ " , " + base.getId_gtce_rubro()
			+ " , " + base.getId_gtce_subrubro()
			+ " , " + base.getId_gtce_modalidad()
			+ " , " + base.getTotal_uf()
			+ " , " + base.getNumero_transacciones()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsPais.close();
		rsComuna.close();
		return con.prepareCall(sql).executeUpdate();
	}

}
