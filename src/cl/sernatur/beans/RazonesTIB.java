package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class RazonesTIB {

	private Integer razonCercania;
	private Integer razonCasaDeptoEnLugar;
	private Integer razonVivenFamiliares;
	private Integer razonRecomendacionFamiliaAmigos;
	private Integer razonCalidadAlojamiento;
	private Integer razonRelacionPrecioCalidad;
	private Integer razonPaisajesNaturales;
	private Integer razonConocerOtrasPersonas;
	private Integer razonRealizarDiversasActividades;
	private Integer razonVariedadCosasHacerVer;
	private Integer razonActividadesCulturales;
	private Integer razonVidaNocturna;
	private Integer razonLugarSeguroTranquilo;
	private Integer razonGastronomia;
	private Integer razonProfesionales;
	private Integer razonOtra;

	public RazonesTIB() {

	}

	public Integer getRazonCercania() {
		return razonCercania;
	}

	public void setRazonCercania(Integer razonCercania) {
		this.razonCercania = razonCercania;
	}

	public Integer getRazonCasaDeptoEnLugar() {
		return razonCasaDeptoEnLugar;
	}

	public void setRazonCasaDeptoEnLugar(Integer razonCasaDeptoEnLugar) {
		this.razonCasaDeptoEnLugar = razonCasaDeptoEnLugar;
	}

	public Integer getRazonVivenFamiliares() {
		return razonVivenFamiliares;
	}

	public void setRazonVivenFamiliares(Integer razonVivenFamiliares) {
		this.razonVivenFamiliares = razonVivenFamiliares;
	}

	public Integer getRazonRecomendacionFamiliaAmigos() {
		return razonRecomendacionFamiliaAmigos;
	}

	public void setRazonRecomendacionFamiliaAmigos(Integer razonRecomendacionFamiliaAmigos) {
		this.razonRecomendacionFamiliaAmigos = razonRecomendacionFamiliaAmigos;
	}

	public Integer getRazonCalidadAlojamiento() {
		return razonCalidadAlojamiento;
	}

	public void setRazonCalidadAlojamiento(Integer razonCalidadAlojamiento) {
		this.razonCalidadAlojamiento = razonCalidadAlojamiento;
	}

	public Integer getRazonRelacionPrecioCalidad() {
		return razonRelacionPrecioCalidad;
	}

	public void setRazonRelacionPrecioCalidad(Integer razonRelacionPrecioCalidad) {
		this.razonRelacionPrecioCalidad = razonRelacionPrecioCalidad;
	}

	public Integer getRazonPaisajesNaturales() {
		return razonPaisajesNaturales;
	}

	public void setRazonPaisajesNaturales(Integer razonPaisajesNaturales) {
		this.razonPaisajesNaturales = razonPaisajesNaturales;
	}

	public Integer getRazonConocerOtrasPersonas() {
		return razonConocerOtrasPersonas;
	}

	public void setRazonConocerOtrasPersonas(Integer razonConocerOtrasPersonas) {
		this.razonConocerOtrasPersonas = razonConocerOtrasPersonas;
	}

	public Integer getRazonRealizarDiversasActividades() {
		return razonRealizarDiversasActividades;
	}

	public void setRazonRealizarDiversasActividades(Integer razonRealizarDiversasActividades) {
		this.razonRealizarDiversasActividades = razonRealizarDiversasActividades;
	}

	public Integer getRazonVariedadCosasHacerVer() {
		return razonVariedadCosasHacerVer;
	}

	public void setRazonVariedadCosasHacerVer(Integer razonVariedadCosasHacerVer) {
		this.razonVariedadCosasHacerVer = razonVariedadCosasHacerVer;
	}

	public Integer getRazonActividadesCulturales() {
		return razonActividadesCulturales;
	}

	public void setRazonActividadesCulturales(Integer razonActividadesCulturales) {
		this.razonActividadesCulturales = razonActividadesCulturales;
	}

	public Integer getRazonVidaNocturna() {
		return razonVidaNocturna;
	}

	public void setRazonVidaNocturna(Integer razonVidaNocturna) {
		this.razonVidaNocturna = razonVidaNocturna;
	}

	public Integer getRazonLugarSeguroTranquilo() {
		return razonLugarSeguroTranquilo;
	}

	public void setRazonLugarSeguroTranquilo(Integer razonLugarSeguroTranquilo) {
		this.razonLugarSeguroTranquilo = razonLugarSeguroTranquilo;
	}

	public Integer getRazonGastronomia() {
		return razonGastronomia;
	}

	public void setRazonGastronomia(Integer razonGastronomia) {
		this.razonGastronomia = razonGastronomia;
	}

	public Integer getRazonProfesionales() {
		return razonProfesionales;
	}

	public void setRazonProfesionales(Integer razonProfesionales) {
		this.razonProfesionales = razonProfesionales;
	}

	public Integer getRazonOtra() {
		return razonOtra;
	}

	public void setRazonOtra(Integer razonOtra) {
		this.razonOtra = razonOtra;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (razonCercania != 0) {
			resultado.add(String.valueOf(1));
		}
		if (razonCasaDeptoEnLugar != 0) {
			resultado.add(String.valueOf(2));
		}
		if (razonVivenFamiliares != 0) {
			resultado.add(String.valueOf(3));
		}
		if (razonRecomendacionFamiliaAmigos != 0) {
			resultado.add(String.valueOf(4));
		}
		if (razonCalidadAlojamiento != 0) {
			resultado.add(String.valueOf(5));
		}
		if (razonRelacionPrecioCalidad != 0) {
			resultado.add(String.valueOf(6));
		}
		if (razonPaisajesNaturales != 0) {
			resultado.add(String.valueOf(7));
		}
		if (razonConocerOtrasPersonas != 0) {
			resultado.add(String.valueOf(8));
		}
		if (razonRealizarDiversasActividades != 0) {
			resultado.add(String.valueOf(9));
		}
		if (razonVariedadCosasHacerVer != 0) {
			resultado.add(String.valueOf(10));
		}
		if (razonActividadesCulturales != 0) {
			resultado.add(String.valueOf(11));
		}
		if (razonVidaNocturna != 0) {
			resultado.add(String.valueOf(12));
		}
		if (razonLugarSeguroTranquilo != 0) {
			resultado.add(String.valueOf(13));
		}
		if (razonGastronomia != 0) {
			resultado.add(String.valueOf(14));
		}
		if (razonProfesionales != 0) {
			resultado.add(String.valueOf(15));
		}
		if (razonOtra != 0) {
			resultado.add(String.valueOf(16));
		}
		//
		return resultado;

	}

}
