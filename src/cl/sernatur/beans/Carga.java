package cl.sernatur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carga")

public class Carga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="file_name")
	private String file_name;
	
	@Column(name="file_size")
	private long file_size;
	
	@Column(name="file_type")
	private String file_type;
	
	@Column(name="num_rows")
	private long num_rows;
	
	@Column(name="num_cols")
	private Integer num_cols;
	
	@Column(name="id_tipo_carga")
	private Integer id_tipo_carga;
	
	@Column(name="id_estado_carga")
	private Integer id_estado_carga;
	
	@Column(name="id_error_carga")
	private Integer id_error_carga;
	
	@Column(name="id_base")
	private Integer id_base;
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="hora")
	private String hora;
	
	@Column(name="anio_periodo_inicio")
	private Integer anio_periodo_inicio;
	
	@Column(name="mes_periodo_inicio")
	private Integer mes_periodo_inicio;
	
	@Column(name="anio_periodo_fin")
	private Integer anio_periodo_fin;
	
	@Column(name="mes_periodo_fin")
	private Integer mes_periodo_fin;		
	
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

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setNum_rows(long num_rows) {
		this.num_rows = num_rows;
	}

	public long getNum_rows() {
		return num_rows;
	}

	public void setNum_cols(Integer num_cols) {
		this.num_cols = num_cols;
	}

	public Integer getNum_cols() {
		return num_cols;
	}

	public void setId_tipo_carga(Integer id_tipo_carga) {
		this.id_tipo_carga = id_tipo_carga;
	}

	public Integer getId_tipo_carga() {
		return id_tipo_carga;
	}

	public void setId_estado_carga(Integer id_estado_carga) {
		this.id_estado_carga = id_estado_carga;
	}

	public Integer getId_estado_carga() {
		return id_estado_carga;
	}

	public void setId_error_carga(Integer id_error_carga) {
		this.id_error_carga = id_error_carga;
	}

	public Integer getId_error_carga() {
		return id_error_carga;
	}

	public void setId_base(Integer id_base) {
		this.id_base = id_base;
	}

	public Integer getId_base() {
		return id_base;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora() {
		return hora;
	}
	
	public void setAnio_periodo_inicio(Integer val) {
		this.anio_periodo_inicio = val;
	}
	
	public Integer getAnio_periodo_inicio() {
		return anio_periodo_inicio;
	}
	
	public void setMes_periodo_inicio(Integer val) {
		this.mes_periodo_inicio = val;
	}
	
	public Integer getMes_periodo_inicio() {
		return mes_periodo_inicio;
	}
	
	public void setAnio_periodo_fin(Integer val) {
		this.anio_periodo_fin = val;
	}
	
	public Integer getAnio_periodo_fin() {
		return anio_periodo_fin;
	}
	
	public void setMes_periodo_fin(Integer val) {
		this.mes_periodo_fin = val;
	}
	
	public Integer getMes_periodo_fin() {
		return mes_periodo_fin;
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
