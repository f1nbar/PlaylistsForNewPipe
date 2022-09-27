package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.PanelUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import service.Extract;

public class Frame extends JFrame {

  static JButton generateButton, fileBrowseButton;
  static JLabel headerImage;
  static JComboBox<String> comboBox;
  static ArrayList<JButton> playlistLinks;
  Extract extract;

  public Frame() throws Exception {
    initUI();
    pack();
  }

  private void initUI() throws Exception {

    extract = new Extract();

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel mainPanel = new JPanel();
    JPanel playlistPanel = new JPanel();
    tabbedPane.addTab("Extract", mainPanel);
    tabbedPane.addTab("Playlists", playlistPanel);

    setTitle("Select a NewPipe DB file");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icon = new ImageIcon("images/icon.png");
    setIconImage(icon.getImage());
    mainPanel.setLayout(new GridBagLayout());
    setSize(400, 800);
    setVisible(true);  
    add(tabbedPane);

 
    Image image =  ImageIO.read(new File("images/icon.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    headerImage = new JLabel(new ImageIcon(image));
    generateButton = new JButton("Generate Playlist");

    Vector<String> playlistNames = new Vector<>();
    playlistNames.add("Waiting for playlist database");
    DefaultComboBoxModel model = new DefaultComboBoxModel<>(playlistNames);
    comboBox = new JComboBox<>(model);

    GridBagConstraints constraints = new GridBagConstraints();
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
  
    mainPanel.add(headerImage, constraints);
    mainPanel.add(headerImage, constraints);
    mainPanel.add(fileBrowseButton, constraints);
    mainPanel.add(comboBox, constraints);
    mainPanel.add(generateButton, constraints);

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
          for (JButton button : playlistLinks) {
            add(button, constraints);
          }
          pack();
        } catch (Exception error) {
          System.out.println(error);
        }
      }
    });
  }
}

