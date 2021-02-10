//NewPipe Playlist Extractor by Finbar Ó Deaghaidh

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.sql.*;
import java.util.ArrayList;

public class Extract {

    static FileChooser fc = new FileChooser();
    public static String[] names;

    ArrayList<ArrayList<String>> connect() throws Exception {

        ArrayList<String> newPlaylist = new ArrayList<>();
        ArrayList<ArrayList<String>> arrayOfPlaylists = new ArrayList<ArrayList<String>>();
        Connection connection = null;
        Statement state = null;

        fc.create(); // call ui to be drawn

        while (fc.getLabel().equals("No File Selected")) { // try catch block while user chooses file from combobox
            try {
                wait();
            } catch (Exception ignored) {
            }
        }

        try {
            String url = ("jdbc:sqlite:" + fc.getLabel());// connects to set database, will change for flexibility
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assert connection != null;
        state = connection.createStatement();
        String numPlaylists = ("SELECT COUNT(*) FROM playlists;"); // gets the number of playlists in db
        ResultSet count = state.executeQuery(numPlaylists);
        int playlists = count.getInt(1);

        if (playlists != 1) {
            ArrayList<Integer> uid = new ArrayList<>();
            String playlistID = ("SELECT playlists.uid FROM playlists"); /*
             * each playlist has as a unique id that
             * must be extracted in order to get the
             * video urls that correspond to them														 */
            ResultSet id = state.executeQuery(playlistID);
            while (id.next()) {
                uid.add(id.getInt(1));
            }
            id.close();

            ArrayList<String> namesArrayList = new ArrayList<>();
            String name = ("SELECT name FROM playlists"); // we need the names of the playlists to display to the user
            // in the drop down
            ResultSet nameSet = state.executeQuery(name);
            while (nameSet.next()) {
                namesArrayList.add(nameSet.getString(1));
            }

            for (Integer integer : uid) { // for loop that iterates through the playlists
                String sql = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id ="
                        + integer + ";"); /*
                 * gets video urls from by performing an inner join on streams and
                 * playlist_stream_join with chosen playlist uid and stream id that
                 * corresponds to the playlist id
                 */
                ResultSet rs = state.executeQuery(sql);
                ;
                while (rs.next()) {
                    String url = rs.getString(1); // second column contains youtube
                    newPlaylist.add(url);
                }
                arrayOfPlaylists.add(newPlaylist);
                newPlaylist.clear();
                fc.createComboBox(namesArrayList);
            }
            while (FileChooser.nameOfSelected == null) { // wait until user chooses a playlist to extract
                try {
                    wait();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String findUID = ("SELECT playlists.uid FROM playlists WHERE playlists.name = " + "\""
                    + FileChooser.nameOfSelected + "\"" + ";");      //gets uid of playlist that user has selected
            ResultSet rUID = state.executeQuery(findUID);
            int uidFound = rUID.getInt(1); //first column of results contains the uid

            String sql2 = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id ="
                    + uidFound + ";");   /*
             * gets video urls from by performing an inner join on streams and
             * playlist_stream_join with chosen playlist uid and stream id that
             * corresponds to the playlist id
             */
            ResultSet rs2 = state.executeQuery(sql2);
            while (rs2.next()) {
                String url = rs2.getString(1);
                newPlaylist.add(url);
            }

        } else { //in the case of just one playlist
            ArrayList<String> namesArrayList = new ArrayList<>();
            String name = ("SELECT name FROM playlists");
            ResultSet nameSet = state.executeQuery(name);
            namesArrayList.add(nameSet.getString(1));
            nameSet.close();
            fc.createComboBox(namesArrayList);
            String streamSet = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id;");
            ResultSet urlRes = state.executeQuery(streamSet);
            while (urlRes.next()) {
                String url = urlRes.getString(1); // second column contains youtube url
                newPlaylist.add(url);
            }
            arrayOfPlaylists.add(newPlaylist);
        }
        state.close();
        connection.close(); // clean up environment
        return arrayOfPlaylists;

    }

    void ytPlaylistFromLinks(ArrayList<String> links) throws IOException {
        String play;
        if (fc.invidious) {
            play = "https://www.invidio.us/watch_videos?video_ids=";
        } else {
            play = "https://www.youtube.com/watch_videos?video_ids=";
        }
        StringBuilder sb = new StringBuilder();
        for (String link : links) { //takes the video id section of the youtube URL and appends them to the "play" string with a comma seperator to create a playlist link
            sb.append(link.substring(32, link.length())).append(",");
        }
        play = play + sb;
        fc.linkPlayList(play);
    }
}
