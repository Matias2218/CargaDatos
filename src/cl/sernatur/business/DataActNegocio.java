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

import cl.sernatur.beans.BaseAct;
import cl.sernatur.service.BaseActService;
import cl.sernatur.service.impl.BaseActServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataActNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseAct base;
	private BaseActService baseService;
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
		baseService = new BaseActServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.act.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String comuna = data[1].trim();
			String actividadEconomica = data[3].trim();
			//String actividad = data[5].trim();
			String numeroEmpresa = data[7].trim().replace(",",".");
			String ventaUf = data[8].trim().replace(".", "").replace(",",".");
			String numeroTrabajador = data[9].trim().replace(",",".");
			String numeroTrabajadorMujer = data[10].trim().replace(",",".");
			String numeroTrabajadorHombre = data[11].trim().replace(",",".");
			String rentaNeta = data[12].trim().replace(".", "").replace(",",".");
			String rentaNetaMujer = data[13].trim().replace(".", "").replace(",",".");
			String rentaNetaHombre = data[14].trim().replace(".", "").replace(",",".");
			String trabajadorPonderado = data[15].trim().replace(".", "").replace(",",".");
			String trabajadorPonderadoMujer = data[16].trim().replace(".", "").replace(",",".");
			String trabajadorPonderadoHombre = data[17].trim().replace(".", "").replace(",",".");
			
			if (comuna.equals("") || comuna.contentEquals("Sin Informacion")) comuna = "9999";
			if (ventaUf.equals("") || ventaUf.equals("*")) ventaUf = "0";
			if (numeroTrabajador.equals("") || numeroTrabajador.equals("*")) numeroTrabajador = "0";
			if (numeroTrabajadorMujer.equals("") || numeroTrabajadorMujer.equals("*")) numeroTrabajadorMujer = "0";
			if (numeroTrabajadorHombre.equals("") || numeroTrabajadorHombre.equals("*")) numeroTrabajadorHombre = "0";
			if (rentaNeta.equals("") || rentaNeta.equals("*")) rentaNeta = "0";
			if (rentaNetaMujer.equals("") || rentaNetaMujer.equals("*")) rentaNetaMujer = "0";
			if (rentaNetaHombre.equals("") || rentaNetaHombre.equals("*")) rentaNetaHombre = "0";
			if (trabajadorPonderado.equals("") || trabajadorPonderado.equals("*")) trabajadorPonderado = "0";
			if (trabajadorPonderadoMujer.equals("") || trabajadorPonderadoMujer.equals("*")) trabajadorPonderadoMujer = "0";
			if (trabajadorPonderadoHombre.equals("") || trabajadorPonderadoHombre.equals("*")) trabajadorPonderadoHombre = "0";
			
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
			
			// Validar tipo de dato comuna
			} else if (!Util.esNumero(comuna)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de comuna");
				bitacora.add("213;" + cuenta + ";comuna;" + comuna + ";Tipo invalido de comuna, línea " + cuenta);
			
			// Validar tipo de dato actividad económica
			} else if (!Util.esNumero(actividadEconomica)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de actividad económica");
				bitacora.add("213;" + cuenta + ";actividad económica;" + actividadEconomica + ";Tipo invalido de actividad económica, línea " + cuenta);
			
			// Validar tipo de dato número empresas
			} else if (!Util.esNumero(numeroEmpresa)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de número empresa");
				bitacora.add("213;" + cuenta + ";número empresa;" + numeroEmpresa + ";Tipo invalido de número empresa, línea " + cuenta);
			
			// Validar tipo de dato venta UF
			} else if (!Util.esDecimal(ventaUf)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de venta UF");
				bitacora.add("213;" + cuenta + ";venta UF;" + ventaUf + ";Tipo invalido de venta UF, línea " + cuenta);
			
			// Validar tipo de dato número trabajador
			} else if (!Util.esNumero(numeroTrabajador)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de número trabajador");
				bitacora.add("213;" + cuenta + ";número trabajador;" + numeroTrabajador + ";Tipo invalido de número trabajador, línea " + cuenta);
			
			// Validar tipo de dato número trabajador mujer
			} else if (!Util.esNumero(numeroTrabajadorMujer)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de número trabajador mujer");
				bitacora.add("213;" + cuenta + ";número trabajador mujer;" + numeroTrabajadorMujer + ";Tipo invalido de número trabajador mujer, línea " + cuenta);
			
			// Validar tipo de dato número trabajador hombre
			} else if (!Util.esNumero(numeroTrabajadorHombre)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de número trabajador hombre");
				bitacora.add("213;" + cuenta + ";número trabajador hombre;" + numeroTrabajadorHombre + ";Tipo invalido de número trabajador hombre, línea " + cuenta);
			
			// Validar tipo de dato renta neta
			} else if (!Util.esDecimal(rentaNeta)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de renta neta");
				bitacora.add("213;" + cuenta + ";renta neta;" + rentaNeta + ";Tipo invalido de renta neta, línea " + cuenta);
			
			// Validar tipo de dato renta neta mujer
			} else if (!Util.esDecimal(rentaNetaMujer)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de renta neta mujer");
				bitacora.add("213;" + cuenta + ";renta neta mujer;" + rentaNetaMujer + ";Tipo invalido de renta neta mujer, línea " + cuenta);

			// Validar tipo de dato renta neta hombre
			} else if (!Util.esDecimal(rentaNetaHombre)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de renta neta hombre");
				bitacora.add("213;" + cuenta + ";renta neta hombre;" + rentaNetaHombre + ";Tipo invalido de renta neta hombre, línea " + cuenta);
			
			// Validar tipo de dato trabajador ponderado
			} else if (!Util.esDecimal(trabajadorPonderado)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de trabajador ponderado");
				bitacora.add("213;" + cuenta + ";trabajador ponderado;" + trabajadorPonderado + ";Tipo invalido de trabajador ponderado, línea " + cuenta);
			
			// Validar tipo de dato trabajador ponderado mujer
			} else if (!Util.esDecimal(trabajadorPonderadoMujer)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de trabajador ponderado mujer");
				bitacora.add("213;" + cuenta + ";trabajador ponderado mujer;" + trabajadorPonderadoMujer + ";Tipo invalido de trabajador ponderado mujer, línea " + cuenta);
			
			// Validar tipo de dato trabajador ponderado hombre
			} else if (!Util.esDecimal(trabajadorPonderadoHombre)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de trabajador ponderado hombre");
				bitacora.add("213;" + cuenta + ";trabajador ponderado hombre;" + trabajadorPonderadoHombre + ";Tipo invalido de trabajador ponderado hombre, línea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseAct();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_comuna(Integer.parseInt(comuna));
				base.setId_acteco(Integer.parseInt(actividadEconomica));
				base.setNumero_empresas(Integer.parseInt(numeroEmpresa));
				base.setVentas_uf(Double.parseDouble(ventaUf));
				base.setNumero_trabajadores(Integer.parseInt(numeroTrabajador));
				base.setTrabajadores_femenino_informado(Integer.parseInt(numeroTrabajadorMujer));
				base.setTrabajadores_masculino_informado(Integer.parseInt(numeroTrabajadorHombre));
				base.setRenta_neta(Double.parseDouble(rentaNeta));
				base.setRenta_neta_femenino(Double.parseDouble(rentaNetaMujer));
				base.setRenta_neta_masculino(Double.parseDouble(rentaNetaHombre));
				base.setTrabajadores_ponderados(Double.parseDouble(trabajadorPonderado));
				base.setTrabajadores_ponderados_femenino(Double.parseDouble(trabajadorPonderadoMujer));
				base.setTrabajadores_ponderados_masculinos(Double.parseDouble(trabajadorPonderadoHombre));
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				base.setCodigo_carga(Integer.parseInt(idCarga));
				retorno = true;
			
			}
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en línea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
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
			carga.setId_base(2); //ACT
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.act.col")));
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
