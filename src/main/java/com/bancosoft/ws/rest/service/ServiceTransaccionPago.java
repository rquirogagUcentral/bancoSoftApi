package com.bancosoft.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bancosoft.ws.rest.controller.ControladorTransaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

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
			if(response.getEstado().equals("OK"))
				return Response.status(Response.Status.OK).entity(response).build();
			else
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
		}
		catch(Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
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
			if(response.getEstado().equals("OK"))
				return Response.status(Response.Status.OK).entity(response).build();
			else
				return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
		}
	}
}
