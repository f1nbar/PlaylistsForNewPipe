import java.util.ArrayList;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.EventQueue;
import GUI.*;

public class Main {

  public static void main(String[] args) throws Exception {
    FlatDarkLaf.setup();
    EventQueue.invokeLater(() -> {
      Frame frame = new Frame();
      frame.setVisible(true);
    });
  }
}
    // Thread t1 = new Thread(new Extract());
    // t1.start();
    //ArrayList<ArrayList<String>> playlist = extract.connect();
    //extract.ytPlaylistFromLinks(playlist.get(0));
