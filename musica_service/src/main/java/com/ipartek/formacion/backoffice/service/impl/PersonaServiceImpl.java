package com.ipartek.formacion.backoffice.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;
import com.ipartek.formacion.backoffice.service.PersonaService;

public class PersonaServiceImpl implements PersonaService {

	private static PersonaService singleton = null;
	private PersonaDAO daoPersona = null;

	/**
	 * Constructor privado para patrón Singleton
	 */
	private PersonaServiceImpl() {
		super();
		daoPersona = new PersonaDAO();
	}

	/**
	 * Patrón Singleton para que solo se pueda crear unobjeto de esta clase
	 *
	 * @return
	 */
	public static PersonaService getSingleton() {
		if (singleton == null) {
			singleton = new PersonaServiceImpl();
		}
		return singleton;
	}

	@Override
	public List<Persona> listar() {
		ArrayList<Persona> list;
		try {
			list = (ArrayList<Persona>) daoPersona.getAll();
		} catch (SQLException e) {
			list = new ArrayList<Persona>();
			// TODO Log
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertar(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Persona> buscar(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona detalle(int id) {
		Persona p;
		try {
			p = daoPersona.getById(id);
		} catch (SQLException e) {
			// TODO log
			e.printStackTrace();
			p = new Persona();
		}
		return p;
	}

}
