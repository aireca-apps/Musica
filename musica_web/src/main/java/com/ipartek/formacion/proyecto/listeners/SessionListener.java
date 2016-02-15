package com.ipartek.formacion.proyecto.listeners;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.pojo.Grupo;

public class SessionListener implements HttpSessionListener,
HttpSessionAttributeListener {

	private final static Logger LOG = Logger.getLogger(SessionListener.class);

	private int sessionCount = 0;
	private int userLoggedCount = 0;
	public static ArrayList<Grupo> listaUsariosLogeados = new ArrayList<Grupo>();
	private ServletContext sc;

	@Override
	public void sessionCreated(HttpSessionEvent event) {

		synchronized (this) {
			sessionCount++;
			sc = event.getSession().getServletContext();
			sc.setAttribute("visitantes", sessionCount);
		}

		LOG.info("Session Created: " + event.getSession().getId());
		LOG.info("Total Sessions: " + sessionCount);
		LOG.info("Total Sessions: " + userLoggedCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount--;
			sc = event.getSession().getServletContext();
			sc.setAttribute("visitantes", sessionCount);
		}
		LOG.info("Session Destroyed: " + event.getSession().getId());
		LOG.info("Total Sessions: " + sessionCount);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {

		LOG.info("session attributeAdded " + se.getName());

		if ("userlogged".equals(se.getName())) {
			synchronized (this) {
				userLoggedCount++;
				listaUsariosLogeados.add((Grupo) se.getValue());
			}
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {

		LOG.info("session attributeRemoved " + se.getName());
		if ("userlogged".equals(se.getName())) {
			synchronized (this) {
				userLoggedCount++;
				listaUsariosLogeados.remove(se.getValue());
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {

		LOG.info("session attributeReplaced " + se.getName());

	}
}