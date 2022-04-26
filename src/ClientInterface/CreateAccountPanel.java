package ClientInterface;

import ClientComm.CreateAccountControl;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel {
    private JLabel errorLabel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JPasswordField password2nd;
    private JButton submit;
    private JButton cancel;

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getPassword2nd() {
        return new String(password2nd.getPassword());
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public void setPasswordField(String pw) {
        this.passwordField.setText(pw);
    }

    public void setUsernameField(String username) {
        this.usernameField.setText(username);
    }

    public void setPassword2nd(String pw) {
        this.password2nd.setText(pw);
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setError(String error) {
        errorLabel.setText(error);
    }

    public String getError() {
        return errorLabel.getText();
    }

    public CreateAccountPanel(CreateAccountControl cc) {

        JPanel labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        errorLabel = new JLabel("", JLabel.CENTER);
        errorLabel.setForeground(Color.RED);

        JLabel instructionLabel = new JLabel("Enter your username and password to create an account.", JLabel.CENTER);
        JLabel instructionLabel2 = new JLabel("Your password must be at least 6 characters.", JLabel.CENTER);

        labelPanel.add(errorLabel);
        labelPanel.add(instructionLabel);
        labelPanel.add(instructionLabel2);

        // Create a panel for the login information form
        JPanel createPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
        usernameField = new JTextField(10);

        JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
        passwordField = new JPasswordField(10);

        JLabel passwordLabel2nd = new JLabel("Verify Password:", JLabel.RIGHT);
        password2nd = new JPasswordField(10);

        createPanel.add(usernameLabel);
        createPanel.add(usernameField);
        createPanel.add(passwordLabel);
        createPanel.add(passwordField);
        createPanel.add(passwordLabel2nd);
        createPanel.add(password2nd);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();

        submit = new JButton("Submit");
        submit.addActionListener(cc);

        cancel = new JButton("Cancel");
        cancel.addActionListener(cc);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        // Arrange the three panels in a grid
        JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
        grid.add(labelPanel);
        grid.add(createPanel);
        grid.add(buttonPanel);
        this.add(grid);
    }

    public void clearContents() {
        usernameField.setText("");
        passwordField.setText("");
        password2nd.setText("");
    }
}
