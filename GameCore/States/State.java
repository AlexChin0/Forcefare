//This is an abstract class which is the base for all states, which are different menus/screens that are displayed at different stages of the game.

package GameCore.States; 
import GameCore.Handler;

import java.awt.Graphics;
  
public abstract class State {
  
  private static State currentState = null;
  protected int [] kingdoms = new int [2];
  protected int player = 1;
  
  public static void setState(State state){
   currentState = state; 
  }
  
  public static State getState(){
   return currentState; 
  }
  
  protected Handler handler;//creates a handler object which is available to all states
  
  public State (Handler handler){
   this.handler = handler; 
  }
  
  public abstract void tick();
  
  public abstract void render (Graphics g);
}
