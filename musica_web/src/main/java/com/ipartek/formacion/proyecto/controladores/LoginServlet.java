package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.pojo.Usuario;
import com.ipartek.formacion.proyecto.service.UsuarioService;
import com.ipartek.formacion.proyecto.service.impl.UsuarioServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
public class LoginServlet extends HttpServlet {

	private final static Logger LOG = Logger.getLogger(LoginServlet.class);

	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatch;
	private static UsuarioService servicioUsuario;
	private static Mensaje msj;
	private HttpSession session;

	private Cookie cEmail;

	private Cookie cLastVisit;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		servicioUsuario = UsuarioServiceImpl.getSingleton();
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		servicioUsuario = null;
		msj = null;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(true);
		msj = null;
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			if (session.getAttribute("userlogged") == null) {

				String pEmail = request.getParameter("email");
				String pPass = request.getParameter("password");
				String pIdioma = request.getParameter("idioma");
				boolean recordar = (request.getParameter("recuerdame") == null ? false : true);
				Usuario userlogged = servicioUsuario.accede(pEmail, pPass);

				if (userlogged.equals(new Usuario())) {
					msj = new Mensaje("Credenciales no válidas", Mensaje.TIPO_DANGER);
					dispatch = request.getRequestDispatcher(Constantes.VIEW_LOGIN);
				} else {
					if (recordar) {
						cEmail = new Cookie("cEmail", pEmail);
						cLastVisit = new Cookie("cLastVisit", String.valueOf(System.currentTimeMillis()));
						cEmail.setMaxAge(60 * 60 * 24 * 7); // 7 dias

						response.addCookie(cLastVisit);
						LOG.debug("Cookie caduca " + cLastVisit.getMaxAge() + " segundos por defecto");
					} else {
						cEmail.setMaxAge(0);
					}
					response.addCookie(cEmail);
					// Guardar cookie del idioma
					Cookie cIdioma = new Cookie("cIdioma", pIdioma);
					response.addCookie(cIdioma);

					// Guardar en Session el Usuario
					session.setAttribute(Constantes.SESSION_USER_LOGGED, userlogged);
					session.setAttribute(Constantes.SESSION_USER_LANGUAGE, pIdioma);

					LOG.info(" logged: " + userlogged);
					dispatch = request.getRequestDispatcher(Constantes.VIEW_INDEX);
				}

				// Usuario ya esta logeado
			} else {
				dispatch = request.getRequestDispatcher(Constantes.VIEW_INDEX);
			}

		} catch (Exception e) {
			LOG.error("Excepcion en Login " + e.getMessage());
			dispatch = request.getRequestDispatcher(Constantes.VIEW_LOGIN);
		} finally {
			request.setAttribute("msj", msj);
			dispatch.forward(request, response);
		}
	}
}