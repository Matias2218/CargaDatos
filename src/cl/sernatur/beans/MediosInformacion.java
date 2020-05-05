package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class MediosInformacion {
	
	private Integer consejosRecomendaciones;
	private Integer informacionInternet;
	private Integer redesSociales;
	private Integer agenciaViajeTourOperador;
	private Integer guiasTuristicasImpresas;
	private Integer guiasTuristicasOnline;
	private Integer folletosOficiasTurismoEmbajada;
	private Integer organizaEmpresa;
	private Integer organizaOtro;
	private Integer conocimientoPrevio;
	private Integer otro;
	
	public MediosInformacion() {
		
	}

	public Integer getConsejosRecomendaciones() {
		return consejosRecomendaciones;
	}

	public void setConsejosRecomendaciones(Integer consejosRecomendaciones) {
		this.consejosRecomendaciones = consejosRecomendaciones;
	}

	public Integer getInformacionInternet() {
		return informacionInternet;
	}

	public void setInformacionInternet(Integer informacionInternet) {
		this.informacionInternet = informacionInternet;
	}

	public Integer getRedesSociales() {
		return redesSociales;
	}

	public void setRedesSociales(Integer redesSociales) {
		this.redesSociales = redesSociales;
	}

	public Integer getAgenciaViajeTourOperador() {
		return agenciaViajeTourOperador;
	}

	public void setAgenciaViajeTourOperador(Integer agenciaViajeTourOperador) {
		this.agenciaViajeTourOperador = agenciaViajeTourOperador;
	}

	public Integer getGuiasTuristicasImpresas() {
		return guiasTuristicasImpresas;
	}

	public void setGuiasTuristicasImpresas(Integer guiasTuristicasImpresas) {
		this.guiasTuristicasImpresas = guiasTuristicasImpresas;
	}

	public Integer getGuiasTuristicasOnline() {
		return guiasTuristicasOnline;
	}

	public void setGuiasTuristicasOnline(Integer guiasTuristicasOnline) {
		this.guiasTuristicasOnline = guiasTuristicasOnline;
	}

	public Integer getFolletosOficiasTurismoEmbajada() {
		return folletosOficiasTurismoEmbajada;
	}

	public void setFolletosOficiasTurismoEmbajada(Integer folletosOficiasTurismoEmbajada) {
		this.folletosOficiasTurismoEmbajada = folletosOficiasTurismoEmbajada;
	}

	public Integer getOrganizaEmpresa() {
		return organizaEmpresa;
	}

	public void setOrganizaEmpresa(Integer organizaEmpresa) {
		this.organizaEmpresa = organizaEmpresa;
	}

	public Integer getOrganizaOtro() {
		return organizaOtro;
	}

	public void setOrganizaOtro(Integer organizaOtro) {
		this.organizaOtro = organizaOtro;
	}

	public Integer getConocimientoPrevio() {
		return conocimientoPrevio;
	}

	public void setConocimientoPrevio(Integer conocimientoPrevio) {
		this.conocimientoPrevio = conocimientoPrevio;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(consejosRecomendaciones != 0) {
			resultado.add(consejosRecomendaciones.toString());
		}
		if(informacionInternet != 0) {
			resultado.add(informacionInternet.toString());
		}
		if(redesSociales != 0) {
			resultado.add(redesSociales.toString());
		}
		if(agenciaViajeTourOperador != 0) {
			resultado.add(agenciaViajeTourOperador.toString());
		}
		if(guiasTuristicasImpresas != 0) {
			resultado.add(guiasTuristicasImpresas.toString());
		}
		if(guiasTuristicasOnline != 0) {
			resultado.add(guiasTuristicasOnline.toString());
		}
		if(folletosOficiasTurismoEmbajada != 0) {
			resultado.add(folletosOficiasTurismoEmbajada.toString());
		}if(organizaEmpresa != 0) {
			resultado.add(organizaEmpresa.toString());
		}
		if(organizaOtro != 0) {
			resultado.add(organizaOtro.toString());
		}
		if(conocimientoPrevio != 0) {
			resultado.add(conocimientoPrevio.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}

}
