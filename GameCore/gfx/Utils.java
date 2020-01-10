//The utility class. It loads a txt file full of integers and reads them using a buffered reader. This is used to load the world file. 

package GameCore.gfx;

import java.io.*;

final public class Utils {
  
  public static String loadFileAsString (String path) {
   StringBuilder builder = new StringBuilder ();
   
   try {
     BufferedReader br = new BufferedReader (new FileReader(path));
     String line;
     while ((line = br.readLine()) != null)
       builder.append (line + "\n"); //reads the entire file and stops reading when their are no more characters left.
     
     br.close ();
   }catch(IOException e){
     e.printStackTrace(); 
   }
   
   return builder.toString();
  }
  
  public static int parseInt (String number){
    try {
      return Integer.parseInt (number);
    }catch (NumberFormatException e){ //try and catch statement which stops when no more characters are available.
     e.printStackTrace();
     return 0;
    }
  }
  
}

