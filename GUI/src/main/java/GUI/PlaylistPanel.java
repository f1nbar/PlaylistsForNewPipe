package GUI;


import javax.swing.*;
import javax.imageio.ImageIO;
import service.Extract;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;


public class PlaylistPanel extends JPanel {
  
  GridBagConstraints constraints = new GridBagConstraints();

  public void initPanel(){

    setLayout(new GridBagLayout());
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);
  }

  public void showPlaylistButtons(ArrayList<JButton> playlistLinks) {

    removeAll(); //clear the panel before generation of buttons

    for (JButton button : playlistLinks) {
      add(button, constraints);
    }
  }
}
