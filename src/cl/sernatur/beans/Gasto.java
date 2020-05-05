package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Gasto {
	
	private Float hotelesSimilares;
	private Float casaDeptoArrendado;
	private Float restaurantSimilares;
	private Float compraAlimentos;
	private Float transporteInternoAereo;
	private Float transporteInternoTerrestre;
	private Float transporteInternoMaritimo;
	private Float compras;
	private Float agenciasViajeChile;
	private Float arriendoAutoSinChofer;
	private Float actividadesRecreativasDeportivas;
	private Float actividadesCulturales;
	private Float servicioReservasNaturales;
	private Float bencinaPeajeEstacionamientos;
	private Float otros;
	
	public Gasto() {
		
	}

	public Float getHotelesSimilares() {
		return hotelesSimilares;
	}

	public void setHotelesSimilares(Float hotelesSimilares) {
		this.hotelesSimilares = hotelesSimilares;
	}

	public Float getCasaDeptoArrendado() {
		return casaDeptoArrendado;
	}

	public void setCasaDeptoArrendado(Float casaDeptoArrendado) {
		this.casaDeptoArrendado = casaDeptoArrendado;
	}

	public Float getRestaurantSimilares() {
		return restaurantSimilares;
	}

	public void setRestaurantSimilares(Float restaurantSimilares) {
		this.restaurantSimilares = restaurantSimilares;
	}

	public Float getCompraAlimentos() {
		return compraAlimentos;
	}

	public void setCompraAlimentos(Float compraAlimentos) {
		this.compraAlimentos = compraAlimentos;
	}

	public Float getTransporteInternoAereo() {
		return transporteInternoAereo;
	}

	public void setTransporteInternoAereo(Float transporteInternoAereo) {
		this.transporteInternoAereo = transporteInternoAereo;
	}

	public Float getTransporteInternoTerrestre() {
		return transporteInternoTerrestre;
	}

	public void setTransporteInternoTerrestre(Float transporteInternoTerrestre) {
		this.transporteInternoTerrestre = transporteInternoTerrestre;
	}

	public Float getTransporteInternoMaritimo() {
		return transporteInternoMaritimo;
	}

	public void setTransporteInternoMaritimo(Float transporteInternoMaritimo) {
		this.transporteInternoMaritimo = transporteInternoMaritimo;
	}

	public Float getCompras() {
		return compras;
	}

	public void setCompras(Float compras) {
		this.compras = compras;
	}

	public Float getAgenciasViajeChile() {
		return agenciasViajeChile;
	}

	public void setAgenciasViajeChile(Float agenciasViajeChile) {
		this.agenciasViajeChile = agenciasViajeChile;
	}

	public Float getArriendoAutoSinChofer() {
		return arriendoAutoSinChofer;
	}

	public void setArriendoAutoSinChofer(Float arriendoAutoSinChofer) {
		this.arriendoAutoSinChofer = arriendoAutoSinChofer;
	}

	public Float getActividadesRecreativasDeportivas() {
		return actividadesRecreativasDeportivas;
	}

	public void setActividadesRecreativasDeportivas(Float actividadesRecreativasDeportivas) {
		this.actividadesRecreativasDeportivas = actividadesRecreativasDeportivas;
	}

	public Float getActividadesCulturales() {
		return actividadesCulturales;
	}

	public void setActividadesCulturales(Float actividadesCulturales) {
		this.actividadesCulturales = actividadesCulturales;
	}

	public Float getServicioReservasNaturales() {
		return servicioReservasNaturales;
	}

	public void setServicioReservasNaturales(Float servicioReservasNaturales) {
		this.servicioReservasNaturales = servicioReservasNaturales;
	}

	public Float getBencinaPeajeEstacionamientos() {
		return bencinaPeajeEstacionamientos;
	}

	public void setBencinaPeajeEstacionamientos(Float bencinaPeajeEstacionamientos) {
		this.bencinaPeajeEstacionamientos = bencinaPeajeEstacionamientos;
	}

	public Float getOtros() {
		return otros;
	}

	public void setOtros(Float otros) {
		this.otros = otros;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(hotelesSimilares != 0) {
			resultado.add(hotelesSimilares.toString());
			resultado.add("1");
		}
		if(casaDeptoArrendado != 0) {
			resultado.add(casaDeptoArrendado.toString());
			resultado.add("2");
		}
		if(restaurantSimilares != 0) {
			resultado.add(restaurantSimilares.toString());
			resultado.add("3");
		}
		if(compraAlimentos != 0) {
			resultado.add(compraAlimentos.toString());
			resultado.add("4");
		}
		if(transporteInternoAereo != 0) {
			resultado.add(transporteInternoAereo.toString());
			resultado.add("5");
		}
		if(transporteInternoTerrestre != 0) {
			resultado.add(transporteInternoTerrestre.toString());
			resultado.add("6");
		}
		if(transporteInternoMaritimo != 0) {
			resultado.add(transporteInternoMaritimo.toString());
			resultado.add("7");
		}
		if(compras != 0) {
			resultado.add(compras.toString());
			resultado.add("8");
		}
		if(agenciasViajeChile != 0) {
			resultado.add(agenciasViajeChile.toString());
			resultado.add("9");
		}
		if(arriendoAutoSinChofer != 0) {
			resultado.add(arriendoAutoSinChofer.toString());
			resultado.add("10");
		}
		if(actividadesRecreativasDeportivas != 0) {
			resultado.add(actividadesRecreativasDeportivas.toString());
			resultado.add("11");
		}
		if(actividadesCulturales != 0) {
			resultado.add(actividadesCulturales.toString());
			resultado.add("12");
		}
		if(servicioReservasNaturales != 0) {
			resultado.add(servicioReservasNaturales.toString());
			resultado.add("13");
		}
		if(bencinaPeajeEstacionamientos != 0) {
			resultado.add(bencinaPeajeEstacionamientos.toString());
			resultado.add("14");
		}
		if(otros != 0) {
			resultado.add(otros.toString());
			resultado.add("15");
		}
		
		
		return resultado;
	}

}
