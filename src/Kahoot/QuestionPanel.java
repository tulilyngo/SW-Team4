package Kahoot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.*;

public class QuestionPanel extends JFrame {
  private JButton A;
  private JButton B;
  private JButton C;
  private JButton D;

  public QuestionPanel(String title) {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // ---------- Question Header Panel ----------
    JLabel questionLabel = new JLabel("Question");

    // ---------- North Panel for Question Header ----------
    JPanel north = new JPanel(new FlowLayout());
    north.add(questionLabel);

    // ---------- Center Panel for Timer and No of answers submitted ----------
    JTextField timer = new JTextField("0");
    JTextField noAnswers = new JTextField("0");
    
    JPanel center = new JPanel();

    center.add(timer);
    center.add(noAnswers);

    A = new JButton("A");
    B = new JButton("B");
    C = new JButton("C");
    D = new JButton("D");

    A.setPreferredSize(new Dimension(300, 100));
    B.setPreferredSize(new Dimension(300, 100));
    C.setPreferredSize(new Dimension(300, 100));
    D.setPreferredSize(new Dimension(300, 100));

    JPanel firstRowAnswers = new JPanel(new FlowLayout());
    firstRowAnswers.add(A);
    firstRowAnswers.add(B);

    JPanel secondRowAnswers = new JPanel(new FlowLayout());
    secondRowAnswers.add(C);
    secondRowAnswers.add(D);

    // ---------- all_panel ----------
    JPanel all_panel = new JPanel();
    all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
    all_panel.add(north);
    all_panel.add(center);
    all_panel.add(firstRowAnswers);
    all_panel.add(secondRowAnswers);

    // ----- Add all_panel to main panel to be included in main frame -----
    JPanel main = new JPanel();
    main.add(all_panel);

    this.add(main, BorderLayout.CENTER);

    A.addActionListener(new EventHandler(A.getText()));
    B.addActionListener(new EventHandler(B.getText()));
    C.addActionListener(new EventHandler(C.getText()));
    D.addActionListener(new EventHandler(D.getText()));

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
    new QuestionPanel("Play Kahoot!"); // args[0] represents the title of the GUI
  }
}
