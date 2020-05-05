package cl.sernatur.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_ti_base")
public class BaseTib {
	
	@Column(name = "id")	
	private Integer id;
	
	@Column(name = "nombre")	
	private String nombre;
	
	@Column(name = "codigo_encuesta")	
	private Integer codigo_encuesta;
	
	@Column(name = "fecha")	
	private String fecha;
	
	@Column(name = "id_anio")	
	private Integer id_anio;
	
	@Column(name = "id_mes")	
	private Integer id_mes;
	
	@Column(name = "id_mes_trimestre")	
	private Integer id_mes_trimestre;
	
	@Column(name = "id_mes_semestre")	
	private Integer id_mes_semestre;
	
	@Column(name = "iteracion")	
	private Integer iteracion;
	
	@Column(name = "ronda")	
	private Integer ronda;
	
	@Column(name = "id_tipo_viaje")	
	private Integer id_tipo_viaje;
	
	@Column(name = "id_fdsl")	
	private Integer id_fdsl;
	
	@Column(name = "id_temporada")	
	private Integer id_temporada;
	
	@Column(name = "id_region_origen")	
	private Integer id_region_origen;
	
	@Column(name = "id_comuna_origen")	
	private Integer id_comuna_origen;
	
	@Column(name = "id_region_destino")	
	private Integer id_region_destino;
	
	@Column(name = "id_comuna_destino")	
	private Integer id_comuna_destino;
	
	@Column(name = "id_destino_emat")	
	private Integer id_destino_emat;
	
	@Column(name = "id_mtransporte")	
	private Integer id_mtransporte;
	
	@Column(name = "id_tipo_alojamiento")	
	private Integer id_tipo_alojamiento;
	
	@Column(name = "id_motivo")	
	private Integer id_motivo;

	@Column(name = "gasto_total")
	private Float gasto_total;
	
	@Column(name = "id_nse")	
	private Integer id_nse;
	
	@Column(name = "id_organiza")	
	private Integer id_organiza;
	
	@Column(name = "id_planifica")	
	private Integer id_planifica;
	
	@Column(name = "id_expectativa")	
	private Integer id_expectativa;
	
	@Column(name = "id_organiza_gasto")	
	private Integer id_organiza_gasto;
	
	@Column(name = "destino_extra_aloja")	
	private Integer destino_extra_aloja;
	
	@Column(name = "gasto_compartido_numero")	
	private Integer gasto_compartido_numero;
	
	@Column(name = "num_visitas_prev_dp")	
	private Integer num_visitas_prev_dp;
	
	@Column(name = "numero_visitas_anterior_dp")	
	private Integer numero_visitas_anterior_dp;
	
	@Column(name = "fa_viv")	
	private Float fa_viv;
	
	@Column(name = "fa_pob")	
	private Float fa_pob;
	
	@Column(name = "fa_panel")	
	private Float fa_panel;
	
	@Column(name = "f_personas_viajan")	
	private Float f_personas_viajan;
	
	@Column(name = "fe_personas")	
	private Float fe_personas;
	
	@Column(name = "fe_hogares")	
	private Float fe_hogares;
	
	@Column(name = "fa_personas_viajan_d")	
	private Float fa_personas_viajan_d;
	
	@Column(name = "fa_viv_ajuste2")	
	private Float fa_viv_ajuste2;
	
	@Column(name = "gpdi")	
	private Float gpdi;
	
	@Column(name = "ajuste_persona")	
	private Float ajuste_persona;
	
	@Column(name = "ajuste_hogar")	
	private Float ajuste_hogar;
	
	@Column(name = "obsrvacion")	
	private String obsrvacion;
	
	@Column(name = "descripcion")	
	private String descripcion;
	
	@Column(name = "id_tipo_activo")	
	private Integer id_tipo_activo;
	
	public BaseTib() {
		
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

	public Integer getCodigo_encuesta() {
		return codigo_encuesta;
	}

	public void setCodigo_encuesta(Integer codigo_encuesta) {
		this.codigo_encuesta = codigo_encuesta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	public Integer getIteracion() {
		return iteracion;
	}

	public void setIteracion(Integer iteracion) {
		this.iteracion = iteracion;
	}

	public Integer getRonda() {
		return ronda;
	}

	public void setRonda(Integer ronda) {
		this.ronda = ronda;
	}

	public Integer getId_tipo_viaje() {
		return id_tipo_viaje;
	}

	public void setId_tipo_viaje(Integer id_tipo_viaje) {
		this.id_tipo_viaje = id_tipo_viaje;
	}

	public Integer getId_fdsl() {
		return id_fdsl;
	}

	public void setId_fdsl(Integer id_fdsl) {
		this.id_fdsl = id_fdsl;
	}

	public Integer getId_temporada() {
		return id_temporada;
	}

	public void setId_temporada(Integer id_temporada) {
		this.id_temporada = id_temporada;
	}

	public Integer getId_region_origen() {
		return id_region_origen;
	}

	public void setId_region_origen(Integer id_region_origen) {
		this.id_region_origen = id_region_origen;
	}

	public Integer getId_comuna_origen() {
		return id_comuna_origen;
	}

	public void setId_comuna_origen(Integer id_comuna_origen) {
		this.id_comuna_origen = id_comuna_origen;
	}

	public Integer getId_region_destino() {
		return id_region_destino;
	}

	public void setId_region_destino(Integer id_region_destino) {
		this.id_region_destino = id_region_destino;
	}

	public Integer getId_comuna_destino() {
		return id_comuna_destino;
	}

	public void setId_comuna_destino(Integer id_comuna_destino) {
		this.id_comuna_destino = id_comuna_destino;
	}

	public Integer getId_destino_emat() {
		return id_destino_emat;
	}

	public void setId_destino_emat(Integer id_destino_emat) {
		this.id_destino_emat = id_destino_emat;
	}

	public Integer getId_mtransporte() {
		return id_mtransporte;
	}

	public void setId_mtransporte(Integer id_mtransporte) {
		this.id_mtransporte = id_mtransporte;
	}

	public Integer getId_tipo_alojamiento() {
		return id_tipo_alojamiento;
	}

	public void setId_tipo_alojamiento(Integer id_tipo_alojamiento) {
		this.id_tipo_alojamiento = id_tipo_alojamiento;
	}

	public Integer getId_motivo() {
		return id_motivo;
	}

	public void setId_motivo(Integer id_motivo) {
		this.id_motivo = id_motivo;
	}
	
	public Float getGasto_total() {
		return gasto_total;
	}

	public void setGasto_total(Float gasto_total) {
		this.gasto_total = gasto_total;
	}

	public Integer getId_nse() {
		return id_nse;
	}

	public void setId_nse(Integer id_nse) {
		this.id_nse = id_nse;
	}

	public Integer getId_organiza() {
		return id_organiza;
	}

	public void setId_organiza(Integer id_organiza) {
		this.id_organiza = id_organiza;
	}

	public Integer getId_planifica() {
		return id_planifica;
	}

	public void setId_planifica(Integer id_planifica) {
		this.id_planifica = id_planifica;
	}

	public Integer getId_expectativa() {
		return id_expectativa;
	}

	public void setId_expectativa(Integer id_expectativa) {
		this.id_expectativa = id_expectativa;
	}

	public Integer getId_organiza_gasto() {
		return id_organiza_gasto;
	}

	public void setId_organiza_gasto(Integer id_organiza_gasto) {
		this.id_organiza_gasto = id_organiza_gasto;
	}

	public Integer getDestino_extra_aloja() {
		return destino_extra_aloja;
	}

	public void setDestino_extra_aloja(Integer destino_extra_aloja) {
		this.destino_extra_aloja = destino_extra_aloja;
	}

	public Integer getGasto_compartido_numero() {
		return gasto_compartido_numero;
	}

	public void setGasto_compartido_numero(Integer gasto_compartido_numero) {
		this.gasto_compartido_numero = gasto_compartido_numero;
	}

	public Integer getNum_visitas_prev_dp() {
		return num_visitas_prev_dp;
	}

	public void setNum_visitas_prev_dp(Integer num_visitas_prev_dp) {
		this.num_visitas_prev_dp = num_visitas_prev_dp;
	}

	public Integer getNumero_visitas_anterior_dp() {
		return numero_visitas_anterior_dp;
	}

	public void setNumero_visitas_anterior_dp(Integer numero_visitas_anterior_dp) {
		this.numero_visitas_anterior_dp = numero_visitas_anterior_dp;
	}

	public Float getFa_viv() {
		return fa_viv;
	}

	public void setFa_viv(Float fa_viv) {
		this.fa_viv = fa_viv;
	}

	public Float getFa_pob() {
		return fa_pob;
	}

	public void setFa_pob(Float fa_pob) {
		this.fa_pob = fa_pob;
	}

	public Float getFa_panel() {
		return fa_panel;
	}

	public void setFa_panel(Float fa_panel) {
		this.fa_panel = fa_panel;
	}

	public Float getF_personas_viajan() {
		return f_personas_viajan;
	}

	public void setF_personas_viajan(Float f_personas_viajan) {
		this.f_personas_viajan = f_personas_viajan;
	}

	public Float getFe_personas() {
		return fe_personas;
	}

	public void setFe_personas(Float fe_personas) {
		this.fe_personas = fe_personas;
	}

	public Float getFe_hogares() {
		return fe_hogares;
	}

	public void setFe_hogares(Float fe_hogares) {
		this.fe_hogares = fe_hogares;
	}

	public Float getFa_personas_viajan_d() {
		return fa_personas_viajan_d;
	}

	public void setFa_personas_viajan_d(Float fa_personas_viajan_d) {
		this.fa_personas_viajan_d = fa_personas_viajan_d;
	}

	public Float getFa_viv_ajuste2() {
		return fa_viv_ajuste2;
	}

	public void setFa_viv_ajuste2(Float fa_viv_ajuste2) {
		this.fa_viv_ajuste2 = fa_viv_ajuste2;
	}

	public Float getGpdi() {
		return gpdi;
	}

	public void setGpdi(Float gpdi) {
		this.gpdi = gpdi;
	}

	public Float getAjuste_persona() {
		return ajuste_persona;
	}

	public void setAjuste_persona(Float ajuste_persona) {
		this.ajuste_persona = ajuste_persona;
	}

	public Float getAjuste_hogar() {
		return ajuste_hogar;
	}

	public void setAjuste_hogar(Float ajuste_hogar) {
		this.ajuste_hogar = ajuste_hogar;
	}

	public String getObsrvacion() {
		return obsrvacion;
	}

	public void setObsrvacion(String obsrvacion) {
		this.obsrvacion = obsrvacion;
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
	
	

	

}
