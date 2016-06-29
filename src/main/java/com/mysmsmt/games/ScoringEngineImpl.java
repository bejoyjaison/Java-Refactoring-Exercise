package com.mysmsmt.games;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScoringEngineImpl implements ScoringEngine {
   private static final int NO_FRAMES_IN_A_GAME = 10;
   private List<Frame> frames = new ArrayList<Frame>();
   private int totalScore = 0;

   public void accept(Frame f) {
      if (frames.size() == NO_FRAMES_IN_A_GAME) {
         throw new ScoringException("Game already finished");
      }
      if (f.is3BallFrame()) {
         if (frames.size() != (NO_FRAMES_IN_A_GAME - 1)) {
            throw new ScoringException("Can ony have 3 balls in final frame");
         }
         else if (! f.isLegal3BallFrame()) {
            throw new ScoringException("Illegal 3 ball frame");
         }
      }
      frames.add(f);
      totalScore = recalcTotalScore();
   }

   private int recalcTotalScore() {
      return recalcTotalScore(frames.size());
   }

   private int recalcTotalScore(int frameMax) {
      int score = 0;
      int max = Math.min(frameMax, frames.size());
      for (int i=0; i<max; i++) {
         Frame thisFrame = frames.get(i);
         Frame nextFrame1 = nextFrame(i, 1);
         Frame nextFrame2 = nextFrame(i, 2);
         score += thisFrame.getTotalScore(nextFrame1, nextFrame2);
      }
      return score;
   }

   private Frame nextFrame(int index, int offset) {
      Frame nextFrame = null;
      if (index < (frames.size() - offset)) {
         nextFrame = frames.get(index + offset);
      }
      return nextFrame;
   }

   public int totalScore() {
      return totalScore;
   }

   public int totalScore(int frameIndex) {
      return recalcTotalScore(frameIndex + 1);
   }
}
