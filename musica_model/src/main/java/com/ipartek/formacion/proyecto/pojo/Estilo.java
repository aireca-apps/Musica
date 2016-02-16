package com.ipartek.formacion.proyecto.pojo;

public class Estilo {

	public static final int ROL_ID_USUARIO = 1, ROL_ID_ADMINISTRADOR = 3;

	private int id;
	private String nombre;
	private String descripcion;
	private String codigo;

	public Estilo() {
		super();
		this.id = -1;
		this.nombre = "";
		this.descripcion = "";
		this.codigo = "";
	}

	public Estilo(String nombre) {
		this();
		this.nombre = nombre;
	}

	public Estilo(String nombre, String descripcion, String codigo) {
		this(nombre);
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	public Estilo(int id, String nombre, String descripcion, String codigo) {
		this(nombre, descripcion, codigo);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		return "Estilo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", codigo=" + codigo + "]";
	}

	
}