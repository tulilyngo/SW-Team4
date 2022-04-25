package Client.CreateAccountView;

import Client.GameClient;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener
{
	private JPanel container;
	private GameClient client;

	public CreateAccountControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
	}

	//Handle button clicks
	public void actionPerformed(ActionEvent ae)
	{
		//Get the name of the button clicked
		String command = ae.getActionCommand();

		//The Cancel button takes the user back to the initial panel
		if (command == "Cancel")
		{
			//Reset the create account panel
			CreateAccountPanel createPanel = (CreateAccountPanel)container.getComponent(2);
			createPanel.clearContents();
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}

		//The Submit button submits the login information to the server
		else if (command == "Submit")
		{
			//Get the username and password the user entered
			CreateAccountPanel createPanel = (CreateAccountPanel)container.getComponent(2);	//3rd panel of the CardLayout container
			CreateAccountData data = new CreateAccountData(createPanel.getUsername(), createPanel.getPassword(), createPanel.getPassword2nd());

			//Check the validity of the info locally
			if (data.getUsername().equals("") || data.getPassword().equals("") || data.getPassword2nd().equals(""))
			{
				displayError("Please complete all fields.");
				return;
			}
			else if (data.getPassword().length() < 6)
			{
				displayError("Password needs to be at least 6 characters.");
				return;
			}
			else if (!(data.getPassword().equals(data.getPassword2nd())))
			{
				displayError("Password verification error.");
				return;
			}

			//Submit the create info to the server
			try
			{
				client.sendToServer(data);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	//After the creation is successful, display the login screen
	//Invoked by the ChatClient
	public void createSuccess()
	{	
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "1");

		//Reset the create account panel
		CreateAccountPanel createPanel = (CreateAccountPanel)container.getComponent(2);
		createPanel.clearContents();
	}

	public void displayError(String error)
	{
		CreateAccountPanel createPanel = (CreateAccountPanel)container.getComponent(2);
		createPanel.setError(error);
	}
}
