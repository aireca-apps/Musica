package com.ipartek.formacion.proyecto.service;

import java.util.List;

import com.ipartek.formacion.proyecto.pojo.Usuario;

public interface UsuarioService {

	Usuario accede(String email, String pass);

	boolean comprobar(String campo, String valor);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code List<Persona>} listado personas, si no existe lista vacía
	 */
	List<Usuario> listar();

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se inserte, false en caso
	 *         contrario
	 */
	boolean insertar(Usuario usu);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se elimine, false en caso
	 *         contrario
	 */
	boolean eliminar(Usuario usu);

	/**
	 * Colección de Personas, limitado a las últimas 500
	 *
	 * @return @{code boolean} true en caso de que se modifique, false en caso
	 *         contrario
	 */
	boolean modificar(Usuario usu);

	/**
	 * TODO acabar la descripcion
	 *
	 * @param id
	 * @return
	 */
	Usuario detalle(int id);

}
