package ClientComm;

import ServerComm.GameClient;
import Database.LoginData;
import ClientInterface.LoginPanel;
import ServerComm.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener {
    private JPanel container;
    private GameClient client;
    private Player player;

    public LoginControl(JPanel container, GameClient client) {
        this.container = container;
        this.client = client;
        player = new Player("", "");
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent ae) {
        // Get the name of the button clicked
        String command = ae.getActionCommand();

        // The Cancel button takes the user back to the initial panel
        if (command == "Cancel") {
            // Reset the log in panel
            LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
            loginPanel.clearContents();

            CardLayout cardLayout = (CardLayout)container.getLayout();
            cardLayout.show(container, "1");
        }

        // The Submit button submits the create information to the server
        else if (command == "Submit") {
            //Get the username and password the user entered
            LoginPanel loginPanel = (LoginPanel)container.getComponent(1);	//2nd panel of the CardLayout container
            LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

            //Check the validity of the info locally
            if (data.getUsername().equals("") || data.getPassword().equals("")) {
                displayError("You must enter a username and password.");
                return;
            }

            //Submit the login info to the server
            try {
                client.sendToServer(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // After the login is successful and only one player is currently in the game,
    // redirect to waiting scree
    public void loginSuccess(Integer numPlayers) {
        //Reset the log in panel
        LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
        loginPanel.clearContents();

        CardLayout cardLayout = (CardLayout) container.getLayout();
        if (numPlayers != 2) {
            cardLayout.show(container, "wait");
        }
    }

    public void displayError(String error) {
        LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
        loginPanel.setError(error);
    }
}
