package com.bancosoft.ws.rest.mo;

public class TransaccionPagoRequest {
	private String codPasarela;
	private String referencia;
	private String urlRetorno;
	private OrigenComercio origenComercio;
	private DestinoComercio destinoComercio;
	private String recursoId;
	public TransaccionPagoRequest(String codPasarela, String referencia, String urlRetorno,
			OrigenComercio origenComercio, DestinoComercio destinoComercio, String recursoId) {
		super();
		this.codPasarela = codPasarela;
		this.referencia = referencia;
		this.urlRetorno = urlRetorno;
		this.origenComercio = origenComercio;
		this.destinoComercio = destinoComercio;
		this.recursoId = recursoId;
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
	public DestinoComercio getDestinoComercio() {
		return destinoComercio;
	}
	public void setDestinoComercio(DestinoComercio destinoComercio) {
		this.destinoComercio = destinoComercio;
	}
	public String getRecursoId() {
		return recursoId;
	}
	public void setRecursoId(String recursoId) {
		this.recursoId = recursoId;
	}
	
}
