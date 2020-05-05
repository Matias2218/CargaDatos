package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class NochesTIB {
	
	private Integer comunaDestinoSecundario1;
	private Integer comunaDestinoSecundario2;
	private Integer comunaDestinoSecundario3;
	private Integer comunaDestinoSecundario4;
	private Integer comunaDestinoSecundario5;
	private Integer nochesDestinoSecundario1;
	private Integer nochesDestinoSecundario2;
	private Integer nochesDestinoSecundario3;
	private Integer nochesDestinoSecundario4;
	private Integer nochesDestinoSecundario5;

	public NochesTIB() {
		
	}

	public Integer getComunaDestinoSecundario1() {
		return comunaDestinoSecundario1;
	}

	public void setComunaDestinoSecundario1(Integer comunaDestinoSecundario1) {
		this.comunaDestinoSecundario1 = comunaDestinoSecundario1;
	}

	public Integer getComunaDestinoSecundario2() {
		return comunaDestinoSecundario2;
	}

	public void setComunaDestinoSecundario2(Integer comunaDestinoSecundario2) {
		this.comunaDestinoSecundario2 = comunaDestinoSecundario2;
	}

	public Integer getComunaDestinoSecundario3() {
		return comunaDestinoSecundario3;
	}

	public void setComunaDestinoSecundario3(Integer comunaDestinoSecundario3) {
		this.comunaDestinoSecundario3 = comunaDestinoSecundario3;
	}

	public Integer getComunaDestinoSecundario4() {
		return comunaDestinoSecundario4;
	}

	public void setComunaDestinoSecundario4(Integer comunaDestinoSecundario4) {
		this.comunaDestinoSecundario4 = comunaDestinoSecundario4;
	}

	public Integer getComunaDestinoSecundario5() {
		return comunaDestinoSecundario5;
	}

	public void setComunaDestinoSecundario5(Integer comunaDestinoSecundario5) {
		this.comunaDestinoSecundario5 = comunaDestinoSecundario5;
	}

	public Integer getNochesDestinoSecundario1() {
		return nochesDestinoSecundario1;
	}

	public void setNochesDestinoSecundario1(Integer nochesDestinoSecundario1) {
		this.nochesDestinoSecundario1 = nochesDestinoSecundario1;
	}

	public Integer getNochesDestinoSecundario2() {
		return nochesDestinoSecundario2;
	}

	public void setNochesDestinoSecundario2(Integer nochesDestinoSecundario2) {
		this.nochesDestinoSecundario2 = nochesDestinoSecundario2;
	}

	public Integer getNochesDestinoSecundario3() {
		return nochesDestinoSecundario3;
	}

	public void setNochesDestinoSecundario3(Integer nochesDestinoSecundario3) {
		this.nochesDestinoSecundario3 = nochesDestinoSecundario3;
	}

	public Integer getNochesDestinoSecundario4() {
		return nochesDestinoSecundario4;
	}

	public void setNochesDestinoSecundario4(Integer nochesDestinoSecundario4) {
		this.nochesDestinoSecundario4 = nochesDestinoSecundario4;
	}

	public Integer getNochesDestinoSecundario5() {
		return nochesDestinoSecundario5;
	}

	public void setNochesDestinoSecundario5(Integer nochesDestinoSecundario5) {
		this.nochesDestinoSecundario5 = nochesDestinoSecundario5;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if(comunaDestinoSecundario1 != 0) {
			resultado.add("1");
			resultado.add(comunaDestinoSecundario1.toString());
			resultado.add(nochesDestinoSecundario1.toString());
		}if(comunaDestinoSecundario2 != 0) {
			resultado.add("2");
			resultado.add(comunaDestinoSecundario2.toString());
			resultado.add(nochesDestinoSecundario2.toString());
		}if(comunaDestinoSecundario3 != 0) {
			resultado.add("3");
			resultado.add(comunaDestinoSecundario3.toString());
			resultado.add(nochesDestinoSecundario3.toString());
		}if(comunaDestinoSecundario4 != 0) {
			resultado.add("4");
			resultado.add(comunaDestinoSecundario4.toString());
			resultado.add(nochesDestinoSecundario4.toString());
		}if(comunaDestinoSecundario5 != 0) {
			resultado.add("5");
			resultado.add(comunaDestinoSecundario5.toString());
			resultado.add(nochesDestinoSecundario5.toString());
		}
		//
		return resultado;
		
	}

}
