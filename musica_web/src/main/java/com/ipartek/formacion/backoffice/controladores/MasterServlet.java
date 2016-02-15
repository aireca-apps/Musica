package com.ipartek.formacion.backoffice.controladores;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.backoffice.Constantes;
import com.ipartek.formacion.backoffice.listeners.DAOListener;
import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.modelo.TablaAuxiliarDAO;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//DAOs
	protected static PersonaDAO daoPersona;
	protected static TablaAuxiliarDAO daoRol;		
	protected static RequestDispatcher dispatch; // El que se encarga de enrutar. Solo puede ir a un sitio, no se puede cargar dos veces	
	protected static HttpSession session;	
	protected ResourceBundle messages; //fichero de properties
	protected static Mensaje msj; //Mensaje a mostrar la usuario
	protected String idioma; //idioma session del usuario
	protected String language; 
	protected String country; 

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoPersona = DAOListener.daoPersona;
		daoRol = DAOListener.daoRol;
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		daoPersona = null;
		daoRol = null;
		dispatch = null;
		session = null;
		msj = null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");			
		msj = null;
		session = request.getSession();
		idioma = (String)session.getAttribute( Constantes.SESSION_USER_LANGUAGE );
		language = idioma.split("_")[0];
		country = idioma.split("_")[1];		
		messages = null;
		messages = ResourceBundle.getBundle("i18nmesages", new Locale(language, country ));
		super.service(request, response);
	}

}
