package Client;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CorrectAnswerControl {
//Private data field for storing the container.
  private JPanel container;

//Constructor for the initial controller.
  public CorrectAnswerControl(JPanel container) {
    this.container = container;
  }

//Handle timer
  public void direct() {
    CardLayout cardLayout = (CardLayout) container.getLayout();
    cardLayout.show(container, "1");
    System.out.println("To Question Panel");
  }
}
