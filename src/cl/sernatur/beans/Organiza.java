package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Organiza {
	
	private Integer ustedMismo;
	private Integer agenciaViajeOnline;
	private Integer agenciaViajeFueraChile;
	private Integer oficinaViajePresencialOnlineChilena;
	private Integer sitiosWebReserva;
	private Integer sitiosWebComparadorPrecios;
	private Integer otro;
	
	public Organiza() {
		
	}

	public Integer getUstedMismo() {
		return ustedMismo;
	}

	public void setUstedMismo(Integer ustedMismo) {
		this.ustedMismo = ustedMismo;
	}

	public Integer getAgenciaViajeOnline() {
		return agenciaViajeOnline;
	}

	public void setAgenciaViajeOnline(Integer agenciaViajeOnline) {
		this.agenciaViajeOnline = agenciaViajeOnline;
	}

	public Integer getAgenciaViajeFueraChile() {
		return agenciaViajeFueraChile;
	}

	public void setAgenciaViajeFueraChile(Integer agenciaViajeFueraChile) {
		this.agenciaViajeFueraChile = agenciaViajeFueraChile;
	}

	public Integer getOficinaViajePresencialOnlineChilena() {
		return oficinaViajePresencialOnlineChilena;
	}

	public void setOficinaViajePresencialOnlineChilena(Integer oficinaViajePresencialOnlineChilena) {
		this.oficinaViajePresencialOnlineChilena = oficinaViajePresencialOnlineChilena;
	}

	public Integer getSitiosWebReserva() {
		return sitiosWebReserva;
	}

	public void setSitiosWebReserva(Integer sitiosWebReserva) {
		this.sitiosWebReserva = sitiosWebReserva;
	}

	public Integer getSitiosWebComparadorPrecios() {
		return sitiosWebComparadorPrecios;
	}

	public void setSitiosWebComparadorPrecios(Integer sitiosWebComparadorPrecios) {
		this.sitiosWebComparadorPrecios = sitiosWebComparadorPrecios;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(ustedMismo != 0) {
			resultado.add(ustedMismo.toString());
		}
		if(agenciaViajeOnline != 0) {
			resultado.add(agenciaViajeOnline.toString());
		}
		if(agenciaViajeFueraChile != 0) {
			resultado.add(agenciaViajeFueraChile.toString());
		}
		if(oficinaViajePresencialOnlineChilena != 0) {
			resultado.add(oficinaViajePresencialOnlineChilena.toString());
		}
		if(sitiosWebReserva != 0) {
			resultado.add(sitiosWebReserva.toString());
		}
		if(sitiosWebComparadorPrecios != 0) {
			resultado.add(sitiosWebComparadorPrecios.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		return resultado;
	}

}
