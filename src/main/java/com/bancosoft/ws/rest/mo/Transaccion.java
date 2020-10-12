package com.bancosoft.ws.rest.mo;

import java.util.Date;

public class Transaccion {
	private String idTransaccion;
	private int valor;
	private Date fechaTransaccion;
	private String descripcion;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	public Transaccion(String idTransaccion, int valor, Date fechaTransaccion, String descripcion, Cuenta cuentaOrigen,
			Cuenta cuentaDestino) {
		super();
		this.idTransaccion = idTransaccion;
		this.valor = valor;
		this.fechaTransaccion = fechaTransaccion;
		this.descripcion = descripcion;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}
	public Transaccion() {
		super();
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	
	
}
