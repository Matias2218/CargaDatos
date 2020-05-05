package cl.sernatur.service;

import cl.sernatur.beans.BaseJac;

public interface BaseJacService {
	public int agregar(BaseJac base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}