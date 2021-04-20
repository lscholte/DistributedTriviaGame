package GUI;

import javax.swing.*;

import client.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

// Home Screen --- > Create Screen OR Joining Screen

public class LobbyScreen implements ActionListener {

    JFrame frame = new JFrame();

    // Home Screen Buttons
    JButton Create_Lobby_Button = new JButton();
    JButton Join_Lobby_Button = new JButton();

    // Text Areas
    JTextArea textArea = new JTextArea();
    JTextArea textArea2 = new JTextArea();

    // ID Input
    JTextField UU_ID_Input = new JTextField("Enter Lobby ID: ", 100);
    
    // Name input
    JPanel namePanel = new JPanel();
    JLabel nameLabel = new JLabel("Enter a name: ");
    JTextField nameInput = new JTextField("", 100);

    // Host Start Game Button
    JButton Start_Button = new JButton();

    // Player join Lobby Button
    JButton Join_Button = new JButton();

    private Client client;
    private UUID lobbyUuid;

    public LobbyScreen(Client client)   {
        this.client = client;
        
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(null);
        frame.setResizable(false);

        Create_Lobby_Button.setBounds(225,225,200,100);
        Create_Lobby_Button.setFocusable(false);
        Create_Lobby_Button.addActionListener(this);
        Create_Lobby_Button.setText("Create Lobby");
        Create_Lobby_Button.setEnabled(false);

        Join_Lobby_Button.setBounds(225,325,200,100);
        Join_Lobby_Button.setFocusable(false);
        Join_Lobby_Button.addActionListener(this);
        Join_Lobby_Button.setText("Join Lobby");
        Join_Lobby_Button.setEnabled(false);

        // Start Button (Initially invisible and disabled)
        Start_Button.setBounds(225,225,200,100);
        Start_Button.setFocusable(false);
        Start_Button.addActionListener(this);
        Start_Button.setText("START QUIZ");
        Start_Button.setVisible(false);
        Start_Button.setEnabled(false);

        // Join Button (Initially invisible and disabled)
        Join_Button.setBounds(225,225,200,100);
        Join_Button.setFocusable(false);
        Join_Button.addActionListener(this);
        Join_Button.setText("JOIN");
        Join_Button.setVisible(false);
        Join_Button.setEnabled(false);

        // Title
        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setText("Select an option: ");
        
        // Name input
        namePanel.setBounds(0, 100, 650, 50);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(nameLabel);
        namePanel.add(nameInput);
        namePanel.setBackground(new Color(25,25,25));
        namePanel.setForeground(new Color(25,255,0));
        namePanel.setBorder(BorderFactory.createBevelBorder(1));
        nameLabel.setBackground(new Color(25,25,25));
        nameLabel.setForeground(new Color(25,255,0));
        
        nameInput.setBackground(new Color(25,25,25));
        nameInput.setForeground(new Color(25,255,0));
        nameInput.setCaretColor(new Color(255,255,255));
        nameInput.setBorder(BorderFactory.createBevelBorder(1));
        nameInput.setEditable(true);
        nameInput.setVisible(true);
        nameInput.setEnabled(true);
        nameInput.addActionListener(event -> {
            Create_Lobby_Button.setEnabled(!nameInput.getText().isEmpty());
            Join_Lobby_Button.setEnabled(!nameInput.getText().isEmpty());
        });

        // Players joining status
        textArea2.setBounds(0,150,650,50);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setBackground(new Color(25,25,25));
        textArea2.setForeground(new Color(25,255,0));
        textArea2.setBorder(BorderFactory.createBevelBorder(1));
        textArea2.setEditable(false);
        textArea2.setText("");
        
        // Lobby Input
        UU_ID_Input.setBounds(0,150,650,50);
        UU_ID_Input.setBackground(new Color(25,25,25));
        UU_ID_Input.setForeground(new Color(25,255,0));
        UU_ID_Input.setCaretColor(new Color(255,255,255));
        UU_ID_Input.setBorder(BorderFactory.createBevelBorder(1));
        UU_ID_Input.setEditable(false);
        UU_ID_Input.setVisible(false);
        UU_ID_Input.setEnabled(false);


        frame.add(Create_Lobby_Button);
        frame.add(Join_Lobby_Button);
        frame.add(Start_Button);
        frame.add(textArea);
        frame.add(textArea2);
        frame.add(namePanel);
        frame.add(UU_ID_Input);
        frame.add(Join_Button);
        frame.setVisible(true);
    }

    public void CreateLobbyScreen() {
        UU_ID_Input.setEditable(false);
        UU_ID_Input.setEnabled(false);
        UU_ID_Input.setVisible(false);
        
        nameInput.setEditable(false);
        
        Create_Lobby_Button.setEnabled(false);
        Join_Lobby_Button.setEnabled(false);

        Create_Lobby_Button.setVisible(false);
        Join_Lobby_Button.setVisible(false);

        Start_Button.setVisible(true);
        Start_Button.setEnabled(true);

        textArea.setText("Your Lobby ID: " + lobbyUuid.toString());
    }

    public void JoinLobbyScreen() {
        nameInput.setEditable(false);

        Create_Lobby_Button.setEnabled(false);
        Join_Lobby_Button.setEnabled(false);

        Create_Lobby_Button.setVisible(false);
        Join_Lobby_Button.setVisible(false);

        textArea.setText("Enter Lobby ID below:");

        textArea2.setVisible(false);

        UU_ID_Input.setEditable(true);
        UU_ID_Input.setVisible(true);
        UU_ID_Input.setEnabled(true);
        UU_ID_Input.setText("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
        UU_ID_Input.addActionListener(event -> {
            try {
                lobbyUuid = UUID.fromString(UU_ID_Input.getText());
                Join_Button.setEnabled(true);
            }
            catch (IllegalArgumentException e) {
                Join_Button.setEnabled(false);
            }
        });
        
        Join_Button.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Create_Lobby_Button){
            lobbyUuid = client.createLobby();
            client.joinLobby(lobbyUuid, nameInput.getText());
            CreateLobbyScreen();
        }

        if (e.getSource()==Join_Lobby_Button){
            JoinLobbyScreen();
        }

        // To Join: Start Game Implementation
        if (e.getSource()==Start_Button){
            client.startGame(lobbyUuid);
        }

        // To Do: Join Game implementation
        if (e.getSource()==Join_Button){
            Join_Button.setText("JOINED");
            Join_Button.setEnabled(false);
            Join_Button.setEnabled(false);
            client.joinLobby(lobbyUuid, nameInput.getText());
            CreateLobbyScreen();
        }
    }
    
    public void setPlayers(java.util.List<String> players) {
        textArea2.setText(("Players: " + String.join(", ", players)));

        UU_ID_Input.setVisible(false);
        textArea2.setVisible(true);
    }
    
    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }
}