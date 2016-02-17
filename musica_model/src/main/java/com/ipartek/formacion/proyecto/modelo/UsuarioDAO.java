/**
 *
 */
package com.ipartek.formacion.proyecto.modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.ia.AbstractPersistible;
import com.ipartek.formacion.proyecto.pojo.Usuario;

/**
 * @author Curso
 *
 */
public class UsuarioDAO extends AbstractPersistible<Usuario> {

	public Usuario login(String email, String pass) throws SQLException {
		Usuario resultado = new Usuario();
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidi
		String sql = "{call login (?, ?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setString(1, email);
		consulta.setString(2, pass);
		ResultSet rs = consulta.executeQuery();
		if (rs.next()) {
			resultado = mapeo(rs);
		}
		AbstractPersistible.cerrarConsulta(rs, conn, consulta);
		return resultado;
	}

	public boolean check(String campo, String valor) throws SQLException {
		boolean resultado = false;
		DbConnection conn = new DbConnection();
		String sql = "select * from `usuario` where " + campo + "= ?;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setString(1, valor);
		ResultSet rs = consulta.executeQuery();
		if (rs.next()) {
			resultado = true;
		}
		cerrarConsulta(rs, conn, consulta);
		return resultado;
	}

	@Override
	public List<Usuario> getAll() throws SQLException {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		DbConnection conn = new DbConnection();
		String sql = "select id, nick, email, pass from `usuario` order by `id` desc limit 500;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		ResultSet rs = consulta.executeQuery();
		while (rs.next()) {
			lista.add(mapeo(rs));
		}
		cerrarConsulta(rs, conn, consulta);
		return lista;
	}

	@Override
	public Usuario getById(int id) throws SQLException {
		Usuario usu = new Usuario();
		DbConnection conn = new DbConnection();
		String sql = "select id, nick, email, pass from `usuario` where id = ? ;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		ResultSet rs = consulta.executeQuery();
		if (rs.next()) {
			usu = mapeo(rs);
		}
		cerrarConsulta(rs, conn, consulta);
		return usu;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean resul = false;
		DbConnection conn = new DbConnection();
		String sql = "DELETE FROM `usuario` WHERE  `id`= ? ;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		if (consulta.executeUpdate() == 1) {
			resul = true;
		}
		cerrarPeticion(conn, consulta);
		return resul;
	}

	@Override
	public boolean update(Usuario usu) throws SQLException {
		boolean resul = false;
		if ((usu != null) && !usu.equals(new Usuario())) {
			DbConnection conn = new DbConnection();
			String sql = "UPDATE `usuario` SET `nick`= ? , `email`= ?, `pass`= ? WHERE  `id`= ? ;";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
			consulta.setString(1, usu.getNick());
			consulta.setString(2, usu.getEmail());
			consulta.setString(3, usu.getPass());
			consulta.setInt(4, usu.getId());
			if (consulta.executeUpdate() == 1) {
				resul = true;
			}
			cerrarPeticion(conn, consulta);
		}
		return resul;
	}

	@Override
	public int insert(Usuario usu) throws SQLException {
		int resul = -1;
		if ((usu != null) && !usu.equals(new Usuario())) {
			DbConnection conn = new DbConnection();
			String sql = "INSERT INTO `usuario` (`nick`,`email`, `pass` ) VALUES ( ?, ?, ? );";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			consulta.setString(1, usu.getNick());
			consulta.setString(2, usu.getEmail());
			consulta.setString(3, usu.getPass());
			if (consulta.executeUpdate() == 1) {
				ResultSet generatedKeys = consulta.getGeneratedKeys();
				if (generatedKeys.next()) {
					resul = generatedKeys.getInt(1);
					usu.setId(resul);
				}
			}
			cerrarPeticion(conn, consulta);
		}
		return resul;
	}

	/**
	 * Mapear ResulSet a un Pojo
	 *
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Usuario mapeo(ResultSet res) throws SQLException {
		Usuario resultado = new Usuario();
		resultado.setId(res.getInt("id"));
		resultado.setNick(res.getString("nick"));
		resultado.setEmail(res.getString("email"));
		resultado.setPass(res.getString("pass"));
		return resultado;
	}

}