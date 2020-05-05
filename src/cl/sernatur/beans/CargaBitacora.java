package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carga_bitacora")

public class CargaBitacora {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="linea")
	private Integer linea;
	
	@Column(name="valor")
	private String valor;
	
	@Column(name="codigo_carga")
	private Integer codigo_carga;
	
	@Column(name="campo")
	private String campo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="id_tipo_activo")
	private Integer id_tipo_activo;

	
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

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Integer getLinea() {
		return linea;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public void setCampo(String val) {
		this.campo = val;
	}

	public String getCampo() {
		return campo;
	}
	
	public void setCodigo_carga(Integer codigoCarga) {
		this.codigo_carga = codigoCarga;
	}

	public Integer getCodigo_carga() {
		return codigo_carga;
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
	
}
