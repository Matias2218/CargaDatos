package cl.sernatur.service;

import cl.sernatur.beans.BaseSat;

public interface BaseSatService {
	public int agregar(BaseSat base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}