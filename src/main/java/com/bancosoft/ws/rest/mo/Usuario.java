package com.bancosoft.ws.rest.mo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.bancosoft.ws.rest.Facade.ICrudServices;

import com.bancosoft.ws.rest.DAO.Singleton;

public class Usuario implements ICrudServices{
	private int id;
	private String tipoDocumento;
	private String contrasena;
	private String tipoUsuario;
	private String nombre;
	private String apellido;
	private String correo;
	public Usuario(int id, String tipoDocumento, String contrasena, String tipoUsuario, String nombre, String apellido,
			String correo) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	public Usuario() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public Object consutar(Object request) {
		ConsultaUsuarioResponse cur = new ConsultaUsuarioResponse();
		ConsultaUsuarioRequest cu = new ConsultaUsuarioRequest(); 
		
		cu=(ConsultaUsuarioRequest) request;
		
		List<Cuenta> Cuentas = new ArrayList<Cuenta>();
		
		
		Cuenta cuentaA = new Cuenta("1001", "00", "234-5678-89", "12340934", "Leidy Johana Llanos Culma", 100);
		Cuenta cuentaB = new Cuenta("1001", "00", "234-982348-84", "12340934", "Leidy Johana Llanos Culma", 500);
		
		cur.setEstado("OK");
		cur.setUsuario(new Usuario(103234434,"CC","","00","Leidy Johanna","Llanos Culma","lllanosc@uceuntal.edu.co"));
		Cuentas.add(cuentaA);
		Cuentas.add(cuentaB);
		cur.setCuentas(Cuentas);
		
		Connection connection=Singleton.getConnection();
		
		return cur;
		
	}
	@Override
	public boolean crear(Object request) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean actualizar(Object request) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean eliminar(Object request) {
		// TODO Auto-generated method stub
		return false;
	}
}
