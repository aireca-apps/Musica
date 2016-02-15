package com.ipartek.formacion.backoffice.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.backoffice.Constantes;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends MasterServlet {
	
	private static final long serialVersionUID = 1L;     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session.removeAttribute("userlogged");		
		session.invalidate();
		session = null;
		
		msj = new Mensaje("Gracias por su visita", Mensaje.TIPO_INFO);
		request.setAttribute("msj", msj);
		
		dispatch = request.getRequestDispatcher( Constantes.VIEW_LOGIN);
		dispatch.forward(request, response);
	}

}
