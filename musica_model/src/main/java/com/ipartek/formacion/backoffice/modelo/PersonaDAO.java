package com.ipartek.formacion.backoffice.modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.backoffice.pojo.Persona;
import com.ipartek.formacion.backoffice.pojo.Rol;

public class PersonaDAO implements Persistable<Persona> {

	public Persona login(String email, String pass) throws SQLException {

		Persona resultado = null;
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidi
		String sql = "{call login (?, ?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setString(1, email);
		consulta.setString(2, pass);
		ResultSet rs = consulta.executeQuery();
		while (rs.next()) {
			resultado = mapeo(rs);
		}
		rs.close();
		consulta.close();
		conn.desconectar();
		return resultado;
	}

	@Override
	public List<Persona> getAll() throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidy
		String sql = "{call getPersonasAll (?)}";
		CallableStatement cs = conn.getConnection().prepareCall(sql);
		cs.setInt(1, 500);
		ResultSet res = cs.executeQuery();

		// ArrayList de personas
		ArrayList<Persona> lista = new ArrayList<Persona>();

		// iterar sobre los resultados
		while (res.next())
			lista.add(mapeo(res));
		res.close();
		cs.close();
		conn.desconectar();
		return lista;
	}

	public List<Persona> getByDni(String DNI) throws SQLException {
		return getPersonas(DNI);
	}

	public List<Persona> getPersonas(String criterio) throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidy
		String sql = "{call searchPersonas (?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setString(1, criterio);
		ResultSet res = consulta.executeQuery();
		// ArrayList de personas
		ArrayList<Persona> lista = new ArrayList<Persona>();

		// iterar sobre los resultados
		while (res.next())
			lista.add(mapeo(res));

		res.close();
		consulta.close();
		conn.desconectar();
		return lista;
	}

	@Override
	public Persona getById(int id) throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();
		// nombre de la clase y ctrl + espacio
		String sql = "select p.`id` as persona_id, p.`rol_id`, p.`nombre`, p.`dni`, p.`pass`, "
				+ "p.`fecha_nacimiento`, p.`observaciones`, p.`email`, r.`nombre` as rol_nombre, "
				+ "r.`descripcion` as rol_descripcion, r.`codigo` as rol_codigo from persona AS p INNER JOIN `rol` AS r ON p.`rol_id` = r.`id`"
				+ " where p.`id` = ?;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		// ejecutar la consulta
		ResultSet res = consulta.executeQuery();

		// iterar sobre los resultados
		res.next();
		Persona p = mapeo(res);

		res.close();
		consulta.close();
		conn.desconectar();
		return p;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		DbConnection conn = new DbConnection();
		boolean resul = false;
		String sql = "delete from `persona` where `id` = ?;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		if (pst.executeUpdate() == 1)// Si solo afecta a una línea
			resul = true;
		return resul;
	}

	@Override
	public boolean update(Persona persistable) throws SQLException {
		boolean resul = false;
		if (persistable != null) {
			DbConnection conn = new DbConnection();
			String sql = "update `persona` set dni = ?, pass = ?, fecha_nacimiento = ?, observaciones = ?, email = ?, nombre = ?, rol_id = ? where `id` = ? ;";
			PreparedStatement pst = conn.getConnection().prepareStatement(sql);
			pst.setString(1, persistable.getDni());
			pst.setString(2, persistable.getPass());
			pst.setDate(3, (Date) persistable.getFechaNacimiento());
			pst.setString(4, persistable.getObservaciones());
			pst.setString(5, persistable.getEmail());
			pst.setString(6, persistable.getNombre());
			pst.setInt(7, persistable.getRol().getId());
			pst.setInt(8, persistable.getId());
			if (pst.executeUpdate() == 1)// Si solo afecta a una línea
				resul = true;
		}
		return resul;
	}

	@Override
	public int insert(Persona persistable) throws SQLException {
		int i;
		// Se abre conexión
		DbConnection conn = new DbConnection();
		try {
			String sql = "INSERT INTO `persona` (`dni`, `pass`, `fecha_nacimiento`, `observaciones`, `email`, `nombre`, `rol_id`) VALUES (?,?,?,?,?,?, ?);";
			PreparedStatement pst = conn.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pst.setString(1, persistable.getDni());
			pst.setString(2, persistable.getPass());
			pst.setDate(3, (Date) persistable.getFechaNacimiento());
			pst.setString(4, persistable.getObservaciones());
			pst.setString(5, persistable.getEmail());
			pst.setString(6, persistable.getNombre());
			pst.setInt(7, persistable.getRol().getId());
			// ejecutar la consulta. Si no afecta a una línea, lanzamos la
			// excepción
			if (pst.executeUpdate() != 1)
				throw new SQLException("No se ha insertado el dato");

			ResultSet generatedKeys = pst.getGeneratedKeys();
			generatedKeys.next();
			i = generatedKeys.getInt(1);
			persistable.setId(i);
			pst.close();

		} catch (Exception e) {
			i = -1;
			e.printStackTrace();
		}
		conn.desconectar();
		return i;
	}

	private Persona mapeo(ResultSet res) throws SQLException {

		Persona p = new Persona();
		try {
			p.setId(res.getInt("persona_id"));
			p.setNombre(res.getString("nombre"));
			p.setDni(res.getString("dni"));
			p.setPass(res.getString("pass"));
			p.setObservaciones(res.getString("observaciones"));
			p.setEmail(res.getString("email"));
			p.setFechaNacimiento(res.getDate("fecha_nacimiento"));
			p.setRol(new Rol(res.getInt("rol_id"), res.getString("rol_nombre"), res.getString("rol_descripcion"),
					res.getString("rol_codigo")));
		} catch (Exception e) {
		}
		return p;
	}
}