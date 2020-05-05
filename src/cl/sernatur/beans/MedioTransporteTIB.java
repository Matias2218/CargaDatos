package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class MedioTransporteTIB {

	private int autoFamiliaresAmigos;
	private int embarcacionPrivada;
	private int moto;
	private int bicicleta;
	private int busInterurbano;
	private int tren;
	private int avion;
	private int barcoBoteLancha;
	private int crucero;
	private int otro;

	public MedioTransporteTIB() {

	}

	public int getAutoFamiliaresAmigos() {
		return autoFamiliaresAmigos;
	}

	public void setAutoFamiliaresAmigos(int autoFamiliaresAmigos) {
		this.autoFamiliaresAmigos = autoFamiliaresAmigos;
	}

	public int getEmbarcacionPrivada() {
		return embarcacionPrivada;
	}

	public void setEmbarcacionPrivada(int embarcacionPrivada) {
		this.embarcacionPrivada = embarcacionPrivada;
	}

	public int getMoto() {
		return moto;
	}

	public void setMoto(int moto) {
		this.moto = moto;
	}

	public int getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(int bicicleta) {
		this.bicicleta = bicicleta;
	}

	public int getTren() {
		return tren;
	}

	public void setTren(int tren) {
		this.tren = tren;
	}

	public int getAvion() {
		return avion;
	}

	public void setAvion(int avion) {
		this.avion = avion;
	}

	public int getBarcoBoteLancha() {
		return barcoBoteLancha;
	}

	public void setBarcoBoteLancha(int barcoBoteLancha) {
		this.barcoBoteLancha = barcoBoteLancha;
	}

	public int getCrucero() {
		return crucero;
	}

	public void setCrucero(int crucero) {
		this.crucero = crucero;
	}

	public int getOtro() {
		return otro;
	}

	public void setOtro(int otro) {
		this.otro = otro;
	}
	
	public int getBusInterurbano() {
		return busInterurbano;
	}

	public void setBusInterurbano(int busInterurbano) {
		this.busInterurbano = busInterurbano;
	}

	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if (autoFamiliaresAmigos != 0) {
			resultado.add(String.valueOf(1));
		}
		if (embarcacionPrivada != 0) {
			resultado.add(String.valueOf(2));
		}
		if (moto != 0) {
			resultado.add(String.valueOf(3));
		}
		if (bicicleta != 0) {
			resultado.add(String.valueOf(4));
		}
		if (tren != 0) {
			resultado.add(String.valueOf(5));
		}
		if (avion != 0) {
			resultado.add(String.valueOf(6));
		}
		if (barcoBoteLancha != 0) {
			resultado.add(String.valueOf(7));
		}
		if (crucero != 0) {
			resultado.add(String.valueOf(8));
		}
		if (otro != 0) {
			resultado.add(String.valueOf(9));
		}
		//
		return resultado;

	}

}
