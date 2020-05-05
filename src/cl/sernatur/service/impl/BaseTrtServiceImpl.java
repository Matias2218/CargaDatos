package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTrt;
import cl.sernatur.service.BaseTrtService;


@Transactional
//@Repository

public class BaseTrtServiceImpl implements BaseTrtService {
	
	private Connection con;
	private String sql;
	
	public BaseTrtServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_tr_agrupa";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes_trimestre"
										+ ", id_mes_semestre"
										+ ", id_mes"
										+ ", id_pais_ag1"
										+ ", id_pais_reside"
										+ ", id_pais_origen"
										+ ", id_motivo_ag1"
										+ ", id_tr_motivo1"
										+ ", id_tr_frontera1"
										+ ", noches"
										+ ", dias_turista"
										+ ", gpdi"
										+ ", gti"
										+ ", divisas"
										+ ", fe"
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
	public int agregar(BaseTrt base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		// No está asosociada la tabla mes con mes_trimestre
		Integer trimestre = 0;
		if (base.getId_mes() == 1 || base.getId_mes() == 2 || base.getId_mes() == 3) trimestre = 1; 
		if (base.getId_mes() == 4 || base.getId_mes() == 5 || base.getId_mes() == 6) trimestre = 2;
		if (base.getId_mes() == 7 || base.getId_mes() == 8 || base.getId_mes() == 9) trimestre = 3;
		if (base.getId_mes() == 10 || base.getId_mes() == 11 || base.getId_mes() == 12) trimestre = 4;
		
		sql = "SELECT id_mes_semestre FROM mes_trimestre WHERE id = " + trimestre;
		ResultSet rsTrimestre = con.prepareCall(sql).executeQuery();
		rsTrimestre.next();
		
		sql = "SELECT id_pais_ag1 FROM tr_pais WHERE id = " + base.getId_pais_reside();
		ResultSet rsTr_pais = con.prepareCall(sql).executeQuery();
		rsTr_pais.next();
		
		sql = "SELECT id_motivo_ag1 FROM tr_motivo1 WHERE id = " + base.getId_tr_motivo1();
		ResultSet rsTr_motivo1 = con.prepareCall(sql).executeQuery();
		rsTr_motivo1.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'TRT" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + trimestre
			+ " , " + rsTrimestre.getString("id_mes_semestre")
			+ " , " + base.getId_mes()
			+ " , " + rsTr_pais.getString("id_pais_ag1")
			+ " , " + base.getId_pais_reside()
			+ " , " + base.getId_pais_origen()
			+ " , " + rsTr_motivo1.getString("id_motivo_ag1")
			+ " , " + base.getId_tr_motivo1()
			+ " , " + base.getId_tr_frontera1()
			+ " , " + base.getNoches()
			+ " , " + base.getDias_turista()
			+ " , " + base.getGpdi()
			+ " , " + base.getGti()
			+ " , " + base.getDivisas()
			+ " , " + base.getFactor_expansion()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		rs.close();
		rsTrimestre.close();
		rsTr_pais.close();
		rsTr_motivo1.close();
		return con.prepareCall(sql).executeUpdate();
	}
	
}
