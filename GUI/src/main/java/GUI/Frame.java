package GUI;

import javax.swing.*;
import service.Extract;

public class Frame extends JFrame {

  static ImageIcon icon = new ImageIcon("images/icon.png");
  MainPanel mainPanel;
  PlaylistPanel playlistPanel;

  public Frame() throws Exception {
    initUI();
    pack();
  }

  private void initUI() throws Exception {

    JTabbedPane tabbedPane = new JTabbedPane();
    mainPanel = new MainPanel();
    playlistPanel = new PlaylistPanel();
    tabbedPane.addTab("Extract", mainPanel);
    tabbedPane.addTab("Playlists", playlistPanel);
    mainPanel.initPanel(playlistPanel, tabbedPane);
    playlistPanel.initPanel();


    setTitle("Select a NewPipe DB file");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(icon.getImage());
    setSize(400, 800);
    setVisible(true);  
    add(tabbedPane);
  }
}

