package com.bancosoft.ws.rest.mo;

public class TransaccionConsultaRequest {
	private String referencia;
	private String codPasarela;
	
	public TransaccionConsultaRequest(String referencia, String codPasarela) {
		super();
		this.referencia = referencia;
		this.codPasarela = codPasarela;
	}

	public TransaccionConsultaRequest() {
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
	
	
	
}
