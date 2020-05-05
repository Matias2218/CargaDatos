package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.beans.ActividadesTIB;
import cl.sernatur.beans.BaseTib;
import cl.sernatur.beans.EvaluacionTIB;
import cl.sernatur.beans.GastosTIB;
import cl.sernatur.beans.InformacionTIB;
import cl.sernatur.beans.InternetTIB;
import cl.sernatur.beans.MedioTransporteTIB;
import cl.sernatur.beans.MiembrosTIB;
import cl.sernatur.beans.MotivosTIB;
import cl.sernatur.beans.NochesTIB;
import cl.sernatur.beans.RazonesTIB;
import cl.sernatur.beans.ServptTIB;
import cl.sernatur.beans.UsoWebTIB;
import cl.sernatur.service.BaseTibService;

@Transactional
public class BaseTibServiceImpl implements BaseTibService {
	
	private Connection con;
	private String sql;
	
	public BaseTibServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_ti_base";
	private final String ALL_ID_COLUMNS = 	"id"
										+ ", nombre"
										+ ", codigo_encuesta"
										+ ", fecha"
										+ ", id_anio"
										+ ", id_mes"
										+ ", id_mes_trimestre"
										+ ", id_mes_semestre"
										+ ", iteracion"
										+ ", ronda"
										+ ", id_tipo_viaje"
										+ ", id_fdsl"
										+ ", id_temporada"
										+ ", id_region_origen"
										+ ", id_comuna_origen"
										+ ", id_region_destino"
										+ ", id_comuna_destino"
										+ ", id_destino_emat"
										+ ", id_mtransporte"
										+ ", id_tipo_alojamiento"
										+ ", id_motivo"
										+ ", id_nse"
										+ ", id_organiza"
										+ ", id_planifica"
										+ ", id_expectativa"
										+ ", id_organiza_gasto"
										+ ", destino_extra_aloja"
										+ ", gasto_compartido_numero"
										+ ", num_visitas_prev_dp"
										+ ", numero_visitas_anterior_dp"
										+ ", f_personas_viajan"
										+ ", fe_personas"
										+ ", fe_hogaares"
										+ ", fa_personas_viajan_d"
										+ ", fa_viv_ajuste2"
										+ ", gpdi"
										+ ", obsrvacion"
										+ ", descripcion"
										+ ", id_tipo_activo";



	@Override
	public int agregar(BaseTib baseTib, ActividadesTIB actividad, EvaluacionTIB evaluacion, GastosTIB gastos, InformacionTIB informacion,
			InternetTIB internet, RazonesTIB razones, MedioTransporteTIB medioTransporte, MiembrosTIB miembros, 
			MotivosTIB motivos, NochesTIB noches, ServptTIB servpt, UsoWebTIB usoWeb) throws Exception {
		String idComunaOrigen = baseTib.getId_comuna_origen().toString();
		String idComunaDestino = baseTib.getId_comuna_destino().toString();
		
		sql = "SELECT id_region FROM comuna WHERE id = " + idComunaOrigen;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idRegionOrigen = rs.getString("id_region");
		
		sql = "SELECT id_region FROM comuna WHERE id = " + idComunaDestino;
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idRegionDestino = rs.getString("id_region");
		
		//------------------------------------------------------------//
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_alojamiento_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idAlojamientoR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_expectativa_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idExpectativaR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_motivo_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idMotivoR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_organiza_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idOrganizaR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_orgasto_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idOrgastoR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_planifica_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idPlanificaR = rs.getString("id");
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_transporte_r";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idTransporteR = rs.getString("id");
		
		//------------------------------------------------------------//
		
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String id = rs.getString("id");
		
		
		
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
				+ " ) VALUES (" + id
				+ ", 'TIB_" + id 
				+ "', " + baseTib.getCodigo_encuesta().toString()
				+ ", '" + baseTib.getFecha()
				+ "', " + baseTib.getId_anio().toString()
				+ ", " + baseTib.getId_mes().toString()
				+ ", " + baseTib.getId_mes_trimestre().toString()
				+ ", " + baseTib.getId_mes_semestre().toString()
				+ ", " + baseTib.getIteracion().toString()
				+ ", " + baseTib.getRonda().toString()
				+ ", " + baseTib.getId_tipo_viaje().toString()
				+ ", " + baseTib.getId_fdsl().toString()
				+ ", " + baseTib.getId_temporada().toString()
				+ ", " + idRegionOrigen
				+ ", " + baseTib.getId_comuna_origen().toString()
				+ ", " + idRegionDestino
				+ ", " + baseTib.getId_comuna_destino().toString()
				+ ", " + baseTib.getId_destino_emat().toString()
				+ ", " + baseTib.getId_mtransporte().toString()
				+ ", " + baseTib.getId_tipo_alojamiento().toString()
				+ ", " + baseTib.getId_motivo().toString()
				+ ", " + baseTib.getId_nse().toString()
				+ ", " + baseTib.getId_organiza().toString()
				+ ", " + baseTib.getId_planifica().toString()
				+ ", " + baseTib.getId_expectativa().toString()
				+ ", " + baseTib.getId_organiza_gasto().toString()
				+ ", " + baseTib.getDestino_extra_aloja().toString()
				+ ", " + baseTib.getGasto_compartido_numero().toString()
				+ ", " + baseTib.getNum_visitas_prev_dp().toString()
				+ ", " + baseTib.getNumero_visitas_anterior_dp()
				+ ", " + baseTib.getF_personas_viajan().toString()
				+ ", " + baseTib.getFe_personas().toString()
				+ ", " + baseTib.getFe_hogares().toString()
				+ ", " + baseTib.getFa_personas_viajan_d()
				+ ", " + baseTib.getFa_viv_ajuste2().toString()
				+ ", " + baseTib.getGpdi().toString()
				+ ", '" + baseTib.getObsrvacion()
				+ "', '" + baseTib.getDescripcion()
				+ "', " + baseTib.getId_tipo_activo()
				+ ")";
				
				
		int resultado = con.prepareCall(sql).executeUpdate();
		
		agregarAlojamiento(idAlojamientoR, id, baseTib.getId_tipo_alojamiento().toString());
		agregarExpectativa(idExpectativaR, id, baseTib.getId_expectativa().toString());
		agregarMotivo(idMotivoR, id, baseTib.getId_motivo().toString());
		agregarOrganiza(idOrganizaR, id, baseTib.getId_organiza().toString());
		agregarOrgasto(idOrgastoR, id, baseTib.getId_organiza_gasto().toString());
		agregarPlanifica(idPlanificaR, id, baseTib.getId_planifica().toString());
		agregarTransporte(idTransporteR, id, baseTib.getId_mtransporte().toString());
		agregarActividadTIB(id, actividad);
		agregarEvaluaTIB(id, evaluacion);
		agregarGastosTIB(id, gastos);
		agregarInformacionTIB(id, informacion);
		agregarMiembrosTIB(id, miembros);
		agregarNochesTIB(id, noches);
		agregarUsoWebTIB(id, usoWeb);
		agregarInternetTIB(id, internet);
		agregarMedioTransporteTIB(id, medioTransporte);
		agregarServptTIB(id, servpt);
		agregarRazonesTIB(id, razones);
		return resultado;
		
	}

	@Override
	public int eliminar(String desde, String hasta) throws Exception {
		sql = "DELETE FROM " + TABLE_NAME
				+ " WHERE id_anio IS NOT NULL"
				+ " AND id_anio BETWEEN " + desde + " AND " + hasta;
			
			return this.con.prepareCall(sql).executeUpdate();
	}
	
	private void agregarAlojamiento(String id, String idBaseTib, String idAloja) throws SQLException {
		sql = "INSERT INTO ti_alojamiento_r (id, id_data_ti, id_aloja) VALUES (" + id + ", " + idBaseTib + ", " + idAloja + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarExpectativa(String id, String idBaseTib, String idExpectativa) throws SQLException {
		sql = "INSERT INTO ti_expectativa_r (id, id_data_ti, id_expectativa) VALUES (" + id + ", " + idBaseTib + ", " + idExpectativa + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarMotivo(String id, String idBaseTib, String idMotivo) throws SQLException {
		sql = "INSERT INTO ti_motivo_r (id, id_data_ti, id_motivo) VALUES (" + id + ", " + idBaseTib + ", " + idMotivo + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarOrganiza(String id, String idBaseTib, String idOrganiza) throws SQLException {
		sql = "INSERT INTO ti_organiza_r (id, id_data_ti, id_organiza) VALUES (" + id + ", " + idBaseTib + ", " + idOrganiza + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarOrgasto(String id, String idBaseTib, String idOrgasto) throws SQLException {
		sql = "INSERT INTO ti_orgasto_r (id, id_data_ti, id_orgasto) VALUES (" + id + ", " + idBaseTib + ", " + idOrgasto + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarPlanifica(String id, String idBaseTib, String idPlanificacion) throws SQLException {
		sql = "INSERT INTO ti_planifica_r (id, id_data_ti, id_planifica) VALUES (" + id + ", " + idBaseTib + ", " + idPlanificacion + ")";
		con.prepareCall(sql).executeUpdate();
	}	
	private void agregarTransporte(String id, String idBaseTib, String idTransporte) throws SQLException {
		sql = "INSERT INTO ti_transporte_r (id, id_data_ti, id_transporte) VALUES (" + id + ", " + idBaseTib + ", " + idTransporte + ")";
		con.prepareCall(sql).executeUpdate();
	}
	/////////////////////////////////////////////////
	private void agregarActividadTIB(String id_base, ActividadesTIB actividad) throws SQLException {
		List<String> valores = actividad.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_actividad_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idActividad = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_actividad_r (id, id_data_ti, id_actividad) VALUES (" +
				idActividad + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarGastosTIB(String id_base, GastosTIB gastos) throws SQLException {
		List<String> valores = gastos.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 2) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_gastos_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idGastos = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_gastos_r (id, id_data_ti, id_gasto_base, monto) VALUES (" +
				idGastos + ", " + id_base + ", " + valores.get(cont) + ", " + valores.get(cont + 1) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarEvaluaTIB(String id_base, EvaluacionTIB evalua) throws SQLException {
		List<String> valores = evalua.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 2) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_evalua_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idEvalua = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_evalua_r (id, id_data_ti, id_evalua, valor) VALUES (" +
				idEvalua + ", " + id_base + ", " + valores.get(cont) + ", " + valores.get(cont + 1) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarNochesTIB(String id_base, NochesTIB noches) throws SQLException {
		List<String> valores = noches.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 3) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_noches_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idNoche = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_noches_r (id, id_data_ti, id_noches, id_comuna, noches) VALUES (" +
				idNoche + ", " + id_base + ", " + valores.get(cont) + ", " + valores.get(cont + 1) + ", " + valores.get(cont + 2) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarMiembrosTIB(String id_base, MiembrosTIB miembros) throws SQLException {
		List<String> valores = miembros.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 2) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_miembros_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idMiembro = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_miembros_r (id, id_data_ti, id_miembros, valor) VALUES (" +
				idMiembro + ", " + id_base + ", " + valores.get(cont) + ", " + valores.get(cont + 1) + ")";
			con.prepareCall(sql).executeUpdate();
		}
	}
	
	private void agregarInformacionTIB(String id_base, InformacionTIB informacion) throws SQLException {
		List<String> valores = informacion.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 1) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_info_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idInfo = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_info_r (id, id_data_ti, id_info) VALUES (" +
				idInfo + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
	}
	
	private void agregarUsoWebTIB(String id_base, UsoWebTIB usoWeb) throws SQLException {
		List<String> valores = usoWeb.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 1) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_usoweb_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idUsoWeb = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_usoweb_r (id, id_data_ti, id_info) VALUES (" +
				idUsoWeb + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
	}
	
	private void agregarInternetTIB(String id_base, InternetTIB internet) throws SQLException {
		List<String> valores = internet.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_web_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idWeb = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_web_r (id, id_data_ti, id_web) VALUES (" +
				idWeb + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarMedioTransporteTIB(String id_base, MedioTransporteTIB mtransporte) throws SQLException {
		List<String> valores = mtransporte.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_ptransporte_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idMedioTransporte = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_ptransporte_r (id, id_data_ti, id_ptransporte) VALUES (" +
				idMedioTransporte + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarServptTIB(String id_base, ServptTIB servpt) throws SQLException {
		List<String> valores = servpt.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_servpt_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idServpt = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_servpt_r (id, id_data_ti, id_servpt) VALUES (" +
				idServpt + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}
	
	private void agregarRazonesTIB(String id_base, RazonesTIB razones) throws SQLException {
		List<String> valores = razones.getValoresValidos();
		if(valores.size() == 0) {
			return;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM ti_razones_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idRazon = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO ti_razones_r (id, id_data_ti, id_razones) VALUES (" +
				idRazon + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
	}

}
