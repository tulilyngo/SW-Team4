package ClientInterface;

import ClientComm.CreateAccountControl;
import ClientComm.InitialControl;
import ClientComm.LoginControl;
import ServerComm.GameClient;
import ServerInterface.ServerGUI;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

class CreateAccountPanelTest {
    private static CreateAccountPanel panel;
    private CreateAccountControl cc;

    @BeforeClass
    public static void setUp() throws InterruptedException
    {
        ServerGUI s = new ServerGUI();
        Thread.sleep(1000);
        s.getListen().doClick(500);
        Thread.sleep(1000);
    }

    @Before
    public void setupBeforeTest() throws Exception
    {
        JFrame clientFrame = new JFrame();
        clientFrame.setTitle("Client");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        GameClient client = new GameClient(container, cardLayout);
        try
        {
            client.openConnection();
            //make a game panel

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InitialControl ic = new InitialControl(container);
        LoginControl lc = new LoginControl(container, client);
        cc = new CreateAccountControl(container, client);

        JPanel view1 = new InitialPanel(ic);
        JPanel view2 = new LoginPanel(lc);
        panel = new CreateAccountPanel(cc);

        container.add(view1, "1");
        container.add(view2, "2");
        container.add(panel, "3");

        cardLayout.show(container, "3");
        clientFrame.add(container, BorderLayout.CENTER);

        Thread.sleep(1000);
        panel.setUsernameField("testUser");
        Thread.sleep(1000);
        panel.setPasswordField("hello123");
        Thread.sleep(1000);
        panel.setPassword2nd("hello12");
        Thread.sleep(1000);
        panel.getSubmit().doClick(500);
    }

    @Test
    public void testSetError() throws InterruptedException
    {
        Thread.sleep(500);
        assertEquals("Correct error didn't show up", panel.getError(), "Password verification error.");
    }
}