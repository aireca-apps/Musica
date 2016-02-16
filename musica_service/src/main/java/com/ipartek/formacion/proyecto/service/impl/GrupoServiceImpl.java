package com.ipartek.formacion.proyecto.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.GrupoDAO;
import com.ipartek.formacion.proyecto.pojo.Grupo;
import com.ipartek.formacion.proyecto.service.GrupoService;

public class GrupoServiceImpl implements GrupoService {

	private static GrupoService singleton = null;
	private GrupoDAO daoGrupo = null;

	/**
	 * Constructor privado para patrón Singleton
	 */
	private GrupoServiceImpl() {
		super();
		daoGrupo = new GrupoDAO();
	}

	/**
	 * Patrón Singleton para que solo se pueda crear unobjeto de esta clase
	 *
	 * @return
	 */
	public static GrupoService getSingleton() {
		if (singleton == null) {
			singleton = new GrupoServiceImpl();
		}
		return singleton;
	}

	@Override
	public List<Grupo> listar() {
		ArrayList<Grupo> list;
		try {
			list = (ArrayList<Grupo>) daoGrupo.getAll();
		} catch (SQLException e) {
			list = new ArrayList<Grupo>();
			// TODO Log
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertar(Grupo grup) {
		boolean respuesta = false;
		try {
			if (daoGrupo.insert(grup) != -1)
				respuesta = true;
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean eliminar(Grupo gru) {
		boolean respuesta = false;
		try {
			if (gru.getId() != -1)
				respuesta = daoGrupo.delete(gru.getId());
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public boolean modificar(Grupo grup) {
		boolean respuesta = false;
		try {
			respuesta = daoGrupo.update(grup);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	public List<Grupo> buscar(String criterio) {
		List<Grupo> listado;
		try {
			listado = daoGrupo.searchGrupos(criterio);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			listado = new ArrayList<Grupo>();
		}
		return listado;
	}

	@Override
	public Grupo detalle(int id) {
		Grupo gru;
		try {
			gru = daoGrupo.getById(id);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			gru = new Grupo();
		}
		return gru;
	}

	@Override
	public Grupo accede(String email, String pass) {
		Grupo gru;
		try {
			gru = daoGrupo.login(email, pass);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			gru = new Grupo();
		}
		return gru;
	}

}
