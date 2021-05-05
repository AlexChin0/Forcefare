//This class creates an array list of player and monster in the game. It controls the number of entities that are rendered to the screen and
// is repsonsible for proper handling of all interactable objects in game.

package managers;
import GameCore.Handler;

import gfx.Assets;
import entities.*;

import java.awt.*;
import java.util.*;

public class EntityManager {

  private Handler handler;
  private ArrayList<Entity> entities; //render priority check


  public EntityManager(Handler handler){ //class constructor
    this.handler = handler;
    entities = new ArrayList<Entity>();
  }
  
  public void tick(){
    for(int i = 0; i < entities.size(); i++){ //ticks every player in the game
     Entity e = entities.get(i);
     e.tick();
          
     if(!e.isAlive()){ //removes entities who are dead
       entities.remove(e);
     }
    }
    
  }
  
  public void render(Graphics g){ //renders all entities to the screen
    g.drawImage(Assets.hud1, 0, 832, 960, 128, null);
    for(Entity e : entities){
  
      e.render(g);      

        if(e.getSelect()){
          g.setFont(new Font("Consolas", Font.PLAIN, 25)); 
          g.setColor(Color.red);
          g.drawString("Health: " + (int)(e.getHealth()), 30, 880);
          g.fillRect(210, 865, (int)(e.getHealth()), 10);
          g.setColor(Color.blue);
          g.drawString("Protection: " + (int)(e.getProt()), 30, 910);
          g.fillRect(250, 900, (int)(e.getProt()), 10);
        }
        
        if(e.getWasHurt()) {
            g.setFont(new Font("Consolas", Font.PLAIN, 25)); 
            g.setColor(Color.red);
            g.drawString("" + (int)(e.getDeltaHealth()), (int)e.getX(), (int)e.getY());
        }
    }
  }
  
  public void addEntity (Entity e){ //adds entities to the array list
    entities.add(e);
  }
 
  //getters and setters
  
  public Handler getHandler() {
    return handler;
  }
  
  public void setHandler(Handler handler) {
    this.handler = handler;
  }
  
  public ArrayList<Entity> getEntities() {
    return entities;
  }
  
  public void setEntities(ArrayList<Entity> entities) {
    this.entities = entities;
  }
  
}
