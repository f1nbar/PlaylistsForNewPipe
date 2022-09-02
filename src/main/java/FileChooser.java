import javax.swing.*;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.filechooser.*;

class FileChooser extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;
  static JLabel l;
  static JLabel playlist;
  static JComboBox<String> c1;
  static JComboBox<String> combo;
  static String nameOfSelected;
  Boolean invidious = false;

  FileChooser() { }

  public String getLabel() {
    return l.getText();
  }

  public Boolean validFile() {
    return !l.getText().equals("Cancelled") || !l.getText().equals("No File Selected");
  }

  public void appearance() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void create() { // returns true if youtube playlist is selected, false for invidious


    JFrame f = new JFrame("Select a NewPipe DB file");
    ImageIcon icon = new ImageIcon("icon.png");
    f.setIconImage(icon.getImage());
    f.setSize(600, 100);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JRadioButton youtube = new JRadioButton("Youtube Playlist", true);
    JRadioButton invidiousButton = new JRadioButton("Invidio.us Playlist", false);
    ButtonGroup group = new ButtonGroup();
    group.add(youtube);
    group.add(invidiousButton);
    String[] playlistNames = new String[]{"Select A Playlist!"};
    combo = new JComboBox<String>(playlistNames);
    JButton open = new JButton("Open");
    JButton reset = new JButton("Reset");
    FileChooser fileChooser = new FileChooser();

    open.addActionListener(fileChooser); 
    // reset.addActionListener(new ActionListener() {
    //
    //   @Override
    //   public void actionPerformed(ActionEvent e) {
    //     try {
    //       Runtime.getRuntime().exec("java -jar PlaylistsForNewPipe.jar");
    //     } catch (IOException e1) {
    //       e1.printStackTrace();
    //     }
    //     System.exit(0);
    //   }
    // });
    invidiousButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        invidious = true;
      }
    });

    JPanel p = new JPanel(); // panel to add buttons
    playlist = new JLabel();
    p.add(combo);
    combo.setEnabled(false);
    l = new JLabel("No File Selected");
    playlist = new JLabel();
    p.add(youtube);
    p.add(invidiousButton);
    p.add(open);
    p.add(reset);
    p.add(playlist);
    p.add(l);
    p.add(playlist);
    f.add(p); // add label to panel and panel to frame

    f.setVisible(true);
  }

  public void createComboBox(ArrayList<String> namesForDisplay) { // helper function to retrieve names of playlists and display them in the comboBox
    nameOfSelected = "";

    if (combo.getItemCount() == 1) {
      for (String s : namesForDisplay) {
        combo.addItem(s);
      }
    }
    combo.setEnabled(true);
    combo.setSelectedIndex(0);
    combo.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          nameOfSelected = (String) combo.getSelectedItem();
        }
      }
    });
  }


  public void linkPlayList(String URL) { //creates clickable underlined link

    String text = "Playlist (Click Me!)";
    URI temp = null;
    try {
      temp = new URI(URL);
    } catch (Exception e) {
      e.printStackTrace();
    }

    final URI uri = temp;

    playlist.setText("<HTML><FONT color=\"#000099\">" + text + "</FONT></HTML>");
    playlist.setCursor(new Cursor(Cursor.HAND_CURSOR));
    playlist.addMouseListener(new MouseListener() {

      public void mouseExited(MouseEvent arg0) {
        playlist.setText("<HTML><FONT color=\"#000099\">" + text + "</FONT></HTML>");
      }

      public void mouseEntered(MouseEvent arg0) {
        playlist.setText("<HTML><FONT color=\"#000099\"><U>" + text + "</U></FONT></HTML>");
      }

      public void mouseClicked(MouseEvent arg0) {

        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(uri);
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          JOptionPane pane = new JOptionPane("Could not open link.");
          JDialog dialog = pane.createDialog(new JFrame(), "");
          dialog.setVisible(true);
        }
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }
    });

  }

  public void actionPerformed(ActionEvent evt) {

    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
    FileFilter filter = new FileNameExtensionFilter("NewPipe .db File", "db"); 
    j.setFileFilter(filter);
    int r = j.showOpenDialog(null); // show save option

    if (r == JFileChooser.APPROVE_OPTION) { // if user selects a file
      l.setText(j.getSelectedFile().getAbsolutePath()); // set label to selected file path
    } else
    l.setText("Cancelled");
  }

}
