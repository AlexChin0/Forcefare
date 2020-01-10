//This is an extension of the State class. It displays the instructions to the screen if the option is chosen and allows the game to load 
//afterwords.

package GameCore.States;
import GameCore.Handler;
import GameCore.managers.UIManager;
import GameCore.ui.*;

import GameCore.gfx.Assets;

import java.awt.*;

public class IntroState extends State {

 private Boolean set = false;
 private UIManager uiManager;

 public IntroState(final Handler handler) {
  super(handler);
  
    uiManager = handler.getMouseManager().getUIManager();
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
   g.drawImage(Assets.introscreen, 0, 0, null);
   uiManager.render(g);
 }
 
 public void setButtons(){
   uiManager.addObject(new UIImageButton(500, 640, 160, 80, Assets.btn_start, new ClickListener() {
     @Override
     public void onClick(){ 
       State.setState(handler.getGame().selectionState);
       set = false;
     }}));
   set = true;
 }

}