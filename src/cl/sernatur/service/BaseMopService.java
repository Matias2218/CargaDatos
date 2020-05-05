package cl.sernatur.service;

import cl.sernatur.beans.BaseMop;

public interface BaseMopService {
	public int agregar(BaseMop base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}