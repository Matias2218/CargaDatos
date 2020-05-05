package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_tr_agrupa")

	public class BaseTrt {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_mes")
		private Integer id_mes;
				
		@Column(name="id_pais_reside")
		private Integer id_pais_reside;

		@Column(name="id_pais_origen")
		private Integer id_pais_origen;
		
		@Column(name="id_tr_motivo1")
		private Integer id_tr_motivo1;
		
		@Column(name="id_tr_frontera1")
		private Integer id_tr_frontera1;

		@Column(name="noches")
		private Double noches;
		
		@Column(name="dias_turista")
		private Double dias_turista;

		@Column(name="gpdi")
		private Double gpdi;
		
		@Column(name="gti")
		private Double gti;
		
		@Column(name="divisas")
		private Double divisas;
		
		@Column(name="factor_expansion")
		private Double factor_expansion;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseTrt() {

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
		
		public void setId_mes(Integer id_mes) {
			this.id_mes = id_mes;
		}

		public Integer getId_mes() {
			return id_mes;
		}

		public void setId_pais_reside(Integer id_pais_reside) {
			this.id_pais_reside = id_pais_reside;
		}

		public Integer getId_pais_reside() {
			return id_pais_reside;
		}
		
		public void setId_pais_origen(Integer id_pais_origen) {
			this.id_pais_origen = id_pais_origen;
		}

		public Integer getId_pais_origen() {
			return id_pais_origen;
		}
		
		public void setId_tr_motivo1(Integer id_tr_motivo1) {
			this.id_tr_motivo1 = id_tr_motivo1;
		}

		public Integer getId_tr_motivo1() {
			return id_tr_motivo1;
		}
		
		public void setId_tr_frontera1(Integer id_tr_frontera1) {
			this.id_tr_frontera1 = id_tr_frontera1;
		}

		public Integer getId_tr_frontera1() {
			return id_tr_frontera1;
		}
		
		public void setNoches(Double noches) {
			this.noches = noches;
		}

		public Double getNoches() {
			return noches;
		}
		
		public void setDias_turista(Double dias_turista) {
			this.dias_turista = dias_turista;
		}

		public Double getDias_turista() {
			return dias_turista;
		}
		
		public void setGpdi(Double gpdi) {
			this.gpdi = gpdi;
		}

		public Double getGpdi() {
			return gpdi;
		}
		
		public void setGti(Double gti) {
			this.gti = gti;
		}

		public Double getGti() {
			return gti;
		}
		
		public void setDivisas(Double divisas) {
			this.divisas = divisas;
		}

		public Double getDivisas() {
			return divisas;
		}
		
		public void setFactor_expansion(Double factor_expansion) {
			this.factor_expansion = factor_expansion;
		}

		public Double getFactor_expansion() {
			return factor_expansion;
		}
		
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}	 

		public String getDescripcion() {
			return descripcion;
		}

		public void setId_tipo_activo(Integer id_tipo_activo) {
			this.id_tipo_activo = id_tipo_activo;
		}	 

		public Integer getId_tipo_activo() {
			return id_tipo_activo;
		}

		public void setCodigo_carga(Integer codigo_carga) {
			this.codigo_carga = codigo_carga;
		}	 	
		
		public Integer getCodigo_carga() {
			return codigo_carga;
		}
			
}