package cl.sernatur.service;

import cl.sernatur.beans.BasePdi;

public interface BasePdiService {
	public int agregar(BasePdi base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}