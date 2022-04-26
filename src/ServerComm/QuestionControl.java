package ServerComm;

import Database.GameData;
import Database.QuestionData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class QuestionControl implements ActionListener {
    // Private data field for storing the container.
    private JPanel container;
    private GameClient client;

    private GameData gameData;
    private boolean isPlayer1;
    private List<JButton> btns;

    // Constructor for the initial controller.
    public QuestionControl(JPanel container, GameClient client) {
        this.container = container;
        this.client = client;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void setPlayer1(boolean player1) {
        isPlayer1 = player1;
    }

    public void setBtns(JButton... btns) {
        this.btns = new ArrayList<>();
        for (JButton btn : btns) {
            this.btns.add(btn);
        }
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae) {
        String ans = ae.getActionCommand();
        QuestionData questionData = gameData.getQuestions().get(gameData.getCurrentQuestion());
        String correctAns = questionData.getAnswers().get(questionData.getAns());

        JButton currentBtn = (JButton) ae.getSource();

        for (JButton btn : btns) {
            if (btn.getText().equals(correctAns)) {
                // Highlight and show the correct answer as green
                btn.setBackground(Color.GREEN);
            } else {
                // Blur and show the incorrect answer as gray
                btn.setBackground(Color.LIGHT_GRAY);
            }
            btn.setOpaque(true);
        }

        if (ans.equals(correctAns)) {
            // If the chosen answer is correct, paint it green
            currentBtn.setBackground(Color.GREEN);

            // Update score for the appropriate player
            if (isPlayer1) {
                gameData.setPlayer1Score(gameData.getPlayer1Score() + 1);
            } else {
                gameData.setPlayer2Score(gameData.getPlayer2Score() + 1);
            }
        } else {
            // If the chosen answer is incorrect, paint it red
            currentBtn.setBackground(Color.RED);
        }
        currentBtn.setOpaque(true);

        try {
            client.sendToServer(String.format(
                    "client submitted answer,%b,%d,%d", isPlayer1, gameData.getScore(), gameData.getCurrentQuestion()
            ));

        } catch (IOException e) {
            System.out.println("Failed to send score update to server");
        }
    }

    // Handle timer
    public void direct() {
        CardLayout cardLayout = (CardLayout) container.getLayout();
        cardLayout.show(container, "2");
        System.out.println("To Correct Answer Panel");
    }
}
