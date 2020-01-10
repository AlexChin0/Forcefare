//This is the class which is the base of all tiles which are loaded to the screen. It uses 2 arrays of tile IDs to load the necessary tile classes which make up the tiles of the map in game.

package GameCore.worlds.Tiles;

import java.awt.image.*;
import java.awt.*;

public class Tile {
  
  //static variables
  public static Tile[] tiles = new Tile [195];
  public static Tile CliffTile2 = new CliffTile2(1);
  public static Tile CliffTile = new CliffTile (2);
  public static Tile Grass = new Grass(3);
  public static Tile Path = new Path (4); 
 
 public static final int TILEWIDTH = 64, TILEHEIGHT = 64;//dimensions of the tile
 
 protected BufferedImage texture;
 protected final int id;
 protected Rectangle bounds;
 
 public Tile(BufferedImage texture, int id){
  this.texture = texture;
  this.id = id;
  bounds = new Rectangle(0, 0, TILEWIDTH, TILEHEIGHT);
  
  tiles[id] = this;//array of tiles
 }
 
 public void tick(){
   
 }
 
 //method which draws tiles 
 public void render(Graphics g, int x, int y){
  g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
 }
 
 public boolean isSolid(){
  return false;//A method that indicates a tile that cannot be walked on
 }
 
 public int getId(){
  return id;//a method which indicates the type of tile
 }
 
 public void setX(int x){
   bounds.x = x;
 }
 
 public void setY(int y){
   bounds.y = y;
 }
 
 public int getX(){
   return bounds.x;
 }
 
 public int getY(){
   return bounds.y;
 }
 
 public Rectangle getBounds(){
 return bounds;
 }
}