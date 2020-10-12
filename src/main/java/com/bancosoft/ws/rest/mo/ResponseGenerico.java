package com.bancosoft.ws.rest.mo;

public class ResponseGenerico {
	private String estado;
	private String descripcionEstado;
	
	
	
	public ResponseGenerico() {
		super();
	}
	public ResponseGenerico(String estado, String descripcionEstado) {
		super();
		this.estado = estado;
		this.descripcionEstado = descripcionEstado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	@Override
	public String toString() {
		return "ResponseGenerico [estado=" + estado + ", descripcionEstado=" + descripcionEstado + "]";
	}
	
	
	
}
