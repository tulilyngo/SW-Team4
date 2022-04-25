package Kahoot;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
  private boolean foundUser; // check if a user is found in the database
  private String key = "key";

  private Connection con;
  private Statement stmt;
  private ResultSet rs;

  public Database() throws IOException {
    // Read properties file
    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream("Kahoot/db.properties");
    prop.load(fis);
    String url = prop.getProperty("url");
    String user = prop.getProperty("user");
    String pass = prop.getProperty("password");

    try {
      con = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<String> query(String query) {
    ArrayList<String> result = new ArrayList<String>();

    try {
      // Create a statement
      stmt = con.createStatement();

      // Execute a query
      rs = stmt.executeQuery(query);

      // If the result set is empty, return null. Else, proceed to read into the array
      // list
      // Use do while so not to lose the 1st line of the file
      if (!rs.next()) {
        System.out.println("Result set is empty.");
        return null;
      } else {
        do {
          // Get metadata about the query
          ResultSetMetaData rmd = rs.getMetaData();

          // Get the # of columns
          int no_col = rmd.getColumnCount();

          // Add info to the Result array list
          for (int i = 1; i <= no_col; i++)
            result.add(rs.getString(i));

        } while (rs.next());
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return result;
  }
  
  public ArrayList<String> getQuestion()
  {
    System.out.println("Call success");
    ArrayList<String> result = query(
        "SELECT question FROM questions;");

   return result;
  }
}
