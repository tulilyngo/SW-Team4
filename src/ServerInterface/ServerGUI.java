package ServerInterface;

import Database.Database;
import ServerComm.GameServer;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class ServerGUI extends JFrame {
    private JLabel status; //Initialized to “Not Connected”
    private JLabel logLabel;

    private String[] labels = {"Port #", "Timeout"};

    private JTextField[] textFields = new JTextField[labels.length];

    private JTextArea log;

    private JScrollPane scroll;

    private JButton listen;
    private JButton close;
    private JButton stop;
    private JButton quit;

    private GameServer server;

    public ServerGUI() {
        server = new GameServer();

        this.setTitle("ServerComm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //---- Panel for status ----
        //Create a status containing “Status: Not Connected”
        JLabel statusLb = new JLabel("Status: ");

        status = new JLabel("Not Connected");
        status.setForeground(Color.RED);
        server.setStatus(status);

        // Store the JLabel in a JPanel named “north” that implements FlowLayout as the Layout Manager
        JPanel north = new JPanel(new FlowLayout());
        north.add(statusLb);
        north.add(status);

        //---- Panel for info ----
        JPanel info = new JPanel(new FlowLayout());

        //--- Panels for each info
        int numLabels = labels.length;
        JPanel infoGrid = new JPanel(new GridLayout(numLabels,2,5,5));

        for (int i = 0; i < numLabels; i++) {
            // Label
            JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);

            // Text field
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(70,20));
            // TODO: only for quick testing, remove when done
            if (labels[i].equals("Port #")) {
                textFields[i].setText("8300");
            } else {
                textFields[i].setText("500");
            }

            infoGrid.add(label);
            infoGrid.add(textFields[i]);
        }

        // Add the grid to the info panel
        info.add(infoGrid);

        //---- Panel for server log ----
        JPanel serverLog = new JPanel(new FlowLayout());

        // A nested panel for all components
        JPanel logJp = new JPanel();
        logJp.setLayout(new BoxLayout(logJp, BoxLayout.Y_AXIS));

        // Put each label in a panel for alignment
        JPanel logPanel = new JPanel(new FlowLayout());
        logLabel = new JLabel("Server Log Below", SwingConstants.CENTER);
        logPanel.add(logLabel);
        logJp.add(logPanel);

        logJp.add(Box.createVerticalStrut(5));    //Extra space btwn components

        log = new JTextArea();
        log.setEditable(false);
        server.setLog(log);
        scroll = new JScrollPane(log);
        scroll.setPreferredSize(new Dimension(400,200));
        logJp.add(scroll);

        logJp.add(Box.createVerticalStrut(10));

        // Add the panel into serverLog panel
        serverLog.add(logJp);

        //---- Panel for server info ----
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(info);
        center.add(Box.createVerticalStrut(40));    //Add a space between the panel
        center.add(serverLog);

        //---- Panel for buttons ----
        // Create 3 JButtons
        listen = new JButton("Listen");
        close = new JButton("Close");
        stop = new JButton("Stop");
        quit = new JButton("Quit");

        // Store the JButtons in a JPanel named “south” that implements FlowLayout as the Layout Manager
        JPanel south = new JPanel(new FlowLayout());
        south.add(listen);
        south.add(close);
        south.add(stop);
        south.add(quit);

        //---- Panel to contain north, center, and south components ----
        JPanel all_panel = new JPanel();
        all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
        all_panel.add(north);
        all_panel.add(center);
        all_panel.add(south);

        //---- Main panel to be added to frame ----
        JPanel main = new JPanel();
        main.add(all_panel);

        this.add(main,BorderLayout.CENTER);

        // Display frame
        setSize(450,450);
        setVisible(true);

        //---- Buttons functionality ----
        listen.addActionListener(event -> {
            if (textFields[0].getText().isEmpty() || textFields[1].getText().isEmpty()) {
                log.append("Port Number/timeout not entered before pressing Listen\n");
            }
            else {
                //Get and set the input port # and timeout
                int port = Integer.parseInt(textFields[0].getText());
                int timeout = Integer.parseInt(textFields[1].getText());
                server.setPort(port);
                server.setTimeout(timeout);

                try {
                    server.listen();
                } catch (IOException e) {
                    log.append("Failed to find server listening on port " + port + ": " + e.getMessage() + "\n");
                }
            }
        });

        close.addActionListener(event -> {
            int port = Integer.parseInt(textFields[0].getText());
            if (!server.isListening()) {
                log.append("Server not currently started\n");
            }
            else
                try {
                    server.close();
                } catch (IOException e) {
                    log.append("Failed to start server on port " + port + ": " + e.getMessage() + "\n");
                }
        });

        stop.addActionListener(event -> {
            if (!server.isListening()) {
                log.append("Server not currently started\n");
            }
            else {
                server.stopListening();
            }
        });

        quit.addActionListener(event -> System.exit(0));
    }

    public JButton getListen() {
        return listen;
    }

    public static void main(String[] args)
    {
        new ServerGUI();
    }
}