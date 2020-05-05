package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class Financia {

	private Integer udMismo;
	private Integer empresaInstitucionExterna;
	private Integer familiaDesdeExtranjero;
	private Integer invitacionChile;
	private Integer otro;
	
	public Financia() {
		
	}

	public Integer getUdMismo() {
		return udMismo;
	}

	public void setUdMismo(Integer udMismo) {
		this.udMismo = udMismo;
	}

	public Integer getEmpresaInstitucionExterna() {
		return empresaInstitucionExterna;
	}

	public void setEmpresaInstitucionExterna(Integer empresaInstitucionExterna) {
		this.empresaInstitucionExterna = empresaInstitucionExterna;
	}

	public Integer getFamiliaDesdeExtranjero() {
		return familiaDesdeExtranjero;
	}

	public void setFamiliaDesdeExtranjero(Integer familiaDesdeExtranjero) {
		this.familiaDesdeExtranjero = familiaDesdeExtranjero;
	}

	public Integer getInvitacionChile() {
		return invitacionChile;
	}

	public void setInvitacionChile(Integer invitacionChile) {
		this.invitacionChile = invitacionChile;
	}

	public Integer getOtro() {
		return otro;
	}

	public void setOtro(Integer otro) {
		this.otro = otro;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		if(udMismo != 0) {
			resultado.add(udMismo.toString());
		}
		if(empresaInstitucionExterna != 0) {
			resultado.add(empresaInstitucionExterna.toString());
		}
		if(familiaDesdeExtranjero != 0) {
			resultado.add(familiaDesdeExtranjero.toString());
		}
		if(invitacionChile != 0) {
			resultado.add(invitacionChile.toString());
		}
		if(otro != 0) {
			resultado.add(otro.toString());
		}
		
		return resultado;
	}
	
}
