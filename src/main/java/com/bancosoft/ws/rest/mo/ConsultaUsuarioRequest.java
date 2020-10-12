package com.bancosoft.ws.rest.mo;

public class ConsultaUsuarioRequest {
	private int id;
	private String tipoDocumento;
	private String contrasena;
	public ConsultaUsuarioRequest(int id, String tipoDocumento, String contrasena) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.contrasena = contrasena;
	}
	public ConsultaUsuarioRequest() {
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
}
