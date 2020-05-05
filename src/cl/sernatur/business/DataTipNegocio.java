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

import cl.sernatur.beans.BaseTip;
import cl.sernatur.beans.BaseTipGastoItem;
import cl.sernatur.service.BaseTipService;
import cl.sernatur.service.impl.BaseTipServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataTipNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseTip base;
	private BaseTipService baseService;
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
		baseService = new BaseTipServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.tip.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String codigo_encuesta = data[1].trim();
			String id_ti_origen = data[2].trim();
			String ronda = data[3].trim();				 
			String iteracion = data[4].trim();
			String fe_persona = data[5].trim().replace(".","").replace(",",".");
			String fe_hogar = data[6].trim().replace(".","").replace(",",".");
			String id_ti_tipo_viaje = data[7].trim();
			String id_svdf = data[9].trim();
			String noches_fuera = data[11].trim().replace(",",".");
			String fecha_inicio = data[12].trim();
			String personas_viajan = data[13].trim().replace(",",".");
			String numero_viajes = data[14].trim().replace(",",".");
			String numero_viajes_modificado = data[15].trim().replace(",",".");
			String id_feriado = data[16].trim();
			String gasto_promedio_svdf = data[18].trim().replace(".","").replace(",",".");
			String gasto_promedio_gpti = data[19].trim().replace(".","").replace(",",".");
			String gasto_promedio_gpdi = data[20].trim().replace(".","").replace(",",".");
			String gasto_total = data[21].trim().replace(".","").replace(",",".");
			String id_comuna_origen = data[22].trim();
			String id_ti_destino = data[24].trim();
			String id_ti_medio_transporte = data[26].trim();
			String id_ti_medio_transporte_otro = data[28].trim();
			String id_ti_alojamiento = data[30].trim();
			String id_ti_alojamiento_otro = data[32].trim();
			String id_ti_alojamiento_tipo = data[34].trim();
			String id_ti_motivo_viaje = data[36].trim();
			String id_ti_motivo_viaje_otro = data[38].trim();
			String id_nse = data[40].trim();
			String qcc_1 = data[65].trim().replace(".","").replace(",",".");
			String qcc_2 = data[66].trim().replace(".","").replace(",",".");
			
			if (fe_persona.equals("")) fe_persona = "0";
			if (fe_hogar.equals("")) fe_hogar = "0";
			if (noches_fuera.equals("")) noches_fuera = "0";
			if (personas_viajan.equals("")) personas_viajan = "0";
			if (numero_viajes.equals("")) numero_viajes = "0";
			if (numero_viajes_modificado.equals("")) numero_viajes_modificado = "0";
			if (gasto_promedio_svdf.equals("")) gasto_promedio_svdf = "0";
			if (gasto_promedio_gpti.equals("")) gasto_promedio_gpti = "0";
			if (gasto_promedio_gpdi.equals("")) gasto_promedio_gpdi = "0";
			if (gasto_total.equals("")) gasto_total = "0";
			if (qcc_1.equals("")) qcc_1 = "0";
			if (qcc_2.equals("")) qcc_2 = "0";
			
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
				
			// Validar tipo de dato código encuesta
			} else if (!Util.esNumero(codigo_encuesta)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de código encuesta");
				bitacora.add("213;" + cuenta + ";codigo_encuesta;" + codigo_encuesta + ";Tipo invalido de código encuesta, línea " + cuenta);
			
			// Validar tipo de dato bdd
			} else if (!Util.esNumero(id_ti_origen)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de BDD");
				bitacora.add("213;" + cuenta + ";id_ti_origen;" + id_ti_origen + ";Tipo invalido de BDD, línea " + cuenta);
			
			// Validar tipo de dato ronda
			} else if (!Util.esNumero(ronda)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de ronda");
				bitacora.add("213;" + cuenta + ";ronda;" + ronda + ";Tipo invalido de ronda, línea " + cuenta);
			
			// Validar tipo de dato iteración
			} else if (!Util.esNumero(iteracion)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de iteración");
				bitacora.add("213;" + cuenta + ";iteracion;" + iteracion + ";Tipo invalido de iteración, línea " + cuenta);
			
			// Validar tipo de dato tipo viaje
			} else if (!Util.esNumero(id_ti_tipo_viaje)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de tipo viaje");
				bitacora.add("213;" + cuenta + ";id_ti_tipo_viaje;" + id_ti_tipo_viaje + ";Tipo invalido de tipo viaje, línea " + cuenta);
				
			// Validar tipo de dato código svdf
			} else if (!Util.esNumero(id_svdf)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de código svdf");
				bitacora.add("213;" + cuenta + ";id_svdf;" + id_svdf + ";Tipo invalido de código svdf, línea " + cuenta);
				
			// Validar tipo de dato comuna origen
			} else if (!Util.esNumero(id_comuna_origen)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de comuna origen");
				bitacora.add("213;" + cuenta + ";id_comuna_origen;" + id_comuna_origen + ";Tipo invalido de comuna origen, línea " + cuenta);
			
			// Validar tipo de dato destino
			} else if (!Util.esNumero(id_ti_destino)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de destino");
				bitacora.add("213;" + cuenta + ";id_ti_destino;" + id_ti_destino + ";Tipo invalido de destino, línea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseTip();
				base.setId_anio(Integer.parseInt(anio));
				base.setCodigo_encuesta(Integer.parseInt(codigo_encuesta));
				base.setId_ti_origen(Integer.parseInt(id_ti_origen));
				base.setRonda(Integer.parseInt(ronda));
				base.setIteracion(Integer.parseInt(iteracion));
				base.setFe_persona(Double.parseDouble(fe_persona));
				base.setFe_hogar(Double.parseDouble(fe_hogar));
				base.setId_ti_tipo_viaje(Integer.parseInt(id_ti_tipo_viaje));
				base.setId_svdf(Integer.parseInt(id_svdf));
				base.setNoches_fuera(Integer.parseInt(noches_fuera));
				base.setPersonas_viajan(Integer.parseInt(personas_viajan));
				base.setGasto_total(Double.parseDouble(gasto_total));
				base.setGasto_promedio_svdf(Double.parseDouble(gasto_promedio_svdf));
				base.setGasto_promedio_total_individual(Double.parseDouble(gasto_promedio_gpti));
				base.setNumero_viajes(Integer.parseInt(numero_viajes));
				base.setNumero_viajes_modificado(Integer.parseInt(numero_viajes_modificado));
				base.setGasto_promedio_diario_individual(Double.parseDouble(gasto_promedio_gpdi));
				base.setId_feriado(Integer.parseInt(id_feriado));
				base.setFecha_inicio(fecha_inicio);
				base.setId_comuna_origen(Integer.parseInt(id_comuna_origen));
				base.setId_ti_destino(Integer.parseInt(id_ti_destino));
				base.setId_ti_medio_transporte(Integer.parseInt(id_ti_medio_transporte));
				base.setId_ti_medio_transporte_otro(Integer.parseInt(id_ti_medio_transporte_otro));
				base.setId_ti_alojamiento(Integer.parseInt(id_ti_alojamiento));
				base.setId_ti_alojamiento_otro(Integer.parseInt(id_ti_alojamiento_otro));
				base.setId_ti_alojamiento_tipo(Integer.parseInt(id_ti_alojamiento_tipo));
				base.setId_ti_motivo_viaje(Integer.parseInt(id_ti_motivo_viaje));
				base.setId_ti_motivo_viaje_otro(Integer.parseInt(id_ti_motivo_viaje_otro));
				base.setId_nse(Integer.parseInt(id_nse));
				base.setQcc_1(Integer.parseInt(qcc_1));
				base.setQcc_2(Double.parseDouble(qcc_2));
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				base.setCodigo_carga(Integer.parseInt(idCarga));
				
				base.setLista_gasto_item(listaBaseGastoItem(data));
				
				retorno = true;
			
			}
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en línea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
	}
	
	
	private List<BaseTipGastoItem> listaBaseGastoItem(String[] data) throws Exception {
		List<BaseTipGastoItem> listaBaseGastoItem = new ArrayList<BaseTipGastoItem>();
		
		for (int i = 0; i < 12; i++) {
			if (!(data[i+41].trim().equals("") || data[i+41].trim().equals("0"))) {
				BaseTipGastoItem baseGastoItem = new BaseTipGastoItem();
				baseGastoItem.setId_ti_item(i+1);
				baseGastoItem.setId_ti_gasto(1);
				baseGastoItem.setValor(Double.parseDouble(data[i+41].trim().replace(".","").replace(",",".")));
				baseGastoItem.setDescripcion("");
				baseGastoItem.setId_tipo_activo(2);
				listaBaseGastoItem.add(baseGastoItem);
			}
			if (!(data[i+53].trim().equals("") || data[i+53].trim().equals("0"))) {
				BaseTipGastoItem baseGastoItem = new BaseTipGastoItem();
				baseGastoItem.setId_ti_item(i+1);
				baseGastoItem.setId_ti_gasto(2);
				baseGastoItem.setValor(Double.parseDouble(data[i+53].trim().replace(".","").replace(",",".")));
				baseGastoItem.setDescripcion("");
				baseGastoItem.setId_tipo_activo(2);
				listaBaseGastoItem.add(baseGastoItem);
			}
		}
		
		return listaBaseGastoItem;
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
