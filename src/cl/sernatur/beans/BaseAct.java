package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_act")

	public class BaseAct {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "id_anio")
		private Integer id_anio;
		
		@Column(name = "id_region")
		private Integer id_region;
		
		@Column(name = "id_provincia")
		private Integer id_provincia;
		
		@Column(name = "id_comuna")
		private Integer id_comuna;
		
		@Column(name = "id_act")
		private Integer id_act;
		
		@Column(name = "id_acteco")
		private Integer id_acteco;
		
		@Column(name = "numero_empresas")
		private Integer numero_empresas;
		
		@Column(name = "ventas_uf")
		private double ventas_uf;
		
		@Column(name = "numero_trabajadores")
		private Integer numero_trabajadores;
		
		@Column(name = "trabajadores_femenino_informado")
		private Integer trabajadores_femenino_informado;
		
		@Column(name = "trabajadores_masculino_informado")
		private Integer trabajadores_masculino_informado;
		
		@Column(name = "renta_neta")
		private double renta_neta;
		
		@Column(name = "renta_neta_femenino")
		private double renta_neta_femenino;
		
		@Column(name = "renta_neta_masculino")
		private double renta_neta_masculino;
		
		@Column(name = "trabajadores_ponderados")
		private double trabajadores_ponderados;
		
		@Column(name = "trabajadores_ponderados_femenino")
		private double trabajadores_ponderados_femenino;
		
		@Column(name = "trabajadores_ponderados_masculinos")
		private double trabajadores_ponderados_masculinos;
		
		@Column(name = "descripcion")
		private String descripcion;
		
		@Column(name = "id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		
		public BaseAct() {

		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Integer getId_anio() {
			return id_anio;
		}

		public void setId_anio(Integer id_anio) {
			this.id_anio = id_anio;
		}

		public Integer getId_region() {
			return id_region;
		}

		public void setId_region(Integer id_region) {
			this.id_region = id_region;
		}

		public Integer getId_provincia() {
			return id_provincia;
		}

		public void setId_provincia(Integer id_provincia) {
			this.id_provincia = id_provincia;
		}

		public Integer getId_comuna() {
			return id_comuna;
		}

		public void setId_comuna(Integer id_comuna) {
			this.id_comuna = id_comuna;
		}

		public Integer getId_act() {
			return id_act;
		}

		public void setId_act(Integer id_act) {
			this.id_act = id_act;
		}

		public Integer getId_acteco() {
			return id_acteco;
		}

		public void setId_acteco(Integer id_acteco) {
			this.id_acteco = id_acteco;
		}

		public Integer getNumero_empresas() {
			return numero_empresas;
		}

		public void setNumero_empresas(Integer numero_empresas) {
			this.numero_empresas = numero_empresas;
		}

		public double getVentas_uf() {
			return ventas_uf;
		}

		public void setVentas_uf(double ventas_uf) {
			this.ventas_uf = ventas_uf;
		}

		public Integer getNumero_trabajadores() {
			return numero_trabajadores;
		}

		public void setNumero_trabajadores(Integer numero_trabajadores) {
			this.numero_trabajadores = numero_trabajadores;
		}

		public Integer getTrabajadores_femenino_informado() {
			return trabajadores_femenino_informado;
		}

		public void setTrabajadores_femenino_informado(Integer trabajadores_femenino_informado) {
			this.trabajadores_femenino_informado = trabajadores_femenino_informado;
		}

		public Integer getTrabajadores_masculino_informado() {
			return trabajadores_masculino_informado;
		}

		public void setTrabajadores_masculino_informado(Integer trabajadores_masculino_informado) {
			this.trabajadores_masculino_informado = trabajadores_masculino_informado;
		}

		public Double getRenta_neta() {
			return renta_neta;
		}

		public void setRenta_neta(Double renta_neta) {
			this.renta_neta = renta_neta;
		}

		public Double getRenta_neta_femenino() {
			return renta_neta_femenino;
		}

		public void setRenta_neta_femenino(Double renta_neta_femenino) {
			this.renta_neta_femenino = renta_neta_femenino;
		}

		public Double getRenta_neta_masculino() {
			return renta_neta_masculino;
		}

		public void setRenta_neta_masculino(Double renta_neta_masculino) {
			this.renta_neta_masculino = renta_neta_masculino;
		}

		public Double getTrabajadores_ponderados() {
			return trabajadores_ponderados;
		}

		public void setTrabajadores_ponderados(Double trabajadores_ponderados) {
			this.trabajadores_ponderados = trabajadores_ponderados;
		}

		public Double getTrabajadores_ponderados_femenino() {
			return trabajadores_ponderados_femenino;
		}

		public void setTrabajadores_ponderados_femenino(Double trabajadores_ponderados_femenino) {
			this.trabajadores_ponderados_femenino = trabajadores_ponderados_femenino;
		}

		public Double getTrabajadores_ponderados_masculinos() {
			return trabajadores_ponderados_masculinos;
		}

		public void setTrabajadores_ponderados_masculinos(Double trabajadores_ponderados_masculinos) {
			this.trabajadores_ponderados_masculinos = trabajadores_ponderados_masculinos;
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