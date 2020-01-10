//This is an extension of the State class. It is displays the world when the game has begun. 

package GameCore.States;
import GameCore.Handler;

import GameCore.entities.*;
import GameCore.entities.units.grad.*;
import GameCore.worlds.World;
import GameCore.States.SelectionState;

import java.awt.Graphics;

public class GameState extends State{
  
  private World world; //world object
  
  public static boolean end = false;
  public static int turnNum = 1;
  
  public GameState(Handler handler, String stage){
    super (handler);
    world = new World (handler, stage);//loads the txt file for the world
    handler.setWorld (world);
  }
  
  @Override
  public void tick() {
    world.tick();
    if(end){
      State.setState(handler.getGame().endState); //keeps track of if the game is still running
    }
  }
  
  @Override
  public void render (Graphics g){
  world.render(g);
  }
}