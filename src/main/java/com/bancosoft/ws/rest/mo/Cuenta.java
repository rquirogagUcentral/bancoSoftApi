package com.bancosoft.ws.rest.mo;

import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.Facade.ICrudServices;

public class Cuenta implements ICrudServices{
	private String codBanco;
	private String tipoCuenta;
	private String codCuenta;
	private String idTitularCuenta;
	private String nombreTitularCuenta;
	private int saldo;
	
	ProductDAO pd =  new ProductDAO();
	
	public Cuenta(String codBanco, String tipoCuenta, String codCuenta, String idTitularCuenta,
			String nombreTitularCuenta, int saldo) {
		super();
		this.codBanco = codBanco;
		this.tipoCuenta = tipoCuenta;
		this.codCuenta = codCuenta;
		this.idTitularCuenta = idTitularCuenta;
		this.nombreTitularCuenta = nombreTitularCuenta;
		this.saldo = saldo;
	}
	public Cuenta() {
		super();
	}
	public String getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getCodCuenta() {
		return codCuenta;
	}
	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}
	public String getIdTitularCuenta() {
		return idTitularCuenta;
	}
	public void setIdTitularCuenta(String idTitularCuenta) {
		this.idTitularCuenta = idTitularCuenta;
	}
	public String getNombreTitularCuenta() {
		return nombreTitularCuenta;
	}
	public void setNombreTitularCuenta(String nombreTitularCuenta) {
		this.nombreTitularCuenta = nombreTitularCuenta;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	@Override
	public Object consutar(Object request) {
		// TODO Auto-generated method stub
		Cuenta cu = new Cuenta();
		Cuenta cuenta = new Cuenta();
		try
		{
			cuenta=(Cuenta) request;
			cu = pd.consultaCuenta(cuenta.getCodCuenta());
			if(cu.getCodCuenta()==null)
				cu=null;
		}
		catch(Exception e)
		{
			e.toString();
			cu=null;
		}
		return cu;
	}
	@Override
	public boolean crear(Object request) {
		// TODO Auto-generated method stub
		Cuenta cuenta = (Cuenta) request;
		boolean resultado = false;
		try
		{
			resultado = pd.crearCuenta(cuenta);
		}
		catch(Exception e)
		{
			e.toString();
			resultado = false;
		}
		
		return resultado;
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

