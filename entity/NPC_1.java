package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
//import object.BufferedImage;

public class NPC_1 extends Entity{
     
     public int screenX, screenY;

     public NPC_1(GamePanel gp){

          super(gp);

          direction = "up";
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
          // screenX = gp.screenWidth/2 - (gp.tileSize / 2) + 2;
          // screenY = gp.screenHeight/2 - (gp.tileSize /2);
          

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
          screenX = worldX - gp.player.worldX + gp.player.screenX;
          screenY = worldY - gp.player.worldY + gp.player.screenY;

          gp.cChecker.checkTile(this);
          spriteCounter++;
          if(this.collisionOn || spriteCounter % 120 == 0){ //player direction changes every 120 frames or on tile collision
               int random = (int)(Math.random()*4);
               switch(random){
                    case 0: direction = "up";break;
                    case 1: direction = "down";break;
                    case 2: direction = "left";break;
                    case 3: direction = "right";break;
               }

               collisionOn = false;
               spriteCounter = 0;
          }
          
          if(spriteCounter % 12 == 0 && !collisionOn){
               if(spriteNum == 1){
                    spriteNum = 2;
               }else if(spriteNum == 2){
                    spriteNum = 1;
               }
          }

          switch(direction){
               case "up":
                    worldY -= speed;
                    break;
               case "down":
                    worldY += speed;
                    break;
               case "left":
                    worldX -= speed;
                    break;
               case "right":
                    worldX += speed;
                    break;
          }
     }

     public void draw(Graphics2D g2){
          // g2.setColor(Color.white);

          // g2.fillRect(x, y, gp.tileSize, gp.tileSize); //top
          
          BufferedImage image = null;
          image = up1;

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
