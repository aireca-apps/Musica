package com.ipartek.formacion.proyecto.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.modelo.DbConnection;

/**
 * Application Lifecycle Listener implementation class DBListener
 *
 */
public class DBListener implements ServletContextListener {

	private final static Logger LOG = Logger.getLogger(DBListener.class);
	public static boolean error = false;

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		error = false;
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			new DbConnection();
		} catch (Exception e) {
			LOG.error("No hay conexión con la base de datos: " + e.getMessage());
			error = true;
		}
	}
}