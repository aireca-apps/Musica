package com.ipartek.formacion.proyecto.service;

import java.util.List;

import com.ipartek.formacion.proyecto.pojo.Grupo;

public interface GrupoService {

	String accede(String email, String pass);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code List<Persona>} listado personas, si no existe lista vacía
	 */
	List<Grupo> listar();

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se inserte, false en caso
	 *         contrario
	 */
	boolean insertar(Grupo gru);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se elimine, false en caso
	 *         contrario
	 */
	boolean eliminar(Grupo gru);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se modifique, false en caso
	 *         contrario
	 */
	boolean modificar(Grupo gru);

	/**
	 * Busca persona por cualquier propiedad
	 *
	 * @param criterio
	 *            {@code String} cadena alfanumérica, no perite expresiones
	 *            regulares
	 * @return @{code List<Persona>} listado personas que coincidan con el
	 *         criterio, si no existe lista vacía
	 */
	List<Grupo> buscar(String criterio);

	/**
	 * TODO acabar la descripcion
	 *
	 * @param id
	 * @return
	 */
	Grupo detalle(int id);
}
