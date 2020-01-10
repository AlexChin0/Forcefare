//This is the display class. It is responsible for creating the JFrame, or run window. A canvas object is also created to 
// allow drawing objects to the screen.

package GameCore.gfx;

import javax.swing.JFrame;
import java.awt.*;
  
public class Display {

 private JFrame frame; //run window
 private Canvas canvas; // drawing canvas
 
 private String title;
 private int width, height;
 
 public Display(String title, int width, int height){ //class constructor
  this.title = title;
  this.width = width;
  this.height = height;
  
  createDisplay();
 }
 
 private void createDisplay(){
  frame = new JFrame(title);
  frame.setSize(width, height);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //creates the window with proper size and settings
  frame.setResizable(false);
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
  
  canvas = new Canvas();
  canvas.setPreferredSize(new Dimension(width, height));
  canvas.setMaximumSize(new Dimension(width, height)); //creates the canvas with size and settings
  canvas.setMinimumSize(new Dimension(width, height));
  canvas.setFocusable(false);
  
  frame.add(canvas);
  frame.pack();
 }
 
 //helper methods which give other classes access to the frame and canvas
 public Canvas getCanvas(){
  return canvas; 
 }
 
 public JFrame getFrame(){
   return frame;
 }
  
}
