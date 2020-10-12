package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionNotificacionRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class ControladorTransaccion {

	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		
		return new Transacciones().crearTransaccion(request);
	}

	public TransaccionPagoResponse notificarTransaccion(TransaccionNotificacionRequest request) {
		
		return  new Transacciones().notificarTransaccion(request);
	}
	
	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		return new Transacciones().consultaTransaccion(request);
	}
}
