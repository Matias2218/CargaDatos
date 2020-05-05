package cl.sernatur.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_ti")

	public class BaseTip {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="codigo_encuesta")
		private Integer codigo_encuesta;
		
		@Column(name="id_ti_origen")
		private Integer id_ti_origen;
		
		@Column(name="ronda")
		private Integer ronda;
		
		@Column(name="iteracion")
		private Integer iteracion;
		
		@Column(name="fe_persona")
		private double fe_persona;
		
		@Column(name="fe_hogar")
		private double fe_hogar;
		
		@Column(name="id_ti_tipo_viaje")
		private Integer id_ti_tipo_viaje;
		
		@Column(name="id_svdf")
		private Integer id_svdf;
		
		@Column(name="noches_fuera")
		private Integer noches_fuera;
		
		@Column(name="personas_viajan")
		private Integer personas_viajan;
		
		@Column(name="gasto_total")
		private Double gasto_total;
		
		@Column(name="gasto_promedio_svdf")
		private Double gasto_promedio_svdf;
		
		@Column(name="gasto_promedio_total_individual")
		private Double gasto_promedio_total_individual;
		
		@Column(name="numero_viajes")
		private Integer numero_viajes;
		
		@Column(name="numero_viajes_modificado")
		private Integer numero_viajes_modificado;
		
		@Column(name="gasto_promedio_diario_individual")
		private Double gasto_promedio_diario_individual;
		
		@Column(name="id_feriado")
		private Integer id_feriado;
		
		@Column(name="fecha_inicio")
		private String fecha_inicio;
		
		@Column(name="id_region_origen")
		private Integer id_region_origen;
		
		@Column(name="id_comuna_origen")
		private Integer id_comuna_origen;
		
		@Column(name="id_region_destino")
		private Integer id_region_destino;
		
		@Column(name="id_comuna_destino")
		private Integer id_comuna_destino;
		
		@Column(name="id_ti_destino")
		private Integer id_ti_destino;
		
		@Column(name="id_ti_medio_transporte")
		private Integer id_ti_medio_transporte;
		
		@Column(name="id_ti_medio_transporte_otro")
		private Integer id_ti_medio_transporte_otro;
		
		@Column(name="id_ti_alojamiento")
		private Integer id_ti_alojamiento;
		
		@Column(name="id_ti_alojamiento_otro")
		private Integer id_ti_alojamiento_otro;
		
		@Column(name="id_ti_alojamiento_tipo")
		private Integer id_ti_alojamiento_tipo;
		
		@Column(name="id_ti_motivo_viaje")
		private Integer id_ti_motivo_viaje;
		
		@Column(name="id_ti_motivo_viaje_otro")
		private Integer id_ti_motivo_viaje_otro;
		
		@Column(name="id_nse")
		private Integer id_nse;
		
		@Column(name="qcc_1")
		private Integer qcc_1;
		
		@Column(name="qcc_2")
		private Double qcc_2;
		
		@Column(name = "descripcion")
		private String descripcion;
		
		@Column(name = "id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		@Column(name="lista_gasto_item")
		private List<BaseTipGastoItem> lista_gasto_item;
		
		
		public BaseTip() {

		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public Integer getId() {
			return id;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}		
		
		public String getNombre() {
			return nombre;
		}

		public void setId_anio(Integer id_anio) {
			this.id_anio = id_anio;
		}

		public Integer getId_anio() {
			return id_anio;
		}

		public void setCodigo_encuesta(Integer codigo_encuesta) {
			this.codigo_encuesta = codigo_encuesta;
		}

		public Integer getCodigo_encuesta() {
			return codigo_encuesta;
		}

		public void setId_ti_origen(Integer id_ti_origen) {
			this.id_ti_origen = id_ti_origen;
		}

		public Integer getId_ti_origen() {
			return id_ti_origen;
		}

		public void setRonda(Integer ronda) {
			this.ronda = ronda;
		}

		public Integer getRonda() {
			return ronda;
		}

		public void setIteracion(Integer iteracion) {
			this.iteracion = iteracion;
		}

		public Integer getIteracion() {
			return iteracion;
		}

		public void setFe_persona(Double fe_persona) {
			this.fe_persona = fe_persona;
		}

		public Double getFe_persona() {
			return fe_persona;
		}

		public void setFe_hogar(double fe_hogar) {
			this.fe_hogar = fe_hogar;
		}

		public double getFe_hogar() {
			return fe_hogar;
		}

		public void setId_ti_tipo_viaje(Integer id_ti_tipo_viaje) {
			this.id_ti_tipo_viaje = id_ti_tipo_viaje;
		}

		public Integer getId_ti_tipo_viaje() {
			return id_ti_tipo_viaje;
		}

		public void setId_svdf(Integer id_svdf) {
			this.id_svdf = id_svdf;
		}

		public Integer getId_svdf() {
			return id_svdf;
		}

		public void setNoches_fuera(Integer noches_fuera) {
			this.noches_fuera = noches_fuera;
		}

		public Integer getNoches_fuera() {
			return noches_fuera;
		}

		public void setPersonas_viajan(Integer personas_viajan) {
			this.personas_viajan = personas_viajan;
		}

		public Integer getPersonas_viajan() {
			return personas_viajan;
		}

		public void setGasto_total(Double gasto_total) {
			this.gasto_total = gasto_total;
		}

		public Double getGasto_total() {
			return gasto_total;
		}

		public void setGasto_promedio_svdf(Double gasto_promedio_svdf) {
			this.gasto_promedio_svdf = gasto_promedio_svdf;
		}

		public Double getGasto_promedio_svdf() {
			return gasto_promedio_svdf;
		}

		public void setGasto_promedio_total_individual(Double gasto_promedio_total_individual) {
			this.gasto_promedio_total_individual = gasto_promedio_total_individual;
		}

		public Double getGasto_promedio_total_individual() {
			return gasto_promedio_total_individual;
		}

		public void setNumero_viajes(Integer numero_viajes) {
			this.numero_viajes = numero_viajes;
		}

		public Integer getNumero_viajes() {
			return numero_viajes;
		}

		public void setNumero_viajes_modificado(Integer numero_viajes_modificado) {
			this.numero_viajes_modificado = numero_viajes_modificado;
		}

		public Integer getNumero_viajes_modificado() {
			return numero_viajes_modificado;
		}

		public void setGasto_promedio_diario_individual(Double gasto_promedio_diario_individual) {
			this.gasto_promedio_diario_individual = gasto_promedio_diario_individual;
		}

		public Double getGasto_promedio_diario_individual() {
			return gasto_promedio_diario_individual;
		}

		public void setId_feriado(Integer id_feriado) {
			this.id_feriado = id_feriado;
		}

		public Integer getId_feriado() {
			return id_feriado;
		}

		public void setFecha_inicio(String fecha_inicio) {
			this.fecha_inicio = fecha_inicio;
		}

		public String getFecha_inicio() {
			return fecha_inicio;
		}

		public void setId_region_origen(Integer id_region_origen) {
			this.id_region_origen = id_region_origen;
		}

		public Integer getId_region_origen() {
			return id_region_origen;
		}

		public void setId_comuna_origen(Integer id_comuna_origen) {
			this.id_comuna_origen = id_comuna_origen;
		}

		public Integer getId_comuna_origen() {
			return id_comuna_origen;
		}

		public void setId_region_destino(Integer id_region_destino) {
			this.id_region_destino = id_region_destino;
		}

		public Integer getId_region_destino() {
			return id_region_destino;
		}

		public void setId_comuna_destino(Integer id_comuna_destino) {
			this.id_comuna_destino = id_comuna_destino;
		}

		public Integer getId_comuna_destino() {
			return id_comuna_destino;
		}

		public void setId_ti_destino(Integer id_ti_destino) {
			this.id_ti_destino = id_ti_destino;
		}

		public Integer getId_ti_destino() {
			return id_ti_destino;
		}

		public void setId_ti_medio_transporte(Integer id_ti_medio_transporte) {
			this.id_ti_medio_transporte = id_ti_medio_transporte;
		}

		public Integer getId_ti_medio_transporte() {
			return id_ti_medio_transporte;
		}

		public void setId_ti_medio_transporte_otro(Integer id_ti_medio_transporte_otro) {
			this.id_ti_medio_transporte_otro = id_ti_medio_transporte_otro;
		}

		public Integer getId_ti_medio_transporte_otro() {
			return id_ti_medio_transporte_otro;
		}

		public void setId_ti_alojamiento(Integer id_ti_alojamiento) {
			this.id_ti_alojamiento = id_ti_alojamiento;
		}

		public Integer getId_ti_alojamiento() {
			return id_ti_alojamiento;
		}

		public void setId_ti_alojamiento_otro(Integer id_ti_alojamiento_otro) {
			this.id_ti_alojamiento_otro = id_ti_alojamiento_otro;
		}

		public Integer getId_ti_alojamiento_otro() {
			return id_ti_alojamiento_otro;
		}

		public void setId_ti_alojamiento_tipo(Integer id_ti_alojamiento_tipo) {
			this.id_ti_alojamiento_tipo = id_ti_alojamiento_tipo;
		}

		public Integer getId_ti_alojamiento_tipo() {
			return id_ti_alojamiento_tipo;
		}

		public void setId_ti_motivo_viaje(Integer id_ti_motivo_viaje) {
			this.id_ti_motivo_viaje = id_ti_motivo_viaje;
		}

		public Integer getId_ti_motivo_viaje() {
			return id_ti_motivo_viaje;
		}

		public void setId_ti_motivo_viaje_otro(Integer id_ti_motivo_viaje_otro) {
			this.id_ti_motivo_viaje_otro = id_ti_motivo_viaje_otro;
		}

		public Integer getId_ti_motivo_viaje_otro() {
			return id_ti_motivo_viaje_otro;
		}

		public void setId_nse(Integer id_nse) {
			this.id_nse = id_nse;
		}

		public Integer getId_nse() {
			return id_nse;
		}

		public void setQcc_1(Integer qcc_1) {
			this.qcc_1 = qcc_1;
		}

		public Integer getQcc_1() {
			return qcc_1;
		}

		public void setQcc_2(Double qcc_2) {
			this.qcc_2 = qcc_2;
		}

		public Double getQcc_2() {
			return qcc_2;
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
		
		public void setCodigo_carga(Integer codigo_carga) {
			this.codigo_carga = codigo_carga;
		}	 	
			
		public Integer getCodigo_carga() {
			return codigo_carga;
		}
		
		public void setLista_gasto_item(List<BaseTipGastoItem> lista_gasto_item) {
			this.lista_gasto_item = lista_gasto_item;
		}
		
		public List<BaseTipGastoItem> getLista_gasto_item() {
			return lista_gasto_item;
		}
		
}