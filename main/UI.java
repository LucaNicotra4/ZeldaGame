package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.inventoryItem;

/**
 * Class responsible for the User Interface
 * of the game
 */
public class UI {
     GamePanel gp;
     Graphics2D g2;
     Font arial_40, arial_80B;
     public boolean messageOn;
     public String message = "";
     int messageCounter = 0;
     public boolean gameFinished = false;
     int slotXStart, slotYStart;
     public int slotCol = 1;
     public int slotRow = 1;
     public BufferedImage swordBase, shieldBase, armorBase,
          fullHeart, threeQuartersHeart,halfHeart, quarterHeart, emptyHeart;
     
     public inventoryItem[][] items = new inventoryItem[4][3];


     public UI(GamePanel gp){
          this.gp = gp;

          messageOn = false;
          arial_40 = new Font("Arial", Font.PLAIN, 40);
          arial_80B = new Font("Arial", Font.BOLD, 40);
          loadBaseImages();
          loadInventoryItems();
     }

     //displays message to screen
     public void showMessage(String text){
          
     }

     public void displayMessageOn(){
          messageOn = true;
     }

     public void objectInteractionMessage(String text){
          message = text;
          messageOn = true;
          g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
          int x = getCenteredX(text); 
          int y = (gp.screenHeight/5) * 4;

          g2.drawString(text, x, y);
     }


     public void draw(Graphics2D g2){
          this.g2 = g2;
          g2.setFont(arial_40);
          g2.setColor(Color.white);
          if(gp.gameState == gp.playState){
               updateHearts(gp.player.hearts, gp.player.maxHearts);
               if(messageOn) objectInteractionMessage(message);
          }
          if(gp.gameState == gp.pauseState){
               drawPauseScreen();
               drawInventory();
          }
          messageOn = false;
     }

     public void drawPauseScreen(){
          g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
          String text = "PAUSED";
          int x = getCenteredX(text); 
          int y = gp.screenHeight/2;

          g2.drawString(text, x, y);
     }

     public int getCenteredX(String text){
          int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
          int x = gp.screenWidth/2 - length/2;
          return x;
     }

     public void drawInventory(){

          //Window
          int frameX = gp.tileSize*9;
          int frameY = gp.tileSize;
          int frameWidth = gp.tileSize*5;
          int frameHeight = gp.tileSize*5;
          drawSubWindow(frameX, frameY, frameWidth,frameHeight);

          //Slot
          final int slotXStart = frameX + 20;
          final int slotYStart = frameY + 20;
          // int slotX = slotXStart;
          // int slotY = slotYStart;

          //Cursor
          int cursorX = slotXStart + (gp.tileSize * slotCol);
          int cursorY = slotYStart + (gp.tileSize * slotRow);
          int cursorWidth = gp.tileSize;
          int cursorHeight = gp.tileSize;

          //Draw Cursor
          g2.setColor(Color.white);
          g2.setStroke(new BasicStroke(3));
          g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

     }

     public void drawSubWindow(int x, int y, int width, int height){

          //Black Opaque Rectangle
          Color c = new Color(0,0,0, 180); //Black
          g2.setColor(c);
          g2.fillRoundRect(x, y, width, height, 35, 35);

          //Gold Frame
          c = new Color(204,204,0); //Gold
          g2.setColor(c);
          g2.setStroke(new BasicStroke(5));
          g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

          //Base pics
          int picX = gp.tileSize*9 + 10;
          int picY = gp.tileSize + 20;
          g2.drawImage(swordBase, picX, picY, gp.tileSize, gp.tileSize, null);
          g2.drawImage(shieldBase, picX, picY+gp.tileSize, gp.tileSize, gp.tileSize, null);
          g2.drawImage(armorBase, picX, picY+(2*gp.tileSize), gp.tileSize, gp.tileSize, null);
          g2.drawImage(armorBase, picX, picY+(3*gp.tileSize), gp.tileSize, gp.tileSize, null);

          //Inventory Items
          picX = gp.tileSize*10 + 20;
          picY = gp.tileSize + 20;
          int origX = picX;
          int origY = picY;
          c = new Color(204,204,0, 180); //Opaque Gold
          g2.setColor(c);
          for(int i = 0; i < items.length; i++){
               picY = origY + (i * gp.tileSize);
               for(int j = 0; j < items[i].length; j++){
                    picX = origX + (j * gp.tileSize);
                    if(items[i][j] != null){ //Drawing selected square
                         if(items[i][j].selected){ //always false?
                              g2.fillRoundRect(picX, picY, gp.tileSize, gp.tileSize, 10, 10);
                         }
                         g2.drawImage(items[i][j].image, picX, picY, gp.tileSize, gp.tileSize, null);
                    }
               }
          }
     }

     /**
      * Sets currently highlighted item to selected
      */
     public void itemSelected(){
          if(items[slotRow][slotCol-1].obtained){
               for(int i = 0; i < items[slotRow].length; i++){
                    items[slotRow][i].selected = false;
               }
               items[slotRow][slotCol-1].selected = true;
          }
     }

     public void loadBaseImages(){
          try{
               swordBase = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sword.png"));
               shieldBase = ImageIO.read(getClass().getResourceAsStream("/res/tiles/shield.png"));
               armorBase = ImageIO.read(getClass().getResourceAsStream("/res/tiles/armor.png"));

               fullHeart = ImageIO.read(getClass().getResourceAsStream("/res/objects/fullHeart.png"));
               threeQuartersHeart = ImageIO.read(getClass().getResourceAsStream("/res/objects/3quarterHeart.png"));
               halfHeart = ImageIO.read(getClass().getResourceAsStream("/res/objects/halfHeart.png"));
               quarterHeart = ImageIO.read(getClass().getResourceAsStream("/res/objects/quarterHeart.png"));
               emptyHeart = ImageIO.read(getClass().getResourceAsStream("/res/objects/emptyHeart.png"));
          }catch(Exception e){
               System.out.println("Error loading inventory images");
          }
     }

     public void loadInventoryItems(){ //swords, shields, armor
          items[0][0] = new inventoryItem("/res/objects/woodSword.png");
          items[0][1] = new inventoryItem("/res/objects/baseSword.png");
          items[0][2] = new inventoryItem("/res/objects/masterSword.png");

          items[1][0] = new inventoryItem("/res/objects/woodShield.png");
          items[1][1] = new inventoryItem("/res/objects/baseShield.png");
          items[1][2] = new inventoryItem("/res/objects/hyruleShield.png");

          items[2][0] = new inventoryItem("/res/objects/heroArmor.png");
          items[2][1] = new inventoryItem("/res/objects/zoraArmor.png");
          items[2][2] = new inventoryItem("/res/objects/magicArmor.png");

          // items[3][0] = new inventoryItem("/res/objects/baseSword.png");
          // items[3][1] = new inventoryItem("/res/objects/baseSword.png");
          // items[3][2] = new inventoryItem("/res/objects/baseSword.png");
     }

     /**
      * Function that draws heart graphics to the screen
      * @param hearts current amount of hearts that player has (measured in quarter hearts)
      * @param maxHearts maximum amount of hearts player can have (measured in quarte hearts)
      */
     public void updateHearts(int hearts, int maxHearts){
          int full = (int)hearts / 4; //number of full hearts
          int count = 0;
          int x = 0;
          int y = 0;

          for(int i = 0; i < full; i++){ //draws full hearts
               g2.drawImage(fullHeart, x, y, gp.tileSize, gp.tileSize, null);
               count += 4;
               x += gp.tileSize;
          }
          hearts -= (full * 4);
          if(hearts == 1){ // draws a quarter heart
               g2.drawImage(quarterHeart, x, y, gp.tileSize, gp.tileSize, null);
               count += 1;
               x += gp.tileSize;
          }else if(hearts == 2){ //draws a half heart
               g2.drawImage(halfHeart, x, y, gp.tileSize, gp.tileSize, null);
               count += 2;
               x += gp.tileSize;
          }else if(hearts == 3){ //draws a 3-quarters heart
               g2.drawImage(threeQuartersHeart, x, y, gp.tileSize, gp.tileSize, null);
               count += 3;
               x += gp.tileSize;
          }

          //draw remaining empty hearts
          if(count < maxHearts){
               int remaining = (maxHearts - count) / 4;
               for(int i = 0; i < remaining; i++){
                    g2.drawImage(emptyHeart, x, y, gp.tileSize, gp.tileSize, null);
                    x += gp.tileSize;
               }
          }
     }
     
}
