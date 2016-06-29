package com.mysmsmt.games;

import junit.framework.Assert;
import junit.framework.TestCase;


public class ScoreEngineGameTest extends TestCase {

   private Game game = new Game(new Player("Jerry", new ScoringEngineImpl()),
                                new Player("Martin", new ScoringEngineImpl()));

   public void testNFrames() {
      runNFrames(14);
   }

   public void testMoreThan10Frames() {
      try {
         // 10 per player + 1
         runNFrames(21);
         Assert.fail("Should not have got here");
      }
      catch (ScoringException se) {
         // ignore
      }
   }

   private void runNFrames(int NO_OF_FRAMES) {
      boolean isFirstPlayer;
      for (int i=0; i<NO_OF_FRAMES; i++) {
         isFirstPlayer = ((i % 2) == 0);
         runOneFrame(3, 5, isFirstPlayer, i);
      }
   }

   private void runOneFrame(int ball1, int ball2, boolean isFirstPlayer, int index) {
      Frame f = new Frame(ball1, ball2);
      game.accept(f);
      Assert.assertEquals(isFirstPlayer, game.isFirstPlayerPlaying());
      int thisPlayersIndex = index / 2;
      Assert.assertEquals((thisPlayersIndex + 1) * (ball1 + ball2), game.getCurrentPlayer().getScoringEngine().totalScore());
   }
}
