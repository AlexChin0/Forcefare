//This is another child class of the entity class. It is for entities which can move or attack such as the monsters.
package entities;

import GameCore.*;
import worlds.Tiles.Tile;
import States.GameState;
import gfx.Assets;
import java.util.*;

import java.awt.*;

public abstract class LiveEntity extends Entity {
 
 protected float xMove, yMove;
  
//constructor
  public LiveEntity(Handler handler, float x, float y, int width, int height, int playerNum) { 
    super(handler, x , y, width, height);
    
    this.handler = handler;
    this.x = x;
    this.y = y;//instance variables
    this.width = width; 
    this.height = height;
    this.playerNum = playerNum;
    health = defaultHP;
    
    bounds = new Rectangle ((int)(x), (int)(y), width, height);
    
  }
  
  public void stunned(int time){
    effect = 1;
    stunned = true;
    effectTime = time;
  }
  
  public void slowed(int time){
    effect = 2;
    effectTime = time;
    return;
  }
  
  public void silenced(int time){
    
  }
  
  //inherited methods
  public void tick(){
    
  }
  
  public void render(Graphics g){

  }
  
  public void cancel(){
    
  }
  
  public void drawMoves(Graphics g){
    int initX = (int)(x);
    int initY = (int)(y);
    int deltaX, deltaY;
    
    for(int i = 0; i < 15; i++){ //loads the tile to x and y coordinates
      for(int j = 0; j < 13; j++){
        handler.getWorld().getTile(i, j).setX(i * Tile.TILEWIDTH);
        handler.getWorld().getTile(i, j).setY(j * Tile.TILEHEIGHT);
            
            deltaX = Math.abs(initX - handler.getWorld().getTile(i, j).getX());
            deltaY = Math.abs(initY - handler.getWorld().getTile(i, j).getY());
            
              if(deltaX + deltaY <= 64 * speed && !checkSpace()){
                if(playerNum == 1){
                g.drawImage(Assets.blueTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
                }else if(playerNum == 2){
                  g.drawImage(Assets.redTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
                }
              }
      }
    }
  }
  
  //directional types: 1 - verticals, 2 - horizontals, 3 - square radial, 4 - diamond radial, 5 - ranged
  //tile types: 1 - dmg, 2 - heal
  public void drawRange(Graphics g, int dirs, int xRange, int yRange, int type){
    int initX = (int)(x);
    int initY = (int)(y);
    int deltaX, deltaY;
    
    for(int i = 0; i < 15; i++){ //loads the tile to x and y coordinates
      for(int j = 0; j < 13; j++){
        handler.getWorld().getTile(i, j).setX(i * Tile.TILEWIDTH);
        handler.getWorld().getTile(i, j).setY(j * Tile.TILEHEIGHT);
        
        deltaX = Math.abs(initX - handler.getWorld().getTile(i, j).getX());
        deltaY = Math.abs(initY - handler.getWorld().getTile(i, j).getY());
        
        if(dirs == 1){//verticals
          if(deltaX <= 64 * xRange && deltaY <= 64 * yRange && deltaY != 0){
            if(type == 1){
              g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 2){
              g.drawImage(Assets.healTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }
          }
        }else if(dirs == 2){//horizontals
          if(deltaX <= 64 * xRange && deltaY <= 64 * yRange && deltaX != 0){
            if(type == 1){
              g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 2){
              g.drawImage(Assets.healTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }
          }
        }else if(dirs == 3){//square radials
          if(deltaX <= 64 * xRange && deltaY <= 64 * yRange && deltaX != 0 || deltaX <= 64 * xRange && deltaY <= 64 * yRange && deltaY != 0){
            if(type == 1){
              g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 2){
              g.drawImage(Assets.healTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }
          }
        }else if(dirs == 4){//diamond radial
          
          if(deltaX + deltaY <= 64 * xRange && !checkSpace()){
            if(type == 1){
              g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 2){
              g.drawImage(Assets.healTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 3){
              g.drawImage(Assets.tpTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
            }else if(type == 4){
              g.drawImage(Assets.redTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null); 
            }
          }
        }else if(dirs == 5){
          if(deltaX <= 64 * xRange && deltaY < 64 * yRange && deltaY != 0){
              g.drawImage(Assets.redTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
          }
          if(deltaX <= 64 * xRange && deltaY == 64 * yRange && deltaY != 0){
            g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY(), 64, 64, null);
          }
        }
      }
    }
    
  }

  //triggers collisions with solid tiles
  protected boolean collisionWithTile(int x, int y){
   return handler.getWorld().getTile(x, y).isSolid(); 
  }

public void selectCheck(){
  if(!checkTurn(playerNum) || stunned){
    click();
    return;
  }
  if(selected){
    if(moved){
      endTurn(playerNum);
    }else if(aiming){
      cancel();
    }
    selected = false;
    aiming = false;
    dir = 0;
    click();
  }else if(!selected && !hasMoved()){
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.equals(this)){
        continue;
      }else if(e.selected == true){
        e.dir = 0;
        e.selected = false;
        e.aiming = false;
      }
    }
    moved = false;
    selected = true;
    click();
  }
}

public boolean hasMouse(){
  if(bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
    return true;
  }else{
    return false; 
  }
}

  public void endTurn(int playerNum){
    resetMoves();
    if(playerNum == 1){
      GameState.turnNum = 2;
    }else if(playerNum == 2){
      GameState.turnNum = 1;
    }
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.effect > 0 && GameState.turnNum == e.playerNum){
        e.effectTime --;
        e.effectCheck(e.effect, e.effectTime);
      }
    }
  }
  
  public boolean aimCheck(){
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.aiming){
        return true;
      }
    }
    return false;
  }
  
  public boolean checkTurn(int playerNum){
    if(playerNum == GameState.turnNum){
      return true;
    }else{
      System.out.println("Not your turn.");
     return false; 
    }
  }
  
  public void resetMoves(){
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.playerNum == GameState.turnNum){
        e.moved = false;
      }
    }
  }
  
  public boolean hasMoved(){
    for(Entity e : handler.getWorld().getEntityManager().getEntities()){
      if(e.equals(this)){
        continue;
      }else if(e.moved == true && e.playerNum == playerNum){
        return true;
      }
    }
    return false;
  }

public void click(){
  handler.getMouseManager().leftPressed = false;
}

public boolean proc (int procChance){
  int rnJesus = (int)(Math.random() * 100) + 0;
  if (rnJesus <= procChance){
    return true;
  }else
    return false; 
}

public void moveCheck(){
  int initX = (int)(x);
  int initY = (int)(y);
  int deltaX, deltaY;
  
  for(int i = 0; i < 15; i++){ //loads the tile to x and y coordinates
    for(int j = 0; j < 13; j++){
      handler.getWorld().getTile(i, j).setX(i * Tile.TILEWIDTH);
      handler.getWorld().getTile(i, j).setY(j * Tile.TILEHEIGHT);
      if(handler.getWorld().getTile(i, j).getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed() && !checkSpace()){
        if(!bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
          
            deltaX = Math.abs(initX - handler.getWorld().getTile(i, j).getX());
            deltaY = Math.abs(initY - handler.getWorld().getTile(i, j).getY());
            
            if(deltaX + deltaY <= 64 * speed){
              x = handler.getWorld().getTile(i, j).getX();
              y = handler.getWorld().getTile(i, j).getY();
              bounds.x = (int)(x);
              bounds.y = (int)(y);
              click(); 
              moved = true;
            }
        }

      }
    }
  }
  
}
  
//inherited methods
  @Override
  public void hurt(float amt){
    float dmgRange = (int)((Math.random() * amt)) - amt/2; 
    wasHurt = true;
    deltaHealth = amt + dmgRange;

    if(protection > 0){
      protection -= (amt + dmgRange);
      if(protection <= 0){
        health += protection;
        protection = 0;
      }
    }else{
    health -= amt + dmgRange;
    }
    System.out.println(amt + dmgRange);
    if(health <= 0){
      alive = false;
      die();
    }
  }
  
  @Override
  public void heal(float amt){
    health+= amt;
    wasHealed = true;
    
    deltaHealth = amt;
    
    if(health >= maxHealth){
      health = maxHealth;
    }
    System.out.println(amt + " Healing recieved. " + health + " hp left");
  }
  
  @Override
  public void protect(float amt){
    protection+= amt;
    if(protection >= 100){
      protection = 100;
    } 
    System.out.println(amt + " Protection recieved. ");
  }
}
