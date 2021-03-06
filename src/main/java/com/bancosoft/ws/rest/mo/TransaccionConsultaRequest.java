package com.bancosoft.ws.rest.mo;

public class TransaccionConsultaRequest {
	private String referencia;
	private String codPasarela;
	private String idAplicacion;
	
	public TransaccionConsultaRequest(String referencia, String codPasarela, String idAplicacion) {
		super();
		this.referencia = referencia;
		this.codPasarela = codPasarela;
		this.idAplicacion = idAplicacion;
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

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	
	
	
}
