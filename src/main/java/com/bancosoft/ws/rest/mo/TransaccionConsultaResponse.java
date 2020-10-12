package com.bancosoft.ws.rest.mo;

public class TransaccionConsultaResponse extends ResponseGenerico
{
	private String referencia;
	private String codPasarela;
	private Transaccion transaccion;
	public TransaccionConsultaResponse(String referencia, String codPasarela, Transaccion transaccion) {
		super();
		this.referencia = referencia;
		this.codPasarela = codPasarela;
		this.transaccion = transaccion;
	}
	public TransaccionConsultaResponse() {
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
