package com.ipartek.formacion.backoffice.service;

import java.util.List;

import com.ipartek.formacion.backoffice.pojo.Persona;

public interface PersonaService {

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code List<Persona>} listado personas, si no existe lista vacía
	 */
	List<Persona> listar();

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se inserte, false en caso
	 *         contrario
	 */
	boolean insertar(Persona p);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se elimine, false en caso
	 *         contrario
	 */
	boolean eliminar(Persona p);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se modifique, false en caso
	 *         contrario
	 */
	boolean modificar(Persona p);

	/**
	 * Busca persona por cualquier propiedad
	 *
	 * @param criterio
	 *            {@code String} cadena alfanumérica, no perite expresiones
	 *            regulares
	 * @return @{code List<Persona>} listado personas que coincidan con el
	 *         criterio, si no existe lista vacía
	 */
	List<Persona> buscar(String criterio);

	/**
	 * TODO acabar la descripcion
	 * 
	 * @param id
	 * @return
	 */
	Persona detalle(int id);
}
