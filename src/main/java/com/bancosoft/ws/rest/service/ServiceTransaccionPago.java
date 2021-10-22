package com.bancosoft.ws.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bancosoft.ws.rest.Facade.ICrudServices;
import com.bancosoft.ws.rest.controller.ControladorTransaccion;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioRequest;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioResponse;
import com.bancosoft.ws.rest.mo.CrearPedidoRequest;
import com.bancosoft.ws.rest.mo.CrearProductoRequest;
import com.bancosoft.ws.rest.mo.CrearUsuarioRequest;
import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.ObtenerProductos;
import com.bancosoft.ws.rest.mo.PagoRequest;
import com.bancosoft.ws.rest.mo.ResponseGenerico;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionNotificacionRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;
import com.bancosoft.ws.rest.mo.Usuario;

@Path("/api")
public class ServiceTransaccionPago {
	
	private ResponseGenerico response;

	@POST
	@Path("/crearTransaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response crearTransaccion(TransaccionPagoRequest request)
	{
		String metodo = "Service.Transaccion.crearTransaccion";
		TransaccionPagoResponse response = null;
		ControladorTransaccion ct = new ControladorTransaccion();
		try
		{
			response = ct.crearTransaccion(request);
			if(response.getEstado().equals("CREADO"))
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			else
			{
				return Response.status(Response.Status.NOT_ACCEPTABLE)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
	}
	
	@POST
	@Path("/notificarTransaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response notificarTransaccion(TransaccionNotificacionRequest request)
	{
		TransaccionPagoResponse response = null;
		ControladorTransaccion ct = new ControladorTransaccion();
		try
		{
			response = ct.notificarTransaccion(request);
			if(response.getEstado().equals("OK"))
			{
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				return Response.status(Response.Status.NOT_ACCEPTABLE)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
	}
	
	@POST
	@Path("/consultaTransaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultaTransaccion(TransaccionConsultaRequest request)
	{
		TransaccionConsultaResponse response = null;
		
		try
		{
			response = new ControladorTransaccion().consultaTransaccion(request);
			switch (response.getEstado())
			{
				case "OK":
				case "CANCELADO":
				case "CREADO":
				case "EXPIRADO":
					return Response.status(Response.Status.OK)
							.header("Access-Control-Allow-Origin", "*")
						    .header("Access-Control-Allow-Credentials", "true")
						    .header("Access-Control-Allow-Headers",
						      "origin, content-type, accept, authorization")
						    .header("Access-Control-Allow-Methods", 
						        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
							.entity(response).build();
					
				default:
					return Response.status(Response.Status.NOT_FOUND)
							.header("Access-Control-Allow-Origin", "*")
						    .header("Access-Control-Allow-Credentials", "true")
						    .header("Access-Control-Allow-Headers",
						      "origin, content-type, accept, authorization")
						    .header("Access-Control-Allow-Methods", 
						        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
							.entity(response).build();
			}
			
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
	}
	
	@POST
	@Path("/consultaUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultaUsuario(ConsultaUsuarioRequest request)
	{
		ConsultaUsuarioResponse response = null;
		
		try
		{
			//response = new ControladorUsuario().consultaUsuario(request);
			
			ICrudServices iCrudUsuario =  new Usuario();
			response = (ConsultaUsuarioResponse) iCrudUsuario.consutar(request);
			if(response.getEstado().equals("OK") || 
					response.getEstado().equals("NO_AUTORIZADO"))
			{
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				return Response.status(Response.Status.NOT_FOUND)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
	}
	
	
	@GET
	@Path("/consultaUsuario/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response consultaUsuario(@PathParam("id") int request)
	{
		ConsultaUsuarioResponse response = null;
		ConsultaUsuarioResponse usuario = new ConsultaUsuarioResponse();
		Usuario usu= new Usuario();
		
		try
		{
			usuario = usu.ConsultaUsuario(request);
			
			if(usuario.getEstado().equals("OK"))
			{
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(usuario).build();
			}
			else
			{
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(usuario).build();
			}
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(usuario).build();
		}
		
	}
	
	
	@POST
	@Path("/pagarTransaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response pagarTransaccion(PagoRequest request)
	{
		ResponseGenerico response = new ResponseGenerico();
		String resultado = null;
		try
		{
			
			resultado =  new ControladorTransaccion().pagarTransaccion(request);
			request.setEstado(resultado);
			switch (request.getEstado())
			{
				case "OK":
					response.setEstado("OK");
					response.setDescripcionEstado("Pago finalizado correctamente");
					break;
				case "FALLIDO":
					response.setEstado("FALLIDO");
					response.setDescripcionEstado("El pago no se ha podido finalizar, intente nuevamente.");
					break;
				case "EXPIRADO":
					response.setEstado("EXPIRADO");
					response.setDescripcionEstado("El pago no se ha podido finalizar, intente nuevamente.");
					break;
				case "CANCELADO":
					response.setEstado("CANCELADO");
					response.setDescripcionEstado("El pago no se ha podido finalizar, intente nuevamente.");
					break;
			}
			return Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
			
		}
		catch(Exception e)
		{
			e.toString();
			response.setEstado("FALLIDO");
			response.setDescripcionEstado("El pago no se ha podido finalizar, intente nuevamente. ");
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
	
	}
	
	@POST
	@Path("/crearUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response crearUsuario(CrearUsuarioRequest request)
	{
		ResponseGenerico response = new ResponseGenerico();
		boolean resultado = false;
		Usuario usuario = new Usuario();
		Cuenta cuenta = new Cuenta();
		ConsultaUsuarioRequest cur = new ConsultaUsuarioRequest();
		ConsultaUsuarioResponse curs = new ConsultaUsuarioResponse();
		List<Cuenta> cu = new ArrayList<Cuenta>();
		boolean existe = false;
		
		try
		{
			
			usuario = request.getUsuario();
			cuenta = request.getCuenta();
			
			cuenta.setTipoCuenta(Integer.parseInt(cuenta.getTipoCuenta())+"");
			cur.setId(usuario.getId());
			cur.setContrasena(usuario.getContrasena());
			cur.setTipoDocumento(usuario.getTipoDocumento());
			
			
			ICrudServices iCrudUsuario =  new Usuario();
			curs = (ConsultaUsuarioResponse) iCrudUsuario.consutar(cur);
			cu=curs.getCuentas();
			if(cu != null)
			{
				existe = true;
				for (Cuenta cue: cu)
				{
					existe = (cue.getCodCuenta().equals(cuenta.getCodCuenta()));
					if (existe)
						break;
				}	
				
				if(!existe)
				{
					ICrudServices iCrudCuenta =  new Cuenta();
					resultado = iCrudCuenta.crear(cuenta);
				}
			}
			else
			{
				resultado = iCrudUsuario.crear(usuario);
				if(resultado)
				{
					ICrudServices iCrudCuenta =  new Cuenta();
					resultado = iCrudCuenta.crear(cuenta);
				}
			}
			
			if(resultado)
			{
				response.setEstado("OK");
				response.setDescripcionEstado("Creación de cuenta generada con Éxito");
				
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				response.setEstado("FALLIDO");
				response.setDescripcionEstado("Creación de cuenta no se pudo generar, Usuario o Cuenta ya existente ");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			
			
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
		
	}
	
	@POST
	@Path("/crearProducto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response crearProducto(CrearProductoRequest request)
	{
		ResponseGenerico response = new ResponseGenerico();
		boolean resultado = false;
		
		try
		{
			
			ICrudServices iCrudProducto =  new CrearProductoRequest();
			resultado = iCrudProducto.crear(request);
			
			if(resultado)
			{
				response.setEstado("OK");
				response.setDescripcionEstado("Creación de cuenta generada con Éxito");
				
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				response.setEstado("FALLIDO");
				response.setDescripcionEstado("Creación de cuenta no se pudo generar, Usuario o Cuenta ya existente ");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			
			
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
		
	}
	
	@GET
	@Path("/crearProducto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response obtenerProducto()
	{
		ObtenerProductos response = new ObtenerProductos();
		
		
		try
		{
			
			CrearProductoRequest op = new CrearProductoRequest();
			response = op.consultarProds(); 
			
			return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			
			
			
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
		
	}
	
	@GET
	@Path("/crearProductos")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CrearProductoRequest> obtenerProductos()
	{
		List<CrearProductoRequest> response = null;
		
		
		try
		{
			
			CrearProductoRequest op = new CrearProductoRequest();
			response = op.consultarProdsV2(); 
			
			
			return response;
			
			
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	@GET
	@Path("/crearProductos/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CrearProductoRequest obtenerProductoId(@PathParam("id") int request)
	{
		CrearProductoRequest response = null;
		try
		{
			CrearProductoRequest op = new CrearProductoRequest();
			response = op.consultarProdId(request); 
			
			return response;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	@POST
	@Path("/editarProducto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response editarProducto(CrearProductoRequest request)
	{
		ResponseGenerico response = new ResponseGenerico();
		boolean resultado = false;
		
		try
		{
			
			ICrudServices iCrudProducto =  new CrearProductoRequest();
			resultado = iCrudProducto.actualizar(request);
			
			if(resultado)
			{
				response.setEstado("OK");
				response.setDescripcionEstado("Creación de cuenta generada con Éxito");
				
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				response.setEstado("FALLIDO");
				response.setDescripcionEstado("Creación de cuenta no se pudo generar, Usuario o Cuenta ya existente ");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			
			
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
		
	}
	
	@DELETE
	@Path("/editarProductos/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response eliminarProductoId(@PathParam("id") int request)
	{
		ResponseGenerico response = new ResponseGenerico();
		boolean result= false;
		try
		{
			CrearProductoRequest op = new CrearProductoRequest();
			result = (boolean) op.eliminarProdId(request); 
			
			if(result)
			{
				response.setEstado("OK");
				response.setDescripcionEstado("Eliminado correctamente");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				response.setEstado("FAILED");
				response.setDescripcionEstado("No se logra eliminar correctamente");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			
			
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	@POST
	@Path("/Pedido")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response crearPedido(CrearPedidoRequest request)
	{
		ResponseGenerico response = new ResponseGenerico();
		boolean resultado = false;
		
		try
		{
			
			ICrudServices iCrudPedido =  new CrearPedidoRequest();
			resultado = iCrudPedido.crear(request);
			
			if(resultado)
			{
				response.setEstado("OK");
				response.setDescripcionEstado("Creación de cuenta generada con Éxito");
				
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			else
			{
				response.setEstado("FALLIDO");
				response.setDescripcionEstado("Creación de cuenta no se pudo generar, Usuario o Cuenta ya existente ");
				return Response.status(Response.Status.OK)
						.header("Access-Control-Allow-Origin", "*")
					    .header("Access-Control-Allow-Credentials", "true")
					    .header("Access-Control-Allow-Headers",
					      "origin, content-type, accept, authorization")
					    .header("Access-Control-Allow-Methods", 
					        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
						.entity(response).build();
			}
			
			
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				      "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(response).build();
		}
		
	}
	
	@GET
	@Path("/Pedido")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<CrearPedidoRequest> obtenerPedidos()
	{
		List<CrearPedidoRequest> response = null;
		
		try
		{
			CrearPedidoRequest op = new CrearPedidoRequest();
			response = op.consultarPedidos(); 
			
			return response;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	@GET
	@Path("/Pedido/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CrearProductoRequest obtenerPedidoId(@PathParam("id") int request)
	{
		CrearProductoRequest response = null;
		try
		{
			CrearProductoRequest op = new CrearProductoRequest();
			response = op.consultarProdId(request); 
			
			return response;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
}
