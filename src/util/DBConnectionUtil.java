package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUtil {
	/*
	private static final String URL = "jdbc:mysql://localhost:3306/rrhh?serverTimezone=UTC";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";*/
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL    = "jdbc:postgresql://localhost:5432/rrhh";
	private static final String USERNAME   = "postgres";
	private static final String PASSWORD   = "timmax0810"; 
	
	private static Connection connection = null;
	
	public static Connection openConnection() {
		if (connection != null)
            return connection;
        else {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                boolean valid = connection.isValid(50000);
                System.out.println(valid ? "TEST OK" : "TEST FAIL");
            } catch (ClassNotFoundException e) {
            	System.out.println("Error al registrar el driver de PostgreSQL: " + e);
            } catch (SQLException e) {
            	System.out.println("Error: " + e);
                e.printStackTrace();
            } 
            return connection;
        }
	}
	
	
}
