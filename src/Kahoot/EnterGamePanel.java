package Kahoot;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EnterGamePanel extends JPanel {
	// Private data fields for the important GUI components.
	private JTextField portField;

	public String getPort() {
		return portField.getText();
	}

	// Constructor for the login panel.
	public EnterGamePanel(EnterGameControl lc) {
	
		JPanel enterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel portLabel = new JLabel("Port:", JLabel.RIGHT);
		portField = new JTextField(10);
		enterPanel.add(portLabel);
		enterPanel.add(portField);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Enter");
		submitButton.addActionListener(lc);
		buttonPanel.add(submitButton);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(2, 1, 0, 10));
		grid.add(enterPanel);
		grid.add(buttonPanel);
		this.add(grid);
	}
}