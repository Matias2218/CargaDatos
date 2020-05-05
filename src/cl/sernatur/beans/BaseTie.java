package cl.sernatur.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_ti_excursion")
public class BaseTie {
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "id_anio")
	private Integer id_anio;
	
	@Column(name = "codigo_encuesta")
	private Integer codigo_encuesta;
	
	@Column(name = "id_ti_origen")
	private Integer id_ti_origen;
	
	@Column(name = "id_region_origen")
	private Integer id_region_origen;
	
	@Column(name = "id_provincia_origen")
	private Integer id_provincia_origen;
	
	@Column(name = "id_comuna_origen")
	private Integer id_comuna_origen;
	
	@Column(name = "id_destino")
	private Integer id_destino;
	
	@Column(name = "id_region_destino")
	private Integer id_region_destino;
	
	@Column(name = "id_provincia_destino")
	private Integer id_provincia_destino;
	
	@Column(name = "id_comuna_destino")
	private Integer id_comuna_destino;
	
	@Column(name = "dato1")
	private Integer dato1;
	
	@Column(name = "dato2")
	private Integer dato2;
	
	@Column(name = "dato3")
	private Integer dato3;
	
	@Column(name = "dato4")
	private Integer dato4;
	
	@Column(name = "dato5")
	private Integer dato5;
	
	@Column(name = "dato6")
	private Integer dato6;
	
	@Column(name = "dato7")
	private Integer dato7;
	
	@Column(name = "dato8")
	private Integer dato8;
	
	@Column(name = "dato9")
	private Integer dato9;
	
	@Column(name = "dato10")
	private Integer dato10;
	
	@Column(name = "dato11")
	private Integer dato11;
	
	@Column(name = "dato12")
	private Integer dato12;
	
	@Column(name = "id_motivo")
	private Integer id_motivo;
	
	@Column(name = "id_organiza")
	private Integer id_organiza;
	
	@Column(name = "id_nse")
	private Integer id_nse;
	
	@Column(name = "personas_viajan")
	private Integer personas_viajan;
	
	@Column(name = "total_gasto")
	private Integer total_gasto;
	
	@Column(name = "fact_ajustado_viv")
	private Float fact_ajustado_viv;
	
	@Column(name = "fa_vexcursion")
	private Float fa_vexcursion;
	
	@Column(name = "fact_ajustado_viv_ajuste2")
	private Float fact_ajustado_viv_ajuste2;
	
	@Column(name = "factor_personas_viajan")
	private Float factor_personas_viajan;
	
	@Column(name = "codigo_carga")
	private Integer codigo_carga;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "id_tipo_activo")
	private Integer id_tipo_activo;
	
	public BaseTie() {
		
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

	public Integer getCodigo_encuesta() {
		return codigo_encuesta;
	}

	public void setCodigo_encuesta(Integer codigo_encuesta) {
		this.codigo_encuesta = codigo_encuesta;
	}

	public Integer getId_ti_origen() {
		return id_ti_origen;
	}

	public void setId_ti_origen(Integer id_ti_origen) {
		this.id_ti_origen = id_ti_origen;
	}

	public Integer getId_region_origen() {
		return id_region_origen;
	}

	public void setId_region_origen(Integer id_region_origen) {
		this.id_region_origen = id_region_origen;
	}

	public Integer getId_provincia_origen() {
		return id_provincia_origen;
	}

	public void setId_provincia_origen(Integer id_provincia_origen) {
		this.id_provincia_origen = id_provincia_origen;
	}

	public Integer getId_comuna_origen() {
		return id_comuna_origen;
	}

	public void setId_comuna_origen(Integer id_comuna_origen) {
		this.id_comuna_origen = id_comuna_origen;
	}

	public Integer getId_destino() {
		return id_destino;
	}

	public void setId_destino(Integer id_destino) {
		this.id_destino = id_destino;
	}

	public Integer getId_region_destino() {
		return id_region_destino;
	}

	public void setId_region_destino(Integer id_region_destino) {
		this.id_region_destino = id_region_destino;
	}

	public Integer getId_provincia_destino() {
		return id_provincia_destino;
	}

	public void setId_provincia_destino(Integer id_provincia_destino) {
		this.id_provincia_destino = id_provincia_destino;
	}

	public Integer getId_comuna_destino() {
		return id_comuna_destino;
	}

	public void setId_comuna_destino(Integer id_comuna_destino) {
		this.id_comuna_destino = id_comuna_destino;
	}

	public Integer getDato1() {
		return dato1;
	}

	public void setDato1(Integer dato1) {
		this.dato1 = dato1;
	}

	public Integer getDato2() {
		return dato2;
	}

	public void setDato2(Integer dato2) {
		this.dato2 = dato2;
	}

	public Integer getDato3() {
		return dato3;
	}

	public void setDato3(Integer dato3) {
		this.dato3 = dato3;
	}

	public Integer getDato4() {
		return dato4;
	}

	public void setDato4(Integer dato4) {
		this.dato4 = dato4;
	}

	public Integer getDato5() {
		return dato5;
	}

	public void setDato5(Integer dato5) {
		this.dato5 = dato5;
	}

	public Integer getDato6() {
		return dato6;
	}

	public void setDato6(Integer dato6) {
		this.dato6 = dato6;
	}

	public Integer getDato7() {
		return dato7;
	}

	public void setDato7(Integer dato7) {
		this.dato7 = dato7;
	}

	public Integer getDato8() {
		return dato8;
	}

	public void setDato8(Integer dato8) {
		this.dato8 = dato8;
	}

	public Integer getDato9() {
		return dato9;
	}

	public void setDato9(Integer dato9) {
		this.dato9 = dato9;
	}

	public Integer getDato10() {
		return dato10;
	}

	public void setDato10(Integer dato10) {
		this.dato10 = dato10;
	}

	public Integer getDato11() {
		return dato11;
	}

	public void setDato11(Integer dato11) {
		this.dato11 = dato11;
	}

	public Integer getDato12() {
		return dato12;
	}

	public void setDato12(Integer dato12) {
		this.dato12 = dato12;
	}

	public Integer getId_motivo() {
		return id_motivo;
	}

	public void setId_motivo(Integer id_motivo) {
		this.id_motivo = id_motivo;
	}

	public Integer getId_organiza() {
		return id_organiza;
	}

	public void setId_organiza(Integer id_organiza) {
		this.id_organiza = id_organiza;
	}

	public Integer getId_nse() {
		return id_nse;
	}

	public void setId_nse(Integer id_nse) {
		this.id_nse = id_nse;
	}

	public Integer getPersonas_viajan() {
		return personas_viajan;
	}

	public void setPersonas_viajan(Integer personas_viajan) {
		this.personas_viajan = personas_viajan;
	}

	public Integer getTotal_gasto() {
		return total_gasto;
	}

	public void setTotal_gasto(Integer total_gasto) {
		this.total_gasto = total_gasto;
	}

	public Float getFact_ajustado_viv() {
		return fact_ajustado_viv;
	}

	public void setFact_ajustado_viv(Float fact_ajustado_viv) {
		this.fact_ajustado_viv = fact_ajustado_viv;
	}

	public Float getFa_vexcursion() {
		return fa_vexcursion;
	}

	public void setFa_vexcursion(Float fa_vexcursion) {
		this.fa_vexcursion = fa_vexcursion;
	}

	public Float getFact_ajustado_viv_ajuste2() {
		return fact_ajustado_viv_ajuste2;
	}

	public void setFact_ajustado_viv_ajuste2(Float fact_ajustado_viv_ajuste2) {
		this.fact_ajustado_viv_ajuste2 = fact_ajustado_viv_ajuste2;
	}

	public Float getFactor_personas_viajan() {
		return factor_personas_viajan;
	}

	public void setFactor_personas_viajan(Float factor_personas_viajan) {
		this.factor_personas_viajan = factor_personas_viajan;
	}

	public Integer getCodigo_carga() {
		return codigo_carga;
	}

	public void setCodigo_carga(Integer codigo_carga) {
		this.codigo_carga = codigo_carga;
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
