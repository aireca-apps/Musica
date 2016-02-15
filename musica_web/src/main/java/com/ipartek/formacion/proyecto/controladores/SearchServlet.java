package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Grupo;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends MasterServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(SearchServlet.class);

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * Ahora se llamaría a este método y de este método llamaría a doGet o
	 * doPost mediante thread
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * Hacemos lo mismo venga de doGet o doPost
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Guardar el listado como atributo en request
		String criterio = request.getParameter("criterio");
		LOG.debug("Buscando criterio " + criterio);
		ArrayList<Grupo> listaUsers = new ArrayList<Grupo>();
		listaUsers = (ArrayList<Grupo>) servicioGrupo.buscar(criterio);
		// cargamos la jsp
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("listaUsuarios", listaUsers);
		request.setAttribute("criterio", criterio);
		request.getRequestDispatcher(Constantes.VIEW_USER_SEARCH).forward(request, response);
	}

}
