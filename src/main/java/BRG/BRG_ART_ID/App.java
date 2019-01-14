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
    public static void main( String[] args )
    {
    	System.out.println("Searching for Oracle JDBC driver...");
    	 
    	try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	}
    	 
    	catch (ClassNotFoundException e) {
    	System.out.println("Oracle JDBC driver not found!");
    	e.printStackTrace();
    	return;
    	}
    	 
    	System.out.println("Oracle JDBC Driver registered.");
    	 
    	Connection connection = null;
    	 
    	try {
    	connection = DriverManager.getConnection("jdbc:oracle:thin:@ondora04.hu.nl:8521/educ31", "cursist", "cursist0191");
    	}
    	 
    	catch (SQLException e) {
    	System.out.println("Connection Failed! Check username and password");
    	e.printStackTrace();
    	return;
    	}
    	 
    	if (connection != null) {
    	System.out.println("Connection to database successful");
    	}
    	 
    	else {
    	System.out.println("Unable to connect to database.");
    	}
    }
}
