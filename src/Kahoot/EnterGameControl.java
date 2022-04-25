package Kahoot;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class EnterGameControl implements ActionListener
{
	  private JPanel container;
	  
	  // Constructor for the enbter game controller.
	  public EnterGameControl(JPanel container)
	  {
	    this.container = container;
	  }
	  
	  // Handle button clicks.
	  public void actionPerformed(ActionEvent ae)
	  {
	    // Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    if (command == "Enter")
	    {
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "5");
	    }
	  }
	  
	}
