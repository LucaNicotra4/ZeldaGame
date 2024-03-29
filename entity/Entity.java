package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
     
     GamePanel gp;
     public int worldX, worldY;
     public int speed;

     public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
     public String direction;

     public int spriteCounter = 0; //Used to count frames along with spriteNum
     public int spriteNum = 1; //Determines which interation of sprite to use in a certain direction

     public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
     public int solidAreaDefaultX, solidAreaDefaultY;
     public boolean collisionOn = false;

     //abstract class, always instantiate as a subclass
     //gp must always be passed
     public Entity(GamePanel gp){
          this.gp = gp;
     }
}
