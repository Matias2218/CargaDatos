package cl.sernatur.service;

import cl.sernatur.beans.BaseTeGrupo;
import cl.sernatur.beans.BaseTeFinancia;
import cl.sernatur.beans.BaseTe;

public interface BaseTeService {
	public int agregar(BaseTeGrupo base) throws Exception;
	public int agregar(BaseTeFinancia base) throws Exception;
	public int agregar(BaseTe base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}