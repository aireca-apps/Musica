package com.ipartek.formacion.backoffice.pojo;

import java.util.Date;
import java.util.GregorianCalendar;

public class Persona {

	private int id;
	private String nombre, dni, pass, observaciones, email;
	private Date fechaNacimiento;
	private Rol rol;

	public Persona() {
		super();
		id = -1;
		this.nombre = "";
		this.dni = "";
		this.observaciones = "";
		this.email = "";
		this.pass = "";
		this.rol = null;
		GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
		this.fechaNacimiento = new Date(calendar.getTimeInMillis());
	}

	public Persona(String nombre, String dni, String pass, String observaciones, String email, Date fechaNacimiento) {
		this();
		this.nombre = nombre;
		this.dni = dni;
		this.pass = pass;
		this.observaciones = observaciones;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", pass=" + pass + ", observaciones="
				+ observaciones + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((dni == null) ? 0 : dni.hashCode());
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((nombre == null) ? 0 : nombre.hashCode());
		result = (prime * result) + ((observaciones == null) ? 0 : observaciones.hashCode());
		result = (prime * result) + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
}