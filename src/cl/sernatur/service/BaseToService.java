package cl.sernatur.service;

import cl.sernatur.beans.BaseTo;

public interface BaseToService {
	public int agregar(BaseTo base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}