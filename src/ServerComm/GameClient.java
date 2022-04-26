package ServerComm;

import java.awt.*;

import ClientComm.CreateAccountControl;
import Database.CreateAccountData;
import ClientComm.LoginControl;
import ocsf.client.AbstractClient;

import javax.swing.*;

public class GameClient extends AbstractClient {
    private LoginControl loginControl;
    private CreateAccountControl createAccountControl;
    private QuestionControl questionControl;

    public JPanel container;

    public GameClient()
    {
        super("localhost",8300);
    }

    @Override
    public void handleMessageFromServer(Object arg0) {
        if (arg0 == null) {

        }
        // Sever sends an integer = successful login, the integer is the # players currently in the game
        else if (arg0 instanceof Integer) {
            loginControl.loginSuccess((Integer) arg0);
        }
        // Server sending back a CreateAccountData instance = successful creation
        else if (arg0 instanceof CreateAccountData) {
            createAccountControl.createSuccess();
        }
        // Server sending back a string = error
        else {
            String msg = (String)arg0;

            // Determine which panel the error msg belongs to
            if (msg.equals("Cannot find log in info. Check username/password or create an account.")) {
                loginControl.displayError(msg);
            }
            else if (msg.equals("Username already existed.")) {
                createAccountControl.displayError(msg);
            }
            else if (msg.equals("start")) {
                // initialize the game
                CardLayout cardLayout = (CardLayout)container.getLayout();
                cardLayout.show(container, "question");
            }
        }
    }

    public void connectionException (Throwable exception) {
        System.out.println("Connection Exception Occurred");
        System.out.println(exception.getMessage());
        exception.printStackTrace();
    }

    public void setLoginControl(LoginControl lc)
    {
        this.loginControl = lc;
    }

    public void setCreateControl(CreateAccountControl cc)
    {
        this.createAccountControl = cc;
    }
}
