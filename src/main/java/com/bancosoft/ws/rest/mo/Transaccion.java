package com.bancosoft.ws.rest.mo;

import java.util.Date;
import java.util.Properties;

import com.bancosoft.ws.rest.DAO.ProductDAO;
import com.bancosoft.ws.rest.util.PropertiesUtil;

public class Transaccion {
	private String idTransaccion;
	private int valor;
	private Date fechaTransaccion;
	private String descripcion;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	
	private static final String UF_PROPERTIES = "URLFront.properties";
	private static final String UF_HOST_PROP ="host";
	
	ProductDAO pd =  new ProductDAO();
	
	public Transaccion(String idTransaccion, int valor, Date fechaTransaccion, String descripcion, Cuenta cuentaOrigen,
			Cuenta cuentaDestino) {
		super();
		this.idTransaccion = idTransaccion;
		this.valor = valor;
		this.fechaTransaccion = fechaTransaccion;
		this.descripcion = descripcion;
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
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
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
					tpr.setEstado("OK");
					tpr.setLifetimeSecs("1234");
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
		
		try
		{
			tcr = pd.consultaTrans(request);
			//int saldo = (request.getIdAplicacion().equals("0")) ? 0 : 1000 ;
			
			if(tcr != null)
			{
				if(request.getCodPasarela().equals(tcr.getCodPasarela()) &&
					request.getReferencia().equals(tcr.getReferencia()))
				{
					tcr.setEstado("OK");
					tcr.setDescripcionEstado("Id de Transaccion encontrado ");
					/*tcr.setCodPasarela("1234");
					tcr.setReferencia("abc1234");
					tcr.setTransaccion(new Transaccion("10001",
							1000,
							new Date(),
							"Pago de algo jeeje",
							new Cuenta("1001","00","356-4353-876","10203040","Jessica Maria",saldo),
							new Cuenta("1001","01","32-6574-546","1032555896","Rosemberg JAJAJ",saldo)
							));*/
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
}
