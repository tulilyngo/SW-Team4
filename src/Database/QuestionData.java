package Database;

import java.io.Serializable;
import java.util.List;

public class QuestionData implements Serializable {
    private int id;
    private String question;
    List<String> answers;
    private String ans;

    public QuestionData(int id, String question, List<String> answers, String ans) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.ans = ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
