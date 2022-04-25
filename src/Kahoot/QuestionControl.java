package Kahoot;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class QuestionControl implements ActionListener {
  // Private data field for storing the container.
  private JPanel container;

  // Constructor for the initial controller.
  public QuestionControl(JPanel container) {
    this.container = container;
  }

  // Handle button clicks.
  public void actionPerformed(ActionEvent ae) {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Login button takes the user to the login panel.
    if (command.equals("Answer 1")) {
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "2");
    }
    // The Create button takes the user to the create account panel.
    else if (command.equals("Create")) {
      CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
      createAccountPanel.setError("");
      CardLayout cardLayout = (CardLayout) container.getLayout();
      cardLayout.show(container, "3");
    }
  }
  
  // Handle timer
  public void direct() {
    CardLayout cardLayout = (CardLayout) container.getLayout();
    cardLayout.show(container, "2");
    System.out.println("To Correct Answer Panel");
  }
}
