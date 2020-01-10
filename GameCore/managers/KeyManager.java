//this class uses a key manager to handle all keyboard inputs and assigns them to the proper key binds.

package GameCore.managers; 
import java.awt.event.*;
  
public class KeyManager implements KeyListener{
  
private boolean [] keys;
public boolean up, down, left, right, up2, down2, left2, right2;
public boolean attackLight, attackHeavy, attackLight2, attackHeavy2; //key control variables
public boolean a1, a2, a3, a4, a5, a6;
public boolean reset;

public KeyManager (){
 keys = new boolean [256]; //creates a boolean for every key 
}

public void tick (){
  //key bindings may be changed to the user preferance
  up = keys[KeyEvent.VK_W]; 
  down = keys[KeyEvent.VK_S]; 
  left = keys[KeyEvent.VK_A]; //player move keys
  right = keys[KeyEvent.VK_D];  
  
  attackLight = keys[KeyEvent.VK_F];  
  attackHeavy = keys[KeyEvent.VK_C]; //player attack keys
  
  a1 = keys[KeyEvent.VK_Q];
  a2 = keys[KeyEvent.VK_E];//player ability keys
  a3 = keys[KeyEvent.VK_R];
  a4 = keys[KeyEvent.VK_SPACE];
  
  reset = keys[KeyEvent.VK_Y];
}

//calls the default key procedure for the specified button
@Override
public void keyPressed (KeyEvent e){
 keys [e.getKeyCode()] = true; 
}

@Override
public void keyReleased (KeyEvent e){
  keys [e.getKeyCode()] = false;
}

@Override
public void keyTyped (KeyEvent e) {
  
}

}
