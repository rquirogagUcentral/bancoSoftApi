package com.bancosoft.ws.rest.mo;

public class CrearUsuarioRequest {
	private Usuario usuario;
	private Cuenta cuenta;
	public CrearUsuarioRequest() {
		super();
	}
	public CrearUsuarioRequest(Usuario usuario, Cuenta cuenta) {
		super();
		this.usuario = usuario;
		this.cuenta = cuenta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
}
