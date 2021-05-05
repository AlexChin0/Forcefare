//This is an extension of the State class. It is displayed when the game ends and gives the option to either quit or replay the game.

package States;
import GameCore.Handler;
import gfx.Assets;
import managers.UIManager;
import ui.*;

import java.awt.Graphics;

public class EndState extends State {

  private boolean set = false;
  private UIManager uiManager;

 public EndState(final Handler handler) {
  super(handler);
     
  uiManager = handler.getMouseManager().getUIManager();//retreive the UI Manager
 }
 
 //initialize the buttons
 public void setButtons(){
   uiManager.addObject(new UIImageButton(350, 350, 160, 80, Assets.btn_restart, new ClickListener() {
     @Override
     public void onClick(){ 
     State.setState(handler.getGame().menuState);
     handler.getWorld().getEntityManager().getEntities().clear(); //restarts the game
     GameState.end = false;
     set = false;
     }}));
   
   uiManager.addObject(new UIImageButton(690, 350, 160, 80, Assets.btn_exit, new ClickListener() {
     @Override
     public void onClick(){  
       System.exit(1);//exits the game
     }})); 
 }

 @Override
 public void tick(){
   if(!set){
     handler.getMouseManager().pauseUIManager(false);
     setButtons();
     set = true;
   }
   uiManager.tick();
 }

 @Override
 public void render(Graphics g) {
   uiManager.render(g);
 }

}