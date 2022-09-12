package service;

import java.sql.*;
import java.util.ArrayList;

public class Extract {

  int numPlaylists; //this might be irrelevant
  Statement statement;
  ArrayList<String> videoURLS = new ArrayList<>();

  public void setNumPlaylists() throws Exception {
    statement.executeQuery("SELECT COUNT(*) FROM playlists;").getInt(1);
  }

  public int getNumPlaylists() {
    return numPlaylists;
  }

  public void fileHandler(String path) throws Exception { 
    //TODO restrict the type of files to only zip or db
    //if db file
    queryDb(path);
    setNumPlaylists();
    queryUids();
    queryNames();
    int uid = 6; //TODO hardcoded for the longest uid
    queryVideoIDs(uid);
  }

  public void queryDb(String path) throws Exception {
    DBConnection dbConnection = new DBConnection();
    dbConnection.setConnection(path);
    assert dbConnection.getConnection() != null;
    statement = dbConnection.getConnection().createStatement();
  }

  public void queryUids() throws Exception {
    ResultSet set = statement.executeQuery("SELECT playlists.uid FROM playlists;");
    while (set.next()) {
      System.out.println(set.getInt(1)); //Here we are getting the playlist UIDs
    }
  }

  public void queryNames() throws Exception {
    ResultSet set = statement.executeQuery("SELECT name FROM playlists;");
    while (set.next()) {
      System.out.println(set.getString(1)); 
    }
  }

  public void queryVideoIDs(int uid) throws Exception {
    //obtains the unique video part of the URL which occurs after "watch?v="
    ResultSet set = statement.executeQuery("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id=" + uid + ";"); 
    while (set.next()) {
      System.out.println(set.getString(1).substring(32, set.getString(1).length())); 
      videoURLS.add(set.getString(1).substring(32, set.getString(1).length()));
    }
  }

}
