package cl.sernatur.service;

import cl.sernatur.beans.BaseIit;

public interface BaseIitService {
	public int agregar(BaseIit base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}