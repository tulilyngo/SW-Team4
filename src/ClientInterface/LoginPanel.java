package ClientInterface;

import ClientComm.LoginControl;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JButton submit;
    private JButton cancel;

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setError(String error) {
        errorLabel.setText(error);
    }

    public LoginPanel(LoginControl loginControl) {
        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        errorLabel = new JLabel("", JLabel.CENTER);
        errorLabel.setForeground(Color.RED);

        JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);

        labelPanel.add(errorLabel);
        labelPanel.add(instructionLabel);

        // Create a panel for the login information form.
        JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
        usernameField = new JTextField(10);
        // TODO: only for quick testing, remove when done
        usernameField.setText("lngo");

        JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
        passwordField = new JPasswordField(10);
        // TODO: only for quick testing, remove when done
        passwordField.setText("123123");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        // Create a panel for the buttons.
        JPanel buttonPanel = new JPanel();

        submit = new JButton("Submit");
        submit.addActionListener(loginControl);

        cancel = new JButton("Cancel");
        cancel.addActionListener(loginControl);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        // Arrange the three panels in a grid.
        JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
        grid.add(labelPanel);
        grid.add(loginPanel);
        grid.add(buttonPanel);
        this.add(grid);
    }

    public void clearContents() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
