package Database;

import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {
    private static int currentQuestion = 0;

    private List<QuestionData> questions;
    private int player1Score = 0;
    private int player2Score = 0;
    private int timeLeft = 15;
    private boolean isPlayer1;

    public GameData(List<QuestionData> questions) {
        this.questions = questions;
    }

    public static int getCurrentQuestion() {
        return currentQuestion;
    }

    public static void setCurrentQuestion(int currentQuestion) {
        GameData.currentQuestion = currentQuestion;
    }

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionData> questions) {
        this.questions = questions;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public void setPlayer1(boolean player1) {
        isPlayer1 = player1;
    }
}
