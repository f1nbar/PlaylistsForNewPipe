package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
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

  public Frame() throws Exception {
    initUI();
    pack();
  }

  private void initUI() throws Exception {

    setTitle("Select a NewPipe DB file");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icon = new ImageIcon("images/icon.png");
    setIconImage(icon.getImage());
    setLayout(new GridBagLayout());
    setSize(400, 800);
    setVisible(true);  
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
        Extract extract = new Extract();
        FileChoose fileChoose = new FileChoose();
        String selectedFile = fileChoose.fileSelector();
        try {
          extract.fileHandler(selectedFile);
          model.addAll(extract.queryNames());
          model.removeElementAt(0); //remove placeholder
        } catch (Exception error) {
          System.out.println(error);
        }
      }
    });



    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);

    add(headerImage, constraints);
    add(fileBrowseButton, constraints);
    add(comboBox, constraints);
    add(generateButton, constraints);
  }

  public void fillCombobox(ArrayList<String> playlistNames) {

  }
}

