package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Crate extends SuperObject{
     public OBJ_Crate(){
          name = "crate";
          collision = true;

          try{
               image = ImageIO.read(getClass().getResourceAsStream("/res/objects/crate.png"));
          }catch (IOException ioe){
               ioe.printStackTrace();
          }
     }
}
