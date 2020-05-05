package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ti_gasto_item")

	public class BaseTipGastoItem  {
		
		//private static final long serialVersionUID = 1L;
		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		
		@Column(name = "id")
		private Integer id;
		
		@Column(name="id_data_ti")
		private Integer id_data_ti;
		
		@Column(name="id_ti_item")
		private Integer id_ti_item;
		
		@Column(name="id_ti_gasto")
		private Integer id_ti_gasto;
		
		@Column(name="valor")
		private Double valor;
		
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
	
		public void setId_data_ti(Integer id_data_ti) {
			this.id_data_ti = id_data_ti;
		}
	
		public Integer getId_data_ti() {
			return id_data_ti;
		}
	
		public void setId_ti_item(Integer id_ti_item) {
			this.id_ti_item = id_ti_item;
		}
	
		public Integer getId_ti_item() {
			return id_ti_item;
		}
	
		public void setId_ti_gasto(Integer id_ti_gasto) {
			this.id_ti_gasto = id_ti_gasto;
		}
	
		public Integer getId_ti_gasto() {
			return id_ti_gasto;
		}
	
		public void setValor(Double valor) {
			this.valor = valor;
		}
	
		public Double getValor() {
			return valor;
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