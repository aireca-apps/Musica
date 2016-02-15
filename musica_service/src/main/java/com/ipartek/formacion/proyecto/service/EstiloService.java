package com.ipartek.formacion.proyecto.service;

import java.util.List;

import com.ipartek.formacion.proyecto.pojo.Estilo;

public interface EstiloService {

	/**
	 * Colección de Estilos
	 *
	 * @return @{code List<Estilo>} listado estilos, si no existe lista vacía
	 */
	List<Estilo> listar();

	/**
	 * Colección de Estilos, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se inserte, false en caso
	 *         contrario
	 */
	boolean insertar(Estilo est);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se elimine, false en caso
	 *         contrario
	 */
	boolean eliminar(Estilo est);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se modifique, false en caso
	 *         contrario
	 */
	boolean modificar(Estilo est);

	/**
	 * TODO acabar la descripcion
	 *
	 * @param id
	 * @return
	 */
	Estilo detalle(int id);

}
