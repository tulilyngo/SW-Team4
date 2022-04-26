package ServerComm;

import Database.Database;
import Database.GameData;
import Database.QuestionData;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class GameLogicControlServer {
    // Store the players so we can send message to them individually
    ConnectionToClient player1;
    ConnectionToClient player2;

    Database database;

    // Store the player size so we can determine when there are two players
    private int players_size = 0;

    public GameServer server;
    public JTextArea log;

    public GameLogicControlServer(Database database) {
        this.database = database;
    }

    public void handleClientConnection(ConnectionToClient client)
    {
        if (players_size == 0) {
            players_size++;
            player1 = client;
        }
        else {
            players_size++;
            player2 = client;
        }

        if (player1 != null && player2 != null) {
            // Once there are 2 clients, stop accepting new clients
            server.stopListening();

            List<QuestionData> questions = database.getQuestions();

            GameData gameData = new GameData(questions);

            try {
                gameData.setPlayer1(true);
                player1.sendToClient(gameData);

                gameData.setPlayer1(false);
                player2.sendToClient(gameData);
            } catch (IOException e) {
                log.append("Failed to send signal to start game to client\n");
            }
        }
    }

    public void handleDataFromClient(String msg) {
    }

    public void resetState() {
        player1 = null;
        player2 = null;
        players_size = 0;
    }
}
