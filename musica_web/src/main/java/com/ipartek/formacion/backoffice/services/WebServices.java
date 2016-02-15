package com.ipartek.formacion.backoffice.services;

import java.sql.SQLException;
import java.util.List;

import com.ipartek.formacion.backoffice.listeners.DAOListener;
import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;

public class WebServices {

	private PersonaDAO daoPersona = DAOListener.daoPersona;
	private final int keyApi = 1234;

	public Persona[] listar(int keyApi) throws SQLException {
		List<Persona> personas = null;
		if (this.keyApi == keyApi) {
			personas = daoPersona.getAll();
		}
		return personas.toArray(new Persona[personas.size()]);
	}

	public Persona detalle(int idPersona, int keyApi) throws SQLException {
		Persona persona = new Persona();
		if (this.keyApi == keyApi) {
			persona = daoPersona.getById(idPersona);
		}
		return persona;
	}
}
