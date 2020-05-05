package cl.sernatur.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import cl.advise.util.Util;

import cl.sernatur.beans.BaseEat;
import cl.sernatur.beans.BaseEatTotal;
import cl.sernatur.beans.BaseEatNacional;
import cl.sernatur.beans.BaseEatExtranjero;
import cl.sernatur.service.BaseEatService;
import cl.sernatur.service.impl.BaseEatServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataEatNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseEat base;
	private BaseEatService baseService;
	private Carga carga;
	private CargaService cargaService;
	private CargaBitacora cargaBitacora;
	private CargaBitacoraService cargaBitacoraService;
	private int flag;
	private BufferedReader fileReader;
	List<String> bitacora = new ArrayList<>();
	
	
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
				
				// Crear conexión
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
				
				// Insertar carga bitácora
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
		formatoFecha = Util.validarFormatoAnioMes(archivoCarga);
		if (!(formatoFecha[0].equals("0000") || formatoFecha[1].equals("00") || formatoFecha[2].equals("0000") || formatoFecha[3].equals("00"))) {
			String desde = formatoFecha[0] + formatoFecha[1];
			String hasta = formatoFecha[2] + formatoFecha[3];
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
		baseService = new BaseEatServiceImpl(con);
		try {
			String desde = formatoFecha[0] + formatoFecha[1];
			String hasta = formatoFecha[2] + formatoFecha[3];
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.eat.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String mes = data[1].trim();
			String comuna = data[2].trim();
			String claseAlojamiento = data[4].trim();
			String uId = data[6].trim();
			String dias = data[7].trim();
			String temporada = data[8].trim();
			String destino = data[10].trim();
			String totalUnidadAlojamiento = data[16].trim().replace(".","").replace(",",".");
			String totalPlaza = data[21].trim().replace(".","").replace(",",".");
			String totalLlegadaNacional = data[54].trim().replace(".","").replace(",",".");
			String totalPernoctaNacional = data[55].trim().replace(".","").replace(",",".");
			String totalLlegadaExtranjero = data[88].trim().replace(".","").replace(",",".");
			String totalPernoctaExtranjero = data[89].trim().replace(".","").replace(",",".");
			String totalLlegadas = data[90].trim().replace(".","").replace(",",".");
			String totalPernocta = data[91].trim().replace(".","").replace(",",".");
			String unidadAlojamientoOcupado = data[92].trim().replace(".","").replace(",",".");
			String plazaAdicionalInstalada = data[93].trim().replace(".","").replace(",",".");
			String ingresoNetoAlojamiento = data[94].trim().replace(".","").replace(",",".");
			String ingresoNetoOtro = data[95].trim().replace(".","").replace(",",".");
			String ingresoNetoOperacion = data[96].trim().replace(".","").replace(",",".");
			String habitacionDiaDisponible = data[97].trim().replace(".","").replace(",",".");
			String plazaDiaDisponible = data[98].trim().replace(".","").replace(",",".");
			String factorExpansion = data[99].trim().replace(".","").replace(",",".");
			
			if (uId.equals("") || uId.equals("NA")) uId = "0";			
			if (totalUnidadAlojamiento.equals("")) totalUnidadAlojamiento = "0";
			if (totalPlaza.equals("")) totalPlaza = "0";
			if (totalLlegadaNacional.equals("")) totalLlegadaNacional = "0";
			if (totalPernoctaNacional.equals("")) totalPernoctaNacional = "0";
			if (totalLlegadaExtranjero.equals("")) totalLlegadaExtranjero = "0";
			if (totalPernoctaExtranjero.equals("")) totalPernoctaExtranjero = "0";
			if (totalLlegadas.equals("")) totalLlegadas = "0";
			if (totalPernocta.equals("")) totalPernocta = "0";
			if (unidadAlojamientoOcupado.equals("")) unidadAlojamientoOcupado = "0";
			if (plazaAdicionalInstalada.equals("")) plazaAdicionalInstalada = "0";
			if (ingresoNetoAlojamiento.equals("")) ingresoNetoAlojamiento = "0";
			if (ingresoNetoOtro.equals("")) ingresoNetoOtro = "0";
			if (ingresoNetoOperacion.equals("")) ingresoNetoOperacion = "0";
			if (habitacionDiaDisponible.equals("")) habitacionDiaDisponible = "0";
			if (plazaDiaDisponible.equals("")) plazaDiaDisponible = "0";
			if (factorExpansion.equals("")) factorExpansion = "0";
			
			// Validar número de campos
			if (data.length != numeroCampos) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Número de campos no corresponde");
				bitacora.add("201;" + cuenta + ";global;0;[Global] El Número de campos no corresponde a lo establecido (" + numeroCampos + ")");
			
			// Validar formato de año
			} else if (anio.length() != 4) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Formato invalido de año");
				bitacora.add("210;" + cuenta + ";año;" + anio + ";Formato invalido de año, línea " + cuenta);
			
			// Validar rango de año
			} else if (Integer.parseInt(anio) < anioMinimo) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Rango invalido de año");
				bitacora.add("211;" + cuenta + ";año;" + anio + ";Rango invalido de año, línea " + cuenta);
			
			// Validar tipo de dato año
			} else if (!Util.esNumero(anio)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Tipo invalido de año");
				bitacora.add("213;" + cuenta + ";año;" + anio + ";Tipo invalido de año, línea " + cuenta);
				
			// Validar tipo de dato mes
			} else if (!Util.esNumero(mes)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de mes");
				bitacora.add("213;" + cuenta + ";mes;" + mes + ";Tipo invalido de mes, línea " + cuenta);
			
			// Validar tipo de dato comuna
			} else if (!Util.esNumero(comuna)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de comuna");
				bitacora.add("213;" + cuenta + ";comuna;" + comuna + ";Tipo invalido de comuna, línea " + cuenta);
			
			// Validar tipo de dato clase alojamiento
			} else if (!Util.esNumero(claseAlojamiento)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de clase alojamiento");
				bitacora.add("213;" + cuenta + ";clase alojamiento;" + claseAlojamiento + ";Tipo invalido de clase alojamiento, línea " + cuenta);
			
			// Validar tipo de dato UID
			} else if (!Util.esNumero(uId)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de UID");
				bitacora.add("213;" + cuenta + ";UID;" + uId + ";Tipo invalido de UID, línea " + cuenta);
			
			// Validar tipo de dato días funciona
			} else if (!Util.esNumero(dias)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de días funciona");
				bitacora.add("213;" + cuenta + ";días funciona;" + dias + ";Tipo invalido de días funciona, línea " + cuenta);
				
			// Validar tipo de dato temporada agregada
			} else if (!Util.esNumero(temporada)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de temporada agregada");
				bitacora.add("213;" + cuenta + ";temporada agregada;" + temporada + ";Tipo invalido de temporada agregada, línea " + cuenta);
				
			// Validar tipo de dato destino turístico
			} else if (!Util.esNumero(destino)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de destino turístico");
				bitacora.add("213;" + cuenta + ";destino turístico;" + destino + ";Tipo invalido de destino turístico, línea " + cuenta);
				
			} else {
				
				// Setear datos
				base = new BaseEat();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_mes(Integer.parseInt(mes));
				base.setUid(Integer.parseInt(uId));
				base.setDias_funciona(Integer.parseInt(dias));
				base.setId_eat_clase_alojamiento(Integer.parseInt(claseAlojamiento));
				base.setId_comuna(Integer.parseInt(comuna));
				base.setId_eat_temporada(Integer.parseInt(temporada));
				base.setId_eat_destino(Integer.parseInt(destino));
				base.setTotal_unidad_alojamiento(Double.parseDouble(totalUnidadAlojamiento));
				base.setTotal_plazas(Double.parseDouble(totalPlaza));
				base.setTotal_llegada_nacional(Double.parseDouble(totalLlegadaNacional));
				base.setTotal_pernocta_nacional(Double.parseDouble(totalPernoctaNacional));
				base.setTotal_llegada_extranjero(Double.parseDouble(totalLlegadaExtranjero));
				base.setTotal_pernocta_extranjero(Double.parseDouble(totalPernoctaExtranjero));
				base.setTotal_llegadas(Double.parseDouble(totalLlegadas));
				base.setTotal_pernocta(Double.parseDouble(totalPernocta));
				base.setUnidad_alojamiento_ocupada(Double.parseDouble(unidadAlojamientoOcupado));
				base.setPlaza_adicional_instalada(Double.parseDouble(plazaAdicionalInstalada));
				base.setIngreso_neto_alojamiento(Double.parseDouble(ingresoNetoAlojamiento));
				base.setIngreso_neto_otros(Double.parseDouble(ingresoNetoOtro));
				base.setTotal_ingreso_neto(Double.parseDouble(ingresoNetoOperacion));
				base.setHab_dia_disponible(Double.parseDouble(habitacionDiaDisponible));
				base.setPlaza_dia_disponible(Double.parseDouble(plazaDiaDisponible));
				base.setFactor_expansion(Double.parseDouble(factorExpansion));				
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				base.setCodigo_carga(Integer.parseInt(idCarga));
				
				base.setLista_total(listaBaseTotal(data));
				base.setLista_nacional(listaBaseNacional(data));
				base.setLista_extranjero(listaBaseExtranjero(data));
				
				retorno = true;
			
			}
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en línea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
	}
	
	
	private List<BaseEatTotal> listaBaseTotal(String[] data) throws Exception {
		List<BaseEatTotal> listaBaseTotal = new ArrayList<BaseEatTotal>();
		
		for (int i = 0; i < 4; i++) {
			BaseEatTotal baseTotal = new BaseEatTotal();
			baseTotal.setId_eat_tipo_alojamiento(i+1);
			baseTotal.setTotal_ta(Double.parseDouble(data[i+12].trim().replace(".","").replace(",",".")));
			baseTotal.setTotal_plazas(Double.parseDouble(data[i+17].trim().replace(".","").replace(",",".")));
			baseTotal.setDescripcion("");	
			baseTotal.setId_tipo_activo(2);
			listaBaseTotal.add(baseTotal);
		}
		
		return listaBaseTotal;
	}
	
	
	private List<BaseEatNacional> listaBaseNacional(String[] data) throws Exception {
		List<BaseEatNacional> listaBaseNacional = new ArrayList<BaseEatNacional>();
		
		int x = 0;
		for (int i = 0; i < 16; i++) {
			BaseEatNacional baseNacional = new BaseEatNacional();
			baseNacional.setId_region(i+1);
			baseNacional.setLlegadas(Double.parseDouble(data[x+22].trim().replace(".","").replace(",",".")));
			baseNacional.setPernocta(Double.parseDouble(data[x+23].trim().replace(".","").replace(",",".")));
			baseNacional.setDescripcion("");
			baseNacional.setId_tipo_activo(2);
			listaBaseNacional.add(baseNacional);
			x+=2;
		}
		
		return listaBaseNacional;
	}
	
	
	private List<BaseEatExtranjero> listaBaseExtranjero(String[] data) throws Exception {
		List<BaseEatExtranjero> listaBaseExtranjero = new ArrayList<BaseEatExtranjero>();
		
	   	int x = 0;
		for (int i = 0; i < 16; i++) {
			BaseEatExtranjero baseExtranjero = new BaseEatExtranjero();
			baseExtranjero.setId_region(i+1);
			baseExtranjero.setLlegadas(Double.parseDouble(data[x+56].trim().replace(".","").replace(",",".")));
			baseExtranjero.setPernocta(Double.parseDouble(data[x+57].trim().replace(".","").replace(",",".")));
			baseExtranjero.setDescripcion("");
		   	baseExtranjero.setId_tipo_activo(2);
			listaBaseExtranjero.add(baseExtranjero);
			x+=2;
		}
		
		return listaBaseExtranjero;
	}
	
	
	private boolean insertarRegistro(long cuenta) throws Exception {
		boolean retorno = false;
		try {
			flag = baseService.agregar(base);
			if (flag == 1) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> OK en línea " + cuenta, "");
				retorno = true;
			} else {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta +" (" + flag + ")", "");
				bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
			}
		} catch (SQLException ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en línea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
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
			carga.setId_base(3); //EAT
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.eat.col")));
			carga.setAnio_periodo_inicio(Integer.parseInt(formatoFecha[0]));
			carga.setMes_periodo_inicio(Integer.parseInt(formatoFecha[1]));
			carga.setAnio_periodo_fin(Integer.parseInt(formatoFecha[2]));
			carga.setMes_periodo_fin(Integer.parseInt(formatoFecha[3]));
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
