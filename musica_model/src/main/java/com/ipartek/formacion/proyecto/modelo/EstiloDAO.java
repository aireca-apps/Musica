package com.ipartek.formacion.proyecto.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.ia.InterfacePersistable;
import com.ipartek.formacion.proyecto.modelo.ia.AbstractPersistible;
import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

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
public class EstiloDAO extends AbstractPersistible<Estilo> {

	@Override
	public List<Estilo> getAll() throws SQLException {
		ArrayList<Estilo> lista = new ArrayList<Estilo>();
		DbConnection conn = new DbConnection();
		String sql = "select id, nombre, descripcion, codigo from `estilo` order by `id` desc limit 500;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		ResultSet rs = consulta.executeQuery();
		while (rs.next()) {
			lista.add(mapeo(rs));
		}
		cerrarConsulta(rs, conn, consulta);
		return lista;
	}

	@Override
	public Estilo getById(int id) throws SQLException {
		Estilo est = new Estilo();
		DbConnection conn = new DbConnection();
		String sql = "select id, nombre, descripcion, codigo from `estilo` where id = ? ;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		ResultSet rs = consulta.executeQuery();
		if (rs.next()) {
			est = mapeo(rs);
		}
		cerrarConsulta(rs, conn, consulta);
		return est;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean resul = false;
		DbConnection conn = new DbConnection();
		String sql = "DELETE FROM `estilo` WHERE  `id`= ? ;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		if (consulta.executeUpdate() == 1) {
			resul = true;
		}
		cerrarPeticion(conn, consulta);
		return resul;
	}

	@Override
	public boolean update(Estilo est) throws SQLException {
		boolean resul = false;
		if (est != null && !est.equals(new Estilo())) {
			DbConnection conn = new DbConnection();
			String sql = "UPDATE `estilo` SET `nombre`= ? , `descripcion`= ?, `codigo`= ? WHERE  `id`= ? ;";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
			consulta.setString(1, est.getNombre());
			consulta.setString(2, est.getDescripcion());
			consulta.setString(3, est.getCodigo());
			consulta.setInt(4, est.getId());
			if (consulta.executeUpdate() == 1) {
				resul = true;
			}
			cerrarPeticion(conn, consulta);
		}
		return resul;
	}

	@Override
	public int insert(Estilo est) throws SQLException {
		int resul = -1;
		if (est != null && !est.equals(new Estilo())) {
			DbConnection conn = new DbConnection();
			String sql = "INSERT INTO `estilo` (`nombre`,`descripcion`, `codigo` ) VALUES ( ?, ?, ? );";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			consulta.setString(1, est.getNombre());
			consulta.setString(2, est.getDescripcion());
			consulta.setString(3, est.getCodigo());
			if (consulta.executeUpdate() == 1) {
				ResultSet generatedKeys = consulta.getGeneratedKeys();
				if (generatedKeys.next()) {
					resul = generatedKeys.getInt(1);
					est.setId(resul);
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
	public Estilo mapeo(ResultSet res) throws SQLException {
		Estilo gru = new Estilo();
		gru.setId(res.getInt("id"));
		gru.setNombre(res.getString("nombre"));
		gru.setDescripcion(res.getString("descripcion"));
		gru.setCodigo(res.getString("codigo"));
		return gru;
	}

}