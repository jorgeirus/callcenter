package com.almundo.backendjava.CallCenter.model;

public class Llamada {

	private int idLlamada;
	private String description;
	private Integer duracion;
	private String empleado;

	public Llamada() {
		super();
	}

	

	public Llamada(int idLlamada, String description) {
		this.idLlamada = idLlamada;
		this.description = description;
	}



	public int getIdLlamada() {
		return idLlamada;
	}

	public void setIdLlamada(int idLlamada) {
		this.idLlamada = idLlamada;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
