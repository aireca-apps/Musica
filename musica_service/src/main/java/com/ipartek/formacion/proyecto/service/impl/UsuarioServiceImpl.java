/**
 *
 */
package com.ipartek.formacion.proyecto.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.UsuarioDAO;
import com.ipartek.formacion.proyecto.pojo.Usuario;
import com.ipartek.formacion.proyecto.service.UsuarioService;

/**
 * @author Curso
 *
 */
public class UsuarioServiceImpl implements UsuarioService {

	private static UsuarioService singleton = null;
	private UsuarioDAO daoUsuario = null;

	/**
	 *
	 */
	private UsuarioServiceImpl() {
		super();
		daoUsuario = new UsuarioDAO();
	}

	public static UsuarioService getSingleton() {
		if (singleton == null) {
			singleton = new UsuarioServiceImpl();
		}
		return singleton;
	}

	@Override
	public Usuario accede(String email, String pass) {
		Usuario usu = null;
		try {
			usu = daoUsuario.login(email, pass);
		} catch (SQLException e) {
			usu = new Usuario();
		}
		return usu;
	}

	@Override
	public boolean comprobar(String campo, String valor) {
		boolean result;
		try {
			result = daoUsuario.check(campo, valor);
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

	@Override
	public List<Usuario> listar() {
		ArrayList<Usuario> list;
		try {
			list = (ArrayList<Usuario>) daoUsuario.getAll();
		} catch (SQLException e) {
			list = new ArrayList<Usuario>();
			// TODO Log
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertar(Usuario usu) {
		boolean respuesta = false;
		try {
			if (daoUsuario.insert(usu) != -1)
				respuesta = true;
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean eliminar(Usuario usu) {
		boolean respuesta = false;
		try {
			if (usu.getId() != -1)
				respuesta = daoUsuario.delete(usu.getId());
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean modificar(Usuario usu) {
		boolean respuesta = false;
		try {
			respuesta = daoUsuario.update(usu);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public Usuario detalle(int id) {
		Usuario usu;
		try {
			usu = daoUsuario.getById(id);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			usu = new Usuario();
		}
		return usu;
	}
}
