
package Data;

import java.io.Serializable;

public class Player implements Serializable {
    private String username;
    private String password;
    private int score;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}