package com.almundo.backendjava.CallCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.almundo.backendjava.CallCenter.dispatcher.Dispatcher;
import com.almundo.backendjava.CallCenter.model.Empleado;
import com.almundo.backendjava.CallCenter.model.Llamada;
import com.almundo.backendjava.CallCenter.model.TipoEmpleado;
import com.almundo.backendjava.CallCenter.utils.ConfigurationProperties;

/**
 * Hello world!
 *
 */
public class CallCenter {
	public static void main(String[] args) {
		/**
		 * Haciendo un test con el archivo de configuración por defecto esto para
		 * recrear la solicitud de la prueba java La clase Dispatcher debe tener la
		 * capacidad de poder procesar 10 llamadas al mismo tiempo (de modo
		 * concurrente).
		 */
		List<Llamada> testLlamadas = new ArrayList<>();
		List<Empleado> empleados = new ArrayList<>();
		int numeroDeLlamadas = 15;
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		IntStream.range(0, configurationProperties.getNumDirectores())
				.forEach(i -> empleados.add(new Empleado(i, TipoEmpleado.DIRECTOR)));
		IntStream.range(0, configurationProperties.getNumOperadores())
				.forEach(i -> empleados.add(new Empleado(i, TipoEmpleado.OPERADOR)));
		IntStream.range(0, configurationProperties.getNumSupervisores())
				.forEach(i -> empleados.add(new Empleado(i, TipoEmpleado.SUPERVISOR)));
		IntStream.range(0, numeroDeLlamadas).forEach(i -> testLlamadas.add(new Llamada(i, "llamada Entrante")));
		Dispatcher dispatcher = new Dispatcher(empleados, configurationProperties);
		dispatcher.atenderLlamadas(testLlamadas);

	}
}
