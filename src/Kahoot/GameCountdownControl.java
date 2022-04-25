package Kahoot;

//import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GameCountdownControl implements ActionListener
{
		private JButton countdown;
		private JLabel beginDisplay;	
		private JLabel number;
		private Timer timer;

	// Private data field for storing the container.
	  private JPanel container;
	  // Constructor for the  controller.
	  public GameCountdownControl(JPanel container)
	  {
	    this.container = container;
	  }
	  
	  public class event implements ActionListener
		{
		  @Override
		  public void actionPerformed(ActionEvent e)
			{
				//cast to integer since entered as string
				int count = (int)(Double.parseDouble((number.getText())));
				number.setText(""+count);
				
				//object time class created 
				TimeClass tc = new TimeClass(count);
				timer = new Timer(1000, tc);
				timer.start();
			}
		}
		
		//logic of decrementing the counter time class object
		public class TimeClass implements ActionListener
		{
			int counter;
			 
			public TimeClass(int counter)
			{
				this.counter = counter;
			}
			
			
			public void actionPerformed(ActionEvent tc)
			{
				counter--;
				
				if(counter >= 1)
				{
					number.setText(""+counter);
				}
				else
				{
					timer.stop();
					number.setText("Begin!");
				}
			}
}

		//@Override
		//public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		//}
}
