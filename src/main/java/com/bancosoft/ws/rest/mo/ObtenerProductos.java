package com.bancosoft.ws.rest.mo;

import java.util.List;

public class ObtenerProductos {
	private List<CrearProductoRequest> productos;

	public ObtenerProductos(List<CrearProductoRequest> productos) {
		super();
		this.productos = productos;
	}

	public ObtenerProductos() {
		super();
	}

	public List<CrearProductoRequest> getProductos() {
		return productos;
	}

	public void setProductos(List<CrearProductoRequest> productos) {
		this.productos = productos;
	}
	
	
}
