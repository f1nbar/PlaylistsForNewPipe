//NewPipe Playlist Extractor by Finbar Ó Deaghaidh

import java.sql.*;
import java.util.ArrayList;

public class Extract {

	static filechooser fc = new filechooser();
	private static ArrayList<ArrayList<String>> playlist;
	public static String[] names;

	private ArrayList<ArrayList<String>> connect() throws Exception {

		ArrayList<String> newPlaylist = new ArrayList<>();
		ArrayList<ArrayList<String>> arrayOfPlaylists = new ArrayList<ArrayList<String>>();
		Connection connection = null;
		Statement state = null;

		fc.create(); // call ui to be drawn

		while (fc.getLabel() == "No File Selected") { // try catch block while user chooses file from combobox
			try {
				wait();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		try {
			String url = ("jdbc:sqlite:" + fc.getLabel());// connects to set database, will change for flexibility
			connection = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		state = connection.createStatement();
		String amount = ("SELECT COUNT(*) FROM playlists;"); // gets the number of playlists in db
		ResultSet count = state.executeQuery(amount);
		int playlists = count.getInt(1);

		if (playlists != 1) {

			ArrayList<Integer> uid = new ArrayList<>();
			String playlistID = ("SELECT playlists.uid FROM playlists"); /*
																			 * each playlist has as a unique id that
																			 * must be extracted in order to get the
																			 * video urls that correspond to them
																			 */
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
			nameSet.close();

			for (int i = 0; i < uid.size(); i++) { // for loop that iterates through the playlists

				String sql = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id AND playlist_id ="
						+ uid.get(i) + ";"); /*
												 * gets video urls from by performing an inner join on streams and
												 * playlist_stream_join with chosen playlist uid and stream id that
												 * corresponds to the playlist id
												 */

				ResultSet rs = state.executeQuery(sql);

				while (rs.next()) {
					String url = rs.getString(1); // second column contains youtube
					newPlaylist.add(url);
				}

				arrayOfPlaylists.add(newPlaylist);
				newPlaylist.clear();
				fc.createComboBox(namesArrayList);

			}

			while (filechooser.nameOfSelected == null) { // wait until user chooses a playlist to extract
				try {
					wait();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			String findUID = ("SELECT playlists.uid FROM playlists WHERE playlists.name = " + "\""
					+ filechooser.nameOfSelected + "\"" + ";");      //gets uid of playlist that user has selected   
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

			String sql = ("SELECT streams.url FROM streams INNER join playlist_stream_join on streams.uid=playlist_stream_join.stream_id;");
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				String url = rs.getString(1); // second column contains youtube url

				newPlaylist.add(url);
			}
			arrayOfPlaylists.add(newPlaylist);
			rs.close();

		}

		state.close();
		connection.close(); // clean up environment

		return arrayOfPlaylists;

	}

	private String ytPlaylistFromLinks(ArrayList<String> links) {

		String play;
		
		if (fc.invidious == true) {
			play = "https://www.invidio.us/watch_videos?video_ids=";
		} else {
			play = "https://www.youtube.com/watch_videos?video_ids=";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < links.size(); i++) { //takes the video id section of the youtube URL and appends them to the "play" string with a comma seperator to create a playlist link 

			sb.append(links.get(i).substring(32, links.get(i).length()) + ","); 
		}
		play = play + sb;

		fc.linkPlayList(play);
		return play;

	}

	public static void main(String[] args) throws Exception {
		Extract ext = new Extract();
		playlist = ext.connect();
		ext.ytPlaylistFromLinks(playlist.get(0));

	}

}
