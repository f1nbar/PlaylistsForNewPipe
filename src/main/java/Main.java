import java.awt.EventQueue;

import GUI.Frame;
import com.formdev.flatlaf.FlatDarkLaf;


public class Main {

  public static void main(String[] args) throws Exception {
    FlatDarkLaf.setup();
    EventQueue.invokeLater(() -> {
      Frame frame = new Frame();
      frame.setVisible(true);
    });
  }
}
