package com.bancosoft.ws.rest.mo;

public class TransaccionPagoResponse extends ResponseGenerico{
	private String urlRedirigir;
	private String lifetimeSecs;
	private String idTransaccion;
	
	public TransaccionPagoResponse() {
		super();
	}
	public TransaccionPagoResponse(String urlRedirigir, String lifetimeSecs, String idTransaccion) {
		super();
		this.urlRedirigir = urlRedirigir;
		this.lifetimeSecs = lifetimeSecs;
		this.idTransaccion = idTransaccion;
	}
	public String getUrlRedirigir() {
		return urlRedirigir;
	}
	public void setUrlRedirigir(String urlRedirigir) {
		this.urlRedirigir = urlRedirigir;
	}
	public String getLifetimeSecs() {
		return lifetimeSecs;
	}
	public void setLifetimeSecs(String lifetimeSecs) {
		this.lifetimeSecs = lifetimeSecs;
	}
	public String getidTransaccion() {
		return idTransaccion;
	}
	public void setidTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	
}
