import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    Extract extract = new Extract();
    ArrayList<ArrayList<String>> playlist = extract.connect();
    extract.ytPlaylistFromLinks(playlist.get(0));
  }
}
