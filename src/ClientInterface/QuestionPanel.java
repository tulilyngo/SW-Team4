package ClientInterface;

import ServerComm.QuestionControl;
import Database.GameData;
import Database.QuestionData;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

import javax.swing.*;

public class QuestionPanel extends JPanel {
  private QuestionControl questionControl;

  public QuestionPanel(QuestionControl questionControl, GameData gameData) {
    QuestionData questionData = gameData.getQuestions().get(gameData.getCurrentQuestion());

    // ---------- Question Label Panel ----------
    JPanel labelPanel = new JPanel(new GridLayout(1, 1));

    JLabel questionLabel = new JLabel("Question", JLabel.CENTER);
    questionLabel.setText(questionData.getQuestion());
    questionLabel.setFont(new Font("Serif", Font.BOLD, 16));
    questionLabel.setBackground(Color.decode("#fefffe"));
    questionLabel.setOpaque(true);
    questionLabel.setForeground(Color.decode("#323332"));

    labelPanel.add(questionLabel);

    // ---------- Center Panel for Timer and No of answers submitted ----------
    JPanel centerPanel = new JPanel(new GridLayout(1, 2, 0, 0));

    JLabel timerLabel = new JLabel("", JLabel.CENTER);
//    timerLabel.setBackground(Color.decode("#874cbe"));
//    timerLabel.setOpaque(true);
//    timerLabel.setForeground(Color.decode("#fefffe"));
//    timerLabel.setFont(new Font("Serif", Font.PLAIN, 28));
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      int i = 20;

      public void run() {

        timerLabel.setText("Time left: " + i);
        i--;

        if (i < 0) {
          timer.cancel();
          timerLabel.setText("Time Over");
          questionControl.direct();
        }
      }
    }, 0, 1000);

    JLabel noAnswers = new JLabel("0 Answers", JLabel.CENTER);

    centerPanel.add(timerLabel);
    centerPanel.add(noAnswers);

    // ---------- Answers Label Panel ----------
    List<String> options = questionData.getAnswers();
    JPanel answerPanel = new JPanel(new GridLayout(2, 2, 5, 5));

    JButton answer1 = new JButton(options.get(0));
    JButton answer2 = new JButton(options.get(1));
    JButton answer3 = new JButton(options.get(2));
    JButton answer4 = new JButton(options.get(3));

    answer1.setPreferredSize(new Dimension(200, 50));
    answer2.setPreferredSize(new Dimension(200, 50));
    answer3.setPreferredSize(new Dimension(200, 50));
    answer4.setPreferredSize(new Dimension(200, 50));

    answer1.setBackground(Color.decode("#e21a3d"));
    answer1.setOpaque(true);
    answer1.setBorderPainted(false);
    answer1.setForeground(Color.decode("#fefffe"));

    answer2.setBackground(Color.decode("#1368cf"));
    answer2.setOpaque(true);
    answer2.setBorderPainted(false);
    answer2.setForeground(Color.decode("#fefffe"));

    answer3.setBackground(Color.decode("#d89e00"));
    answer3.setOpaque(true);
    answer3.setBorderPainted(false);
    answer3.setForeground(Color.decode("#fefffe"));

    answer4.setBackground(Color.decode("#26890b"));
    answer4.setOpaque(true);
    answer4.setBorderPainted(false);
    answer4.setForeground(Color.decode("#fefffe"));

    answer1.addActionListener(questionControl);
    answer2.addActionListener(questionControl);
    answer3.addActionListener(questionControl);
    answer4.addActionListener(questionControl);

    answerPanel.add(answer1);
    answerPanel.add(answer2);
    answerPanel.add(answer3);
    answerPanel.add(answer4);

    // ---------- Arrange the three panels in a grid ----------
    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 0));
    grid.add(labelPanel);
    grid.add(centerPanel);
    grid.add(answerPanel);
    this.add(grid);
  }
}
