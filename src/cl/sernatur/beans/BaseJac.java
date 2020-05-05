package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_jac")

	public class BaseJac {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_mes")
		private Integer id_mes;
		
		@Column(name="codigo_aeropuerto_origen")
		private String codigo_aeropuerto_origen;
		
		@Column(name="codigo_aeropuerto_destino")
		private String codigo_aeropuerto_destino;
		
		@Column(name="id_region_omt_origen")
		private Integer id_region_omt_origen;
		
		@Column(name="id_pais_origen")
		private Integer id_pais_origen;
		
		@Column(name="id_ciudad_origen")
		private Integer id_ciudad_origen;
		
		@Column(name="id_region_omt_destino")
		private Integer id_region_omt_destino;
		
		@Column(name="id_pais_destino")
		private Integer id_pais_destino;
		
		@Column(name="id_ciudad_destino")
		private Integer id_ciudad_destino;
		
		@Column(name="codigo_linea_aerea")
		private String codigo_linea_aerea;
		
		@Column(name="id_tipo_nacional")
		private Integer id_tipo_nacional;
		
		@Column(name="pasajeros_llegada")
		private Integer pasajeros_llegada;
		
		@Column(name="pasajeros_salida")
		private Integer pasajeros_salida;
		
		@Column(name="pasajero_km")
		private Integer pasajero_km;
		
		@Column(name="carga_llegada")
		private Float carga_llegada;
		
		@Column(name="carga_salida")
		private Float carga_salida;
		
		@Column(name="carga_km")
		private Float carga_km;
		
		@Column(name="distancia")
		private Integer distancia;
		
		@Column(name = "descripcion")
		private String descripcion;
		
		@Column(name = "id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseJac() {

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

		public void setCodigo_aeropuerto_origen(String codigo_aeropuerto_origen) {
			this.codigo_aeropuerto_origen = codigo_aeropuerto_origen;
		}

		public String getCodigo_aeropuerto_origen() {
			return codigo_aeropuerto_origen;
		}

		public void setCodigo_aeropuerto_destino(String codigo_aeropuerto_destino) {
			this.codigo_aeropuerto_destino = codigo_aeropuerto_destino;
		}

		public String getCodigo_aeropuerto_destino() {
			return codigo_aeropuerto_destino;
		}

		public void setId_region_omt_origen(Integer id_region_omt_origen) {
			this.id_region_omt_origen = id_region_omt_origen;
		}

		public Integer getId_region_omt_origen() {
			return id_region_omt_origen;
		}

		public void setId_pais_origen(Integer id_pais_origen) {
			this.id_pais_origen = id_pais_origen;
		}

		public Integer getId_pais_origen() {
			return id_pais_origen;
		}

		public void setId_ciudad_origen(Integer id_ciudad_origen) {
			this.id_ciudad_origen = id_ciudad_origen;
		}

		public Integer getId_ciudad_origen() {
			return id_ciudad_origen;
		}

		public void setId_region_omt_destino(Integer id_region_omt_destino) {
			this.id_region_omt_destino = id_region_omt_destino;
		}

		public Integer getId_region_omt_destino() {
			return id_region_omt_destino;
		}

		public void setId_pais_destino(Integer id_pais_destino) {
			this.id_pais_destino = id_pais_destino;
		}

		public Integer getId_pais_destino() {
			return id_pais_destino;
		}

		public void setId_ciudad_destino(Integer id_ciudad_destino) {
			this.id_ciudad_destino = id_ciudad_destino;
		}

		public Integer getId_ciudad_destino() {
			return id_ciudad_destino;
		}

		public void setCodigo_linea_aerea(String codigo_linea_aerea) {
			this.codigo_linea_aerea = codigo_linea_aerea;
		}

		public String getCodigo_linea_aerea() {
			return codigo_linea_aerea;
		}

		public void setId_tipo_nacional(Integer id_tipo_nacional) {
			this.id_tipo_nacional = id_tipo_nacional;
		}

		public Integer getId_tipo_nacional() {
			return id_tipo_nacional;
		}

		public void setPasajeros_llegada(Integer pasajeros_llegada) {
			this.pasajeros_llegada = pasajeros_llegada;
		}

		public Integer getPasajeros_llegada() {
			return pasajeros_llegada;
		}

		public void setPasajeros_salida(Integer pasajeros_salida) {
			this.pasajeros_salida = pasajeros_salida;
		}

		public Integer getPasajeros_salida() {
			return pasajeros_salida;
		}

		public void setPasajero_km(Integer pasajero_km) {
			this.pasajero_km = pasajero_km;
		}

		public Integer getPasajero_km() {
			return pasajero_km;
		}

		public void setCarga_llegada(Float carga_llegada) {
			this.carga_llegada = carga_llegada;
		}

		public Float getCarga_llegada() {
			return carga_llegada;
		}

		public void setCarga_salida(Float carga_salida) {
			this.carga_salida = carga_salida;
		}

		public Float getCarga_salida() {
			return carga_salida;
		}

		public void setCarga_km(Float carga_km) {
			this.carga_km = carga_km;
		}

		public Float getCarga_km() {
			return carga_km;
		}

		public void setDistancia(Integer distancia) {
			this.distancia = distancia;
		}

		public Integer getDistancia() {
			return distancia;
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
		
}