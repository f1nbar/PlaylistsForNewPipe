package GUI;


import javax.swing.*;
import javax.imageio.ImageIO;
import service.Extract;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;


public class PlaylistPanel extends JPanel {
  
  GridBagConstraints constraints = new GridBagConstraints();

  public void initPanel(){

    setLayout(new GridBagLayout());
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10,10,10,10);
  }

  public void showPlaylistButtons(ArrayList<JButton> playlistLinks, ArrayList<String> generatedPlaylists ) {

    removeAll(); //clear the panel before generation of buttons

    for (JButton button : playlistLinks) {
      add(button, constraints);
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
          URI uri = null;
          try {
            uri = new URI(generatedPlaylists.get(playlistLinks.indexOf(button)));
          } catch (URISyntaxException uriEx) {
            System.out.println(uriEx);
          }
          if (Desktop.isDesktopSupported()) {
            try {
              Desktop.getDesktop().browse(uri);
            } catch (Exception exception) {
              exception.printStackTrace();
            }
          } else {
            JOptionPane pane = new JOptionPane("Could not open link.");
            JDialog dialog = pane.createDialog(new JFrame(), "");
            dialog.setVisible(true);
          }
        }
      });
    }
  }
}
