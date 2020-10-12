package com.bancosoft.ws.rest.mo;

public class Cuenta {
	private String codBanco;
	private String tipoCuenta;
	private String codCuenta;
	private String idTitularCuenta;
	private String nombreTitularCuenta;
	public Cuenta(String codBanco, String tipoCuenta, String codCuenta, String idTitularCuenta,
			String nombreTitularCuenta) {
		super();
		this.codBanco = codBanco;
		this.tipoCuenta = tipoCuenta;
		this.codCuenta = codCuenta;
		this.idTitularCuenta = idTitularCuenta;
		this.nombreTitularCuenta = nombreTitularCuenta;
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
	
	
}

