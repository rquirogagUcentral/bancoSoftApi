package com.bancosoft.ws.rest.mo;

public class Orden {
	private CrearProductoRequest producto;
	private int cantidad;
	private int valor_total;
	public Orden(CrearProductoRequest producto, int cantidad, int valor_total) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.valor_total = valor_total;
	}
	public Orden() {
		super();
	}
	public CrearProductoRequest getProducto() {
		return producto;
	}
	public void setProducto(CrearProductoRequest producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValor_total() {
		return valor_total;
	}
	public void setValor_total(int valor_total) {
		this.valor_total = valor_total;
	}
	
	
	
}
