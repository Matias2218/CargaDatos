package cl.sernatur.service;

import cl.sernatur.beans.BaseTrt;

public interface BaseTrtService {
	public int agregar(BaseTrt base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}