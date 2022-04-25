package Client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WaitingScreenPanel extends JPanel {
  public WaitingScreenPanel()
  {
    JLabel statusLabel = new JLabel("You're in!", JLabel.CENTER);
    statusLabel.setFont(new Font("Serif", Font.BOLD, 16));
    JLabel waitingLabel = new JLabel("Waiting for player 2…", JLabel.CENTER);
    waitingLabel.setFont(new Font("Serif", Font.BOLD, 16));
    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    labelPanel.add(statusLabel);
    labelPanel.add(waitingLabel);

    //Arrange the components
    JPanel all = new JPanel();
    all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    all.add(labelPanel);
    this.add(all);
  }

}
