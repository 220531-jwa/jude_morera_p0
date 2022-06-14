package dev.morera.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {

	private static ConnectionUtility cu;
	private static Properties dbProps;
	
	private ConnectionUtility() {
		dbProps = new Properties();
		InputStream props = ConnectionUtility.class.getClassLoader().getResourceAsStream("connection.properties");
	//credentials
		try {
			dbProps.load(props);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//ensures we only have exactly one
	public static synchronized ConnectionUtility getConnectionUtility() {
		if (cu == null) {
			cu = new ConnectionUtility();
		}
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {Class.forName(dbProps.getProperty("driver"));
		}
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		
		try {
			conn = DriverManager.getConnection(url,username,password);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
		
	}
	
//public static void main(String[] args) {
//		
//		Connection connection = getConnectionUtility().getConnection();
//		
//		if (connection != null) {
//			System.out.println("Connection Successful");
//		} else {
//			System.out.println("Something went wrong");
//		}
//		
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

}

	

