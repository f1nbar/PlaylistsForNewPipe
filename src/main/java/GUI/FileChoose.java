package GUI;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class FileChoose extends JFrame implements ActionListener {

  FileChoose() { }

  public void InitUI() {
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Zip Files or NewPipe Databases", "db", "zip"));
    fileChooser.setAcceptAllFileFilterUsed(true);
    fileChooser.showSaveDialog(null);
  }

  public void actionPerformed(ActionEvent e) {

  } 
}
