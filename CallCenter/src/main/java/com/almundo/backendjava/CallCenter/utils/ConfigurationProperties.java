package com.almundo.backendjava.CallCenter.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProperties {

	private Integer tiempoMaxLlamada;
	private Integer tiempoMinLlamada;
	private Integer threads;
	private Integer numOperadores;
	private Integer numSupervisores;
	private Integer numDirectores;

	/**
	 * Constructor con parametros de configuración
	 * 
	 * @param threads
	 * @param numOperadores
	 * @param numSupervisores
	 * @param numDirectores
	 */
	public ConfigurationProperties(Integer threads, Integer numOperadores, Integer numSupervisores,
			Integer numDirectores) {
		super();
		Properties prop = this.loadConfig();
		this.tiempoMaxLlamada = Integer.parseInt(prop.getProperty("tiempoMaxLlamada"));
		this.tiempoMinLlamada = Integer.parseInt(prop.getProperty("tiempoMinLlamada"));
		this.threads = threads;
		this.numOperadores = numOperadores;
		this.numSupervisores = numSupervisores;
		this.numDirectores = numDirectores;
	}

	
	/**
	 * Constructor sin parametros para el cargue desde el archivo config.properties
	 */
	public ConfigurationProperties() {
		super();
		Properties prop = this.loadConfig();
		this.tiempoMaxLlamada = Integer.parseInt(prop.getProperty("tiempoMaxLlamada"));
		this.tiempoMinLlamada = Integer.parseInt(prop.getProperty("tiempoMinLlamada"));
		this.threads = Integer.parseInt(prop.getProperty("threads"));
		this.numOperadores = Integer.parseInt(prop.getProperty("operador"));
		this.numSupervisores = Integer.parseInt(prop.getProperty("supervisor"));
		this.numDirectores = Integer.parseInt(prop.getProperty("director"));
	}


	/**
	 * Configuración del archivo .properties para ser leido en cualquier clase que lo requiera
	 * @return
	 */
	public Properties loadConfig() {
		Properties prop = new Properties();
		String filePath = "config.properties";
		InputStream inputStream = null;
		try {

			inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("El Archivo de propiedades: " + filePath + " No se econtro");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prop;
	}

	public Integer getTiempoMaxLlamada() {
		return tiempoMaxLlamada;
	}

	public void setTiempoMaxLlamada(Integer tiempoMaxLlamada) {
		this.tiempoMaxLlamada = tiempoMaxLlamada;
	}

	public Integer getTiempoMinLlamada() {
		return tiempoMinLlamada;
	}

	public void setTiempoMinLlamada(Integer tiempoMinLlamada) {
		this.tiempoMinLlamada = tiempoMinLlamada;
	}

	public Integer getNumOperadores() {
		return numOperadores;
	}

	public void setNumOperadores(Integer numOperadores) {
		this.numOperadores = numOperadores;
	}

	public Integer getNumSupervisores() {
		return numSupervisores;
	}

	public void setNumSupervisores(Integer numSupervisores) {
		this.numSupervisores = numSupervisores;
	}

	public Integer getNumDirectores() {
		return numDirectores;
	}

	public void setNumDirectores(Integer numDirectores) {
		this.numDirectores = numDirectores;
	}

	public Integer getThreads() {
		return threads;
	}

	public void setThreads(Integer threads) {
		this.threads = threads;
	}

}
