package cl.sernatur.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data_eat")

	public class BaseEat {

		//@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "nombre")
		private Integer nombre;
		
		@Column(name="id_mes")
		private Integer id_mes;
		
		@Column(name="id_anio")
		private Integer id_anio;
		
		@Column(name="uid")
		private Integer uid;
		
		@Column(name="dias_funciona")
		private Integer dias_funciona;
		
		@Column(name="id_eat_clase_alojamiento")
		private Integer id_eat_clase_alojamiento;
		
		@Column(name="id_region")
		private Integer id_region;
		
		@Column(name="id_provincia")
		private Integer id_provincia;
		
		@Column(name="id_comuna")
		private Integer id_comuna;
		
		@Column(name="id_eat_temporada")
		private Integer id_eat_temporada;
		
		@Column(name="id_eat_destino")
		private Integer id_eat_destino;
		
		@Column(name="total_unidad_alojamiento")
		private Double total_unidad_alojamiento;
		
		@Column(name="total_plazas")
		private Double total_plazas;
		
		@Column(name="total_llegada_nacional")
		private Double total_llegada_nacional;
		
		@Column(name="total_pernocta_nacional")
		private Double total_pernocta_nacional;
		
		@Column(name="total_llegada_extranjero")
		private Double total_llegada_extranjero;
		
		@Column(name="total_pernocta_extranjero")
		private Double total_pernocta_extranjero;
		
		@Column(name="total_llegadas")
		private Double total_llegadas;
		
		@Column(name="total_pernocta")
		private Double total_pernocta;
		
		@Column(name="unidad_alojamiento_ocupada")
		private Double unidad_alojamiento_ocupada;
		
		@Column(name="plaza_adicional_instalada")
		private Double plaza_adicional_instalada;
		
		@Column(name="ingreso_neto_alojamiento")
		private Double ingreso_neto_alojamiento;
		
		@Column(name="ingreso_neto_otros")
		private Double ingreso_neto_otros;
		
		@Column(name="total_ingreso_neto")
		private Double total_ingreso_neto;
		
		@Column(name="Hab_dia_disponible")
		private Double Hab_dia_disponible;
		
		@Column(name="plaza_dia_disponible")
		private Double plaza_dia_disponible;
		
		@Column(name="factor_expansion")
		private double factor_expansion;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="id_tipo_activo")
		private Integer id_tipo_activo;
		
		@Column(name="codigo_carga")
		private Integer codigo_carga;
		
		@Column(name="lista_total")
		private List<BaseEatTotal> lista_total;
		
		@Column(name="lista_nacional")
		private List<BaseEatNacional> lista_nacional;
		
		@Column(name="lista_extranjero")
		private List<BaseEatExtranjero> lista_extranjero;
		
		
		public BaseEat() {

		}

		public void setId(Integer id) {
			this.id = id;
		}	 

		public Integer getId() {
			return id;
		}

		public void setNombre(Integer nombre) {
			this.nombre = nombre;
		}	 

		public Integer getNombre() {
			return nombre;
		}

		public void setId_mes(Integer id_mes) {
			this.id_mes = id_mes;
		}	 

		public Integer getId_mes() {
			return id_mes;
		}

		public void setId_anio(Integer id_anio) {
			this.id_anio = id_anio;
		}	 

		public Integer getId_anio() {
			return id_anio;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}	 

		public Integer getUid() {
			return uid;
		}

		public void setDias_funciona(Integer dias_funciona) {
			this.dias_funciona = dias_funciona;
		}	 

		public Integer getDias_funciona() {
			return dias_funciona;
		}

		public void setId_eat_clase_alojamiento(Integer val) {
			this.id_eat_clase_alojamiento = val;
		}	 

		public Integer getId_eat_clase_alojamiento() {
			return id_eat_clase_alojamiento;
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

		public void setId_eat_temporada(Integer id_eat_temporada) {
			this.id_eat_temporada = id_eat_temporada;
		}	 

		public Integer getId_eat_temporada() {
			return id_eat_temporada;
		}

		public void setId_eat_destino(Integer id_eat_destino) {
			this.id_eat_destino = id_eat_destino;
		}	 

		public Integer getId_eat_destino() {
			return id_eat_destino;
		}
			
		public void setTotal_unidad_alojamiento(Double total_unidad_alojamiento) {
			this.total_unidad_alojamiento = total_unidad_alojamiento;
		}	 

		public Double getTotal_unidad_alojamiento() {
			return total_unidad_alojamiento;
		}

		public void setTotal_plazas(Double total_plazas) {
			this.total_plazas = total_plazas;
		}	 

		public Double getTotal_plazas() {
			return total_plazas;
		}

		public void setTotal_llegada_nacional(Double total_llegada_nacional) {
			this.total_llegada_nacional = total_llegada_nacional;
		}	 

		public Double getTotal_llegada_nacional() {
			return total_llegada_nacional;
		}

		public void setTotal_pernocta_nacional(Double total_pernocta_nacional) {
			this.total_pernocta_nacional = total_pernocta_nacional;
		}	 

		public Double getTotal_pernocta_nacional() {
			return total_pernocta_nacional;
		}

		public void setTotal_llegada_extranjero(Double total_llegada_extranjero) {
			this.total_llegada_extranjero = total_llegada_extranjero;
		}	 

		public Double getTotal_llegada_extranjero() {
			return total_llegada_extranjero;
		}

		public void setTotal_pernocta_extranjero(Double total_pernocta_extranjero) {
			this.total_pernocta_extranjero = total_pernocta_extranjero;
		}	 

		public Double getTotal_pernocta_extranjero() {
			return total_pernocta_extranjero;
		}

		public void setTotal_llegadas(Double total_llegadas) {
			this.total_llegadas = total_llegadas;
		}	 

		public Double getTotal_llegadas() {
			return total_llegadas;
		}

		public void setTotal_pernocta(Double total_pernocta) {
			this.total_pernocta = total_pernocta;
		}	 

		public Double getTotal_pernocta() {
			return total_pernocta;
		}

		public void setUnidad_alojamiento_ocupada(Double unidad_alojamiento_ocupada) {
			this.unidad_alojamiento_ocupada = unidad_alojamiento_ocupada;
		}	 

		public Double getUnidad_alojamiento_ocupada() {
			return unidad_alojamiento_ocupada;
		}

		public void setPlaza_adicional_instalada(Double plaza_adicional_instalada) {
			this.plaza_adicional_instalada = plaza_adicional_instalada;
		}	 

		public Double getPlaza_adicional_instalada() {
			return plaza_adicional_instalada;
		}

		public void setIngreso_neto_alojamiento(Double val) {
			this.ingreso_neto_alojamiento = val;
		}	 

		public Double getIngreso_neto_alojamiento() {
			return ingreso_neto_alojamiento;
		}

		public void setIngreso_neto_otros(Double val) {
			this.ingreso_neto_otros = val;
		}	 

		public Double getIngreso_neto_otros() {
			return ingreso_neto_otros;
		}

		public void setTotal_ingreso_neto(Double val) {
			this.total_ingreso_neto = val;
		}	 

		public Double getTotal_ingreso_neto() {
			return total_ingreso_neto;
		}

		public void setHab_dia_disponible(Double Hab_dia_disponible) {
			this.Hab_dia_disponible = Hab_dia_disponible;
		}	 

		public Double getHab_dia_disponible() {
			return Hab_dia_disponible;
		}

		public void setPlaza_dia_disponible(Double plaza_dia_disponible) {
			this.plaza_dia_disponible = plaza_dia_disponible;
		}	 

		public Double getPlaza_dia_disponible() {
			return plaza_dia_disponible;
		}

		public void setFactor_expansion(double factor_expansion) {
			this.factor_expansion = factor_expansion;
		}	 

		public double getFactor_expansion() {
			return factor_expansion;
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
		
		public void setLista_total(List<BaseEatTotal> lista_total) {
			this.lista_total = lista_total;
		}
		
		public List<BaseEatTotal> getLista_total() {
			return lista_total;
		}
		
		public void setLista_nacional(List<BaseEatNacional> lista_nacional) {
			this.lista_nacional = lista_nacional;
		}
		
		public List<BaseEatNacional> getLista_nacional() {
			return lista_nacional;
		}
		
		public void setLista_extranjero(List<BaseEatExtranjero> lista_extranjero) {
			this.lista_extranjero = lista_extranjero;
		}
		
		public List<BaseEatExtranjero> getLista_extranjero() {
			return lista_extranjero;
		}

}