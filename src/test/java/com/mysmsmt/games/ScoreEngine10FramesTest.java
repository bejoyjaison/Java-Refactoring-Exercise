package com.mysmsmt.games;

import junit.framework.Assert;
import junit.framework.TestCase;


public class ScoreEngine10FramesTest extends TestCase {

   private ScoringEngine scoringEngine = new ScoringEngineImpl();

   /**
    * See EXAMPLE 1 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample1Scenario() {
      runOneFrame(9, 0, 9);
      runOneFrame(3, 5, 17);
      runOneFrame(6, 1, 24);
      runOneFrame(3, 6, 33);
      runOneFrame(8, 1, 42);
      runOneFrame(5, 3, 50);
      runOneFrame(2, 5, 57);
      runOneFrame(8, 0, 65);
      runOneFrame(7, 1, 73);
      runOneFrame(8, 1, 82);
   }

   /**
    * See EXAMPLE 2 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample2ScenarioSpares() {
      runOneGameExample2(new Frame(8, 0), 121);
   }

   private void runOneGameExample2(Frame f, int finalScore) {
      runOneFrame(9, 0, 9);
      runOneFrame(3, 7);
      runOneFrame(6, 1);
      runOneFrame(3, 7);
      runOneFrame(8, 1);
      runOneFrame(5, 5);
      runOneFrame(0, 10);
      runOneFrame(8, 0);
      runOneFrame(7, 3);
      runOneFrame(f);

      checkTotalFrameScore(1, 25);
      checkTotalFrameScore(2, 32);
      checkTotalFrameScore(3, 50);
      checkTotalFrameScore(4, 59);
      checkTotalFrameScore(5, 69);
      checkTotalFrameScore(6, 87);
      checkTotalFrameScore(7, 95);
      checkTotalFrameScore(8, 113);
      checkTotalFrameScore(9, finalScore);
   }

   /**
    * See EXAMPLE 2 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample2ScenarioStrikesEndWithSpare() {
      runOneGameExample2(new Frame(8, 2, 8), 131);
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioStrikes() {
      runOneGameExample3(new Frame(8, 0), 161, 169);
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioStrikesEndWithStrikes() {
      runOneGameExample3(new Frame(10, 10, 10), 163, 193);
   }

   /**
    * See EXAMPLE 3 on http://breaktimetenpin.com/tprules.php
    */
   public void testExample3ScenarioIllegal3BallFrame() {
      try {
         runOneGameExample3(new Frame(4, 5, 4), 999, 999);
         Assert.fail("Should not have got here");
      }
      catch (ScoringException se) {
         // ignore
      }
   }

   private void runOneGameExample3(Frame f, int lastButOnefinalScore, int finalScore) {
      runOneFrame(10, 0);
      runOneFrame(3, 7);
      runOneFrame(6, 1);
      runOneFrame(10, 0);
      runOneFrame(10, 0);
      runOneFrame(10, 0);
      runOneFrame(2, 8);
      runOneFrame(9, 0);
      runOneFrame(7, 3);
      runOneFrame(f);

      checkTotalFrameScore(0, 20);
      checkTotalFrameScore(1, 36);
      checkTotalFrameScore(2, 43);
      checkTotalFrameScore(3, 73);
      checkTotalFrameScore(4, 95);
      checkTotalFrameScore(5, 115);
      checkTotalFrameScore(6, 134);
      checkTotalFrameScore(7, 143);
      checkTotalFrameScore(8, lastButOnefinalScore);
      checkTotalFrameScore(9, finalScore);
   }

   private void checkTotalFrameScore(int frameIndex, int score) {
      Assert.assertEquals(score, scoringEngine.totalScore(frameIndex));
   }

   private void runOneFrame(int ball1, int ball2, int totalScore) {
      runOneFrame(ball1, ball2);
      Assert.assertEquals(totalScore, scoringEngine.totalScore());
   }

   private void runOneFrame(int ball1, int ball2) {
      Frame f = new Frame(ball1, ball2);
      runOneFrame(f);
   }

   private void runOneFrame(Frame f) {
      scoringEngine.accept(f);
   }
}
