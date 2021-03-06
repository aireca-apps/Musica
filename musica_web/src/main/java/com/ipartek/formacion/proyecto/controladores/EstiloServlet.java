package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Estilo;

/**
 * Servlet implementation class TablaAuxiliarServlet
 */
public class EstiloServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW_LIST = "/pages/estilo/estiloList.jsp",
			VIEW_FORM = "/pages/estilo/estiloDetalle.jsp";
	private static int operacion;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operacion = Constantes.OP_LISTAR;
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		} finally {
			request.setAttribute("msj", msj);
			dispatch.forward(request, response);
		}
	}

	private void modificarCrear(HttpServletRequest request) throws SQLException {
		// recoger parámetros formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String pNombre = request.getParameter("nombre"), pDescripcion = request.getParameter("descripcion"),
				pCodigo = request.getParameter("codigo");

		// construir estilo
		Estilo estilo = new Estilo(id, pNombre, pDescripcion, pCodigo);
		// persistir en la bbdd
		if (estilo.getId() == -1)
			if (serviceEstilo.insertar(estilo))
				msj = new Mensaje("Estilo insertado con éxito", Mensaje.TIPO_SUCCESS);
			else
				msj = new Mensaje("No se ha insertado el estilo", Mensaje.TIPO_WARNING);
		else if (serviceEstilo.modificar(estilo)) {
			msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
		} else {
			msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Estilo est = serviceEstilo.detalle(id);
		if (est.getId() != -1) {
			if (serviceEstilo.eliminar(est)) {
				msj = new Mensaje("Registro eliminado con éxito", Mensaje.TIPO_SUCCESS);
			} else {
				msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
			}
		} else {
			msj = new Mensaje("No existe un estilo con ese registro", Mensaje.TIPO_DANGER);
		}
		listar(request);
	}

	private void nuevo(HttpServletRequest request) {
		request.setAttribute("estilo", new Estilo());
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		String pId = request.getParameter("id");
		Estilo estilo = serviceEstilo.detalle(Integer.parseInt(pId));
		request.setAttribute("estilo", estilo);
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {
		request.setAttribute("lista", serviceEstilo.listar());
		dispatch = request.getRequestDispatcher(VIEW_LIST);
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
}
