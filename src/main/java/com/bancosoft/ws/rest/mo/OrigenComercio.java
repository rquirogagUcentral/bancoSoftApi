package com.bancosoft.ws.rest.mo;

public class OrigenComercio {
	private String refComercio;
	private int valor;
	private String descripcion;
	
	public OrigenComercio(String refComercio, int valor, String descripcion) {
		super();
		this.refComercio = refComercio;
		this.valor = valor;
		this.descripcion = descripcion;
	}

	
	
	public OrigenComercio() {
		super();
	}



	public String getRefComercio() {
		return refComercio;
	}

	public void setRefComercio(String refComercio) {
		this.refComercio = refComercio;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
