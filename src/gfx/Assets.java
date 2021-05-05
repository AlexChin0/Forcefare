//This is the assets class. It loads all of the png images required for the game before it begins, and assigns them to arrays and variables.
// this way the images are all loaded at once, during launch. 

package gfx;

import java.awt.image.*;


public class Assets {
  
  public static BufferedImage cliff1, cliff2, grass, path, gradSymbol;
  
  public static BufferedImage [] btn_start, btn_intro, btn_select, btn_exit, btn_restart, btn_coop, heals, charCards, hCrystal; //image arrays
  
  public static BufferedImage selectscreen, introscreen, blank, hud1, blueTab, redTab, dmgTab, healTab, tpTab;

  public static BufferedImage[] berN, berA, wC, maN, maA, maS, laser1, laser2;
  public static BufferedImage[] gguardN, gguardH, gguardUp, gguardDown, abN, abH, abUp, abDown, warN, warH, warUp, warDown, aurN, aurH, aurUp, aurDown; 
  public static BufferedImage[] sshieldN, sshieldH, sshieldUp, sshieldDown;
    
  public static void init () {
    
    SpriteSheet tileSheet = new SpriteSheet ("textures/background.png");
    SpriteSheet texs = new SpriteSheet ("textures/tex.png");
    SpriteSheet healing = new SpriteSheet ("textures/healing.png");
    SpriteSheet hcrystal = new SpriteSheet ("textures/Crystal.png");   
    SpriteSheet tabs = new SpriteSheet("textures/tabs.png");
    SpriteSheet screens = new SpriteSheet("textures/Screens.png");
    SpriteSheet symbols = new SpriteSheet("textures/symbols.png");
    
    hud1 = ImageLoader.loadImage("textures/HUD.png");
    
    SpriteSheet monsters = new SpriteSheet("ents/Monster Sheet.png");
    SpriteSheet lasers = new SpriteSheet("ents/Laser.png");
    SpriteSheet gunits = new SpriteSheet ("ents/gradunits.png");
    
    //units
    //glyphguard
    gguardN = new BufferedImage [3];
    gguardN[0] = gunits.crop(0, 0, 64, 64);
    gguardN[1] = gunits.crop(64, 0, 64, 64);
    gguardN[2] = gunits.crop(128, 0, 64, 64);
    
    gguardH = new BufferedImage [4];
    gguardH[0] = gunits.crop(192, 0, 64, 64);
    gguardH[1] = gunits.crop(0, 64, 64, 64);
    gguardH[2] = gunits.crop(64, 64, 64, 64);   
    gguardH[3] = gunits.crop(128, 64, 64, 64);   
    
    gguardDown = new BufferedImage [4];
    gguardDown[0] = gunits.crop(192, 64, 64, 64);
    gguardDown[1] = gunits.crop(0, 128, 64, 64);
    gguardDown[2] = gunits.crop(64, 128, 64, 64);   
    gguardDown[3] = gunits.crop(128, 128, 64, 64);   
    
    gguardUp = new BufferedImage [4];
    gguardUp[0] = gunits.crop(192, 128, 64, 64);
    gguardUp[1] = gunits.crop(0, 192, 64, 64);
    gguardUp[2] = gunits.crop(64, 192, 64, 64);   
    gguardUp[3] = gunits.crop(128, 192, 64, 64);   
    
    //absolver
    abN = new BufferedImage [3];
    abN[0] = gunits.crop(192, 192, 64, 64);
    abN[1] = gunits.crop(0, 256, 64, 64);
    abN[2] = gunits.crop(64, 256, 64, 64);
    
    abH = new BufferedImage [4];
    abH[0] = gunits.crop(128, 256, 64, 64);
    abH[1] = gunits.crop(192, 256, 64, 64);
    abH[2] = gunits.crop(0, 320, 64, 64);   
    abH[3] = gunits.crop(64, 320, 64, 64);   
    
    abDown = new BufferedImage [4];
    abDown[0] = gunits.crop(128, 320, 64, 64);
    abDown[1] = gunits.crop(192, 320, 64, 64);
    abDown[2] = gunits.crop(0, 384, 64, 64);   
    abDown[3] = gunits.crop(64, 384, 64, 64);   
    
    abUp = new BufferedImage [4];
    abUp[0] = gunits.crop(128, 384, 64, 64);
    abUp[1] = gunits.crop(192, 384, 64, 64);
    abUp[2] = gunits.crop(0, 448, 64, 64);   
    abUp[3] = gunits.crop(64, 448, 64, 64);   
    
    //warper
    warN = new BufferedImage [3];
    warN[0] = gunits.crop(128, 448, 64, 64);
    warN[1] = gunits.crop(192, 448, 64, 64);
    warN[2] = gunits.crop(0, 512, 64, 64);
    
    warH = new BufferedImage [4];
    warH[0] = gunits.crop(64, 512, 64, 64);
    warH[1] = gunits.crop(128, 512, 64, 64);
    warH[2] = gunits.crop(192, 512, 64, 64);   
    warH[3] = gunits.crop(0, 576, 64, 64);   
    
    warDown = new BufferedImage [4];
    warDown[0] = gunits.crop(64, 576, 64, 64);
    warDown[1] = gunits.crop(128, 576, 64, 64);
    warDown[2] = gunits.crop(192, 576, 64, 64);   
    warDown[3] = gunits.crop(0, 640, 64, 64);   
    
    warUp = new BufferedImage [4];
    warUp[0] = gunits.crop(64, 640, 64, 64);
    warUp[1] = gunits.crop(128, 640, 64, 64);
    warUp[2] = gunits.crop(192, 640, 64, 64);   
    warUp[3] = gunits.crop(0, 704, 64, 64);   
    
    //auror
    aurN = new BufferedImage [3];
    aurN[0] = gunits.crop(0, 768, 64, 64);
    aurN[1] = gunits.crop(64, 768, 64, 64);
    aurN[2] = gunits.crop(128, 768, 64, 64);
    
    aurH = new BufferedImage [4];
    aurH[0] = gunits.crop(192, 768, 64, 64);
    aurH[1] = gunits.crop(0, 832, 64, 64);
    aurH[2] = gunits.crop(64, 832, 64, 64);   
    aurH[3] = gunits.crop(128, 832, 64, 64);   
    
    aurDown = new BufferedImage [4];
    aurDown[0] = gunits.crop(192, 832, 64, 64);
    aurDown[1] = gunits.crop(0, 896, 64, 64);
    aurDown[2] = gunits.crop(64, 896, 64, 64);   
    aurDown[3] = gunits.crop(128, 896, 64, 64);   
    
    aurUp = new BufferedImage [4];
    aurUp[0] = gunits.crop(192, 896, 64, 64);
    aurUp[1] = gunits.crop(0, 960, 64, 64);
    aurUp[2] = gunits.crop(64, 960, 64, 64);   
    aurUp[3] = gunits.crop(128, 960, 64, 64);   
    
    //slipshield
    sshieldN = new BufferedImage [3];
    sshieldN[0] = gunits.crop(128, 1024, 64, 64);
    sshieldN[1] = gunits.crop(192, 1024, 64, 64);
    sshieldN[2] = gunits.crop(0, 1088, 64, 64);
    
    sshieldH = new BufferedImage [4];
    sshieldH[0] = gunits.crop(64, 1088, 64, 64);
    sshieldH[1] = gunits.crop(128, 1088, 64, 64);
    sshieldH[2] = gunits.crop(192, 1088, 64, 64);   
    sshieldH[3] = gunits.crop(0, 1152, 64, 64);   
    
    sshieldDown = new BufferedImage [4];
    sshieldDown[0] = gunits.crop(64, 1152, 64, 64);
    sshieldDown[1] = gunits.crop(128, 1152, 64, 64);
    sshieldDown[2] = gunits.crop(192, 1152, 64, 64);   
    sshieldDown[3] = gunits.crop(0, 1216, 64, 64);   
    
    sshieldUp = new BufferedImage [4];
    sshieldUp[0] = gunits.crop(64, 1216, 64, 64);
    sshieldUp[1] = gunits.crop(128, 1216, 64, 64);
    sshieldUp[2] = gunits.crop(192, 1216, 64, 64);   
    sshieldUp[3] = gunits.crop(0, 1280, 64, 64);   

    //tiles
    selectscreen = screens.crop(0, 0, 64, 64);
    
    cliff1 = tileSheet.crop(0, 0, 32, 32);
    cliff2 = tileSheet.crop(32, 0, 32, 32);
    grass = tileSheet.crop(0, 32, 32, 32);
    path = tileSheet.crop(32, 32, 32, 32);
    
    gradSymbol = symbols.crop(0, 64, 64, 64);
    
    blueTab = tabs.crop(0, 0, 64, 64);
    redTab = tabs.crop(64, 0, 64, 64);
    healTab = tabs.crop(0, 64, 64, 64);
    dmgTab = tabs.crop(64, 64, 64, 64);
    tpTab = tabs.crop(0, 128, 64, 64);
    
   btn_start = new BufferedImage [2];
   btn_start [0] = texs.crop (0, 0, 128, 64);
   btn_start [1] = texs.crop (128, 0, 128, 64);
         
   btn_intro = new BufferedImage [2];
   btn_intro [0] = texs.crop (0, 64, 128, 64);
   btn_intro [1] = texs.crop (128, 64, 128, 64);   //all image crops
   
   btn_select = new BufferedImage [2];
   btn_select [0] = texs.crop(0, 128, 128 ,64);
   btn_select [1] = texs.crop(128, 128, 128,64);
   
   btn_exit = new BufferedImage[2];
   btn_exit[0] = texs.crop(0, 192, 128, 64);
   btn_exit[1] = texs.crop(128, 192, 128, 64);
   
   btn_restart = new BufferedImage[2];
   btn_restart[0] = texs.crop(0, 251, 128, 64);
   btn_restart[1] = texs.crop(128, 251, 128, 64);
   
   btn_coop = new BufferedImage[2];
   btn_coop[0] = texs.crop(0, 320, 128, 64);
   btn_coop[1] = texs.crop(128, 320, 128, 64);
   
   heals = new BufferedImage[4];
   heals[0] = healing.crop(0, 0, 192, 192);
   heals[1] = healing.crop(192, 0, 192, 192);
   heals[2] = healing.crop(0, 192, 192, 192);
   heals[3] = healing.crop(192, 192, 192, 192);
   
   hCrystal = new BufferedImage[3];
   hCrystal[0] = hcrystal.crop(0, 0, 120, 240);
   hCrystal[1] = hcrystal.crop(120, 0, 120, 240);
   hCrystal[2] = hcrystal.crop(0, 240, 120, 240);
   
   //berring
   berN = new BufferedImage[4];
   berN [0] = monsters.crop(0, 0, 192, 192);
   berN [1] = monsters.crop(192, 0, 192, 192);
   berN [2] = monsters.crop(384, 0, 192, 192);
   berN [3] = monsters.crop(576, 0, 192, 192);
   
   berA = new BufferedImage[4];
   berA [0] = monsters.crop(768, 0, 192, 192);
   berA [1] = monsters.crop(0, 192, 192, 192);
   berA [2] = monsters.crop(192, 192, 192, 192);
   berA [3] = monsters.crop(384, 192, 192, 192);
   
   //wingcharager
   wC = new BufferedImage[3];
   wC[0] = monsters.crop(192, 384, 192, 192);
   wC[1] = monsters.crop(384, 384, 192, 192);
   wC[2] = monsters.crop(576, 384, 192, 192);
   
   //marcher 
   maN = new BufferedImage[5];
   maN[0] = monsters.crop(768, 384, 192, 192);
   maN[1] = monsters.crop(0, 576, 192, 192);
   maN[2] = monsters.crop(192, 576, 192, 192);
   maN[3] = monsters.crop(384, 576, 192, 192);
   maN[4] = monsters.crop(576, 576, 192, 192);
   
   maA = new BufferedImage[8];
   maA[0] = monsters.crop(768, 576, 192, 192);
   maA[1] = monsters.crop(0, 768, 192, 192);
   maA[2] = monsters.crop(192, 768, 192, 192);
   maA[3] = monsters.crop(384, 768, 192, 192);
   maA[4] = monsters.crop(576, 768, 192, 192);
   maA[5] = monsters.crop(768, 768, 192, 192);
   maA[6] = monsters.crop(0, 960, 192, 192);
   maA[7] = monsters.crop(192, 960, 192, 192);
   
   maS = new BufferedImage[2];
   maS[0] = monsters.crop(768, 576, 192, 192);
   maS[1] = monsters.crop(0, 768, 192, 192);
   
   laser1 = new BufferedImage[3];
   laser1 [0] = lasers.crop(0, 0, 32, 32);
   laser1 [1] = lasers.crop(32, 0, 32, 32);
   laser1 [2] = lasers.crop(0, 32, 32, 32);
   
   laser2 = new BufferedImage[3];
   laser2 [0] = lasers.crop(32, 32, 32, 32);
   laser2 [1] = lasers.crop(0, 64, 32, 32);
   laser2 [2] = lasers.crop(32, 64, 32, 32);
   
  }

}