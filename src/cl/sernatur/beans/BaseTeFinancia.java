package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "te_financia_r")

	public class BaseTeFinancia  {
		
		//private static final long serialVersionUID = 1L;
		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="id_data_te")
		private Integer id_data_te;
		
		@Column(name="id_te_financia")
		private Integer id_te_financia;
				
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getId_data_te() {
			return id_data_te;
		}

		public void setId_data_te(Integer id_data_te) {
			this.id_data_te = id_data_te;
		}

		public Integer getId_te_financia() {
			return id_te_financia;
		}

		public void setId_te_financia(Integer id_te_financia) {
			this.id_te_financia = id_te_financia;
		}

}