package cl.sernatur.service;

import cl.sernatur.beans.BaseIpc;

public interface BaseIpcService {
	public int agregar(BaseIpc base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}