package com.ipartek.formacion.proyecto.controladores.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.proyecto.controladores.MasterServlet;
import com.ipartek.formacion.proyecto.pojo.Grupo;
import com.ipartek.formacion.proyecto.service.GrupoService;

/**
 * Servlet implementation class CheckUserServlet
 */
public class CheckUserServlet extends MasterServlet {
	private static final long serialVersionUID = 1L;
	private static GrupoService servicioGrupo;

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

			String criterio = request.getParameter("p1");
			String busqueda = request.getParameter("p2");

			boolean existe = false;

			for (Grupo grupo : servicioGrupo.buscar(busqueda)) {
				if (("nombre".equals(criterio) && grupo.getNombre().equals(busqueda))
						|| ("dni".equals(criterio) && grupo.getDni().equals(busqueda))
						|| ("email".equals(criterio) && grupo.getEmail().equals(busqueda))) {
					existe = true;
				}
			}

			out.print(existe);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}