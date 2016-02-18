package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Estilo;

/**
 * Servlet implementation class TablaAuxiliarServlet
 */
public class EstiloServlet extends MasterServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EstiloServlet.class);

	private static final String VIEW_LIST = "/pages/estilo/estiloList.jsp",
			VIEW_FORM = "/pages/estilo/estiloDetalle.jsp";
	private static int operacion;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operacion = Constantes.OP_LISTAR;
		LOG.trace("EstiloServlet: service");
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("EstiloServlet: doGet, llama al doProcess");
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			LOG.trace("EstiloServlet: doProcess");
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
			LOG.error("EstiloServlet: " + e.getMessage());
			msj = new Mensaje("Excepción: " + e.getMessage(), Mensaje.TIPO_DANGER);
			dispatch = request.getRequestDispatcher(VIEW_LIST);
		} finally {
			request.setAttribute("msj", msj);
			LOG.trace("EstiloServlet: Se procede a realizar el dispatch");
			dispatch.forward(request, response);
		}
	}

	private void modificarCrear(HttpServletRequest request) throws SQLException {
		LOG.trace("EstiloServlet: modificarCrear");
		// recoger parámetros formulario
		int id = Integer.parseInt(request.getParameter("id"));
		String pNombre = request.getParameter("nombre"), pDescripcion = request.getParameter("descripcion"),
				pCodigo = request.getParameter("codigo");

		// construir estilo
		Estilo estilo = new Estilo(id, pNombre, pDescripcion, pCodigo);
		// persistir en la bbdd
		if (estilo.getId() == -1) {
			if (serviceEstilo.insertar(estilo)) {
				msj = new Mensaje("Estilo insertado con éxito", Mensaje.TIPO_SUCCESS);
				LOG.trace("EstiloServlet: Estilo " + estilo.toString() + " insertado con éxito");
			} else {
				msj = new Mensaje("No se ha insertado el estilo", Mensaje.TIPO_WARNING);
				LOG.error("EstiloServlet: No se ha insertado el estilo " + estilo.toString());
			}
		} else if (serviceEstilo.modificar(estilo)) {
			msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
			LOG.trace("EstiloServlet: Estilo " + estilo.toString() + " modificado con éxito");
		} else {
			msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
			LOG.error("EstiloServlet: No se ha modificado el estilo " + estilo.toString());
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		LOG.trace("EstiloServlet: eliminar");
		int id = Integer.parseInt(request.getParameter("id"));
		Estilo est = serviceEstilo.detalle(id);
		if (est.getId() != -1) {
			if (serviceEstilo.eliminar(est)) {
				msj = new Mensaje("Registro eliminado con éxito", Mensaje.TIPO_SUCCESS);
				LOG.trace("EstiloServlet: Estilo " + est.toString() + " eliminado con éxito");
			} else {
				msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
				LOG.error("EstiloServlet: No se ha eliminado el estilo " + est.toString());
			}
		} else {
			msj = new Mensaje("No existe un estilo con ese registro", Mensaje.TIPO_DANGER);
			LOG.error("EstiloServlet: No existe el estilo " + est.toString());
		}
		listar(request);
	}

	private void nuevo(HttpServletRequest request) {
		request.setAttribute("estilo", new Estilo());
		LOG.trace("EstiloServlet: Solicitud de nuevo estilo");
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		String pId = request.getParameter("id");
		Estilo estilo = serviceEstilo.detalle(Integer.parseInt(pId));
		LOG.trace("EstiloServlet: Solicitud de estilo " + estilo.toString());
		request.setAttribute("estilo", estilo);
		dispatch = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {
		request.setAttribute("lista", serviceEstilo.listar());
		LOG.trace("EstiloServlet: Solicitud para listar todos los estilos");
		dispatch = request.getRequestDispatcher(VIEW_LIST);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("EstiloServlet: doPost llama a doProcess");
		doProcess(request, response);
	}
}
