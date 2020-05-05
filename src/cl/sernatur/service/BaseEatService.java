package cl.sernatur.service;

import cl.sernatur.beans.BaseEatTotal;
import cl.sernatur.beans.BaseEatNacional;
import cl.sernatur.beans.BaseEatExtranjero;
import cl.sernatur.beans.BaseEat;

public interface BaseEatService {
	public int agregar(BaseEatTotal base) throws Exception;
	public int agregar(BaseEatNacional base) throws Exception;
	public int agregar(BaseEatExtranjero base) throws Exception;
	public int agregar(BaseEat base) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}