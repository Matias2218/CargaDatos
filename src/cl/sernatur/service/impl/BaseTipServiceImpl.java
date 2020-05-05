package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTipGastoItem;
import cl.sernatur.beans.BaseTip;
import cl.sernatur.service.BaseTipService;


@Transactional
//@Repository

public class BaseTipServiceImpl implements BaseTipService {
	
	private Connection con;
	private String sql;
	private String sqlDelete;
	
	public BaseTipServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME_GASTOITEM = "ti_gasto_item";
	private final String ALL_ID_COLUMNS_GASTOITEM = "id"
												+ ", id_data_ti"
												+ ", id_ti_item"
												+ ", id_ti_gasto"
												+ ", valor"
												+ ", descripcion"
												+ ", id_tipo_activo";
	
	@Override
	public int agregar(BaseTipGastoItem base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_GASTOITEM;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME_GASTOITEM + " (" + ALL_ID_COLUMNS_GASTOITEM
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_ti()
			+ " , " + base.getId_ti_item()
			+ " , " + base.getId_ti_gasto()
			+ " , " + base.getValor()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_ti";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", codigo_encuesta"
										+ ", id_ti_origen"
										+ ", ronda"
										+ ", iteracion"
										+ ", fe_persona"
										+ ", fe_hogar"
										+ ", id_ti_tipo_viaje"
										+ ", id_svdf"
										+ ", noches_fuera"
										+ ", personas_viajan"
										+ ", gasto_total"
										+ ", gasto_promedio_svdf"
										+ ", gasto_promedio_total_individual"
										+ ", numero_viajes"
										+ ", numero_viajes_modificado"
										+ ", gasto_promedio_diario_individual"
										+ ", id_feriado"
										+ ", fecha_inicio"
										+ ", id_region_origen"
										+ ", id_comuna_origen"
										+ ", id_region_destino"
										//+ ", id_comuna_destino"
										+ ", id_ti_destino"
										+ ", id_ti_medio_transporte"
										+ ", id_ti_medio_transporte_otro"
										+ ", id_ti_alojamiento"
										+ ", id_ti_alojamiento_otro"
										+ ", id_ti_alojamiento_tipo"
										+ ", id_ti_motivo_viaje"
										+ ", id_ti_motivo_viaje_otro"
										+ ", id_nse"
										+ ", qcc_1"
										+ ", qcc_2"
										+ ", descripcion"
										+ ", id_tipo_activo"
										+ ", codigo_carga";
	
	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "FROM " + TABLE_NAME
			+ " WHERE id_anio IS NOT NULL"
			+ " AND id_anio BETWEEN " + desde + " AND " + hasta;
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_GASTOITEM + " WHERE id_data_ti IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sql = "DELETE " + sql;
		return this.con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int agregar(BaseTip base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_region FROM comuna WHERE id = " + base.getId_comuna_origen();
		ResultSet rsComuna = con.prepareCall(sql).executeQuery();
		rsComuna.next();
		
		sql = "SELECT id_region, id_comuna FROM ti_destino WHERE id = " + base.getId_ti_destino();
		ResultSet rsDestino = con.prepareCall(sql).executeQuery();
		rsDestino.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'TIP" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getCodigo_encuesta()
			+ " , " + base.getId_ti_origen()
			+ " , " + base.getRonda()
			+ " , " + base.getIteracion()
			+ " , " + base.getFe_persona()
			+ " , " + base.getFe_hogar()
			+ " , " + base.getId_ti_tipo_viaje()
			+ " , " + base.getId_svdf()
			+ " , " + base.getNoches_fuera()
			+ " , " + base.getPersonas_viajan()
			+ " , " + base.getGasto_total()
			+ " , " + base.getGasto_promedio_svdf()
			+ " , " + base.getGasto_promedio_total_individual()
			+ " , " + base.getNumero_viajes()
			+ " , " + base.getNumero_viajes_modificado()
			+ " , " + base.getGasto_promedio_diario_individual()
			+ " , " + base.getId_feriado()
			+ " , '" + base.getFecha_inicio() + "'"
			+ " , " + rsComuna.getString("id_region")
			+ " , " + base.getId_comuna_origen()
			+ " , " + rsDestino.getString("id_region")
			//+ " , " + rsDestino.getString("id_comuna") //Validar con Leo este dato
			+ " , " + base.getId_ti_destino()
			+ " , " + base.getId_ti_medio_transporte()
			+ " , " + base.getId_ti_medio_transporte_otro()
			+ " , " + base.getId_ti_alojamiento()
			+ " , " + base.getId_ti_alojamiento_otro()
			+ " , " + base.getId_ti_alojamiento_tipo()
			+ " , " + base.getId_ti_motivo_viaje()
			+ " , " + base.getId_ti_motivo_viaje_otro()
			+ " , " + base.getId_nse()
			+ " , " + base.getQcc_1()
			+ " , " + base.getQcc_2()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		int flag = con.prepareCall(sql).executeUpdate();
		
		int flagGastoItem = 0;
		for (Iterator<BaseTipGastoItem> iter = base.getLista_gasto_item().iterator(); iter.hasNext();) {
			BaseTipGastoItem baseGastoItem = iter.next();
			baseGastoItem.setId_data_ti(rs.getInt("id"));
			flagGastoItem = agregar(baseGastoItem);
		}
		
		rs.close();
		rsComuna.close();
		rsDestino.close();
		if (flag == 1 && flagGastoItem >= 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
