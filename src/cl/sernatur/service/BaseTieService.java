package cl.sernatur.service;

import cl.sernatur.beans.BaseTie;

public interface BaseTieService {
	
	public int agregar(BaseTie baseTie) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;

}
