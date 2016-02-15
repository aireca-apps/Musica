package com.ipartek.formacion.proyecto.modelo;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ipartek.formacion.proyecto.modelo.DbConnection;

public class GrupoDAOTest {

	static DbConnection db;
	static Connection conn;

	@Test
	public void testDataBase() throws SQLException {
		try {
			db = new DbConnection();
			conn = db.getConnection();
			db.desconectar();
		} catch (Exception e) {
			fail("Excepción base de datos " + e.getMessage());
		}
	}
}