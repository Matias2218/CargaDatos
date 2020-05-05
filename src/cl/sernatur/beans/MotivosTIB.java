package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class MotivosTIB {

	private Integer cercania;
	private Integer casaDeptoEnLugar;
	private Integer vivenFamiliaresAmigos;
	private Integer recomendacionFamiliaresAmigos;
	private Integer calidadAlojamiento;
	private Integer buenaRelacionPrecioCalidad;
	private Integer paisajesNaturales;
	private Integer conocerOtrasPersonas;
	private Integer realizarDiversasActividades;
	private Integer variedadDeCosasQueVerHacer;
	private Integer actividadesCulturales;
	private Integer vidaNocturna;
	private Integer lugarSeguroTranquilo;
	private Integer gastronomia;
	private Integer razonesProfesionales;
	private Integer otra;

	public MotivosTIB() {

	}

	public Integer getCercania() {
		return cercania;
	}

	public void setCercania(Integer cercania) {
		this.cercania = cercania;
	}

	public Integer getCasaDeptoEnLugar() {
		return casaDeptoEnLugar;
	}

	public void setCasaDeptoEnLugar(Integer casaDeptoEnLugar) {
		this.casaDeptoEnLugar = casaDeptoEnLugar;
	}

	public Integer getVivenFamiliaresAmigos() {
		return vivenFamiliaresAmigos;
	}

	public void setVivenFamiliaresAmigos(Integer vivenFamiliaresAmigos) {
		this.vivenFamiliaresAmigos = vivenFamiliaresAmigos;
	}

	public Integer getRecomendacionFamiliaresAmigos() {
		return recomendacionFamiliaresAmigos;
	}

	public void setRecomendacionFamiliaresAmigos(Integer recomendacionFamiliaresAmigos) {
		this.recomendacionFamiliaresAmigos = recomendacionFamiliaresAmigos;
	}

	public Integer getCalidadAlojamiento() {
		return calidadAlojamiento;
	}

	public void setCalidadAlojamiento(Integer calidadAlojamiento) {
		this.calidadAlojamiento = calidadAlojamiento;
	}

	public Integer getBuenaRelacionPrecioCalidad() {
		return buenaRelacionPrecioCalidad;
	}

	public void setBuenaRelacionPrecioCalidad(Integer buenaRelacionPrecioCalidad) {
		this.buenaRelacionPrecioCalidad = buenaRelacionPrecioCalidad;
	}

	public Integer getPaisajesNaturales() {
		return paisajesNaturales;
	}

	public void setPaisajesNaturales(Integer paisajesNaturales) {
		this.paisajesNaturales = paisajesNaturales;
	}

	public Integer getConocerOtrasPersonas() {
		return conocerOtrasPersonas;
	}

	public void setConocerOtrasPersonas(Integer conocerOtrasPersonas) {
		this.conocerOtrasPersonas = conocerOtrasPersonas;
	}

	public Integer getRealizarDiversasActividades() {
		return realizarDiversasActividades;
	}

	public void setRealizarDiversasActividades(Integer realizarDiversasActividades) {
		this.realizarDiversasActividades = realizarDiversasActividades;
	}

	public Integer getVariedadDeCosasQueVerHacer() {
		return variedadDeCosasQueVerHacer;
	}

	public void setVariedadDeCosasQueVerHacer(Integer variedadDeCosasQueVerHacer) {
		this.variedadDeCosasQueVerHacer = variedadDeCosasQueVerHacer;
	}

	public Integer getActividadesCulturales() {
		return actividadesCulturales;
	}

	public void setActividadesCulturales(Integer actividadesCulturales) {
		this.actividadesCulturales = actividadesCulturales;
	}

	public Integer getVidaNocturna() {
		return vidaNocturna;
	}

	public void setVidaNocturna(Integer vidaNocturna) {
		this.vidaNocturna = vidaNocturna;
	}

	public Integer getLugarSeguroTranquilo() {
		return lugarSeguroTranquilo;
	}

	public void setLugarSeguroTranquilo(Integer lugarSeguroTranquilo) {
		this.lugarSeguroTranquilo = lugarSeguroTranquilo;
	}

	public Integer getGastronomia() {
		return gastronomia;
	}

	public void setGastronomia(Integer gastronomia) {
		this.gastronomia = gastronomia;
	}

	public Integer getRazonesProfesionales() {
		return razonesProfesionales;
	}

	public void setRazonesProfesionales(Integer razonesProfesionales) {
		this.razonesProfesionales = razonesProfesionales;
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
		if (cercania != 0) {
			resultado.add("1");
		}
		if (casaDeptoEnLugar != 0) {
			resultado.add("2");
		}
		if (vivenFamiliaresAmigos != 0) {
			resultado.add("3");
		}
		if (recomendacionFamiliaresAmigos != 0) {
			resultado.add("4");
		}
		if (calidadAlojamiento != 0) {
			resultado.add("5");
		}
		if (buenaRelacionPrecioCalidad != 0) {
			resultado.add("6");
		}
		if (paisajesNaturales != 0) {
			resultado.add("7");
		}
		if (conocerOtrasPersonas != 0) {
			resultado.add("8");
		}
		if (realizarDiversasActividades != 0) {
			resultado.add("9");
		}
		if (variedadDeCosasQueVerHacer != 0) {
			resultado.add("10");
		}
		if (actividadesCulturales != 0) {
			resultado.add("11");
		}
		if (vidaNocturna != 0) {
			resultado.add("12");
		}
		if (lugarSeguroTranquilo != 0) {
			resultado.add("13");
		}
		if (gastronomia != 0) {
			resultado.add("14");
		}
		if (razonesProfesionales != 0) {
			resultado.add("15");
		}
		if (otra != 0) {
			resultado.add("16");
		}
		//
		return resultado;

	}
}
