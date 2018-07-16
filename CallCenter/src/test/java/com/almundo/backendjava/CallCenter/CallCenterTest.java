package com.almundo.backendjava.CallCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.almundo.backendjava.CallCenter.dispatcher.Dispatcher;
import com.almundo.backendjava.CallCenter.model.Empleado;
import com.almundo.backendjava.CallCenter.model.Llamada;
import com.almundo.backendjava.CallCenter.model.TipoEmpleado;
import com.almundo.backendjava.CallCenter.utils.ConfigurationProperties;


import junit.framework.TestCase;

/**
 * @author Jorge Moreno Unit test for CallCenter.
 */
public class CallCenterTest extends TestCase {

	List<Llamada> testLlamadas;
	List<Empleado> empleadosTest;
	private static final Logger LOGGER = Logger.getLogger(CallCenterTest.class.getName());

	

	public CallCenterTest(String name) {
		super(name);
		this.testLlamadas = new ArrayList<>();
		this.empleadosTest = new ArrayList<>();
	}

	@Before
	public void beforeInitTest() {
		testLlamadas = new ArrayList<>();
		empleadosTest = new ArrayList<>();
	}
	/**
	 * Primera prueba unitaria con una sola llamada un proceso o hilo y 1 solo empleado para el caso un SUPERVISOR Nomeclatura (Hilo, Operador, Supervisor, Director)
	 * @throws InterruptedException 
	 */
	@Test
	public void test1llamada() throws InterruptedException {
		int numeroDeLlamadas = 1;
		ConfigurationProperties configurationProperties = new ConfigurationProperties(1, 0, 1, 0);
		IntStream.range(0, configurationProperties.getNumDirectores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.DIRECTOR)));
    	IntStream.range(0, configurationProperties.getNumOperadores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.OPERADOR)));
    	IntStream.range(0, configurationProperties.getNumSupervisores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.SUPERVISOR)));
    	IntStream.range(0, numeroDeLlamadas).forEach(i->testLlamadas.add(new Llamada(i, "llamada Entrante")));
    	Dispatcher dispatcher = new Dispatcher(empleadosTest, configurationProperties);
    	dispatcher.atenderLlamadas(testLlamadas);
    	Thread.sleep(2000);
    	Assert.assertEquals(this.testLlamadas.get(0), "Supervisor 0");
    	
	}
	
	/**
	 * Segunda prueba unitaria con 3 llamadas 3 procesos o hilos 1 solo empleado para el caso 1 OPERADOR (prueba de cola de llamadas) Nomeclatura (Hilo, Operador, Supervisor, Director)
	 */
	@Test
	public void test3llamadas() {
		int numeroDeLlamadas = 3;
		ConfigurationProperties configurationProperties = new ConfigurationProperties(3, 1, 0, 0);
		IntStream.range(0, configurationProperties.getNumDirectores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.DIRECTOR)));
    	IntStream.range(0, configurationProperties.getNumOperadores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.OPERADOR)));
    	IntStream.range(0, configurationProperties.getNumSupervisores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.SUPERVISOR)));
    	IntStream.range(0, numeroDeLlamadas).forEach(i->testLlamadas.add(new Llamada(i, "llamada Entrante")));
    	Dispatcher dispatcher = new Dispatcher(empleadosTest, configurationProperties);
    	dispatcher.atenderLlamadas(testLlamadas);
    	
	}
	
	/**
	 * Tercera prueba unitaria con 10 llamadas 10 procesos o hilos, para el caso 6 OPERADORES, 2 SUPERVISORES y 2 DIRECTORES Nomeclatura (Hilo, Operador, Supervisor, Director)
	 */
	@Test
	public void test10llamadas() {
		int numeroDeLlamadas = 10;
		ConfigurationProperties configurationProperties = new ConfigurationProperties(10, 6, 2, 2);
		IntStream.range(0, configurationProperties.getNumDirectores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.DIRECTOR)));
    	IntStream.range(0, configurationProperties.getNumOperadores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.OPERADOR)));
    	IntStream.range(0, configurationProperties.getNumSupervisores()).forEach(i -> empleadosTest.add(new Empleado(i,TipoEmpleado.SUPERVISOR)));
    	IntStream.range(0, numeroDeLlamadas).forEach(i->testLlamadas.add(new Llamada(i, "llamada Entrante")));
    	Dispatcher dispatcher = new Dispatcher(empleadosTest, configurationProperties);
    	dispatcher.atenderLlamadas(testLlamadas);
    	
	}

}
