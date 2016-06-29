package com.mysmsmt.games;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 30/08/12
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
   private String name;
   private ScoringEngine scoringEngine;

   public Player(String name, ScoringEngine scoringEngine) {
      this.name = name;
      this.scoringEngine = scoringEngine;
   }

   public ScoringEngine getScoringEngine() {
      return scoringEngine;
   }

   public String getName() {
      return name;
   }
}
