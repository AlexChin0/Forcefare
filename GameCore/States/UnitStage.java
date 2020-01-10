/**
 * Auto Generated Java Class.
 */
package GameCore.States;
import GameCore.Handler;
import GameCore.gfx.Assets;
import java.awt.*;
import GameCore.managers.UIManager;
import GameCore.ui.*;
import GameCore.entities.*;

import java.awt.Graphics;
  
public class UnitStage extends State {
  
  private UIManager uiManager;
  private boolean set = false;

 public UnitStage(Handler handler) {
  super(handler);
  
  uiManager = handler.getMouseManager().getUIManager();//set the UI Manager
  
 }
 
 //initializes the buttons
 public void setButtons(){
   uiManager.addObject(new UIImageButton(460, 480, 160, 80, Assets.btn_select, new ClickListener() {//These buttons initialize the game 
     @Override
     public void onClick(){ 
       //handler.getMouseManager().pauseUIManager(true);
       handler.getGame().gameInit("res/worlds/world1.txt");
       set = false;
     }})); 
   set = true;
 }

 @Override
 public void tick() {
   if(!set){
     setButtons();//set the buttons
   }
   
   uiManager.tick();
 }

 //draws the buttons and text to the screen
 @Override
 public void render(Graphics g) {
   g.setFont(new Font("Consolas", Font.PLAIN, 32)); 
   g.drawString("Choose Your Units", 420, 100);
   uiManager.render(g);
 }

}