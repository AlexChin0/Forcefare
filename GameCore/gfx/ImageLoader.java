//This class is the image loader class. It is a helper class which just loads png images from the project files, using the proper file path.
// This method is used in the assets class to load images. 

package GameCore.gfx;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

final public class ImageLoader {
  
public static BufferedImage loadImage(String path){
	
    try{ //try and catch statement, returns null to images that do not exist in the folder
        return ImageIO.read(ImageLoader.class.getResourceAsStream("/res" + path));
      } catch (IOException e){
        e.printStackTrace(); 
        System.exit(1);
      }
    return null;
}
}

