package Client;

import Server.Player;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContactPanel extends JPanel
{
  private JTextArea contactsArea;
  private JButton delete;
  private JButton add;
  private JButton logout;
  
  private GameClient client;

  public ContactPanel()
  {
    JLabel label = new JLabel("Contacts", JLabel.CENTER);
    JPanel labelPanel = new JPanel();
    labelPanel.add(label);

    //Create the area for user's contacts
    contactsArea = new JTextArea();
    contactsArea.setEditable(false);
    JScrollPane scroll = new JScrollPane(contactsArea);
    scroll.setPreferredSize(new Dimension(400,200));

    JPanel contactsPanel = new JPanel();
    contactsPanel.add(scroll);

    //Create the 3 buttons
    delete = new JButton("Delete Contact");
    add = new JButton("Add Contact");
    logout = new JButton("Log Out");

    JPanel buttonsTop = new JPanel();
    buttonsTop.add(delete);
    buttonsTop.add(add);

    JPanel buttonBottom = new JPanel();
    buttonBottom.add(logout);

    JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    buttonsPanel.add(buttonsTop);
    buttonsPanel.add(buttonBottom);

    //Arrange the components
    JPanel all = new JPanel();
    all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    all.add(labelPanel);
    all.add(Box.createVerticalStrut(10));
    all.add(contactsPanel);
    all.add(Box.createVerticalStrut(10));
    all.add(buttonsPanel);
    this.add(all);
  }

  //Need this method to get the user's contacts list if log in is successful
  public void displayContacts(Player user, ArrayList<String> contacts)
  {
    if (contacts != null)
    {
      user.setContacts(contacts);
      for (String contact : contacts)
      {
        contactsArea.append(contact + "\n");
      }
    }
  }
}
