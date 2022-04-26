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
    private int numPlayers = 0;
    private int numQuestions = 0;

    GameData dataToSendToClient;
    public GameServer server;
    public JTextArea log;

    private int answersReceived = 0;

    public GameLogicControlServer(Database database) {
        this.database = database;
    }

    public void handleClientConnection(ConnectionToClient client)
    {
        if (numPlayers == 0) {
            numPlayers++;
            player1 = client;
        }
        else {
            numPlayers = 0;
            player2 = client;
        }

        if (player1 != null && player2 != null) {
            // Once there are 2 clients, stop accepting new clients
            server.stopListening();

            List<QuestionData> questions = database.getQuestions();
            numQuestions = questions.size();

            GameData gameData = new GameData(questions);
            dataToSendToClient = gameData;

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
        String[] data = msg.split(",");
        if (data[0].equals("client submitted answer")) {
            boolean isPlayer1 = Boolean.parseBoolean(data[1]);
            int score = Integer.parseInt(data[2]);
            int questionNum = Integer.parseInt(data[3]);

            answersReceived++;
            if (answersReceived == 2) {
                server.sendToAllClients(isPlayer1 + ";" + answersReceived + " Answers;" + score);
                answersReceived = 0;
                if (isPlayer1) {
                    dataToSendToClient.setPlayer1Score(score);
                } else {
                    dataToSendToClient.setPlayer2Score(score);
                }

                if (questionNum + 1 < numQuestions) {
                    dataToSendToClient.setCurrentQuestion(questionNum + 1);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    dataToSendToClient.setGameOver(true);
                    try {
                        server.listen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                server.sendToAllClients(dataToSendToClient);
            } else {
                if (isPlayer1) {
                    dataToSendToClient.setPlayer1Score(score);
                } else {
                    dataToSendToClient.setPlayer2Score(score);
                }
                server.sendToAllClients(isPlayer1 + ";" + answersReceived + " Answers;" + score);
            }
        }
    }

    private void resetState() {
        player1 = null;
        player2 = null;
        numPlayers = 0;
        numQuestions = 0;
        answersReceived = 0;
    }
}
