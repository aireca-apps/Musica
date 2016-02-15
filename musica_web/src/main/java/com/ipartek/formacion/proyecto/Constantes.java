package com.ipartek.formacion.proyecto;

/**
 * Variables Globales para nuestra app
 *
 * @author ur00
 *
 */
public final class Constantes {

	/**
	 * Constructor por defecto
	 */
	private Constantes() {
		super();
	}

	// Generales
	public static final String APP_NAME = "TuMusica";
	// public static final String SERVER = "http://localhost:8080/";
	public static final String ROOT = /* SERVER + */"/" + APP_NAME + "/";

	// Variables session
	public static final String SESSION_USER_LOGGED = "userlogged";
	public static final String SESSION_USER_LANGUAGE = "userLanguage";

	// OPERACIONES CONTROLADORES
	public static final int OP_NUEVO = 0;
	public static final int OP_DETALLE = 1;
	public static final int OP_LISTAR = 2;
	public static final int OP_MODIFICAR = 3;
	public static final int OP_ELIMINAR = 4;

	// CONTROLADORES
	public static final String CONTROLLER_LOGIN = "loginUser";
	public static final String CONTROLLER_LOGOUT = "logout";

	public static final String CONTROLLER_USUARIOS_LOGEADOS = "musica/loggeduser";

	public static final String CONTROLLER_GRUPOS = "musica/grupos";
	public static final String CONTROLLER_ESTILOS = "musica/estilos";
	public static final String CONTROLLER_SEARCH = "musica/search";

	// VISTAS
	public static final String VIEW_LOGIN = "login.jsp";
	public static final String VIEW_INDEX = "index.jsp";

	public static final String VIEW_GRUPO_LIST = "/pages/grupo/grupos.jsp";
	public static final String VIEW_GRUPO_FORM = "/pages/grupo/grupoDetalle.jsp";
	public static final String VIEW_GRUPO_SEARCH = "/pages/searchResult.jsp";

}
