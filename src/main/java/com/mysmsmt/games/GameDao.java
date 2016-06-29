package com.mysmsmt.games;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: jerryshea
 * Date: 31/08/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GameDao {
   void save(Game g);
   Collection<Game> retrieveAll();
}
