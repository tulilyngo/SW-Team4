package ClientInterface;

import ClientComm.GameOverControl;
import Database.GameData;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private JLabel statusLabel;
    private JLabel resultLabel;
    private JButton exit;
    private JButton playAgain;
    private JPanel labelPanel;
    private JPanel btnPanel;
    private JPanel all;

    public GameOverPanel(GameOverControl gameOverControl, GameData gameData, boolean isPlayer1) {
        statusLabel = new JLabel("Game Over!", JLabel.CENTER);
        statusLabel.setFont(new Font("Serif", Font.BOLD, 16));

        if (gameData.getPlayer1Score() > gameData.getPlayer2Score()) {
            if (isPlayer1) {
                resultLabel = new JLabel("You won!", JLabel.CENTER);
            } else {
                resultLabel = new JLabel("You lost...", JLabel.CENTER);
            }
        } else if (gameData.getPlayer1Score() < gameData.getPlayer2Score()) {
            if (isPlayer1) {
                resultLabel = new JLabel("You lost...", JLabel.CENTER);
            } else {
                resultLabel = new JLabel("You won!", JLabel.CENTER);
            }
        } else {
            resultLabel = new JLabel("It's a tie!", JLabel.CENTER);
        }
        resultLabel.setFont(new Font("Serif", Font.BOLD, 16));

        btnPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        exit = new JButton("Exit");
        exit.addActionListener(gameOverControl);
        playAgain = new JButton("Play Again");
        playAgain.addActionListener(gameOverControl);
        btnPanel.add(exit);
        btnPanel.add(playAgain);

        labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        labelPanel.add(statusLabel);
        labelPanel.add(resultLabel);

        // Arrange the components
        all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.add(labelPanel);
        all.add(btnPanel);
        this.add(all);
    }
}
