package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_pdi")

	public class BasePdi {

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
		
		@Column(name="id_region")
		private Integer id_region;
		
		@Column(name="id_paso_r5")
		private Integer id_paso_r5;
		
		@Column(name="id_paso_r4")
		private Integer id_paso_r4;
		
		@Column(name="id_paso_r3")
		private Integer id_paso_r3;
		
		@Column(name="id_paso_r2")
		private Integer id_paso_r2;
		
		@Column(name="id_paso_r1")
		private Integer id_paso_r1;
		
		@Column(name="id_paso")
		private Integer id_paso;
		
		@Column(name="id_continente")
		private Integer id_continente;
		
		@Column(name="id_pais")
		private Integer id_pais;
		
		@Column(name="total_llegadas")
		private Integer total_llegadas;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BasePdi() {

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

		public void setId_region(Integer id_region) {
			this.id_region = id_region;
		}

		public Integer getId_region() {
			return id_region;
		}

		public void setId_paso_r5(Integer id_paso_r5) {
			this.id_paso_r5 = id_paso_r5;
		}

		public Integer getId_paso_r5() {
			return id_paso_r5;
		}

		public void setId_paso_r4(Integer id_paso_r4) {
			this.id_paso_r4 = id_paso_r4;
		}

		public Integer getId_paso_r4() {
			return id_paso_r4;
		}

		public void setId_paso_r3(Integer id_paso_r3) {
			this.id_paso_r3 = id_paso_r3;
		}

		public Integer getId_paso_r3() {
			return id_paso_r3;
		}

		public void setId_paso_r2(Integer id_paso_r2) {
			this.id_paso_r2 = id_paso_r2;
		}

		public Integer getId_paso_r2() {
			return id_paso_r2;
		}

		public void setId_paso_r1(Integer id_paso_r1) {
			this.id_paso_r1 = id_paso_r1;
		}

		public Integer getId_paso_r1() {
			return id_paso_r1;
		}

		public void setId_paso(Integer id_paso) {
			this.id_paso = id_paso;
		}

		public Integer getId_paso() {
			return id_paso;
		}
			
		public void setId_continente(Integer id_continente) {
			this.id_continente = id_continente;
		}

		public Integer getId_continente() {
			return id_continente;
		}			

		public void setId_pais(Integer id_pais) {
			this.id_pais = id_pais;
		}

		public Integer getId_pais() {
			return id_pais;
		}

		public void setTotal_llegadas(Integer total_llegadas) {
			this.total_llegadas = total_llegadas;
		}

		public Integer getTotal_llegadas() {
			return total_llegadas;
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