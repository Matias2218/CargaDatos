package cl.sernatur.service;

import cl.sernatur.beans.BaseGtce;

public interface BaseGtceService {
	public int agregar(BaseGtce base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}
