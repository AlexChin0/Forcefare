// This is the animation class. It is a helper class which takes arrays of buffered images and turns them into frame, by frame motion.
// all in game animations are created from instances of this class.
package GameCore.gfx;

import java.awt.image.BufferedImage;

public class Animation {
  
  public int speed, index;
  private BufferedImage[] frames;
  public long lastTime, timer;
  private Boolean casted;
  
  public Animation (int speed, BufferedImage[] frames){ // class constructor
    this.speed = speed;
    this.frames = frames;
    index = 0;
    timer = 0;
    casted = false;
    lastTime = System.currentTimeMillis();
  }
  
  public void tick(){
    timer += System.currentTimeMillis() - lastTime;
    lastTime = System.currentTimeMillis(); // clock timer
    
      if(timer > speed){ // ticks the frames of animation
        index++;
        timer = 0;
        if (index >= frames.length){
          index = 0; //resets the frames to 0;
          casted = true;
        }else{
          casted = false;
        }
      }
  }
  
  public BufferedImage getCurrentFrame(){
   return frames[index]; // returns the buffered image of the current frame in the array
  }
  
  public int getFrameNum(){
   return index; 
  }
  
  public Boolean checkAnim(){
    if (casted){
     return true; //a method with checks whether or not the animation has ended
    }else
      return false;
  }
  
}

