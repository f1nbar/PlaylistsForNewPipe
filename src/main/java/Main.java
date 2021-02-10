import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Extract ext = new Extract();
        ArrayList<ArrayList<String>> playlist = ext.connect();
        ext.ytPlaylistFromLinks(playlist.get(0));
    }
}
