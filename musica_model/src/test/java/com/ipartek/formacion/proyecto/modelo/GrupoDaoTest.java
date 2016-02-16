package com.ipartek.formacion.proyecto.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

public class GrupoDaoTest {

	static DbConnection db;
	static Connection conn;
	static GrupoDAO daoGrupo;
	static EstiloDAO daoEstilo;
	static Grupo gruMock1, gruMock2;
	static Estilo estilo1, estilo2;
	static int id1, id2; // identificador de la última operación realizada en el
							// DAO

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			daoGrupo = new GrupoDAO();
			daoEstilo = new EstiloDAO();
			estilo1 = new Estilo(1, "rock", "música Rock", "RC");
			estilo2 = new Estilo(2, "jazz", "música Jazz", "JZ");
			assertTrue("No se ha insertado el estilo1", daoEstilo.insert(estilo1) != -1);
			assertTrue("No se ha insertado el estilo2", daoEstilo.insert(estilo2) != -1);
			GregorianCalendar calendar2 = new GregorianCalendar(1983, 9, 11),
					calendar = new GregorianCalendar(1980, 10, 15);
			gruMock1 = new Grupo("Mock", "cuato componentes", new Date(calendar.getTimeInMillis()),
					new Date(calendar2.getTimeInMillis()), estilo1);
			gruMock2 = new Grupo("Mock2", "tres componentes", new Date(calendar.getTimeInMillis()), null, estilo2);

			db = new DbConnection();
			conn = db.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			fail("Excepción setUpBeforeClass " + e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
		try {
			assertTrue("No se ha eliminado el estilo1", daoEstilo.delete(estilo1.getId()));
			assertTrue("No se ha eliminado el estilo2", daoEstilo.delete(estilo2.getId()));
			conn.rollback();
			db.desconectar();
			db = null;
			daoGrupo = null;
			daoEstilo = null;
			estilo1 = null;
			estilo2 = null;
			gruMock1 = null;
			gruMock2 = null;
		} catch (Exception e) {
			fail("Excepción tearDownAfterClass " + e.getMessage());
		}
	}

	@Before
	public void setUp() {
		try {
			id1 = daoGrupo.insert(gruMock1);
			assertTrue("No se ha realizado la inserción", id1 > 0);
		} catch (Exception e) {
			fail("Excepción setUp " + e.getMessage());
		}
	}

	@After
	public void tearDown() {
		try {
			// Tras el test Delete dará error
			daoGrupo.delete(id1);
		} catch (Exception e) {
		}
	}

	@Test
	public void testInsert() {
		try {
			// Inserción válida
			id2 = daoGrupo.insert(gruMock2);
			assertTrue("No se ha insertado Mock2", id2 != -1);
			// Inserción nula
			assertEquals(-1, daoGrupo.insert(null));

			// Inserción de un objeto vacío
			assertEquals(-1, daoGrupo.insert(new Grupo()));
			assertTrue("No se pudo eliminar", daoGrupo.delete(id2));
		} catch (Exception e) {
			fail("Excepción testInsert " + e.getMessage());
		}
	}

	// TODO acabar este test
	@Ignore
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		try {
			int gruposSize = daoGrupo.getAll().size();
			assertTrue("debería al menos recuperar un grupo", gruposSize > 0);
			id2 = daoGrupo.insert(gruMock2);
			assertTrue("No se ha realizado la inserción", id2 != -1);
			assertTrue("debería al menos recuperar dos grupos", (gruposSize + 1) == daoGrupo.getAll().size());// Solo
																												// grupos
			assertTrue("No se pudo eliminar", daoGrupo.delete(id2));
		} catch (Exception e) {
			fail("Excepción testGetAll " + e.getMessage());
		}
	}

	// TODO acabar este test
	@Ignore
	public void testSearchGrupos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		try {
			// Se comprueba con los dos Mock:
			assertTrue("No tienen los mismos atributos", gruMock1.equals(daoGrupo.getById(id1)));

			id2 = daoGrupo.insert(gruMock2);
			assertTrue("No se ha realizado la inserción", id2 != -1);
			assertTrue("No tienen los mismos atributos", gruMock2.equals(daoGrupo.getById(id2)));
			assertTrue("No se pudo eliminar", daoGrupo.delete(id2));

			// Se comprueba que devuelve un grupo vacío en caso de no
			// encontrarlo en la bbdd
			assertTrue("No devuelve un grupo vacío si no encuentra el id en la bbdd",
					new Grupo().equals(daoGrupo.getById(-1)));
		} catch (Exception e) {
			fail("Excepción testGetById " + e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		try {
			// Se elimina un elemento con un id existente
			assertTrue("No se pudo eliminar", daoGrupo.delete(id1));

			// Se elimina un elemento con un id inexistente
			assertFalse("No puede eliminar lo que no existe", daoGrupo.delete(-1));
		} catch (Exception e) {
			fail("Excepción testDelete " + e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		try {
			assertEquals("No tienen los mismos atributos", gruMock1, daoGrupo.getById(id1));
			gruMock1.setNombre("Nombre inventado");
			gruMock1.setComponentes("Nadie toca en este grupo");
			gruMock1.setFechaInicio(new Date(new GregorianCalendar(2010, 10, 11).getTimeInMillis()));
			gruMock1.setFechaFin(new Date(new GregorianCalendar(2015, 8, 2).getTimeInMillis()));
			gruMock1.setEstilo(estilo2);
			assertTrue("no se ha actualizado el objeto", daoGrupo.update(gruMock1));
			assertTrue("No tienen los mismos atributos", gruMock1.equals(daoGrupo.getById(id1)));

			// Test null
			assertFalse("no se puede modifica la base una persona que no existe", daoGrupo.update(null));

			// Test grupo vacío
			assertFalse("no se puede modifica la base una persona que no existe", daoGrupo.update(new Grupo()));

			// Test grupo que no existe
			assertFalse("no se puede modifica la base una persona que no existe", daoGrupo.update(new Grupo("nombre",
					"componentes", new Date(new GregorianCalendar(2010, 10, 11).getTimeInMillis()), null, estilo1)));

		} catch (Exception e) {
			fail("Excepción testUpdate " + e.getMessage());
		}
	}
}