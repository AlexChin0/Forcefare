package GameCore.entities.units.grad;

import java.awt.*;
import java.awt.image.BufferedImage;
import GameCore.entities.*;
import GameCore.*;
import GameCore.gfx.*;
import GameCore.worlds.Tiles.Tile;

public class warper extends LiveEntity{
  
  private Animation neutral, up, down, hori; //animations
  private Rectangle a1, a2, a3;
  private boolean bolt, warp, lance;
  private int boltBonus = 0;
  
  //constructor
  public warper(Handler handler, float x, float y, int width, int height, int playerNum) {
    super(handler, x, y, width, height, playerNum);
    
    neutral = new Animation(600, Assets.warN);  
    up = new Animation(100, Assets.warUp);
    down = new Animation(100, Assets.warDown);
    hori = new Animation(100, Assets.warH);
    
    health = 160;
    maxHealth = 160;
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
        bolt = true;
        aiming = true;
        click();
        warp = false;
        lance = false;
      }
      if(a2.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed() && boltBonus == 0){
        warp = true;
        aiming = true;
        click();
        bolt = false;
        lance = false;
      }
      if(a3.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()){
        lance = true;
        aiming = true;
        click();
        bolt = false;
        warp = false;
      }
      
      if(bolt){
        A1();
      }else if(warp){
        A2();
      }else if(lance){
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
        if(bolt){
          drawRange(g, 5, 0, 3, 1);
        }else if(warp){
          drawRange(g, 4, 3, 3, 3);
        }else if(lance){
          drawRange(g, 4, 2, 2, 4);
          
          int initX = (int)(x);
          int initY = (int)(y - 64);
          int deltaX, deltaY;
          
          for(int i = 0; i < 15; i++){
            for(int j = 0; j < 13; j++){
              
              deltaX = Math.abs(initX - handler.getWorld().getTile(i, j).getX());
              deltaY = Math.abs(initY - handler.getWorld().getTile(i, j).getY());   
              
              handler.getWorld().getTile(i, j).setX(i * Tile.TILEWIDTH);
              handler.getWorld().getTile(i, j).setY(j * Tile.TILEHEIGHT);
              if(handler.getWorld().getTile(i, j).getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && deltaX + deltaY <= 128){
                for(int a = -1; a <= 1; a++){
                  g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX() + a*64, handler.getWorld().getTile(i, j).getY(), null);
                }                
                for(int b = -1; b <= 1; b++){
                  g.drawImage(Assets.dmgTab, handler.getWorld().getTile(i, j).getX(), handler.getWorld().getTile(i, j).getY() + b*64, null);
                }
              }
            }
          }
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
   bolt = false;
   warp = false;
   lance = false;
  }
  
  private void A1(){
    if(aiming){
      if(dir == 1 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        rangedCheck((int)(x), (int)(y-192), 64, 64, 30 + boltBonus, 0);
        click();
        aiming = false;
      }else if(dir == 3 && handler.getMouseManager().isLeftPressed()&& !hasMouse()){
        rangedCheck((int)(x), (int)(y+192), 64, 64, 30 + boltBonus, 0);
        click();
        aiming = false;
      }
    }else{
    boltBonus = 0;
    endTurn(playerNum);
    selected = false;
    bolt = false;
    dir = 0;
    }
  }
  
  private void A2(){
    if(aiming){
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
              
              if(deltaX + deltaY <= 64 * 3){
                x = handler.getWorld().getTile(i, j).getX();
                y = handler.getWorld().getTile(i, j).getY();
                bounds.x = (int)(x);
                bounds.y = (int)(y);
                click(); 
                aiming = false;
              }
            }
            
          }
        }
      }
    }else{
      protect(20);
      boltBonus = 20;
      selected = false;
      warp = false;
      dir = 0;
      endTurn(playerNum);
    }
  }
  
  private void A3(){
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
              
              if(deltaX + deltaY <= 128){        
                targetCheck(1, 60, 1);
              }
            
          }
        }
      }
             
    }else{
      aiming = false;
      boltBonus = 0;
      endTurn(playerNum);
      selected = false;
      lance = false;
      dir = 0;
      return; 
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