package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class InternetTIB {

	private int chileturCopec;
	private int booking;
	private int lonelyPlanet;
	private int chilestuyo;
	private int tripAdvisor;
	private int airbnb;
	private int despegar;
	private int otra;

	public InternetTIB() {

	}

	public int getChileturCopec() {
		return chileturCopec;
	}

	public void setChileturCopec(int chileturCopec) {
		this.chileturCopec = chileturCopec;
	}

	public int getBooking() {
		return booking;
	}

	public void setBooking(int booking) {
		this.booking = booking;
	}

	public int getLonelyPlanet() {
		return lonelyPlanet;
	}

	public void setLonelyPlanet(int lonelyPlanet) {
		this.lonelyPlanet = lonelyPlanet;
	}

	public int getChilestuyo() {
		return chilestuyo;
	}

	public void setChilestuyo(int chilestuyo) {
		this.chilestuyo = chilestuyo;
	}

	public int getTripAdvisor() {
		return tripAdvisor;
	}

	public void setTripAdvisor(int tripAdvisor) {
		this.tripAdvisor = tripAdvisor;
	}

	public int getAirbnb() {
		return airbnb;
	}

	public void setAirbnb(int airbnb) {
		this.airbnb = airbnb;
	}

	public int getDespegar() {
		return despegar;
	}

	public void setDespegar(int despegar) {
		this.despegar = despegar;
	}

	public int getOtra() {
		return otra;
	}

	public void setOtra(int otra) {
		this.otra = otra;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (chileturCopec != 0) {
			resultado.add("1");
		}
		if (booking != 0) {
			resultado.add("2");
		}
		if (lonelyPlanet != 0) {
			resultado.add("3");
		}
		if (chilestuyo != 0) {
			resultado.add("4");
		}
		if (tripAdvisor != 0) {
			resultado.add("5");
		}
		if (airbnb != 0) {
			resultado.add("6");
		}
		if (despegar != 0) {
			resultado.add("7");
		}
		if (otra != 0) {
			resultado.add("8");
		}
		//
		return resultado;

	}

}
