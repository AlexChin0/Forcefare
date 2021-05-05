//The handler class is a hub for all of the programs instance classes. This class is used to give classes access to eachother.
package GameCore;

import managers.*;
import worlds.World;

public class Handler {
  
 private Game game;
 private World world;
 private EntityManager entityManager;
 
 //This uses the getter and setter system. When the methods are called they return the class specified.
 
 public Handler(Game game){
  this.game = game;
 }
 
public KeyManager getKeyManager(){
  return game.getKeyManager();
 }

public MouseManager getMouseManager(){
  return game.getMouseManager();
}
 
 public Game getGame() {
  return game;
 }
 
 public void setGame(Game game) {
   this.game = game;
 }
 
 public World getWorld() {
   return world;
 }

 public void setWorld(World world) {
  this.world = world;
 }
 
 public EntityManager getEntityManager() {
   return entityManager;
 }
 
 public void setEntityManager(EntityManager entityManager) {
   this.entityManager = entityManager;
 }
  
}
