package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "te_grupo")

	public class BaseTeGrupo  {
		
		//private static final long serialVersionUID = 1L;
		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="id_data_te")
		private Integer id_data_te;
		
		@Column(name="id_genero")
		private Integer id_genero;
		
		@Column(name="id_te_parentesco")
		private Integer id_te_parentesco;
		
		@Column(name="id_te_ocupacion")
		private Integer id_te_ocupacion;
		
		@Column(name="edad")
		private Integer edad;
		
		@Column(name="id_te_tramo")
		private Integer id_te_tramo;
		
		
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

		public Integer getId_genero() {
			return id_genero;
		}

		public void setId_genero(Integer id_genero) {
			this.id_genero = id_genero;
		}

		public Integer getId_te_parentesco() {
			return id_te_parentesco;
		}

		public void setId_te_parentesco(Integer id_te_parentesco) {
			this.id_te_parentesco = id_te_parentesco;
		}

		public Integer getId_te_ocupacion() {
			return id_te_ocupacion;
		}

		public void setId_te_ocupacion(Integer id_te_ocupacion) {
			this.id_te_ocupacion = id_te_ocupacion;
		}

		public Integer getEdad() {
			return edad;
		}

		public void setEdad(Integer edad) {
			this.edad = edad;
		}

		public Integer getId_te_tramo() {
			return id_te_tramo;
		}

		public void setId_te_tramo(Integer id_te_tramo) {
			this.id_te_tramo = id_te_tramo;
		}
		
}