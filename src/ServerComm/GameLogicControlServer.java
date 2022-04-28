package ServerComm;

import Data.Database;
import Data.GameData;
import Data.QuestionData;
import Data.Player;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameLogicControlServer {
    // Store the players so we can send message to them individually
    ConnectionToClient player1Connection;
    ConnectionToClient player2Connection;

    Player player1;
    Player player2;

    Database database;

    // Store the player size so we can determine when there are two players
    private int numPlayers = 0;
    private int numQuestions = 0;

    GameData dataToSendToClient;
    public GameServer server;
    public JTextArea log;

    private int answersReceived = 0;

    public GameLogicControlServer(Database database) {
        this.database = database;
    }

    public void handleClientConnection(ConnectionToClient client, Player player)
    {
        if (numPlayers == 0) {
            numPlayers++;
            player1Connection = client;
            player1 = player;
        }
        else {
            numPlayers = 0;
            player2Connection = client;
            player2 = player;
        }

        if (player1Connection != null && player2Connection != null) {
            // Once there are 2 clients, stop accepting new clients
            server.stopListening();

            List<QuestionData> questions = database.getQuestions();
            numQuestions = questions.size();

            GameData gameData = new GameData(questions);
            gameData.setPlayers(Arrays.asList(player1, player2));
            dataToSendToClient = gameData;

            server.sendToAllClients(gameData);
        }
    }

    public void handleDataFromClient(String msg) {
        String[] data = msg.split(",");
        if (data[0].equals("client submitted answer")) {
            boolean isPlayer1 = Boolean.parseBoolean(data[1]);
            int score = Integer.parseInt(data[2]);
            int questionNum = Integer.parseInt(data[3]);

            answersReceived++;

            // Update player's score on GameData object that's used to sync data between players
            if (isPlayer1) {
                dataToSendToClient.getPlayers().get(0).setScore(score);
            } else {
                dataToSendToClient.getPlayers().get(1).setScore(score);
            }
            server.sendToAllClients(isPlayer1 + ";" + answersReceived + " Answers;" + score);

            // Check to see if game should move on to the next question
            if (answersReceived == 2) {
                answersReceived = 0;

                if (questionNum + 1 < numQuestions) {
                    dataToSendToClient.setCurrentQuestion(questionNum + 1);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    dataToSendToClient.setGameOver(true);
                    resetState();
                }
                server.sendToAllClients(dataToSendToClient);
            }
        }
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    private void resetState() {
        player1Connection = null;
        player2Connection = null;
        player1 = null;
        player2 = null;
        numPlayers = 0;
        numQuestions = 0;
        answersReceived = 0;

        try {
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
