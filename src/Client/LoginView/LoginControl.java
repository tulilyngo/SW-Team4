package Client.LoginView;

import Client.GameClient;
import Client.QuestionView.QuestionPanel;
import Server.Player;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private Player user;					//need this to retrieve user's contacts if login successful

	public LoginControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
		user = new Player("", "");
	}

	//Handle button clicks
	public void actionPerformed(ActionEvent ae)
	{
		//Get the name of the button clicked
		String command = ae.getActionCommand();

		//The Cancel button takes the user back to the initial panel
		if (command == "Cancel")
		{
			//Reset the log in panel
			LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
			loginPanel.clearContents();
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}

		//The Submit button submits the create information to the server
		else if (command == "Submit")
		{
			//Get the username and password the user entered
			LoginPanel loginPanel = (LoginPanel)container.getComponent(1);	//2nd panel of the CardLayout container
			LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

			//Check the validity of the info locally
			if (data.getUsername().equals("") || data.getPassword().equals(""))
			{
				displayError("You must enter a username and password.");
				return;
			}

			//Submit the login info to the server
			try
			{
				client.sendToServer(data);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	//After the login is successful, set the User object and display the contacts screen
	//Invoked by the ChatClient
	public void loginSuccess()
	{
//		ArrayList<String> contacts = client.getContacts();
		
		//Reset the log in panel 
		LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		loginPanel.clearContents();

		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "4");

		//Display contacts of the user
		QuestionPanel questionPanel = (QuestionPanel)container.getComponent(4);
//		questionPanel.displayQuestions(user, contacts);
	}

	public void displayError(String error)
	{
		LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		loginPanel.setError(error);
	}
}
