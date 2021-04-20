package GUI;

import javax.swing.*;

import client.Player;

import java.awt.*;

public class ResultsScreen {

    private JFrame frame = new JFrame();
    private JTextField textField = new JTextField();
    private JTextArea scoreArea = new JTextArea();
            
    public ResultsScreen()   {
        
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(null);
        frame.setResizable(false);
        
        // Title Area
        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("Final Scores");
        
        // Score Area
        scoreArea.setBounds(0,50,650,100);
        scoreArea.setLineWrap(true);
        scoreArea.setWrapStyleWord(true);
        scoreArea.setBackground(new Color(25,25,25));
        scoreArea.setForeground(new Color(25,255,0));
        scoreArea.setBorder(BorderFactory.createBevelBorder(1));
        scoreArea.setEditable(false);


        frame.add(scoreArea);
        frame.add(textField);
        frame.setVisible(true);
    }
    
  

    public void showResults(java.util.List<Player> players) {
        
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < players.size(); ++i) {
            stringBuilder
                .append("(")
                .append(i + 1)
                .append(")")
                .append(" ")
                .append(players.get(i).getScore())
                .append(" - ")
                .append(players.get(i).getName());
        }
        
        scoreArea.setText(stringBuilder.toString());
    }

    
}
