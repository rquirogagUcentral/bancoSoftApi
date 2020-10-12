package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.Facade.IGenerarTransaccion;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class Crear implements IGenerarTransaccion{

	@Override
	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		// TODO Auto-generated method stub
		TransaccionPagoResponse tpr = new TransaccionPagoResponse();
		tpr.setDescripcionEstado("Transaccion creada correctamente");
		tpr.setEstado("OK");
		tpr.setLifetimeSecs("1234");
		tpr.setUrlRedirigir("http://localhost:8080/PaginaPago.html?token=asdFASdfasuasfbasbfalsidfsasf9087872389");
		tpr.setidTransaccion("1001");
		return tpr;
	}

	

}
