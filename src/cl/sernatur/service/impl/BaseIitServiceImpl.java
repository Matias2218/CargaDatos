package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseIit;
import cl.sernatur.service.BaseIitService;


@Transactional
//@Repository

public class BaseIitServiceImpl implements BaseIitService {
	
	private Connection con;
	private String sql;
	
	public BaseIitServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_iit";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_region"
										+ ", id_provincia"
										+ ", id_comuna"
										+ ", poblacion_flotante"
										+ ", venta_total_uf"
										+ ", ventas_sii"
										+ ", trabajadores_sii"
										+ ", renta_neta_trabajadores_sii"
										+ ", pernocta_vivienda_particular"
										+ ", unidades_alojamiento"
										+ ", plazas_emat"
										+ ", llegadas_emat"
										+ ", pernocta_emat"
										+ ", alojamiento_registro"
										+ ", turismo_aventura_registro"
										+ ", empresas_sii"
										+ ", snaspe"
										+ ", atractivos"
										+ ", indice_iit"
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
	public int agregar(BaseIit base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region, id_provincia FROM comuna WHERE id = " + base.getId_comuna();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'IIT" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + rsComuna.getString("id_region")
			+ " , " + rsComuna.getString("id_provincia")
			+ " , " + base.getId_comuna()
			+ " , " + base.getPoblacion_flotante()
			+ " , " + base.getVenta_total_uf()
			+ " , " + base.getVentas_sii()
			+ " , " + base.getTrabajadores_sii()
			+ " , " + base.getRenta_neta_trabajadores_sii()
			+ " , " + base.getPernocta_vivienda_particular()
			+ " , " + base.getUnidades_alojamiento()
			+ " , " + base.getPlazas_emat()
			+ " , " + base.getLlegadas_emat()
			+ " , " + base.getPernocta_emat()
			+ " , " + base.getAlojamiento_registro()
			+ " , " + base.getTurismo_aventura_registro()
			+ " , " + base.getEmpresas_sii()
			+ " , " + base.getSnaspe()
			+ " , " + base.getAtractivos()
			+ " , " + base.getIndice_iit()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsComuna.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
