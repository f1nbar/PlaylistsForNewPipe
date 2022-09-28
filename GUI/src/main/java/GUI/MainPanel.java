package GUI;

import javax.swing.*;
import javax.imageio.ImageIO;
import service.Extract;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;


public class MainPanel extends JPanel {

  static JButton generateButton, fileBrowseButton;
  static JLabel headerImage;
  static JComboBox<String> comboBox;
  static ArrayList<JButton> playlistLinks;
  static ImageIcon icon = new ImageIcon("images/icon.png");

  Extract extract;

  public MainPanel() {}

  public void initPanel(PlaylistPanel playlistPanel, JTabbedPane tabbedPane) throws Exception {

    extract = new Extract();

    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    Image image =  ImageIO.read(new File("images/icon.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    headerImage = new JLabel(new ImageIcon(image));
    generateButton = new JButton("Generate Playlist");

    Vector<String> playlistNames = new Vector<>();
    playlistNames.add("Waiting for playlist database");
    DefaultComboBoxModel model = new DefaultComboBoxModel<>(playlistNames);
    comboBox = new JComboBox<>(model);

    fileBrowseButton = new JButton("Select File");
    fileBrowseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        FileChoose fileChoose = new FileChoose();
        String selectedFile = fileChoose.fileSelector();
        try {
          extract.fileHandler(selectedFile);
          model.addAll(extract.queryNames());
          model.removeElementAt(0); //remove placeholder text
        } catch (Exception error) {
          System.out.println(error);
        }
      }
    });

    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);
  
    add(headerImage, constraints);
    add(headerImage, constraints);
    add(fileBrowseButton, constraints);
    add(comboBox, constraints);
    add(generateButton, constraints);

    generateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        playlistLinks = new ArrayList<>();
        try {
          String name = comboBox.getSelectedItem().toString();
          int uid = extract.getUIDFromName(name);
          extract.createPlaylist(uid);
          extract.setNumPlaylists();
          for (int i = 0; i < extract.getGeneratedPlaylists().size(); i++) {
            playlistLinks.add(new JButton(name + " " + (i + 1))); 
          }
          tabbedPane.addTab("Playlists", playlistPanel);
          playlistPanel.showPlaylistButtons(playlistLinks, extract.getGeneratedPlaylists());
          tabbedPane.setSelectedIndex(1);
          
        } catch (Exception error) {
          System.out.println(error);
        }
      }
    });
  }
}

  


