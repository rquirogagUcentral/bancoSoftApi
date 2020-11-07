package com.bancosoft.ws.rest.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.ExecutionException;

import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.Transaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.Usuario;
import com.bancosoft.ws.rest.util.PropertiesUtil;
import com.sun.jersey.server.impl.model.parameter.multivalued.StringReaderProviders.TypeValueOf;

public class ProductDAO {

	//region ProductDao-Transacciones
	PropertiesUtil pu = new PropertiesUtil();
	
	
	public boolean consultarPasarela(String codPasarela) {
		Connection connection = Singleton.cadenaConexion();
		//Connection connection=Singleton.getConnection();
		int codigo = 0;
		try
		{
			int codPas = Integer.parseInt(codPasarela);
			PreparedStatement st = connection.prepareStatement("SELECT CODIGO_PASARELA FROM PASARELA WHERE CODIGO_PASARELA = ?");
			st.setInt(1, codPas);
			ResultSet rst= st.executeQuery();
			while(rst.next())
			{
				codigo = rst.getInt(1);
			}
			
			
			if(codigo == codPas)
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int proxCodTransaccion(String metodo) {
		
		//Connection connection=Singleton.getConnection();
		Connection connection = Singleton.cadenaConexion();
		
		int proximoCod = 0;
		try
		{
			switch (metodo) 
			{
				case "crearTransaccion":
					PreparedStatement st = connection.prepareStatement("SELECT ID_MOVIMIENTO FROM MOVIMIENTOS ORDER BY ID_MOVIMIENTO DESC LIMIT 1");
					ResultSet rs= st.executeQuery();
					while(rs.next())
					{
						proximoCod = rs.getInt(1);
					}
					break;
				case "crearCuenta":
					PreparedStatement sta = connection.prepareStatement("SELECT ID FROM CUENTAS ORDER BY ID DESC LIM 1");
					ResultSet rst= sta.executeQuery();
					while(rst.next())
					{
						proximoCod = rst.getInt(1);
					}
					break;
			}
			return proximoCod + 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public boolean InsertaMovimiento(int codTransaccion, TransaccionPagoRequest request) {
		//Connection connection=Singleton.getConnection();
		Connection connection = Singleton.cadenaConexion();
		boolean resultado;
		
		try
		{
			
			/*Ajusta la fecha*/
			Date dt= new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
			String currentTime = sdf.format(dt);
			
			PreparedStatement ps =  connection.prepareStatement("INSERT INTO MOVIMIENTOS VALUES (?,?,?,?,?,?,?,?,?,?,?);");
			ps.setInt(1, codTransaccion);
			ps.setDouble(2, request.getOrigenComercio().getValor());
			ps.setTimestamp(3, java.sql.Timestamp.valueOf(currentTime));
			ps.setString(4, request.getOrigenComercio().getDescripcion());
			ps.setInt(5, 0);
			ps.setString(6, request.getCodPasarela());
			ps.setString(7, request.getReferencia());
			ps.setString(8, request.getUrlRetorno());
			ps.setString(9, request.getOrigenComercio().getRefComercio());
			ps.setString(10, "CREADO");
			ps.setInt(11, 1);
			ps.executeUpdate();
			resultado =  true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultado = false;
		}
		
		
		if (resultado)
		{
			try
			{
				PreparedStatement ps =  connection.prepareStatement("INSERT INTO MOVIMIENTO_CUENTAS VALUES (?,?,?,?,?,?,?,?,?,?,?);");
				ps.setInt(1, codTransaccion);
				ps.setString(2, request.getDestinoComercio().getCodBanco());
				ps.setString(3, request.getDestinoComercio().getTipoCuenta());
				ps.setString(4, request.getDestinoComercio().getCodCuenta());
				ps.setString(5, request.getDestinoComercio().getIdTitularCuenta());
				ps.setString(6, request.getDestinoComercio().getNombreTitularCuenta());
				ps.setString(7, null);
				ps.setString(8, null);
				ps.setString(9, null);
				ps.setString(10, null);
				ps.setString(11, null);
				ps.executeUpdate();
				resultado =  true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				resultado = false;
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return resultado;
	}

	public TransaccionConsultaResponse consultaTrans(TransaccionConsultaRequest request) {
		
		TransaccionConsultaResponse tcr = new TransaccionConsultaResponse();
		Transaccion tx = new Transaccion();
		Cuenta origen = new Cuenta();
		Cuenta destino = new Cuenta();
		//Connection connection=Singleton.getConnection();
		Connection connection = Singleton.cadenaConexion();
		
		/*Variables temporales para crear el response*/
		String referencia = null;
		String codPasarela = null;
		String idTransaccion = null;
		int valortx = 0;
		Timestamp fechaTx = null;
		String descriciontx = null;
		String ref_comercio = null;
		String urlRetorno = null;
		String estado = null;
		int uso = 0;
		
		String orgCodBanco = null;
		String orgTipCuenta = null;
		String orgCodCuenta = null;
		String orgIdTitular = null;
		String orgNombTitular = null;
		String destCodBanco = null;
		String destTipCuenta = null;
		String destCodCuenta = null;
		String destIdTitular = null;
		String destNombTitular = null;
		
		String fechaTrans = null;
		
		/*Genera la consulta para la tabla Movimientos*/
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT ID_MOVIMIENTO, VALOR_TX, FECHA_TX, DESCRIPCION, COD_PASARELA, REFERENCIA_PASARELA, REFERENCIA_COMERCIO, URL_RETORNO , ESTADO, USO FROM MOVIMIENTOS WHERE COD_PASARELA = ? AND REFERENCIA_PASARELA = ?");
			ps.setString(1, request.getCodPasarela());
			ps.setString(2, request.getReferencia());
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
			    idTransaccion = rs.getString(1);
			    valortx = rs.getInt(2);
			    fechaTx = rs.getTimestamp(3);
			    descriciontx = rs.getString(4);
			    codPasarela = rs.getString(5);
			    referencia = rs.getString(6);
			    ref_comercio = rs.getString(7);
			    urlRetorno = rs.getString(8);
			    estado = rs.getString(9);
			    uso = rs.getInt(10);
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String currentTime = sdf.format(fechaTx);
			    fechaTrans =   currentTime;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tcr = null;
		}
		
		/*Genera la consulta para la tabla Movimiento_Cuentas*/
		try
		{
			PreparedStatement st = connection.prepareStatement("SELECT * FROM MOVIMIENTO_CUENTAS WHERE ID_MOVIMIENTO = ?");
			st.setString(1, idTransaccion);
			ResultSet rs= st.executeQuery();
			while(rs.next())
			{
				destCodBanco = rs.getString(2);
				destTipCuenta = rs.getString(3);
				destCodCuenta = rs.getString(4);
				destIdTitular = rs.getString(5);
				destNombTitular = rs.getString(6);
				orgCodBanco = rs.getString(7);
				orgTipCuenta = rs.getString(8);
				orgCodCuenta = rs.getString(9);
				orgIdTitular = rs.getString(10);
				orgNombTitular = rs.getString(11);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try
		{
			tcr.setReferencia(referencia);
			tcr.setCodPasarela(codPasarela);
			tcr.setEstado(estado);
			
			tx.setIdTransaccion(idTransaccion);
			tx.setValor(valortx);
			tx.setFechaTransaccion(fechaTrans);
			tx.setDescripcion(descriciontx);
			tx.setUrlRetorno(urlRetorno);
			tx.setUso(uso);
			
			origen.setCodBanco(orgCodBanco);
			origen.setTipoCuenta(orgTipCuenta);
			origen.setCodCuenta(orgCodCuenta);
			origen.setIdTitularCuenta(orgIdTitular);
			origen.setNombreTitularCuenta(orgNombTitular);
			tx.setCuentaOrigen(origen);
			
			destino.setCodBanco(destCodBanco);
			destino.setTipoCuenta(destTipCuenta);
			destino.setCodCuenta(destCodCuenta);
			destino.setIdTitularCuenta(destIdTitular);
			destino.setNombreTitularCuenta(destNombTitular);
			tx.setCuentaDestino(destino);
			
			tcr.setTransaccion(tx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tcr = null;
		}
		
		return tcr;
	}
	
	//endregion 	
	
	//region ProductDao-Usuario

	public Usuario consultaUsuario(int id) {
		//Connection connection=Singleton.getConnection();
		Connection connection = Singleton.cadenaConexion();
		Usuario usu = null;
		int captid;
		String tId = null;
		try
		{
			PreparedStatement st = connection.prepareStatement("SELECT ID, TIPOS_DOCUMENTO_ID, CONTRASENA, ROLES_IDROLES, NOMBRE, APELLIDO, CORREO FROM PERSONAS WHERE ID = ?;");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				captid = rs.getInt(2);
				switch (captid)
				{
					case 1:
						tId = "CC";
						break;
					case 2:
						tId = "CE";
						break;
					case 3:
						tId = "PP";
						break;
					case 4:
						tId = "NIT";
						break;
				}
				usu = new Usuario(
							rs.getInt(1),
							tId,
							rs.getString(3),
							String.valueOf(rs.getInt(4)),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7)
						);
			}
			return usu;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			usu = null;
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usu;
	}

	public List<Cuenta> consultaCuentas(int id, String nombre) {
		//Connection connection=Singleton.getConnection();
		Connection connection = Singleton.cadenaConexion();
		List<Cuenta> cu = new ArrayList<Cuenta>();
		
		try
		{
			PreparedStatement st = connection.prepareStatement("SELECT COD_BANCO, TIPO_CUENTA_ID, NUMERO_CUENTA, USUARIOS_ID, SALDO_CUENTA FROM CUENTAS WHERE USUARIOS_ID = ?;");
			st.setInt(1, id);
			ResultSet rs= st.executeQuery();
			while(rs.next())
			{
				Cuenta cta =  new Cuenta(rs.getString(1),String.valueOf(rs.getInt(2)),String.valueOf(rs.getString(3)),String.valueOf(rs.getInt(4)),nombre,rs.getInt(5));
				cu.add(cta);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cu;
	}

	public boolean actualizarMovimiento(String idTransaccion, String estado, Cuenta cuenta) {
		// TODO Auto-generated method stub
		Connection connection = Singleton.cadenaConexion();
		boolean resultado = false;
		int uso = 0;
		
		
		try
		{
			PreparedStatement ps = connection.prepareStatement("UPDATE MOVIMIENTOS SET ESTADO = ? , USO = ? WHERE ID_MOVIMIENTO = ?");
			ps.setString(1, estado);
			ps.setInt(2, uso);
			ps.setString(3, idTransaccion);
			ps.executeUpdate();
			resultado = true;
		}
		catch (Exception e)
		{
			e.toString();
			resultado = false;
		}
		
		if (resultado)
		{
			try
			{
				PreparedStatement pst = connection.prepareStatement("UPDATE MOVIMIENTO_CUENTAS SET ORIG_COD_BANCO = ? , ORIG_TIPO_CUENTA = ? , ORIG_COD_CUENTA = ? , ORIG_ID_TITULAR = ? , ORIG_NOMB_TITULAR = ? WHERE ID_MOVIMIENTO = ?");
				pst.setString(1, cuenta.getCodBanco());
				pst.setString(2, cuenta.getTipoCuenta());
				pst.setString(3, cuenta.getCodCuenta());
				pst.setString(4, cuenta.getIdTitularCuenta());
				pst.setString(5, cuenta.getNombreTitularCuenta());
				pst.setString(6, idTransaccion);
				pst.executeUpdate();
				resultado = true;
			}
			catch(Exception e)
			{
				e.toString();
				resultado = false;
			}
		}
		
		return resultado;
	}

	public Transaccion consultaTransaccion(String idTransaccion) 
	{
		Connection connection = Singleton.cadenaConexion();
		Transaccion tx = new Transaccion();
		
		String referencia = null;
		String codPasarela = null;
		String idTrans = null;
		int valortx = 0;
		Timestamp fechaTx = null;
		String descriciontx = null;
		String ref_comercio = null;
		String urlRetorno = null;
		String estado = null;
		int uso = 0;
		String fechaTrans = null;
		
		try 
		{
			PreparedStatement ps = connection.prepareStatement("SELECT ID_MOVIMIENTO, VALOR_TX, FECHA_TX, DESCRIPCION, COD_PASARELA, REFERENCIA_PASARELA, REFERENCIA_COMERCIO, URL_RETORNO , ESTADO, USO FROM MOVIMIENTOS WHERE ID_MOVIMIENTO = ? ");
			ps.setString(1, idTransaccion);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				idTrans = rs.getString(1);
			    valortx = rs.getInt(2);
			    fechaTx = rs.getTimestamp(3);
			    descriciontx = rs.getString(4);
			    codPasarela = rs.getString(5);
			    referencia = rs.getString(6);
			    ref_comercio = rs.getString(7);
			    urlRetorno = rs.getString(8);
			    estado = rs.getString(9);
			    uso = rs.getInt(10);
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String currentTime = sdf.format(fechaTx);
			    fechaTrans =   currentTime;
			}
			
			tx.setIdTransaccion(idTransaccion);
			tx.setValor(valortx);
			tx.setFechaTransaccion(fechaTrans);
			tx.setDescripcion(descriciontx);
			tx.setUrlRetorno(urlRetorno);
			tx.setUso(uso);
			
			
		}
		catch(Exception e)
		{
			e.toString();
			tx = null;
		}
		return tx;
	}

	public boolean actualizarCuenta(Cuenta objCuenta, int tipoTransaccion, int valor) {
		
		Connection connection = Singleton.cadenaConexion();
		boolean resultado = false ;
		int saldoResultado = 0;
		try
		{
			if (tipoTransaccion == 0)
				saldoResultado = pu.operación(objCuenta.getSaldo(),valor,"DEBITO");//Debito a la cuenta				
			else
				saldoResultado = pu.operación(objCuenta.getSaldo(),valor,"DEBITO");//Deposita en cuenta
			
			PreparedStatement pst = connection.prepareStatement("UPDATE CUENTAS SET SALDO_CUENTA = ? WHERE NUMERO_CUENTA = ?");
			pst.setInt(1, saldoResultado);
			pst.setString(2, objCuenta.getCodCuenta());
			pst.executeUpdate();
			resultado = true;
			
			
		}
		catch(Exception e)
		{
			e.toString();
			resultado =  false;
		}
		
		
		return resultado;
	}	
	
	//endregion 
}
