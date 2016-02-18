package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends MasterServlet {

	private static final long serialVersionUID = 8772839050207508062L;
	private final static Logger LOG = Logger.getLogger(UsuarioServlet.class);

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

		LOG.trace("UsuarioServlet: deGet");
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
			LOG.trace("UsuarioServlet: se procede a hacer el dispatch");
			dispatch.forward(request, response);
		} catch (Exception e) {
			LOG.error("UsuarioServlet: " + e.getMessage());
		}
	}

	/**
	 * Nos lleva a la vista del formulario para crear una persona
	 *
	 * @param request
	 * @throws SQLException
	 */
	private void nuevo(HttpServletRequest request) throws SQLException {
		request.setAttribute("usuario", new Usuario());
		LOG.trace("UsuarioServlet: solicitud de nuevo usuario");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_USER_FORM);
	}

	private void listar(HttpServletRequest request) throws SQLException {
		request.setAttribute("listaUsuarios", serviceUsuario.listar());
		LOG.trace("UsuarioServlet: solicitud para listar usuarios");
		dispatch = request.getRequestDispatcher(Constantes.VIEW_USER_LIST);
	}

	private void detalle(HttpServletRequest request) throws NumberFormatException, SQLException {
		pId = request.getParameter("id");
		request.setAttribute("usuario", serviceUsuario.detalle(Integer.parseInt(pId)));
		LOG.trace("UsuarioServlet: solicitud para el detalle del usuario con id: " + pId);
		dispatch = request.getRequestDispatcher(Constantes.VIEW_USER_FORM);
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
		int id = Integer.parseInt(request.getParameter("id"));
		String pNick = request.getParameter("nick"), pEmail = request.getParameter("email"),
				pPass = request.getParameter("pass");

		Usuario usuario = new Usuario(id, pNick, pEmail, pPass);

		// persistir en la bbdd
		if (usuario.getId() == -1) {
			if (serviceUsuario.insertar(usuario)) {
				msj = new Mensaje("Usuario insertado con éxito", Mensaje.TIPO_SUCCESS);
				LOG.trace("UsuarioServlet: Se inserta el usuario: " + usuario.toString());
				session.setAttribute("userlogged", usuario);
			} else {
				msj = new Mensaje("No se ha insertado el usuario", Mensaje.TIPO_WARNING);
				LOG.error("UsuarioServlet: Error al intentar insertar usuario: " + usuario.toString());
			}
		} else if (serviceUsuario.modificar(usuario)) {
			session.setAttribute("userlogged", usuario);
			msj = new Mensaje("Registro modificado con éxito", Mensaje.TIPO_SUCCESS);
			LOG.trace("UsuarioServlet: Modificado el usuario: " + usuario.toString());
		} else {
			msj = new Mensaje("No se ha modificado el registro", Mensaje.TIPO_WARNING);
			LOG.error("UsuarioServlet: Error al modificar el usuario: " + usuario.toString());
		}
		// listar
		listar(request);
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario usuario = serviceUsuario.detalle(id);
			if (usuario.getId() != -1) {
				if (serviceUsuario.eliminar(usuario)) {
					msj = new Mensaje("Registro eliminado con éxito", Mensaje.TIPO_SUCCESS);
					LOG.trace("UsuarioServlet: Usuario: " + usuario.toString() + " eliminado");
				} else {
					msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
					LOG.error("UsuarioServlet: Error al intentar eliminar usuario: " + usuario.toString());
					listar(request);
				}
			} else {
				msj = new Mensaje("No existe un usuario con ese registro", Mensaje.TIPO_DANGER);
				LOG.error("UsuarioServlet: No existe el usuario: " + usuario.toString() + " en la base de datos");
				listar(request);
			}
		} catch (Exception e) {
			msj = new Mensaje("No se ha eliminado el registro", Mensaje.TIPO_DANGER);
			LOG.error("UsuarioServlet: " + e.getMessage());
			listar(request);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("UsuarioServlet: doPost, que llama al doGet");
		doGet(request, response);
	}
}