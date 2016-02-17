package com.ipartek.formacion.proyecto.controladores.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.proyecto.controladores.MasterServlet;

/**
 * Servlet implementation class CheckUserServlet
 */
public class CheckUserServlet extends MasterServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();

			String campo = request.getParameter("p1");
			String valor = request.getParameter("p2");

			out.print(serviceUsuario.comprobar(campo, valor));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}