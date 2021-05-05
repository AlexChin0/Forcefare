//This is the UIManager class. It holds and handles an array of the buttons which are displayed in the various states. 
package managers;

import GameCore.Handler;
import ui.UIObject;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

 private Handler handler;
 private ArrayList<UIObject> objects;//array of class objects
 
 public UIManager(Handler handler){
  this.handler = handler;
  objects = new ArrayList<UIObject>();
 }
 
 public void tick(){
  for(UIObject o : objects)//ticks all the buttons in the array
   o.tick();
 }
 
 public void render(Graphics g){
  for(UIObject o : objects)
   o.render(g);//draw all buttons in the array
 }
 
 //calls each buttons onMouseMove method
 public void onMouseMove(MouseEvent e){
  for(UIObject o : objects)
   o.onMouseMove(e);
 }
 
  //calls each buttons onMouseRelease method
 public void onMouseRelease(MouseEvent e){
   for(UIObject o : objects){
       o.onMouseRelease(e);
       if(o.isHovering()){
         objects.clear();
         break;
       }
   }
 }
 
 //adds a button to the array
 public void addObject(UIObject o){
  objects.add(o);
 }
 
 //removes a button from the array
 public void removeObject(UIObject o){
  objects.remove(o);
 }
 
 //clears the array
 public void removeAll (){
  objects.clear(); 
 }

 //getters and setters
 public Handler getHandler() {
  return handler;
 }

 public void setHandler(Handler handler) {
  this.handler = handler;
 }

 public ArrayList<UIObject> getObjects() {
  return objects;
 }

 public void setObjects(ArrayList<UIObject> objects) {
  this.objects = objects;
 }
 
}