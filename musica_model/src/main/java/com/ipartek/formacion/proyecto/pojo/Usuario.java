/**
 *
 */
package com.ipartek.formacion.proyecto.pojo;

/**
 * @author Curso
 *
 */
public class Usuario {

	private String nick, email, pass;
	private int id;

	/**
	 *
	 */
	public Usuario() {
		this.id = -1;
		this.nick = "";
		this.email = "";
		this.pass = "";
	}

	public Usuario(String nick, String email, String pass) {
		this();
		this.nick = nick;
		this.email = email;
		this.pass = pass;
	}

	public Usuario(int id, String nick, String email, String pass) {
		this(nick, email, pass);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick
	 *            the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", email=" + email + "]";
	}

}