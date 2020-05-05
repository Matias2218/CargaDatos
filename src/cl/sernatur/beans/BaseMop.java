package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_Mop")

	public class BaseMop {

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
		
		@Column(name="id_peaje")
		private Integer id_peaje;
		
		@Column(name="tv01")
		private Integer tv01;
		
		@Column(name="tv02")
		private Integer tv02;
		
		@Column(name="tv03")
		private Integer tv03;
		
		@Column(name="tv04")
		private Integer tv04;
		
		@Column(name="tv05")
		private Integer tv05;
		
		@Column(name="tv06")
		private Integer tv06;
		
		@Column(name="tv07")
		private Integer tv07;
		
		@Column(name="tv08")
		private Integer tv08;
		
		@Column(name="tv09")
		private Integer tv09;
		
		@Column(name="tv10")
		private Integer tv10;
		
		@Column(name="tv11")
		private Integer tv11;
		
		@Column(name="tv12")
		private Integer tv12;
		
		@Column(name="tv13")
		private Integer tv13;
		
		@Column(name="tv14")
		private Integer tv14;		
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseMop() {

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
		
		public void setId_peaje(Integer id_peaje) {
			this.id_peaje = id_peaje;
		}

		public Integer getId_peaje() {
			return id_peaje;
		}

		public void setTv01(Integer tv01) {
			this.tv01 = tv01;
		}

		public Integer getTv01() {
			return tv01;
		}

		public void setTv02(Integer tv02) {
			this.tv02 = tv02;
		}

		public Integer getTv02() {
			return tv02;
		}

		public void setTv03(Integer tv03) {
			this.tv03 = tv03;
		}

		public Integer getTv03() {
			return tv03;
		}

		public void setTv04(Integer tv04) {
			this.tv04 = tv04;
		}

		public Integer getTv04() {
			return tv04;
		}

		public void setTv05(Integer tv05) {
			this.tv05 = tv05;
		}

		public Integer getTv05() {
			return tv05;
		}

		public void setTv06(Integer tv06) {
			this.tv06 = tv06;
		}

		public Integer getTv06() {
			return tv06;
		}

		public void setTv07(Integer tv07) {
			this.tv07 = tv07;
		}

		public Integer getTv07() {
			return tv07;
		}

		public void setTv08(Integer tv08) {
			this.tv08 = tv08;
		}

		public Integer getTv08() {
			return tv08;
		}

		public void setTv09(Integer tv09) {
			this.tv09 = tv09;
		}

		public Integer getTv09() {
			return tv09;
		}

		public void setTv10(Integer tv10) {
			this.tv10 = tv10;
		}

		public Integer getTv10() {
			return tv10;
		}

		public void setTv11(Integer tv11) {
			this.tv11 = tv11;
		}

		public Integer getTv11() {
			return tv11;
		}

		public void setTv12(Integer tv12) {
			this.tv12 = tv12;
		}

		public Integer getTv12() {
			return tv12;
		}

		public void setTv13(Integer tv13) {
			this.tv13 = tv13;
		}

		public Integer getTv13() {
			return tv13;
		}

		public void setTv14(Integer tv14) {
			this.tv14 = tv14;
		}

		public Integer getTv14() {
			return tv14;
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