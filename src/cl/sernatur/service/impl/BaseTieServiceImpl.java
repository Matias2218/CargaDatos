package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTie;
import cl.sernatur.service.BaseTieService;

@Transactional
public class BaseTieServiceImpl implements BaseTieService {
	
	private Connection con;
	private String sql;
	
	public BaseTieServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_ti_excursion";
	private final String ALL_ID_COLUMNS = 	"id" +
									    ", nombre" +
									    ", id_anio" +
									    ", codigo_encuesta" +
									    ", id_ti_origen" +
									    ", id_region_origen" +
									    ", id_provincia_origen" +
									    ", id_comuna_origen" +
									    ", id_destino" +
									    ", id_region_destino" +
									    ", id_provincia_destino" +
									    ", id_comuna_destino" +
									    ", dato1" +
									    ", dato2" +
									    ", dato3" +
									    ", dato4" +
									    ", dato5" +
									    ", dato6" +
									    ", dato7" +
									    ", dato8" +
									    ", dato9" +
									    ", dato10" +
									    ", dato11" +
									    ", dato12" +
									    ", id_motivo" +
									    ", id_organiza" +
									    ", id_nse" +
									    ", personas_viajan" +
									    ", total_gasto" +
									    ", fact_ajustado_viv" +
									    ", fa_vexcursion" +
									    ", fact_ajustado_viv_ajuste2" +
									    ", factor_personas_viajan" +
									    ", codigo_carga" +
									    ", descripcion" +
									    ", id_tipo_activo";

	@Override
	public int agregar(BaseTie baseTie) throws Exception {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idBaseTie = rs.getString("id");
		
		sql = "SELECT id_provincia, id_region FROM comuna WHERE id = " + baseTie.getId_comuna_origen().toString();
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idProvinciaOrigen = rs.getString("id_provincia");
		String idRegionOrigen = rs.getString("id_region");
		
		sql = "SELECT id_provincia, id_region FROM comuna WHERE id = " + baseTie.getId_comuna_destino().toString();
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idProvinciaDestino = rs.getString("id_provincia");
		String idRegionDestino = rs.getString("id_region");
		
		//Insert a data_tr_base
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES (" + //Falta agregar las variables de baseTie y los insert de las tablas secundarias.
			idBaseTie.toString()// id
			+ ", 'TIE" +  baseTie.getId_anio().toString() + "_" + idBaseTie.toString() + "'"// nombre
			+ ", " + baseTie.getId_anio().toString()// id_anio
			+ ", " + baseTie.getCodigo_encuesta().toString()// codigo_encuesta
			+ ", 0" // id_ti_origen (ver el valor correcto)
			+ ", " + idRegionOrigen// id_region_origen
			+ ", " + idProvinciaOrigen// id_provincia_origen
			+ ", " + baseTie.getId_comuna_origen().toString() // id_comuna_origen
			+ ", " + baseTie.getId_destino().toString() // id_destino
			+ ", " + idRegionDestino // id_region_destino
			+ ", " + idProvinciaDestino// id_provincia_destino
			+ ", " + baseTie.getId_comuna_destino().toString()// id_comuna_destino, 
			+ ", " + baseTie.getDato1().toString()	//dato1
			+ ", " + baseTie.getDato2().toString()	//dato2
			+ ", " + baseTie.getDato3().toString()	//dato3
			+ ", " + baseTie.getDato4().toString()	//dato4
			+ ", " + baseTie.getDato5().toString()	//dato5
			+ ", " + baseTie.getDato6().toString()	//dato6
			+ ", " + baseTie.getDato7().toString()	//dato7
			+ ", " + baseTie.getDato8().toString()	//dato8
			+ ", " + baseTie.getDato9().toString()	//dato9
			+ ", " + baseTie.getDato10().toString()	//dato10
			+ ", " + baseTie.getDato11().toString()	//dato11
			+ ", " + baseTie.getDato12().toString()	//dato12
			+ ", " + baseTie.getId_motivo().toString()// id_motivo
			+ ", " + baseTie.getId_organiza().toString()// id_organiza
			+ ", " + baseTie.getId_nse().toString()// id_nse
			+ ", " + baseTie.getPersonas_viajan().toString()// personas_viajan
			+ ", " + baseTie.getTotal_gasto().toString()// total_gasto
			+ ", " + baseTie.getFact_ajustado_viv().toString()// fact_ajustado_viv
			+ ", " + baseTie.getFa_vexcursion().toString()// fa_vexcursion
			+ ", " + baseTie.getFact_ajustado_viv_ajuste2().toString()// fact_ajustado_viv_ajuste2
			+ ", " + baseTie.getFactor_personas_viajan().toString()// factor_personas_viajan
			+ ", " + baseTie.getCodigo_carga().toString() // codigo_carga
			+ ", '" + baseTie.getDescripcion() + "'"// descripcion
			+ ", " + baseTie.getId_tipo_activo().toString() // id_tipo_activo
			+ ")";
		return con.prepareCall(sql).executeUpdate();
	}

	@Override
	public int eliminar(String desde, String hasta) throws SQLException {
		sql = "DELETE FROM " + TABLE_NAME
				+ " WHERE id_anio IS NOT NULL"
				+ " AND id_anio BETWEEN " + desde + " AND " + hasta;
			
			return this.con.prepareCall(sql).executeUpdate();
		}
	

}
