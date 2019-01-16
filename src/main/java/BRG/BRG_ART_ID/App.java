package BRG.BRG_ART_ID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
	
 private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
 private static final String DB_URL = "jdbc:oracle:thin:@ondora04.hu.nl:8521/educ31";
 private static final String DB_USER = "cursist";
 private static final String DB_PASS = "cursist0191";
 private static Connection conn;


 protected static Connection getConnection() throws SQLException{
  return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
}

public void closeConnection(){
		// close connection volgende opdracht
}

public static void main( String[] args ) throws SQLException
{
	Connection conn = getConnection();
	
	if (conn != null) {
		System.out.println("Connection goed");
	}
}
}
