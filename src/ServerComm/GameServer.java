package ServerComm;

import java.awt.*;
import javax.swing.*;

import java.io.IOException;
import java.sql.SQLException;

import Database.CreateAccountData;
import Database.Database;
import Database.LoginData;
import Database.GameData;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
    // Data fields for this chat server.
    private JTextArea log;
    private JLabel status;
    private Database database;
    private GameLogicControlServer gameLogicControlServer;
    private Integer numPlayers = 0;

    // Constructor for initializing the server with default settings.
    public GameServer() {
        super(12345);
        // Declare Database object
        try {
            database = new Database();
        } catch (IOException e) {
            log.append("Failed to connect to database: " + e.getMessage() + "\n");
        }
        gameLogicControlServer = new GameLogicControlServer(database);
        gameLogicControlServer.server = this;
    }

    public void setLog(JTextArea log)
    {
        this.log = log;
        gameLogicControlServer.log = this.log;
        log.append(log.getText());
    }

    public JTextArea getLog() {
        return log;
    }

    public void setStatus(JLabel status)
    {
        this.status = status;
    }

    public JLabel getStatus() {
        return status;
    }

    @Override
    public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
        // If client sends log in info to server, check username and password
        if (arg0 instanceof LoginData) {
            log.append("Log In info from Client " + arg1.getId() + "\n");

            LoginData loginData = (LoginData) arg0;
            Player player = new Player(loginData.getUsername(), loginData.getPassword());
            try {
                database.findUser(player);
            } catch (SQLException e) {
                log.append("Failed to find player " + loginData.getUsername() + " with the provided password\n");
            }

            // If user found, send current # players to client
            if (database.getFoundUser()) {
                log.append("Log in for client " + loginData.getUsername() + " successful\n");
                gameLogicControlServer.handleClientConnection(arg1);
                numPlayers++;
                try {
                    arg1.sendToClient(numPlayers);
                } catch (IOException e) {
                    log.append("Failed to send successful login message to client\n");
                }
            }
            // If can't find user, send error msg to client
            else {
                log.append("Cannot find client " + loginData.getUsername() + "\n");
                try {
                    arg1.sendToClient("Cannot find log in info. Check username/password or create an account.");
                } catch (IOException e) {
                    log.append("Failed to send failing login message to client\n");
                }
            }
        }
        // If we received CreateAccountData, create a new account.
        else if (arg0 instanceof CreateAccountData) {
            log.append("Create Account info from Client " + arg1.getId() + "\n");
            CreateAccountData createData = (CreateAccountData)arg0;

            Player player = new Player(createData.getUsername(), createData.getPassword());

            log.append("Create Account for client " + createData.getUsername() + " successful\n");
            player.setID();
            database.addUser(player);

            // If username found, send error msg to client
            if (database.getFoundUser()) {
                log.append("Username already existed\n");
                try {
                    arg1.sendToClient("Username already existed.");
                } catch (IOException e) {
                    log.append("Failed to send failing create account message to client\n");
                }
            }
            // If can't find username, send CreateAccountData instance to client
            else {
                try {
                    arg1.sendToClient(createData);
                } catch (IOException e) {
                    log.append("Failed to send successful create account message to client\n");
                }
            }
        }
        //
        else if (arg0 instanceof String) {
            gameLogicControlServer.handleDataFromClient((String) arg0);
        }
    }

    protected void listeningException(Throwable exception) {
        System.out.println("Listening Exception:" + exception);
        exception.printStackTrace();
        System.out.println(exception.getMessage());

        if (this.isListening()) {
            log.append("Server not Listening\n");
            status.setText("Not Connected");
            status.setForeground(Color.RED);
        }
    }

    protected void serverStarted() {
        log.append("Server Started\n");
        status.setText("Listening");
        status.setForeground(Color.GREEN);
    }

    protected void serverStopped() {
        //Pause for half a second to show message from serverClosed()
        //in case the method is called first
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        log.append("Server stopped accepting new clients - Press Listen to start accepting new clients\n");
        status.setText("Stopped");
        status.setForeground(Color.RED);
    }

    protected void serverClosed() {
        log.append("Server and all current clients are closed\n");
        status.setText("Closed");
        status.setForeground(Color.RED);
    }

    protected void clientConnected(ConnectionToClient client)
    {
        log.append("Client Connected\n");
    }
}