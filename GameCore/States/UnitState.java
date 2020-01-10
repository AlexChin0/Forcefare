/**
 * Auto Generated Java Class.
 */
package GameCore.States;
import GameCore.Handler;
import GameCore.gfx.Assets;
import GameCore.entities.Entity;
import java.awt.*;
import GameCore.managers.UIManager;
import GameCore.ui.*;
import GameCore.entities.units.grad.*;
import java.util.stream.*;
import java.util.*;

import java.awt.Graphics;
  
public class UnitState extends State {
  
  private UIManager uiManager;
  private boolean set = false;
  private int[] p1Units = new int[5];
  private int[] p2Units = new int[5];
  private Rectangle add1, sub1;
  private Rectangle[] adds = new Rectangle[5];
  private Rectangle[] subs = new Rectangle[5];
  private Rectangle[] adds2 = new Rectangle[5];
  private Rectangle[] subs2 = new Rectangle[5];
  private int p1Total, p2Total;

 public UnitState(Handler handler) {
  super(handler);
  
  uiManager = handler.getMouseManager().getUIManager();//set the UI Manager
  
  adds[0] = new Rectangle(285, 195, 50, 50);
  subs[0] = new Rectangle(175, 195, 50, 50);
  
  adds[1] = new Rectangle(705, 195, 50, 50);
  subs[1] = new Rectangle(595, 195, 50, 50);
  
  adds[2] = new Rectangle(285, 335, 50, 50);
  subs[2] = new Rectangle(175, 335, 50, 50);
  
  adds[3] = new Rectangle(705, 335, 50, 50);
  subs[3] = new Rectangle(595, 335, 50, 50);
  
  adds[4] = new Rectangle(505, 335, 50, 50);
  subs[4] = new Rectangle(395, 335, 50, 50);
  
  adds2[0] = new Rectangle(285, 545, 50, 50);
  subs2[0] = new Rectangle(175, 545, 50, 50);
  
  adds2[1] = new Rectangle(705, 545, 50, 50);
  subs2[1] = new Rectangle(595, 545, 50, 50);
  
  adds2[2] = new Rectangle(285, 685, 50, 50);
  subs2[2] = new Rectangle(175, 685, 50, 50);
  
  adds2[3] = new Rectangle(705, 685, 50, 50);
  subs2[3] = new Rectangle(595, 685, 50, 50);
  
  adds2[4] = new Rectangle(505, 685, 50, 50);
  subs2[4] = new Rectangle(395, 685, 50, 50);
 }
 
 //initializes the buttons
 public void setButtons(){
   //gradith units
   if(kingdoms[0] == 0){

   }
   uiManager.addObject(new UIImageButton(430, 800, 160, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       handler.getGame().gameInit("res/worlds/world1.txt");
       
       int xLoc = 64;
       int yLoc = 0;
       
       if(kingdoms[0] == 0){
         for(int i = 0; i < 5; i++){
           for(int j = 0; j < p1Units[i]; j++){
             if(i == 0){
               handler.getWorld().getEntityManager().addEntity(new glyphguard(handler, 0, 0, 64, 64, 1));
             }else if(i == 1){
               handler.getWorld().getEntityManager().addEntity(new Absolver(handler, 0, 0, 64, 64, 1));
             }else if(i == 2){
               handler.getWorld().getEntityManager().addEntity(new Auror(handler, 0, 0, 64, 64, 1));
             }else if(i == 3){
               handler.getWorld().getEntityManager().addEntity(new warper(handler, 0, 0, 64, 64, 1));
             }else if(i == 4){
               handler.getWorld().getEntityManager().addEntity(new slipshield(handler, 0, 0, 64, 64, 1));
             }
           }
         }
         Collections.shuffle(handler.getWorld().getEntityManager().getEntities());
         for(Entity e : handler.getWorld().getEntityManager().getEntities()){
           if(e.playerNum == 1){
             e.setX(xLoc);
             e.getBounds().x = (int)(e.getX());
             e.setY(yLoc);
             e.getBounds().y = (int)(e.getY());
             xLoc += 192;
             if(xLoc >= 896){
               xLoc = 256;
               yLoc += 64;
             }
           }
         }
         kingdoms[0] = 1;
       }
       
       if(kingdoms[0] == 1){
         
         yLoc = 768; 
         xLoc = 64;
                
         for(int i = 0; i < 5; i++){
           for(int j = 0; j < p2Units[i]; j++){
             if(i == 0){
               handler.getWorld().getEntityManager().addEntity(new glyphguard(handler, 0, 0, 64, 64, 2));
             }else if(i == 1){
               handler.getWorld().getEntityManager().addEntity(new Absolver(handler, 0, 0, 64, 64, 2));
             }else if(i == 2){
               handler.getWorld().getEntityManager().addEntity(new Auror(handler, 0, 0, 64, 64, 2));
             }else if(i == 3){
               handler.getWorld().getEntityManager().addEntity(new warper(handler, 0, 0, 64, 64, 2));
             }else if(i == 4){
               handler.getWorld().getEntityManager().addEntity(new slipshield(handler, 0, 0, 64, 64, 2));
             }
           }
         }
         Collections.shuffle(handler.getWorld().getEntityManager().getEntities());
         for(Entity e : handler.getWorld().getEntityManager().getEntities()){
           if(e.playerNum == 2){
             e.setX(xLoc);
             e.getBounds().x = (int)(e.getX());
             e.setY(yLoc);
             e.getBounds().y = (int)(e.getY());
             xLoc += 192;
             if(xLoc >= 896){
               xLoc = 256;
               yLoc -= 64;
             }
           }
         }
       }
       set = false;
     }})); 
   set = true;
 }

 @Override
 public void tick() {
   if(!set){
     setButtons();//set the buttons
   }
   
   for(int i = 0; i < 5; i++){
     if(adds[i].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()&& p1Units[i] < 4 && p1Total < 8){
       p1Units[i]++;
       p1Total++;
       handler.getMouseManager().leftPressed = false;
     }
   }
   for(int i = 0; i < 5; i++){
     if(subs[i].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed() && p1Units[i] > 0){
       p1Units[i]--;
       p1Total--;
       handler.getMouseManager().leftPressed = false;
     }
   }
   
   for(int i = 0; i < 5; i++){
     if(adds2[i].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed()&& p2Units[i] < 4 && p2Total < 8){
       p2Units[i]++;
       p2Total++;
       handler.getMouseManager().leftPressed = false;
     }
   }
   for(int i = 0; i < 5; i++){
     if(subs2[i].contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()) && handler.getMouseManager().isLeftPressed() && p2Units[i] > 0){
       p2Units[i]--;
       p2Total--;
       handler.getMouseManager().leftPressed = false;
     }
   }
   
   uiManager.tick();
 }

 //draws the buttons and text to the screen
 @Override
 public void render(Graphics g) {
   g.setFont(new Font("Consolas", Font.PLAIN, 32)); 
   g.drawString("Choose Your Units", 330, 100);
   
   g.drawRect(subs[4].x, subs[4].y, 50, 50);
   g.drawRect(adds[4].x, adds[4].y, 50, 50);
   
   g.drawString("-  " + p1Units[0] + "  +", 190, 230);
   g.drawString("-  " + p1Units[1] + "  +", 610, 230);
   g.drawString("-  " + p1Units[2] + "  +", 190, 370);
   g.drawString("-  " + p1Units[3] + "  +", 610, 370);
   g.drawString("-  " + p1Units[4] + "  +", 410, 370);
   
   g.drawString("-  " + p2Units[0] + "  +", 190, 580);
   g.drawString("-  " + p2Units[1] + "  +", 610, 580);
   g.drawString("-  " + p2Units[2] + "  +", 190, 720);
   g.drawString("-  " + p2Units[3] + "  +", 610, 720);
   g.drawString("-  " + p2Units[4] + "  +", 410, 720);

   uiManager.render(g);
 }

}