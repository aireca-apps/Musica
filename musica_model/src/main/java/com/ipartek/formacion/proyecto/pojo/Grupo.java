package com.ipartek.formacion.proyecto.pojo;

import java.util.Date;
import java.util.GregorianCalendar;

public class Grupo {

	private int id;
	private String nombre, componentes;
	private Date fechaInicio, fechaFin;
	private Estilo estilo;

	public Grupo() {
		super();
		id = -1;
		this.nombre = "";
		this.componentes = "";
		this.estilo = null;
		GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
		this.fechaInicio = new Date(calendar.getTimeInMillis());
		this.fechaFin = this.fechaInicio;
	}

	public Grupo(String nombre, String componentes, Date fechaInicio, Date fechaFin) {
		this();
		this.nombre = nombre;
		this.componentes = componentes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Grupo(String nombre, String componentes, Date fechaInicio, Date fechaFin, Estilo estilo) {
		this(nombre, componentes, fechaInicio, fechaFin);
		this.estilo = estilo;
	}
	
	public Grupo(int id, String nombre, String componentes, Date fechaInicio, Date fechaFin, Estilo estilo) {
		this(nombre, componentes, fechaInicio, fechaFin, estilo);
		this.id = id;
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

	public String getComponentes() {
		return componentes;
	}

	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((componentes == null) ? 0 : componentes.hashCode());
		result = (prime * result) + ((estilo == null) ? 0 : estilo.hashCode());
		result = (prime * result) + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = (prime * result) + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean resul = false;
		if (obj != null) {
			resul = this.hashCode() == obj.hashCode();
		}
		return resul;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + ", componentes=" + componentes + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", estilo=" + estilo.toString() + "]";
	}	
}