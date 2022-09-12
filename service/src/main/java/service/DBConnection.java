package service;
import java.sql.*;

public class DBConnection{

  private Connection connection;

  public void setConnection (String dbName){
    try {
      String url = ("jdbc:sqlite:" + dbName);
      connection = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public Connection getConnection(){
    return connection;
  }

}
