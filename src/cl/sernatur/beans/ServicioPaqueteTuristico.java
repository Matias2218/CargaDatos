package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class ServicioPaqueteTuristico {
	
	private Integer pasajesInternacionales;
	private Integer alojamiento;
	private Integer alimentacionFueraHotel;
	private Integer trasladosAeropuertoDestino;
	private Integer vuelosDomesticosExtranjero;
	private Integer otrosDestinosInternacionales;
	private Integer tourExcursiones;
	private Integer seguroViajeSalud;
	private Integer otro;
	
	public ServicioPaqueteTuristico() {
		
	}

	public Integer getPasajesInternacionales() {
		return pasajesInternacionales;
	}

	public void setPasajesInternacionales(Integer pasajesInternacionales) {
		this.pasajesInternacionales = pasajesInternacionales;
	}

	public Integer getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Integer alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Integer getAlimentacionFueraHotel() {
		return alimentacionFueraHotel;
	}

	public void setAlimentacionFueraHotel(Integer alimentacionFueraHotel) {
		this.alimentacionFueraHotel = alimentacionFueraHotel;
	}

	public Integer getTrasladosAeropuertoDestino() {
		return trasladosAeropuertoDestino;
	}

	public void setTrasladosAeropuertoDestino(Integer trasladosAeropuertoDestino) {
		this.trasladosAeropuertoDestino = trasladosAeropuertoDestino;
	}

	public Integer getVuelosDomesticosExtranjero() {
		return vuelosDomesticosExtranjero;
	}

	public void setVuelosDomesticosExtranjero(Integer vuelosDomesticosExtranjero) {
		this.vuelosDomesticosExtranjero = vuelosDomesticosExtranjero;
	}

	public Integer getOtrosDestinosInternacionales() {
		return otrosDestinosInternacionales;
	}

	public void setOtrosDestinosInternacionales(Integer otrosDestinosInternacionales) {
		this.otrosDestinosInternacionales = otrosDestinosInternacionales;
	}

	public Integer getTourExcursiones() {
		return tourExcursiones;
	}

	public void setTourExcursiones(Integer tourExcursiones) {
		this.tourExcursiones = tourExcursiones;
	}

	public Integer getSeguroViajeSalud() {
		return seguroViajeSalud;
	}

	public void setSeguroViajeSalud(Integer seguroViajeSalud) {
		this.seguroViajeSalud = seguroViajeSalud;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(pasajesInternacionales != 0) {
			resultado.add(pasajesInternacionales.toString());
		}
		if(alojamiento != 0) {
			resultado.add(alojamiento.toString());
		}
		if(alimentacionFueraHotel != 0) {
			resultado.add(alimentacionFueraHotel.toString());
		}
		if(trasladosAeropuertoDestino != 0) {
			resultado.add(trasladosAeropuertoDestino.toString());
		}
		if(vuelosDomesticosExtranjero != 0) {
			resultado.add(vuelosDomesticosExtranjero.toString());
		}
		if(otrosDestinosInternacionales != 0) {
			resultado.add(otrosDestinosInternacionales.toString());
		}
		if(tourExcursiones != 0) {
			resultado.add(tourExcursiones.toString());
		}
		if(seguroViajeSalud != 0) {
			resultado.add(seguroViajeSalud.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}

}
