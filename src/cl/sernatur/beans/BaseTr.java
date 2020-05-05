package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_tr_base")

	public class BaseTr {

	@Column(name="id")
	private Integer id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="codigo_tr")
	private Integer codigo_tr;

	@Column(name="id_encuesta")
	private String id_encuesta;

	@Column(name="codigo_interno")
	private Integer codigo_interno;

	@Column(name="fecha")
	private String fecha;

	@Column(name="segmento")
	private Integer segmento;

	@Column(name="encuestador")
	private String encuestador;

	@Column(name="id_paso")
	private Integer id_paso;

	@Column(name="id_idioma")
	private Integer id_idioma;

	@Column(name="id_anio")
	private Integer id_anio;

	@Column(name="id_mes")
	private Integer id_mes;

	@Column(name="id_mes_trimestre")
	private Integer id_mes_trimestre;

	@Column(name="id_mes_semestre")
	private Integer id_mes_semestre;

	@Column(name="id_nacionalidad")
	private Integer id_nacionalidad;

	@Column(name="id_residencia1")
	private Integer id_residencia1;

	@Column(name="id_residencia")
	private Integer id_residencia;

	@Column(name="ciudad")
	private String ciudad;

	@Column(name="id_tipo_persona")
	private Integer id_tipo_persona;

	@Column(name="grupo_mujer")
	private Integer grupo_mujer;

	@Column(name="grupo_hombre")
	private Integer grupo_hombre;

	@Column(name="grupo_total")
	private Integer grupo_total;

	@Column(name="primera_visita")
	private Integer primera_visita;

	@Column(name="visitas_ultimo_mes")
	private Integer visitas_ultimo_mes;

	@Column(name="id_tr_frecuencia")
	private Integer id_tr_frecuencia;

	@Column(name="id_tr_planifica")
	private Integer id_tr_planifica;

	@Column(name="id_motivo_principal")
	private Integer id_motivo_principal;

	@Column(name="noches_chile")
	private Integer noches_chile;

	@Column(name="noches_fuera_prev")
	private Integer noches_fuera_prev;

	@Column(name="noches_fuera_next")
	private Integer noches_fuera_next;

	@Column(name="id_tr_transporte_entrada")
	private Integer id_tr_transporte_entrada;

	@Column(name="id_patente_entrada")
	private Integer id_patente_entrada;

	@Column(name="vehiculo_modalidad_entrada")
	private Integer vehiculo_modalidad_entrada;

	@Column(name="id_clase_pasaje_entrada")
	private Integer id_clase_pasaje_entrada;

	@Column(name="id_linea_aerea_entrada")
	private Integer id_linea_aerea_entrada;

	@Column(name="numero_vuelo_entrada")
	private Integer numero_vuelo_entrada;

	@Column(name="monto_pasaje_entrada")
	private Float monto_pasaje_entrada;

	@Column(name="id_moneda_entrada")
	private Integer id_moneda_entrada;

	@Column(name="monto_pasaje_entrada_usd")
	private Float monto_pasaje_entrada_usd;

	@Column(name="id_tr_transporte_salida")
	private Integer id_tr_transporte_salida;

	@Column(name="id_patente_salida")
	private Integer id_patente_salida;

	@Column(name="vehiculo_modalidad_salida")
	private Integer vehiculo_modalidad_salida;

	@Column(name="tipo_pasaje_aereo_salida")
	private Integer tipo_pasaje_aereo_salida;

	@Column(name="id_linea_aerea_salida")
	private Integer id_linea_aerea_salida;

	@Column(name="numero_vuelo_salida")
	private Integer numero_vuelo_salida;

	@Column(name="monto_pasaje_salida")
	private Float monto_pasaje_salida;

	@Column(name="id_moneda_salida")
	private Integer id_moneda_salida;

	@Column(name="monto_pasaje_salida_usd")
	private Float monto_pasaje_salida_usd;

	@Column(name="pasaje_idavuelta")
	private Integer pasaje_idavuelta;

	@Column(name="monto_idavuelta")
	private Integer monto_idavuelta;

	@Column(name="id_moneda_idavuelta")
	private Integer id_moneda_idavuelta;

	@Column(name="monto_idavuelta_usd")
	private Float monto_idavuelta_usd;

	@Column(name="agencia_online")
	private String agencia_online;

	@Column(name="gasto_agencia_online")
	private Integer gasto_agencia_online;

	@Column(name="moneda_agencia_online")
	private Integer moneda_agencia_online;

	@Column(name="gasto_agencia_online_usd")
	private Float gasto_agencia_online_usd;

	@Column(name="gasto_agencia_online_tarjeta")
	private Float gasto_agencia_online_tarjeta;

	@Column(name="id_tr_aloja_agencia_online")
	private Integer id_tr_aloja_agencia_online;

	@Column(name="id_tr_alimenta_agencia_online")
	private Integer id_tr_alimenta_agencia_online;

	@Column(name="agencia_viaje")
	private String agencia_viaje;

	@Column(name="gasto_agencia_viaje")
	private Integer gasto_agencia_viaje;

	@Column(name="moneda_agencia_viaje")
	private Integer moneda_agencia_viaje;

	@Column(name="gasto_agencia_viaje_usd")
	private Float gasto_agencia_viaje_usd;

	@Column(name="gasto_agencia_viaje_tarjeta")
	private Float gasto_agencia_viaje_tarjeta;

	@Column(name="id_tr_aloja_agencia_viaje")
	private Integer id_tr_aloja_agencia_viaje;

	@Column(name="id_tr_alimenta_agencia_viaje")
	private Integer id_tr_alimenta_agencia_viaje;

	@Column(name="gasto_total_alojamiento")
	private Float gasto_total_alojamiento;

	@Column(name="gasto_total_grupo")
	private Float gasto_total_grupo;

	@Column(name="id_moneda_total_grupo")
	private Integer id_moneda_total_grupo;

	@Column(name="gasto_total_grupo_usd")
	private Float gasto_total_grupo_usd;

	@Column(name="gasto_total_grupo_tarjeta")
	private Float gasto_total_grupo_tarjeta;

	@Column(name="gasto_total")
	private Float gasto_total;

	@Column(name="divisas")
	private Float divisas;

	@Column(name="gpdi")
	private Float gpdi;

	@Column(name="id_gpdi_tramo")
	private Integer id_gpdi_tramo;

	@Column(name="fe")
	private Float fe;

	@Column(name="fe_hombre")
	private Float fe_hombre;

	@Column(name="fe_mujer")
	private Float fe_mujer;

	@Column(name="dia_turista")
	private Integer dia_turista;

	@Column(name="satisfaccion1")
	private Integer satisfaccion1;

	@Column(name="satisfaccion")
	private Integer satisfaccion;

	@Column(name="nps1")
	private Integer nps1;

	@Column(name="nps")
	private Integer nps;

	@Column(name="observacion")
	private String observacion;

	@Column(name="tmp_trans1_sum")
	private Integer tmp_trans1_sum;

	@Column(name="tmp_ttasum")
	private Integer tmp_ttasum;

	@Column(name="tmp_indice2")
	private Float tmp_indice2;

	@Column(name="tmp_prop")
	private Float tmp_prop;

	@Column(name="descripcion")
	private String descripcion;

	@Column(name="id_tipo_activo")
	private Integer id_tipo_activo;

	@Column(name="codigo_carga")
	private Integer codigo_carga;

	@Column(name="monto_pt")
	private Float monto_pt;

	@Column(name="moneda_pt")
	private Integer moneda_pt;

	@Column(name="monto_pt_usd")
	private Float monto_pt_usd;
		
		
	public BaseTr() {

	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getCodigo_tr() {
		return codigo_tr;
	}


	public void setCodigo_tr(Integer codigo_tr) {
		this.codigo_tr = codigo_tr;
	}


	public String getId_encuesta() {
		return id_encuesta;
	}


	public void setId_encuesta(String id_encuesta) {
		this.id_encuesta = id_encuesta;
	}


	public Integer getCodigo_interno() {
		return codigo_interno;
	}


	public void setCodigo_interno(Integer codigo_interno) {
		this.codigo_interno = codigo_interno;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Integer getSegmento() {
		return segmento;
	}


	public void setSegmento(Integer segmento) {
		this.segmento = segmento;
	}


	public String getEncuestador() {
		return encuestador;
	}


	public void setEncuestador(String encuestador) {
		this.encuestador = encuestador;
	}


	public Integer getId_paso() {
		return id_paso;
	}


	public void setId_paso(Integer id_paso) {
		this.id_paso = id_paso;
	}


	public Integer getId_idioma() {
		return id_idioma;
	}


	public void setId_idioma(Integer id_idioma) {
		this.id_idioma = id_idioma;
	}


	public Integer getId_anio() {
		return id_anio;
	}


	public void setId_anio(Integer id_anio) {
		this.id_anio = id_anio;
	}


	public Integer getId_mes() {
		return id_mes;
	}


	public void setId_mes(Integer id_mes) {
		this.id_mes = id_mes;
	}


	public Integer getId_mes_trimestre() {
		return id_mes_trimestre;
	}


	public void setId_mes_trimestre(Integer id_mes_trimestre) {
		this.id_mes_trimestre = id_mes_trimestre;
	}


	public Integer getId_mes_semestre() {
		return id_mes_semestre;
	}


	public void setId_mes_semestre(Integer id_mes_semestre) {
		this.id_mes_semestre = id_mes_semestre;
	}


	public Integer getId_nacionalidad() {
		return id_nacionalidad;
	}


	public void setId_nacionalidad(Integer id_nacionalidad) {
		this.id_nacionalidad = id_nacionalidad;
	}


	public Integer getId_residencia1() {
		return id_residencia1;
	}


	public void setId_residencia1(Integer id_residencia1) {
		this.id_residencia1 = id_residencia1;
	}


	public Integer getId_residencia() {
		return id_residencia;
	}


	public void setId_residencia(Integer id_residencia) {
		this.id_residencia = id_residencia;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public Integer getId_tipo_persona() {
		return id_tipo_persona;
	}


	public void setId_tipo_persona(Integer id_tipo_persona) {
		this.id_tipo_persona = id_tipo_persona;
	}


	public Integer getGrupo_mujer() {
		return grupo_mujer;
	}


	public void setGrupo_mujer(Integer grupo_mujer) {
		this.grupo_mujer = grupo_mujer;
	}


	public Integer getGrupo_hombre() {
		return grupo_hombre;
	}


	public void setGrupo_hombre(Integer grupo_hombre) {
		this.grupo_hombre = grupo_hombre;
	}


	public Integer getGrupo_total() {
		return grupo_total;
	}


	public void setGrupo_total(Integer grupo_total) {
		this.grupo_total = grupo_total;
	}


	public Integer getPrimera_visita() {
		return primera_visita;
	}


	public void setPrimera_visita(Integer primera_visita) {
		this.primera_visita = primera_visita;
	}


	public Integer getVisitas_ultimo_mes() {
		return visitas_ultimo_mes;
	}


	public void setVisitas_ultimo_mes(Integer visitas_ultimo_mes) {
		this.visitas_ultimo_mes = visitas_ultimo_mes;
	}


	public Integer getId_tr_frecuencia() {
		return id_tr_frecuencia;
	}


	public void setId_tr_frecuencia(Integer id_tr_frecuencia) {
		this.id_tr_frecuencia = id_tr_frecuencia;
	}


	public Integer getId_tr_planifica() {
		return id_tr_planifica;
	}


	public void setId_tr_planifica(Integer id_tr_planifica) {
		this.id_tr_planifica = id_tr_planifica;
	}


	public Integer getId_motivo_principal() {
		return id_motivo_principal;
	}


	public void setId_motivo_principal(Integer id_motivo_principal) {
		this.id_motivo_principal = id_motivo_principal;
	}


	public Integer getNoches_chile() {
		return noches_chile;
	}


	public void setNoches_chile(Integer noches_chile) {
		this.noches_chile = noches_chile;
	}


	public Integer getNoches_fuera_prev() {
		return noches_fuera_prev;
	}


	public void setNoches_fuera_prev(Integer noches_fuera_prev) {
		this.noches_fuera_prev = noches_fuera_prev;
	}


	public Integer getNoches_fuera_next() {
		return noches_fuera_next;
	}


	public void setNoches_fuera_next(Integer noches_fuera_next) {
		this.noches_fuera_next = noches_fuera_next;
	}


	public Integer getId_tr_transporte_entrada() {
		return id_tr_transporte_entrada;
	}


	public void setId_tr_transporte_entrada(Integer id_tr_transporte_entrada) {
		this.id_tr_transporte_entrada = id_tr_transporte_entrada;
	}


	public Integer getId_patente_entrada() {
		return id_patente_entrada;
	}


	public void setId_patente_entrada(Integer id_patente_entrada) {
		this.id_patente_entrada = id_patente_entrada;
	}


	public Integer getVehiculo_modalidad_entrada() {
		return vehiculo_modalidad_entrada;
	}


	public void setVehiculo_modalidad_entrada(Integer vehiculo_modalidad_entrada) {
		this.vehiculo_modalidad_entrada = vehiculo_modalidad_entrada;
	}


	public Integer getId_clase_pasaje_entrada() {
		return id_clase_pasaje_entrada;
	}


	public void setId_clase_pasaje_entrada(Integer id_clase_pasaje_entrada) {
		this.id_clase_pasaje_entrada = id_clase_pasaje_entrada;
	}


	public Integer getId_linea_aerea_entrada() {
		return id_linea_aerea_entrada;
	}


	public void setId_linea_aerea_entrada(Integer id_linea_aerea_entrada) {
		this.id_linea_aerea_entrada = id_linea_aerea_entrada;
	}


	public Integer getNumero_vuelo_entrada() {
		return numero_vuelo_entrada;
	}


	public void setNumero_vuelo_entrada(Integer numero_vuelo_entrada) {
		this.numero_vuelo_entrada = numero_vuelo_entrada;
	}


	public Float getMonto_pasaje_entrada() {
		return monto_pasaje_entrada;
	}


	public void setMonto_pasaje_entrada(Float monto_pasaje_entrada) {
		this.monto_pasaje_entrada = monto_pasaje_entrada;
	}


	public Integer getId_moneda_entrada() {
		return id_moneda_entrada;
	}


	public void setId_moneda_entrada(Integer id_moneda_entrada) {
		this.id_moneda_entrada = id_moneda_entrada;
	}


	public Float getMonto_pasaje_entrada_usd() {
		return monto_pasaje_entrada_usd;
	}


	public void setMonto_pasaje_entrada_usd(Float monto_pasaje_entrada_usd) {
		this.monto_pasaje_entrada_usd = monto_pasaje_entrada_usd;
	}


	public Integer getId_tr_transporte_salida() {
		return id_tr_transporte_salida;
	}


	public void setId_tr_transporte_salida(Integer id_tr_transporte_salida) {
		this.id_tr_transporte_salida = id_tr_transporte_salida;
	}


	public Integer getId_patente_salida() {
		return id_patente_salida;
	}


	public void setId_patente_salida(Integer id_patente_salida) {
		this.id_patente_salida = id_patente_salida;
	}


	public Integer getVehiculo_modalidad_salida() {
		return vehiculo_modalidad_salida;
	}


	public void setVehiculo_modalidad_salida(Integer vehiculo_modalidad_salida) {
		this.vehiculo_modalidad_salida = vehiculo_modalidad_salida;
	}


	public Integer getTipo_pasaje_aereo_salida() {
		return tipo_pasaje_aereo_salida;
	}


	public void setTipo_pasaje_aereo_salida(Integer tipo_pasaje_aereo_salida) {
		this.tipo_pasaje_aereo_salida = tipo_pasaje_aereo_salida;
	}


	public Integer getId_linea_aerea_salida() {
		return id_linea_aerea_salida;
	}


	public void setId_linea_aerea_salida(Integer id_linea_aerea_salida) {
		this.id_linea_aerea_salida = id_linea_aerea_salida;
	}


	public Integer getNumero_vuelo_salida() {
		return numero_vuelo_salida;
	}


	public void setNumero_vuelo_salida(Integer numero_vuelo_salida) {
		this.numero_vuelo_salida = numero_vuelo_salida;
	}


	public Float getMonto_pasaje_salida() {
		return monto_pasaje_salida;
	}


	public void setMonto_pasaje_salida(Float monto_pasaje_salida) {
		this.monto_pasaje_salida = monto_pasaje_salida;
	}


	public Integer getId_moneda_salida() {
		return id_moneda_salida;
	}


	public void setId_moneda_salida(Integer id_moneda_salida) {
		this.id_moneda_salida = id_moneda_salida;
	}


	public Float getMonto_pasaje_salida_usd() {
		return monto_pasaje_salida_usd;
	}


	public void setMonto_pasaje_salida_usd(Float monto_pasaje_salida_usd) {
		this.monto_pasaje_salida_usd = monto_pasaje_salida_usd;
	}


	public Integer getPasaje_idavuelta() {
		return pasaje_idavuelta;
	}


	public void setPasaje_idavuelta(Integer pasaje_idavuelta) {
		this.pasaje_idavuelta = pasaje_idavuelta;
	}


	public Integer getMonto_idavuelta() {
		return monto_idavuelta;
	}


	public void setMonto_idavuelta(Integer monto_idavuelta) {
		this.monto_idavuelta = monto_idavuelta;
	}


	public Integer getId_moneda_idavuelta() {
		return id_moneda_idavuelta;
	}


	public void setId_moneda_idavuelta(Integer id_moneda_idavuelta) {
		this.id_moneda_idavuelta = id_moneda_idavuelta;
	}


	public Float getMonto_idavuelta_usd() {
		return monto_idavuelta_usd;
	}


	public void setMonto_idavuelta_usd(Float monto_idavuelta_usd) {
		this.monto_idavuelta_usd = monto_idavuelta_usd;
	}


	public String getAgencia_online() {
		return agencia_online;
	}


	public void setAgencia_online(String agencia_online) {
		this.agencia_online = agencia_online;
	}


	public Integer getGasto_agencia_online() {
		return gasto_agencia_online;
	}


	public void setGasto_agencia_online(Integer gasto_agencia_online) {
		this.gasto_agencia_online = gasto_agencia_online;
	}


	public Integer getMoneda_agencia_online() {
		return moneda_agencia_online;
	}


	public void setMoneda_agencia_online(Integer moneda_agencia_online) {
		this.moneda_agencia_online = moneda_agencia_online;
	}


	public Float getGasto_agencia_online_usd() {
		return gasto_agencia_online_usd;
	}


	public void setGasto_agencia_online_usd(Float gasto_agencia_online_usd) {
		this.gasto_agencia_online_usd = gasto_agencia_online_usd;
	}


	public Float getGasto_agencia_online_tarjeta() {
		return gasto_agencia_online_tarjeta;
	}


	public void setGasto_agencia_online_tarjeta(Float gasto_agencia_online_tarjeta) {
		this.gasto_agencia_online_tarjeta = gasto_agencia_online_tarjeta;
	}


	public Integer getId_tr_aloja_agencia_online() {
		return id_tr_aloja_agencia_online;
	}


	public void setId_tr_aloja_agencia_online(Integer id_tr_aloja_agencia_online) {
		this.id_tr_aloja_agencia_online = id_tr_aloja_agencia_online;
	}


	public Integer getId_tr_alimenta_agencia_online() {
		return id_tr_alimenta_agencia_online;
	}


	public void setId_tr_alimenta_agencia_online(Integer id_tr_alimenta_agencia_online) {
		this.id_tr_alimenta_agencia_online = id_tr_alimenta_agencia_online;
	}


	public String getAgencia_viaje() {
		return agencia_viaje;
	}


	public void setAgencia_viaje(String agencia_viaje) {
		this.agencia_viaje = agencia_viaje;
	}


	public Integer getGasto_agencia_viaje() {
		return gasto_agencia_viaje;
	}


	public void setGasto_agencia_viaje(Integer gasto_agencia_viaje) {
		this.gasto_agencia_viaje = gasto_agencia_viaje;
	}


	public Integer getMoneda_agencia_viaje() {
		return moneda_agencia_viaje;
	}


	public void setMoneda_agencia_viaje(Integer moneda_agencia_viaje) {
		this.moneda_agencia_viaje = moneda_agencia_viaje;
	}


	public Float getGasto_agencia_viaje_usd() {
		return gasto_agencia_viaje_usd;
	}


	public void setGasto_agencia_viaje_usd(Float gasto_agencia_viaje_usd) {
		this.gasto_agencia_viaje_usd = gasto_agencia_viaje_usd;
	}


	public Float getGasto_agencia_viaje_tarjeta() {
		return gasto_agencia_viaje_tarjeta;
	}


	public void setGasto_agencia_viaje_tarjeta(Float gasto_agencia_viaje_tarjeta) {
		this.gasto_agencia_viaje_tarjeta = gasto_agencia_viaje_tarjeta;
	}


	public Integer getId_tr_aloja_agencia_viaje() {
		return id_tr_aloja_agencia_viaje;
	}


	public void setId_tr_aloja_agencia_viaje(Integer id_tr_aloja_agencia_viaje) {
		this.id_tr_aloja_agencia_viaje = id_tr_aloja_agencia_viaje;
	}


	public Integer getId_tr_alimenta_agencia_viaje() {
		return id_tr_alimenta_agencia_viaje;
	}


	public void setId_tr_alimenta_agencia_viaje(Integer id_tr_alimenta_agencia_viaje) {
		this.id_tr_alimenta_agencia_viaje = id_tr_alimenta_agencia_viaje;
	}


	public Float getGasto_total_alojamiento() {
		return gasto_total_alojamiento;
	}


	public void setGasto_total_alojamiento(Float gasto_total_alojamiento) {
		this.gasto_total_alojamiento = gasto_total_alojamiento;
	}


	public Float getGasto_total_grupo() {
		return gasto_total_grupo;
	}


	public void setGasto_total_grupo(Float gasto_total_grupo) {
		this.gasto_total_grupo = gasto_total_grupo;
	}


	public Integer getId_moneda_total_grupo() {
		return id_moneda_total_grupo;
	}


	public void setId_moneda_total_grupo(Integer id_moneda_total_grupo) {
		this.id_moneda_total_grupo = id_moneda_total_grupo;
	}


	public Float getGasto_total_grupo_usd() {
		return gasto_total_grupo_usd;
	}


	public void setGasto_total_grupo_usd(Float gasto_total_grupo_usd) {
		this.gasto_total_grupo_usd = gasto_total_grupo_usd;
	}


	public Float getGasto_total_grupo_tarjeta() {
		return gasto_total_grupo_tarjeta;
	}


	public void setGasto_total_grupo_tarjeta(Float gasto_total_grupo_tarjeta) {
		this.gasto_total_grupo_tarjeta = gasto_total_grupo_tarjeta;
	}


	public Float getGasto_total() {
		return gasto_total;
	}


	public void setGasto_total(Float gasto_total) {
		this.gasto_total = gasto_total;
	}


	public Float getDivisas() {
		return divisas;
	}


	public void setDivisas(Float divisas) {
		this.divisas = divisas;
	}


	public Float getGpdi() {
		return gpdi;
	}


	public void setGpdi(Float gpdi) {
		this.gpdi = gpdi;
	}


	public Integer getId_gpdi_tramo() {
		return id_gpdi_tramo;
	}


	public void setId_gpdi_tramo(Integer id_gpdi_tramo) {
		this.id_gpdi_tramo = id_gpdi_tramo;
	}


	public Float getFe() {
		return fe;
	}


	public void setFe(Float fe) {
		this.fe = fe;
	}


	public Float getFe_hombre() {
		return fe_hombre;
	}


	public void setFe_hombre(Float fe_hombre) {
		this.fe_hombre = fe_hombre;
	}


	public Float getFe_mujer() {
		return fe_mujer;
	}


	public void setFe_mujer(Float fe_mujer) {
		this.fe_mujer = fe_mujer;
	}


	public Integer getDia_turista() {
		return dia_turista;
	}


	public void setDia_turista(Integer dia_turista) {
		this.dia_turista = dia_turista;
	}


	public Integer getSatisfaccion1() {
		return satisfaccion1;
	}


	public void setSatisfaccion1(Integer satisfaccion1) {
		this.satisfaccion1 = satisfaccion1;
	}


	public Integer getSatisfaccion() {
		return satisfaccion;
	}


	public void setSatisfaccion(Integer satisfaccion) {
		this.satisfaccion = satisfaccion;
	}


	public Integer getNps1() {
		return nps1;
	}


	public void setNps1(Integer nps1) {
		this.nps1 = nps1;
	}


	public Integer getNps() {
		return nps;
	}


	public void setNps(Integer nps) {
		this.nps = nps;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Integer getTmp_trans1_sum() {
		return tmp_trans1_sum;
	}


	public void setTmp_trans1_sum(Integer tmp_trans1_sum) {
		this.tmp_trans1_sum = tmp_trans1_sum;
	}


	public Integer getTmp_ttasum() {
		return tmp_ttasum;
	}


	public void setTmp_ttasum(Integer tmp_ttasum) {
		this.tmp_ttasum = tmp_ttasum;
	}


	public Float getTmp_indice2() {
		return tmp_indice2;
	}


	public void setTmp_indice2(Float tmp_indice2) {
		this.tmp_indice2 = tmp_indice2;
	}


	public Float getTmp_prop() {
		return tmp_prop;
	}


	public void setTmp_prop(Float tmp_prop) {
		this.tmp_prop = tmp_prop;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getId_tipo_activo() {
		return id_tipo_activo;
	}


	public void setId_tipo_activo(Integer id_tipo_activo) {
		this.id_tipo_activo = id_tipo_activo;
	}


	public Integer getCodigo_carga() {
		return codigo_carga;
	}


	public void setCodigo_carga(Integer codigo_carga) {
		this.codigo_carga = codigo_carga;
	}


	public Float getMonto_pt() {
		return monto_pt;
	}


	public void setMonto_pt(Float monto_pt) {
		this.monto_pt = monto_pt;
	}


	public Integer getMoneda_pt() {
		return moneda_pt;
	}


	public void setMoneda_pt(Integer moneda_pt) {
		this.moneda_pt = moneda_pt;
	}


	public Float getMonto_pt_usd() {
		return monto_pt_usd;
	}


	public void setMonto_pt_usd(Float monto_pt_usd) {
		this.monto_pt_usd = monto_pt_usd;
	}
	
}