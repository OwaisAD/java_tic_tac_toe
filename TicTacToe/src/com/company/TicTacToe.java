package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();

    JPanel button_panel = new JPanel();
    JButton resetButton = new JButton();
    JButton resetPlayerScoresButton = new JButton();

    JPanel resetButton_Panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    //declared as static since we still want the scores even when game is reset
    static int player1Wins;
    static int player2Wins;
    JLabel scoresField = new JLabel();

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 65));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800, 100);
        title_panel.add(textField);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Montserrat", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }


        resetPlayerScoresButton.addActionListener(this);
        resetPlayerScoresButton.setText("RESET SCORES");
        resetPlayerScoresButton.setFont(new Font("Montserrat", Font.BOLD, 16));
        resetPlayerScoresButton.setFocusable(false);

        resetButton.addActionListener(this);
        resetButton.setText("RESET GAME");
        resetButton.setFont(new Font("Montserrat", Font.BOLD, 16));
        resetButton.setFocusable(false);
        //resetButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // maybe more useful under the buttons panel

        scoresField.setBackground(new Color(25, 25, 25));
        scoresField.setForeground(new Color(25, 255, 0));
        scoresField.setFont(new Font("Ink Free", Font.BOLD, 30));
        scoresField.setHorizontalAlignment(JLabel.CENTER);
        scoresField.setText("<html>Player 1: " + player1Wins + "<br/>Player 2: " + player2Wins + "</html>");
        scoresField.setOpaque(true);

        resetButton_Panel.setLayout(new BorderLayout());
        resetButton_Panel.add(resetButton, BorderLayout.EAST);
        resetButton_Panel.add(resetPlayerScoresButton, BorderLayout.WEST);
        resetButton_Panel.add(scoresField,BorderLayout.CENTER);

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.CENTER);
        frame.add(resetButton_Panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if(e.getSource() == buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else {
                    buttons[i].setForeground(new Color(0, 0, 255));
                    buttons[i].setText("O");
                    player1_turn = true;
                    textField.setText("X turn");
                    check();
                }
            }
        }

        if(e.getSource() == resetButton) {
            frame.dispose();
            new TicTacToe();
        }

        if(e.getSource() == resetPlayerScoresButton) {
            player1Wins = 0;
            player2Wins = 0;
        }
    }

    // whose turn is it
    public void firstTurn() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(random.nextInt(2) == 0) {
            player1_turn = true;
            textField.setText("X turn");
       } else {
           player1_turn = false;
           textField.setText("O turn");
       }
    }

    //check win condition
    public void check() {
        // check X win conditions
        if(
                (buttons[0].getText() == "X") &&
                (buttons[1].getText() == "X") &&
                (buttons[2].getText() == "X")
                ) {
            xWins(0, 1, 2);
        }
        if(
                (buttons[3].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[5].getText() == "X")
        ) {
            xWins(3, 4, 5);
        }

        if(
                (buttons[6].getText() == "X") &&
                (buttons[7].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) {
            xWins(6, 7, 8);
        }

        if(
                (buttons[0].getText() == "X") &&
                (buttons[3].getText() == "X") &&
                (buttons[6].getText() == "X")
        ) {
            xWins(0, 3, 6);
        }

        if(
                (buttons[1].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[7].getText() == "X")
        ) {
            xWins(1, 4, 7);
        }
        if(
                (buttons[2].getText() == "X") &&
                (buttons[5].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) {
            xWins(2, 5, 8);
        }

        if(
                (buttons[0].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) {
            xWins(0, 4, 8);
        }

        if(
                (buttons[2].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[6].getText() == "X")
        ) {
            xWins(2, 4, 6);
        }

        //check Y win conditions
        if(
                (buttons[0].getText() == "O") &&
                (buttons[1].getText() == "O") &&
                (buttons[2].getText() == "O")
        ) {
            oWins(0, 1, 2);
        }
        if(
                (buttons[3].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[5].getText() == "O")
        ) {
            oWins(3, 4, 5);
        }

        if(
                (buttons[6].getText() == "O") &&
                        (buttons[7].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(6, 7, 8);
        }

        if(
                (buttons[0].getText() == "O") &&
                        (buttons[3].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(0, 3, 6);
        }

        if(
                (buttons[1].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[7].getText() == "O")
        ) {
            oWins(1, 4, 7);
        }
        if(
                (buttons[2].getText() == "O") &&
                        (buttons[5].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(2, 5, 8);
        }

        if(
                (buttons[0].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(0, 4, 8);
        }

        if(
                (buttons[2].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(2, 4, 6);
        }

        if(buttons[0].getText() != "" &&
                buttons[1].getText() != "" &&
                buttons[2].getText() != "" &&
                buttons[3].getText() != "" &&
                buttons[4].getText() != "" &&
                buttons[5].getText() != "" &&
                buttons[6].getText() != "" &&
                buttons[7].getText() != "" &&
                buttons[8].getText() != "") {
            for(int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textField.setText("It's a TIE");
        }

    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("X wins");
        player1Wins++;
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
        player2Wins++;
    }
}
