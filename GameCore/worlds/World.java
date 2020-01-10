//The world class is a class which draws the map and ticks the games when it is running. 
//it is the primary drawing class for the game.

package GameCore.worlds;

import GameCore.worlds.Tiles.*;
import GameCore.gfx.Utils;
import GameCore.Handler;
import GameCore.managers.EntityManager;
import java.awt.*;

public class World {
  
private Handler handler;  
private int width, height;
private int[][] tiles;

//instance of entity manager
private EntityManager entityManager;

//constructor takes in Handler class and string which indicates the world text file
public World(Handler handler, String path){
  this.handler = handler;
  entityManager = new EntityManager(handler);//initiates the entity manager class
  loadWorld(path); //loads the world
}

public void tick(){
  entityManager.tick();//ticks the entity manager
}

//draws the tiles to the screen
public void render(Graphics g){
  for(int y = 0;y < height;y++){ //loads the tile to x and y coordinates
    for(int x = 0;x < width;x++){
      getTile(x, y).setX(x * Tile.TILEWIDTH);
      getTile(x, y).setY(y * Tile.TILEHEIGHT);
      getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
//      if(getTile(x, y).getBounds().contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())){
//        g.setColor(Color.red);
//        System.out.println(getTile(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()).getX() + " " + getTile(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()).getY());
//        g.fillRect(getTile(x, y).getX(), getTile(x, y).getY(), Tile.TILEWIDTH, Tile.TILEHEIGHT);
//      }
    }
  }
  entityManager.render(g);
}

 public Tile getTile(int x, int y){
   if(x < 0 || y < 0 || x >= width || y >= height){
   return Tile.Grass; //draws map tiles
   }
   
  Tile t = Tile.tiles[tiles[x][y]];
  if(t == null)
   return Tile.Grass;//returns a default tile if one is missing
  return t;//returns a tile to be drawn to the screen
 }
 
 private void loadWorld(String path){
  String file = Utils.loadFileAsString(path); //reads the file from the utility class to draw tiles
  String[] tokens = file.split("\\s+");
  width = Utils.parseInt(tokens[0]);
  height = Utils.parseInt(tokens[1]);
  
  tiles = new int[width][height];
  for(int y = 0;y < height;y++){//double loop to draw tiles
    for(int x = 0;x < width;x++){
      tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
    }
  }
 }
 
 //getter for entity manager class
 public EntityManager getEntityManager(){
  return entityManager; 
 }
  
}
