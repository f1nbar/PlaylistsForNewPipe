
import java.sql.*;

import java.util.ArrayList;

public class Extract {

  int numPlaylists; //this might be irrelevant
  Statement statement;

  public void setNumPlaylists() throws Exception {
    statement.executeQuery("SELECT COUNT(*) FROM playlists;").getInt(1);
  }

  public int getNumPlaylists() {
    return numPlaylists;
  }

  public void fileHandler(String path) throws Exception { 

    queryDb(path);
    setNumPlaylists();
    queryUids();
    queryNames();
  }

  public void queryDb(String path) throws Exception {

    DBConnection dbConnection = new DBConnection();
    dbConnection.setConnection(path);
    assert dbConnection.getConnection() != null;
    statement = dbConnection.getConnection().createStatement();
  }

  public void queryUids() throws Exception {
    ResultSet set = statement.executeQuery("SELECT playlists.uid FROM playlists;");
    // System.out.println(set.getInt(1)); //Here we are getting the playlist UIDs
  }

  public ArrayList<String> queryNames() throws Exception {

    ArrayList<String> playlistNames = new ArrayList<>();
    ResultSet set = statement.executeQuery("SELECT name FROM playlists;");
    while (set.next()) {
      playlistNames.add(set.getString(1));
    }
    return playlistNames;
  }

  public ArrayList<String> queryVideoIDs(int uid) throws Exception {

    ArrayList<String> videoURLS = new ArrayList<>();
    //obtains the unique video part of the URL which occurs after "watch?v="
    ResultSet set = statement.executeQuery("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id=" + uid + ";"); 
    while (set.next()) {
      videoURLS.add(set.getString(1).substring(32, set.getString(1).length()));
    }
    return videoURLS;
  }

  public int getUIDFromName(String name) throws Exception {
    ResultSet set = statement.executeQuery("SELECT playlists.uid FROM playlists WHERE playlists.name = " + "\"" + name + "\"" + ";");
    return set.getInt(1);
  }

  public void createPlaylist(int uid) throws Exception {

    ArrayList<String> videoURLS = queryVideoIDs(uid);
    int playlistsToCreate = (videoURLS.size() / 50) + 1;
    ArrayList<ArrayList <String>> generatedPlaylists = new ArrayList<>();

    for (int i = 0; i < playlistsToCreate; i++) { //make sublists
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (String url : videoURLS) {
      stringBuilder.append(url + ","); //comma seperate all video urls to append to playlist link
    }
    String playlist = "https://www.youtube.com/watch_videos?video_ids=" + stringBuilder;
    System.out.println(playlist);
  }
}
