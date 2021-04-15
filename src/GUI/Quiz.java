package GUI;

import javax.swing.*;

import org.apache.commons.lang3.tuple.Pair;

import client.Client;
import client.MultipleChoiceAnswer;
import client.Player;
import client.Question;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class Quiz implements ActionListener {

    char guess;
    char answer;
    int index;
    int correct_guesses=0;
    int total_questions=10;//TODO: if we actually care about this, we should get this info from server
    int result;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea scoreArea = new JTextArea();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
        
    private Timer timer;
    
    private Client client;

    public Quiz(Client client)   {
        this.client = client;
        
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
        textField.setText("Title");
        
        // Score Area
        scoreArea.setBounds(0,50,650,100);
        scoreArea.setLineWrap(true);
        scoreArea.setWrapStyleWord(true);
        scoreArea.setBackground(new Color(25,25,25));
        scoreArea.setForeground(new Color(25,255,0));
        scoreArea.setBorder(BorderFactory.createBevelBorder(1));
        scoreArea.setEditable(false);
        
        // Question Area
        textArea.setBounds(0,150,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        // Button Work
        buttonA.setBounds(0,200,100,100);
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");
        buttonB.setBounds(0,300,100,100);
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        buttonC.setBounds(0,400,100,100);
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");
        buttonD.setBounds(0,500,100,100);
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        // Answer Labels
        answer_labelA.setBounds(125,200,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setText("Testing label 1");

        answer_labelB.setBounds(125,300,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setText("Testing label 2");

        answer_labelC.setBounds(125,400,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setText("Testing label 3");

        answer_labelD.setBounds(125,500,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setText("Testing label 4");

        seconds_left.setBounds(535,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
//        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535,475,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Timer: ");

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25,255,0));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

//        frame.add(percentage);
//        frame.add(number_right);
        
        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(scoreArea);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

//        nextQuestion();
    }
    
    public void updateScores(java.util.List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder
                .append(player.getName())
                .append(": ")
                .append(player.getScore())
                .append(System.lineSeparator());
        }
        scoreArea.setText(stringBuilder.toString());
    }

    public void nextQuestion(Question question, Date deadline) {
        if (timer != null) {
            timer.stop();
        }
        
        if (index >= total_questions){
            results();
        }
        else {
            java.util.List<JLabel> answerLabels = new ArrayList<>();
            answerLabels.add(answer_labelA);
            answerLabels.add(answer_labelB);
            answerLabels.add(answer_labelC);
            answerLabels.add(answer_labelD);
            
            answerLabels.forEach(label -> label.setForeground(new Color(25, 255, 0)));
            
            textField.setText("Question " + question.getNumber());
            textArea.setText(question.getText());
            answer_labelA.setText(question.getOptions().get(0));
            answer_labelB.setText(question.getOptions().get(1));
            answer_labelC.setText(question.getOptions().get(2));
            answer_labelD.setText(question.getOptions().get(3));
            
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long remainingMillis = deadline.getTime() - new Date().getTime();
                    
                    seconds_left.setText(String.valueOf(remainingMillis/1000));
                }
            });
            timer.start();
        }

    }

//    public void displayAnswer() {
////        Timer.stop();
//        buttonA.setEnabled(false);
//        buttonB.setEnabled(false);
//        buttonC.setEnabled(false);
//        buttonD.setEnabled(false);
//
//        if (answers[index] != 'A'){
//            answer_labelA.setForeground(new Color(255,0,0));
//        }
//        if (answers[index] != 'B'){
//            answer_labelB.setForeground(new Color(255,0,0));
//        }
//        if (answers[index] != 'C'){
//            answer_labelC.setForeground(new Color(255,0,0));
//        }
//        if (answers[index] != 'D'){
//            answer_labelD.setForeground(new Color(255,0,0));
//        }
//
//        Timer pause = new Timer(2000, new ActionListener() {
//
//            //Flip colors back to green
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                answer_labelA.setForeground(new Color(25,255,0));
//                answer_labelB.setForeground(new Color(25,255,0));
//                answer_labelC.setForeground(new Color(25,255,0));
//                answer_labelD.setForeground(new Color(25,255,0));
//
//                answer = ' ';
////                seconds_left.setText(String.valueOf(seconds));
//
//                buttonA.setEnabled(true);
//                buttonB.setEnabled(true);
//                buttonC.setEnabled(true);
//                buttonD.setEnabled(true);
//
//                index++;
////                nextQuestion();
//            }
//        });
//
//        pause.setRepeats(false);
//        pause.start();
//    }

    public void results() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textField.setText("Results");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        frame.add(percentage);
        frame.add(number_right);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
       
      
        Pair<Boolean, String> correctness = null;
        if (e.getSource()==buttonA){
            correctness = client.answer(answer_labelA.getText());
        }

        if (e.getSource()==buttonB){
            correctness = client.answer(answer_labelB.getText());
        }

        if (e.getSource()==buttonC){
            correctness = client.answer(answer_labelC.getText());
        }

        if (e.getSource()==buttonD){
            correctness = client.answer(answer_labelD.getText());
        }
        
        boolean isCorrect = correctness.getLeft();
        String correctAnswer = correctness.getRight();
        
        java.util.List<JLabel> answerLabels = new ArrayList<>();
        answerLabels.add(answer_labelA);
        answerLabels.add(answer_labelB);
        answerLabels.add(answer_labelC);
        answerLabels.add(answer_labelD);
        
        answerLabels.stream().filter(label -> !label.getText().equals(correctAnswer)).forEach(label -> label.setForeground(new Color(255, 0, 0)));
        
        if (isCorrect) {
            correct_guesses++;
        }
    }
}
