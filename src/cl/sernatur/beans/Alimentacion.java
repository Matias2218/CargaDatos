package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Alimentacion {
	
	private Integer restaurant;
	private Integer comidaRapida;
	private Integer hotelAlojamiento;
	private Integer compraMercaderia;
	private Integer casaFamiliaresAmigos;
	private Integer ninguno;
	private Integer otro;
	
	public Alimentacion() {
		
	}

	public Integer getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Integer restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getComidaRapida() {
		return comidaRapida;
	}

	public void setComidaRapida(Integer comidaRapida) {
		this.comidaRapida = comidaRapida;
	}

	public Integer getHotelAlojamiento() {
		return hotelAlojamiento;
	}

	public void setHotelAlojamiento(Integer hotelAlojamiento) {
		this.hotelAlojamiento = hotelAlojamiento;
	}

	public Integer getCompraMercaderia() {
		return compraMercaderia;
	}

	public void setCompraMercaderia(Integer compraMercaderia) {
		this.compraMercaderia = compraMercaderia;
	}

	public Integer getCasaFamiliaresAmigos() {
		return casaFamiliaresAmigos;
	}

	public void setCasaFamiliaresAmigos(Integer casaFamiliaresAmigos) {
		this.casaFamiliaresAmigos = casaFamiliaresAmigos;
	}

	public Integer getNinguno() {
		return ninguno;
	}

	public void setNinguno(Integer ninguno) {
		this.ninguno = ninguno;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(restaurant != 0) {
			resultado.add(restaurant.toString());
		}
		if(comidaRapida != 0) {
			resultado.add(comidaRapida.toString());
		}
		if(hotelAlojamiento != 0) {
			resultado.add(hotelAlojamiento.toString());
		}
		if(compraMercaderia != 0) {
			resultado.add(compraMercaderia.toString());
		}
		if(casaFamiliaresAmigos != 0) {
			resultado.add(casaFamiliaresAmigos.toString());
		}
		if(ninguno != 0) {
			resultado.add(ninguno.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}
	
	
}
