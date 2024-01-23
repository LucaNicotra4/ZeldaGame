package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
//import object.BufferedImage;

public class NPC_1 extends Entity{
     
     public int screenX, screenY;

     public NPC_1(GamePanel gp){

          super(gp);

          direction = "down";
          speed = 1;

          solidArea = new Rectangle();
          solidArea.x = 8;
          solidArea.y = 16;
          solidAreaDefaultX = solidArea.x;
          solidAreaDefaultY = solidArea.y;
          solidArea.width = 28;
          solidArea.height = 28;

          worldX = gp.tileSize * 20;
          worldY = gp.tileSize * 18;

          loadImages();
     }

     public void loadImages(){
          try{
               up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Up_1.png"));
               up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Up_2.png"));
               down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Down_1.png"));
               down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Down_2.png"));
               right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Right_1.png"));
               right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Right_2.png"));
               left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Left_1.png"));
               left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Link_Left_2.png"));
          }catch(IOException e){
               //e.printStackTrace(null);
          }
     }

     public void update(){
          // int screenX = worldX - gp.player.worldX + gp.player.screenX;
          // int screenY = worldY - gp.player.worldY + gp.player.screenY;
          int screenX = worldX;
          int screenY = worldY;

          spriteCounter++;
          if(spriteCounter > 30){ //player image changes every 30 frames
               System.out.println("(" + screenX + ", " + screenY + ")");
               if(spriteNum == 1){
                    spriteNum = 2;
               }else if(spriteNum == 2){
                    spriteNum = 1;
               }
               spriteCounter = 0;
          }
     }

     public void draw(Graphics2D g2){
          // g2.setColor(Color.white);

          // g2.fillRect(x, y, gp.tileSize, gp.tileSize); //top
          
          BufferedImage image = null;

          switch(direction){
          case "up":
               if(spriteNum == 1) image = up1;
               if(spriteNum == 2) image = up2;
               break;
          case "down":
               if(spriteNum == 1) image = down1;
               if(spriteNum == 2) image = down2;
               break;
          case "left":
               if(spriteNum == 1) image = left1;
               if(spriteNum == 2) image = left2;
               break;
          case "right":
               if(spriteNum == 1) image = right1;
               if(spriteNum == 2) image = right2;
               break;
          }
          
          g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
     }
}
