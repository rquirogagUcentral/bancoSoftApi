package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.mo.PagoRequest;
import com.bancosoft.ws.rest.mo.Transaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionNotificacionRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class ControladorTransaccion {

	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		
		return new Transaccion().crearTransaccion(request);
	}

	public TransaccionPagoResponse notificarTransaccion(TransaccionNotificacionRequest request) {
		
		return  new Transaccion().notificarTransaccion(request);
	}
	
	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		return new Transaccion().consultaTransaccion(request);
	}
	
	public boolean pagarTransaccion(PagoRequest request)
	{
		return new Transaccion().pagarTransaccion(request);
	}
}
