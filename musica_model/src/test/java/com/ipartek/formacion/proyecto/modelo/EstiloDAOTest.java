package com.ipartek.formacion.proyecto.modelo;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.proyecto.pojo.Estilo;
import com.ipartek.formacion.proyecto.pojo.Grupo;

public class EstiloDAOTest {

	static DbConnection db;
	static Connection conn;
	static EstiloDAO daoEstilo;
	static Estilo estilo1, estilo2;
	static int id1, id2; // identificador de la última operación realizada en el
							// DAO

	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			daoEstilo = new EstiloDAO();
			estilo1 = new Estilo(1, "rock", "música Rock", "RC");
			estilo2 = new Estilo(2, "jazz", "música Jazz", "JZ");
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
			conn.rollback();
			db.desconectar();
			db = null;
			daoEstilo = null;
			estilo1 = null;
			estilo2 = null;
		} catch (Exception e) {
			fail("Excepción tearDownAfterClass " + e.getMessage());
		}
	}

	@Before
	public void setUp() {
		try {
			id1 = daoEstilo.insert(estilo1);
			assertTrue("No se ha insertado el estilo1", id1 > 0);
		} catch (Exception e) {
			fail("Excepción setUp " + e.getMessage());
		}
	}

	@After
	public void tearDown() {
		try {
			// Tras el test Delete dará error
			daoEstilo.delete(id1);
		} catch (Exception e) {
		}
	}

	@Test
	public void testInsert() {
		try {
			// Inserción válida
			id2 = daoEstilo.insert(estilo2);
			assertTrue("No se ha insertado estilo2", id2 != -1);
			// Inserción nula
			assertEquals(-1, daoEstilo.insert(null));

			// Inserción de un objeto vacío
			assertEquals(-1, daoEstilo.insert(new Estilo()));
			assertTrue("No se pudo eliminar", daoEstilo.delete(id2));
		} catch (Exception e) {
			fail("Excepción testInsert  " + e.getMessage());
		}
	}

	@Test
	public void testGetAll() {
		try {
			int estilosSize = daoEstilo.getAll().size();
			assertTrue("debería al menos recuperar un estilo", estilosSize > 0);
			id2 = daoEstilo.insert(estilo2);
			assertTrue("No se ha realizado la inserción", id2 != -1);
			assertTrue("debería al menos recuperar dos estilos", (estilosSize + 1) == daoEstilo.getAll().size());
			assertTrue("No se pudo eliminar", daoEstilo.delete(id2));
		} catch (Exception e) {
			fail("Excepción testGetAll " + e.getMessage());
		}
	}

	@Test
	public void testGetById() {
		try {
			// Se comprueba con los dos Mock:
			assertTrue("No tienen los mismos atributos", estilo1.equals(daoEstilo.getById(id1)));

			id2 = daoEstilo.insert(estilo2);
			assertTrue("No se ha realizado la inserción", id2 != -1);
			assertTrue("No tienen los mismos atributos", estilo2.equals(daoEstilo.getById(id2)));
			assertTrue("No se pudo eliminar", daoEstilo.delete(id2));

			// Se comprueba que devuelve un grupo vacío en caso de no
			// encontrarlo en la bbdd
			assertTrue("No devuelve un grupo vacío si no encuentra el id en la bbdd",
					new Estilo().equals(daoEstilo.getById(-1)));
		} catch (Exception e) {
			fail("Excepción testGetById " + e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		try {
			// Se elimina un elemento con un id existente
			assertTrue("No se pudo eliminar", daoEstilo.delete(id1));

			// Se elimina un elemento con un id inexistente
			assertFalse("No puede eliminar lo que no existe", daoEstilo.delete(-1));
		} catch (Exception e) {
			fail("Excepción testDelete " + e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		try {
			assertEquals("No tienen los mismos atributos", estilo1, daoEstilo.getById(id1));
			estilo1.setNombre("Nombre inventado");
			estilo1.setDescripcion("Descripcion inventada");
			estilo1.setCodigo("NI");
			assertTrue("no se ha actualizado el objeto", daoEstilo.update(estilo1));
			assertTrue("No tienen los mismos atributos", estilo1.equals(daoEstilo.getById(id1)));

			// Test null
			assertFalse("no se puede modifica la base una persona que no existe", daoEstilo.update(null));

			// Test grupo vacío
			assertFalse("no se puede modifica la base una persona que no existe", daoEstilo.update(new Estilo()));

			// Test grupo que no existe
			assertFalse("no se puede modifica la base una persona que no existe", daoEstilo.update(new Estilo("nombre",
					"descripcion", "NN")));
		} catch (Exception e) {
			fail("Excepción testUpdate " + e.getMessage());
		}
	}

}
