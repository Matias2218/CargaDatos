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

import cl.sernatur.beans.BaseTrt;
import cl.sernatur.service.BaseTrtService;
import cl.sernatur.service.impl.BaseTrtServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataTrtNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseTrt base;
	private BaseTrtService baseService;
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
		baseService = new BaseTrtServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.trt.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String mes = data[1].trim();
			String pais_reside = data[2].trim();
			String pais_origen = data[4].trim();
			String motivo = data[6].trim();
			String frontera = data[8].trim();
			String noches = data[10].trim().replace(".","").replace(",",".");
			String dias = data[11].trim().replace(".","").replace(",",".");
			String gpdi = data[12].trim().replace(".","").replace(",",".");
			String gti = data[13].trim().replace(".","").replace(",",".");
			String divisas = data[14].trim().replace(".","").replace(",",".");
			String fe = data[15].trim().replace(".","").replace(",",".");
			
			if (noches.equals("")) noches = "0";
			if (dias.equals("")) dias = "0";
			if (gpdi.equals("")) gpdi = "0";
			if (gti.equals("")) gti = "0";
			if (divisas.equals("")) divisas = "0";
			if (fe.equals("")) fe = "0";
			
			// Validar n�mero de campos
			if (data.length != numeroCampos) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "N�mero de campos no corresponde");
				bitacora.add("201;" + cuenta + ";global;0;[Global] El N�mero de campos no corresponde a lo establecido (" + numeroCampos + ")");
			
			// Validar formato de a�o
			} else if (anio.length() != 4) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "Formato invalido de a�o");
				bitacora.add("210;" + cuenta + ";a�o;" + anio + ";Formato invalido de a�o, l�nea " + cuenta);
			
			// Validar rango de a�o
			} else if (Integer.parseInt(anio) < anioMinimo) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "Rango invalido de a�o");
				bitacora.add("211;" + cuenta + ";a�o;" + anio + ";Rango invalido de a�o, l�nea " + cuenta);
			
			// Validar tipo de dato a�o
			} else if (!Util.esNumero(anio)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "Tipo invalido de a�o");
				bitacora.add("213;" + cuenta + ";a�o;" + anio + ";Tipo invalido de a�o, l�nea " + cuenta);
			
			// Validar tipo de dato mes
			} else if (!Util.esNumero(mes)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de mes");
				bitacora.add("213;" + cuenta + ";mes;" + mes + ";Tipo invalido de mes, l�nea " + cuenta);
			
			// Validar tipo de dato pa�s de residencia
			} else if (!Util.esNumero(pais_reside)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de pa�s de residencia");
				bitacora.add("213;" + cuenta + ";pais_reside;" + pais_reside + ";Tipo invalido de pa�s de residencia, l�nea " + cuenta);

			// Validar tipo de dato pa�s de origen
			} else if (!Util.esNumero(pais_origen)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de pa�s de origen");
				bitacora.add("213;" + cuenta + ";pais_origen;" + pais_origen + ";Tipo invalido de pa�s de origen, l�nea " + cuenta);

			// Validar tipo de dato motivo principal
			} else if (!Util.esNumero(motivo)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de motivo principal");
				bitacora.add("213;" + cuenta + ";motivo;" + motivo + ";Tipo invalido de motivo principal, l�nea " + cuenta);
			
			// Validar tipo de dato frontera
			} else if (!Util.esNumero(frontera)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de frontera");
				bitacora.add("213;" + cuenta + ";frontera;" + frontera + ";Tipo invalido de frontera, l�nea " + cuenta);

			// Validar tipo de dato valor total noches
			} else if (!Util.esDecimal(noches)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor total noches");
				bitacora.add("213;" + cuenta + ";noches;" + noches + ";Tipo invalido de valor total noches, l�nea " + cuenta);
			
			// Validar tipo de dato valor d�as turista
			} else if (!Util.esDecimal(dias)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor d�as turista");
				bitacora.add("213;" + cuenta + ";dias;" + dias + ";Tipo invalido de valor d�as turista, l�nea " + cuenta);

			// Validar tipo de dato valor gpdi
			} else if (!Util.esDecimal(gpdi)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor gpdi");
				bitacora.add("213;" + cuenta + ";gpdi;" + gpdi + ";Tipo invalido de valor gpdi, l�nea " + cuenta);
			
			// Validar tipo de dato valor gti
			} else if (!Util.esDecimal(gti)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor gti");
				bitacora.add("213;" + cuenta + ";gti;" + gti + ";Tipo invalido de valor gti, l�nea " + cuenta);
			
			// Validar tipo de dato valor divisas
			} else if (!Util.esDecimal(divisas)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor divisas");
				bitacora.add("213;" + cuenta + ";divisas;" + divisas + ";Tipo invalido de valor divisas, l�nea " + cuenta);
			
			// Validar tipo de dato factor expansi�n
			} else if (!Util.esDecimal(fe)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de valor factor expansi�n");
				bitacora.add("213;" + cuenta + ";fe;" + fe + ";Tipo invalido de valor factor expansi�n, l�nea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseTrt();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_mes(Integer.parseInt(mes));
				base.setId_pais_reside(Integer.parseInt(pais_reside));
				base.setId_pais_origen(Integer.parseInt(pais_origen));
				base.setId_tr_motivo1(Integer.parseInt(motivo));
				base.setId_tr_frontera1(Integer.parseInt(frontera));
				base.setNoches(Double.parseDouble(noches));
				base.setDias_turista(Double.parseDouble(dias));
				base.setGpdi(Double.parseDouble(gpdi));
				base.setGti(Double.parseDouble(gti));
				base.setDivisas(Double.parseDouble(divisas));
				base.setFactor_expansion(Double.parseDouble(fe));
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				base.setCodigo_carga(Integer.parseInt(idCarga));
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
			flag = baseService.agregar(base);
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
			carga.setId_base(14); //TRT
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.trt.col")));
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
