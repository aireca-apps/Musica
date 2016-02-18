package com.ipartek.formacion.proyecto.controladores.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.ipartek.formacion.proyecto.listeners.SessionListener;

/**
 * Servlet implementation class LoggedUserServlet
 */
public class LoggedUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoggedUserServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("LoggedUserServlet: doGet llama a do Post");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("LoggedUserServlet: doPost");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		String repuesta = gson.toJson(SessionListener.listaUsariosLogeados);
		out.print(repuesta);
		out.flush();
	}
}