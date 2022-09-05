import java.sql.*;

public class DatabaseInterface {

  public Connection dbConnect(String dbName){
    Connection connection = null;
    try {
      String url = ("jdbc:sqlite:" + dbName);// connects to set database, will change for flexibility
      connection = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return connection;
  }

}
