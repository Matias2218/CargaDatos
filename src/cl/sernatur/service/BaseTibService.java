package cl.sernatur.service;

import cl.sernatur.beans.ActividadesTIB;
import cl.sernatur.beans.BaseTib;
import cl.sernatur.beans.EvaluacionTIB;
import cl.sernatur.beans.GastosTIB;
import cl.sernatur.beans.InformacionTIB;
import cl.sernatur.beans.InternetTIB;
import cl.sernatur.beans.MedioTransporteTIB;
import cl.sernatur.beans.MiembrosTIB;
import cl.sernatur.beans.MotivosTIB;
import cl.sernatur.beans.NochesTIB;
import cl.sernatur.beans.RazonesTIB;
import cl.sernatur.beans.ServptTIB;
import cl.sernatur.beans.UsoWebTIB;

public interface BaseTibService {
	
	public int eliminar(String desde, String hasta) throws Exception;
	public int agregar(BaseTib baseTib, ActividadesTIB actividad, EvaluacionTIB evaluacion, GastosTIB gastos,
			InformacionTIB informacion, InternetTIB internet, RazonesTIB razones, MedioTransporteTIB medioTransporte,
			MiembrosTIB miembros, MotivosTIB motivos, NochesTIB noches, ServptTIB servpt, UsoWebTIB usoWeb)
			throws Exception;

}
