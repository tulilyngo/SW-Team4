package ClientComm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverControl implements ActionListener {
    private JPanel container;

    public GameOverControl(JPanel container) {
        this.container = container;
    }

    public void actionPerformed(ActionEvent ae) {
        // Get the name of the button clicked
        String command = ae.getActionCommand();

        if (command == "Exit") {
            System.exit(0);
        } else if (command == "Play Again") {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "initial");
        }
    }
}
