package com.ipartek.formacion.backoffice.pojo;

public class Rol {

	public static final int ROL_ID_USUARIO = 1, ROL_ID_ADMINISTRADOR = 3;

	private int id;
	private String nombre;
	private String descripcion;
	private String codigo;

	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.descripcion = "";
		this.codigo = "";
	}

	public Rol(int id, String nombre, String descripcion, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}