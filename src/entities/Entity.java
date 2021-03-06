///This is the parent class for all entities in the game. It holds all methods responsible for making up the stats of the entity.
package entities;

import States.GameState;

import java.awt.*;
import GameCore.Handler;

public abstract class Entity {
  
  protected Handler handler;
  protected int width, height, dir = 0, effectTime = 0, effect = 0;
  protected boolean selected = false, aiming = false;
  protected float x, y, health, protection, maxHealth, deltaHealth, dmgFactor = 1.0f;
  public static final float DEFAULT_HEALTH = 100;//all default instance variables
  protected boolean alive = true;
  public boolean stunned = false, silenced = false;
  protected boolean wasHurt = false, wasHealed = false; 
  protected Rectangle bounds;
  protected boolean moved = false;
  public int playerNum;
  protected int speed, maxSpeed;
  
  protected static final int defaultHP = 500;
  public static final int defaultWidth = 64;
  public static final int defaultHeight = 64;
  
  public Entity(Handler handler, float x, float y, int width, int height) {
    this.handler = handler;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    health = DEFAULT_HEALTH;

  }

  //returns the collision box of the entity
  public Rectangle getCollisionBounds (float xOffset, float yOffset){
   return new Rectangle ((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height); 
  }
  
  //returns the collision box of the entity
  public Rectangle getBounds (){
    return new Rectangle (bounds.x,  bounds.y, bounds.width, bounds.height); 
  }
  
  //returns true when an entity collides with another
  public boolean checkEntityCollisions (float xOffset, float yOffset){
   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
    if (e.equals(this)){
     continue;
    }else if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.playerNum != 0)
      return true;
   }
   return false;
  }
  
  public void effectCheck(int effect, int effectTime){   // effect index: 1 = stun, 2 = slowed, 3 = silenced
    System.out.println(effect + " " + effectTime);
    
    if(effectTime > 0){
      if(effect == 1){
        speed = 0;
        stunned = true;
      }else if(effect == 2){
        speed = 1;
      }
    }else if(effectTime == 0){
      effectTime = 0;
      effect = 0;
      speed = maxSpeed;
      stunned = false;
    }
  }
  
  public boolean checkSpace(){
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this)){
        continue;
      }else if (!e.equals(this)){
        for(int i = 0; i < 15; i++){ //loads the tile to x and y coordinates
          for(int j = 0; j < 13; j++){
            if(handler.getWorld().getTile(i, j).getBounds().intersects(e.bounds)){
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  public boolean checkUnit(){
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this)){
        continue;
      }else if (!e.equals(this)){
        if(e.getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && e.playerNum == playerNum){
          return true;
        }
      }
    }
  return false;
  }

  //a targeting method who's origin point is at the mouse, type indicates damage or heal
  public boolean targetCheck(int type, int amt, int spread, int spreadType){
  for(int i = 0; i < 15; i++){ 
    for(int j = 0; j < 13; j++){
      handler.getWorld().getTile(i, j).setX(i * 64);
      handler.getWorld().getTile(i, j).setY(j * 64);
        if(handler.getWorld().getTile(i, j).getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
          int tileX = handler.getWorld().getTile(i, j).getX();
          int tileY = handler.getWorld().getTile(i, j).getY();
          if(type == 1){
            rangedCheck(tileX, tileY, 64, 64, amt, spread, spreadType);
          }else if(type == 2){
            healCheck(tileX, tileY, 64, 64, amt);
          }
          aiming = false;
          handler.getMouseManager().leftPressed = false;
          return true;
        }
    }
  }
  return false;
  }
  
  //a method which checks an attack at a range, where the origin point specified
  //if a spread type is specified, splash damage will be checked
 public int rangedCheck(int x, int y, int width, int height, int dmg, int spread, int spreadType){
     Rectangle ar = new Rectangle ();
     int count = 0;
     ar.width = width;
     ar.height = height;      
     
     ar.x = x; 
     ar.y = y;
     
     for (Entity e : handler.getWorld().getEntityManager().getEntities()){
       if (e.equals(this))
         continue;
       if(e.getBounds().intersects(ar) && e.playerNum != playerNum){
         e.hurt(dmg * dmgFactor); 
         if(spread == 1){
           e.spreadCheck((int)(x), (int)(y), 1, 1, dmg/2, spreadType);
         }
         count++;
         continue;
       }
     }
     return count;
   }
 
 //a method which checks splash damage from a specified point
 // type 1 = radial, type 2 = adjacent
 public int spreadCheck(int x, int y, int xRange, int yRange, int dmg, int spreadType){
   int count = 0;   
	   
   if (spreadType == 1) {
	   Rectangle ar = new Rectangle ();
	   ar.x = x; 
	   ar.y = y;
	   
	   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
	     if (e.equals(this)){
	       continue;
	     }
	     else if(Math.abs(e.getX() - x) <= 64 * xRange && Math.abs(e.getY() - y) <= 64 * yRange){
	       e.hurt(dmg * dmgFactor); 
	       count++;
	       continue;
	     }
	   }
   } else if (spreadType == 2) {
	   
   }
   
   return count;
 }
 
 //effect index 1. slowed 2. stun
 public int specialCheck(int x, int y, int width, int height, int amt, int type, int target, int time){
   Rectangle ar = new Rectangle ();
   int count = 0;
   ar.width = width;
   ar.height = height;      
   
   ar.x = x; 
   ar.y = y;
   
   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
     if (e.equals(this))
       continue;
     if(e.getBounds().intersects(ar)){
       if(target == 1){
         if(e.playerNum != playerNum){
           e.hurt(amt * dmgFactor);
           if(type == 1){
             e.slowed(time);
           }else if(type == 2){
             e.stunned(time);
           }
         }
       }else if(target == 2){

       }
       count++;
       continue;
     }
   }
   return count;
 }
 
 public int healCheck(int x, int y, int width, int height, int hp){
   Rectangle ar = new Rectangle ();
   int count = 0;
   ar.width = width;
   ar.height = height;      
   
   ar.x = x; 
   ar.y = y;
   
   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
     if (e.equals(this))
       continue;
     if(e.getBounds().intersects(ar) && e.playerNum == playerNum){
       e.heal(hp); 
       count++;
       continue;
     }
   }
   return count;
 }
 
 public int knockBack(int x, int y, int width, int height, int dmg, int knock){
   Rectangle ar = new Rectangle ();
   int count = 0;
   ar.width = width;
   ar.height = height;      
   
   ar.x = x; 
   ar.y = y;
   
   for (Entity e : handler.getWorld().getEntityManager().getEntities()){
     if (e.equals(this))
       continue;
     if(e.getBounds().intersects(ar) && e.playerNum != playerNum){
       e.hurt(dmg * dmgFactor); 
       if(dir == 1){
         e.setY(e.getY() - 64*knock);
         if(e.getY() <= 0){
           e.setY(0);
         }
       }else if(dir == 3){
         e.setY(e.getY() + 64*knock);
         if(e.getY() >= 832){
           e.setY(768);
         }
       }else if(dir == 2){
         e.setX(e.getX() + 64*knock);
         if(e.getX() >= 960){
           e.setX(832);
         }
       }else if(dir == 4){
         e.setX(e.getX() - 64*knock);
         if(e.getX() <= 0){
           e.setX(0);
         }
       }
       count++;
       continue;
     }
   }
   return count;
 }
  
  //abstract classes
  public abstract void tick();
  
  public abstract void render(Graphics g);
  
  public abstract void hurt(float amt);
  
  public abstract void heal(float amt);
  
  public abstract void protect(float amt);
  
  public abstract void slowed(int time);
  
  public abstract void stunned(int time);
  
  public abstract void silenced(int time);
  
  public void die(){
    alive = false;
  }
  
  //getters and setters
  public float getX() {
   return x;
  }

  public void setX(float x) {
   this.x = x;
   bounds.x = (int)(x);
  }

  public float getY() {
   return y; 
  }

  public void setY (float y) {
   this.y = y;
   bounds.y = (int)(y);
  }

  public int getWidth() {
   return width;
  }

  public void setWidth (int width) {
   this.width = width;
  }

  public int getHeight() {
   return height;
  }

  public void setHeight (int height) {
   this.height = height;
  }

  public float getHealth() {
   return health;
  }
  
  public void setHealth(float health) {
    this.health = health;
  }
  
  public float getProt(){
   return protection; 
  }
  
  public void setProt(float protection){
   this.protection = protection; 
  }
  
  public float getMaxHealth(){
   return maxHealth; 
  }
  
  public float getDeltaHealth(){
    return deltaHealth; 
  }

  public void setDeltaHealth (int deltaHealth) {
    this.deltaHealth = deltaHealth;
  }
  
  public boolean getWasHurt() {
    return wasHurt;
  }
  
  public void setWasHurt(boolean wasHurt) {
	this.wasHurt = wasHurt;
  }
  
  public boolean getWasHealed() {
	return wasHealed;
  }
  
  public void setWasHealed(boolean wasHealed) {
	this.wasHealed = wasHealed;
  }
  
  public boolean isAlive(){
    return alive;
  }
  
  public int getDir(){
    return 0;
  }
  
  public void setDir(int dir){
    this.dir = dir;
  }
  
  public boolean getSelect(){
   return selected; 
  }
  
  public boolean getAim(){
    return aiming; 
  }
}
