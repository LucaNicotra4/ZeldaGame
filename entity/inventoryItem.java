package entity;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class inventoryItem {
     public BufferedImage image;
     public boolean obtained;
     public boolean selected;

     public inventoryItem(String directory){
          obtained = true;
          selected = false;
          try{
               image = ImageIO.read(getClass().getResourceAsStream(directory));
          }catch(Exception e){
               System.out.println("Error loading inventory image");
          }
     }
}
