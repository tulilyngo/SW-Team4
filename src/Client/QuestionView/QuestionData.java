package Client.QuestionView;

import Server.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionData {
  private Database database;

  public ArrayList<String> getQuestion() throws SQLException {
    ArrayList<String> question = database.getQuestions();
    
    return question;
  }
}
