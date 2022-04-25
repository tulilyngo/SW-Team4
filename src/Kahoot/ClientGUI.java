package Kahoot;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientGUI extends JFrame
{
	  // Constructor that creates the client GUI.
	  public ClientGUI()
	  {
	    // Set the title and default close operation.
	    this.setTitle("Kahoot Client");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    // Create the card layout container.
	    CardLayout cardLayout = new CardLayout();
	    JPanel container = new JPanel(cardLayout);
	    
	    //Create the Controllers next
	    //Next, create the Controllers
	    InitialControl ic = new InitialControl(container);
	    LoginControl lc = new LoginControl(container);
	    CreateAccountControl cac = new CreateAccountControl(container);
	    EnterGameControl ec = new EnterGameControl(container);
	    GameCountdownControl cd = new GameCountdownControl(container);
	    
	    	   
	    
	    // Create the four views. (need the controller to register with the Panels
	    JPanel view1 = new InitialPanel(ic);
	    JPanel view2 = new LoginPanel(lc);
	    JPanel view3 = new CreateAccountPanel(cac);
	    JPanel view4 = new EnterGamePanel(ec);
	    JPanel view5 = new GameCountdownPanel(cd);
	    
	    // Add the views to the card layout container.
	    container.add(view1, "1");
	    container.add(view2, "2");
	    container.add(view3, "3");
	    container.add(view4, "4");
	    container.add(view5, "5");
	    
	    // Show the initial view in the card layout.
	    cardLayout.show(container, "1");
	    
	    // Add the card layout container to the JFrame.
	    // GridBagLayout makes the container stay centered in the window.
	    this.setLayout(new GridBagLayout());
	    this.add(container);

	    // Show the JFrame.
	    this.setSize(550, 350);
	    this.setVisible(true);
	  }

	  // Main function that creates the client GUI when the program is started.
	  public static void main(String[] args)
	  {
	    new ClientGUI();
	  }
	}