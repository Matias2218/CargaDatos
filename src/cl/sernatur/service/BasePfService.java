package cl.sernatur.service;

import cl.sernatur.beans.BasePf;

public interface BasePfService {
	public int agregar(BasePf base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}