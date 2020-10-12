package com.bancosoft.ws.rest.mo;

import java.util.List;

public class ConsultaUsuarioResponse extends ResponseGenerico {
	private Usuario usuario;
	private List<Cuenta> cuentas;
	public ConsultaUsuarioResponse(Usuario usuario, List<Cuenta> cuentas) {
		super();
		this.usuario = usuario;
		this.cuentas = cuentas;
	}
	public ConsultaUsuarioResponse() {
		super();
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	
}
