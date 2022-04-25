package Kahoot;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class GameCountdownPanel extends JFrame
{
	 JButton countdown;
	 JLabel beginDisplay;	
	 JLabel number;
	 Timer timer;
	
	public GameCountdownPanel(String title) 
	{
	    this.setTitle(title);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  //creates the event
		  event e = new event();
	  	JPanel south = new JPanel(new FlowLayout());
	    
	    
	// contains all panels
	    JPanel combine_panel = new JPanel();
	    combine_panel.setLayout(new BoxLayout(combine_panel, BoxLayout.Y_AXIS));
	    combine_panel.add(south);
	    
	  //countdown
		  countdown = new JButton("start");
		  countdown.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  south.add(countdown);
		  countdown.addActionListener(e);

	    getContentPane().add(combine_panel, BorderLayout.CENTER);
	    
	    //add to panel
	    JPanel center = new JPanel();
	    center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    combine_panel.add(center);
	    number = new JLabel("5");
	    number.setFont(new Font("Tahoma", Font.PLAIN, 60));
	    center.add(number);
	    
	    Panel panel = new Panel();
	    combine_panel.add(panel);
	    
		  
	// South panel for "Game about to begin"
        beginDisplay = new JLabel(title + "Game is about to begin!");
        beginDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(beginDisplay);

	    setSize(800, 450);
	    setVisible(true);
	}
	
	//Performs event 
	public class event implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//cast to int since entered as string
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
	
	public static void main(String[] args) 
	{
		new GameCountdownPanel(" "); // args[0] represents the title of the GUI
	
	}
}



