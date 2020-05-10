package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Alojamiento {
	
	private Integer hotel;
	private Integer cabaña;
	private Integer hostal;
	private Integer campingRefugioLodgeResort;
	private Integer casaDepartamento;
	private Integer airbnbCouchsurfingHouse;
	private Integer pernoctacionCrucero;
	private Integer casaFamiliares;
	private Integer residenciaParticular;
	private Integer recintoParticular;
	private Integer vehiculoMotorizado;
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

	public Integer getCampingRefugioLodgeResort() {
		return campingRefugioLodgeResort;
	}

	public void setCampingRefugioLodgeResort(Integer campingRefugioLodgeResort) {
		this.campingRefugioLodgeResort = campingRefugioLodgeResort;
	}

	public Integer getCasaDepartamento() {
		return casaDepartamento;
	}

	public void setCasaDepartamento(Integer casaDepartamento) {
		this.casaDepartamento = casaDepartamento;
	}

	public Integer getAirbnbCouchsurfingHouse() {
		return airbnbCouchsurfingHouse;
	}

	public void setAirbnbCouchsurfingHouse(Integer airbnbCouchsurfingHouse) {
		this.airbnbCouchsurfingHouse = airbnbCouchsurfingHouse;
	}

	public Integer getPernoctacionCrucero() {
		return pernoctacionCrucero;
	}

	public void setPernoctacionCrucero(Integer pernoctacionCrucero) {
		this.pernoctacionCrucero = pernoctacionCrucero;
	}

	public Integer getCasaFamiliares() {
		return casaFamiliares;
	}

	public void setCasaFamiliares(Integer casaFamiliares) {
		this.casaFamiliares = casaFamiliares;
	}

	public Integer getResidenciaParticular() {
		return residenciaParticular;
	}

	public void setResidenciaParticular(Integer residenciaParticular) {
		this.residenciaParticular = residenciaParticular;
	}

	public Integer getRecintoParticular() {
		return recintoParticular;
	}

	public void setRecintoParticular(Integer recintoParticular) {
		this.recintoParticular = recintoParticular;
	}

	public Integer getVehiculoMotorizado() {
		return vehiculoMotorizado;
	}

	public void setVehiculoMotorizado(Integer vehiculoMotorizado) {
		this.vehiculoMotorizado = vehiculoMotorizado;
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
		if(campingRefugioLodgeResort != 0) {
			resultado.add(campingRefugioLodgeResort.toString());
		}
		if(casaDepartamento != 0) {
			resultado.add(casaDepartamento.toString());
		}
		if(airbnbCouchsurfingHouse != 0) {
			resultado.add(airbnbCouchsurfingHouse.toString());
		}
		if(pernoctacionCrucero != 0) {
			resultado.add(pernoctacionCrucero.toString());
		}if(casaFamiliares != 0) {
			resultado.add(casaFamiliares.toString());
		}
		if(residenciaParticular != 0) {
			resultado.add(residenciaParticular.toString());
		}
		if(recintoParticular != 0) {
			resultado.add(recintoParticular.toString());
		}
		if(vehiculoMotorizado != 0) {
			resultado.add(vehiculoMotorizado.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}

}
