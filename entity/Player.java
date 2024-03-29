/**
 * <h1>Class for main playable character<h1>
 * Class outlining Link's playable character
 * <p>Extends Entity class<p>
 */

package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler; 

public class Player extends Entity{
     KeyHandler keyH;

     public final int screenX;
     public final int screenY;
     public int maxHearts; //hearts measured in quarters
     public int hearts; //4 hearts equivalent to one full heart

     public Player(GamePanel gp, KeyHandler keyH){

          super(gp); //passing gamePanel to superclass
          this.keyH = keyH;

          screenX = gp.screenWidth/2 - (gp.tileSize / 2);
          screenY = gp.screenHeight/2 - (gp.tileSize /2);

          solidArea = new Rectangle();
          solidArea.x = 8;
          solidArea.y = 16;
          solidAreaDefaultX = solidArea.x;
          solidAreaDefaultY = solidArea.y;
          solidArea.width = 28;
          solidArea.height = 28;

          setDefaultValues();
          getPlayerImage();
          //gp.ui.updateHearts(maxHearts, maxHearts);
     }

     /**
      * Sets the starting values for Link such as world position and speed
      */
     public void setDefaultValues(){
          //Player positioning on the world map
          worldX = gp.tileSize * 23;
          worldY = gp.tileSize * 21;
          speed = 4;
          direction = "down";
          maxHearts = 12;
          hearts = 12;
     }

     /**
      * Loads the different character frames for Link
      */
     public void getPlayerImage(){
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

     /**
      * Updates player regarding image movements and direction along with position and collision detection
      */
     public void update(){
          if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
               if(keyH.upPressed == true){
                    direction = "up";
               }else if(keyH.downPressed == true){
                    direction = "down";
               }else if(keyH.leftPressed == true){
                    direction = "left";
               }else if(keyH.rightPressed == true){
                    direction = "right";  
               }

               //CHECK TILE COLLISION
               collisionOn = false;
               gp.cChecker.checkTile(this);

               //CHECK OBJECT COLLISION
               int objIndex = gp.cChecker.checkObject(this, true);
               if(objIndex != 999) objectCollisionHasOccurred(objIndex);
               //pickUpObject(objIndex);

               //If collision is false, player can move
               if(collisionOn == false){
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
     
               spriteCounter++;
               if(spriteCounter % 12 == 0){ //player image changes every 12 frames
                    if(spriteNum == 1){
                         spriteNum = 2;
                    }else if(spriteNum == 2){
                         spriteNum = 1;
                    }
                    //spriteCounter = 0;
                    //System.out.println("(" + this.worldX + ", " + this.worldY + ")");
               }
          }
     }

     public void objectCollisionHasOccurred(int index){
          if(gp.obj[index].name.equals("crate")){
               gp.ui.objectInteractionMessage("Press R to Interact");
               //gp.ui.displayMessageOn();
          }
     }

     public void pickUpObject(int index){
          if(index != 999){
               
          }
     }

     public void takeDamage(int damage){
          if(!(hearts - damage <= 0)){
               hearts -= damage;
               //gp.ui.updateHearts(hearts, maxHearts);
          }else{
               //character dies
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
