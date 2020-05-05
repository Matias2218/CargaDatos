package cl.sernatur.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_te")

	public class BaseTe {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="id_mes")
		private Integer id_mes;
		
		@Column(name="id_mes_trimestre")
		private Integer id_mes_trimestre;
		
		@Column(name="id_mes_semestre")
		private Integer id_mes_semestre;
		
		@Column(name="codigo_id")
		private String codigo_id;
		
		@Column(name="codigo_encuestador")
		private String codigo_encuestador;
		
		@Column(name="fecha")
		private String fecha;
		
		@Column(name="id_nacionalidad")
		private Integer id_nacionalidad;
		
		@Column(name="id_region_omt")
		private Integer id_region_omt;
		
		@Column(name="id_subregion_omt")
		private Integer id_subregion_omt;
		
		@Column(name="id_pais_destino")
		private Integer id_pais_destino;
		
		@Column(name="id_te_motivo")
		private Integer id_te_motivo;
		
		@Column(name="numero_noches")
		private Integer numero_noches;
		
		@Column(name="numero_mujeres")
		private Integer numero_mujeres;
		
		@Column(name="numero_hombres")
		private Integer numero_hombres;
		
		@Column(name="numero_grupo")
		private Integer numero_grupo;
		
		@Column(name="codigo_linea_aerea_salida")
		private String codigo_linea_aerea_salida;
		
		@Column(name="codigo_linea_aerea_regreso")
		private String codigo_linea_aerea_regreso;
		
		@Column(name="vuelo_numero")
		private Integer vuelo_numero;
		
		@Column(name="total_pt")
		private Double total_pt;
		
		@Column(name="fe")
		private Double fe;
		
		@Column(name="fe_m")
		private Double fe_m;
		
		@Column(name="fe_mm")
		private Double fe_mm;
		
		@Column(name="fe_f")
		private Double fe_f;
		
		@Column(name="fe_ff")
		private Double fe_ff;
		
		@Column(name="fee")
		private Double fee;
		
		@Column(name="egreso")
		private Double egreso;
		
		@Column(name="llegadas")
		private Double llegadas;
		
		@Column(name="gpdi")
		private Double gpdi;
		
		@Column(name="gtg")
		private Double gtg;
		
		@Column(name="total_sin_pasajes")
		private Double total_sin_pasajes;
		
		@Column(name="total_con_pasajes")
		private Double total_con_pasajes;
				
		@Column(name = "descripcion")
		private String descripcion;
		
		@Column(name = "id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		@Column(name="lista_grupo")
		private List<BaseTeGrupo> lista_grupo;
		
		@Column(name="lista_financia")
		private List<BaseTeFinancia> lista_financia;
		
		
		public BaseTe() {

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


		public Integer getId_mes() {
			return id_mes;
		}


		public void setId_mes(Integer id_mes) {
			this.id_mes = id_mes;
		}


		public Integer getId_mes_trimestre() {
			return id_mes_trimestre;
		}


		public void setId_mes_trimestre(Integer id_mes_trimestre) {
			this.id_mes_trimestre = id_mes_trimestre;
		}


		public Integer getId_mes_semestre() {
			return id_mes_semestre;
		}


		public void setId_mes_semestre(Integer id_mes_semestre) {
			this.id_mes_semestre = id_mes_semestre;
		}


		public String getCodigo_id() {
			return codigo_id;
		}


		public void setCodigo_id(String codigo_id) {
			this.codigo_id = codigo_id;
		}


		public String getCodigo_encuestador() {
			return codigo_encuestador;
		}


		public void setCodigo_encuestador(String codigo_encuestador) {
			this.codigo_encuestador = codigo_encuestador;
		}


		public String getFecha() {
			return fecha;
		}


		public void setFecha(String fecha) {
			this.fecha = fecha;
		}


		public Integer getId_nacionalidad() {
			return id_nacionalidad;
		}


		public void setId_nacionalidad(Integer id_nacionalidad) {
			this.id_nacionalidad = id_nacionalidad;
		}


		public Integer getId_region_omt() {
			return id_region_omt;
		}


		public void setId_region_omt(Integer id_region_omt) {
			this.id_region_omt = id_region_omt;
		}


		public Integer getId_subregion_omt() {
			return id_subregion_omt;
		}


		public void setId_subregion_omt(Integer id_subregion_omt) {
			this.id_subregion_omt = id_subregion_omt;
		}


		public Integer getId_pais_destino() {
			return id_pais_destino;
		}


		public void setId_pais_destino(Integer id_pais_destino) {
			this.id_pais_destino = id_pais_destino;
		}


		public Integer getId_te_motivo() {
			return id_te_motivo;
		}


		public void setId_te_motivo(Integer id_te_motivo) {
			this.id_te_motivo = id_te_motivo;
		}


		public Integer getNumero_noches() {
			return numero_noches;
		}


		public void setNumero_noches(Integer numero_noches) {
			this.numero_noches = numero_noches;
		}


		public Integer getNumero_mujeres() {
			return numero_mujeres;
		}


		public void setNumero_mujeres(Integer numero_mujeres) {
			this.numero_mujeres = numero_mujeres;
		}


		public Integer getNumero_hombres() {
			return numero_hombres;
		}


		public void setNumero_hombres(Integer numero_hombres) {
			this.numero_hombres = numero_hombres;
		}


		public Integer getNumero_grupo() {
			return numero_grupo;
		}


		public void setNumero_grupo(Integer numero_grupo) {
			this.numero_grupo = numero_grupo;
		}


		public String getCodigo_linea_aerea_salida() {
			return codigo_linea_aerea_salida;
		}


		public void setCodigo_linea_aerea_salida(String codigo_linea_aerea_salida) {
			this.codigo_linea_aerea_salida = codigo_linea_aerea_salida;
		}


		public String getCodigo_linea_aerea_regreso() {
			return codigo_linea_aerea_regreso;
		}


		public void setCodigo_linea_aerea_regreso(String codigo_linea_aerea_regreso) {
			this.codigo_linea_aerea_regreso = codigo_linea_aerea_regreso;
		}


		public Integer getVuelo_numero() {
			return vuelo_numero;
		}


		public void setVuelo_numero(Integer vuelo_numero) {
			this.vuelo_numero = vuelo_numero;
		}


		public Double getTotal_pt() {
			return total_pt;
		}


		public void setTotal_pt(Double total_pt) {
			this.total_pt = total_pt;
		}


		public Double getFe() {
			return fe;
		}


		public void setFe(Double fe) {
			this.fe = fe;
		}


		public Double getFe_m() {
			return fe_m;
		}


		public void setFe_m(Double fe_m) {
			this.fe_m = fe_m;
		}


		public Double getFe_mm() {
			return fe_mm;
		}


		public void setFe_mm(Double fe_mm) {
			this.fe_mm = fe_mm;
		}


		public Double getFe_f() {
			return fe_f;
		}


		public void setFe_f(Double fe_f) {
			this.fe_f = fe_f;
		}


		public Double getFe_ff() {
			return fe_ff;
		}


		public void setFe_ff(Double fe_ff) {
			this.fe_ff = fe_ff;
		}


		public Double getFee() {
			return fee;
		}


		public void setFee(Double fee) {
			this.fee = fee;
		}


		public Double getEgreso() {
			return egreso;
		}


		public void setEgreso(Double egreso) {
			this.egreso = egreso;
		}


		public Double getLlegadas() {
			return llegadas;
		}


		public void setLlegadas(Double llegadas) {
			this.llegadas = llegadas;
		}


		public Double getGpdi() {
			return gpdi;
		}


		public void setGpdi(Double gpdi) {
			this.gpdi = gpdi;
		}


		public Double getGtg() {
			return gtg;
		}


		public void setGtg(Double gtg) {
			this.gtg = gtg;
		}
		
		
		public Double getTotal_sin_pasajes() {
			return total_sin_pasajes;
		}


		public void setTotal_sin_pasajes(Double total_sin_pasajes) {
			this.total_sin_pasajes = total_sin_pasajes;
		}
		
		
		public Double getTotal_con_pasajes() {
			return total_con_pasajes;
		}


		public void setTotal_con_pasajes(Double total_con_pasajes) {
			this.total_con_pasajes = total_con_pasajes;
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


		public Integer getCodigo_carga() {
			return codigo_carga;
		}


		public void setCodigo_carga(Integer codigo_carga) {
			this.codigo_carga = codigo_carga;
		}


		public List<BaseTeGrupo> getLista_grupo() {
			return lista_grupo;
		}


		public void setLista_grupo(List<BaseTeGrupo> lista_grupo) {
			this.lista_grupo = lista_grupo;
		}
		
		
		public List<BaseTeFinancia> getLista_financia() {
			return lista_financia;
		}


		public void setLista_financia(List<BaseTeFinancia> lista_financia) {
			this.lista_financia = lista_financia;
		}

		
}