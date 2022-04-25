package Kahoot;

import java.util.ArrayList;

public class QuestionData {
  private Database database;

  public ArrayList<String> getQuestion() {
    ArrayList<String> question = database.getQuestion();
    
    return question;
  }
}
