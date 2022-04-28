package Data;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
    private boolean foundUser; // check if a user is found in the database
    private String key = "key";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public Database() throws IOException {
        // Read properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/db.properties");
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

    public ArrayList<String> query(String query) {
        ArrayList<String> result = new ArrayList();

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
            }
            else {
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
            System.out.println("Failed to execute query: " + e.getMessage());
            return null;
        }

        return result;
    }

    public void executeDML(String dml) throws SQLException {
        // Add your code here
        stmt = con.createStatement();
        stmt.execute(dml);
    }

    public void findUser(Player user) throws SQLException {
        ArrayList<String> result = query("SELECT username, password FROM client " + "WHERE username = \""
            + user.getUsername() + "\" " + "AND password = aes_encrypt(\"" + user.getPassword() + "\",\"" + key + "\");");

        if (result == null) {
            foundUser = false;
        }
        else {
            foundUser = true;
        }
    }

    public void addUser(Player user) {
        try {
            executeDML("INSERT INTO client " + "VALUES (\"" + user.getUsername() + "\"," + "aes_encrypt(\""
                + user.getPassword() + "\",\"" + key + "\"));");
            foundUser = false;
        } catch (SQLException e) {
            foundUser = true;
        }
    }

    public boolean getFoundUser() {
        return foundUser;
    }

    public List<QuestionData> getQuestions() {
        ArrayList<String> resultQuestion = query(
                "SELECT * FROM question;");

        int id;
        String question;
        String ans;

        List<QuestionData> questions = new ArrayList<>();
        for (int i = 0; i < resultQuestion.size(); i += 3) {
            id = Integer.parseInt(resultQuestion.get(i));
            question = resultQuestion.get(i + 1);
            ans = resultQuestion.get(i + 2);

            List<String> resultAnswer = query(
                    "SELECT answer FROM answer " + "WHERE questionID = \"" + id + "\";");

            questions.add(new QuestionData(id, question, resultAnswer, ans));
        }

        return questions;
    }
}


