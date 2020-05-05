package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_iit")

	public class BaseIit {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name="nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_comuna")
		private Integer id_comuna;
		
		@Column(name="poblacion_flotante")
		private Double poblacion_flotante;
		
		@Column(name="venta_total_uf")
		private Double venta_total_uf;
		
		@Column(name="ventas_sii")
		private Double ventas_sii;
		
		@Column(name="trabajadores_sii")
		private Double trabajadores_sii;
		
		@Column(name="renta_neta_trabajadores_sii")
		private Double renta_neta_trabajadores_sii;
		
		@Column(name="pernocta_vivienda_particular")
		private Double pernocta_vivienda_particular;
		
		@Column(name="unidades_alojamiento")
		private Integer unidades_alojamiento;
		
		@Column(name="plazas_emat")
		private Double plazas_emat;
		
		@Column(name="llegadas_emat")
		private Double llegadas_emat;
		
		@Column(name="pernocta_emat")
		private Double pernocta_emat;
		
		@Column(name="alojamiento_registro")
		private Integer alojamiento_registro;
		
		@Column(name="turismo_aventura_registro")
		private Integer turismo_aventura_registro;
		
		@Column(name="empresas_sii")
		private Double empresas_sii;
		
		@Column(name="snaspe")
		private Double snaspe;
		
		@Column(name="atractivos")
		private Integer atractivos;

		@Column(name="indice_iit")
		private Double indice_iit;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseIit() {

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
		
		public void setId_comuna(Integer id_comuna) {
			this.id_comuna = id_comuna;
		}

		public Integer getId_comuna() {
			return id_comuna;
		}

		public void setPoblacion_flotante(Double poblacion_flotante) {
			this.poblacion_flotante = poblacion_flotante;
		}

		public Double getPoblacion_flotante() {
			return poblacion_flotante;
		}
		
		public void setVenta_total_uf(Double venta_total_uf) {
			this.venta_total_uf = venta_total_uf;
		}

		public Double getVenta_total_uf() {
			return venta_total_uf;
		}
		
		public void setVentas_sii(Double ventas_sii) {
			this.ventas_sii = ventas_sii;
		}

		public Double getVentas_sii() {
			return ventas_sii;
		}
		
		public void setTrabajadores_sii(Double trabajadores_sii) {
			this.trabajadores_sii = trabajadores_sii;
		}

		public Double getTrabajadores_sii() {
			return trabajadores_sii;
		}
		
		public void setRenta_neta_trabajadores_sii(Double renta_neta_trabajadores_sii) {
			this.renta_neta_trabajadores_sii = renta_neta_trabajadores_sii;
		}

		public Double getRenta_neta_trabajadores_sii() {
			return renta_neta_trabajadores_sii;
		}
		
		public void setPernocta_vivienda_particular(Double pernocta_vivienda_particular) {
			this.pernocta_vivienda_particular = pernocta_vivienda_particular;
		}

		public Double getPernocta_vivienda_particular() {
			return pernocta_vivienda_particular;
		}
		
		public void setUnidades_alojamiento(Integer unidades_alojamiento) {
			this.unidades_alojamiento = unidades_alojamiento;
		}

		public Integer getUnidades_alojamiento() {
			return unidades_alojamiento;
		}
		
		public void setPlazas_emat(Double plazas_emat) {
			this.plazas_emat = plazas_emat;
		}

		public Double getPlazas_emat() {
			return plazas_emat;
		}
		
		public void setLlegadas_emat(Double llegadas_emat) {
			this.llegadas_emat = llegadas_emat;
		}

		public Double getLlegadas_emat() {
			return llegadas_emat;
		}
		
		public void setPernocta_emat(Double pernocta_emat) {
			this.pernocta_emat = pernocta_emat;
		}

		public Double getPernocta_emat() {
			return pernocta_emat;
		}
		
		public void setAlojamiento_registro(Integer alojamiento_registro) {
			this.alojamiento_registro = alojamiento_registro;
		}

		public Integer getAlojamiento_registro() {
			return alojamiento_registro;
		}
		
		public void setTurismo_aventura_registro(Integer turismo_aventura_registro) {
			this.turismo_aventura_registro = turismo_aventura_registro;
		}

		public Integer getTurismo_aventura_registro() {
			return turismo_aventura_registro;
		}
		
		public void setEmpresas_sii(Double empresas_sii) {
			this.empresas_sii = empresas_sii;
		}

		public Double getEmpresas_sii() {
			return empresas_sii;
		}
		
		public void setSnaspe(Double snaspe) {
			this.snaspe = snaspe;
		}

		public Double getSnaspe() {
			return snaspe;
		}
		
		public void setAtractivos(Integer atractivos) {
			this.atractivos = atractivos;
		}

		public Integer getAtractivos() {
			return atractivos;
		}
		
		public void setIndice_iit(Double indice_iit) {
			this.indice_iit = indice_iit;
		}

		public Double getIndice_iit() {
			return indice_iit;
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