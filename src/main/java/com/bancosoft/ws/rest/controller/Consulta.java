package com.bancosoft.ws.rest.controller;

import java.util.Date;

import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.Transaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;

public class Consulta {
	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		TransaccionConsultaResponse tcr = new TransaccionConsultaResponse();
		
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
					new Cuenta("1001","00","356-4353-876","10203040","Jessica M"),
					new Cuenta("1001","01","32-6574-546","1032555896","Rosemberg")
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
