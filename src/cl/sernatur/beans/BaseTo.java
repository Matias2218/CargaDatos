package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_to")

	public class BaseTo {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_feriado")
		private Integer id_feriado;
		
		@Column(name="id_region")
		private Integer id_region;
		
		@Column(name="id_destino")
		private Integer id_destino;
		
		@Column(name="tasa")
		private Double tasa;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseTo() {

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

		public void setId_feriado(Integer id_feriado) {
			this.id_feriado = id_feriado;
		}

		public Integer getId_feriado() {
			return id_feriado;
		}
		
		public void setId_region(Integer id_region) {
			this.id_region = id_region;
		}

		public Integer getId_region() {
			return id_region;
		}
		
		public void setId_destino(Integer id_destino) {
			this.id_destino = id_destino;
		}

		public Integer getId_destino() {
			return id_destino;
		}

		public void setTasa(Double tasa) {
			this.tasa = tasa;
		}

		public Double getTasa() {
			return tasa;
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