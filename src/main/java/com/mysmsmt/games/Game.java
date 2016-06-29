package com.mysmsmt.games;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {
   private Player player1;
   private Player player2;
   private boolean firstPlayerPlaying = false;

   public Game(Player player1, Player player2) {
      this.player1 = player1;
      this.player2 = player2;
   }

   public boolean isFirstPlayerPlaying() {
      return firstPlayerPlaying;
   }

   public void accept(Frame f) {
      firstPlayerPlaying = ! firstPlayerPlaying;
      getCurrentPlayer().getScoringEngine().accept(f);
   }

   public Player getCurrentPlayer() {
      return firstPlayerPlaying ? player1 : player2;
   }

   public Player getPlayer1() {
      return player1;
   }

   public Player getPlayer2() {
      return player2;
   }
}
