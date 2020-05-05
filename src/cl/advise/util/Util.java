package cl.advise.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Util {
	
	
	public static final String FILE_PROPERTIES_CONFIG = "CargaDatos.properties";
//	public static final String FILE_PROPERTIES_PATH = String.format("%s/config", System.getenv("HOME"));
	public static final String FILE_PROPERTIES_PATH = "C:\\desarrollo\\CargaDatos\\Archivos\\";
	Properties properties;
	
	public InputStream getResource(String filename) throws IOException {
        InputStream streamOnClass = getClass().getResourceAsStream(filename);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        streamOnClass = getClass().getResourceAsStream("/"+filename);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        streamOnClass = getClass().getClassLoader().getResourceAsStream(filename);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        streamOnClass = getClass().getClassLoader().getResourceAsStream("/"+filename);        
        if (streamOnClass != null) {
        	return streamOnClass;
        }
        streamOnClass = new ClassPathResource(filename).getInputStream();
        return streamOnClass;
    }
	
	public Properties getParametros() {
		
		if (properties == null) {
			properties = new Properties();

			try {
				System.out.println("---------------------------------------------");
				System.out.println("Cargando configuraciones externas");
				System.out.println("---------------------------------------------");
				String propsFileFullPath = String.format("%s/%s", FILE_PROPERTIES_PATH, FILE_PROPERTIES_CONFIG).replace("//", "/");
				System.out.println(String.format("Buscando archivo de configuracion en la siguiente ruta: %s", propsFileFullPath));
				InputStream resource = new FileInputStream(propsFileFullPath);

				if (resource != null) {
					System.out.println(String.format("Archivo properties cargado desde fuera del WAR: %s", propsFileFullPath));
					properties.load(resource);
				}
			} catch (Exception e) {
				System.out.println("CONFIGURACIONES EXTERNAS NO ENCONTRADAS!!");
			}
		}
		return properties;
	}
	
	// Rescatar DataSource
	public static DriverManagerDataSource getDataSource(String driver, String url, String user, String pass) {
		DriverManagerDataSource dataSource = null;
		try { 
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(pass);
			//dataSource.getConnection().setAutoCommit(false);
		} catch (Exception ex) {
			dataSource = null;
			ex.printStackTrace();
		}
		return dataSource;
	}
	
	
	// Respaldar archivo
	public static boolean respaldarArchivo(String in, String out) {
		Path pathIn = FileSystems.getDefault().getPath(in);
		Path pathOut = FileSystems.getDefault().getPath(out);
		try {
			Files.move(pathIn, pathOut, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception ex) {
			ex.printStackTrace();
        	return false;
		}
		return true;
	}
	
	
	// Generar Log
	public static void setLog(String file, String mode, String oper, String exception) {
		FileWriter fileLog = null;
		try {
			if (!(new File(file).exists())) {
				fileLog = new FileWriter(new File(file),false);
			}
			fileLog = new FileWriter(new File(file), true);
			fileLog.write(getFechaHoraLog() + " " + mode + " " + oper);
			if (!(exception==null || exception.trim().equals(""))) {
				fileLog.write(" (" + exception + ")");
			}
			fileLog.write("\r\n");
			fileLog.flush();
			fileLog.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static String getOut(String oper) {
		return getFechaHoraLog() + " - " + oper;
	}
	
	
	// Rescatar fecha y hora proceso
	public static String getFechaHoraProceso() {
		return(getFechaHora("yyyyMMddHHmmssSSS"));
	}
	
		
	// Rescatar fecha y hora carga
	public static String getFechaHoraCarga() {
		return(getFechaHora("yyyyMMddHHmmss"));
	}
	
	
	// Rescatar fecha Carga
	public static String getFechaCarga() {
		return(getFechaHora("yyyy-MM-dd"));
	}
	
	
	// Rescatar fecha Carga
	public static String getHoraCarga() {
		return(getFechaHora("HH:mm:ss"));
	}
	
	
	// Rescatar fecha y hora log
	public static String getFechaHoraLog() {
		return(getFechaHora("dd/MM/yyyy HH:mm:ss.SSS"));
	}
	
	
	// Rescatar fecha y hora
	public static String getFechaHora(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String sDate = formatter.format(new Date());
		return(sDate);
	}
	
	
	// Validar número entero
	public static boolean esNumero(Object dato){
		try {
			Integer.parseInt(String.valueOf(dato));
			return true;
		} catch (NumberFormatException ex){
			return false;
		}
	}
	
	
	// Validar número entero grande
	public static boolean esNumeroGrande(Object dato){
		try {
			Float.parseFloat(String.valueOf(dato));
			return true;
		} catch (NumberFormatException ex){
			return false;
		}
	}

	
	// Validar número decimal
	public static boolean esDecimal(Object dato){
		try {
			Double.parseDouble(String.valueOf(dato));
			return true;
		} catch (NumberFormatException ex){
			return false;
		}
	}
	
	
	// Validar formato archivo (Anio)
	public static String[] validarFormatoAnio(String file) {
		String[] data = {"0000","00"};
		try {
			String[] dataArchivo = file.split("_");
			if (dataArchivo.length == 3) {
				String dataDesde = dataArchivo[dataArchivo.length-2];
				data[0] = dataDesde.substring(0,4);
				String dataHasta = dataArchivo[dataArchivo.length-1];
				data[1] = dataHasta.substring(0,4);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	
	// Validar formato archivo (AnioMes)
	public static String[] validarFormatoAnioMes(String file) {
		String[] data = {"0000","00","0000","00"};
		try {
			String[] dataArchivo = file.split("_");
			if (dataArchivo.length == 3) {
				String dataDesde  = dataArchivo[dataArchivo.length-2];
				data[0] = dataDesde.substring(0,4);
				data[1] = dataDesde.substring(4,6);
				String dataHasta = dataArchivo[dataArchivo.length-1];
				data[2] = dataHasta.substring(0,4);
				data[3] = dataHasta.substring(4,6);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	
	// Actualizar vistas
	public static boolean actualizarVistas(DataSource dataSource, String list) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		try {
			jdbcTemplate.setDataSource(dataSource);
			for (String view: list.split(";")) {
				jdbcTemplate.execute("REFRESH MATERIALIZED VIEW " + view);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
        	return false;
		}
		return true;
	}
	
	
	// Enviar mail
	public static boolean enviarMail(String ip, String port, String from, String recipients, String subject, String body, String user, String pass) {
		Properties propMail = new Properties();
		Session session;
		StringBuilder str = new StringBuilder();
		str.append("mail.smtp.host: " + ip + ", ");
		str.append("mail.smtp.port: " + port + ", ");
		str.append("recipients: " + recipients + ", ");
		str.append("subject: " + subject + ", ");
		str.append("body: " + body + ", ");
		str.append("user: " + user + ", ");
		str.append("pass: " + pass + ".");
		System.out.printf(str.toString());
		
		try {
			propMail.put("mail.smtp.host", ip);
			propMail.put("mail.smtp.port", port);
			propMail.put("mail.smtp.auth", "true");
			propMail.put("mail.smtp.starttls.enable", "true");
			propMail.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			propMail.put("mail.smtp.user", user);
			propMail.put("mail.smtp.clave", pass);    //La clave de la cuenta
		    
			session = Session.getInstance(propMail);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, recipients);
			message.setSubject(subject);
			message.setText(body);
	        Transport.send(message, user, pass);
		} catch (Exception ex) {
			ex.printStackTrace();
        	return false;
		}
		return true;
	}
	
}
