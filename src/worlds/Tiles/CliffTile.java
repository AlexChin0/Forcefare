//A cliffside tile
package worlds.Tiles;

import gfx.Assets;

public class CliffTile extends Tile {
  
  public CliffTile (int id) {
   super (Assets.cliff1, id); 
  }
  
  @Override
  public boolean isSolid () {
    return true;
  }
  
}