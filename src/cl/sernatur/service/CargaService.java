package cl.sernatur.service;

import cl.sernatur.beans.Carga;

public interface CargaService {
	public int agregar(Carga base) throws Exception;
	public int actualizar(Carga base) throws Exception;
}
