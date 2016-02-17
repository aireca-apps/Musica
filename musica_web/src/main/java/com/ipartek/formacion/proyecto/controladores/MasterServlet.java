package com.ipartek.formacion.proyecto.controladores;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.proyecto.Constantes;
import com.ipartek.formacion.proyecto.service.EstiloService;
import com.ipartek.formacion.proyecto.service.GrupoService;
import com.ipartek.formacion.proyecto.service.UsuarioService;
import com.ipartek.formacion.proyecto.service.impl.EstiloServiceImpl;
import com.ipartek.formacion.proyecto.service.impl.GrupoServiceImpl;
import com.ipartek.formacion.proyecto.service.impl.UsuarioServiceImpl;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static GrupoService servicioGrupo;
	protected static EstiloService serviceEstilo;
	protected static UsuarioService serviceUsuario;
	protected static RequestDispatcher dispatch; // El que se encarga de
													// enrutar. Solo puede ir a
													// un sitio, no se puede
													// cargar dos veces
	protected static HttpSession session;
	protected ResourceBundle messages; // fichero de properties
	protected static Mensaje msj; // Mensaje a mostrar la usuario
	protected String idioma; // idioma session del usuario
	protected String language;
	protected String country;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		servicioGrupo = GrupoServiceImpl.getSingleton();
		serviceEstilo = EstiloServiceImpl.getSingleton();
		serviceUsuario = UsuarioServiceImpl.getSingleton();
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		servicioGrupo = null;
		serviceEstilo = null;
		serviceUsuario = null;
		dispatch = null;
		session = null;
		msj = null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			msj = null;
			session = request.getSession();
			idioma = (String) session.getAttribute(Constantes.SESSION_USER_LANGUAGE);
			language = idioma.split("_")[0];
			country = idioma.split("_")[1];
			messages = null;
			messages = ResourceBundle.getBundle("i18nmesages", new Locale(language, country));
		} catch (Exception e) {
		}
		super.service(request, response);
	}

}
