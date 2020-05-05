package cl.sernatur.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import cl.advise.util.Util;
import cl.sernatur.beans.ActividadesTIB;
import cl.sernatur.beans.BaseTib;
import cl.sernatur.beans.Carga;
import cl.sernatur.beans.CargaBitacora;
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
import cl.sernatur.service.BaseTibService;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.BaseTibServiceImpl;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;
import cl.sernatur.service.impl.CargaServiceImpl;

public class DataTibNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseTib base;
	private BaseTibService baseService;
	private Carga carga;
	private CargaService cargaService;
	private CargaBitacora cargaBitacora;
	private CargaBitacoraService cargaBitacoraService;
	private int flag;
	private BufferedReader fileReader;
	List<String> bitacora = new ArrayList<>();
	
	private GastosTIB gastos;
	private ActividadesTIB actividades;
	private EvaluacionTIB evaluacion;
	private InformacionTIB informacion;
	private InternetTIB internet;
	private MedioTransporteTIB medioTransporte;
	private MiembrosTIB miembros;
	private MotivosTIB motivos;
	private NochesTIB noches;
	private ServptTIB servpt;
	private UsoWebTIB usoWeb;
	private RazonesTIB razones;
	
	
	@SuppressWarnings("finally")
	public String[] procesaData(Properties prop, DataSource dataSource, String nombreArchivo, String archivoLog, String archivoCarga, String idCarga)  {
		Util.setLog(archivoLog, "I", "Inicio", "");
		
		this.prop = prop;
		this.dataSource = dataSource;
		this.nombreArchivo = nombreArchivo;
		this.archivoLog = archivoLog ;
		this.archivoCarga = archivoCarga;
		this.idCarga = idCarga;
		
		String[] retorno = new String[]{"false",""};
		
		try {
			// Validar formato de archivo
			if (validarFormato()) {
				
				// Crear conexi�n
				crearConexion();
					
				// Eliminar registros
				eliminarRegistros();
				
				// Insertar carga
				insertarCarga(0,1,100);
					
				// Recorrer Archivo
				Util.setLog(archivoLog, "I", "Insertar Registros - Inicio", "");
				fileReader = new BufferedReader(new FileReader(archivoCarga));
				long cuenta = 0;
				long cuentaOk = 0;
				long cuentaError = 0;
				String line = "";
				while ((line = fileReader.readLine()) != null) {
					if (cuenta > 0) {
						String[] datos = line.split(prop.getProperty("file.entrada.sep"));
						
						// Validar datos
						if (validartDatos(datos, cuenta)) {
							
							// Insertar registro
							if (insertarRegistro(cuenta)) {
								cuentaOk++;
							} else {
								cuentaError++;
							}
							
						} else {
							cuentaError++;
						}
						
					}
					cuenta++;
				}
				
				Util.setLog(archivoLog, "I", "Insertar Registros --> OK (" + cuentaOk + ") -  ERROR (" + cuentaError + ")", "");
				if (cuentaError == 0) {
					con.commit();
					retorno[0] = "true";
					retorno[1] = "Carga de registros exitosa --> OK: " + String.valueOf(cuentaOk) + " - ERROR: 0";
					// Insertar carga ok
					insertarCarga(cuentaOk,2,1);
					bitacora.add("800;0;;;Ok Se cargaron " + String.valueOf(cuentaOk) + " registros");
				} else {
					con.rollback();
					retorno[1] = "Carga de registros con error --> OK: " + String.valueOf(cuentaOk) + " - ERROR: " + String.valueOf(cuentaError);
					// Insertar carga error
					insertarCarga(0,1,100);
				}
				
				// Insertar carga bit�cora
				for (String item : bitacora) {
					insertarCargaBitacora(item.split(";"));
				}
				con.commit();
				con.close();
				Util.setLog(archivoLog, "I", "Insertar Registros - Fin", "");
				
			} else {
				Util.setLog(archivoLog, "I", "Formato Archivo --> ERROR", "");
				retorno[1] = "Carga de registros con error --> Formato de archivo invalido";
			}
			
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Error", ex.getMessage());
			ex.printStackTrace();
			retorno[1] = "Carga de registros con error --> Problemas al procesar archivo";
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Error", ex.getMessage());
			ex.printStackTrace();
			retorno[1] = "Carga de registros con error --> Problemas al procesar archivo";
		} finally {
			Util.setLog(archivoLog, "I", "Fin", "");
			return retorno;
		}
		
	}
	
	
	private boolean validarFormato() throws Exception {
		boolean retorno = false;
		Util.setLog(archivoLog, "I", "Validar Formato Archivo --> " + nombreArchivo, "");
		formatoFecha = Util.validarFormatoAnio(archivoCarga);
		if (!(formatoFecha[0].equals("0000") || formatoFecha[1].equals("0000"))) {
			String desde = formatoFecha[0];
			String hasta = formatoFecha[1];
			Util.setLog(archivoLog, "I", "Validar Formato Archivo --> OK (" + desde +" - " + hasta + ")", "");
			retorno = true;
		} else {
			Util.setLog(archivoLog, "I", "Validar Formato Archivo --> ERROR", "");
		}
		return retorno;
	}
	
	
	private void crearConexion() throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
	}
	
	
	private void eliminarRegistros() throws Exception {
		baseService = new BaseTibServiceImpl(con);
		try {
			String desde = formatoFecha[0];
			String hasta = formatoFecha[1];
			flag = baseService.eliminar(desde, hasta);
			Util.setLog(archivoLog, "I", "Eliminar Registros --> OK (" + flag + ")", "");
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Eliminar Registros --> ERROR", ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	private boolean validartDatos(String[] data, long cuenta) throws Exception {
		boolean retorno = false;
		try {
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			String codigoEncuesta = data[1].trim();
			String fechaEncuesta = data[2].trim();
	        String anio = data[0].trim();
	        String mes = fechaEncuesta.substring(5,7);
			String iteracion = data[3].trim();
			String ronda = data[4].trim();
			String comunaOrigen = data[5].trim();
			String comunaDestino = data[7].trim();
			String codigoDestinoEmat = data[9].trim();
			String tipoViaje = data[11].trim();
			String fdsl = data[13].trim();
			String nse = data[15].trim();
			String temporada = data[17].trim();
			String tipoAlojamiento = data[19].trim();
			String organizaViaje = data[21].trim();
			String organizaGastos = data[23].trim();
			String planifica = data[25].trim();
			String expectativa = data[27].trim();
			String medioTransporteId = data[29].trim();
			String motivoViaje = data[31].trim();
			String totalGastoViaje = data[33].trim().replace(",", ".");
			String personasViajan = data[34].trim().replace(",", ".");
			String numeroVisitasPrevDp = data[35].trim().replace(",", ".");
			String numeroVisitasAnteriorDp = data[36].trim().replace(",", ".");
			String destinoExtraAloja = data[37].trim().replace(",", ".");
			String comparteGastoNumPersonas = data[38].trim().replace(",", ".");
			String gpdi = data[39].trim().replace(",", ".");
			String fePersona = data[40].trim().replace(",", ".");
			String feHogar = data[41].trim().replace(",", ".");
			String faVivAjuste2 = data[42].trim().replace(",", ".");
			String faPersonasViajan = data[43].trim().replace(",", ".");
			//
			String gastoPT = data[44].trim().replace(",", ".");
			String gastoAloja = data[45].trim().replace(",", ".");
			String gastoAlimenta = data[46].trim().replace(",", ".");
			String gastoAvion = data[47].trim().replace(",", ".");
			String gastoBusesInter = data[48].trim().replace(",", ".");
			String gastoMaritimoTrenOtro = data[49].trim().replace(",", ".");
			String gastoArriendoAuto = data[50].trim().replace(",", ".");
			String gastoMicroTaxiColectivoMetro = data[51].trim().replace(",", ".");
			String gastoPeajesCombustibleEstacionamiento = data[52].trim().replace(",", ".");
			String gastoRecreativaCulturales = data[53].trim().replace(",", ".");
			String gastoCompras = data[54].trim().replace(",", ".");
			String gastoOtros = data[55].trim().replace(",", ".");
			//
			String nocheComunaSecun1 = data[56].trim();
			String nocheDestinoSecun1 = data[57].trim();
			String nocheComunaSecun2 = data[58].trim();
			String nocheDestinoSecun2 = data[59].trim();
			String nocheComunaSecun3 = data[60].trim();
			String nocheDestinoSecun3 = data[61].trim();
			String nocheComunaSecun4 = data[62].trim();
			String nocheDestinoSecun4 = data[63].trim();
			String nocheComunaSecun5 = data[64].trim();
			String nocheDestinoSecun5 = data[65].trim();
			//
			String m1 = data[66].trim();
			String m2 = data[67].trim();
			String m3 = data[68].trim();
			String m4 = data[69].trim();
			String m5 = data[70].trim();
			String m6 = data[71].trim();
			String m7 = data[72].trim();
			String m8 = data[73].trim();
			String m9 = data[74].trim();
			String m10 = data[75].trim();
			String m11 = data[76].trim();
			String m12 = data[77].trim();
			//
			String recomendacionFamiliaresAmigos = data[78].trim();
			String agenciaViajes = data[79].trim();
			String cartelesViaPublica = data[80].trim();
			String television = data[81].trim();
			String radio = data[82].trim();
			String diarioRevistas = data[83].trim();
			String experienciasViajesAnteriores = data[84].trim();
			String internetInfo = data[85].trim();
			String comentariosRrSs = data[86].trim();
			String guiasTuristicasFolletos = data[87].trim();
			String otra = data[88].trim();
			//
			String transporteBuscarInfo = data[89].trim();
			String transportePagar = data[90].trim();
			String alojamientoBuscarInfo = data[91].trim();
			String alojamientoReservar = data[92].trim();
			String alojamientoPagar = data[93].trim();
			String actividadesEsparcimientoBuscarInfo = data[94].trim();
			String actividadesEsparcimientoReservar = data[95].trim();
			String actividadesEsparcimientoPagar = data[96].trim();
			//
			String actividadesProfesionales = data[97].trim();
			String visitasAmigosFamiliares = data[98].trim();
			String aprendizajeLenguaOriginariaCostumbresTradiciones = data[99].trim();
			String asistenciaEventosCulturales = data[100].trim();
			String asistenciaFestivalesFerias = data[101].trim();
			String asistenciaEventosDeportivos = data[102].trim();
			String visitaPaisajesAtractivosNaturales = data[103].trim();
			String visitaMuseos = data[104].trim();
			String visitaMonumentosHistoricos = data[105].trim();
			String visitaSitiosPatrimonialesIglesias = data[106].trim();
			String visitaArquitecturaModerna = data[107].trim();
			String visitaPequeñosPueblosCiudades = data[108].trim();
			String visitaParqueAtracciones = data[109].trim();
			String visitaMercadosEspaciosPopulares = data[110].trim();
			String visitaCasinoSalaJuegos = data[111].trim();
			String irCompras = data[112].trim();
			String visitaTermas = data[113].trim();
			String visitaRestaurantGastronomiaChilena = data[114].trim();
			String visitaRestaurantGastronomiaIntegerernacional = data[115].trim();
			String visitaBaresPubLugaresNocturnos = data[116].trim();
			String peregrinacionesEventosReligiosos = data[117].trim();
			String visitaPlayas = data[118].trim();
			String cazaPescaCamping = data[119].trim();
			String excursionismoSenderismo = data[120].trim();
			String esquiar = data[121].trim();
			String visitaParquesNacionales = data[122].trim();
			String actividadesDeportivasAireLibre = data[123].trim();
			String visitaObservatoriosAstronomicos = data[124].trim();
			String visitaSitiosArqueologicos = data[125].trim();
			String visitaViñas = data[126].trim();
			String otro = data[127].trim();
			//
			String chileturCopec = data[128].trim();
			String booking = data[129].trim();
			String lonelyPlanet = data[130].trim();
			String chilestuyo = data[131].trim();
			String tripAdvisor = data[132].trim();
			String airbnb = data[133].trim();
			String despegar = data[134].trim();
			String otraInternet = data[135].trim();
			//
			String razonCercania = data[136].trim();
			String razonCasaDeptoEnLugar = data[137].trim();
			String razonVivenFamiliares = data[138].trim();
			String razonRecomendacionFamiliaAmigos = data[139].trim();
			String razonCalidadAlojamiento = data[140].trim();
			String razonRelacionPrecioCalidad = data[141].trim();
			String razonPaisajesNaturales = data[142].trim();
			String razonConocerOtrasPersonas = data[143].trim();
			String razonRealizarDiversasActividades = data[144].trim();
			String razonVariedadCosasHacerVer = data[145].trim();
			String razonActividadesCulturales = data[146].trim();
			String razonVidaNocturna = data[147].trim();
			String razonLugarSeguroTranquilo = data[148].trim();
			String razonGastronomia = data[149].trim();
			String razonProfesionales = data[150].trim();
			String razonOtra = data[151].trim();
			//
			String precioCalidadAlojamientoTuristico = data[152].trim();
			String cantidadVariedadAlojamientoTuristico = data[153].trim();
			String precioCalidadServiciosAlimentacion = data[154].trim();
			String cantidadVariedadServiciosAlimentacion = data[155].trim();
			String limpiezaGeneralEspacioPublico = data[156].trim();
			String conectividadWiFiEspaciosPublicos = data[157].trim();
			String señalizacionAtractivosServiciosTuristicos = data[158].trim();
			String disponibilidadCentrosInfoTuristica = data[159].trim();
			String seguridadActividadesTurismoAventura = data[160].trim();
			String contaminacionAmbiental = data[161].trim();
			String disponibilidadBancosCajeros = data[162].trim();
			String disponibilidadTransporteLocal = data[163].trim();
			String satisfaccionGeneral = data[164].trim();
			String precioCalidadActividadTuristicas = data[165].trim();
			//
			String autoFamiliaresAmigos = data[166].trim();
			String embarcacionPrivada = data[167].trim();
			String moto = data[168].trim();
			String bicicleta = data[169].trim();
			String busInterurbano = data[170].trim();
			String tren = data[171].trim();
			String avion = data[171].trim();
			String barcoBoteLancha = data[172].trim();
			String crucero = data[173].trim();
			String otroMedioTransporte = data[174].trim();
			//
			String transporteADestino = data[176].trim();
			String alojamiento = data[177].trim();
			String comidas = data[178].trim();
			String transporteEnDestinoArriendoAuto = data[179].trim();
			String excursionesEspectaculo = data[180].trim();
			String seguroSaludAsistenciaViajero = data[181].trim();
			//
			String observaciones = data[182].trim();
			//
			
			// Validar formato de año
			  if (anio.length() != 4) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en linea " + cuenta, "Formato invalido de año");
				bitacora.add("210;" + cuenta + ";año;" + anio + ";Formato invalido de año, linea " + cuenta);
			
			// Validar rango de año
			} else if (Integer.parseInt(anio) < anioMinimo) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "Rango invalido de año");
				bitacora.add("211;" + cuenta + ";año;" + anio + ";Rango invalido de año, l�nea " + cuenta);
			
			// Validar tipo de dato año
			} else if (!Util.esNumero(anio)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "Tipo invalido de año");
				bitacora.add("213;" + cuenta + ";año;" + anio + ";Tipo invalido de año, l�nea " + cuenta);
				
			// Validar tipo de dato c�digo encuesta
			} else if (!Util.esNumero(codigoEncuesta)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de c�digo encuesta");
				bitacora.add("213;" + cuenta + ";codigo_encuesta;" + codigoEncuesta + ";Tipo invalido de c�digo encuesta, l�nea " + cuenta);
			
			} 
			// Validar tipo de dato iteraci�n
			else {
				Integer semestre = 0, trimestre = 0;
				Integer mesAux = Integer.valueOf(mes);
				//Setear primeros datos
				if(mesAux < 7) {
					semestre = 1;
					if(mesAux < 4) {
						trimestre = 1;
					} else {
						trimestre = 2;
					}
				} else {
					semestre = 2;
					if(mesAux < 10) {
						trimestre = 3;
					} else {
						trimestre = 4;
					}
				}
				
				// Setear datos
				base = new BaseTib();
				actividades = new ActividadesTIB();
				evaluacion = new EvaluacionTIB();
				gastos = new GastosTIB();
				informacion = new InformacionTIB();
				internet = new InternetTIB();
				razones = new RazonesTIB();
				medioTransporte = new MedioTransporteTIB();
				miembros = new MiembrosTIB();
				motivos = new MotivosTIB();
				noches = new NochesTIB();
				servpt = new ServptTIB();
				usoWeb = new UsoWebTIB();
				
				//
				base.setId_anio(Integer.valueOf(anio));
				base.setId_mes(Integer.valueOf(mes));
				base.setId_mes_semestre(Integer.valueOf(semestre));
				base.setId_mes_trimestre(Integer.valueOf(trimestre));
				base.setCodigo_encuesta(Integer.valueOf(codigoEncuesta));
				base.setFecha(fechaEncuesta);
				base.setIteracion(Integer.valueOf(iteracion));
				base.setRonda(Integer.valueOf(ronda));
				base.setId_comuna_origen(Integer.valueOf(comunaOrigen));
				base.setId_comuna_destino(Integer.valueOf(comunaDestino));
				base.setId_destino_emat(Integer.valueOf(codigoDestinoEmat));
				base.setId_tipo_viaje(Integer.valueOf(tipoViaje));
				base.setId_fdsl(Integer.valueOf(fdsl));
				base.setId_nse(Integer.valueOf(nse));
				base.setId_temporada(Integer.valueOf(temporada));
				base.setId_tipo_alojamiento(Integer.valueOf(tipoAlojamiento));
				base.setId_organiza(Integer.valueOf(organizaViaje));
				base.setId_organiza_gasto(Integer.valueOf(organizaGastos));
				base.setId_planifica(Integer.valueOf(planifica));
				base.setId_expectativa(Integer.valueOf(expectativa));
				base.setId_mtransporte(Integer.valueOf(medioTransporteId));
				base.setId_motivo(Integer.valueOf(motivoViaje));
				base.setGasto_total(Float.valueOf(totalGastoViaje));
				base.setF_personas_viajan(Float.valueOf(personasViajan));
				base.setNum_visitas_prev_dp(Integer.valueOf(numeroVisitasPrevDp));
				base.setNumero_visitas_anterior_dp(Integer.valueOf(numeroVisitasAnteriorDp));
				base.setDestino_extra_aloja(Integer.valueOf(destinoExtraAloja));
				base.setGasto_compartido_numero(Integer.valueOf(comparteGastoNumPersonas));
				base.setGpdi(Float.valueOf(gpdi));
				base.setFe_personas(Float.valueOf(fePersona));
				base.setFe_hogares(Float.valueOf(feHogar));
				base.setFa_viv_ajuste2(Float.valueOf(faVivAjuste2));
				base.setFa_personas_viajan_d(Float.valueOf(faPersonasViajan));
				base.setObsrvacion(observaciones);
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				//
				gastos.setPaqueteTuristico(Float.valueOf(gastoPT));
				gastos.setAlojamiento(Float.valueOf(gastoAloja));
				gastos.setAlimentacion(Float.valueOf(gastoAlimenta));
				gastos.setAvion(Float.valueOf(gastoAvion));
				gastos.setBusesInterurbanos(Float.valueOf(gastoBusesInter));
				gastos.setTransporteMaritimoTrenOtro(Float.valueOf(gastoMaritimoTrenOtro));
				gastos.setArriendoAuto(Float.valueOf(gastoArriendoAuto));
				gastos.setMicroTaxiColectivoMetro(Float.valueOf(gastoMicroTaxiColectivoMetro));
				gastos.setPeajesCombustibleEstacionamiento(Float.valueOf(gastoPeajesCombustibleEstacionamiento));
				gastos.setActividadesRecreativasCulturalesDeportivas(Float.valueOf(gastoRecreativaCulturales));
				gastos.setCompras(Float.valueOf(gastoCompras));
				gastos.setOtros(Float.valueOf(gastoOtros));
				//
				noches.setComunaDestinoSecundario1(Integer.valueOf(nocheComunaSecun1));
				noches.setComunaDestinoSecundario2(Integer.valueOf(nocheComunaSecun2));
				noches.setComunaDestinoSecundario3(Integer.valueOf(nocheComunaSecun3));
				noches.setComunaDestinoSecundario4(Integer.valueOf(nocheComunaSecun4));
				noches.setComunaDestinoSecundario5(Integer.valueOf(nocheComunaSecun5));
				noches.setNochesDestinoSecundario1(Integer.valueOf(nocheDestinoSecun1));
				noches.setNochesDestinoSecundario2(Integer.valueOf(nocheDestinoSecun2));
				noches.setNochesDestinoSecundario3(Integer.valueOf(nocheDestinoSecun3));
				noches.setNochesDestinoSecundario4(Integer.valueOf(nocheDestinoSecun4));
				noches.setNochesDestinoSecundario5(Integer.valueOf(nocheDestinoSecun5));
				//
				miembros.setM1(Integer.valueOf(m1));
				miembros.setM2(Integer.valueOf(m2));
				miembros.setM3(Integer.valueOf(m3));
				miembros.setM4(Integer.valueOf(m4));
				miembros.setM5(Integer.valueOf(m5));
				miembros.setM6(Integer.valueOf(m6));
				miembros.setM7(Integer.valueOf(m7));
				miembros.setM8(Integer.valueOf(m8));
				miembros.setM9(Integer.valueOf(m9));
				miembros.setM10(Integer.valueOf(m10));
				miembros.setM11(Integer.valueOf(m11));
				miembros.setM12(Integer.valueOf(m12));
				//
				informacion.setRecomendacionFamiliaresAmigos(Integer.valueOf(recomendacionFamiliaresAmigos));
				informacion.setAgenciaViajes(Integer.valueOf(agenciaViajes));
				informacion.setCartelesViaPublica(Integer.valueOf(cartelesViaPublica));
				informacion.setTelevision(Integer.valueOf(television));
				informacion.setRadio(Integer.valueOf(radio));
				informacion.setDiarioRevistas(Integer.valueOf(diarioRevistas));
				informacion.setExperienciaViajesAnteriores(Integer.valueOf(experienciasViajesAnteriores));
				informacion.setInternet(Integer.valueOf(internetInfo));
				informacion.setComentariosRedesSociales(Integer.valueOf(comentariosRrSs));
				informacion.setGuiasTuristicasFolletos(Integer.valueOf(guiasTuristicasFolletos));
				informacion.setOtra(Integer.valueOf(otra));
				//
				usoWeb.setTransporteBuscarInfo(Integer.valueOf(transporteBuscarInfo));
				usoWeb.setTransportePagar(Integer.valueOf(transportePagar));
				usoWeb.setAlojamientoBuscarInfo(Integer.valueOf(alojamientoBuscarInfo));
				usoWeb.setAlojamientoReservar(Integer.valueOf(alojamientoReservar));
				usoWeb.setAlojamientoPagar(Integer.valueOf(alojamientoPagar));
				usoWeb.setActividadesEsparcimientoBuscarInfo(Integer.valueOf(actividadesEsparcimientoBuscarInfo));
				usoWeb.setActividadesEsparcimientoReservar(Integer.valueOf(actividadesEsparcimientoReservar));
				usoWeb.setActividadesEsparcimientoPagar(Integer.valueOf(actividadesEsparcimientoPagar));
				//
				actividades.setActividadesProfesionales(Integer.valueOf(actividadesProfesionales));
				actividades.setVisitasAmigosFamiliares(Integer.valueOf(visitasAmigosFamiliares));
				actividades.setAprendizajeLenguaOriginariaCostumbresTradiciones(Integer.valueOf(aprendizajeLenguaOriginariaCostumbresTradiciones));
				actividades.setAsistenciaEventosCulturales(Integer.valueOf(asistenciaEventosCulturales));
				actividades.setAsistenciaFestivalesFerias(Integer.valueOf(asistenciaFestivalesFerias));
				actividades.setAsistenciaEventosDeportivos(Integer.valueOf(asistenciaEventosDeportivos));
				actividades.setVisitaPaisajesAtractivosNaturales(Integer.valueOf(visitaPaisajesAtractivosNaturales));
				actividades.setVisitaMuseos(Integer.valueOf(visitaMuseos));
				actividades.setVisitaMonumentosHistoricos(Integer.valueOf(visitaMonumentosHistoricos));
				
				actividades.setVisitaSitiosPatrimonialesIglesias(Integer.valueOf(visitaSitiosPatrimonialesIglesias));
				actividades.setVisitaArquitecturaModerna(Integer.valueOf(visitaArquitecturaModerna));
				actividades.setVisitaPequeñosPueblosCiudades(Integer.valueOf(visitaPequeñosPueblosCiudades));
				actividades.setVisitaParqueAtracciones(Integer.valueOf(visitaParqueAtracciones));
				actividades.setVisitaMercadosEspaciosPopulares(Integer.valueOf(visitaMercadosEspaciosPopulares));
				actividades.setVisitaCasinoSalaJuegos(Integer.valueOf(visitaCasinoSalaJuegos));
				actividades.setIrCompras(Integer.valueOf(irCompras));
				actividades.setVisitaTermas(Integer.valueOf(visitaTermas));
				actividades.setVisitaRestaurantGastronomiaChilena(Integer.valueOf(visitaRestaurantGastronomiaChilena));
				actividades.setVisitaRestaurantGastronomiaIntegerernacional(Integer.valueOf(visitaRestaurantGastronomiaIntegerernacional));
				actividades.setVisitaBaresPubLugaresNocturnos(Integer.valueOf(visitaBaresPubLugaresNocturnos));
				actividades.setPeregrinacionesEventosReligiosos(Integer.valueOf(peregrinacionesEventosReligiosos));
				actividades.setVisitaPlayas(Integer.valueOf(visitaPlayas));
				actividades.setCazaPescaCamping(Integer.valueOf(cazaPescaCamping));
				actividades.setExcursionismoSenderismo(Integer.valueOf(excursionismoSenderismo));
				actividades.setEsquiar(Integer.valueOf(esquiar));
				actividades.setVisitaParquesNacionales(Integer.valueOf(visitaParquesNacionales));
				actividades.setActividadesDeportivasAireLibre(Integer.valueOf(actividadesDeportivasAireLibre));
				actividades.setVisitaObservatoriosAstronomicos(Integer.valueOf(visitaObservatoriosAstronomicos));
				actividades.setVisitaSitiosArqueologicos(Integer.valueOf(visitaSitiosArqueologicos));
				actividades.setVisitaViñas(Integer.valueOf(visitaViñas));
				actividades.setOtro(Integer.valueOf(otro));
				//
				internet.setChileturCopec(Integer.valueOf(chileturCopec));
				internet.setBooking(Integer.valueOf(booking));
				internet.setLonelyPlanet(Integer.valueOf(lonelyPlanet));
				internet.setChilestuyo(Integer.valueOf(chilestuyo));
				internet.setTripAdvisor(Integer.valueOf(tripAdvisor));
				internet.setAirbnb(Integer.valueOf(airbnb));
				internet.setDespegar(Integer.valueOf(despegar));
				internet.setOtra(Integer.valueOf(otraInternet));
				//
				razones.setRazonCercania(Integer.valueOf(razonCercania));
				razones.setRazonCasaDeptoEnLugar(Integer.valueOf(razonCasaDeptoEnLugar));
				razones.setRazonVivenFamiliares(Integer.valueOf(razonVivenFamiliares));
				razones.setRazonRecomendacionFamiliaAmigos(Integer.valueOf(razonRecomendacionFamiliaAmigos));
				razones.setRazonCalidadAlojamiento(Integer.valueOf(razonCalidadAlojamiento));
				razones.setRazonRelacionPrecioCalidad(Integer.valueOf(razonRelacionPrecioCalidad));
				razones.setRazonPaisajesNaturales(Integer.valueOf(razonPaisajesNaturales));
				razones.setRazonConocerOtrasPersonas(Integer.valueOf(razonConocerOtrasPersonas));
				razones.setRazonRealizarDiversasActividades(Integer.valueOf(razonRealizarDiversasActividades));
				razones.setRazonVariedadCosasHacerVer(Integer.valueOf(razonVariedadCosasHacerVer));
				razones.setRazonActividadesCulturales(Integer.valueOf(razonActividadesCulturales));
				razones.setRazonVidaNocturna(Integer.valueOf(razonVidaNocturna));
				razones.setRazonLugarSeguroTranquilo(Integer.valueOf(razonLugarSeguroTranquilo));
				razones.setRazonGastronomia(Integer.valueOf(razonGastronomia));
				razones.setRazonProfesionales(Integer.valueOf(razonProfesionales));
				razones.setRazonOtra(Integer.valueOf(razonOtra));
				//
				servpt.setTransporteADestino(Integer.valueOf(transporteADestino));
				servpt.setAlojamiento(Integer.valueOf(alojamiento));
				servpt.setComidas(Integer.valueOf(comidas));
				servpt.setTransporteEnDestinoArriendoAuto(Integer.valueOf(transporteEnDestinoArriendoAuto));
				servpt.setExcursionesEspectaculo(Integer.valueOf(excursionesEspectaculo));
				servpt.setSeguroSaludAsistenciaViajero(Integer.valueOf(seguroSaludAsistenciaViajero));
				//
				motivos.setCercania(Integer.valueOf(razonCercania));
				motivos.setCasaDeptoEnLugar(Integer.valueOf(razonCasaDeptoEnLugar));
				motivos.setVivenFamiliaresAmigos(Integer.valueOf(razonVivenFamiliares));
				motivos.setRecomendacionFamiliaresAmigos(Integer.valueOf(razonRecomendacionFamiliaAmigos));
				motivos.setCalidadAlojamiento(Integer.valueOf(razonCalidadAlojamiento));
				motivos.setBuenaRelacionPrecioCalidad(Integer.valueOf(razonRelacionPrecioCalidad));
				motivos.setPaisajesNaturales(Integer.valueOf(razonPaisajesNaturales));
				motivos.setConocerOtrasPersonas(Integer.valueOf(razonConocerOtrasPersonas));
				motivos.setRealizarDiversasActividades(Integer.valueOf(razonRealizarDiversasActividades));
				motivos.setVariedadDeCosasQueVerHacer(Integer.valueOf(razonVariedadCosasHacerVer));
				motivos.setActividadesCulturales(Integer.valueOf(razonActividadesCulturales));
				motivos.setVidaNocturna(Integer.valueOf(razonVidaNocturna));
				motivos.setLugarSeguroTranquilo(Integer.valueOf(razonLugarSeguroTranquilo));
				motivos.setGastronomia(Integer.valueOf(razonGastronomia));
				motivos.setRazonesProfesionales(Integer.valueOf(razonProfesionales));
				motivos.setOtra(Integer.valueOf(razonOtra));
				//
				medioTransporte.setAutoFamiliaresAmigos(Integer.valueOf(autoFamiliaresAmigos));
				medioTransporte.setEmbarcacionPrivada(Integer.valueOf(embarcacionPrivada));
				medioTransporte.setMoto(Integer.valueOf(moto));
				medioTransporte.setBicicleta(Integer.valueOf(bicicleta));
				medioTransporte.setBusInterurbano(Integer.valueOf(busInterurbano));
				medioTransporte.setTren(Integer.valueOf(tren));
				medioTransporte.setAvion(Integer.valueOf(avion));
				medioTransporte.setBarcoBoteLancha(Integer.valueOf(barcoBoteLancha));
				medioTransporte.setCrucero(Integer.valueOf(crucero));
				medioTransporte.setOtro(Integer.valueOf(otroMedioTransporte));
				//
				evaluacion.setPrecioCalidadAlojamientoTuristico(Integer.valueOf(precioCalidadAlojamientoTuristico));
				evaluacion.setCantidadVariedadAlojamientoTuristico(Integer.valueOf(cantidadVariedadAlojamientoTuristico));
				evaluacion.setPrecioCalidadServiciosAlimentacion(Integer.valueOf(precioCalidadServiciosAlimentacion));
				evaluacion.setCantidadVariedadServiciosAlimentacion(Integer.valueOf(cantidadVariedadServiciosAlimentacion));
				evaluacion.setLimpiezaGeneralEspacioPublico(Integer.valueOf(limpiezaGeneralEspacioPublico));
				evaluacion.setConectividadWiFiEspaciosPublicos(Integer.valueOf(conectividadWiFiEspaciosPublicos));
				evaluacion.setSeñalizacionAtractivosServiciosTuristicos(Integer.valueOf(señalizacionAtractivosServiciosTuristicos));
				evaluacion.setDisponibilidadCentrosInfoTuristica(Integer.valueOf(disponibilidadCentrosInfoTuristica));
				evaluacion.setSeguridadActividadesTurismoAventura(Integer.valueOf(seguridadActividadesTurismoAventura));
				evaluacion.setContaminacionAmbiental(Integer.valueOf(contaminacionAmbiental));
				evaluacion.setDisponibilidadBancosCajeros(Integer.valueOf(disponibilidadBancosCajeros));
				evaluacion.setDisponibilidadTransporteLocal(Integer.valueOf(disponibilidadTransporteLocal));
				evaluacion.setSatisfaccionGeneral(Integer.valueOf(satisfaccionGeneral));
				evaluacion.setPrecioCalidadActividadTuristicas(Integer.valueOf(precioCalidadActividadTuristicas));
				//
				retorno = true;
			
			}
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en l�nea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en l�nea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
	}
	
	
	private boolean insertarRegistro(long cuenta) throws Exception {
		boolean retorno = false;
		try {
			flag = baseService.agregar(base, actividades, evaluacion, gastos, informacion, internet,
					razones, medioTransporte, miembros, motivos, noches, servpt, usoWeb);
			if (flag == 1) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> OK en l�nea " + cuenta, "");
				retorno = true;
			} else {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta +" (" + flag + ")", "");
				bitacora.add("202;" + cuenta + ";global;0;[Global] Error en l�nea " + cuenta);
			}
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en l�nea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en l�nea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
	}
	
	
	private void insertarCarga(long cuenta, int estado, int error) throws Exception {
		cargaService = new CargaServiceImpl(con);
		try {
			// Inicializar carga
			carga = new Carga();
			carga.setId(Integer.valueOf(idCarga));
			//carga.setNombre(Util.getFechaHoraCarga());
			carga.setNombre(Util.getFechaHoraProceso());
			carga.setFile_name(nombreArchivo);
			carga.setFile_size(new File(archivoCarga).length());
			carga.setFile_type("csv");
			carga.setId_tipo_carga(2);
			carga.setId_base(8); //TIP
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.tip.col")));
			carga.setAnio_periodo_inicio(Integer.parseInt(formatoFecha[0]));
			carga.setMes_periodo_inicio(1);
			carga.setAnio_periodo_fin(Integer.parseInt(formatoFecha[1]));
			carga.setMes_periodo_fin(12);
			carga.setFecha(Util.getFechaCarga());
			carga.setHora(Util.getHoraCarga());
			carga.setDescripcion("");
			carga.setId_tipo_activo(2);
			
			if (cuenta == 0) {
				flag = cargaService.agregar(carga);
			} else {
				flag = cargaService.actualizar(carga);
			}
			if (flag == 1) {
				Util.setLog(archivoLog, "I", "Insertar Carga --> OK", "");
			} else {
				Util.setLog(archivoLog, "I", "Insertar Carga --> ERROR (" + flag + ")", "");
			}
		
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Insertar Carga --> ERROR", ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Carga --> ERROR", ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	
	private void insertarCargaBitacora(String[] data) throws Exception {
		cargaBitacoraService = new CargaBitacoraServiceImpl(con);
		try {
			// Inicializar carga bitacora
			cargaBitacora = new CargaBitacora();
			cargaBitacora.setNombre(data[0]);
			cargaBitacora.setLinea(Integer.valueOf(data[1]));
			cargaBitacora.setCampo(data[2]);
			cargaBitacora.setValor(data[3]);
			cargaBitacora.setCodigo_carga(Integer.valueOf(idCarga));
			cargaBitacora.setDescripcion(data[4]);
			cargaBitacora.setId_tipo_activo(2);
			
			flag = cargaBitacoraService.agregar(cargaBitacora);
			if (flag == 1) {
				Util.setLog(archivoLog, "I", "Insertar Carga Bitacora --> OK", "");
			} else {
				Util.setLog(archivoLog, "I", "Insertar Carga Bitacora --> ERROR (" + flag + ")", "");
			}
		
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Insertar Carga Bitacora --> ERROR", ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Carga Bitacora --> ERROR", ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
}
