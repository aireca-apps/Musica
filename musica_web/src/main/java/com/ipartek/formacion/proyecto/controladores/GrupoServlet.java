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

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

/**
 * Servlet implementation class UsuarioServlet
 */
public class GrupoServlet extends MasterServlet {

	private static final long serialVersionUID = 8772839050207508062L;

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
			/*
			 * forward para servir la jsp (lanzarlo). en forward hay que poner
			 * dos argumentos. doGet tiene dos request y response, al ser una
			 * petición interna, hay que poner los mismos
			 */
			dispatch.forward(request, response);
		} catch (Exception e) {
			// TODO mejor en un LOG
			e.printStackTrace();

			// TODO ir a página error 404.jsp o 500.jsp
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

		// recoger parámetros formulario
		int id = Integer.parseInt(request.getParameter("id")),
				estiloId = Integer.parseInt(request.getParameter("estilo"));
		String pNombre = request.getParameter("nombre"), pDni = request.getParameter("dni"),
				pPass = request.getParameter("pass"), pEmail = request.getParameter("email"),
				pObservaciones = request.getParameter("observaciones"), pFecha = request.getParameter("fecha");

		Estilo estilo = serviceEstilo.detalle(estiloId);
		if (estilo.getId() != -1) {
			// construir persona
			Grupo per = new Grupo();
			per.setId(id);
			per.setNombre(pNombre);
			per.setDni(pDni);
			per.setPass(pPass);
			per.setEmail(pEmail);
			per.setObservaciones(pObservaciones);
			per.setEstilo(estilo);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = format.parse(pFecha);
			per.setFechaNacimiento(new java.sql.Date(parsed.getTime()));
			// persistir en la bbdd
			if (per.getId() == -1)
				if (servicioGrupo.insertar(per))
					msj = new Mensaje("Grupo insertado con éxito", Mensaje.TIPO_SUCCESS);
				else
					msj = new Mensaje("No se ha insertado el grupo", Mensaje.TIPO_WARNING);
			else if (servicioGrupo.modificar(per)) {
				msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
			} else {
				msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
			}
		} else {
			msj = new Mensaje("No existe dicho estilo", Mensaje.TIPO_WARNING);
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
				} else {
					msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
				}
			} else {
				msj = new Mensaje("No existe un grupo con ese registro", Mensaje.TIPO_DANGER);
			}
		} catch (Exception e) {
			msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
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
		Grupo p = new Grupo();
		request.setAttribute("persona", p);
		ArrayList<Estilo> estilos = (ArrayList<Estilo>) serviceEstilo.listar();
		request.setAttribute("estilos", estilos);
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {

		// Guardar listado (se obtiene del servicio) como atributo en request
		request.setAttribute("listaGrupos", servicioGrupo.listar());

		// Petición interna a la jsp (RequestDistapecher es para decirle a donde
		// tiene que ir, se carga el dispatcher)
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_LIST);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		pId = request.getParameter("id");
		request.setAttribute("persona", servicioGrupo.detalle(Integer.parseInt(pId)));
		ArrayList<Estilo> estilos = (ArrayList<Estilo>) serviceEstilo.listar();
		request.setAttribute("estilos", estilos);
		dispatch = request.getRequestDispatcher(Constantes.VIEW_GRUPO_FORM);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}