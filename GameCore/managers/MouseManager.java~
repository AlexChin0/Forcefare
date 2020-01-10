//This class uses a mouse manager that handles mouse clicks and movement in a similar way to the key manager.

package GameCore.managers;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener {

 public boolean leftPressed, rightPressed, paused = true;//booleans for the mouse 
 private int mouseX, mouseY;//mouse position variables
 private UIManager uiManager;
 
 public MouseManager(){

 }
 
 public void setUIManager(UIManager uiManager){
  this.uiManager = uiManager;
 }
 
 //this method is called to pause or unpause this class. When it is paused, the mouse does not detect input.
 public void pauseUIManager(boolean setStat){
   if(setStat){
     paused = true;
   }else{
     paused = false;
   }
 }
 
 //getter for the UIManager class which exists in any instance of the mouse manager
 public UIManager getUIManager(){
  return uiManager; 
 }
  
 //methods which return the boolean states of the mouse
  public boolean isLeftPressed(){
   return leftPressed; 
  }
  
  public boolean isRightPressed(){
   return rightPressed; 
  }
  
 public int getMouseX(){
  return mouseX;
 }
 
 public int getMouseY(){
  return mouseY;
 }
  
 //detects input from the mouse buttons
  @Override
  public void mousePressed(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1) //left and right click buttons
      leftPressed = true;
    else if(e.getButton() == MouseEvent.BUTTON3)
      rightPressed = true;
  }
  
  //detects released mouse presses
  @Override
  public void mouseReleased(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1){
      leftPressed = false;
    }
    else if(e.getButton() == MouseEvent.BUTTON3){
      rightPressed = false;
    }
    
    if(uiManager != null || !paused){
      uiManager.onMouseRelease(e);
      }
  }

 @Override //keeps track of the mouse position on the screen
 public void mouseMoved(MouseEvent e) {
  mouseX = e.getX();
  mouseY = e.getY(); 
  
  if(uiManager != null || !paused){
    uiManager.onMouseMove(e);
 }
 }
 
 //default mouse methods
 @Override
 public void mouseDragged(MouseEvent e) {
  
 }

 @Override
 public void mouseClicked(MouseEvent e) {
  
 }

 @Override
 public void mouseEntered(MouseEvent e) {
  
 }

 @Override
 public void mouseExited(MouseEvent e) {
  
 }
 
}