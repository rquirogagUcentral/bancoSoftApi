package com.bancosoft.ws.rest.mo;

public class TransaccionPagoRequest {
	private String codPasarela;
	private String referencia;
	private String urlRetorno;
	private OrigenComercio origenComercio;
	private Cuenta destinoComercio;
	
	public TransaccionPagoRequest(String codPasarela, String referencia, String urlRetorno,
			OrigenComercio origenComercio, Cuenta destinoComercio) {
		super();
		this.codPasarela = codPasarela;
		this.referencia = referencia;
		this.urlRetorno = urlRetorno;
		this.origenComercio = origenComercio;
		this.destinoComercio = destinoComercio;
	}
	public TransaccionPagoRequest() {
		super();
	}
	public String getCodPasarela() {
		return codPasarela;
	}
	public void setCodPasarela(String codPasarela) {
		this.codPasarela = codPasarela;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getUrlRetorno() {
		return urlRetorno;
	}
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}
	public OrigenComercio getOrigenComercio() {
		return origenComercio;
	}
	public void setOrigenComercio(OrigenComercio origenComercio) {
		this.origenComercio = origenComercio;
	}
	public Cuenta getDestinoComercio() {
		return destinoComercio;
	}
	public void setDestinoComercio(Cuenta destinoComercio) {
		this.destinoComercio = destinoComercio;
	}	
}
