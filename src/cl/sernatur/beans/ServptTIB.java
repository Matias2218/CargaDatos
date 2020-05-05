package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class ServptTIB {

	private Integer transporteADestino;
	private Integer alojamiento;
	private Integer comidas;
	private Integer transporteEnDestinoArriendoAuto;
	private Integer excursionesEspectaculo;
	private Integer seguroSaludAsistenciaViajero;

	public ServptTIB() {

	}

	public Integer getTransporteADestino() {
		return transporteADestino;
	}

	public void setTransporteADestino(Integer transporteADestino) {
		this.transporteADestino = transporteADestino;
	}

	public Integer getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Integer alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Integer getComidas() {
		return comidas;
	}

	public void setComidas(Integer comidas) {
		this.comidas = comidas;
	}

	public Integer getTransporteEnDestinoArriendoAuto() {
		return transporteEnDestinoArriendoAuto;
	}

	public void setTransporteEnDestinoArriendoAuto(Integer transporteEnDestinoArriendoAuto) {
		this.transporteEnDestinoArriendoAuto = transporteEnDestinoArriendoAuto;
	}

	public Integer getExcursionesEspectaculo() {
		return excursionesEspectaculo;
	}

	public void setExcursionesEspectaculo(Integer excursionesEspectaculo) {
		this.excursionesEspectaculo = excursionesEspectaculo;
	}

	public Integer getSeguroSaludAsistenciaViajero() {
		return seguroSaludAsistenciaViajero;
	}

	public void setSeguroSaludAsistenciaViajero(Integer seguroSaludAsistenciaViajero) {
		this.seguroSaludAsistenciaViajero = seguroSaludAsistenciaViajero;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (transporteADestino != 0) {
			resultado.add(String.valueOf(1));
		}
		if (alojamiento != 0) {
			resultado.add(String.valueOf(2));
		}
		if (comidas != 0) {
			resultado.add(String.valueOf(3));
		}
		if (transporteEnDestinoArriendoAuto != 0) {
			resultado.add(String.valueOf(4));
		}
		if (excursionesEspectaculo != 0) {
			resultado.add(String.valueOf(5));
		}
		if (seguroSaludAsistenciaViajero != 0) {
			resultado.add(String.valueOf(6));
		}
		//
		return resultado;

	}
}
