package cl.sernatur.service;

import cl.sernatur.beans.BasePl;

public interface BasePlService {
	public int agregar(BasePl base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}