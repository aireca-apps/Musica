package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

/**
 * Servlet implementation class UsuarioServlet
 */
public class GrupoServlet extends MasterServlet {

	private static final long serialVersionUID = 8772839050207508062L;
	private final static Logger LOG = Logger.getLogger(GrupoServlet.class);

	private static String pId; // Parámetro identificador del grupo, aunque
								// sea un id, es un string, luego se parsea
	private static int operacion;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("GrupoServlet: doGet");
		try {
			// recoger parámetros a realizar
			if (request.getParameter("op") != null)
				operacion = Integer.parseInt(request.getParameter("op"));
			else
				operacion = Constantes.OP_LISTAR;

			// Realizar accion
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
			request.setAttribute("msj", msj);
			LOG.trace("GrupoServlet: Se procede a realizar el dispatch");

			dispatch.forward(request, response);
		} catch (Exception e) {
			LOG.error("GrupoServlet: " + e.getMessage());
		}
	}

	/**
	 * Modifica o crea una nueva persona
	 *
	 * @param request
	 * @throws ParseException
	 * @throws SQLException
	 */
	private void modificarCrear(HttpServletRequest request) throws ParseException, SQLException {
		LOG.trace("GrupoServlet: modificarCrear");
		// recoger parámetros formulario
		int id = Integer.parseInt(request.getParameter("id")),
				estiloId = Integer.parseInt(request.getParameter("estilo"));
		String pNombre = request.getParameter("nombre"), pComponentes = request.getParameter("componentes"),
				pFechaInicio = request.getParameter("fechaInicio"), pFechaFin = request.getParameter("fechaFin");

		Estilo estilo = serviceEstilo.detalle(estiloId);
		if (estilo.getId() != -1) {
			// construir grupo
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = format.parse(pFechaInicio);
			Grupo gru;
			if (pFechaFin != "") {
				java.util.Date parsed2 = format.parse(pFechaFin);
				gru = new Grupo(id, pNombre, pComponentes, new java.sql.Date(parsed.getTime()),
						new java.sql.Date(parsed2.getTime()), serviceEstilo.detalle(estiloId));
			} else {
				gru = new Grupo(id, pNombre, pComponentes, new java.sql.Date(parsed.getTime()), null,
						serviceEstilo.detalle(estiloId));
			}
			// persistir en la bbdd
			if (gru.getId() == -1) {
				if (servicioGrupo.insertar(gru)) {
					msj = new Mensaje("Grupo insertado con éxito", Mensaje.TIPO_SUCCESS);
					LOG.trace("GrupoServlet: Grupo " + gru.toString() + " insertado con éxito");
				} else {
					msj = new Mensaje("No se ha insertado el grupo", Mensaje.TIPO_WARNING);
					LOG.error("GrupoServlet: Grupo " + gru.toString() + " no insertado");
				}
			} else if (servicioGrupo.modificar(gru)) {
				msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
				LOG.trace("GrupoServlet: Grupo " + gru.toString() + " modificado con éxito");
			} else {
				msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
				LOG.error("GrupoServlet: Grupo " + gru.toString() + " no modificado");
			}
		} else {
			msj = new Mensaje("No existe dicho grupo", Mensaje.TIPO_WARNING);
			LOG.error("No existe dicho grupo");
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Grupo gru = servicioGrupo.detalle(id);
			if (gru.getId() != -1) {
				if (servicioGrupo.eliminar(gru)) {
					msj = new Mensaje("Registro eliminado con éxito", Mensaje.TIPO_SUCCESS);
					LOG.trace("GrupoServlet: Grupo " + gru.toString() + " eliminado con éxito");
				} else {
					msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
					LOG.error("GrupoServlet: Grupo " + gru.toString() + " no eliminado");
				}
			} else {
				msj = new Mensaje("No existe un grupo con ese registro", Mensaje.TIPO_DANGER);
				LOG.error("No existe dicho grupo");
			}
		} catch (Exception e) {
			msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
			LOG.error("GrupoServlet: " + e.getMessage());
		}
		listar(request);
	}

	/**
	 * Nos lleva a la vista del formulario para crear una persona
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void nuevo(HttpServletRequest request) throws SQLException {
		request.setAttribute("grupo", new Grupo());
		ArrayList<Estilo> estilos = (ArrayList<Estilo>) serviceEstilo.listar();
		request.setAttribute("estilos", estilos);
		LOG.trace("GrupoServlet: Solicitud de nuevo estilo");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {
		request.setAttribute("listaGrupos", servicioGrupo.listar());
		LOG.trace("GrupoServlet: Solicitud para listar todos los estilos");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_LIST);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		pId = request.getParameter("id");
		request.setAttribute("grupo", servicioGrupo.detalle(Integer.parseInt(pId)));
		ArrayList<Estilo> estilos = (ArrayList<Estilo>) serviceEstilo.listar();
		request.setAttribute("estilos", estilos);
		LOG.trace("GrupoServlet: Solicitud de estilo ");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_FORM);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("GrupoServlet: doPost llama a doGet");
		doGet(request, response);
	}

}