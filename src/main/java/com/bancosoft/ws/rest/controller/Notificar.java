package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.Facade.IGenerarTransaccion;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class Notificar implements IGenerarTransaccion{

	@Override
	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		// TODO Auto-generated method stub
		TransaccionPagoResponse tpr = new TransaccionPagoResponse();
		tpr.setDescripcionEstado("Notificacion generada correctamente ticket numero: 13455");
		tpr.setEstado("OK");
		tpr.setLifetimeSecs("1234");
		tpr.setidTransaccion("1002");
		return tpr;
	}

}
