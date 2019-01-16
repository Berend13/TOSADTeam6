package BRG.BRG_ART_ID.dao;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;


public class BaseDao {
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
} 
