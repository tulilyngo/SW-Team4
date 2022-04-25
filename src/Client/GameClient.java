package Client;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import Client.CreateAccountView.CreateAccountControl;
import Client.CreateAccountView.CreateAccountData;
import Client.LoginView.LoginControl;
import Client.QuestionView.QuestionControl;
import Client.QuestionView.QuestionPanel;
import Server.Player;
import ocsf.client.AbstractClient;

import javax.swing.*;

public class GameClient extends AbstractClient
{
  private LoginControl lc;
  private CreateAccountControl cc;
  private QuestionControl questionControl;

  public JPanel container;
  
  public GameClient()
  {
    super("localhost",8300);
  }

  @Override
  public void handleMessageFromServer(Object arg0)
  {
    //arg0 is only null when a null contacts arraylist is sent back 
    if (arg0 == null)
    {
    }
    else if (arg0 instanceof Integer)
    {
      lc.loginSuccess((Integer) arg0);
    }
    //Server sending back a CreateAccountData instance = successful creation
    else if (arg0 instanceof CreateAccountData)
    {
      cc.createSuccess();
    }
    //Server sending back a string = error
    else 
    {
      String msg = (String)arg0;
      
      //Determine which panel the error msg belongs to
      if (msg.equals("Cannot find log in info. Check username/password or create an account."))
      {
        lc.displayError(msg);
      }
      else if (msg.equals("Username already existed."))
      {
        cc.displayError(msg);
      }
      else if (msg.equals("start")) {
        //initialize the game
        CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "question");
      }
    }
  }
  
  public void connectionException (Throwable exception) 
  {
    System.out.println("Connection Exception Occurred");
    System.out.println(exception.getMessage());
    exception.printStackTrace();
  }
  
  public void setLoginControl(LoginControl lc)
  {
    this.lc = lc;
  }
  
  public void setCreateControl(CreateAccountControl cc)
  {
    this.cc = cc;
  }
}
