package com.bancosoft.ws.rest.controller;

import com.bancosoft.ws.rest.mo.ConsultaUsuarioRequest;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioResponse;

public class ControladorUsuario {

	public ConsultaUsuarioResponse consultaUsuario(ConsultaUsuarioRequest request) {
		// TODO Auto-generated method stub
		return new UsuarioCtl().consultaUsuario(request);
	}

}
