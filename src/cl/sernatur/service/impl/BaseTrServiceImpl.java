package cl.sernatur.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cl.sernatur.beans.*;
import javafx.util.Pair;
import org.springframework.transaction.annotation.Transactional;

import cl.sernatur.service.BaseTrService;


@Transactional
//@Repository

public class BaseTrServiceImpl implements BaseTrService {
	
	private Connection con;
	private String sql;
	
	public BaseTrServiceImpl(Connection con) {
		this.con = con;
	}
	
	// Common attributes
	private final String TABLE_NAME = "data_tr_base";
	private final String ALL_ID_COLUMNS = "id"
										+ ", nombre"
										+ ", codigo_tr"
										+ ", id_encuesta"
										+ ", codigo_interno"
										+ ", fecha"
										+ ", id_paso"
										+ ", id_anio"
										+ ", id_mes"
									    + ", id_mes_trimestre"
									    + ", id_mes_semestre"
									    + ", id_nacionalidad"
									    + ", id_residencia"
									    + ", ciudad"
									    + ", grupo_mujer"
									    + ", grupo_hombre"
									    + ", grupo_total"
									    + ", primera_visita"
									    + ", id_motivo_principal"
									    + ", noches_chile"
									    + ", id_tr_transporte_entrada"
									    + ", id_linea_aerea_entrada"
									    + ", numero_vuelo_entrada"
									    + ", monto_pasaje_entrada_usd"
									    + ", id_linea_aerea_salida"
									    + ", numero_vuelo_salida"
									    + ", monto_pasaje_salida_usd"
									    + ", monto_idavuelta_usd"
									    + ", agencia_online"
									    + ", gasto_total_grupo_usd"
									    + ", divisas"
									    + ", gpdi"
									    + ", id_gpdi_tramo"
									    + ", dia_turista"
									    + ", satisfaccion"
									    + ", nps"
									    + ", descripcion"
									    + ", id_tipo_activo"
									    + ", codigo_carga"
									    + ", monto_pt_usd";

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
	public int agregar(BaseTr base, Actividad actividad, Alimentacion alimentacion, Alojamiento alojamiento, Ciudad ciudad, Financia financia,
			Gasto gasto, MediosInformacion mediosInformacion, Organiza organiza, RazonViaje razonViaje, 
			ServicioPaqueteTuristico servicioPaqueteTuristico, Grupo grupo) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM " + TABLE_NAME;
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idBase = rs.getString("id");
		
		//Insert a data_tr_base
		sql = "INSERT INTO " + TABLE_NAME + " (" + ALL_ID_COLUMNS
			+ " ) VALUES ("
			+ idBase //id
			+ ", 'D" + base.getId_anio().toString() + "_" + rs.getString("id") + "'"  //nombre
			+ ", " + idBase //codigo_tr
			+ ", '" + base.getId_encuesta() + "'"//id_encuesta;
			+ ", " + base.getCodigo_interno().toString()//codigo_interno;
			+ ", '" + base.getFecha() + "'"//fecha;
			+ ", " + base.getId_paso().toString() //id_paso;
			+ ", " + base.getId_anio().toString()//id_anio;
			+ ", " + base.getId_mes().toString()//id_mes;
			+ ", " + base.getId_mes_trimestre().toString()//id_mes_trimestre;
			+ ", " + base.getId_mes_semestre().toString()//id_mes_semestre;
			+ ", " + base.getId_nacionalidad().toString()//id_nacionalidad;
			+ ", " + base.getId_residencia().toString()//id_residencia;
			+ ", '" + ciudad.getLocalidad() + "'"//ciudad;
			+ ", " + base.getGrupo_mujer().toString()//grupo_mujer;
			+ ", " + base.getGrupo_hombre().toString()//grupo_hombre;
			+ ", " + base.getGrupo_total().toString()//grupo_total;
			+ ", " + base.getPrimera_visita().toString()//primera_visita;
			+ ", " + base.getId_motivo_principal().toString()//id_motivo_principal;
			+ ", " + base.getNoches_chile().toString()//noches_chile;
			+ ", " + base.getId_tr_transporte_entrada().toString()//id_tr_transporte_entrada;
			+ ", " + base.getId_linea_aerea_entrada().toString()//id_linea_aerea_entrada;
			+ ", " + base.getNumero_vuelo_entrada().toString()//numero_vuelo_entrada;
			+ ", " + base.getMonto_pasaje_entrada_usd().toString()//monto_pasaje_entrada_usd;
			+ ", " + base.getId_linea_aerea_salida().toString()//id_linea_aerea_salida;
			+ ", " + base.getNumero_vuelo_salida().toString()//numero_vuelo_salida;
			+ ", " + base.getMonto_pasaje_salida_usd().toString()//monto_pasaje_salida_usd;
			+ ", " + base.getMonto_idavuelta_usd().toString().replace(",", ".")//monto_idavuelta_usd;
			+ ", '" + base.getAgencia_online() + "'"//agencia_online;
			+ ", " + base.getGasto_total_grupo_usd().toString()//gasto_total_grupo_usd;
			+ ", " + base.getDivisas().toString()//divisas;
			+ ", " + base.getGpdi().toString().replace(",", ".")//gpdi;
			+ ", " + base.getId_gpdi_tramo().toString()//id_gpdi_tramo;
			+ ", " + base.getDia_turista().toString()//dia_turista;
			+ ", " + base.getSatisfaccion().toString()//satisfaccion;
			+ ", " + base.getNps().toString()//nps;
			+ ", '" + base.getDescripcion() + "'"//descripcion;
			+ ", " + base.getId_tipo_activo().toString()//id_tipo_activo;
			+ ", " + base.getCodigo_carga().toString()//codigo_carga;
			+ ", " + base.getMonto_pt_usd().toString().replace(",", ".")//monto_pt_usd;
			+ ")";
		
		int resultado = con.prepareCall(sql).executeUpdate(); //Se inserta a datos_base_tr
		
		agregarActividad(idBase, actividad);
		agregarAlimentacion(idBase, alimentacion);
		agregarAlojamiento(idBase, alojamiento, base.getNoches_chile().toString());
		agregarCiudad(idBase, ciudad);
		agregarFinancia(idBase, financia);
		agregarGasto(idBase, gasto);
		agregarMediosInformacion(idBase, mediosInformacion);
		agregarOrganiza(idBase, organiza);
		agregarRazonViaje(idBase, razonViaje);
		agregarServicioPaqueteTuristico(idBase, servicioPaqueteTuristico);
		agregarPais(idBase, base.getId_nacionalidad().toString());
		agregarMotivos(idBase, base.getId_motivo_principal());
		agregarGrupos(idBase, grupo, base.getId_anio());
		agregarAcompañamiento(idBase, grupo);

		return resultado;
	}
	
	private Integer agregarActividad(String id_base, Actividad actividad) throws SQLException {
		List<String> valores = actividad.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_actividad_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idActividad = rs.getString("id");
			//---------------------------//
			sql = "INSERT INTO tr_actividad_r (id, id_data_tr, id_tr_actividad) VALUES (" +
				idActividad + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		
		return 1;
	}
	
	private Integer agregarAlimentacion(String id_base, Alimentacion alimentacion) throws SQLException {
		List<String> valores = alimentacion.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_alimenta_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idAlimenta = rs.getString("id");
			
			sql = "INSERT INTO tr_alimenta_r (id, id_data_tr, id_tr_tipo_alimenta, valor) VALUES (" +
					idAlimenta + ", " + id_base + ", " + valores.get(cont) + ", 1)";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	private Integer agregarGrupos(String id_base, Grupo grupo, int anio) throws SQLException{
		List<Pair<Integer, Integer>> valores = grupo.validarGrupos();
		if(valores.size() == 0) {
			return 0;
		}
		for (int i = 0; i < valores.size() ; i++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_grupo";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idGrupo = rs.getString("id");
			sql = "INSERT INTO tr_grupo (id, id_data_tr, id_genero, id_tr_parentesco, edad, id_tr_tramo_etario) VALUES (" +
					idGrupo + ", " + id_base + ", " + valores.get(i).getKey() + ", 1, "+valores.get(i).getValue()+", ";
			if(anio >= 2017) {
				sql =+ grupo.retornarTramoEtario2(valores.get(i).getValue())+" )";
			} else {
				sql =+ grupo.retornarTramoEtario(valores.get(i).getValue())+" )";
			}
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	private Integer agregarAlojamiento(String id_base, Alojamiento alojamiento, String nochesChile) throws SQLException {
		List<String> valores = alojamiento.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_aloja_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idAloja = rs.getString("id");
			
			sql = "INSERT INTO tr_aloja_r (id, id_data_tr, id_tr_tipo_aloja, noches) VALUES (" +
					idAloja + ", " + id_base + ", " + valores.get(cont) + ", " + nochesChile + ")";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarCiudad(String id_base, Ciudad ciudad) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_ciudades_r";
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idCiudad = rs.getString("id");
		sql = "INSERT INTO tr_ciudades_r (id, id_data_tr, id_comuna, localidad, noches) VALUES (" +
				idCiudad + ", " + id_base + ", 9999, '" + ciudad.getLocalidad() + "', " + ciudad.getNoches().toString() + ")";
		con.prepareCall(sql).executeUpdate();
		return 1;
	}

	private Integer agregarAcompañamiento(String id_base, Grupo grupo) throws SQLException{
		List<Integer> valores = grupo.getValoresValidos();
		if(valores.size() == 0){
			return 0;
		}
		List<Integer> valoresSinDuplicados = new ArrayList<>(new HashSet<>(valores));
		for (int i = 0; i < valoresSinDuplicados.size() ; i++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_acompañamiento_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idAcompañamiento = rs.getString("id");
			sql = "INSERT INTO tr_acompañamiento_r (id, id_tr_base, id_parentesco) VALUES (" +
					idAcompañamiento + ", " + id_base + ", " + valoresSinDuplicados.get(i) + " )";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	private Integer agregarFinancia(String id_base, Financia financia) throws SQLException {
		List<String> valores = financia.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_financia_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idFinancia = rs.getString("id");
			
			sql = "INSERT INTO tr_financia_r (id, id_data_tr, id_tr_financia) VALUES (" +
					idFinancia + ", " + id_base + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarGasto(String base_id, Gasto gasto) throws SQLException {
		List<String> valores = gasto.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont = cont + 2) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_gastos_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idGastos = rs.getString("id");
			
			sql = "INSERT INTO tr_gastos_r (id, id_data_tr, id_tr_gasto, monto, id_tr_moneda, monto_usd, porc_tarjeta) VALUES (" +
					idGastos + ", " + base_id + ", " + valores.get(cont + 1).toString() + ", " + 
					valores.get(cont).toString() + ", 23, " + valores.get(cont).toString() + ", 0)";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarMediosInformacion(String base_id, MediosInformacion mediosInformacion) throws SQLException {
		List<String> valores = mediosInformacion.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_medios_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idMedios = rs.getString("id");
			
			sql = "INSERT INTO tr_medios_r (id, id_data_tr, id_tr_medio) VALUES (" +
					idMedios + ", " + base_id + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarMotivos(String base_id, Integer motivo) throws SQLException {
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_motivos_r";
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String idMotivo = rs.getString("id");
		sql = "INSERT INTO tr_motivos_r (id, id_data_tr, id_tr_motivo) VALUES (" +
				idMotivo + ", " + base_id + ", " + motivo.toString() + ")";
		con.prepareCall(sql).executeUpdate();
		return 1;
	}
	
	private Integer agregarOrganiza(String base_id, Organiza organiza) throws SQLException {
		List<String> valores = organiza.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_organiza_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idOrganiza = rs.getString("id");
			
			sql = "INSERT INTO tr_organiza_r (id, id_data_tr, id_tr_organiza) VALUES (" +
					idOrganiza + ", " + base_id + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarRazonViaje(String base_id, RazonViaje razonViaje) throws SQLException {
		List<String> valores = razonViaje.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_razon_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idRazon = rs.getString("id");
			
			sql = "INSERT INTO tr_razon_r (id, id_data_tr, id_tr_razon) VALUES (" +
					idRazon + ", " + base_id + ", " + valores.get(cont) + ")";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarServicioPaqueteTuristico(String base_id, ServicioPaqueteTuristico servicioPaqueteTuristico) throws SQLException {
		List<String> valores = servicioPaqueteTuristico.getValoresValidos();
		if(valores.size() == 0) {
			return 0;
		}
		for(int cont = 0; cont < valores.size(); cont++) {
			sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_servpt_r";
			ResultSet rs = con.prepareCall(sql).executeQuery();
			rs.next();
			String idServpt = rs.getString("id");
			
			sql = "INSERT INTO tr_servpt_r (id, id_data_tr, id_tr_servpt, tipo_agencia) VALUES (" +
					idServpt + ", " + base_id + ", " + valores.get(cont) + ", 1)";
			con.prepareCall(sql).executeUpdate();
		}
		return 1;
	}
	
	private Integer agregarPais(String base_id, String id_residencia) throws SQLException {
		int resultado = 0;
		sql = "SELECT COALESCE(MAX(id),0)+1 AS id FROM tr_paises_r";
		ResultSet rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String id_paises_tr = rs.getString("id");
		
		sql = "SELECT UPPER(p.nombre) as nombre_pais, UPPER(c.nombre) as nombre_continente " +
				"FROM pais p, continente c WHERE p.id = " + id_residencia + " AND p.id_continente = c.id";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		String nombrePais = rs.getString("nombre_pais");
		String nombreContinente = rs.getString("nombre_continente");
		
		if(id_residencia == "1102") {
			nombrePais = "EE.UU.";
		}
		
		
		sql = "SELECT id FROM tr_pais trp WHERE upper(nombre) = '" + nombrePais + "'";
		rs = con.prepareCall(sql).executeQuery();
		rs.next();
		
		String idTrPais = rs.getString("id");
		if(idTrPais.isEmpty()) {
			sql = "SELECT id FROM tr_pais WHERE UPPER(nombre) like '%" + nombreContinente + "%' ORDER BY id";
			rs = con.prepareCall(sql).executeQuery();
			rs.next();
			idTrPais = rs.getString("id");
			if(idTrPais.isEmpty()) {
				idTrPais = "20"; //Resto del mundo
			}
		}
		sql = "INSERT INTO tr_paises_r (id, id_data_tr, id_tr_pais, id_tr_es) VALUES (" + 
				id_paises_tr + ", " + base_id + ", " + idTrPais + ", 1)";
		resultado = con.prepareCall(sql).executeUpdate();
		
		return resultado;
	}
	
}
