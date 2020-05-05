package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_tr_divisas")

	public class BaseTrd {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_trimestre")
		private Integer id_trimestre;
		
		@Column(name="excursionistas")
		private Integer excursionistas;
		
		@Column(name="dv_excursion")
		private Double dv_excursion;
		
		@Column(name="dv_transporte")
		private Double dv_transporte;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseTrd() {

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
		
		public void setId_trimestre(Integer id_trimestre) {
			this.id_trimestre = id_trimestre;
		}

		public Integer getId_trimestre() {
			return id_trimestre;
		}

		public void setExcursionistas(Integer excursionistas) {
			this.excursionistas = excursionistas;
		}

		public Integer getExcursionistas() {
			return excursionistas;
		}
		
		public void setDv_excursion(Double dv_excursion) {
			this.dv_excursion = dv_excursion;
		}

		public Double getDv_excursion() {
			return dv_excursion;
		}
		
		public void setDv_transporte(Double dv_transporte) {
			this.dv_transporte = dv_transporte;
		}

		public Double getDv_transporte() {
			return dv_transporte;
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