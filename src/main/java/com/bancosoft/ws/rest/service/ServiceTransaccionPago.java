package com.bancosoft.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bancosoft.ws.rest.Facade.ICrudServices;
import com.bancosoft.ws.rest.controller.ControladorTransaccion;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioRequest;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioResponse;
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
	
	@POST
	@Path("/crearTransaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response crearTransaccion(TransaccionPagoRequest request)
	{
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
}
