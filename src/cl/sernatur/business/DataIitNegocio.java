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

import cl.sernatur.beans.BaseIit;
import cl.sernatur.service.BaseIitService;
import cl.sernatur.service.impl.BaseIitServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataIitNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseIit base;
	private BaseIitService baseService;
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
		baseService = new BaseIitServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.iit.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String comuna = data[1].trim();
			String poblacion_flotante = data[3].trim().replace(".","").replace(",",".");
			String venta_total_uf = data[4].trim().replace(".","").replace(",",".");
			String ventas_sii = data[5].trim().replace(".","").replace(",",".");
			String trabajadores_sii = data[6].trim().replace(".","").replace(",",".");
			String renta_neta_trabajadores_sii = data[7].trim().replace(".","").replace(",",".");
			String pernocta_vivienda_particular = data[8].trim().replace(".","").replace(",",".");
			String unidades_alojamiento = data[9].trim().replace(",",".");
			String plazas_emat = data[10].trim().replace(".","").replace(",",".");
			String llegadas_emat = data[11].trim().replace(".","").replace(",",".");
			String pernocta_emat = data[12].trim().replace(".","").replace(",",".");
			String alojamiento_registro = data[13].trim().replace(",",".");
			String turismo_aventura_registro = data[14].trim().replace(",",".");
			String empresas_sii = data[15].trim().replace(".","").replace(",",".");
			String snaspe = data[16].trim().replace(".","").replace(",",".");
			String atractivos = data[17].trim().replace(",",".");
			String indice_iit = data[18].trim().replace(".","").replace(",",".");
			
			if (poblacion_flotante.equals("")) poblacion_flotante = "0";
			if (venta_total_uf.equals("")) venta_total_uf = "0";
			if (ventas_sii.equals("")) ventas_sii = "0";
			if (trabajadores_sii.equals("")) trabajadores_sii = "0";
			if (renta_neta_trabajadores_sii.equals("")) renta_neta_trabajadores_sii = "0";
			if (pernocta_vivienda_particular.equals("")) pernocta_vivienda_particular = "0";
			if (unidades_alojamiento.equals("")) unidades_alojamiento = "0";
			if (plazas_emat.equals("")) plazas_emat = "0";
			if (llegadas_emat.equals("")) llegadas_emat = "0";
			if (pernocta_emat.equals("")) pernocta_emat = "0";
			if (alojamiento_registro.equals("")) alojamiento_registro = "0";
			if (turismo_aventura_registro.equals("")) turismo_aventura_registro = "0";
			if (empresas_sii.equals("")) empresas_sii = "0";
			if (snaspe.equals("")) snaspe = "0";
			if (atractivos.equals("")) atractivos = "0";
			if (indice_iit.equals("")) indice_iit = "0";
			
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
			
			// Validar tipo de dato población flotante
			} else if (!Util.esDecimal(poblacion_flotante)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de población flotante");
				bitacora.add("213;" + cuenta + ";poblacion_flotante;" + poblacion_flotante + ";Tipo invalido de población flotante, línea " + cuenta);

			// Validar tipo de dato población flotante
			} else if (!Util.esDecimal(poblacion_flotante)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de población flotante");
				bitacora.add("213;" + cuenta + ";poblacion_flotante;" + poblacion_flotante + ";Tipo invalido de población flotante, línea " + cuenta);
			
			// Validar tipo de dato venta total uf
			} else if (!Util.esDecimal(venta_total_uf)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de venta total uf");
				bitacora.add("213;" + cuenta + ";venta_total_uf;" + venta_total_uf + ";Tipo invalido de venta total uf, línea " + cuenta);
			
			// Validar tipo de dato ventas sii
			} else if (!Util.esDecimal(ventas_sii)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de ventas sii");
				bitacora.add("213;" + cuenta + ";ventas_sii;" + ventas_sii + ";Tipo invalido de ventas sii, línea " + cuenta);
			
			// Validar tipo de dato trabajadores sii
			} else if (!Util.esDecimal(trabajadores_sii)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de trabajadores sii");
				bitacora.add("213;" + cuenta + ";trabajadores_sii;" + trabajadores_sii + ";Tipo invalido de trabajadores sii, línea " + cuenta);
			
			// Validar tipo de dato renta neta trabajadores sii
			} else if (!Util.esDecimal(renta_neta_trabajadores_sii)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de renta neta trabajadores sii");
				bitacora.add("213;" + cuenta + ";renta_neta_trabajadores_sii;" + renta_neta_trabajadores_sii + ";Tipo invalido de renta neta trabajadores sii, línea " + cuenta);
			
			// Validar tipo de dato pernocta vivienda particular
			} else if (!Util.esDecimal(pernocta_vivienda_particular)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de pernocta vivienda particular");
				bitacora.add("213;" + cuenta + ";pernocta_vivienda_particular;" + pernocta_vivienda_particular + ";Tipo invalido de pernocta vivienda particular, línea " + cuenta);
			
			// Validar tipo de dato unidades alojamiento
			} else if (!Util.esNumero(unidades_alojamiento)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de unidades alojamiento");
				bitacora.add("213;" + cuenta + ";unidades_alojamiento;" + unidades_alojamiento + ";Tipo invalido de unidades alojamiento, línea " + cuenta);
			
			// Validar tipo de dato plazas emat
			} else if (!Util.esDecimal(plazas_emat)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de plazas emat");
				bitacora.add("213;" + cuenta + ";plazas_emat;" + plazas_emat + ";Tipo invalido de plazas emat, línea " + cuenta);
			
			// Validar tipo de dato llegadas emat
			} else if (!Util.esDecimal(llegadas_emat)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de llegadas emat");
				bitacora.add("213;" + cuenta + ";llegadas_emat;" + llegadas_emat + ";Tipo invalido de llegadas emat, línea " + cuenta);
			
			// Validar tipo de dato pernocta emat
			} else if (!Util.esDecimal(pernocta_emat)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de pernocta emat");
				bitacora.add("213;" + cuenta + ";pernocta_emat;" + pernocta_emat + ";Tipo invalido de pernocta emat, línea " + cuenta);
			
			// Validar tipo de dato alojamiento registro
			} else if (!Util.esNumero(alojamiento_registro)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de alojamiento registro");
				bitacora.add("213;" + cuenta + ";alojamiento_registro;" + alojamiento_registro + ";Tipo invalido de alojamiento registro, línea " + cuenta);
			
			// Validar tipo de dato turismo aventura registro
			} else if (!Util.esNumero(turismo_aventura_registro)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de turismo aventura registro");
				bitacora.add("213;" + cuenta + ";turismo_aventura_registro;" + turismo_aventura_registro + ";Tipo invalido de turismo aventura registro, línea " + cuenta);
			
			// Validar tipo de dato empresas sii
			} else if (!Util.esDecimal(empresas_sii)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de empresas sii");
				bitacora.add("213;" + cuenta + ";empresas_sii;" + empresas_sii + ";Tipo invalido de empresas sii, línea " + cuenta);
			
			// Validar tipo de dato snaspe
			} else if (!Util.esDecimal(snaspe)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de snaspe");
				bitacora.add("213;" + cuenta + ";snaspe;" + snaspe + ";Tipo invalido de snaspe, línea " + cuenta);
			
			// Validar tipo de dato atractivos
			} else if (!Util.esNumero(atractivos)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de atractivos");
				bitacora.add("213;" + cuenta + ";atractivos;" + atractivos + ";Tipo invalido de atractivos, línea " + cuenta);
			
			// Validar tipo de indice iit
			} else if (!Util.esDecimal(indice_iit)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de indice iit");
				bitacora.add("213;" + cuenta + ";indice_iit;" + indice_iit + ";Tipo invalido de indice iit, línea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseIit();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_comuna(Integer.parseInt(comuna));
				base.setPoblacion_flotante(Double.parseDouble(poblacion_flotante));
				base.setVenta_total_uf(Double.parseDouble(venta_total_uf));
				base.setVentas_sii(Double.parseDouble(ventas_sii));
				base.setTrabajadores_sii(Double.parseDouble(trabajadores_sii));
				base.setRenta_neta_trabajadores_sii(Double.parseDouble(renta_neta_trabajadores_sii));
				base.setPernocta_vivienda_particular(Double.parseDouble(pernocta_vivienda_particular));
				base.setUnidades_alojamiento(Integer.parseInt(unidades_alojamiento));
				base.setPlazas_emat(Double.parseDouble(plazas_emat));
				base.setLlegadas_emat(Double.parseDouble(llegadas_emat));
				base.setPernocta_emat(Double.parseDouble(pernocta_emat));
				base.setAlojamiento_registro(Integer.parseInt(alojamiento_registro));
				base.setTurismo_aventura_registro(Integer.parseInt(turismo_aventura_registro));
				base.setEmpresas_sii(Double.parseDouble(empresas_sii));
				base.setSnaspe(Double.parseDouble(snaspe));
				base.setAtractivos(Integer.parseInt(atractivos));
				base.setIndice_iit(Double.parseDouble(indice_iit));
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
			carga.setId_base(21); //IIT
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.iit.col")));
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
