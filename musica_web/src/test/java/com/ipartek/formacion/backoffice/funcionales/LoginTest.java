package com.ipartek.formacion.backoffice.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.modelo.TablaAuxiliarDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;
import com.ipartek.formacion.backoffice.pojo.Rol;

public class LoginTest {

	private static PersonaDAO daoPersona;
	private static TablaAuxiliarDAO daoRol;
	private static WebDriver driver;
	private static int idU = -1;
	private static int idR = -1;
	// private static long aux;
	private static String data;

	private static Persona per;
	private static Rol rol;

	@Ignore
	public static void setUpBeforeClass() throws Exception {
		// driver = new FirefoxDriver();
		driver = new HtmlUnitDriver();
		// driver = new ChromeDriver();

		daoRol = new TablaAuxiliarDAO();
		daoPersona = new PersonaDAO();
		data = Long.toString(System.currentTimeMillis()).substring(0, 9);

		rol = new Rol(data);
		rol.setCodigo(data.substring(0, 2));
		rol.setDescripcion(data);

		try {
			idR = daoRol.insert(rol);
			assertNotEquals(-1, idR);
			rol.setId(idR);
		} catch (SQLException e) {
			fail("No deberia haber fallado");
		}

		per = new Persona();
		per.setDni(data);
		per.setEmail(data + "@" + data + ".com");
		per.setFechaNacimiento(new Date(System.currentTimeMillis()));
		per.setPass(data);
		per.setRol(rol);

		try {
			idU = daoPersona.insert(per);
			assertNotEquals(-1, idU);
			per.setId(idU);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("No deberia haber fallado");
		}

	}

	@Ignore
	public static void tearDownAfterClass() throws Exception {
		assertTrue(daoPersona.delete(idU));
		assertTrue(daoRol.delete(idR));

	}

	@Ignore
	public void setUp() throws Exception {
	}

	@Ignore
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testDatosCorrectos() {
		driver.get("http://localhost:8080/BackofficeSbAdminBoostrap/");
		WebElement usuario = driver.findElement(By.name("email"));
		usuario.clear();
		usuario.sendKeys(per.getEmail());

		WebElement contra = driver.findElement(By.name("password"));
		contra.clear();
		contra.sendKeys(per.getPass());

		driver.findElement(By.id("submit")).click();

		assertEquals("http://localhost:8080/BackofficeSbAdminBoostrap/loginUser", driver.getCurrentUrl());
		driver.get("http://localhost:8080/BackofficeSbAdminBoostrap/logout");
	}

	@Ignore
	public void testDatosIncorrectos() {
		driver.get("http://localhost:8080/BackofficeSbAdminBoostrap/");
		WebElement usuario = driver.findElement(By.name("email"));
		usuario.clear();

		usuario.sendKeys(per.getEmail());

		WebElement contra = driver.findElement(By.name("password"));
		contra.clear();
		contra.sendKeys(Integer.toString(idR));

		Select selecIdioma = new Select(driver.findElement(By.id("idioma")));
		selecIdioma.selectByValue("eu_ES");

		// submitar formulario
		driver.findElement(By.id("submit")).click();

		usuario = null;
		contra = null;
		assertEquals("http://localhost:8080/BackofficeSbAdminBoostrap/loginUser", driver.getCurrentUrl());

		WebElement msgError = driver.findElement(By.className("error"));
		assertEquals("Credenciales no válidas", msgError.getText());

		contra = driver.findElement(By.name("password"));
		usuario = driver.findElement(By.name("email"));

		// Comprobamos mediante la existencia de los botones que estamos en el
		// login.jsp
		assertNotEquals(null, contra);
		assertNotEquals(null, usuario);
	}

}
