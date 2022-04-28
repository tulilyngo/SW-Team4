package Data;

import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {
    private List<QuestionData> questions;
    List<Player> players;
    private boolean gameOver = false;
    private int currentQuestion = 0;

    public GameData(List<QuestionData> questions) {
        this.questions = questions;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
