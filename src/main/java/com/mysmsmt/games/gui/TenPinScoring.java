package com.mysmsmt.games.gui;

import com.mysmsmt.games.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TenPinScoring {
   private JTextField player1NameTextField;
   private JButton startGameButton;
   private JTextField ball1;
   private JTextField player2NameTextField;
   private JButton acceptScoresButton;
   private JTextPane textPane1;
   private JPanel panel1;
   private JTextField ball2;
   private JLabel spacer1;
   private JLabel spacer2;
   private JLabel currentPlayerName;
   private JLabel player1Score;
   private JLabel player2Score;
   private JLabel errorLabel;
   private JTextField ball3;

   private Game game;
   private static final String NEWLINE = System.getProperty("line.separator");

   public TenPinScoring() {

      startGameButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent actionEvent) {
            if (player1NameTextField.getText() != null && player2NameTextField.getText() != null) {
               game = new Game(new Player(player1NameTextField.getText(), new ScoringEngineImpl()),
                               new Player(player2NameTextField.getText(), new ScoringEngineImpl()));
               acceptScoresButton.setEnabled(true);
            }
         }
      });
      acceptScoresButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent actionEvent) {
            int ball1Int = parse(ball1.getText());
            int ball2Int = parse(ball2.getText());
            int ball3Int = parse(ball3.getText());
            try {
               game.accept(new Frame(ball1Int, ball2Int, ball3Int));
               currentPlayerName.setText(game.getCurrentPlayer().getName());
               player1Score.setText(Integer.toString(game.getPlayer1().getScoringEngine().totalScore()));
               player2Score.setText(Integer.toString(game.getPlayer2().getScoringEngine().totalScore()));
               String message = game.getCurrentPlayer().getName() +
                       ": " + ball1Int + "/" + ball2Int + " total: " +
                       game.getCurrentPlayer().getScoringEngine().totalScore() + NEWLINE;
               textPane1.setText(textPane1.getText() + message);
            }
            catch (ScoringException se) {
               errorLabel.setText(se.getMessage());
            }
         }

         private int parse(String s) {
            if (s == null || s.length() == 0) {
               return 0;
            }
            return Integer.parseInt(s);
         }
      });
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame("TenPinScoring");
      frame.setContentPane(new TenPinScoring().panel1);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setSize(500, 500);
      frame.setVisible(true);
   }
}
