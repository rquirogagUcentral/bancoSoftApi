package com.bancosoft.ws.rest.controller;

import java.util.Date;

import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.Transaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionNotificacionRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public class Transacciones {

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

	public TransaccionPagoResponse notificarTransaccion(TransaccionNotificacionRequest request) {
		// TODO Auto-generated method stub
		TransaccionPagoResponse tpr = new TransaccionPagoResponse();
		tpr.setDescripcionEstado("Notificacion generada correctamente ticket numero: 13455");
		tpr.setEstado("OK");
		tpr.setLifetimeSecs("1234");
		tpr.setidTransaccion("1002");
		return tpr;
	}
	
	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		TransaccionConsultaResponse tcr = new TransaccionConsultaResponse();
		int saldo = (request.getIdAplicacion().equals("0")) ? 0 : 1000 ;
		
		if(request.getCodPasarela().equals("1234") &&
				request.getReferencia().equals("abc1234"))
		{
			tcr.setEstado("OK");
			tcr.setDescripcionEstado("Id de Transaccion encontrado ");
			tcr.setCodPasarela("1234");
			tcr.setReferencia("abc1234");
			tcr.setTransaccion(new Transaccion("10001",
					1000,
					new Date(),
					"Pago de algo jeeje",
					new Cuenta("1001","00","356-4353-876","10203040","Jessica M",saldo),
					new Cuenta("1001","01","32-6574-546","1032555896","Rosemberg",saldo)
					));
		}
		else
		{
			tcr.setEstado("NOT_FOUND");
			tcr.setDescripcionEstado("No se encontro registro de Transaccion");
		}
			
		return tcr;
	}
}
