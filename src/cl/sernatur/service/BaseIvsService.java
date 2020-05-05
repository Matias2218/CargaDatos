package cl.sernatur.service;

import cl.sernatur.beans.BaseIvs;

public interface BaseIvsService {
	public int agregar(BaseIvs base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}