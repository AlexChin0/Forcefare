package entities.units.grad;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import entities.*;
import GameCore.*;
import gfx.*;
import worlds.Tiles.Tile;

public class slipshield extends LiveEntity{
  
  private Animation neutral, up, down, hori; //animations
  private Rectangle a1, a2, a3;
  private boolean dive;
  private int hitDir = 0;
  
  //constructor
  public slipshield(Handler handler, float x, float y, int width, int height, int playerNum) {
    super(handler, x, y, width, height, playerNum);
    
    neutral = new Animation(600, Assets.sshieldN);  
    up = new Animation(100, Assets.sshieldUp);
    down = new Animation(100, Assets.sshieldDown);
    hori = new Animation(100, Assets.sshieldH);
    
    health = 140;
    maxHealth = 140;
    speed = 3;
    maxSpeed = 3;
  
    bounds = new Rectangle((int)(x), (int)(y), width, height);
    a1 = new Rectangle(448, 862, 64, 64);
    a2 = new Rectangle(641, 862, 64, 64);
    a3 = new Rectangle(832, 862, 64, 64);
  }
  
  @Override
  public void tick(){
    up.tick();
    down.tick();
    hori.tick();
    
    if(selected && !aiming && !moved){
      moveCheck();
    }
    if(selected){
      if(handler.getMouseManager().getMouseX() > (int)(x + width) && handler.getMouseManager().getMouseY() >= y && handler.getMouseManager().getMouseY() <= y + height){
        dir = 2;
      }else if(handler.getMouseManager().getMouseX() <= (int)(x) && handler.getMouseManager().getMouseY() >= y && handler.getMouseManager().getMouseY() <= y + height){
        dir = 4;
      }else if(handler.getMouseManager().getMouseY() > y && handler.getMouseManager().getMouseX() < (int)(x + width) && handler.getMouseManager().getMouseX() >= (int)(x)){
        dir = 3;
      }else if(handler.getMouseManager().getMouseY() < y + height && handler.getMouseManager().getMouseX() < (int)(x + width) && handler.getMouseManager().getMouseX() >= (int)(x)){
        dir = 1;
      } 
                
      if(a1.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        dive = true;
        aiming = true;
        click();
      }
//      if(a2.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
//        trip = true;
//        aiming = true;
//        click();
//        attack = false;
//        run = false;
//      }
//      if(a3.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
//        attack = true;
//        aiming = true;
//        click();
//        trip = false;
//        run = false;
//      }
//      
      if(dive){
        A1();
      }
//      if(trip){
//        A2();
//      }
//      if(attack){
//        A3();
//      }
    }
  }
  
  @Override
  public void render(Graphics g){    

//    g.setColor (Color.red);
//    g.fillRect ((int) (bounds.x), (int)(bounds.y), bounds.width, bounds.height);
    if(playerNum == 1){
       g.drawImage(Assets.blueTab,(int) (x),(int) (y) , width, height, null);    
    }else if(playerNum == 2){
      g.drawImage(Assets.redTab,(int) (x),(int) (y) , width, height, null); 
    }
    if(selected){
      if(!aiming && !moved){
        drawMoves(g);
      }else{
        if(dive){
          drawRange(g, 4, 3, 3, 4);//(Graphics g, int dirs, int xRange, int yRange, int type)
        }
        //else if(attack){
//          drawRange(g, 1, 0, 1, 1);
//        }else if(trip){
//          drawRange(g, 2, 1, 0, 1);
//        }
      }
      
      if(dir == 2){
        g.drawImage(getCurrentAnimationFrame(),(int) (x+width),(int) (y) , -width, height, null);
      }else{
        g.drawImage(getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null);
      }
    }else{
    g.drawImage(getCurrentAnimationFrame(),(int) (x),(int) (y) , width, height, null); 
    }
    
    if(bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
      neutral.tick();
      if(handler.getMouseManager().isLeftPressed() && !aimCheck()){
        selectCheck();
      }
    }else{
     neutral.index = 0;
    }
  }
  
//  public boolean checkCooldowns(int abNum){ //a method which returns true if the ability is usable
//    if (abilities[abNum] == cooldowns[abNum] && !silenced){
//      return true;
//    }else{
//      return false; 
//    }
//  }
  
  @Override
  public void cancel(){
    dive = false;
    System.out.println("Action cancelled");
  }
  
  private void A1(){
    if(aiming){
      int initX = (int)(x);
      int initY = (int)(y);
      int deltaX, deltaY;
      
      for(int i = 0; i < 15; i++){ //loads the tile to x and y coordinates
        for(int j = 0; j < 13; j++){
          handler.getWorld().getTile(i, j).setX(i * 64);
          handler.getWorld().getTile(i, j).setY(j * 64);
          if(handler.getWorld().getTile(i, j).getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
            
            deltaX = Math.abs(initX - handler.getWorld().getTile(i, j).getX());
            deltaY = Math.abs(initY - handler.getWorld().getTile(i, j).getY());
            
            if(deltaX + deltaY <= 192){        
              if(targetCheck(1, 40, 0, 0)){
                hitDir = dir;
                setX(handler.getWorld().getTile(i, j).getX());
                setY(handler.getWorld().getTile(i, j).getY());
              }
            }
            
          }
        }
      }   
    }else{
      aiming = false;
      adrenalRunes();
      dir = hitDir;
      knockBack((int)(x), (int)(y), 64, 64, 0, 1);
      endTurn(playerNum);
      selected = false;
      dive = false;
      dir = 0;
      return; 
    }
  }
  
  private void A2(){
    if(aiming){

    }else{
      selected = false;
      dir = 0; 
      endTurn(playerNum);
    }
  }
  
  private void A3(){
    if(aiming){

    }else{
      selected = false;
      dir = 0; 
      endTurn(playerNum);
    }
  }
  
  private void adrenalRunes(){ 
    Rectangle ar = new Rectangle ();
    ar.width = 192;
    ar.height = 192;          
    ar.x = (int)(x) - 64; 
    ar.y = (int)(y) - 64;
    
    protect(10);
    
    for (Entity e : handler.getWorld().getEntityManager().getEntities()){
      if (e.equals(this))
        continue;
      if(e.getBounds().intersects(ar) && e.playerNum == playerNum){
        e.protect(10);
      }
    } 
    
  }
  
  //displays the proper animation to the screen
  public BufferedImage getCurrentAnimationFrame(){
    
    if(dir == 1){
      return up.getCurrentFrame();
    }else if(dir == 3){
      return down.getCurrentFrame();      
    }else if(dir == 2 || dir == 4){
      return hori.getCurrentFrame();
    }
    else{
      return neutral.getCurrentFrame();
    }
  }
 
}