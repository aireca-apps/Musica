package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LogoutServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session.removeAttribute("userlogged");
		session.invalidate();
		session = null;

		msj = new Mensaje("Gracias por su visita", Mensaje.TIPO_INFO);
		request.setAttribute("msj", msj);
		LOG.trace("Se procede a deslogar al usuario");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_LOGIN);
		dispatch.forward(request, response);
	}

}
