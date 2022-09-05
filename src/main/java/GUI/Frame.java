package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

public Frame() {
    initUI();
    pack();
  }

  private void initUI() {
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
        fileChoose.InitUI();
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

