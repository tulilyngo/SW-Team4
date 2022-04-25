package Kahoot;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.*;


public class ServerGUI extends JFrame
{
	//Data fields.
	private JLabel status;
	private JTextField portField;
	private String[] labels = {"Port #", "Timeout"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea waitingRoom;
	private ResultSet wr;
	private JButton listen;
	private JButton stop;
	private JButton quit;
	private ChatServer server;
	private DatabaseFile database = new DatabaseFile();
	
	// Constructor for the server GUI.
		public ServerGUI()
		{	
		  // Create the main variables that will be used.
	 JPanel north = new JPanel();
	 JPanel center = new JPanel(new BorderLayout());
	 JPanel south = new JPanel();
	 EventHandler handler = new EventHandler();           
	 int i = 0;
	 
	 // Set the title and default close operation.
	 this.setTitle("Kahoot Server");
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 // Create the status label.
	 JLabel statusText = new JLabel("Status:");
	 north.add(statusText);
	 status = new JLabel("Not Connected  ");
	 status.setForeground(Color.RED);
	 north.add(status);
	 
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
	
	 // Buffer  and add it to the north part of the center.
	 JPanel centerNorthBuffer = new JPanel();
	 centerNorthBuffer.add(centerNorth);
	 
	 JLabel portLabel = new JLabel("Port #: ");
	 centerNorth.add(portLabel);
	 
	 JTextArea portDisplay = new JTextArea();
	 portDisplay.setText(getPort());
	 centerNorth.add(portDisplay);
	 center.add(centerNorthBuffer, BorderLayout.NORTH);
	 
	 // Create the server log panel.
	 JPanel serverLogPanel = new JPanel(new BorderLayout());
	 JLabel waitingLabel = new JLabel("Players Waiting", JLabel.CENTER);
	 JPanel serverLabelBuffer = new JPanel();
	 serverLabelBuffer.add(waitingLabel);
	 serverLogPanel.add(serverLabelBuffer, BorderLayout.NORTH);
	 waitingRoom = new JTextArea(10, 35);
	 PreparedStatement stmt = connection.prepareStatement("SHOW COLUMNS FROM `players`");      //-----------ERROR-----------------//
	 wr = stmt.executeQuery("SELECT * username"); //gets username of players                
	 waitingRoom.setText(wr);																	//-----------ERROR-----------------//
	 waitingRoom.setEditable(false);
	 JScrollPane serverLogPane = new JScrollPane(waitingRoom);
	 JPanel serverLogPaneBuffer = new JPanel();
	 serverLogPaneBuffer.add(serverLogPane);
	 serverLogPanel.add(serverLogPaneBuffer, BorderLayout.SOUTH);
	
	 // Add the server log panel to the south part of the center.
	 JPanel centerSouth = new JPanel();
	 centerSouth.add(serverLogPanel);
	 center.add(centerSouth, BorderLayout.SOUTH);
	
	 // Create the buttons.
	 listen = new JButton("Listen");
	 listen.addActionListener((ActionListener) handler);
	 south.add(listen);
	 stop = new JButton("Stop");
	 stop.addActionListener((ActionListener) handler);
	 south.add(stop);
	 quit = new JButton("Quit");
	 quit.addActionListener((ActionListener) handler);
	 south.add(quit);
	 
	 // Add the north, center, and south JPanels to the JFrame.
	 getContentPane().add(north, BorderLayout.NORTH);
	 getContentPane().add(center, BorderLayout.CENTER);
	 getContentPane().add(south, BorderLayout.SOUTH);
	 
	 // Display the window.
	 this.setSize(450, 450);
	 this.setVisible(true);
	 
	 // Set up the chat server object.
	 server = new ChatServer();
	 server.setLog(waitingRoom);
	 server.setStatus(status);
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
		  return waitingRoom;
	}
	public String getPort() {
		return portField.getText();
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
	          waitingRoom.append("Port number or timeout not entered before pressing Listen\n");
	        }
	        
	        // Otherwise, tell the server to start listening with the user's settings.
	        else
	        {
	          server.setPort(Integer.parseInt(textFields[0].getText()));                //-----------ERROR-----------------//
	          server.setTimeout(Integer.parseInt(textFields[1].getText()));              //-----------ERROR-----------------//
	          try 
	          {
	            server.listen();                                                       //-----------ERROR-----------------//
	          }
	          catch (IOException e1)
	          {
	        	  waitingRoom.append("An exception occurred: " + e1.getMessage() + "\n");
	          }
	        }
	      }
	      
	      // Handle the Close button.
	      else if (buttonClicked == close)                             //-----------ERROR-----------------//
	      {
	        // Display an error if the server has not been started.
	        if (!server.isRunning())
	        {
	        	waitingRoom.append("Server not currently started\n");
	        }
	        
	        // Otherwise, close the server.
	        else
	        {
	          try
	          {
	            server.close();                                             //-----------ERROR-----------------//
	          }
	          catch (IOException e1)
	          {
	        	  waitingRoom.append("An exception occurred: " + e1.getMessage() + "\n");
	          }
	        }
	      }
	      
	      // Handle the Stop button.
	      else if (buttonClicked == stop)
	      {
	        // Display an error if the server is not listening.
	        if (!server.isListening())                                  //-----------ERROR-----------------//
	        {
	        	waitingRoom.append("Server not currently listening\n");
	        }
	        
	        // Otherwise, stop listening.
	        else
	        {
	        	waitingRoom.stopListening();                                //-----------ERROR-----------------//
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
