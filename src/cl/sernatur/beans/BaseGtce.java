package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_gtce")

public class BaseGtce {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="id_tarjeta")
	private Integer id_tarjeta;
	
	@Column(name="id_anio")
	private Integer id_anio;
	
	@Column(name="id_mes")
	private Integer id_mes;
	
	@Column(name="id_region_omt")
	private Integer id_region_omt;
	
	@Column(name="id_subregion_omt")
	private Integer id_subregion_omt;
	
	@Column(name="id_pais")
	private Integer id_pais;
	
	@Column(name="id_gtce_pais")
	private Integer id_gtce_pais;
	
	@Column(name="id_region")
	private Integer id_region;
	
	@Column(name="id_provincia")
	private Integer id_provincia;
	
	@Column(name="id_comuna")
	private Integer id_comuna;
	
	@Column(name="id_gtce_rubro")
	private Integer id_gtce_rubro;
	
	@Column(name="id_gtce_subrubro")
	private Integer id_gtce_subrubro;
	
	@Column(name="id_gtce_modalidad")
	private Integer id_gtce_modalidad;
	
	@Column(name="numero_transacciones")
	private Integer numero_transacciones;

	@Column(name="total_uf")
	private double total_uf;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="id_tipo_activo")
	private Integer id_tipo_activo;
	
	@Column(name="codigo_carga")
	private Integer codigo_carga;
	
	
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

	public void setId_tarjeta(Integer val) {
		this.id_tarjeta = val;
	}	 

	public Integer getId_tarjeta() {
		return id_tarjeta;
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

	public void setId_region_omt(Integer id_region_omt) {
		this.id_region_omt = id_region_omt;
	}	 

	public Integer getId_region_omt() {
		return id_region_omt;
	}
	
	public void setId_subregion_omt(Integer id_subregion_omt) {
		this.id_subregion_omt = id_subregion_omt;
	}	 

	public Integer getId_subregion_omt() {
		return id_subregion_omt;
	}
	
	public void setId_pais(Integer id_pais) {
		this.id_pais = id_pais;
	}	 

	public Integer getId_pais() {
		return id_pais;
	}

	public void setId_gtce_pais(Integer id_gtce_pais) {
		this.id_gtce_pais = id_gtce_pais;
	}	 

	public Integer getId_gtce_pais() {
		return id_gtce_pais;
	}

	public void setId_region(Integer id_region) {
		this.id_region = id_region;
	}	 

	public Integer getId_region() {
		return id_region;
	}

	public void setId_provincia(Integer id_provincia) {
		this.id_provincia = id_provincia;
	}	 

	public Integer getId_provincia() {
		return id_provincia;
	}

	public void setId_comuna(Integer id_comuna) {
		this.id_comuna = id_comuna;
	}	 

	public Integer getId_comuna() {
		return id_comuna;
	}

	public void setId_gtce_rubro(Integer id_gtce_rubro) {
		this.id_gtce_rubro = id_gtce_rubro;
	}	 

	public Integer getId_gtce_rubro() {
		return id_gtce_rubro;
	}

	public void setId_gtce_subrubro(Integer val) {
		this.id_gtce_subrubro = val;
	}	 

	public Integer getId_gtce_subrubro() {
		return id_gtce_subrubro;
	}
	
	public void setId_gtce_modalidad(Integer id_gtce_modalidad) {
		this.id_gtce_modalidad = id_gtce_modalidad;
	}	 

	public Integer getId_gtce_modalidad() {
		return id_gtce_modalidad;
	}

	public void setNumero_transacciones(Integer numero_transacciones) {
		this.numero_transacciones = numero_transacciones;
	}	 

	public Integer getNumero_transacciones() {
		return numero_transacciones;
	}

	public void setTotal_uf(double total_uf) {
		this.total_uf = total_uf;
	}	 

	public double getTotal_uf() {
		return total_uf;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	 	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setCodigo_carga(Integer codigo_carga) {
		this.codigo_carga = codigo_carga;
	}	 	
	
	public Integer getCodigo_carga() {
		return codigo_carga;
	}
	
	public void setId_tipo_activo(Integer id_tipo_activo) {
		this.id_tipo_activo = id_tipo_activo;
	}	 

	public Integer getId_tipo_activo() {
		return id_tipo_activo;
	}

}