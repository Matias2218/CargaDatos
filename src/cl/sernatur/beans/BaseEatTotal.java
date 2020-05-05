package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "eat_total_ta")

	public class BaseEatTotal {
		
		//private static final long serialVersionUID = 1L;
		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		
		@Column(name = "id")
		private Integer id;
		
		@Column(name="id_data_eat")
		private Integer id_data_eat;
		
		@Column(name="id_eat_tipo_alojamiento")
		private Integer id_eat_tipo_alojamiento;
		
		@Column(name="total_ta")
		private Double total_ta;
		
		@Column(name="total_plazas")
		private Double total_plazas;
	
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
	
		public void setId_data_eat(Integer id_eat) {
			this.id_data_eat = id_eat;
		}
	
		public Integer getId_data_eat() {
			return id_data_eat;
		}
	
		public void setId_eat_tipo_alojamiento(Integer val) {
			this.id_eat_tipo_alojamiento = val;
		}
	
		public Integer getId_eat_tipo_alojamiento() {
			return id_eat_tipo_alojamiento;
		}
	
		public void setTotal_ta(Double total_ta) {
			this.total_ta = total_ta;
		}
	
		public Double getTotal_ta() {
			return total_ta;
		}
	
		public void setTotal_plazas(Double total_plazas) {
			this.total_plazas = total_plazas;
		}
	
		public Double getTotal_plazas() {
			return total_plazas;
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