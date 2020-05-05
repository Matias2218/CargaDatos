package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class InformacionTIB {

	private Integer recomendacionFamiliaresAmigos;
	private Integer agenciaViajes;
	private Integer cartelesViaPublica;
	private Integer television;
	private Integer radio;
	private Integer diarioRevistas;
	private Integer experienciaViajesAnteriores;
	private Integer internet;
	private Integer comentariosRedesSociales;
	private Integer guiasTuristicasFolletos;
	private Integer otra;

	public InformacionTIB() {

	}

	public Integer getRecomendacionFamiliaresAmigos() {
		return recomendacionFamiliaresAmigos;
	}

	public void setRecomendacionFamiliaresAmigos(Integer recomendacionFamiliaresAmigos) {
		this.recomendacionFamiliaresAmigos = recomendacionFamiliaresAmigos;
	}

	public Integer getAgenciaViajes() {
		return agenciaViajes;
	}

	public void setAgenciaViajes(Integer agenciaViajes) {
		this.agenciaViajes = agenciaViajes;
	}

	public Integer getCartelesViaPublica() {
		return cartelesViaPublica;
	}

	public void setCartelesViaPublica(Integer cartelesViaPublica) {
		this.cartelesViaPublica = cartelesViaPublica;
	}

	public Integer getTelevision() {
		return television;
	}

	public void setTelevision(Integer television) {
		this.television = television;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio(Integer radio) {
		this.radio = radio;
	}

	public Integer getDiarioRevistas() {
		return diarioRevistas;
	}

	public void setDiarioRevistas(Integer diarioRevistas) {
		this.diarioRevistas = diarioRevistas;
	}

	public Integer getExperienciaViajesAnteriores() {
		return experienciaViajesAnteriores;
	}

	public void setExperienciaViajesAnteriores(Integer experienciaViajesAnteriores) {
		this.experienciaViajesAnteriores = experienciaViajesAnteriores;
	}

	public Integer getInternet() {
		return internet;
	}

	public void setInternet(Integer internet) {
		this.internet = internet;
	}

	public Integer getComentariosRedesSociales() {
		return comentariosRedesSociales;
	}

	public void setComentariosRedesSociales(Integer comentariosRedesSociales) {
		this.comentariosRedesSociales = comentariosRedesSociales;
	}

	public Integer getGuiasTuristicasFolletos() {
		return guiasTuristicasFolletos;
	}

	public void setGuiasTuristicasFolletos(Integer guiasTuristicasFolletos) {
		this.guiasTuristicasFolletos = guiasTuristicasFolletos;
	}

	public Integer getOtra() {
		return otra;
	}

	public void setOtra(Integer otra) {
		this.otra = otra;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (recomendacionFamiliaresAmigos != 0) {
			resultado.add(String.valueOf(1));
		}
		if (agenciaViajes != 0) {
			resultado.add(String.valueOf(2));
		}
		if (cartelesViaPublica != 0) {
			resultado.add(String.valueOf(3));
		}
		if (television != 0) {
			resultado.add(String.valueOf(4));
		}
		if (radio != 0) {
			resultado.add(String.valueOf(5));
		}
		if (diarioRevistas != 0) {
			resultado.add(String.valueOf(6));
		}
		if (experienciaViajesAnteriores != 0) {
			resultado.add(String.valueOf(7));
		}
		if (internet != 0) {
			resultado.add(String.valueOf(8));
		}
		if (comentariosRedesSociales != 0) {
			resultado.add(String.valueOf(9));
		}
		if (guiasTuristicasFolletos != 0) {
			resultado.add(String.valueOf(10));
		}
		if (otra != 0) {
			resultado.add(String.valueOf(11));
		}
		//
		return resultado;

	}

}
