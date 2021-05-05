//This is the sprite sheet class. It is another helper class which allows png images to be cropped into separate sub images. 
// This is also used in the assets class.
package GameCore.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
  
private BufferedImage sheet; 

public SpriteSheet (String path) {
	sheet = ImageLoader.loadImage(path);
}

public BufferedImage crop (int x, int y, int width, int height) {
  return sheet.getSubimage (x, y, width, height);//method that sets smaller images from the spritesheet
}
  
}
