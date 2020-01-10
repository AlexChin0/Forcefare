//extends the UIObject class. This class takes in more specific parameters such as the buttons graphics and calls the onclick method
package GameCore.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

 private BufferedImage[] images;
 private ClickListener clicker;
 
 //contructor takes in the buttons dimensions and image array
 public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
  super(x, y, width, height);
  this.images = images;
  this.clicker = clicker;
 }

 @Override
 public void tick() {}

 @Override
 public void render(Graphics g) {
  if(hovering)
   g.drawImage(images[1], (int) x, (int) y, width, height, null);//draw the image when the mouse is in bounds
  else
   g.drawImage(images[0], (int) x, (int) y, width, height, null);//draw the button
 }

 @Override
 public void onClick() {
  clicker.onClick();//call the onclick method when the button is clicked
 }

}