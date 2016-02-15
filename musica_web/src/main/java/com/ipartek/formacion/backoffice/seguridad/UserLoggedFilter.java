package com.ipartek.formacion.backoffice.seguridad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.backoffice.Constantes;
import com.ipartek.formacion.backoffice.controladores.Mensaje;
import com.ipartek.formacion.backoffice.pojo.Persona;

/**
 * Servlet Filter implementation class UserLoggedFilter
 */
public class UserLoggedFilter implements Filter {

	private final static Logger LOG = Logger.getLogger(UserLoggedFilter.class);

	private FilterConfig config;
	private String loginView;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		this.loginView = this.config.getInitParameter("excludeLogin");
		LOG.trace("init");
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.config = null;
		LOG.trace("destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOG.debug("doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// recoger url solicitada
		String urlSolicitada = req.getRequestURL().toString();

		LOG.debug("urlSolicitada: " + urlSolicitada);

		// comprobar si es pagina a exluir del filtro
		if (!urlSolicitada.contains(this.loginView)) {

			// Comprobar si usuario loggeado
			Persona userLogged = (Persona) session.getAttribute("userlogged");
			if (userLogged == null) {

				// guardar informacion de la peticion
				LOG.warn("Intento de entrar si logeo");
				LOG.warn("IP: " + req.getRemoteAddr());
				LOG.warn("UserAgent: " + req.getHeader("User-Agent"));

				Mensaje msj = new Mensaje();
				msj.setTexto("Necesitas tener una session activa");
				session.setAttribute("msj", msj);
				// Redirect a login
				res.sendRedirect(Constantes.ROOT + Constantes.VIEW_LOGIN);

				// usuario esta logeado
			} else {
				chain.doFilter(request, response);
			}

			// estamos en login.jsp
		} else {
			chain.doFilter(request, response);
		}

	}

}
