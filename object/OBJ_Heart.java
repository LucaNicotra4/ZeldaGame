package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;

public class OBJ_Heart extends SuperObject{
     GamePanel gp;
     public OBJ_Heart(GamePanel gp){
          this.gp = gp;
          name = "Heart";
          try{
               image = ImageIO.read(getClass().getResourceAsStream("res/objects/fullHeart"));
               image2 = ImageIO.read(getClass().getResourceAsStream("res/objects/fullHeart"));
               image3 = ImageIO.read(getClass().getResourceAsStream("res/objects/fullHeart"));
               image4 = ImageIO.read(getClass().getResourceAsStream("res/objects/fullHeart"));
               image5 = ImageIO.read(getClass().getResourceAsStream("res/objects/fullHeart"));

          }catch(IOException e){
               e.printStackTrace();
          }
     }
}