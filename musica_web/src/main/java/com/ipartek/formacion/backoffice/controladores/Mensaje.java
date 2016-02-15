package com.ipartek.formacion.backoffice.controladores;

public class Mensaje {
	
	//constantes para tipos
	public static final String TIPO_SUCCESS = "success";
	public static final String TIPO_INFO    = "info";
	public static final String TIPO_WARNING = "warning";
	public static final String TIPO_DANGER  = "danger";
	
	private String texto;
	private String tipo;
	
	
	public Mensaje() {
		super();
		this.texto = "Error desconocido";
		this.tipo  = TIPO_DANGER;
	}


	public Mensaje(String texto, String tipo) {
		super();
		this.texto = texto;
		this.tipo = tipo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
