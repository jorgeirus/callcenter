package com.almundo.backendjava.CallCenter.dispatcher;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

import com.almundo.backendjava.CallCenter.model.Empleado;
import com.almundo.backendjava.CallCenter.model.Llamada;
import com.almundo.backendjava.CallCenter.model.TipoEmpleado;
import com.almundo.backendjava.CallCenter.utils.ConfigurationProperties;

/**
 * 
 * @author Jorge Moreno
 *
 */
public class Dispatcher {

	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());

	private List<Llamada> listaDeLLamadasEntrantes;

	private BlockingQueue<Llamada> llamdasPendientes;

	private List<Empleado> listEmpleados;

	private ExecutorService executorService;

	/**
	 * Constructor con parametros el cual recibe una Lista de empleados y crea la
	 * fabrica de procesos concurrentes utilizando la clase Executors, de igual
	 * forma se sobrescribe la clase Thread para crear el escenario
	 * 
	 * @param listEmpleados
	 * @param config
	 */
	public Dispatcher(List<Empleado> listEmpleados, ConfigurationProperties config) {
		super();
		this.listEmpleados = listEmpleados;
		this.executorService = Executors.newFixedThreadPool(config.getThreads(), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				Thread thread = Executors.defaultThreadFactory().newThread(r);
				thread.setDaemon(false);
				return thread;
			}
		});
	}

	/**
	 * Asigna el empleado para atender la llamada y ejecuta el proceso
	 * 
	 * @param llamada
	 * @param empl
	 */
	public void dispatchCall(Llamada llamada, Empleado empl) {
		Runnable proceso = () -> {
			empl.contestarLlamada(llamada);
			liberarLlamadasEnCola();
		};

		executorService.submit(proceso);

	}

	/**
	 * Metodo que recibe la lista de llamadas del CallCenter luego las distribuye
	 * entre los empleados disponibles
	 * 
	 * @param llamadas
	 */
	public void atenderLlamadas(List<Llamada> llamadas) {
		this.setListaDeLLamadasEntrantes(llamadas);
		llamadas.forEach(llamada -> verificarDisponibilidad(llamada));
	}

	/**
	 * Verifica la disponibilidad y ordena las solicitudes por Operador luego
	 * Supervisor y por ultimo Director, en caso de no existir empleados disponibles
	 * encola las llamadas para luego atenderlas
	 * 
	 * @param llamada
	 */
	public void verificarDisponibilidad(Llamada llamada) {
		Empleado empleadoDisponible = isOperador();
		if (empleadoDisponible == null) {
			LOGGER.info("Novedad Llamada " + llamada.getDescription()
					+ " No hay empleados disponibles espere por favor...");
			this.llamdasPendientes.add(llamada);
		}
		dispatchCall(llamada, empleadoDisponible);
	}

	/**
	 * Busca si hay llamadas pendientes si no lo hay mata el proceso o tarea y en
	 * caso de existir la atiende y vuelve a verficar disponibilidad de empleados
	 * para atenderla
	 */
	public void liberarLlamadasEnCola() {
		if (this.llamdasPendientes.isEmpty()) {
			this.executorService.shutdown();
		}
		Llamada llamada = this.llamdasPendientes.remove();
		verificarDisponibilidad(llamada);
	}

	public Empleado isOperador() {
		Empleado empleado = this.listEmpleados.stream()
				.filter(emp -> !emp.isEstadolibre() && emp.getTipoEmpleado() == TipoEmpleado.OPERADOR).findFirst()
				.orElse(isSupervisor());
		return empleado;
	}

	public Empleado isSupervisor() {
		Empleado empleado = this.listEmpleados.stream()
				.filter(emp -> !emp.isEstadolibre() && emp.getTipoEmpleado() == TipoEmpleado.SUPERVISOR).findFirst()
				.orElse(isDirector());
		return empleado;
	}

	public Empleado isDirector() {
		Empleado empleado = this.listEmpleados.stream()
				.filter(emp -> !emp.isEstadolibre() && emp.getTipoEmpleado() == TipoEmpleado.DIRECTOR).findFirst()
				.orElse(null);
		return empleado;
	}

	public List<Llamada> getListaDeLLamadasEntrantes() {
		return listaDeLLamadasEntrantes;
	}

	public void setListaDeLLamadasEntrantes(List<Llamada> listaDeLLamadasEntrantes) {
		this.listaDeLLamadasEntrantes = listaDeLLamadasEntrantes;
	}

	public List<Empleado> getListEmpleados() {
		return listEmpleados;
	}

	public void setListEmpleados(List<Empleado> listEmpleados) {
		this.listEmpleados = listEmpleados;
	}

	public BlockingQueue<Llamada> getLlamdasPendientes() {
		return llamdasPendientes;
	}

	public void setLlamdasPendientes(BlockingQueue<Llamada> llamdasPendientes) {
		this.llamdasPendientes = llamdasPendientes;
	}

}
