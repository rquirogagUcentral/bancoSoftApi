package com.bancosoft.ws.rest.Facade;

import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.TransaccionPagoResponse;

public interface IGenerarTransaccion {
	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request);
	
}
