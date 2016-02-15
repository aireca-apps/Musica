package com.ipartek.formacion.backoffice.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.backoffice.Constantes;
import com.ipartek.formacion.backoffice.pojo.Rol;

/**
 * Servlet implementation class TablaAuxiliarServlet
 */
public class TablaAuxiliarServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_LIST = "/pages/auxiliar/auxiliarList.jsp",
								VIEW_FORM = "/pages/auxiliar/auxiliarForm.jsp";
	private static int operacion;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operacion = Constantes.OP_LISTAR;
		super.service(request, response);
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			if (request.getParameter("op") != null)
				operacion = Integer.parseInt(request.getParameter("op"));

			switch (operacion) {
			case Constantes.OP_LISTAR:
				listar(request);
				break;
			case Constantes.OP_DETALLE:
				detalle(request);
				break;
			case Constantes.OP_NUEVO:
				nuevo(request);
				break;
			case Constantes.OP_ELIMINAR:
				eliminar(request);
				break;
			case Constantes.OP_MODIFICAR:
				modificarCrear(request);
				break;
			}
			
		} catch (Exception e) {
			// TODO mejor en un LOG
			e.printStackTrace();
			msj = new Mensaje("Excepción: " + e.getMessage(), Mensaje.TIPO_DANGER);
			dispatch = request.getRequestDispatcher(VIEW_LIST);
			// TODO ir a página error 404.jsp o 500.jsp
		}finally{
			request.setAttribute("msj", msj);
			dispatch.forward(request, response);
		}
	}

	private void modificarCrear(HttpServletRequest request) throws SQLException {
		// recoger parámetros formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String pNombre = request.getParameter("nombre"), 
				pDescripcion = request.getParameter("descripcion"),
				pCodigo = request.getParameter("codigo");

		// construir rol
		Rol pojo = new Rol(pNombre);
		pojo.setId(id);
		pojo.setDescripcion(pDescripcion);
		pojo.setCodigo(pCodigo);
		
		// persistir en la bbdd
		if (pojo.getId() == -1)
			if (daoRol.insert(pojo) != -1)
				msj = new Mensaje("Rol insertado con éxito", Mensaje.TIPO_SUCCESS);
			else
				msj = new Mensaje("No se ha insertado el rol", Mensaje.TIPO_WARNING);
		else if (daoRol.update(pojo)) {
			msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
		} else {
			msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (daoRol.delete(id)) {
			msj = new Mensaje("Registro eliminado con éxito", Mensaje.TIPO_SUCCESS);
		} else {
			msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
		}
		// listar
				listar(request);
	}

	private void nuevo(HttpServletRequest request) {
		Rol pojo = new Rol("");
		request.setAttribute("pojo", pojo);
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		String pId = request.getParameter("id");
		Rol pojo = daoRol.getById(Integer.parseInt(pId));
		request.setAttribute("pojo", pojo);
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {
		ArrayList<Rol> lista = (ArrayList<Rol>) daoRol.getAll();
		request.setAttribute("lista", lista);
		dispatch = request.getRequestDispatcher(VIEW_LIST);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
