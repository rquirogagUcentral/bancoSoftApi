package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.Facade.IGenerarTransaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class ControladorTransaccion {

	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		
		TransaccionPagoResponse rg = new TransaccionPagoResponse();
		
		switch(request.getRecursoId())
		{
			case "00":
				IGenerarTransaccion iCrudCrearTransaccion =  new Crear();
				rg = iCrudCrearTransaccion.crearTransaccion(request);
				break;
			case "01":
				IGenerarTransaccion iCrudNotificarTransaccion =  new Notificar();
				rg = iCrudNotificarTransaccion.crearTransaccion(request);
				break;
		}
		
		
		return rg;
	}

	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		return new Consulta().consultaTransaccion(request);
	}
}
