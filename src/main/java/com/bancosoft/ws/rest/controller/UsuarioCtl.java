package com.bancosoft.ws.rest.controller;

import java.util.ArrayList;
import java.util.List;

import com.bancosoft.ws.rest.mo.ConsultaUsuarioRequest;
import com.bancosoft.ws.rest.mo.ConsultaUsuarioResponse;
import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.Usuario;

public class UsuarioCtl {

	public ConsultaUsuarioResponse consultaUsuario(ConsultaUsuarioRequest request) {
		// TODO Auto-generated method stub
		ConsultaUsuarioResponse cur = new ConsultaUsuarioResponse();
		List<Cuenta> Cuentas = new ArrayList<Cuenta>();
		
		
		Cuenta cuentaA = new Cuenta("1001", "00", "234-5678-89", "12340934", "Leidy Johana Llanos Culma", 100);
		Cuenta cuentaB = new Cuenta("1001", "00", "234-982348-84", "12340934", "Leidy Johana Llanos Culma", 500);
		
		cur.setEstado("OK");
		cur.setUsuario(new Usuario(103234434,"CC","","00","Leidy Johana","Llanos Culma","lllanosc@uceuntal.edu.co"));
		Cuentas.add(cuentaA);
		Cuentas.add(cuentaB);
		cur.setCuentas(Cuentas);
		
		return cur;
	}

}
