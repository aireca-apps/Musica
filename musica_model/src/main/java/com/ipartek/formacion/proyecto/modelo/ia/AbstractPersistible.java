package com.ipartek.formacion.proyecto.modelo.ia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ipartek.formacion.proyecto.modelo.DbConnection;

public abstract class AbstractPersistible<P> implements InterfacePersistable<P> {

	public static void cerrarConsulta(ResultSet rs, DbConnection conn, Statement consulta) {
		try {
			rs.close();
			consulta.close();
			conn.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cerrarPeticion(DbConnection conn, Statement consulta) {
		try {
			consulta.close();
			conn.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}