package cl.sernatur.main;

import javax.sql.DataSource;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import cl.advise.util.Util;


public class CargaDatos {
	private static DataSource dataSource;
	private static Properties prop;
	private static Util util = new Util();
	public static void main(String[] args) {
		
		// Setear parámetros
		inicio();
		
		// Ejecutar proceso
		ejecutar();
		
		// Cerrar conexión
		cerrar();

	}
	
	
	private static void inicio() {
		// Rescatar parámetros
		System.out.println(Util.getOut("getParametros"));
		prop = util.getParametros();
		if ( prop == null ) {
			return;
		}
		
		// Rescatar conexión de base de datos
		System.out.println(Util.getOut("getDataSource"));
		dataSource = Util.getDataSource(prop.getProperty("sql.driver"), prop.getProperty("sql.url"), prop.getProperty("sql.user"), prop.getProperty("sql.pass"));
		if ( dataSource == null ) {
			return;
		}
	}
	
	
	private static void ejecutar() {
		String idProceso = Util.getFechaHoraProceso();
		System.out.println(Util.getOut("cargaDatos: Proceso: ") + idProceso);
		
		try {
			int correlativo = 10;
			String mensaje = "\r\n";
			
			// Recorrer cargas
			String lista = prop.getProperty("file.list");
			for (String carga: lista.split(";")) {
				String fileCarga = carga.toUpperCase();
				System.out.println(Util.getOut("cargaDatos: " + fileCarga));
				
				String pathEntrada = prop.getProperty("file.path") + fileCarga + prop.getProperty("file.entrada");
				String pathRespaldo = prop.getProperty("file.path") + fileCarga + prop.getProperty("file.respaldo");
				String pathLog = prop.getProperty("file.path") + fileCarga + prop.getProperty("file.log");
				
				// Recorrer archivos
				final File folder = new File(pathEntrada);
				for (final File fileEntry : folder.listFiles()) {
					String idProcesaData = String.valueOf(((new Date().getTime()/100000L)%Integer.MAX_VALUE))+String.valueOf(++correlativo);
					System.out.println(Util.getOut("cargaDatos: " + carga + " - Carga: " + idProcesaData));
					
					String nombreArchivo = fileEntry.getName();
					String nombreRespaldo = nombreArchivo + "." + idProcesaData;
					String nombreLog = carga + "." + prop.getProperty("file.log.ext") + "." + idProcesaData;
					String archivoEntrada = pathEntrada + nombreArchivo;
					String archivoRespaldo = pathRespaldo + nombreRespaldo;
					String archivoLog = pathLog + nombreLog;
					
					try {
						// Respaldar archivo
						if (Util.respaldarArchivo(archivoEntrada, archivoRespaldo)) {
							System.out.println(Util.getOut("cargaDatos: " + carga + " - respaldarArchivo: " + nombreArchivo + " --> OK"));
							
							// Ejecutar clase procesaData
							try {
								Class<?> clase = Class.forName("cl.sernatur.business.Data"+carga+"Negocio");
								Method metodo = clase.getDeclaredMethod("procesaData", Properties.class, DataSource.class, String.class, String.class, String.class, String.class);
								String[] retorno = (String[])metodo.invoke(clase.newInstance(), prop, dataSource, nombreArchivo, archivoLog, archivoRespaldo, idProcesaData);
								if (Boolean.parseBoolean(retorno[0])) {
									System.out.println(Util.getOut("cargaDatos: " + carga + " - procesaData --> OK"));
								} else {
									System.out.println(Util.getOut("cargaDatos: " + carga + " - procesaData --> ERROR"));
								}
								mensaje += "\r\n" + carga + " - Carga: " + idProcesaData + " (" + retorno[1] + ")";
							} catch (Exception ex) {
								ex.printStackTrace();
								mensaje += "\r\n" + carga + " - Carga: " + idProcesaData + " (Carga de registros con error --> Problemas al procesar archivo)";
							}
						} else {
							System.out.println(Util.getOut("cargaDatos: " + carga + " - respaldarArchivo: " + nombreArchivo + " --> ERROR"));
							mensaje += "\r\n" + carga + " - Carga: " + idProcesaData + " (Carga de registros con error --> Problemas al procesar archivo)";
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						mensaje += "\r\n" + carga + " - Carga: " + idProcesaData + " (Carga de registros con error --> Problemas al procesar archivo)";
					}
				}
				
			}
			
			// Refrescar vistas
			if (Util.actualizarVistas(dataSource, prop.getProperty("sql.view"))) {
				System.out.println(Util.getOut("cargaDatos: actualizarVistas --> OK"));
			} else {
				System.out.println(Util.getOut("cargaDatos: actualizarVistas --> ERROR"));
			}
			
			// Enviar Mail
//			String asunto = prop.getProperty("mail.asunto") + " - " + idProceso;
//			String cuerpo = prop.getProperty("mail.cuerpo") + " - " + idProceso + mensaje;
//			if (Util.enviarMail(prop.getProperty("mail.ip"), prop.getProperty("mail.port"), prop.getProperty("mail.de"), prop.getProperty("mail.para"), asunto, cuerpo, prop.getProperty("mail.user"), prop.getProperty("mail.pass"))) {
//				System.out.println(Util.getOut("cargaDatos: enviarMail --> OK"));
//			} else {
//				System.out.println(Util.getOut("cargaDatos: enviarMail --> ERROR"));
//			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	private static void cerrar() {
		if (dataSource!=null) {
			try {
				dataSource.getConnection().close();
				System.out.println(Util.getOut("cargaDatos: cerrarConexion --> OK"));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
