package Kahoot;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener
{
  // Private data fields for the container and chat client.
  private JPanel container;
  
  // Constructor for the login controller.
  public LoginControl(JPanel container)
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

    // The Submit button submits the login information to the server.
    else if (command == "Submit")
    {
      CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "4");
    }
  }

  // After the login is successful, set the User object and display the contacts screen.
  public void loginSuccess()
  {
    LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
    
    CardLayout cardLayout = (CardLayout)container.getLayout();
    cardLayout.show(container, "4");
  }

  // Method that displays a message in the error label.
  public void displayError(String error)
  {
    LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
    loginPanel.setError(error);
  }
}
