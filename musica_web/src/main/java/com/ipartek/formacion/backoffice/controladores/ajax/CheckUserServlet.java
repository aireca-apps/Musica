package com.ipartek.formacion.backoffice.controladores.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.backoffice.listeners.DAOListener;
import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;

/**
 * Servlet implementation class CheckUserServlet
 */
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PersonaDAO daoPersona;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoPersona = DAOListener.daoPersona;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			String criterio = request.getParameter("p1");
			String busqueda = request.getParameter("p2");
			
			boolean existe = false;
			
			for (Persona persona : daoPersona.getPersonas(busqueda)) {
				if (
					( "nombre".equals(criterio)		&& persona.getNombre().equals(busqueda)	) ||
					( "dni".equals(criterio)		&& persona.getDni().equals(busqueda)	) ||
					( "email".equals(criterio)		&& persona.getEmail().equals(busqueda)	)
				) {
					existe = true;
				}
			}
			
			out.print(existe);
			out.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}