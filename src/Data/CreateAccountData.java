package Data;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
    private String username;
    private String password;
    private String password2nd;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPassword2nd()
    {
        return password2nd;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setPassword2nd(String password2nd)
    {
        this.password2nd = password2nd;
    }

    public CreateAccountData(String username, String password, String password2nd) {
        setUsername(username);
        setPassword(password);
        setPassword2nd(password2nd);
    }
}
