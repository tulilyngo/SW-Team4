package Kahoot;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CreateAccountControl implements ActionListener
{
  // Private data fields for the container and chat client.
  private JPanel container;
  
  // Constructor for the create account controller.
  public CreateAccountControl(JPanel container)
  {
    this.container = container;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Cancel button takes the user back to the initial panel.
    if (command == "Cancel")
    {
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "1");
    }

    // The Submit button creates a new account.
    else if (command == "Submit")
    {
      // Get the text the user entered in the three fields.
      CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
      String username = createAccountPanel.getUsername();
      String password = createAccountPanel.getPassword();
      String passwordVerify = createAccountPanel.getPasswordVerify();

      // Check the validity of the information locally first.
      if (username.equals("") || password.equals(""))
      {
        displayError("You must enter a username and password.");
        return;
      }
      else if (!password.equals(passwordVerify))
      {
        displayError("The two passwords did not match.");
        return;
      } else if (password.length() < 6) {
        displayError("The password must be at least 6 characters.");
        return;
      } else {
    	  CardLayout cardLayout = (CardLayout)container.getLayout();
          cardLayout.show(container, "4");
      }
            
    }
  }

  // After an account is created, set the User object and display the contacts screen.
  public void createAccountSuccess()
  {
    CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
    ClientGUI clientGUI = (ClientGUI)SwingUtilities.getWindowAncestor(createAccountPanel);
    //clientGUI.setUser(new User(createAccountPanel.getUsername(), createAccountPanel.getPassword()));
    CardLayout cardLayout = (CardLayout)container.getLayout();
    cardLayout.show(container, "4");
  }
  
  // Method that displays a message in the error label.
  public void displayError(String error)
  {
    CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
    createAccountPanel.setError(error);
  }
}
