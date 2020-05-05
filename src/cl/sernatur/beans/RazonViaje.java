package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class RazonViaje {
	
	private Integer naturalezaPaisajesFloraFauna;
	private Integer culturaLocal;
	private Integer losChilenos;
	private Integer conectividad;
	private Integer sensacionSeguridadPais;
	private Integer clima;
	private Integer vinosViñas;
	private Integer conocerChilePrimeraVez;
	private Integer conocerLugaresPendientes;
	private Integer descansar;
	private Integer incluidoEnRutaViaje;
	private Integer familiaAmigosResidentesChile;
	private Integer compras;
	private Integer otro;
	
	public RazonViaje() {
		
	}

	public Integer getNaturalezaPaisajesFloraFauna() {
		return naturalezaPaisajesFloraFauna;
	}

	public void setNaturalezaPaisajesFloraFauna(Integer naturalezaPaisajesFloraFauna) {
		this.naturalezaPaisajesFloraFauna = naturalezaPaisajesFloraFauna;
	}

	public Integer getCulturaLocal() {
		return culturaLocal;
	}

	public void setCulturaLocal(Integer culturaLocal) {
		this.culturaLocal = culturaLocal;
	}

	public Integer getLosChilenos() {
		return losChilenos;
	}

	public void setLosChilenos(Integer losChilenos) {
		this.losChilenos = losChilenos;
	}

	public Integer getConectividad() {
		return conectividad;
	}

	public void setConectividad(Integer conectividad) {
		this.conectividad = conectividad;
	}

	public Integer getSensacionSeguridadPais() {
		return sensacionSeguridadPais;
	}

	public void setSensacionSeguridadPais(Integer sensacionSeguridadPais) {
		this.sensacionSeguridadPais = sensacionSeguridadPais;
	}

	public Integer getClima() {
		return clima;
	}

	public void setClima(Integer clima) {
		this.clima = clima;
	}

	public Integer getVinosViñas() {
		return vinosViñas;
	}

	public void setVinosViñas(Integer vinosViñas) {
		this.vinosViñas = vinosViñas;
	}

	public Integer getConocerChilePrimeraVez() {
		return conocerChilePrimeraVez;
	}

	public void setConocerChilePrimeraVez(Integer conocerChilePrimeraVez) {
		this.conocerChilePrimeraVez = conocerChilePrimeraVez;
	}

	public Integer getConocerLugaresPendientes() {
		return conocerLugaresPendientes;
	}

	public void setConocerLugaresPendientes(Integer conocerLugaresPendientes) {
		this.conocerLugaresPendientes = conocerLugaresPendientes;
	}

	public Integer getDescansar() {
		return descansar;
	}

	public void setDescansar(Integer descansar) {
		this.descansar = descansar;
	}

	public Integer getIncluidoEnRutaViaje() {
		return incluidoEnRutaViaje;
	}

	public void setIncluidoEnRutaViaje(Integer incluidoEnRutaViaje) {
		this.incluidoEnRutaViaje = incluidoEnRutaViaje;
	}

	public Integer getFamiliaAmigosResidentesChile() {
		return familiaAmigosResidentesChile;
	}

	public void setFamiliaAmigosResidentesChile(Integer familiaAmigosResidentesChile) {
		this.familiaAmigosResidentesChile = familiaAmigosResidentesChile;
	}

	public Integer getCompras() {
		return compras;
	}

	public void setCompras(Integer compras) {
		this.compras = compras;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(naturalezaPaisajesFloraFauna != 0) {
			resultado.add(naturalezaPaisajesFloraFauna.toString());
		}
		if(culturaLocal != 0) {
			resultado.add(culturaLocal.toString());
		}
		if(losChilenos != 0) {
			resultado.add(losChilenos.toString());
		}
		if(conectividad != 0) {
			resultado.add(conectividad.toString());
		}
		if(sensacionSeguridadPais != 0) {
			resultado.add(sensacionSeguridadPais.toString());
		}
		if(clima != 0) {
			resultado.add(clima.toString());
		}
		if(vinosViñas != 0) {
			resultado.add(vinosViñas.toString());
		}
		if(conocerChilePrimeraVez != 0) {
			resultado.add(conocerChilePrimeraVez.toString());
		}
		if(conocerLugaresPendientes != 0) {
			resultado.add(conocerLugaresPendientes.toString());
		}
		if(descansar != 0) {
			resultado.add(descansar.toString());
		}
		if(incluidoEnRutaViaje != 0) {
			resultado.add(incluidoEnRutaViaje.toString());
		}
		if(familiaAmigosResidentesChile != 0) {
			resultado.add(familiaAmigosResidentesChile.toString());
		}
		if(compras != 0) {
			resultado.add(compras.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}

}
