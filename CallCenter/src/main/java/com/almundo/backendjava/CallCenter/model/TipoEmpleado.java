package com.almundo.backendjava.CallCenter.model;

public enum TipoEmpleado {
	
	OPERADOR("Operador"),
	SUPERVISOR("Supervisor"),
	DIRECTOR("Director");
	
	private final String nombreTipoEmpleado;

	private TipoEmpleado(String nombreTipoEmpleado) {
		this.nombreTipoEmpleado = nombreTipoEmpleado;
	}

	public String getNombreTipoEmpleado() {
		return nombreTipoEmpleado;
	}

}
