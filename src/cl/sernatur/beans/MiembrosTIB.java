package cl.sernatur.beans;

import java.util.ArrayList;
import java.util.List;

public class MiembrosTIB {
	
	private Integer m1;
	private Integer m2;
	private Integer m3;
	private Integer m4;
	private Integer m5;
	private Integer m6;
	private Integer m7;
	private Integer m8;
	private Integer m9;
	private Integer m10;
	private Integer m11;
	private Integer m12;

	public MiembrosTIB() {
		
	}

	public Integer getM1() {
		return m1;
	}

	public void setM1(Integer m1) {
		this.m1 = m1;
	}

	public Integer getM2() {
		return m2;
	}

	public void setM2(Integer m2) {
		this.m2 = m2;
	}

	public Integer getM3() {
		return m3;
	}

	public void setM3(Integer m3) {
		this.m3 = m3;
	}

	public Integer getM4() {
		return m4;
	}

	public void setM4(Integer m4) {
		this.m4 = m4;
	}

	public Integer getM5() {
		return m5;
	}

	public void setM5(Integer m5) {
		this.m5 = m5;
	}

	public Integer getM6() {
		return m6;
	}

	public void setM6(Integer m6) {
		this.m6 = m6;
	}

	public Integer getM7() {
		return m7;
	}

	public void setM7(Integer m7) {
		this.m7 = m7;
	}

	public Integer getM8() {
		return m8;
	}

	public void setM8(Integer m8) {
		this.m8 = m8;
	}

	public Integer getM9() {
		return m9;
	}

	public void setM9(Integer m9) {
		this.m9 = m9;
	}

	public Integer getM10() {
		return m10;
	}

	public void setM10(Integer m10) {
		this.m10 = m10;
	}

	public Integer getM11() {
		return m11;
	}

	public void setM11(Integer m11) {
		this.m11 = m11;
	}

	public Integer getM12() {
		return m12;
	}

	public void setM12(Integer m12) {
		this.m12 = m12;
	}
	
	public List<String> getValoresValidos() {
		List<String> resultado = new ArrayList<String>();
		//
		if(m1 != 0) {
			resultado.add("1");
			resultado.add(m1.toString());
		}if(m2 != 0) {
			resultado.add("2");
			resultado.add(m2.toString());
		}if(m3 != 0) {
			resultado.add("3");
			resultado.add(m3.toString());
		}if(m4 != 0) {
			resultado.add("4");
			resultado.add(m4.toString());
		}if(m5 != 0) {
			resultado.add("5");
			resultado.add(m5.toString());
		}if(m6 != 0) {
			resultado.add("6");
			resultado.add(m6.toString());
		}if(m7 != 0) {
			resultado.add("7");
			resultado.add(m7.toString());
		}if(m8 != 0) {
			resultado.add("8");
			resultado.add(m8.toString());
		}if(m9 != 0) {
			resultado.add("9");
			resultado.add(m9.toString());
		}if(m10 != 0) {
			resultado.add("10");
			resultado.add(m10.toString());
		}if(m11 != 0) {
			resultado.add("11");
			resultado.add(m11.toString());
		}if(m12 != 0) {
			resultado.add("12");
			resultado.add(m12.toString());
		}
		//
		return resultado;
		
	}	

}
