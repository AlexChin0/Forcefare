//This is an extension of the State class. It is displayed when a match starts and allows the player to choose which character to play
package States;
import GameCore.Handler;
import gfx.Assets;
import gfx.Animation;
import managers.UIManager;
import ui.*;
import entities.units.grad.*;
import java.awt.*;

import java.awt.Graphics;

public class SelectionState extends State {
  
  private UIManager uiManager;//instance of the UI Manager class
  
  //list of idle animations for the screen
  private boolean set = false;
  
 public SelectionState(final Handler handler) {
  super(handler);
  
  uiManager = handler.getMouseManager().getUIManager();//retreive the UI Manager
  
 }
 
 //initializes the clickable objects
 public void setButtons(){
   uiManager.addObject(new UIImageButton(250, 260, 160, 80, Assets.btn_select, new ClickListener() {
     @Override
     public void onClick(){ 
       State.setState(handler.getGame().unitState);
       kingdoms[0] = 0;
       set = false;
     }})); 
   set = true;
 }

 @Override
 public void tick() {
   if(!set){
     setButtons();
   }

   uiManager.tick();
 }

 @Override
 public void render(Graphics g) {
   g.drawImage(Assets.selectscreen, 0, 0, 960, 960, null);
   
   g.setFont(new Font("Agency FB", Font.PLAIN, 42)); 
   g.drawString("Choose Your Kingdom", 400, 210);
      
   g.drawImage(Assets.gradSymbol, 260, 300, 128, 128, null);
   g.drawString("Gradith", 280, 450);
   
   uiManager.render(g);
}
}