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
    
    private Singleton()
    {
        try{
        	new com.mysql.jdbc.Driver();
            conn=cadenaConexion();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	    
    public Connection cadenaConexion() {
        try {
        String connectionString = createConnectionString();
        //String url = "jdbc:mysql://ingsoftwaremsql.mysql.database.azure.com:3306/banco?useSSL=true&requireSSL=false";
        //Connection connection = DriverManager.getConnection(url,"administrador@ingsoftwaremsql","Grupo&7Software");
        String[] instancia = connectionString.split("\\?");
        String url = instancia[0];
        String[] login = instancia[1].split("\\&");
        String[] usuario = login[0].split("\\=");
        String user = usuario[1];
        String[] contrasena = login[1].split("\\=");
        String pass = contrasena[1]+"&"+login[2];
        Connection connection = DriverManager.getConnection(url,user,pass);
        System.out.println("Connection class ===>"+connection.getClass().getName());
        return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }         
    }    
	    
private String createConnectionString(){
        
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String db = prop.getProperty(DB_NAME_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String user2 = prop.getProperty(DB_USER2_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);
        
        String connectionString = "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"@"+user2+"&password="+password;
        System.out.println("ConnectionString ===>"+ connectionString);
        return connectionString;       
     }

	
	public static Connection getConnection() {
        if(conn==null)
        {
         new Singleton();
        }
        return conn;
    }
	
}
