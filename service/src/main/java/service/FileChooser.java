package service;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.filechooser.*;

class FileChooser {

  // @Override 
  // public void run(){
  //
  // }
  //
  // core.FileChooser() { }
  //
  // public String getLabel() {
  //   return label.getText();
  // }
  //
  // public Boolean validFile() {
  //   return !label.getText().equals("Cancelled") || !label.getText().equals("No File Selected");
  // }
  //
  // public void appearance() {
  //   try {
  //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  // }
  //
  //   public void createComboBox(ArrayList<String> namesForDisplay) { // helper function to retrieve names of playlists and display them in the comboBox
  //     nameOfSelected = "";
  //
  //     if (combo.getItemCount() == 1) {
  //       for (String s : namesForDisplay) {
  //         combo.addItem(s);
  //       }
  //     }
  //     combo.setEnabled(true);
  //     combo.setSelectedIndex(0);
  //     combo.addItemListener(new ItemListener() {
  //       @Override
  //       public void itemStateChanged(ItemEvent e) {
  //         if (e.getStateChange() == ItemEvent.SELECTED) {
  //           nameOfSelected = (String) combo.getSelectedItem();
  //         }
  //       }
  //     });
  //   }
  //
  //   public void linkPlayList(String URL) { //creates clickable underlined link
  //
  //     String text = "Playlist (Click Me!)";
  //     URI temp = null;
  //     try {
  //       temp = new URI(URL);
  //     } catch (Exception e) {
  //       e.printStackTrace();
  //     }
  //
  //     final URI uri = temp;
  //
  //     playlist.setText("<HTML><FONT color=\"#000099\">" + text + "</FONT></HTML>");
  //     playlist.setCursor(new Cursor(Cursor.HAND_CURSOR));
  //     playlist.addMouseListener(new MouseListener() {
  //
  //       public void mouseExited(MouseEvent arg0) {
  //         playlist.setText("<HTML><FONT color=\"#000099\">" + text + "</FONT></HTML>");
  //       }
  //
  //       public void mouseEntered(MouseEvent arg0) {
  //         playlist.setText("<HTML><FONT color=\"#000099\"><U>" + text + "</U></FONT></HTML>");
  //       }
  //
  //       public void mouseClicked(MouseEvent arg0) {
  //
  //         if (Desktop.isDesktopSupported()) {
  //           try {
  //             Desktop.getDesktop().browse(uri);
  //           } catch (Exception e) {
  //             e.printStackTrace();
  //           }
  //         } else {
  //           JOptionPane pane = new JOptionPane("Could not open link.");
  //           JDialog dialog = pane.createDialog(new JFrame(), "");
  //           dialog.setVisible(true);
  //         }
  //       }
  //
  //       public void mousePressed(MouseEvent e) {
  //       }
  //
  //       public void mouseReleased(MouseEvent e) {
  //       }
  //     });
  // }
}
