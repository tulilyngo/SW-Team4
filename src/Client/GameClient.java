package Client;

import java.util.ArrayList;

import Client.CreateAccountView.CreateAccountControl;
import Client.CreateAccountView.CreateAccountData;
import Client.LoginView.LoginControl;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient
{
  private ArrayList<String> contacts;
  private LoginControl lc;
  private CreateAccountControl cc;
  //Need these 2 controls to "deliver" the response based on server's msg
  
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
      contacts = null;
      lc.loginSuccess();
    }
    //Server sending back an array list of contacts = successful log in
    else if (arg0 instanceof ArrayList)
    {
      contacts = (ArrayList<String>) arg0;
      lc.loginSuccess();
    }
    //Server sending back a CreateAccountData instance = successful creation
    else if (arg0 instanceof CreateAccountData)
    {
      cc.createSuccess();
    }
    //Server sending back a string = error
    else 
    {
      String error = (String)arg0;
      
      //Determine which panel the error msg belongs to
      if (error.equals("Cannot find log in info. Check username/password or create an account."))
      {
        lc.displayError(error);
      }
      else if (error.equals("Username already existed."))
      {
        cc.displayError(error);
      }
    }
  }
  
  public void connectionException (Throwable exception) 
  {
    System.out.println("Connection Exception Occurred");
    System.out.println(exception.getMessage());
    exception.printStackTrace();
  }
  
  public ArrayList<String> getContacts()
  {
    return contacts;
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
