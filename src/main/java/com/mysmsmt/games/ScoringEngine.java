package com.mysmsmt.games;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ScoringEngine {
   public void accept(Frame f);
   public int totalScore();
   public int totalScore(int frameIndex);
}
