package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "eat_llyp_extranjero")

	public class BaseEatExtranjero {
		
		//private static final long serialVersionUID = 1L;
		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		
		@Column(name = "id")
		private Integer id;
		
		@Column(name="id_data_eat")
		private Integer id_data_eat;
		
		@Column(name="id_region")
		private Integer id_region;
		
		@Column(name="llegadas")
		private Double llegadas;
		
		@Column(name="pernocta")
		private Double pernocta;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
	
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
	
		public void setId_data_eat(Integer id_data_eat) {
			this.id_data_eat = id_data_eat;
		}
	
		public Integer getId_data_eat() {
			return id_data_eat;
		}
	
		public void setId_region(Integer id_region) {
			this.id_region = id_region;
		}
	
		public Integer getId_region() {
			return id_region;
		}
	
		public void setLlegadas(Double llegadas) {
			this.llegadas = llegadas;
		}
	
		public Double getLlegadas() {
			return llegadas;
		}
	
		public void setPernocta(Double pernocta) {
			this.pernocta = pernocta;
		}
	
		public Double getPernocta() {
			return pernocta;
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