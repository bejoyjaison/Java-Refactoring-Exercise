package com.mysmsmt.games;

import junit.framework.Assert;
import junit.framework.TestCase;


public class ScoreEngineTest extends TestCase {

   private ScoringEngine scoringEngine = new ScoringEngineImpl();

   private void runOneFrame(int ball1, int ball2) {
      Frame f = new Frame(ball1, ball2);
      scoringEngine.accept(f);
   }

   public void testOneFrameTwoBalls() {
      int ball1 = 8, ball2 = 1;
      runOneFrame(ball1, ball2);
      Assert.assertEquals(ball1 + ball2, scoringEngine.totalScore());
   }

   public void testNFramesTwoBalls() {
      runNFrames(4);
   }

   public void testIllegalNumberOfFramesTwoBalls() {
      try {
         runNFrames(11);
         Assert.fail("Should not have got here");
      }
      catch (ScoringException se) {
         // ignore
      }
   }

   private void runNFrames(int NO_OF_FRAMES) {
      for (int i=0; i<NO_OF_FRAMES; i++) {
         int ball1 = 5, ball2 = 4;
         runOneFrame(ball1, ball2);
         Assert.assertEquals((i + 1) * (ball1 + ball2), scoringEngine.totalScore());
      }
   }

   public void testOneFrameTwoIllegalBalls() {
      int ball1 = 3, ball2 = 8;
      try {
         Frame f = new Frame(ball1, ball2);
         Assert.fail("Should not have got here");
      }
      catch (ScoringException se) {
         // ignore
      }
   }

//   public void testOneFrameOneBall() {
//      int ball1 = 8;
//      Frame f = new Frame(ball1);
//      scoringEngine.accept(f);
//      Assert.assertEquals(ball1, scoringEngine.totalScore());
//      Assert.assertEquals(false, f.isComplete());
//   }

//   public void testOneFrameOneIllegalBall() {
//      int ball1 = 11;
//      Frame f = new Frame(ball1);
//      try {
//         scoringEngine.accept(f);
//      }
//      catch (ScoringException se) {
//         // ignore
//      }
//      Assert.fail("Should not have got here");
//   }
}
