package GameCore.entities.units.grad;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import GameCore.entities.*;
import GameCore.*;
import GameCore.gfx.*;
import GameCore.worlds.Tiles.Tile;

public class Auror extends LiveEntity{
  
  private Animation neutral, up, down, hori; //animations
  private Boolean heal = false, amplify = false, wave =false;
  private Rectangle a1, a2, a3;
  
  //constructor
  public Auror(Handler handler, float x, float y, int width, int height, int playerNum) {
    super(handler, x, y, width, height, playerNum);
    
    neutral = new Animation(600, Assets.aurN);  
    up = new Animation(100, Assets.aurUp);
    down = new Animation(100, Assets.aurDown);
    hori = new Animation(100, Assets.aurH);
    
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
        heal = true;
        aiming = true;
        click();
        wave = false;
        amplify = false;
      }
      if(a2.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        amplify = true;
        aiming = true;
        click();
        wave = false;
        heal = false;
      }
      if(a3.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        wave = true;
        aiming = true;
        click();
        heal = false;
        amplify = false;
      }
      
      if(heal){
        A1();
      }else if(amplify){
        A2();
      }else if(wave){
        A3();
      }
      
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
        if(heal){
          drawRange(g, 3, 2, 2, 2);
        }else if(amplify){
          drawRange(g, 3, 2, 2, 2);
        }else if(wave){
          drawRange(g, 3, 1, 1, 1);
        }
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
    System.out.println("Action cancelled");
  }
  
  private void A1(){
    Rectangle ar = new Rectangle ();
    ar.width = 256;
    ar.height = 256;          
    ar.x = (int)(x) - 128; 
    ar.y = (int)(y) - 128;
    
    if(aiming){
      if(ar.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
      targetCheck(2, 60, 0);
      }
    }else{
      endTurn(playerNum);
      click();
      selected = false;
      heal = false;
      dir = 0;
    }
  }
  
  private void A2(){
    Rectangle ar = new Rectangle ();
    ar.width = 256;
    ar.height = 256;          
    ar.x = (int)(x) - 128; 
    ar.y = (int)(y) - 128;
    
    if(aiming){
      if(handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        click();
        aiming = false;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
          if(e.getBounds().intersects(ar) && e.playerNum == playerNum){
            if(e.getProt() > 0){
            e.protect(e.getProt());
            }
          }
        }
      }
    }else{
      endTurn(playerNum);
      selected = false;
      amplify = false;
      dir = 0;
    }
  }
  
  private void A3(){
    if(aiming){
      if(handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        click();
        aiming = false;
        specialCheck((int)(x - 64), (int)(y-64), 192, 192, 0, 1, 1, 2);
        if(rangedCheck((int)(x - 64), (int)(y - 64), 192, 192, 20, 0) + healCheck((int)(x - 64), (int)(y - 64), 192, 192, 10) >= 3){
          healCheck((int)(x - 64), (int)(y - 64), 192, 192, 10);
          protect(40);
        }
      }
    }else{
      selected = false;
      wave = false;
      dir = 0;
      endTurn(playerNum);
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