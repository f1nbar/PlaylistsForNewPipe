package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import service.Extract;

public class Frame extends JFrame {

  public Frame() throws Exception {
    initUI();
    pack();
  }

  private void initUI() throws Exception {
    setTitle("Select a NewPipe DB file");
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icon = new ImageIcon("icon.png");
    setIconImage(icon.getImage());
    setLayout(new GridBagLayout());
    setSize(400, 800);
    setVisible(true);  

    GridBagConstraints constraints = new GridBagConstraints();
    JButton fileBrowseButton = new JButton("Select File");
    fileBrowseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        FileChoose fileChoose = new FileChoose();
        String selectedFile = fileChoose.fileSelector();
        try {
          new Extract().fileHandler(selectedFile);
        } catch (Exception error) {
          System.out.println(error);
        }
      }
    });

    JButton generateButton = new JButton("Generate");

    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);

    add(fileBrowseButton, constraints);
    add(generateButton, constraints);
  }
}

