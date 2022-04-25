package Client;

import Client.CreateAccountView.CreateAccountControl;
import Client.CreateAccountView.CreateAccountPanel;
import Client.InitialView.InitialControl;
import Client.InitialView.InitialPanel;
import Client.LoginView.LoginControl;
import Client.LoginView.LoginPanel;
import Client.QuestionView.QuestionControl;
import Client.QuestionView.QuestionPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientGUI extends JFrame {
  private GameClient client;

  public ClientGUI() {
    this.setTitle("Play Trivia!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    client = new GameClient();
    try
    {
      client.openConnection();
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    // Create the Controllers next
    InitialControl ic = new InitialControl(container);
    LoginControl lc = new LoginControl(container, client);
    CreateAccountControl cc = new CreateAccountControl(container, client);
    QuestionControl qc = new QuestionControl(container);
//    CorrectAnswerControl cac = new CorrectAnswerControl(container);

    // Set the controllers in ChatClient
    client.setLoginControl(lc);
    client.setCreateControl(cc);

    /*
     * Create the views and associate them with their corresponding controllers View
     * 1: Initial panel View 2: Login panel View 3: Create panel View 4: Contacts
     * panel
     */
    JPanel initialView = new InitialPanel(ic);
    JPanel loginView = new LoginPanel(lc);
    JPanel createAccountView = new CreateAccountPanel(cc);
    JPanel watingScreenView = new WaitingScreenPanel();
    JPanel questionView = new QuestionPanel(qc);

    // Add the views to the card layout container
    container.add(initialView, "1");
    container.add(loginView, "2");
    container.add(createAccountView, "3");
//    container.add(watingScreenView, "4");
    container.add(questionView, "4");

    // Show the initial view in the card layout.
    cardLayout.show(container, "1");

    // Add the card layout container to the JFrame.
    this.add(container, BorderLayout.CENTER);

    // Show the JFrame.
    this.setSize(800, 500);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args) {
    new ClientGUI();
  }
}