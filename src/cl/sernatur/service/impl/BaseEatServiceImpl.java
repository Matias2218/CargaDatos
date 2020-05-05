package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseEatTotal;
import cl.sernatur.beans.BaseEatNacional;
import cl.sernatur.beans.BaseEatExtranjero;
import cl.sernatur.beans.BaseEat;
import cl.sernatur.service.BaseEatService;


@Transactional
//@Repository

public class BaseEatServiceImpl implements BaseEatService {
	
	private Connection con;
	private String sql;
	private String sqlDelete;
	
	public BaseEatServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME_TOTAL = "eat_total_ta";
	private final String ALL_ID_COLUMNS_TOTAL = "id"
											+ ", id_data_eat"
											+ ", id_eat_tipo_alojamiento"
											+ ", total_ta"
											+ ", total_plazas"
											+ ", descripcion"
											+ ", id_tipo_activo";
	
	@Override
	public int agregar(BaseEatTotal base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_TOTAL;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME_TOTAL + " (" + ALL_ID_COLUMNS_TOTAL
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_eat()
			+ " , " + base.getId_eat_tipo_alojamiento()
			+ " , " + base.getTotal_ta()
			+ " , " + base.getTotal_plazas()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
		
	// Common attributes
	private final String TABLE_NAME_NACIONAL = "eat_llyp_nacional";
	private final String ALL_ID_COLUMNS_NACIONAL = "id"
												+ ", id_data_eat"
												+ ", id_region"
												+ ", llegadas"
												+ ", pernocta"
												+ ", descripcion"
												+ ", id_tipo_activo";
	
	@Override
	public int agregar(BaseEatNacional base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_NACIONAL;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
	
		sql = "INSERT INTO " + TABLE_NAME_NACIONAL + " (" + ALL_ID_COLUMNS_NACIONAL
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_eat()
			+ " , " + base.getId_region()
			+ " , " + base.getLlegadas()
			+ " , " + base.getPernocta()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
			
		return con.prepareCall(sql).executeUpdate();
	}
	
	// Common attributes
	private final String TABLE_NAME_EXTRANJERO = "eat_llyp_extranjero";
	private final String ALL_ID_COLUMNS_EXTRANJERO = "id"
													+ ", id_data_eat"
													+ ", id_region"
													+ ", llegadas"
													+ ", pernocta"
													+ ", descripcion"
													+ ", id_tipo_activo";
	
	@Override
	public int agregar(BaseEatExtranjero base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_EXTRANJERO;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME_EXTRANJERO + " (" + ALL_ID_COLUMNS_EXTRANJERO
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_eat()
			+ " , " + base.getId_region()
			+ " , " + base.getLlegadas()
			+ " , " + base.getPernocta()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_eat";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", uid"
										+ ", dias_funciona"
										+ ", id_eat_clase_alojamiento"
										+ ", id_region"
										+ ", id_provincia"
										+ ", id_comuna"
										+ ", id_eat_temporada"
										+ ", id_eat_destino"
										+ ", total_unidad_alojamiento"
										+ ", total_plazas"
										+ ", total_llegada_nacional"
										+ ", total_pernocta_nacional"
										+ ", total_llegada_extranjero"
										+ ", total_pernocta_extranjero"
										+ ", total_llegadas"
										+ ", total_pernocta"
										+ ", unidad_alojamiento_ocupada"
										+ ", plaza_adicional_instalada"
										+ ", ingreso_neto_alojamiento"
										+ ", ingreso_neto_otros"
										+ ", total_ingreso_neto"
										+ ", hab_dia_disponible"
										+ ", plaza_dia_disponible"
										+ ", factor_expansion"
										+ ", descripcion"
										+ ", id_tipo_activo"
										+ ", codigo_carga";
	
	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "FROM " + TABLE_NAME
			+ " WHERE id_anio IS NOT NULL"
			+ " AND id_mes IS NOT NULL"
			+ " AND CONCAT(id_anio,SUBSTRING(CONCAT('0',id_mes),LENGTH(CONCAT('0',id_mes))-1,LENGTH(CONCAT('0',id_mes))))::float8"
			+ " BETWEEN " + desde + " AND " + hasta;
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_TOTAL + " WHERE id_data_eat IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_NACIONAL + " WHERE id_data_eat IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_EXTRANJERO + " WHERE id_data_eat IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sql = "DELETE " + sql;
		return this.con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int agregar(BaseEat base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region, id_provincia FROM comuna WHERE id = " + base.getId_comuna();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			//+ " , 'EAT" + rs.getString("id") + "'"
			+ " , " + rs.getString("id")
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + base.getUid()
			+ " , " + base.getDias_funciona()
			+ " , " + base.getId_eat_clase_alojamiento()
			+ " , " + rsComuna.getString("id_region")
			+ " , " + rsComuna.getString("id_provincia")
			+ " , " + base.getId_comuna()
			+ " , " + base.getId_eat_temporada()
			+ " , " + base.getId_eat_destino()
			+ " , " + base.getTotal_unidad_alojamiento()
			+ " , " + base.getTotal_plazas()
			+ " , " + base.getTotal_llegada_nacional()
			+ " , " + base.getTotal_pernocta_nacional()
			+ " , " + base.getTotal_llegada_extranjero()
			+ " , " + base.getTotal_pernocta_extranjero()
			+ " , " + base.getTotal_llegadas()
			+ " , " + base.getTotal_pernocta()
			+ " , " + base.getUnidad_alojamiento_ocupada()
			+ " , " + base.getPlaza_adicional_instalada()
			+ " , " + base.getIngreso_neto_alojamiento()
			+ " , " + base.getIngreso_neto_otros()
			+ " , " + base.getTotal_ingreso_neto()
			+ " , " + base.getHab_dia_disponible()
			+ " , " + base.getPlaza_dia_disponible()
			+ " , " + base.getFactor_expansion()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		int flag = con.prepareCall(sql).executeUpdate();
		
		int flagTotal = 0;
		for (Iterator<BaseEatTotal> iter = base.getLista_total().iterator(); iter.hasNext();) {
			BaseEatTotal baseTotal = iter.next();
			baseTotal.setId_data_eat(rs.getInt("id"));
			flagTotal = agregar(baseTotal);
		}
		
		int flagNacional = 0;
		for (Iterator<BaseEatNacional> iter = base.getLista_nacional().iterator(); iter.hasNext();) {
			BaseEatNacional baseNacional = iter.next();
			baseNacional.setId_data_eat(rs.getInt("id"));
			flagNacional = agregar(baseNacional);
		}
		
		int flagExtranjero = 0;
		for (Iterator<BaseEatExtranjero> iter = base.getLista_extranjero().iterator(); iter.hasNext();) {
			BaseEatExtranjero baseExtranjero = iter.next();
			baseExtranjero.setId_data_eat(rs.getInt("id"));
			flagExtranjero = agregar(baseExtranjero);
		}
		
		rs.close();
		rsComuna.close();
		if (flag == 1 && flagTotal > 0 && flagNacional > 0 && flagExtranjero > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
}
