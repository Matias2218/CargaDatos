package cl.sernatur.service;

import cl.sernatur.beans.BaseTrd;

public interface BaseTrdService {
	public int agregar(BaseTrd base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}