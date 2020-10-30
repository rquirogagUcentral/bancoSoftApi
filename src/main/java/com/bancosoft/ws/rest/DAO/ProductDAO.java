package com.bancosoft.ws.rest.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import com.bancosoft.ws.rest.mo.Cuenta;
import com.bancosoft.ws.rest.mo.Transaccion;
import com.bancosoft.ws.rest.mo.TransaccionConsultaRequest;
import com.bancosoft.ws.rest.mo.TransaccionConsultaResponse;
import com.bancosoft.ws.rest.mo.TransaccionPagoRequest;
import com.bancosoft.ws.rest.mo.Usuario;

public class ProductDAO {

	//region ProductDao-Transacciones
	
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
			Date fecha = new Date();
			//Date date = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
			
			PreparedStatement ps =  connection.prepareStatement("INSERT INTO MOVIMIENTOS VALUES (?,?,?,?,?,?,?,?,?,?);");
			ps.setInt(1, codTransaccion);
			ps.setDouble(2, request.getOrigenComercio().getValor());
			ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			ps.setString(4, request.getOrigenComercio().getDescripcion());
			ps.setInt(5, 0);
			ps.setString(6, request.getCodPasarela());
			ps.setString(7, request.getReferencia());
			ps.setString(8, request.getUrlRetorno());
			ps.setString(9, request.getOrigenComercio().getRefComercio());
			ps.setString(10, "CREADO");
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
		Date fechaTx = null;
		String descriciontx = null;
		String ref_comercio = null;
		String urlRetorno = null;
		String estado = null;
		
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
		
		/*Genera la consulta para la tabla Movimientos*/
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT ID_MOVIMIENTO, VALOR_TX, FECHA_TX, DESCRIPCION, COD_PASARELA, REFERENCIA_PASARELA, REFERENCIA_COMERCIO, URL_RETORNO , ESTADO FROM MOVIMIENTOS WHERE COD_PASARELA = ? AND REFERENCIA_PASARELA = ?");
			ps.setString(1, request.getCodPasarela());
			ps.setString(2, request.getReferencia());
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
			    idTransaccion = rs.getString(1);
			    valortx = rs.getInt(2);
			    fechaTx = rs.getDate(3);
			    descriciontx = rs.getString(4);
			    codPasarela = rs.getString(5);
			    referencia = rs.getString(6);
			    ref_comercio = rs.getString(7);
			    urlRetorno = rs.getString(8);
			    estado = rs.getString(9);
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
			tx.setFechaTransaccion(fechaTx);
			tx.setDescripcion(descriciontx);
			tx.setUrlRetorno(urlRetorno);
			
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

	public boolean actualizarMovimiento(String idTransaccion, String estado) {
		// TODO Auto-generated method stub
		Connection connection = Singleton.cadenaConexion();
		
		return false;
	}	
	
	//endregion 
}
