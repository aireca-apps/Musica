package com.ipartek.formacion.backoffice;

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
	public static final String APP_NAME = "BackofficeWar";
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

	public static final String CONTROLLER_USUARIOS_LOGEADOS = "back/loggeduser";

	public static final String CONTROLLER_USUARIOS = "back/usuarios";
	public static final String CONTROLLER_ROLES = "back/roles";
	public static final String CONTROLLER_SEARCH = "back/search";

	// VISTAS
	public static final String VIEW_LOGIN = "login.jsp";
	public static final String VIEW_INDEX = "index.jsp";

	public static final String VIEW_USER_LIST = "/pages/users/usuarios.jsp";
	public static final String VIEW_USER_FORM = "/pages/users/usuarioDetalle.jsp";
	public static final String VIEW_USER_SEARCH = "/pages/searchResult.jsp";

}
