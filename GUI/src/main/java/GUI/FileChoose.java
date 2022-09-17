package GUI;

import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class FileChoose extends JFrame implements ActionListener {

  FileChoose() { }

  public String fileSelector() {
    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("NewPipe Database Files", "db"));
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //double click to select file
    fileChooser.setAcceptAllFileFilterUsed(true);
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    } else 
    return "null";
  }

  public void actionPerformed(ActionEvent e) {} 
}
