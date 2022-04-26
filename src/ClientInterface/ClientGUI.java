package ClientInterface;

import ClientComm.CreateAccountControl;
import ClientComm.InitialControl;
import ClientComm.LoginControl;
import ServerComm.QuestionControl;
import ServerComm.GameClient;

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
    client.container = container;

    // Create the Controllers next
    InitialControl ic = new InitialControl(container);
    LoginControl lc = new LoginControl(container, client);
    CreateAccountControl cc = new CreateAccountControl(container, client);
    QuestionControl qc = new QuestionControl(container);
//    CorrectAnswerControl cac = new CorrectAnswerControl(container);

    // Set the controllers in ChatClient
    client.setLoginControl(lc);
    client.setCreateControl(cc);

    // Create the views and associate them with their corresponding controllers View
    JPanel initialView = new InitialPanel(ic);
    JPanel loginView = new LoginPanel(lc);
    JPanel createAccountView = new CreateAccountPanel(cc);
    JPanel watingScreenView = new WaitingScreenPanel();
    JPanel questionView = new QuestionPanel(qc);

    // Add the views to the card layout container
    container.add(initialView, "initial");
    container.add(loginView, "login");
    container.add(createAccountView, "create");
    container.add(watingScreenView, "wait");
    container.add(questionView, "question");

    // Show the initial view in the card layout.
    cardLayout.show(container, "initial");

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