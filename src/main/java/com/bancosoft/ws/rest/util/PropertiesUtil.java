package com.bancosoft.ws.rest.util;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties loadProperty(String propertiesURL){
	      try{
	          Properties properties = new Properties();
	          InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesURL);
	          properties.load(inputStream);
	          return properties;
	      }catch (Exception e){
	          e.printStackTrace();
	          return null;
	      }
	  }
	
	public boolean expiraTransaccion(Date fecha)
	{
		long milisegundos = 0; 
		int tiempoExp = 270;
		int tiempoAho = 0;
		boolean resultado = false;
		Date fechaActual = null;
		try
		{
			fechaActual = java.sql.Date.valueOf(java.time.LocalDate.now());
			milisegundos = fechaActual.getTime() - fecha.getTime();
			tiempoAho = (int) (milisegundos / 1000) % 60;
			if (tiempoAho >= tiempoExp)
				resultado = true;
		}
		catch (Exception e)
		{
			e.toString();
			resultado = true;
		}
		
		return resultado;
	}

	public int operación(int saldo, int valor, String string) {
		int resultado = 0;
		
		switch (string) 
		{
		case "DEBITO":
			resultado = saldo - valor;
			break;
		case "DEPOSITO":
			resultado = saldo + valor;
			break;
		
		}
		
		return resultado;
	}
}
