package cl.sernatur.service;

import cl.sernatur.beans.*;

public interface BaseTrService {
	public int agregar(BaseTr base, Actividad actividad, Alimentacion alimentacion, Alojamiento alojamiento, Ciudad ciudad, Financia financia,
			Gasto gasto, MediosInformacion mediosInformacion, Organiza organiza, RazonViaje razonViaje, 
			ServicioPaqueteTuristico servicioPaqueteTuristico, Grupo grupo) throws Exception;
	public int eliminar(String desde, String hasta) throws Exception;
}