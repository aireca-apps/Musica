package com.ipartek.formacion.backoffice.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.backoffice.pojo.Rol;

/**
 * DAO para operaciones de CRUD con Tablas Axuliares, que deben tener esta
 * estructura:
 *
 * <ul>
 * <li><b>id</b> (int) Identificador autoincremental</li>
 * <li><b>nombre</b> (varchar 50) nombre unico</li>
 * <li><b>descripcion</b> (varchar 250) descripcion, permite null</li>
 * </ul>
 *
 *
 * @author ur00
 *
 */

public class TablaAuxiliarDAO implements Persistable<Rol> {

	@Override
	public List<Rol> getAll() throws SQLException {
		ArrayList<Rol> lista = new ArrayList<Rol>();
		DbConnection conn = new DbConnection();
		String sql = "select id, nombre, descripcion, codigo from `rol` order by `id` desc limit 500;";
		PreparedStatement ps = conn.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			lista.add(mapeo(rs));
		}
		rs.close();
		ps.close();
		conn.desconectar();
		return lista;
	}

	@Override
	public Rol getById(int id) throws SQLException {
		Rol p = null;
		DbConnection conn = new DbConnection();
		String sql = "select id, nombre, descripcion, codigo from `rol` where id = ? ;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			p = mapeo(rs);
		}
		rs.close();
		pst.close();
		conn.desconectar();
		return p;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean resul = false;
		DbConnection conn = new DbConnection();
		String sql = "DELETE FROM `rol` WHERE  `id`= ? ;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		if (pst.executeUpdate() == 1) {
			resul = true;
		}
		pst.close();
		conn.desconectar();
		return resul;
	}

	@Override
	public boolean update(Rol p) throws SQLException {
		boolean resul = false;
		if (p != null) {
			DbConnection conn = new DbConnection();
			String sql = "UPDATE `rol` SET `nombre`= ? , `descripcion`= ?, `codigo`= ? WHERE  `id`= ? ;";
			PreparedStatement pst = conn.getConnection().prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getDescripcion());
			pst.setString(3, p.getCodigo());
			pst.setInt(4, p.getId());
			if (pst.executeUpdate() == 1) {
				resul = true;
			}
			pst.close();
			conn.desconectar();
		}
		return resul;
	}

	@Override
	public int insert(Rol p) throws SQLException {
		int resul = -1;
		DbConnection conn = new DbConnection();
		String sql = "INSERT INTO `rol` (`nombre`,`descripcion`, `codigo` ) VALUES ( ?, ?, ? );";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setString(1, p.getNombre());
		pst.setString(2, p.getDescripcion());
		pst.setString(3, p.getCodigo());
		if (pst.executeUpdate() == 1) {
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				p.setId(generatedKeys.getInt(1));
				resul = p.getId();
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
		pst.close();
		conn.desconectar();
		return resul;
	}

	/**
	 * Mapear ResulSet a un Pojo
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Rol mapeo(ResultSet rs) throws SQLException {
		Rol p = new Rol("");
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setDescripcion(rs.getString("descripcion"));
		p.setCodigo(rs.getString("codigo"));
		return p;
	}

}