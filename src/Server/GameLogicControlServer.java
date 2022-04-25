package Server;

import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.io.IOException;

public class GameLogicControlServer
{
  //Store the clients so we can send message to them individually
  ConnectionToClient player1;
  ConnectionToClient player2;

  String player1name = "";
  String player2name = "";

  // Store the player size so we can determine when there is two players
  private int players_size = 0;

  public GameServer server;
  public JTextArea log;

  public GameLogicControlServer()
  {
  }

  public void handleClientConnection(ConnectionToClient client)
  {
    if (player1 == null)
    {
      players_size++;
      player1 = client;
    }
    else
    {
      players_size++;
      player2 = client;
    }

    if (player1 != null && player2 != null)
    {
      server.stopListening();

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      try
      {
        player1.sendToClient("start");
        player2.sendToClient("start");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void handleDataFromClient(String msg)
  {
  }

  public void resetState()
  {
    player1 = null;
    player2 = null;
    players_size = 1;
  }
}
