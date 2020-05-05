package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class GastosTIB {
	
	private Float paqueteTuristico;
	private Float alojamiento;
	private Float alimentacion;
	private Float avion;
	private Float busesInterurbanos;
	private Float transporteMaritimoTrenOtro;
	private Float arriendoAuto;
	private Float microTaxiColectivoMetro;
	private Float peajesCombustibleEstacionamiento;
	private Float actividadesRecreativasCulturalesDeportivas;
	private Float compras;
	private Float otros;
	
	public GastosTIB() {
		
	}

	public Float getPaqueteTuristico() {
		return paqueteTuristico;
	}

	public void setPaqueteTuristico(Float paqueteTuristico) {
		this.paqueteTuristico = paqueteTuristico;
	}

	public Float getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Float alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Float getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(Float alimentacion) {
		this.alimentacion = alimentacion;
	}

	public Float getAvion() {
		return avion;
	}

	public void setAvion(Float avion) {
		this.avion = avion;
	}

	public Float getBusesInterurbanos() {
		return busesInterurbanos;
	}

	public void setBusesInterurbanos(Float busesInterurbanos) {
		this.busesInterurbanos = busesInterurbanos;
	}

	public Float getTransporteMaritimoTrenOtro() {
		return transporteMaritimoTrenOtro;
	}

	public void setTransporteMaritimoTrenOtro(Float transporteMaritimoTrenOtro) {
		this.transporteMaritimoTrenOtro = transporteMaritimoTrenOtro;
	}

	public Float getArriendoAuto() {
		return arriendoAuto;
	}

	public void setArriendoAuto(Float arriendoAuto) {
		this.arriendoAuto = arriendoAuto;
	}

	public Float getMicroTaxiColectivoMetro() {
		return microTaxiColectivoMetro;
	}

	public void setMicroTaxiColectivoMetro(Float microTaxiColectivoMetro) {
		this.microTaxiColectivoMetro = microTaxiColectivoMetro;
	}

	public Float getPeajesCombustibleEstacionamiento() {
		return peajesCombustibleEstacionamiento;
	}

	public void setPeajesCombustibleEstacionamiento(Float peajesCombustibleEstacionamiento) {
		this.peajesCombustibleEstacionamiento = peajesCombustibleEstacionamiento;
	}

	public Float getActividadesRecreativasCulturalesDeportivas() {
		return actividadesRecreativasCulturalesDeportivas;
	}

	public void setActividadesRecreativasCulturalesDeportivas(Float actividadesRecreativasCulturalesDeportivas) {
		this.actividadesRecreativasCulturalesDeportivas = actividadesRecreativasCulturalesDeportivas;
	}

	public Float getCompras() {
		return compras;
	}

	public void setCompras(Float compras) {
		this.compras = compras;
	}

	public Float getOtros() {
		return otros;
	}

	public void setOtros(Float otros) {
		this.otros = otros;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if(paqueteTuristico != 0) {
			resultado.add("1");
			resultado.add(paqueteTuristico.toString());
		} if(alojamiento != 0) {
			resultado.add("2");
			resultado.add(alojamiento.toString());
		} if(alimentacion != 0) {
			resultado.add("3");
			resultado.add(alimentacion.toString());
		} if(avion != 0) {
			resultado.add("4");
			resultado.add(avion.toString());
		} if(busesInterurbanos != 0) {
			resultado.add("5");
			resultado.add(busesInterurbanos.toString());
		} if(transporteMaritimoTrenOtro != 0) {
			resultado.add("6");
			resultado.add(transporteMaritimoTrenOtro.toString());
		} if(arriendoAuto != 0) {
			resultado.add("7");
			resultado.add(arriendoAuto.toString());
		} if(microTaxiColectivoMetro != 0) {
			resultado.add("8");
			resultado.add(microTaxiColectivoMetro.toString());
		} if(peajesCombustibleEstacionamiento != 0) {
			resultado.add("9");
			resultado.add(peajesCombustibleEstacionamiento.toString());
		} if(actividadesRecreativasCulturalesDeportivas != 0) {
			resultado.add("10");
			resultado.add(actividadesRecreativasCulturalesDeportivas.toString());
		} if(compras != 0) {
			resultado.add("11");
			resultado.add(compras.toString());
		} if(otros != 0) {
			resultado.add("12");
			resultado.add(otros.toString());
		}
		//
		return resultado;
		
	}
	

}
