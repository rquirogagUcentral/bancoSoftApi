package com.bancosoft.ws.rest.mo;

public class TransaccionNotificacionRequest {
	private String referencia;
	private String codPasarela;
	private Transaccion transaccion;
	public TransaccionNotificacionRequest(String referencia, String codPasarela, Transaccion transaccion) {
		super();
		this.referencia = referencia;
		this.codPasarela = codPasarela;
		this.transaccion = transaccion;
	}
	public TransaccionNotificacionRequest() {
		super();
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodPasarela() {
		return codPasarela;
	}
	public void setCodPasarela(String codPasarela) {
		this.codPasarela = codPasarela;
	}
	public Transaccion getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
	
}
