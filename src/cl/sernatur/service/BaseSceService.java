package cl.sernatur.service;

import cl.sernatur.beans.BaseSce;

public interface BaseSceService {
	public int agregar(BaseSce base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}