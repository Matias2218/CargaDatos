package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionTIB {

	private Integer precioCalidadAlojamientoTuristico;
	private Integer cantidadVariedadAlojamientoTuristico;
	private Integer precioCalidadServiciosAlimentacion;
	private Integer cantidadVariedadServiciosAlimentacion;
	private Integer limpiezaGeneralEspacioPublico;
	private Integer conectividadWiFiEspaciosPublicos;
	private Integer señalizacionAtractivosServiciosTuristicos;
	private Integer precioCalidadActividadTuristicas;
	private Integer disponibilidadCentrosInfoTuristica;
	private Integer seguridadActividadesTurismoAventura;
	private Integer contaminacionAmbiental;
	private Integer disponibilidadBancosCajeros;
	private Integer disponibilidadTransporteLocal;
	private Integer satisfaccionGeneral;

	public EvaluacionTIB() {

	}

	public Integer getPrecioCalidadActividadTuristicas() {
		return precioCalidadActividadTuristicas;
	}

	public void setPrecioCalidadActividadTuristicas(Integer precioCalidadActividadTuristicas) {
		this.precioCalidadActividadTuristicas = precioCalidadActividadTuristicas;
	}

	public Integer getPrecioCalidadAlojamientoTuristico() {
		return precioCalidadAlojamientoTuristico;
	}

	public void setPrecioCalidadAlojamientoTuristico(Integer precioCalidadAlojamientoTuristico) {
		this.precioCalidadAlojamientoTuristico = precioCalidadAlojamientoTuristico;
	}

	public Integer getCantidadVariedadAlojamientoTuristico() {
		return cantidadVariedadAlojamientoTuristico;
	}

	public void setCantidadVariedadAlojamientoTuristico(Integer cantidadVariedadAlojamientoTuristico) {
		this.cantidadVariedadAlojamientoTuristico = cantidadVariedadAlojamientoTuristico;
	}

	public Integer getPrecioCalidadServiciosAlimentacion() {
		return precioCalidadServiciosAlimentacion;
	}

	public void setPrecioCalidadServiciosAlimentacion(Integer precioCalidadServiciosAlimentacion) {
		this.precioCalidadServiciosAlimentacion = precioCalidadServiciosAlimentacion;
	}

	public Integer getCantidadVariedadServiciosAlimentacion() {
		return cantidadVariedadServiciosAlimentacion;
	}

	public void setCantidadVariedadServiciosAlimentacion(Integer cantidadVariedadServiciosAlimentacion) {
		this.cantidadVariedadServiciosAlimentacion = cantidadVariedadServiciosAlimentacion;
	}

	public Integer getLimpiezaGeneralEspacioPublico() {
		return limpiezaGeneralEspacioPublico;
	}

	public void setLimpiezaGeneralEspacioPublico(Integer limpiezaGeneralEspacioPublico) {
		this.limpiezaGeneralEspacioPublico = limpiezaGeneralEspacioPublico;
	}

	public Integer getConectividadWiFiEspaciosPublicos() {
		return conectividadWiFiEspaciosPublicos;
	}

	public void setConectividadWiFiEspaciosPublicos(Integer conectividadWiFiEspaciosPublicos) {
		this.conectividadWiFiEspaciosPublicos = conectividadWiFiEspaciosPublicos;
	}

	public Integer getSeñalizacionAtractivosServiciosTuristicos() {
		return señalizacionAtractivosServiciosTuristicos;
	}

	public void setSeñalizacionAtractivosServiciosTuristicos(Integer señalizacionAtractivosServiciosTuristicos) {
		this.señalizacionAtractivosServiciosTuristicos = señalizacionAtractivosServiciosTuristicos;
	}

	public Integer getDisponibilidadCentrosInfoTuristica() {
		return disponibilidadCentrosInfoTuristica;
	}

	public void setDisponibilidadCentrosInfoTuristica(Integer disponibilidadCentrosInfoTuristica) {
		this.disponibilidadCentrosInfoTuristica = disponibilidadCentrosInfoTuristica;
	}

	public Integer getSeguridadActividadesTurismoAventura() {
		return seguridadActividadesTurismoAventura;
	}

	public void setSeguridadActividadesTurismoAventura(Integer seguridadActividadesTurismoAventura) {
		this.seguridadActividadesTurismoAventura = seguridadActividadesTurismoAventura;
	}

	public Integer getContaminacionAmbiental() {
		return contaminacionAmbiental;
	}

	public void setContaminacionAmbiental(Integer contaminacionAmbiental) {
		this.contaminacionAmbiental = contaminacionAmbiental;
	}

	public Integer getDisponibilidadBancosCajeros() {
		return disponibilidadBancosCajeros;
	}

	public void setDisponibilidadBancosCajeros(Integer disponibilidadBancosCajeros) {
		this.disponibilidadBancosCajeros = disponibilidadBancosCajeros;
	}

	public Integer getDisponibilidadTransporteLocal() {
		return disponibilidadTransporteLocal;
	}

	public void setDisponibilidadTransporteLocal(Integer disponibilidadTransporteLocal) {
		this.disponibilidadTransporteLocal = disponibilidadTransporteLocal;
	}

	public Integer getSatisfaccionGeneral() {
		return satisfaccionGeneral;
	}

	public void setSatisfaccionGeneral(Integer satisfaccionGeneral) {
		this.satisfaccionGeneral = satisfaccionGeneral;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (precioCalidadAlojamientoTuristico != 0) {
			resultado.add("1");
			resultado.add(String.valueOf(precioCalidadAlojamientoTuristico));
		}
		if (cantidadVariedadAlojamientoTuristico != 0) {
			resultado.add("2");
			resultado.add(String.valueOf(cantidadVariedadAlojamientoTuristico));
		}
		if (precioCalidadServiciosAlimentacion != 0) {
			resultado.add("3");
			resultado.add(String.valueOf(precioCalidadServiciosAlimentacion));
		}
		if (cantidadVariedadServiciosAlimentacion != 0) {
			resultado.add("4");
			resultado.add(String.valueOf(cantidadVariedadServiciosAlimentacion));
		}
		if (limpiezaGeneralEspacioPublico != 0) {
			resultado.add("5");
			resultado.add(String.valueOf(limpiezaGeneralEspacioPublico));
		}
		if (conectividadWiFiEspaciosPublicos != 0) {
			resultado.add("6");
			resultado.add(String.valueOf(limpiezaGeneralEspacioPublico));
		}
		if (señalizacionAtractivosServiciosTuristicos != 0) {
			resultado.add("7");
			resultado.add(String.valueOf(señalizacionAtractivosServiciosTuristicos));
		}
		if(precioCalidadActividadTuristicas != 0) {
			resultado.add("8");
			resultado.add(String.valueOf(precioCalidadActividadTuristicas));
		}
		if (disponibilidadCentrosInfoTuristica != 0) {
			resultado.add("9");
			resultado.add(String.valueOf(disponibilidadCentrosInfoTuristica));
		}
		if (seguridadActividadesTurismoAventura != 0) {
			resultado.add("10");
			resultado.add(String.valueOf(seguridadActividadesTurismoAventura));
		}
		if (contaminacionAmbiental != 0) {
			resultado.add("11");
			resultado.add(String.valueOf(contaminacionAmbiental));
		}
		if (disponibilidadBancosCajeros != 0) {
			resultado.add("12");
			resultado.add(String.valueOf(disponibilidadBancosCajeros));
		}
		if (disponibilidadTransporteLocal != 0) {
			resultado.add("13");
			resultado.add(String.valueOf(disponibilidadTransporteLocal));
		}
		if (satisfaccionGeneral != 0) {
			resultado.add("14");
			resultado.add(String.valueOf(satisfaccionGeneral));
		}
		//
		return resultado;

	}

}
