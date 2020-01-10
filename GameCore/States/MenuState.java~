//This is an extension of the State class. It is displayed when the game is launched, and gives the option to start a match, or go
// to the instruction screen

package GameCore.States;
import GameCore.Handler;
import GameCore.Launcher;
import GameCore.gfx.Assets;
import GameCore.managers.UIManager;
import GameCore.ui.*;

import java.awt.*;

public class MenuState extends State {

 private UIManager uiManager;
 private boolean set = false;
 
 public MenuState(final Handler handle) {
  super(handle);
  
  uiManager = new UIManager(handler);
  handler.getMouseManager().setUIManager(uiManager);//initialize the UI Manager
  handler.getMouseManager().pauseUIManager(false);
  
 }
 
 //initialize the clickable objects
 public void setButtons(){
   uiManager.addObject(new UIImageButton(300, 350, 160, 80, Assets.btn_start, new ClickListener() {
     @Override
     public void onClick(){ 
       State.setState(handler.getGame().selectionState);
       set = false;
     }}));
   
//   uiManager.addObject(new UIImageButton(500, 350, 160, 80, Assets.btn_coop, new ClickListener() {
//     @Override
//     public void onClick(){ 
//       State.setState(handler.getGame().selectionState);
//       coop = true;
//       set = false;
//     }}));
   
   uiManager.addObject(new UIImageButton(700, 350, 160, 80, Assets.btn_intro, new ClickListener() {
     @Override
     public void onClick(){  
       State.setState(handler.getGame().introState);
       set = false;
     }}));
 }

 @Override
 public void tick() {
   
   if(!set){
     handler.getMouseManager().pauseUIManager(false);
     setButtons();
     set = true;
   }
      
   uiManager.tick();
 }

 @Override
 public void render(Graphics g) {
   g.fillRect(0, 0, Launcher.runWidth, Launcher.runHeight);
   g.drawImage(Assets.mainscreen, 0, 0, null);
   uiManager.render(g);
 }

}