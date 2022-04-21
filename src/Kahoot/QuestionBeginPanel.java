package Kahoot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.*;

public class QuestionBeginPanel extends JFrame {

  public QuestionBeginPanel(String title) {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // ---------- Question Header Panel ----------
    JLabel questionLabel = new JLabel("Question");

    // ---------- North Panel for Question Header ----------
    JPanel north = new JPanel(new FlowLayout());
    north.add(questionLabel);

    // ---------- Center Panel for Timer and No of answers submitted ----------
    JTextField timer = new JTextField("0");
    JPanel center = new JPanel();
    center.add(timer);

    // ---------- all_panel ----------
    JPanel all_panel = new JPanel();
    all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
    all_panel.add(north);
    all_panel.add(center);

    // ----- Add all_panel to main panel to be included in main frame -----
    JPanel main = new JPanel();
    main.add(all_panel);

    this.add(main, BorderLayout.CENTER);

    setSize(800, 450);
    setVisible(true);

  }

  private class EventHandler implements ActionListener {
    private String label;

    public EventHandler(String label) {
      this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
      if (label.equals("Quit")) {
        System.exit(0);
      } else {
        System.out.println(label + " Button Pressed");
      }
    }
  }

  public static void main(String[] args) {
    new QuestionBeginPanel("Play Kahoot!"); // args[0] represents the title of the GUI
  }
}
