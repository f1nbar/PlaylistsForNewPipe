//NewPipe Playlist Extractor by Finbar Ó Deaghaidh

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Extract implements Runnable {

  // DatabaseInterface databaseInterface = new DatabaseInterface();
  // static FileChooser fileChooser = new FileChooser();
  // Connection connection;
  // Statement state; 
  // public static String[] names;
  // ArrayList<String> newPlaylist = new ArrayList<>();
  // ArrayList<ArrayList<String>> arrayOfPlaylists = new ArrayList<ArrayList<String>>();
  //
  @Override
  public void run() {
    //fileChooser.create(); // call ui to be drawn
  }
  //
  //
  // public String getDbName(){
  //   return "";
  // }
  // 
  // ArrayList<ArrayList<String>> connect() throws Exception {
  //
  //   while (fileChooser.getLabel().equals("No File Selected")) { // try catch block while user chooses file from combobox
  //     try {
  //       wait();
  //     } catch (Exception ignored) {
  //     }
  //   }
  //
  //
  //   assert connection != null;
  //   state = connection.createStatement();
  //   String numPlaylists = ("SELECT COUNT(*) FROM playlists;"); // gets the number of playlists in db
  //   ResultSet count = state.executeQuery(numPlaylists);
  //   int playlists = count.getInt(1);
  //
  //
  //
  //     
  //   /* each playlist has as a unique id that
  //   must be extracted in order to get the
  //   video urls that correspond to them */
  //
  //   if (playlists != 1) { //TODO error handling
  //     ArrayList<Integer> uid = new ArrayList<>();
  //     ResultSet resultSet = state.executeQuery("SELECT playlists.uid FROM playlists");
  //     while (resultSet.next()) {
  //       uid.add(resultSet.getInt(1));
  //     }
  //     resultSet.close();
  //
  //     ArrayList<String> namesArrayList = new ArrayList<>();
  //     String name = ("SELECT name FROM playlists"); // we need the names of the playlists to display to the user in the drop down
  //     ResultSet nameSet = state.executeQuery(name);
  //     while (nameSet.next()) {
  //       namesArrayList.add(nameSet.getString(1));
  //     }
  //      
  //     /* gets video urls from by performing an inner join on streams and
  //      playlist_stream_join with chosen playlist uid and stream id that
  //      corresponds to the playlist id */
  //    
  //     for (Integer integer : uid) { // iterate through playlist UIDs 
  //       String query = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id ="
  //       + integer + ";");         
  //       ResultSet resultSetStreams = state.executeQuery(query);
  //
  //       while (resultSetStreams.next()) {
  //         String url = resultSetStreams.getString(1); // second column contains youtube
  //         newPlaylist.add(url);
  //       }
  //       arrayOfPlaylists.add(newPlaylist);
  //       newPlaylist.clear();
  //       fileChooser.createComboBox(namesArrayList);
  //     }
  //
  //     while (FileChooser.nameOfSelected == null) { // wait until user chooses a playlist to extract
  //       try {
  //         wait();
  //       } catch (Exception e) {
  //         System.out.println(e.getMessage());
  //       }
  //     }
  //
  //     String findUID = ("SELECT playlists.uid FROM playlists WHERE playlists.name = " + "\""
  //     + FileChooser.nameOfSelected + "\"" + ";");      //gets uid of playlist that user has selected //TODO issue is here
  //     ResultSet rUID = state.executeQuery(findUID);
  //     System.out.println(rUID.getInt(0));
  //     int uidFound = rUID.getInt(1); //first column of results contains the uid
  //
  //     String findVideoURLs = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id ="
  //     + uidFound + ";");  
  //     /*
  //            * gets video urls from by performing an inner join on streams and
  //            * playlist_stream_join with chosen playlist uid and stream id that
  //            * corresponds to the playlist id
  //            */
  //     ResultSet resultSetStreams2 = state.executeQuery(findVideoURLs);
  //     while (resultSetStreams2.next()) {
  //       String url = resultSetStreams2.getString(1);
  //       newPlaylist.add(url);
  //     }
  //
  //   } else { //in the case of just one playlist
  //     ArrayList<String> namesArrayList = new ArrayList<>();
  //     String name = ("SELECT name FROM playlists");
  //     ResultSet nameSet = state.executeQuery(name);
  //     namesArrayList.add(nameSet.getString(1));
  //     nameSet.close();
  //     fileChooser.createComboBox(namesArrayList);
  //     String streamSet = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id;");
  //     ResultSet urlRes = state.executeQuery(streamSet);
  //     while (urlRes.next()) {
  //       String url = urlRes.getString(1); // second column contains youtube url
  //       newPlaylist.add(url);
  //     }
  //     arrayOfPlaylists.add(newPlaylist);
  //   }
  //   state.close();
  //   connection.close(); // clean up environment
  //   return arrayOfPlaylists;
  //
  // }
  //
  // void ytPlaylistFromLinks(ArrayList<String> links) throws IOException {
  //   String play;
  //   if (fileChooser.invidious) {
  //     play = "https://www.iteroni.com/watch_videos?video_ids=";
  //   } else {
  //     play = "https://www.youtube.com/watch_videos?video_ids=";
  //   }
  //   StringBuilder sb = new StringBuilder();
  //   for (String link : links) { //takes the video id section of the youtube URL and appends them to the "play" string with a comma seperator to create a playlist link
  //     sb.append(link.substring(32, link.length())).append(",");
  //   }
  //   play = play + sb;
  //   fileChooser.linkPlayList(play);
  // }
}
