package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UI {
     GamePanel gp;
     Graphics2D g2;
     Font arial_40, arial_80B;
     public boolean messageOn = false;
     public String message = "";
     int messageCounter = 0;
     public boolean gameFinished = false;
     public int slotCol = 0;
     public int slotRow = 0;


     public UI(GamePanel gp){
          this.gp = gp;

          messageOn = false;
          arial_40 = new Font("Arial", Font.PLAIN, 40);
          arial_80B = new Font("Arial", Font.BOLD, 40);
     }

     public void showMessage(String text){
          message = text;
          messageOn = true;
     }

     public void draw(Graphics2D g2){
          this.g2 = g2;
          g2.setFont(arial_40);
          g2.setColor(Color.white);
          if(gp.gameState == gp.playState){

          }
          if(gp.gameState == gp.pauseState){
               //drawPauseScreen();
               drawInventory();
          }
     }

     public void drawPauseScreen(){
          g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
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
          int slotX = slotXStart;
          int slotY = slotYStart;

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
     }

}
