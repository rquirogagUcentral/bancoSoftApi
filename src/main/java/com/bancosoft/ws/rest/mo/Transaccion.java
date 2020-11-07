package com.bancosoft.ws.rest.mo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.util.PropertiesUtil;

public class Transaccion {
	private String idTransaccion;
	private int valor;
	private String fechaTransaccion;
	private String descripcion;
	private String urlRetorno;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private int uso;
	
	private static final String UF_PROPERTIES = "URLFront.properties";
	private static final String UF_HOST_PROP ="host";
	
	ProductDAO pd =  new ProductDAO();
	PropertiesUtil pu = new PropertiesUtil();
	
	public Transaccion(String idTransaccion, int valor, String fechaTransaccion, String descripcion, String urlRetorno,  Cuenta cuentaOrigen,
			Cuenta cuentaDestino) {
		super();
		this.idTransaccion = idTransaccion;
		this.valor = valor;
		this.fechaTransaccion = fechaTransaccion;
		this.descripcion = descripcion;
		this.urlRetorno = urlRetorno;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}
	public Transaccion() {
		super();
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String getUrlRetorno() {
		return urlRetorno;
	}
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}
	public int getUso() {
		return uso;
	}
	public void setUso(int uso) {
		this.uso = uso;
	}
	public TransaccionPagoResponse crearTransaccion(TransaccionPagoRequest request) {
		TransaccionPagoResponse tpr = new TransaccionPagoResponse();
		
		String metodo = "crearTransaccion";
		boolean existePasarela;
		boolean insMov;
		boolean insMovCu;
		int codTransaccion;
		try
		{
			existePasarela = pd.consultarPasarela(request.getCodPasarela());
			if(existePasarela)
			{
				/*Preparando los datos para retornar el consumo a la plataforma*/
				codTransaccion = pd.proxCodTransaccion(metodo);
				Properties prop = PropertiesUtil.loadProperty(UF_PROPERTIES);
		        String host = prop.getProperty(UF_HOST_PROP);
		        
		        /*Ingreso de la transacción en la base de datos tablas : movimientos, movimiento_cuentas, */
		        insMov = pd.InsertaMovimiento(codTransaccion, request);
		        //insMovCu = pd.InsertaMovimientoCuenta(codTransaccion, request);
		        if(insMov)
		        {
		        	tpr.setDescripcionEstado("Transaccion creada correctamente");
					tpr.setEstado("CREADO");
					tpr.setLifetimeSecs("480 Seg");
					tpr.setUrlRedirigir(host + "codPasarela="+request.getCodPasarela()+"&referencia="+request.getReferencia());
					tpr.setidTransaccion(String.valueOf(codTransaccion));
		        }
		        else
		        {
		        	tpr.setDescripcionEstado("ERROR: no se logra crear la transacción correctamente.");
					tpr.setEstado("FALLIDO");
		        }
				
				
			}
			else
			{
				tpr.setDescripcionEstado("La pasarela no existe");
				tpr.setEstado("NO_AUTORIZADO");
			}
			return tpr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tpr.setDescripcionEstado("Error al generar la transacción.");
			tpr.setEstado("FALLIDO");
			return tpr;
		}
	}
	
	public TransaccionPagoResponse notificarTransaccion(TransaccionNotificacionRequest request) {
		// TODO Auto-generated method stub
		TransaccionPagoResponse tpr = new TransaccionPagoResponse();
		tpr.setDescripcionEstado("Notificacion generada correctamente ticket numero: 13455");
		tpr.setEstado("OK");
		tpr.setLifetimeSecs("");
		tpr.setidTransaccion("1002");
		return tpr;
	}
	
	public TransaccionConsultaResponse consultaTransaccion(TransaccionConsultaRequest request)
	{
		TransaccionConsultaResponse tcr = new TransaccionConsultaResponse();
		uso = 0;
		try
		{
			
			tcr = pd.consultaTrans(request);
			
			if(tcr != null)
			{
				if(request.getCodPasarela().equals(tcr.getCodPasarela()) &&
					request.getReferencia().equals(tcr.getReferencia()))
				{
					if (tcr.getEstado() == null)
						tcr.setEstado("");
					//true --> transaccion Expirada, false --> transaccion por responder 270 seg
					/*if(pu.expiraTransaccion(tcr.getTransaccion().getFechaTransaccion()))
					{
						tcr.setEstado("EXPIRADO");
						pd.actualizarMovimiento(tcr.getTransaccion().getIdTransaccion(), tcr.getEstado(), null);
					}*/
					
					tcr.setDescripcionEstado("Id de Transaccion encontrado");
				}
				else
				{
					tcr.setEstado("NOT_FOUND");
					tcr.setDescripcionEstado("No se encuentra transacción con la información indicada");
				}
			}
			else
			{
				tcr.setEstado("NOT_FOUND");
				tcr.setDescripcionEstado("ERROR: Se presento alguna falla en la consulta o no se encontro registro de Transaccion");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			tcr = null;
		}
		
					
		return tcr;
	}
	
	public String pagarTransaccion(PagoRequest request) {
		String strMetodo = "pagarTransaccion";
		List<Cuenta> cu = new ArrayList<Cuenta>();
		Cuenta objCuenta = new Cuenta();
		Transaccion tx = new Transaccion();
		int id = 0;
		boolean resultado = false;
		String devEstado = null;
		try
		{
			tx = pd.consultaTransaccion(request.getIdTransaccion());
//			if (pu.expiraTransaccion(request.getFechaTransaccion()))
//				request.setEstado("EXPIRADO");
			//id = Integer.parseInt(request.getCuentaOrigen().getIdTitularCuenta());
			
			/*Se Consulta de las cuentas del usuario , la cuenta que esta siendo afectada en la transacción*/
			cu = pd.consultaCuentas(Integer.parseInt(request.getCuentaOrigen().getIdTitularCuenta()), request.getCuentaOrigen().getNombreTitularCuenta());
			for(Cuenta cuenta: cu)
			{
				if(cuenta.getCodCuenta().equals(request.getCuentaOrigen().getCodCuenta()))
				{
					objCuenta.setCodBanco(cuenta.getCodBanco());
					objCuenta.setCodCuenta(cuenta.getCodCuenta());
					objCuenta.setIdTitularCuenta(cuenta.getIdTitularCuenta());
					objCuenta.setNombreTitularCuenta(cuenta.getNombreTitularCuenta());
					objCuenta.setSaldo(cuenta.getSaldo());
					objCuenta.setTipoCuenta(cuenta.getTipoCuenta());
				}
			}
			
			
			//1. De acuerdo al estado envíado , se actualizan las tablas
			switch (request.getEstado())
			{
				case "CANCELADO":
					resultado = pd.actualizarMovimiento(request.getIdTransaccion(),request.getEstado(), objCuenta);
					if (resultado)
						devEstado = "CANCELADO";
					else
						devEstado = "FALLIDO";
					break;
				case "OK":
					resultado = pd.actualizarMovimiento(request.getIdTransaccion(),request.getEstado(), objCuenta);
					//2. Si el estado es OK se busca la cuenta y se actualiza el valor .
					if(resultado)
					{
						//tipo de transaccion : 0 --> debita en cuenta, 1 --> deposita en cuenta 
						int tipoTransaccion = 0;
						resultado = pd.actualizarCuenta(objCuenta,tipoTransaccion,tx.getValor());
						if (resultado)
							devEstado = "OK";
						else
							devEstado = "FALLIDO";
						break;
					}
					break;
				case "EXPIDADO":
					resultado = pd.actualizarMovimiento(request.getIdTransaccion(),request.getEstado(), objCuenta);
					if (resultado)
						devEstado = "CANCELADO";
					else
						devEstado = "FALLIDO";
					break;
			}			
		}
		catch(Exception e)
		{
			e.toString();
			devEstado = null;
		}
		return devEstado;
	}
}
