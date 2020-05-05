package cl.sernatur.service;

import cl.sernatur.beans.BaseAct;

public interface BaseActService {
	public int agregar(BaseAct base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}