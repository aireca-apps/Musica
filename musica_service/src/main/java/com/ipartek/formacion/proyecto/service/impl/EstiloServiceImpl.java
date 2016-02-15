/**
 *
 */
package com.ipartek.formacion.proyecto.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.EstiloDAO;
import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.service.EstiloService;

/**
 * @author Curso
 *
 */
public class EstiloServiceImpl implements EstiloService {

	private static EstiloService singleton = null;
	private EstiloDAO daoEstilo = null;

	/**
	 *
	 */
	private EstiloServiceImpl() {
		super();
		daoEstilo = new EstiloDAO();
	}

	public static EstiloService getSingleton() {
		if (singleton == null) {
			singleton = new EstiloServiceImpl();
		}
		return singleton;
	}

	@Override
	public List<Estilo> listar() {
		ArrayList<Estilo> list;
		try {
			list = (ArrayList<Estilo>) daoEstilo.getAll();
		} catch (SQLException e) {
			list = new ArrayList<Estilo>();
			// TODO Log
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertar(Estilo est) {
		boolean respuesta = false;
		try {
			if (daoEstilo.insert(est) != -1)
				respuesta = true;
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean eliminar(Estilo est) {
		boolean respuesta = false;
		try {
			if (est.getId() != -1)
				respuesta = daoEstilo.delete(est.getId());
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean modificar(Estilo est) {
		boolean respuesta = false;
		try {
			respuesta = daoEstilo.update(est);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public Estilo detalle(int id) {
		Estilo est;
		try {
			est = daoEstilo.getById(id);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			est = new Estilo();
		}
		return est;
	}

}
