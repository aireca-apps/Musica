package com.ipartek.formacion.backoffice.controladores.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.backoffice.listeners.SessionListener;

/**
 * Servlet implementation class LoggedUserServlet
 */
public class LoggedUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		//String p1 = request.getParameter("p1");		
		//String respuesta = "{\"clave\": \"volando voy " + p1 + " \"}";		
		//out.print(respuesta);
		
		Gson gson = new Gson();
		String repuesta = gson.toJson( SessionListener.listaUsariosLogeados );		
		out.print(repuesta);
		out.flush();		
	}
}