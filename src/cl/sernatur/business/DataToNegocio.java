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

import cl.sernatur.beans.BaseTo;
import cl.sernatur.service.BaseToService;
import cl.sernatur.service.impl.BaseToServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataToNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseTo base;
	private BaseToService baseService;
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
		baseService = new BaseToServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.to.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String feriado = data[1].trim();
			String destino = data[3].trim();
			String tasa = data[5].trim().replace(".","").replace(",",".");
			
			if (tasa.equals("")) tasa = "0";
			
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
				
			// Validar tipo de dato feriado
			} else if (!Util.esNumero(feriado)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de feriado");
				bitacora.add("213;" + cuenta + ";feriado;" + feriado + ";Tipo invalido de feriado, l�nea " + cuenta);
				
			// Validar tipo de dato destino
			} else if (!Util.esNumero(destino)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de destino");
				bitacora.add("213;" + cuenta + ";destino;" + destino + ";Tipo invalido de destino, l�nea " + cuenta);
			
			// Validar tipo de dato tasa
			} else if (!Util.esDecimal(tasa)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en l�nea " + cuenta, "tipo invalido de tasa");
				bitacora.add("213;" + cuenta + ";tasa;" + tasa + ";Tipo invalido de tasa, l�nea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseTo();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_feriado(Integer.parseInt(feriado));
				base.setId_destino(Integer.parseInt(destino));
				base.setTasa(Double.parseDouble(tasa));
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
			carga.setId_base(18); //TO
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.to.col")));
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
