package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.BaseTeGrupo;
import cl.sernatur.beans.BaseTeFinancia;
import cl.sernatur.beans.BaseTe;
import cl.sernatur.service.BaseTeService;


@Transactional
//@Repository

public class BaseTeServiceImpl implements BaseTeService {
	
	private Connection con;
	private String sql;
	private String sqlDelete;
	
	public BaseTeServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME_GRUPO = "te_grupo";
	private final String ALL_ID_COLUMNS_GRUPO = "id"
												+ ", id_data_te"
												+ ", id_genero"
												+ ", id_te_tramo";
	
	@Override
	public int agregar(BaseTeGrupo base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_GRUPO;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME_GRUPO + " (" + ALL_ID_COLUMNS_GRUPO
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_te()
			+ " , " + base.getId_genero()
			+ " , " + base.getId_te_tramo()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
	
	// Common attributes
	private final String TABLE_NAME_FINANCIA = "te_financia_r";
	private final String ALL_ID_COLUMNS_FINANCIA = "id"
												+ ", id_data_te"
												+ ", id_te_financia";
	
	@Override
	public int agregar(BaseTeFinancia base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME_FINANCIA;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "INSERT INTO " + TABLE_NAME_FINANCIA + " (" + ALL_ID_COLUMNS_FINANCIA
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , " + base.getId_data_te()
			+ " , " + base.getId_te_financia()
			+ ")";
		
		return con.prepareCall(sql).executeUpdate();
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_te";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_mes_trimestre"
										+ ", id_mes_semestre"
										+ ", codigo_id"
										+ ", codigo_encuestador"
										+ ", fecha"
										+ ", id_nacionalidad"
										+ ", id_region_omt"
										+ ", id_subregion_omt"
										+ ", id_pais_destino"
										+ ", id_te_motivo"
										+ ", numero_noches"
										+ ", numero_mujeres"
										+ ", numero_hombres"
										+ ", numero_grupo"
										+ ", id_linea_aerea_salida"
										+ ", id_linea_aerea_regreso"
										+ ", vuelo_numero"
										+ ", total_pt"
										+ ", fe"
										+ ", fe_m"
										+ ", fe_mm"
										+ ", fe_f"
										+ ", fe_ff"
										+ ", fee"
										+ ", egreso"
										+ ", llegadas"
										//+ ", gpdi"
										//+ ", gtg"
										+ ", total_sin_pasajes"
										+ ", total_con_pasajes"
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
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_GRUPO + " WHERE id_data_te IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sqlDelete = "DELETE FROM " + TABLE_NAME_FINANCIA + " WHERE id_data_te IN (SELECT id " + sql + ")";
		this.con.prepareCall(sqlDelete).executeUpdate();
		
		sql = "DELETE " + sql;
		return this.con.prepareCall(sql).executeUpdate();
	}
	
	@Override
	public int agregar(BaseTe base) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		sql = "SELECT id_mes_semestre FROM mes_trimestre WHERE id = " + base.getId_mes_trimestre();
		ResultSet rsTrimestre = con.prepareCall(sql).executeQuery();
		rsTrimestre.next();
		
		sql = "SELECT id_region_omt, id_subregion_omt FROM pais WHERE id = " + base.getId_pais_destino();
		ResultSet rsPais = con.prepareCall(sql).executeQuery();
		rsPais.next();
		
		sql = "SELECT id FROM linea_aerea WHERE codigo = '" + base.getCodigo_linea_aerea_salida() + "'";
		ResultSet rsLineaAereaSalida = con.prepareCall(sql).executeQuery();
		rsLineaAereaSalida.next();
		
		sql = "SELECT id FROM linea_aerea WHERE codigo = '" + base.getCodigo_linea_aerea_regreso() + "'";
		ResultSet rsLineaAereaRegreso = con.prepareCall(sql).executeQuery();
		rsLineaAereaRegreso.next();
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ rs.getString("id")
			+ " , 'TE" + rs.getString("id") + "'"
			+ " , " + base.getId_anio()
			+ " , " + base.getId_mes()
			+ " , " + base.getId_mes_trimestre()
			+ " , " + rsTrimestre.getString("id_mes_semestre")
			+ " , '" + base.getCodigo_id() + "'"
			+ " , '" + base.getCodigo_encuestador() + "'"
			+ " , '" + base.getFecha() + "'"
			+ " , " + base.getId_nacionalidad()
			+ " , " + rsPais.getString("id_region_omt")
			+ " , " + rsPais.getString("id_subregion_omt")
			+ " , " + base.getId_pais_destino()
			+ " , " + base.getId_te_motivo()
			+ " , " + base.getNumero_noches()
			+ " , " + base.getNumero_mujeres()
			+ " , " + base.getNumero_hombres()
			+ " , " + base.getNumero_grupo()
			+ " , " + rsLineaAereaSalida.getString("id")
			+ " , " + rsLineaAereaRegreso.getString("id")
			+ " , " + base.getVuelo_numero()
			+ " , " + base.getTotal_pt()
			+ " , " + base.getFe()
			+ " , " + base.getFe_m()
			+ " , " + base.getFe_mm()
			+ " , " + base.getFe_f()
			+ " , " + base.getFe_ff()
			+ " , " + base.getFee()
			+ " , " + base.getEgreso()
			+ " , " + base.getLlegadas()
			+ " , " + base.getTotal_sin_pasajes()
			+ " , " + base.getTotal_con_pasajes()
			+ " , '" + base.getDescripcion() + "'"
			+ " , " + base.getId_tipo_activo()
			+ " , " + base.getCodigo_carga()
			+ ")";
		
		int flag = con.prepareCall(sql).executeUpdate();
		
		int flagGrupo = 0;
		for (Iterator<BaseTeGrupo> iter = base.getLista_grupo().iterator(); iter.hasNext();) {
			BaseTeGrupo baseGrupo = iter.next();
			baseGrupo.setId_data_te(rs.getInt("id"));
			flagGrupo = agregar(baseGrupo);
		}
		
		int flagFinancia = 0;
		for (Iterator<BaseTeFinancia> iter = base.getLista_financia().iterator(); iter.hasNext();) {
			BaseTeFinancia baseFinancia = iter.next();
			baseFinancia.setId_data_te(rs.getInt("id"));
			flagFinancia = agregar(baseFinancia);
		}
		
		rs.close();
		rsTrimestre.close();
		rsPais.close();
		rsLineaAereaSalida.close();
		rsLineaAereaRegreso.close();
		if (flag == 1 && flagGrupo >= 0 && flagFinancia >= 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
