//This is just the main method. It opens the run window and starts the game.
package GameCore;

public class Launcher {
  
  public static int runWidth = 960, runHeight = 960;
  
  public static void main(String[] args){ 
    
   Game game = new Game("Forcefare", runWidth, runHeight); //launches the run window.
   game.start();
    
  }
 
}
