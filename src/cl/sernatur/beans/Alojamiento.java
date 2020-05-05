package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Alojamiento {
	
	private Integer hotel;
	private Integer cabaña;
	private Integer hostal;
	private Integer camping;
	private Integer refugio;
	private Integer lodge;
	private Integer resort;
	private Integer departamento;
	private Integer familia;
	private Integer airbnb;
	private Integer crucero;
	private Integer otro;
	
	public Alojamiento() {
		
	}

	public Integer getHotel() {
		return hotel;
	}

	public void setHotel(Integer hotel) {
		this.hotel = hotel;
	}

	public Integer getCabaña() {
		return cabaña;
	}

	public void setCabaña(Integer cabaña) {
		this.cabaña = cabaña;
	}

	public Integer getHostal() {
		return hostal;
	}

	public void setHostal(Integer hostal) {
		this.hostal = hostal;
	}

	public Integer getCamping() {
		return camping;
	}

	public void setCamping(Integer camping) {
		this.camping = camping;
	}

	public Integer getRefugio() {
		return refugio;
	}

	public void setRefugio(Integer refugio) {
		this.refugio = refugio;
	}

	public Integer getLodge() {
		return lodge;
	}

	public void setLodge(Integer lodge) {
		this.lodge = lodge;
	}

	public Integer getResort() {
		return resort;
	}

	public void setResort(Integer resort) {
		this.resort = resort;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public Integer getFamilia() {
		return familia;
	}

	public void setFamilia(Integer familia) {
		this.familia = familia;
	}

	public Integer getAirbnb() {
		return airbnb;
	}

	public void setAirbnb(Integer airbnb) {
		this.airbnb = airbnb;
	}

	public Integer getCrucero() {
		return crucero;
	}

	public void setCrucero(Integer crucero) {
		this.crucero = crucero;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(hotel != 0) {
			resultado.add(hotel.toString());
		}
		if(cabaña != 0) {
			resultado.add(cabaña.toString());
		}
		if(hostal != 0) {
			resultado.add(hostal.toString());
		}
		if(camping != 0) {
			resultado.add(camping.toString());
		}
		if(refugio != 0) {
			resultado.add(refugio.toString());
		}
		if(lodge != 0) {
			resultado.add(lodge.toString());
		}
		if(resort != 0) {
			resultado.add(resort.toString());
		}if(departamento != 0) {
			resultado.add(departamento.toString());
		}
		if(familia != 0) {
			resultado.add(familia.toString());
		}
		if(airbnb != 0) {
			resultado.add(airbnb.toString());
		}
		if(crucero != 0) {
			resultado.add(crucero.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}

}
