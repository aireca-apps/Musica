package com.ipartek.formacion.proyecto.modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.proyecto.modelo.ia.AbstractPersistible;
import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

public class GrupoDAO extends AbstractPersistible<Grupo> {

	public Grupo login(String email, String pass) throws SQLException {
		Grupo resultado = null;
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
		cerrarConsulta(rs, conn, consulta);
		return resultado;
	}

	@Override
	public List<Grupo> getAll() throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidy
		String sql = "{call getGrupos (?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setInt(1, 500);
		ResultSet rs = consulta.executeQuery();

		// ArrayList de personas
		ArrayList<Grupo> lista = new ArrayList<Grupo>();

		// iterar sobre los resultados
		while (rs.next())
			lista.add(mapeo(rs));
		cerrarConsulta(rs, conn, consulta);
		return lista;
	}

	public List<Grupo> searchGrupos(String criterio) throws SQLException {
		// Se abre conexión
		DbConnection conn = new DbConnection();

		// para llamar a un procedimiento creado en heidy
		String sql = "{call searchGrupos (?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setString(1, criterio);
		ResultSet rs = consulta.executeQuery();
		// ArrayList de personas
		ArrayList<Grupo> lista = new ArrayList<Grupo>();

		// iterar sobre los resultados
		while (rs.next())
			lista.add(mapeo(rs));

		cerrarConsulta(rs, conn, consulta);
		return lista;
	}

	@Override
	public Grupo getById(int id) throws SQLException {
		Grupo gru = new Grupo();
		// Se abre conexión
		DbConnection conn = new DbConnection();
		String sql = "{call getById (?)}";
		CallableStatement consulta = conn.getConnection().prepareCall(sql);
		consulta.setInt(1, id);
		// ejecutar la consulta
		ResultSet rs = consulta.executeQuery();

		if (rs.next()) {
			gru = mapeo(rs);
		}
		cerrarConsulta(rs, conn, consulta);
		return gru;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		DbConnection conn = new DbConnection();
		boolean resul = false;
		String sql = "delete from `grupo` where `id` = ?;";
		PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
		consulta.setInt(1, id);
		if (consulta.executeUpdate() == 1)// Si solo afecta a una línea
			resul = true;
		cerrarPeticion(conn, consulta);
		return resul;
	}

	@Override
	public boolean update(Grupo gru) throws SQLException {
		boolean resul = false;
		if (gru != null && !gru.equals(new Grupo())) {
			DbConnection conn = new DbConnection();
			String sql = "update `grupo` set nombre = ?, componentes = ?, fecha_inicio = ?,  fecha_fin = ?, estilo_id = ? where `id` = ? ;";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql);
			consulta.setString(1, gru.getNombre());
			consulta.setString(2, gru.getComponentes());
			consulta.setDate(3, (Date) gru.getFechaInicio());
			consulta.setDate(4, (Date) gru.getFechaFin());
			consulta.setInt(5, gru.getEstilo().getId());
			consulta.setInt(6, gru.getId());
			if (consulta.executeUpdate() == 1)// Si solo afecta a una línea
				resul = true;
			cerrarPeticion(conn, consulta);
		}
		return resul;
	}

	@Override
	public int insert(Grupo gru) throws SQLException {
		int resul = -1;
		// Se abre conexión
		if (gru != null && !gru.equals(new Grupo())) {
			DbConnection conn = new DbConnection();
			String sql = "INSERT INTO `grupo` (`nombre`, `componentes`, `fecha_inicio`, `fecha_fin`, `estilo_id`) VALUES (?,?,?,?,?);";
			PreparedStatement consulta = conn.getConnection().prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			consulta.setString(1, gru.getNombre());
			consulta.setString(2, gru.getComponentes());
			consulta.setDate(3, (Date) gru.getFechaInicio());
			consulta.setDate(4, (Date) gru.getFechaFin());
			consulta.setInt(5, gru.getEstilo().getId());
			// ejecutar la consulta. Si no afecta a una línea, lanzamos la
			// excepción
			if (consulta.executeUpdate() == 1) {
				ResultSet generatedKeys = consulta.getGeneratedKeys();
				if (generatedKeys.next()) {
					resul = generatedKeys.getInt(1);
					gru.setId(resul);
				}
			}
			cerrarPeticion(conn, consulta);
		}
		return resul;
	}

	@Override
	public Grupo mapeo(ResultSet res) throws SQLException {
		Grupo gru = new Grupo();
		gru.setId(res.getInt("grupo_id"));
		gru.setNombre(res.getString("nombre"));
		gru.setComponentes(res.getString("componentes"));
		gru.setFechaInicio(res.getDate("fecha_inicio"));
		gru.setFechaFin(res.getDate("fecha_fin"));
		gru.setEstilo(new Estilo(res.getInt("estilo_id"), res.getString("estilo_nombre"),
				res.getString("estilo_descripcion"), res.getString("estilo_codigo")));
		return gru;
	}
}