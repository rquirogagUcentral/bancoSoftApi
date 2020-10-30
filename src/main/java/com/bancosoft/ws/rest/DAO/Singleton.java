package com.bancosoft.ws.rest.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.bancosoft.ws.rest.util.PropertiesUtil;



public class Singleton {
	
	private static Connection conn=null;
	
	 private static final String DB_PROPERTIES = "DBMySQL.properties";
	   
	    
    // Caracteristicas de la conexión
    private static final String DB_NAME_PROP ="dbname";
    private static final String DB_HOST_PROP ="host";
    private static final String DB_PASSWORD_PROP ="password";
    private static final String DB_PORT_PROP ="port";
    private static final String DB_USER_PROP ="user1";
    private static final String DB_USER2_PROP ="user2";
    
    public Singleton()
    {
        try{
        	//new com.mysql.jdbc.Driver();
            conn=cadenaConexion();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	    
    public static Connection cadenaConexion() {
    	
        try 
        {
        	new com.mysql.jdbc.Driver();
        	
        	Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
	        String host = prop.getProperty(DB_HOST_PROP);
	        String port = prop.getProperty(DB_PORT_PROP);
	        String db = prop.getProperty(DB_NAME_PROP);
	        String user = prop.getProperty(DB_USER_PROP);
	        String password = prop.getProperty(DB_PASSWORD_PROP);
	        
	        String connectionString = "jdbc:mysql://"+host+":"+port+"/"+db;
	        System.out.println("ConnectionString ===>"+ connectionString);
	        
	        
	        Connection connection = DriverManager.getConnection(connectionString,user,password);
	        System.out.println("Connection class ===>"+connection.getClass().getName());
	        return connection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }         
    }    
	    
	public static Connection getConnection() {
        if(conn==null)
        {
         new Singleton();
        }
        return conn;
    }
	
}
