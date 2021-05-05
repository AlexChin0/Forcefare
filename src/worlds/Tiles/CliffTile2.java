//A cliffside tile
package worlds.Tiles;

import gfx.Assets;

public class CliffTile2 extends Tile {
  
  public CliffTile2 (int id) {
   super (Assets.cliff2, id); 
  }
  
  @Override
  public boolean isSolid () {
    return true;
  }
  
}