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

import cl.sernatur.beans.BaseTe;
import cl.sernatur.beans.BaseTeGrupo;
import cl.sernatur.beans.BaseTeFinancia;
import cl.sernatur.service.BaseTeService;
import cl.sernatur.service.impl.BaseTeServiceImpl;

import cl.sernatur.beans.Carga;
import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.beans.CargaBitacora;
import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataTeNegocio {
	
	private Properties prop;
	private DataSource dataSource;
	private String nombreArchivo;
	private String archivoLog;
	private String archivoCarga;
	private String idCarga;
	
	private String formatoFecha[];
	private Connection con;
	private BaseTe base;
	private BaseTeService baseService;
	private Carga carga;
	private CargaService cargaService;
	private CargaBitacora cargaBitacora;
	private CargaBitacoraService cargaBitacoraService;
	private int flag;
	private BufferedReader fileReader;
	List<String> bitacora = new ArrayList<>();
	
	private Integer numero_mujeres = 0;
	private Integer numero_hombres = 0;
	
	
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
		baseService = new BaseTeServiceImpl(con);
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
			int numeroCampos = Integer.parseInt(prop.getProperty("carga.te.col"));
			int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));
			
			String anio = data[0].trim();
			String mes = data[1].trim();
			String trimestre = data[2].trim();
			String fecha = data[3].trim();
			String codigo_encuesta = data[4].trim();
			String codigo_encuestador = data[5].trim();
			String nacionalidad = data[7].trim();
			String pais_destino = data[15].trim();
			String motivo = data[17].trim();
			String total_pt = data[19].trim().replace(".","").replace(",",".");
			String noches = data[20].trim().replace(",",".");
			String linea_aerea_salida = data[23].trim();
			String linea_aerea_regreso = data[25].trim();
			String vuelo_numero = data[27].trim();
			String llegadas = data[32].trim().replace(".","").replace(",",".");
			String egreso = data[33].trim().replace(".","").replace(",",".");
			String total_sin_pasajes = data[35].trim().replace(".","").replace(",",".");
			String total_con_pasajes = data[36].trim().replace(".","").replace(",",".");
			String fe = data[37].trim().replace(".","").replace(",",".");
			String fe_m = data[38].trim().replace(".","").replace(",",".");
			String fe_mm = data[39].trim().replace(".","").replace(",",".");
			String fe_f = data[40].trim().replace(".","").replace(",",".");
			String fe_ff = data[41].trim().replace(".","").replace(",",".");
			String fee = data[42].trim().replace(".","").replace(",",".");
			
			if (total_pt.equals("")) total_pt = "0";
			if (noches.equals("")) noches = "0";
			if (llegadas.equals("")) llegadas = "0";
			if (egreso.equals("")) egreso = "0";
			if (total_sin_pasajes.equals("")) total_sin_pasajes = "0";
			if (total_con_pasajes.equals("")) total_con_pasajes = "0";
			if (fe.equals("")) fe = "0";
			if (fe_m.equals("")) fe_m = "0";
			if (fe_mm.equals("")) fe_mm = "0";
			if (fe_f.equals("")) fe_f = "0";
			if (fe_ff.equals("")) fe_ff = "0";
			if (fee.equals("")) fee = "0";
			
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
			
			// Validar tipo de dato trimestre
			} else if (!Util.esNumero(trimestre)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de trimestre");
				bitacora.add("213;" + cuenta + ";trimestre;" + trimestre + ";Tipo invalido de trimestre, línea " + cuenta);

			// Validar tipo de dato nacionalidad
			} else if (!Util.esNumero(nacionalidad)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de nacionalidad");
				bitacora.add("213;" + cuenta + ";nacionalidad;" + nacionalidad + ";Tipo invalido de nacionalidad, línea " + cuenta);
				
			// Validar tipo de dato país destino
			} else if (!Util.esNumero(pais_destino)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de país destino");
				bitacora.add("213;" + cuenta + ";pais_destino;" + pais_destino + ";Tipo invalido de país destino, línea " + cuenta);
				
			// Validar tipo de dato motivo
			} else if (!Util.esNumero(motivo)) {
				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de motivo");
				bitacora.add("213;" + cuenta + ";motivo;" + motivo + ";Tipo invalido de motivo, línea " + cuenta);
			
			} else {
				
				// Setear datos
				base = new BaseTe();
				base.setId_anio(Integer.parseInt(anio));
				base.setId_mes(Integer.parseInt(mes));
				base.setId_mes_trimestre(Integer.parseInt(trimestre));
				base.setFecha(fecha);
				base.setCodigo_id(codigo_encuesta);
				base.setCodigo_encuestador(codigo_encuestador);
				base.setId_nacionalidad(Integer.parseInt(nacionalidad));
				base.setId_pais_destino(Integer.parseInt(pais_destino));
				base.setId_te_motivo(Integer.parseInt(motivo));
				base.setTotal_pt(Double.parseDouble(total_pt));
				base.setNumero_noches(Integer.parseInt(noches));
				base.setCodigo_linea_aerea_salida(linea_aerea_salida);
				base.setCodigo_linea_aerea_regreso(linea_aerea_regreso);
				base.setVuelo_numero(Integer.parseInt(vuelo_numero));
				base.setLlegadas(Double.parseDouble(llegadas));
				base.setEgreso(Double.parseDouble(egreso));
				base.setTotal_sin_pasajes(Double.parseDouble(total_sin_pasajes));
				base.setTotal_con_pasajes(Double.parseDouble(total_con_pasajes));
				base.setFe(Double.parseDouble(fe));
				base.setFe_m(Double.parseDouble(fe_m));
				base.setFe_mm(Double.parseDouble(fe_mm));
				base.setFe_f(Double.parseDouble(fe_f));
				base.setFe_ff(Double.parseDouble(fe_ff));
				base.setFee(Double.parseDouble(fee));
				base.setDescripcion(prop.getProperty("carga.descripcion"));
				base.setId_tipo_activo(Integer.parseInt(prop.getProperty("carga.estado")));
				base.setCodigo_carga(Integer.parseInt(idCarga));
				
				base.setLista_grupo(listaBaseGrupo(data));
				base.setNumero_mujeres(numero_mujeres);
				base.setNumero_hombres(numero_hombres);
				base.setNumero_grupo(numero_mujeres + numero_hombres);
				
				base.setLista_financia(listaBaseFinancia(data));
				
				//Faltan campos de entrada del archivo que no concuerdan con el modelo de datos --> Revisar con Leo
				
				retorno = true;
			
			}
		} catch (Exception ex) {
			Util.setLog(archivoLog, "E", "Insertar Registros --> ERROR en línea " + cuenta, ex.getMessage());
			bitacora.add("202;" + cuenta + ";global;0;[Global] Error en línea " + cuenta);
			ex.printStackTrace();
		}
		return retorno;
	}
	
	
	private List<BaseTeGrupo> listaBaseGrupo(String[] data) throws Exception {
		List<BaseTeGrupo> listaBaseGrupo = new ArrayList<BaseTeGrupo>();
		
		for (int i = 0; i < 7; i++) {
			if (!(data[i+43].trim().equals("") || data[i+43].trim().equals("0")) && Util.esNumero(data[i+43].trim())) {
				BaseTeGrupo baseGrupo = new BaseTeGrupo();
				baseGrupo.setId_te_tramo(i+1);
				baseGrupo.setId_genero(1);
				numero_mujeres += Integer.parseInt(data[i+43].trim());
				listaBaseGrupo.add(baseGrupo);
			}
			if (!(data[i+50].trim().equals("") || data[i+50].trim().equals("0")) && Util.esNumero(data[i+50].trim())) {
				BaseTeGrupo baseGrupo = new BaseTeGrupo();
				baseGrupo.setId_te_tramo(i+1);
				baseGrupo.setId_genero(2);
				numero_hombres += Integer.parseInt(data[i+50].trim());
				listaBaseGrupo.add(baseGrupo);
			}
		}
		
		return listaBaseGrupo;
	}
	
	
	private List<BaseTeFinancia> listaBaseFinancia(String[] data) throws Exception {
		List<BaseTeFinancia> listaBaseFinancia = new ArrayList<BaseTeFinancia>();
		
		for (int i = 0; i < 5; i++) {
			if (data[i+85].trim().equals("1")) {
				BaseTeFinancia baseFinancia = new BaseTeFinancia();
				baseFinancia.setId_te_financia(i+1);
				listaBaseFinancia.add(baseFinancia);
			}
		}
		
		return listaBaseFinancia;
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
			carga.setId_base(12); //TE
			carga.setId_estado_carga(estado);
			carga.setId_error_carga(error);
			carga.setNum_rows(cuenta);
			carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.te.col")));
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
