package ServerComm;

import java.awt.*;

import ClientComm.CreateAccountControl;
import ClientInterface.QuestionPanel;
import Database.CreateAccountData;
import ClientComm.LoginControl;
import Database.GameData;
import ocsf.client.AbstractClient;

import javax.swing.*;

public class GameClient extends AbstractClient {
    private LoginControl loginControl;
    private CreateAccountControl createAccountControl;
    private QuestionControl questionControl;

    private JPanel container;
    private CardLayout cardLayout;

    QuestionPanel questionPanel;

    boolean start = true;

    public GameClient(JPanel container, CardLayout cardLayout) {
        super("localhost",8300);
        this.container = container;
        this.cardLayout = cardLayout;
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
        else if (arg0 instanceof GameData) {
            GameData gameData = (GameData) arg0;

            if (start) {
                // Start game
                JPanel questionView = new QuestionPanel(questionControl, gameData);
                questionPanel = (QuestionPanel) questionView;
                container.add(questionView, "question");
                cardLayout.show(container, "question");
                start = false;
            } else {
                questionPanel.updateQuestion(questionControl, gameData);
            }
        }
        // Server sending back a string = error
        else {
            String msg = (String) arg0;

            // Determine which panel the error msg belongs to
            if (msg.equals("Cannot find log in info. Check username/password or create an account.")) {
                loginControl.displayError(msg);
            }
            else if (msg.equals("Username already existed.")) {
                createAccountControl.displayError(msg);
            }
        }
    }

    public void connectionException (Throwable exception) {
        System.out.println("Connection Exception Occurred");
        System.out.println(exception.getMessage());
        exception.printStackTrace();
    }

    public void setLoginControl(LoginControl loginControl) {
        this.loginControl = loginControl;
    }

    public void setCreateControl(CreateAccountControl createAccountControl) {
        this.createAccountControl = createAccountControl;
    }

    public void setQuestionControl(QuestionControl questionControl) {
        this.questionControl = questionControl;
    }
}
