package com.bancosoft.ws.rest.mo;

public class Usuario {
	private int id;
	private String tipoDocumento;
	private String contrasena;
	private String tipoUsuario;
	private String nombre;
	private String apellido;
	private String correo;
	public Usuario(int id, String tipoDocumento, String contrasena, String tipoUsuario, String nombre, String apellido,
			String correo) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	public Usuario() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
