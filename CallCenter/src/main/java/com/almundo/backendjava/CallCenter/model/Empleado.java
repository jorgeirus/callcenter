package com.almundo.backendjava.CallCenter.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.almundo.backendjava.CallCenter.utils.ConfigurationProperties;

public class Empleado {
	private int idEmpleado;
	private TipoEmpleado tipoEmpleado;
	private boolean Estadolibre;

	protected static final Logger LOGGER = Logger.getLogger(Empleado.class.getName());

	public Empleado() {
		super();
	}

	public Empleado(int idEmpleado, TipoEmpleado tipoEmpleado) {
		super();
		this.idEmpleado = idEmpleado;
		this.tipoEmpleado = tipoEmpleado;
	}

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public boolean isEstadolibre() {
		return Estadolibre;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setEstadolibre(boolean estadolibre) {
		Estadolibre = estadolibre;
	}

	/**
	 * Acción del empleado que contesta la llamada establece el intervalo de tiempo
	 * entre 5 y 10 segundos aleatoriamente de igual manera actualiza su estado a
	 * disponible al terminar
	 * 
	 * @param llamada
	 */
	public void contestarLlamada(Llamada llamada) {
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		int maxTime = configurationProperties.getTiempoMaxLlamada();
		int minTime = configurationProperties.getTiempoMinLlamada();
		Random ran = new Random();
		int time = ran.nextInt(maxTime - minTime) + minTime;
		System.out.println(time);
		llamada.setDuracion(time);
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		llamada.setEmpleado(this.getTipoEmpleado().getNombreTipoEmpleado());
		this.setEstadolibre(true);
		LOGGER.info("Llamada contestada por: " + this.getTipoEmpleado().getNombreTipoEmpleado() + ""+ this.getIdEmpleado() + " Duración: "
				+ time + "seg");
	}

}
