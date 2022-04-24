package Kahoot;

import java.awt.*;
import javax.swing.*;

//import Kahoot.ServerGUI.EventHandler;

import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class ServerGUI extends JFrame
{
		private JLabel status;
	 	private String[] labels = {"Port #", "Timeout"};
	 	private JTextField[] textFields = new JTextField[labels.length];
	 	private JTextArea log;
	 	private JButton listen;
	 	private JButton close;
	 	private JButton stop;
	 	private JButton quit;
	   	private ChatServer server;
	   	private JTextField textField;
	    private DatabaseFile database = new DatabaseFile();

	   	
	   	
	   	public ServerGUI()
		{	
	   	  		
	   		
	   	//Frame 1	
	   		JFrame frame1 = new JFrame("First");
	   		frame1.getContentPane();
	   		frame1.setVisible(true);

	   		
	   		// Create the main variables that will be used.
		    JPanel north1 = new JPanel();
		    JPanel center1 = new JPanel(new BorderLayout());
		    JPanel south1 = new JPanel();
		    EventHandler handler = new EventHandler();
		    int i = 0;
		    
		    // Set the title and default close operation.
		    frame1.setTitle("Kahoot Server");
		    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   		
		   	JPanel enterPort = new JPanel();
		    FlowLayout flowLayout = (FlowLayout) enterPort.getLayout();
		    center1.add(enterPort, BorderLayout.NORTH);
		    
		    JLabel lblNewLabel = new JLabel("Port # : ");
		    enterPort.add(lblNewLabel);
	    
		    textField = new JTextField();
		    enterPort.add(textField);
		    textField.setColumns(10);
		    server.setStatus(status);
	   	
		    // Add the north, center, and south JPanels to the JFrame.
		    getContentPane().add(north1, BorderLayout.NORTH);
		    getContentPane().add(center1, BorderLayout.CENTER);
		    getContentPane().add(south1, BorderLayout.SOUTH);
	   	
	   	   	
	  //Frame 2
	   	JFrame frame2 = new JFrame("Second");
	   	frame1.getContentPane();
   		frame2.setVisible(true);

	   	// Create the main variables that will be used.
	    JPanel north2 = new JPanel();
	    JPanel center2 = new JPanel(new BorderLayout());
	    JPanel south2 = new JPanel();
	    EventHandler handler2 = new EventHandler();
	    int c = 0;
	    
	    // Set the title and default close operation.
	    frame2.setTitle("Kahoot Server");
	    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    // Create the status label.
	    JLabel statusText = new JLabel("Status:");
	    north2.add(statusText);
	    status = new JLabel("   Not Connected  ");
	    status.setForeground(Color.RED);
	    north2.add(status);
	    
	    // Create the grid of text fields.
	    JPanel centerNorth = new JPanel(new GridLayout(labels.length, 2, 5, 5));
	    for (i = 0; i < textFields.length; i++)
	    {
	      JLabel label = new JLabel(labels[i], JLabel.RIGHT);
	      centerNorth.add(label);
	      textFields[i] = new JTextField(10);
	      centerNorth.add(textFields[i]);
	    }

	    // Set some default values for the server.
	    textFields[0].setText("8300");
	    textFields[1].setText("500");

	    
	    // Create the server waiting panel.
	    JPanel serverLogPanel = new JPanel(new BorderLayout());
	    JLabel serverLabel = new JLabel("  WAITING   ", JLabel.CENTER);
	    JPanel serverLabelBuffer = new JPanel();
	    serverLabelBuffer.add(serverLabel);
	    serverLogPanel.add(serverLabelBuffer, BorderLayout.NORTH);
	    log = new JTextArea(10, 35);
	   
	    // Add the north, center, and south JPanels to the JFrame.
	    getContentPane().add(north2, BorderLayout.NORTH);
	    getContentPane().add(center2, BorderLayout.CENTER);
	    getContentPane().add(south2, BorderLayout.SOUTH);
	    
	    
	  //Frame 3
	   	JFrame frame3 = new JFrame("Second");
	   	frame3.getContentPane();
   		frame3.setVisible(true);

	   	// Create the main variables that will be used.
	    JPanel north3 = new JPanel();
	    JPanel center3 = new JPanel(new BorderLayout());
	    JPanel south3 = new JPanel();
	    EventHandler handler3 = new EventHandler();
	    int d = 0;
	    
	    // Set the title and default close operation.
	    frame2.setTitle("Kahoot Server");
	    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //puts player username in the text field
	    // Retrieving the list of column names
        int count = database.getColumnPlayers();
        for (int b = 0; i <= count; i++) {
        	log.setText(database.data.getUsername[b]); 
        }
	    
	    JScrollPane serverLogPane = new JScrollPane(log);
	    JPanel serverLogPaneBuffer = new JPanel();
	    serverLogPaneBuffer.add(serverLogPane);
	    serverLogPanel.add(serverLogPaneBuffer, BorderLayout.SOUTH);

	    // Add the server log panel to the south part of the center.
	    JPanel centerSouth = new JPanel();
	    centerSouth.add(serverLogPanel);
	    center3.add(centerSouth, BorderLayout.SOUTH);

	    // Create the buttons.
	    listen = new JButton("Listen");
	    listen.addActionListener(handler);
	    south3.add(listen);
	    close = new JButton("Close");
	    close.addActionListener(handler);
	    south3.add(close);
	    stop = new JButton("Stop");
	    stop.addActionListener(handler);
	    south3.add(stop);
	    quit = new JButton("Quit");
	    quit.addActionListener(handler);
	    south3.add(quit);
	    
	    // Add the north, center, and south JPanels to the JFrame.
	    getContentPane().add(north3, BorderLayout.NORTH);
	    getContentPane().add(center3, BorderLayout.CENTER);
	    getContentPane().add(south3, BorderLayout.SOUTH);
	    
	    // Display the window.
	    this.setSize(400, 450);
	    this.setVisible(true);
	    
	    
		}
	   	
	 // Main function that creates a server GUI when the program is started.
	 		public static void main(String[] args)
	 		{
	 			new ServerGUI();
	 		}
	 		
	 		// Getters for the important components.
	 		public JTextField getTextFieldAt(int index)
	 		{
	 		  return textFields[index];
	 		}
	 		public JLabel getStatus()
	 	  {
	 		  return status;
	 	  }
	 		public JTextArea getLog()
	 	  {
	 		  return log;
	 	  }
	 		
	 		// Class for handling events.
	 		class EventHandler implements ActionListener
	 		{
	 		  // Event handler for ActionEvent.
	 	    public void actionPerformed(ActionEvent e)
	 	    {
	 	      // Determine which button was clicked.
	 	      Object buttonClicked = e.getSource();
	 	      
	 	      // Handle the Listen button.
	 	      if (buttonClicked == listen)
	 	      {
	 	        // Display an error if the port number or timeout was not entered.
	 	        if (textFields[0].getText().equals("") || textFields[1].getText().equals(""))
	 	        {
	 	          log.append("Port number or timeout not entered before pressing Listen\n");
	 	        }
	 	        
	 	        // Otherwise, tell the server to start listening with the user's settings.
	 	        else
	 	        {
	 	          server.setPort(Integer.parseInt(textFields[0].getText()));
	 	          server.setTimeout(Integer.parseInt(textFields[1].getText()));
	 	          try
	 	          {
	 	            server.listen();
	 	          }
	 	          catch (IOException e1)
	 	          {
	 	            log.append("An exception occurred: " + e1.getMessage() + "\n");
	 	          }
	 	        }
	 	      }
	 	      
	 	      // Handle the Close button.
	 	      else if (buttonClicked == close)
	 	      {
	 	        // Display an error if the server has not been started.
	 	        if (!server.isRunning())
	 	        {
	 	          log.append("Server not currently started\n");
	 	        }
	 	        
	 	        // Otherwise, close the server.
	 	        else
	 	        {
	 	          try
	 	          {
	 	            server.close();
	 	          }
	 	          catch (IOException e1)
	 	          {
	 	            log.append("An exception occurred: " + e1.getMessage() + "\n");
	 	          }
	 	        }
	 	      }
	 	      
	 	      // Handle the Stop button.
	 	      else if (buttonClicked == stop)
	 	      {
	 	        // Display an error if the server is not listening.
	 	        if (!server.isListening())
	 	        {
	 	          log.append("Server not currently listening\n");
	 	        }
	 	        
	 	        // Otherwise, stop listening.
	 	        else
	 	        {
	 	          server.stopListening();
	 	        }
	 	      }
	 	      
	 	      // For the Quit button, just stop this program.
	 	      else if (buttonClicked == quit)
	 	      {
	 	        System.exit(0);
	 	      }
	 	    }
	 	}
	 	
}


