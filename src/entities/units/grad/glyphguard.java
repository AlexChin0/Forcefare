package entities.units.grad;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import entities.*;
import GameCore.*;
import gfx.*;
import worlds.Tiles.Tile;

public class glyphguard extends LiveEntity{
  
  private Animation neutral, up, down, hori; //animations
  private Rectangle a1, a2, a3;
  private boolean shielding = false, shove = false, slam = false;
  
  //constructor
  public glyphguard(Handler handler, float x, float y, int width, int height, int playerNum) {
    super(handler, x, y, width, height, playerNum);
    
    neutral = new Animation(600, Assets.gguardN);  
    up = new Animation(100, Assets.gguardUp);
    down = new Animation(100, Assets.gguardDown);
    hori = new Animation(100, Assets.gguardH);
    
    health = 220;
    maxHealth = 220;
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
        shielding = true;
        aiming = true;
        click();
        shove = false;
        slam = false;
      }
      if(a2.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        shove = true;
        aiming = true;
        click();
        shielding = false;
        slam = false;
      }
      if(a3.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        slam = true;
        aiming = true;
        click();
        shielding = false;
        shove = false;
      }
      
      if(shielding){
        A1();
      }else if(shove){
        A2();
      }else if(slam){
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
        if(shove){
          drawRange(g, 1, 0, 1, 1);
        }else if(slam){
          drawRange(g, 1, 1, 1, 1);
        }else if(shielding){
          drawRange(g, 2, 1, 0, 2);
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
    
//    g.fillRect(a1.x, a1.y, a1.width, a1.height);
//    g.fillRect(a2.x, a2.y, a2.width, a2.height);
//    g.fillRect(a3.x, a3.y, a3.width, a3.height);
    
    if(hasMouse()){
      dir = 0;
      neutral.tick();
      if(handler.getMouseManager().isLeftPressed() && !aimCheck()){
        selectCheck();
      }
    }else{
     neutral.index = 0;
    }
  }
    
  @Override
  public void cancel(){
    System.out.println("Action cancelled");
   shove = false;
   shielding = false;
   slam = false;
  }
  
  private void A1(){
    if(aiming){
      Rectangle ar = new Rectangle ();
      ar.width = 192;
      ar.height = 64;          
      ar.x = (int)(x) - 64; 
      ar.y = (int)(y);
      
      if(handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        protect(40);
        click();
        aiming = false;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
          if (e.equals(this))
            continue;
          if(e.getBounds().intersects(ar) && e.playerNum == playerNum){
            e.protect(40);
          }
        }
      }
    }else{
      selected = false;
      shielding = false;
      dir = 0;
      endTurn(playerNum);
    }
  }
  
  private void A2(){
    if(aiming){
      if(dir == 1 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        specialCheck((int)(x), (int)(y-64), 64, 64, 0, 2, 1, 2);
        knockBack((int)(x), (int)(y-64), 64, 64, 40, 3);
        click();
        aiming = false;
      }else if(dir == 3 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        specialCheck((int)(x), (int)(y+64), 64, 64, 0, 2, 1, 2);
        knockBack((int)(x), (int)(y+64), 64, 64, 40, 3);
        click();
        aiming = false;
      }
    }else{
    endTurn(playerNum);
    selected = false;
    shove = false;
    dir = 0;
    }
  }
  
  private void A3(){
    if(aiming){
      if(dir == 1 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        rangedCheck((int)(x - 64), (int)(y-64), 192, 64, 20, 0, 0);
        click();
        aiming = false;
      }else if(dir == 3 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        rangedCheck((int)(x - 64), (int)(y+64), 192, 64, 20, 0, 0);
        click();
        aiming = false;
      }
    }else{
      endTurn(playerNum);
      selected = false;
      slam = false;
      dir = 0;
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
