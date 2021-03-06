package com.bancosoft.ws.rest.mo;

import java.util.Date;

public class PagoRequest {
	private String idTransaccion;
	private String estado;
	private String fechaTransaccion;
	private Cuenta cuentaOrigen;
	public PagoRequest(String idTransaccion, String estado, String fechaTransaccion, Cuenta cuentaOrigen) {
		super();
		this.idTransaccion = idTransaccion;
		this.estado = estado;
		this.fechaTransaccion = fechaTransaccion;
		this.cuentaOrigen = cuentaOrigen;
	}
	public PagoRequest() {
		super();
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	
	
}
