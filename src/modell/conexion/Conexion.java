package modell.conexion;

import java.sql.*;

/**
 * Clase para la Conexion con la BASE DE DATOS.
 */
public class Conexion {

	private Connection conexion;
	private String driver;
	private String url;
	private String dbms;
	private String host;
	private String puerto;
	private String nombre_usuario;
	private String clave;
	private String nombre_base_datos;

	public Conexion() {
		driver = "org.sqlite.JDBC";
		dbms = "sqlite";
		nombre_base_datos = "roco.db";
		url = "jdbc:" + dbms + ":" + nombre_base_datos;
		try {
			Class.forName(this.driver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err
					.print("Conexion - ClassNotFoundException:No Driver sqlite ");
			System.err.println(e.getMessage());
		}
	}

	public void setDriver(String driver) {
		if (driver.equals("sqlite")) {
			try {
				Class.forName(this.driver);
			} catch (java.lang.ClassNotFoundException e) {
				System.err
						.print("setDriver - ClassNotFoundException: No Driver sqlite ");
				System.err.println(e.getMessage());
			}
		} else {
			try {
				this.driver = driver;
				Class.forName(this.driver);
			} catch (java.lang.ClassNotFoundException e) {
				System.err.print("setDriver - ClassNotFoundException: ");
				System.err.println(e.getMessage());
			}
		}
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
		url = "jdbc:" + this.dbms + ":" + nombre_base_datos;
	}

	public void setHost(String host) {
		this.host = host;
		url = "jdbc:" + this.dbms + ":" + nombre_base_datos;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
		url = "jdbc:" + this.dbms + ":" + nombre_base_datos;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setNombre_base_datos(String nombre_base_datos) {
		this.nombre_base_datos = nombre_base_datos;
		url = "jdbc:" + this.dbms + ":" + nombre_base_datos;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public Connection getConnexion() throws SQLException {
		conexion = DriverManager.getConnection(url);
		return conexion;
	}
}
