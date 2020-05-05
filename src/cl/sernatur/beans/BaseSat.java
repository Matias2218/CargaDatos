package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_sat")

	public class BaseSat {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		

		@Column(name="nombre")
		private String nombre;
		
		@Column(name="codigo")
		private Integer codigo;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="anio_creacion")
		private Integer anio_creacion;
		
		@Column(name="id_clase_alojamiento")
		private Integer id_clase_alojamiento;
		
		@Column(name="id_region")
		private Integer id_region;
		
		@Column(name="id_provincia")
		private Integer id_provincia;
		
		@Column(name="id_comuna")
		private Integer id_comuna;
		
		@Column(name="numero_habitacion")
		private Integer numero_habitacion;
		
		@Column(name="numero_departamento")
		private Integer numero_departamento;
		
		@Column(name="numero_cabana")
		private Integer numero_cabana;
		
		@Column(name="numero_camping")
		private Integer numero_camping;
		
		@Column(name="numero_camas")
		private Integer numero_camas;
		
		@Column(name="total_unidades_alojamiento")
		private Integer total_unidades_alojamiento;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseSat() {

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

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public Integer getCodigo() {
			return codigo;
		}

		public void setId_anio(Integer id_anio) {
			this.id_anio = id_anio;
		}

		public Integer getId_anio() {
			return id_anio;
		}
			
		public void setAnio_creacion(Integer anio_creacion) {
			this.anio_creacion = anio_creacion;
		}

		public Integer getAnio_creacion() {
			return anio_creacion;
		}

		public void setId_clase_alojamiento(Integer id_clase_alojamiento) {
			this.id_clase_alojamiento = id_clase_alojamiento;
		}

		public Integer getId_clase_alojamiento() {
			return id_clase_alojamiento;
		}

		public void setId_region(Integer id_region) {
			this.id_region = id_region;
		}

		public Integer getId_region() {
			return id_region;
		}

		public void setId_provincia(Integer id_provincia) {
			this.id_provincia = id_provincia;
		}

		public Integer getId_provincia() {
			return id_provincia;
		}

		public void setId_comuna(Integer id_comuna) {
			this.id_comuna = id_comuna;
		}

		public Integer getId_comuna() {
			return id_comuna;
		}

		public void setNumero_habitacion(Integer numero_habitacion) {
			this.numero_habitacion = numero_habitacion;
		}

		public Integer getNumero_habitacion() {
			return numero_habitacion;
		}

		public void setNumero_departamento(Integer numero_departamento) {
			this.numero_departamento = numero_departamento;
		}

		public Integer getNumero_departamento() {
			return numero_departamento;
		}

		public void setNumero_cabana(Integer numero_cabana) {
			this.numero_cabana = numero_cabana;
		}

		public Integer getNumero_cabana() {
			return numero_cabana;
		}

		public void setNumero_camping(Integer numero_camping) {
			this.numero_camping = numero_camping;
		}

		public Integer getNumero_camping() {
			return numero_camping;
		}

		public void setNumero_camas(Integer numero_camas) {
			this.numero_camas = numero_camas;
		}

		public Integer getNumero_camas() {
			return numero_camas;
		}

		public void setTotal_unidades_alojamiento(Integer total_unidades_alojamiento) {
			this.total_unidades_alojamiento = total_unidades_alojamiento;
		}

		public Integer getTotal_unidades_alojamiento() {
			return total_unidades_alojamiento;
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