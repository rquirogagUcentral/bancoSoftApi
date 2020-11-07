package com.bancosoft.ws.rest.mo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.bancosoft.ws.rest.Facade.ICrudServices;
import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.DAO.Singleton;

public class Usuario implements ICrudServices{
	private int id;
	private String tipoDocumento;
	private String contrasena;
	private String tipoUsuario;
	private String nombre;
	private String apellido;
	private String correo;
	
	ProductDAO pd =  new ProductDAO();
	
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
	
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Usuario usuario = new Usuario();
		
		cu=(ConsultaUsuarioRequest) request;
		boolean existe = false;
		String nombreConcat = null;
		try
		{
			
			usuario= pd.consultaUsuario(cu.getId());
			
			existe = (usuario != null) ? (cu.getId() == usuario.getId())? true : false : false;
			if(existe)
			{
				if(cu.getContrasena().equals(usuario.getContrasena()))
				{
					if(cu.getTipoDocumento().equals(usuario.getTipoDocumento()))
					{
						nombreConcat = usuario.getNombre() + " " + usuario.getApellido();
						cuentas = pd.consultaCuentas(cu.getId(), nombreConcat);
						
						cur.setEstado("OK");
						cur.setDescripcionEstado("Consulta Exitosa");
						cur.setUsuario(usuario);
						cur.setCuentas(cuentas);
					}
					else
					{
						cur.setEstado("NO_AUTORIZADO");
						cur.setDescripcionEstado("ERROR: No coincide el tipo de Documento, por favor valide de nuevo");
					}
				}
				else
				{
					cur.setEstado("NO_AUTORIZADO");
					cur.setDescripcionEstado("ERROR: No coincide la contraseña, por favor valide de nuevo");
				}
			}
			else
			{
				cur.setEstado("NO_AUTORIZADO");
				cur.setDescripcionEstado("ERROR: No se encuentra el usuario inscrito en la base de datos");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			cur = null;
		}
		
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
