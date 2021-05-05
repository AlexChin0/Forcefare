//this is the state where the player chooses which maps to play on. It incorporates buttons from the UIManager.

package States;
import GameCore.Handler;
import gfx.Assets;
import java.awt.*;
import managers.UIManager;
import ui.*;
import entities.*;

import java.awt.Graphics;
  
public class StageState extends State {
  
  private UIManager uiManager;
  private boolean set = false;

 public StageState(Handler handler) {
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
   g.drawString("Map Selection", 420, 100);
   uiManager.render(g);
 }

}

