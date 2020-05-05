package cl.sernatur.service;

import cl.sernatur.beans.BaseTipGastoItem;
import cl.sernatur.beans.BaseTip;

public interface BaseTipService {
	public int agregar(BaseTipGastoItem base) throws Exception;
	public int agregar(BaseTip base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}