package Kahoot;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class GameCountdownPanel extends JPanel
{
		
	public GameCountdownPanel(GameCountdownControl cd) 
	{
	    
		JLabel label = new JLabel("Game About to Begin!", JLabel.CENTER);
		
		
		// Create the login button.
	    JButton loginButton = new JButton("Start");
	    loginButton.addActionListener(cd);
	    JPanel loginButtonBuffer = new JPanel();
	    loginButtonBuffer.add(loginButton);
	    
	    
	 // Arrange the components in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
	    grid.add(label);
	    grid.add(loginButtonBuffer);
	    this.add(grid);
	    
	}    
}




