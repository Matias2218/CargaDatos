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

import cl.sernatur.beans.*;
import org.springframework.jdbc.core.JdbcTemplate;

import cl.advise.util.Util;
import cl.sernatur.service.BaseTrService;
import cl.sernatur.service.impl.BaseTrServiceImpl;

import cl.sernatur.service.CargaService;
import cl.sernatur.service.impl.CargaServiceImpl;

import cl.sernatur.service.CargaBitacoraService;
import cl.sernatur.service.impl.CargaBitacoraServiceImpl;

public class DataTrNegocio {

    private Properties prop;
    private DataSource dataSource;
    private String nombreArchivo;
    private String archivoLog;
    private String archivoCarga;
    private String idCarga;

    private String formatoFecha[];
    private Connection con;
    private BaseTr base;
    private BaseTrService baseService;
    private Carga carga;
    private CargaService cargaService;
    private CargaBitacora cargaBitacora;
    private CargaBitacoraService cargaBitacoraService;
    private int flag;
    private BufferedReader fileReader;
    List<String> bitacora = new ArrayList<>();

    private Actividad actividad;
    private Alimentacion alimentacion;
    private Alojamiento alojamiento;
    private Ciudad ciudad;
    private Financia financia;
    private Gasto gasto;
    private MediosInformacion mediosInformacion;
    private Organiza organiza;
    private RazonViaje razonViaje;
    private ServicioPaqueteTuristico servicioPaqueteTuristico;
    private Grupo grupo;

    @SuppressWarnings("finally")
    public String[] procesaData(Properties prop, DataSource dataSource, String nombreArchivo, String archivoLog, String archivoCarga, String idCarga) {
        Util.setLog(archivoLog, "I", "Inicio", "");

        this.prop = prop;
        this.dataSource = dataSource;
        this.nombreArchivo = nombreArchivo;
        this.archivoLog = archivoLog;
        this.archivoCarga = archivoCarga;
        this.idCarga = idCarga;

        String[] retorno = new String[]{"false", ""};

        try {
            // Validar formato de archivo
            if (validarFormato()) {

                // Crear conexi�n
                crearConexion();

                // Eliminar registros
                eliminarRegistros();

                // Insertar carga
                insertarCarga(0, 1, 100);

                // Recorrer Archivo
                Util.setLog(archivoLog, "I", "Insertar Registros - Inicio", "");
                fileReader = new BufferedReader(new FileReader(archivoCarga));
                long cuenta = 0;
                long cuentaOk = 0;
                long cuentaError = 0;
                String line = "";
                while ((line = fileReader.readLine()) != null) {
                    if (cuenta > 1) { //Se reemplaza 0 por 1 ya que lee 2 veces las cabeceras (son muchos campos)
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
                    insertarCarga(cuentaOk, 2, 1);
                    bitacora.add("800;0;;;Ok Se cargaron " + String.valueOf(cuentaOk) + " registros");
                } else {
                    con.rollback();
                    retorno[1] = "Carga de registros con error --> OK: " + String.valueOf(cuentaOk) + " - ERROR: " + String.valueOf(cuentaError);
                    // Insertar carga error
                    insertarCarga(0, 1, 100);
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
            Util.setLog(archivoLog, "I", "Validar Formato Archivo --> OK (" + desde + " - " + hasta + ")", "");
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
        baseService = new BaseTrServiceImpl(con);
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
//			int numeroCampos = Integer.parseInt(prop.getProperty("carga.tr.col"));
            int anioMinimo = Integer.parseInt(prop.getProperty("carga.anio"));


            String anio = data[0].trim();
            String mes = data[1].trim().length() == 1 ? "0" + data[1].trim() : data[1].trim();
            ;
            String dia = data[2].trim().length() == 1 ? "0" + data[2].trim() : data[2].trim();
            Integer semestre, trimestre;
            Integer mesAux = Integer.valueOf(mes);
            Float gpdi;
            Integer id_gpdi;

            //Faltan campos --> Revisar con Leo

            // Validar n�mero de campos
//			if (data.length != numeroCampos) {
//				Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "N�mero de campos no corresponde");
//				bitacora.add("201;" + cuenta + ";global;0;[Global] El N�mero de campos no corresponde a lo establecido (" + numeroCampos + ")");
//			}
            // Validar formato de a�o
            if (anio.length() != 4) {
                Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Formato invalido de a�o");
                bitacora.add("210;" + cuenta + ";a�o;" + anio + ";Formato invalido de a�o, línea " + cuenta);

                // Validar rango de a�o
            } else if (Integer.parseInt(anio) < anioMinimo) {
                Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Rango invalido de a�o");
                bitacora.add("211;" + cuenta + ";a�o;" + anio + ";Rango invalido de a�o, línea " + cuenta);

                // Validar tipo de dato a�o
            } else if (!Util.esNumero(anio)) {
                Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "Tipo invalido de a�o");
                bitacora.add("213;" + cuenta + ";a�o;" + anio + ";Tipo invalido de a�o, línea " + cuenta);

                // Validar tipo de dato mes
            } else if (!Util.esNumero(mes)) {
                Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta, "tipo invalido de mes");
                bitacora.add("213;" + cuenta + ";mes;" + mes + ";Tipo invalido de mes, línea " + cuenta);

                //FALTAN VALIDACIONES (?)

            } else {
                //Setear primeros datos
                if (mesAux < 7) {
                    semestre = 1;
                    if (mesAux < 4) {
                        trimestre = 1;
                    } else {
                        trimestre = 2;
                    }
                } else {
                    semestre = 2;
                    if (mesAux < 10) {
                        trimestre = 3;
                    } else {
                        trimestre = 4;
                    }
                }

                gpdi = Float.valueOf(data[37].toString().replace(",", "."));
                if (gpdi < 20) {
                    id_gpdi = 1;
                } else if (20 <= gpdi && gpdi < 50) {
                    id_gpdi = 2;
                } else if (50 <= gpdi && gpdi < 100) {
                    id_gpdi = 3;
                } else if (100 <= gpdi && gpdi < 200) {
                    id_gpdi = 4;
                } else {
                    id_gpdi = 5;
                }

                // Setear datos actividad_r
                actividad = new Actividad();
                actividad.setActividadesDeTurismoCultural(Integer.parseInt(data[122].toString()) == 1 ? 1 : 0);
                actividad.setActividadesProfesionalesCongresos(Integer.parseInt(data[123].toString()) == 1 ? 2 : 0);
                actividad.setVisitasAmigosFamiliares(Integer.parseInt(data[124].toString()) == 1 ? 3 : 0);
                actividad.setAprendizajeIdiomaOtrosEstudios(Integer.parseInt(data[125].toString()) == 1 ? 4 : 0);
                actividad.setAsistenciaEventosDeportivos(Integer.parseInt(data[126].toString()) == 1 ? 5 : 0);
                actividad.setOtrosEstudios(Integer.parseInt(data[127].toString()) == 1 ? 6 : 0);
                actividad.setVisitarParquesAtraccionesZooActEsparcimiento(Integer.parseInt(data[128].toString()) == 1 ? 7 : 0);
                actividad.setIrDeCompras(Integer.parseInt(data[129].toString()) == 1 ? 8 : 0);
                actividad.setAguasTermales(Integer.parseInt(data[130].toString()) == 1 ? 9 : 0);
                actividad.setComerRestaurantGastronomiaTipica(Integer.parseInt(data[131].toString()) == 1 ? 10 : 0);
                actividad.setParticiparVidaNocturna(Integer.parseInt(data[132].toString()) == 1 ? 11 : 0);
                actividad.setActividadesReligiosasPeregrinaciones(Integer.parseInt(data[133].toString()) == 1 ? 12 : 0);
                actividad.setActividadesEnTierra(Integer.parseInt(data[134].toString()) == 1 ? 13 : 0);
                actividad.setEsquiarOtrasActividadesNieve(Integer.parseInt(data[135].toString()) == 1 ? 14 : 0);
                actividad.setActividadesEnAire(Integer.parseInt(data[136].toString()) == 1 ? 15 : 0);
                actividad.setActividadesEnAgua(Integer.parseInt(data[137].toString()) == 1 ? 16 : 0);
                actividad.setVisitarParqueNacionales(Integer.parseInt(data[138].toString()) == 1 ? 17 : 0);
                actividad.setVisitarObservatoriosAstronomicos(Integer.parseInt(data[139].toString()) == 1 ? 18 : 0);
                actividad.setVisitarViñedo(Integer.parseInt(data[140].toString()) == 1 ? 19 : 0);
                actividad.setDescansoOcio(Integer.parseInt(data[141].toString()) == 1 ? 20 : 0);
                actividad.setActividadPlaya(Integer.parseInt(data[142].toString()) == 1 ? 21 : 0);
                actividad.setOtro(Integer.parseInt(data[144].toString()) == 1 ? 22 : 0);
                actividad.setVisitarCasinosSalasJuegos(Integer.parseInt(data[143].toString()) == 1 ? 23 : 0);

                // Setear datos alimentacion_r
                alimentacion = new Alimentacion();
                alimentacion.setRestaurant(Integer.parseInt(data[59].toString()) == 1 ? 1 : 0);
                alimentacion.setComidaRapida(Integer.parseInt(data[60].toString()) == 1 ? 2 : 0);
                alimentacion.setHotelAlojamiento(Integer.parseInt(data[61].toString()) == 1 ? 3 : 0);
                alimentacion.setCompraMercaderia(Integer.parseInt(data[62].toString()) == 1 ? 4 : 0);
                alimentacion.setCasaFamiliaresAmigos(Integer.parseInt(data[63].toString()) == 1 ? 5 : 0);
                alimentacion.setInvitacion(Integer.parseInt(data[64].toString()) == 1 ? 6 : 0);
                alimentacion.setOtro(Integer.parseInt(data[65].toString()) == 1 ? 7 : 0);
                //Setear grupo y alimentacion

                grupo = new Grupo();
                grupo.setSexoP1(Integer.parseInt(data[92]));
                grupo.setEdadP1(Integer.parseInt(data[93]));
                grupo.setParentescoP1(Integer.parseInt(data[94]));
                grupo.setSexoP2(Integer.parseInt(data[95]));
                grupo.setEdadP2(Integer.parseInt(data[96]));
                grupo.setParentescoP2(Integer.parseInt(data[97]));
                grupo.setSexoP3(Integer.parseInt(data[98]));
                grupo.setEdadP3(Integer.parseInt(data[99]));
                grupo.setParentescoP3(Integer.parseInt(data[100]));
                grupo.setSexoP4(Integer.parseInt(data[101]));
                grupo.setEdadP4(Integer.parseInt(data[102]));
                grupo.setParentescoP4(Integer.parseInt(data[103]));
                grupo.setSexoP5(Integer.parseInt(data[104]));
                grupo.setEdadP5(Integer.parseInt(data[105]));
                grupo.setParentescoP5(Integer.parseInt(data[106]));
                grupo.setSexoP6(Integer.parseInt(data[107]));
                grupo.setEdadP6(Integer.parseInt(data[108]));
                grupo.setParentescoP6(Integer.parseInt(data[109]));
                grupo.setSexoP7(Integer.parseInt(data[110]));
                grupo.setEdadP7(Integer.parseInt(data[111]));
                grupo.setParentescoP7(Integer.parseInt(data[112]));
                grupo.setSexoP8(Integer.parseInt(data[113]));
                grupo.setEdadP8(Integer.parseInt(data[114]));
                grupo.setParentescoP8(Integer.parseInt(data[115]));
                grupo.setSexoP9(Integer.parseInt(data[116]));
                grupo.setEdadP9(Integer.parseInt(data[117]));
                grupo.setParentescoP9(Integer.parseInt(data[118]));
                grupo.setSexoP10(Integer.parseInt(data[119]));
                grupo.setEdadP10(Integer.parseInt(data[120]));
                grupo.setParentescoP10(Integer.parseInt(data[121]));


                // Setear datos aloja_r
                alojamiento = new Alojamiento();
                alojamiento.setHotel(Integer.parseInt(data[47].toString()) == 1 ? 1 : 0);
                alojamiento.setCabaña(Integer.parseInt(data[48].toString()) == 1 ? 2 : 0);
                alojamiento.setHostal(Integer.parseInt(data[49].toString()) == 1 ? 3 : 0);
                alojamiento.setCamping(Integer.parseInt(data[50].toString()) == 1 ? 4 : 0);
                alojamiento.setRefugio(Integer.parseInt(data[51].toString()) == 1 ? 5 : 0);
                alojamiento.setLodge(Integer.parseInt(data[52].toString()) == 1 ? 6 : 0);
                alojamiento.setResort(Integer.parseInt(data[53].toString()) == 1 ? 7 : 0);
                alojamiento.setDepartamento(Integer.parseInt(data[54].toString()) == 1 ? 8 : 0);
                alojamiento.setFamilia(Integer.parseInt(data[55].toString()) == 1 ? 9 : 0);
                alojamiento.setAirbnb(Integer.parseInt(data[56].toString()) == 1 ? 10 : 0);
                alojamiento.setCrucero(Integer.parseInt(data[57].toString()) == 1 ? 11 : 0);
                alojamiento.setOtro(Integer.parseInt(data[58].toString()) == 1 ? 12 : 0);

                // Setear datos tr_ciudad_r
                ciudad = new Ciudad();
                ciudad.setLocalidad(data[12].toString());
                ciudad.setNoches(Integer.valueOf(data[17].toString()));

                // Setear datos tr_financia_tr
                financia = new Financia();
                financia.setUdMismo(Integer.valueOf(data[170].toString()) == 1 ? 1 : 0);
                financia.setEmpresaInstitucionExterna(Integer.valueOf(data[171].toString()) == 1 ? 2 : 0);
                financia.setFamiliaDesdeExtranjero(Integer.valueOf(data[172].toString()) == 1 ? 3 : 0);
                financia.setInvitacionChile(Integer.valueOf(data[173].toString()) == 1 ? 4 : 0);
                financia.setOtro(Integer.valueOf(data[174].toString()) == 1 ? 5 : 0);

//				// Setear datps tr_gasto_r
                gasto = new Gasto();
                gasto.setHotelesSimilares(Float.valueOf(data[77].toString().replace(",", ".")));
                gasto.setCasaDeptoArrendado(Float.valueOf(data[78].toString().replace(",", ".")));
                gasto.setRestaurantSimilares(Float.valueOf(data[79].toString().replace(",", ".")));
                gasto.setCompraAlimentos(Float.valueOf(data[80].toString().replace(",", ".")));
                gasto.setTransporteInternoAereo(Float.valueOf(data[81].toString().replace(",", ".")));
                gasto.setTransporteInternoTerrestre(Float.valueOf(data[82].toString().replace(",", ".")));
                gasto.setTransporteInternoMaritimo(Float.valueOf(data[83].toString().replace(",", ".")));
                gasto.setCompras(Float.valueOf(data[84].toString().replace(",", ".")));
                gasto.setAgenciasViajeChile(Float.valueOf(data[85].toString().replace(",", ".")));
                gasto.setArriendoAutoSinChofer(Float.valueOf(data[86].toString().replace(",", ".")));
                gasto.setActividadesRecreativasDeportivas(Float.valueOf(data[87].toString().replace(",", ".")));
                gasto.setActividadesCulturales(Float.valueOf(data[88].toString().replace(",", ".")));
                gasto.setServicioReservasNaturales(Float.valueOf(data[89].toString().replace(",", ".")));
                gasto.setBencinaPeajeEstacionamientos(Float.valueOf(data[90].toString().replace(",", ".")));
                gasto.setOtros(Float.valueOf(data[91].toString().replace(",", ".")));


                // Setear datos tr_medios_r
                mediosInformacion = new MediosInformacion();
                mediosInformacion.setConsejosRecomendaciones(Integer.valueOf(data[145].toString()) == 1 ? 1 : 0);
                mediosInformacion.setInformacionInternet(Integer.valueOf(data[146].toString()) == 1 ? 2 : 0);
                mediosInformacion.setRedesSociales(Integer.valueOf(data[147].toString()) == 1 ? 3 : 0);
                mediosInformacion.setAgenciaViajeTourOperador(Integer.valueOf(data[148].toString()) == 1 ? 4 : 0);
                mediosInformacion.setGuiasTuristicasImpresas(Integer.valueOf(data[149].toString()) == 1 ? 5 : 0);
                mediosInformacion.setGuiasTuristicasOnline(Integer.valueOf(data[150].toString()) == 1 ? 6 : 0);
                mediosInformacion.setFolletosOficiasTurismoEmbajada(Integer.valueOf(data[151].toString()) == 1 ? 7 : 0);
                mediosInformacion.setOrganizaEmpresa(Integer.valueOf(data[152].toString()) == 1 ? 8 : 0);
                mediosInformacion.setOrganizaOtro(Integer.valueOf(data[153].toString()) == 1 ? 9 : 0);
                mediosInformacion.setConocimientoPrevio(Integer.valueOf(data[154].toString()) == 10 ? 1 : 0);
                mediosInformacion.setOtro(Integer.valueOf(data[155].toString()) == 1 ? 11 : 0);

                // Setear datos tr_organiza_r
                organiza = new Organiza();
                organiza.setUstedMismo(Integer.valueOf(data[175].toString()) == 1 ? 1 : 0);
                organiza.setAgenciaViajeOnline(Integer.valueOf(data[176].toString()) == 1 ? 2 : 0);
                organiza.setAgenciaViajeFueraChile(Integer.valueOf(data[177].toString()) == 1 ? 3 : 0);
                organiza.setOficinaViajePresencialOnlineChilena(Integer.valueOf(data[178].toString()) == 1 ? 4 : 0);
                organiza.setSitiosWebReserva(Integer.valueOf(data[179].toString()) == 1 ? 5 : 0);
                organiza.setSitiosWebComparadorPrecios(Integer.valueOf(data[180].toString()) == 1 ? 6 : 0);
                organiza.setOtro(Integer.valueOf(data[181].toString()) == 1 ? 7 : 0);

                // Setear datos tr_razon_r
                razonViaje = new RazonViaje();
                razonViaje.setNaturalezaPaisajesFloraFauna(Integer.valueOf(data[156].toString()) == 1 ? 1 : 0);
                razonViaje.setCulturaLocal(Integer.valueOf(data[157].toString()) == 1 ? 2 : 0);
                razonViaje.setLosChilenos(Integer.valueOf(data[158].toString()) == 1 ? 3 : 0);
                razonViaje.setConectividad(Integer.valueOf(data[159].toString()) == 1 ? 4 : 0);
                razonViaje.setSensacionSeguridadPais(Integer.valueOf(data[160].toString()) == 1 ? 5 : 0);
                razonViaje.setClima(Integer.valueOf(data[161].toString()) == 1 ? 6 : 0);
                razonViaje.setVinosViñas(Integer.valueOf(data[162].toString()) == 1 ? 7 : 0);
                razonViaje.setConocerChilePrimeraVez(Integer.valueOf(data[163].toString()) == 1 ? 8 : 0);
                razonViaje.setConocerLugaresPendientes(Integer.valueOf(data[164].toString()) == 1 ? 9 : 0);
                razonViaje.setDescansar(Integer.valueOf(data[165].toString()) == 1 ? 10 : 0);
                razonViaje.setIncluidoEnRutaViaje(Integer.valueOf(data[166].toString()) == 11 ? 1 : 0);
                razonViaje.setFamiliaAmigosResidentesChile(Integer.valueOf(data[167].toString()) == 1 ? 12 : 0);
                razonViaje.setCompras(Integer.valueOf(data[168].toString()) == 1 ? 13 : 0);
                razonViaje.setOtro(Integer.valueOf(data[169].toString()) == 1 ? 14 : 0);


                // Setear datos tr_servpt_r
                servicioPaqueteTuristico = new ServicioPaqueteTuristico();
                servicioPaqueteTuristico.setPasajesInternacionales(Integer.valueOf(data[66].toString()) == 1 ? 1 : 0);
                servicioPaqueteTuristico.setAlojamiento(Integer.valueOf(data[67].toString()) == 1 ? 2 : 0);
                servicioPaqueteTuristico.setAlimentacionFueraHotel(Integer.valueOf(data[68].toString()) == 1 ? 3 : 0);
                servicioPaqueteTuristico.setTrasladosAeropuertoDestino(Integer.valueOf(data[69].toString()) == 1 ? 4 : 0);
                servicioPaqueteTuristico.setVuelosDomesticosExtranjero(Integer.valueOf(data[70].toString()) == 1 ? 5 : 0);
                servicioPaqueteTuristico.setOtrosDestinosInternacionales(Integer.valueOf(data[71].toString()) == 1 ? 6 : 0);
                servicioPaqueteTuristico.setTourExcursiones(Integer.valueOf(data[72].toString()) == 1 ? 7 : 0);
                servicioPaqueteTuristico.setSeguroViajeSalud(Integer.valueOf(data[73].toString()) == 1 ? 8 : 0);
                servicioPaqueteTuristico.setOtro(Integer.valueOf(data[74].toString()) == 1 ? 9 : 0);

                // Setear datos data_tr_base
                base = new BaseTr();
                base.setId_encuesta(data[3].toString());
                base.setCodigo_interno(Integer.valueOf(data[4].toString()));
                base.setId_encuesta(data[5].toString());
                base.setFecha(anio + "-" + mes + "-" + dia);
                base.setId_paso(Integer.valueOf(data[6].toString()));
                base.setId_anio(Integer.parseInt(anio));
                base.setId_mes(Integer.parseInt(mes));
                base.setId_mes_trimestre(trimestre);
                base.setId_mes_semestre(semestre);
                base.setId_residencia(Integer.valueOf(data[8].toString()));
                base.setId_nacionalidad(Integer.valueOf(data[10].toString()));
                base.setGrupo_mujer(Integer.valueOf(data[30].toString()));
                base.setGrupo_hombre(Integer.valueOf(data[31].toString()));
                base.setGrupo_total(Integer.valueOf(data[32].toString()));
                base.setPrimera_visita(Integer.valueOf(data[18].toString()));
                base.setId_motivo_principal(Integer.valueOf(data[13].toString()));
                base.setNoches_chile(Integer.valueOf(data[17].toString()));
                base.setId_tr_transporte_entrada(Integer.valueOf(data[19].toString()));
                base.setId_linea_aerea_entrada(Integer.valueOf(data[21].toString()));
                base.setNumero_vuelo_entrada(Integer.valueOf(data[23].toString()));
                base.setMonto_pasaje_entrada_usd(Float.valueOf(data[24].toString().replace(",", ".")));
                base.setId_linea_aerea_salida(Integer.valueOf(data[25].toString()));
                base.setNumero_vuelo_salida(Integer.valueOf(data[27].toString()));
                base.setMonto_pasaje_salida_usd(Float.valueOf(data[28].toString().replace(",", ".")));
                base.setMonto_idavuelta_usd(Float.valueOf(data[29].toString().replace(",", ".")));
                base.setAgencia_online(data[76].toString());
                base.setGasto_total_grupo_usd(Float.valueOf(data[35].toString().replace(",", ".")));
                base.setDivisas(Float.valueOf(data[36].toString().replace(",", ".")));
                base.setGpdi(gpdi);
                base.setId_gpdi_tramo(id_gpdi);
                base.setDia_turista(Integer.valueOf(data[34].toString()));
                base.setSatisfaccion(Integer.valueOf(data[42].toString()));
                base.setNps(Integer.valueOf(data[44].toString()));
                base.setMonto_pt_usd(Float.valueOf(data[33].toString().replace(",", ".")));
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
            flag = baseService.agregar(base, actividad, alimentacion, alojamiento, ciudad, financia, gasto, mediosInformacion,
                    organiza, razonViaje, servicioPaqueteTuristico, grupo);
            if (flag == 1) {
                Util.setLog(archivoLog, "I", "Insertar Registros --> OK en línea " + cuenta, "");
                retorno = true;
            } else {
                Util.setLog(archivoLog, "I", "Insertar Registros --> ERROR en línea " + cuenta + " (" + flag + ")", "");
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
            carga.setId_base(13); //TR
            carga.setId_estado_carga(estado);
            carga.setId_error_carga(error);
            carga.setNum_rows(cuenta);
            carga.setNum_cols(Integer.parseInt(prop.getProperty("carga.tr.col")));
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
