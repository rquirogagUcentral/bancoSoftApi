package com.bancosoft.ws.rest.mo;

import java.util.List;

import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.Facade.ICrudServices;

public class CrearPedidoRequest implements ICrudServices{
	private int id_recibo;
	private String fecha;
	private String cedula;
	private List<Orden> productos;
	private int valor_total;
	
	ProductDAO pd =  new ProductDAO();
	
	public CrearPedidoRequest() {
		super();
	}

	public CrearPedidoRequest(int id_recibo, String fecha, String cedula, List<Orden> productos, int valor_total) {
		super();
		this.id_recibo = id_recibo;
		this.fecha = fecha;
		this.cedula = cedula;
		this.productos = productos;
		this.valor_total = valor_total;
	}

	public int getId_recibo() {
		return id_recibo;
	}

	public void setId_recibo(int id_recibo) {
		this.id_recibo = id_recibo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<Orden> getProductos() {
		return productos;
	}

	public void setProductos(List<Orden> productos) {
		this.productos = productos;
	}

	public int getValor_total() {
		return valor_total;
	}

	public void setValor_total(int valor_total) {
		this.valor_total = valor_total;
	}

	@Override
	public Object consutar(Object request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CrearPedidoRequest> consultarPedidos()
	{
		//ObtenerProductos productos = new ObtenerProductos();
		List<CrearPedidoRequest> prods = null;
		prods = pd.consultaPedidos();
		
		return prods;
	}

	@Override
	public boolean crear(Object request) {
		
		CrearPedidoRequest req = (CrearPedidoRequest) request;  
		boolean result = false;
		int seq = 0;
		int cant_current = 0;
		try
		{
			seq = pd.getNextSeq();
			result = pd.insertarRecibo(seq, req.fecha, req.cedula, req.valor_total);
			
			if(result)
			{
				for(Orden orden: req.productos)
				{
					result = pd.insertarOrden(seq, orden.getProducto().getId_Producto(), orden.getCantidad(), orden.getValor_total());
					/* metodo para restar la cantidad de la tabla productos*/
					cant_current=pd.consultarcantidad(orden.getProducto().getId_Producto());
					/*Metodo para actualizar la cantidad de productos*/
					CrearProductoRequest actProd = new CrearProductoRequest();
					actProd.setCantidad(cant_current-orden.getCantidad());
					actProd.setDescripcion(orden.getProducto().getDescripcion());
					actProd.setPrecio(orden.getProducto().getPrecio());
					actProd.setId_Producto(orden.getProducto().getId_Producto());
					result = pd.editarProducto(actProd);
				}
			}
		}
		catch(Exception err)
		{
			err.toString();
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean actualizar(Object request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Object request) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
