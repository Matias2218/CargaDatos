package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseAct;
import cl.sernatur.service.BaseActService;


@Transactional
//@Repository

public class BaseActServiceImpl implements BaseActService {
	
	private Connection con;
	private String sql;
	
	public BaseActServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_act";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_region"
										+ ", id_provincia"
										+ ", id_comuna"
										+ ", id_act"
										+ ", id_acteco"
										+ ", numero_empresas"
										+ ", ventas_uf"
										+ ", numero_trabajadores"
										+ ", trabajadores_femenino_informado"
										+ ", trabajadores_masculino_informado"
										+ ", renta_neta"
										+ ", renta_neta_femenino"
										+ ", renta_neta_masculino"
										+ ", trabajadores_ponderados"
										+ ", trabajadores_ponderados_femenino"
										+ ", trabajadores_ponderados_masculinos"
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
	public int agregar(BaseAct base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region, id_provincia FROM comuna WHERE id = " + base.getId_comuna();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "SELECT id_act FROM acteco WHERE id = " + base.getId_acteco();
		ResultSet rsActividadEconomica = con.prepareCall(sql).executeQuery();
		rsActividadEconomica.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'ACT" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + rsComuna.getString("id_region")
			+ " , " + rsComuna.getString("id_provincia")
			+ " , " + base.getId_comuna()
			+ " , " + rsActividadEconomica.getString("id_act")
			+ " , " + base.getId_acteco()			
			+ " , " + base.getNumero_empresas()
			+ " , " + base.getVentas_uf()
			+ " , " + base.getNumero_trabajadores()
			+ " , " + base.getTrabajadores_femenino_informado()
			+ " , " + base.getTrabajadores_masculino_informado()
			+ " , " + base.getRenta_neta()
			+ " , " + base.getRenta_neta_femenino()
			+ " , " + base.getRenta_neta_masculino()
			+ " , " + base.getTrabajadores_ponderados()
			+ " , " + base.getTrabajadores_ponderados_femenino()
			+ " , " + base.getTrabajadores_ponderados_masculinos()			
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsComuna.close();
		rsActividadEconomica.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
