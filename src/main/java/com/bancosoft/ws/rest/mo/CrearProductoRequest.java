package com.bancosoft.ws.rest.mo;



import java.util.List;

import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.Facade.ICrudServices;

public class CrearProductoRequest implements ICrudServices{
	private int id_Producto;
	private int precio;
	private int cantidad;
	private String descripcion;
	private boolean checked;
	
	ProductDAO pd =  new ProductDAO();
	
	public CrearProductoRequest() {
		super();
	}

	public CrearProductoRequest(int precio, int cantidad, String descripcion) {
		super();
		this.precio = precio;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	public CrearProductoRequest(int id_Producto, int precio, int cantidad, String descripcion) {
		super();
		this.id_Producto = id_Producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}
	
	
	
	public CrearProductoRequest(int id_Producto, int precio, int cantidad, String descripcion, boolean checked,
			ProductDAO pd) {
		super();
		this.id_Producto = id_Producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.checked = checked;
		this.pd = pd;
	}

	public int getId_Producto() {
		return id_Producto;
	}
	public void setId_Producto(int id_Producto) {
		this.id_Producto = id_Producto;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String nombre_Producto) {
		this.descripcion = nombre_Producto;
	}
	

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public Object consutar(Object request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObtenerProductos consultarProds()
	{
		ObtenerProductos productos = new ObtenerProductos();
		List<CrearProductoRequest> prods = null;
		prods = pd.consultaProductos();
		
		if(prods !=  null)
		{
			productos.setProductos(prods);
		}
		
		return productos;
	}

	public List<CrearProductoRequest> consultarProdsV2()
	{
		//ObtenerProductos productos = new ObtenerProductos();
		List<CrearProductoRequest> prods = null;
		prods = pd.consultaProductos();
		
		
		
		return prods;
	}
	
	public CrearProductoRequest consultarProdId(int request)
	{
		//ObtenerProductos productos = new ObtenerProductos();
		CrearProductoRequest prods = null;
		prods = pd.consultaProductoId(request);
		
		
		
		return prods;
	}
	
	public boolean eliminarProdId(int request)
	{
		//ObtenerProductos productos = new ObtenerProductos();
		boolean prods = false;
		prods = pd.eliminarProductoId(request);
		
		
		
		return prods;
	}
	
	@Override
	public boolean crear(Object request) {
		CrearProductoRequest producto = (CrearProductoRequest) request;
		boolean resultado = false;
		try
		{
			resultado = pd.crearCuenta(producto);
		}
		catch(Exception e)
		{
			e.toString();
			resultado = false;
		}
		
		return resultado;
	}

	@Override
	public boolean actualizar(Object request) {
		CrearProductoRequest producto = (CrearProductoRequest) request;
		boolean resultado = false;
		try
		{
			resultado = pd.editarProducto(producto);
		}
		catch(Exception e)
		{
			e.toString();
			resultado = false;
		}
		
		return resultado;
		
	}

	@Override
	public boolean eliminar(Object request) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
