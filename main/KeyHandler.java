package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

     GamePanel gp;
     public boolean upPressed, downPressed, leftPressed, rightPressed;
     public boolean checkDrawtime;

     public KeyHandler(GamePanel gp){
          this.gp = gp;
     }

     @Override
     public void keyTyped(KeyEvent e) {
     }

     @Override
     public void keyPressed(KeyEvent e) {
          int code = e.getKeyCode();

          if(gp.gameState == gp.playState){
               playState(code);
          }else if(gp.gameState == gp.pauseState){
               pauseState(code);
          }
     }


     public void pauseState(int code){
          if(code == KeyEvent.VK_P){
               gp.gameState = gp.playState;
          }else if(code == KeyEvent.VK_W){
               if(gp.ui.slotRow != 0) gp.ui.slotRow--;
          }else if(code == KeyEvent.VK_A){
               if(gp.ui.slotCol != 1) gp.ui.slotCol--;
          }else if(code == KeyEvent.VK_S){
               if(gp.ui.slotRow != 3) gp.ui.slotRow++;
          }else if(code == KeyEvent.VK_D){
               if(gp.ui.slotCol != 3) gp.ui.slotCol++;
          }
     }

     public void playState(int code){
          if(code == KeyEvent.VK_W){ //if user presses 'W'
               upPressed = true;
          }

          if(code == KeyEvent.VK_A){
               leftPressed = true;
          }

          if(code == KeyEvent.VK_S){
               downPressed = true;
          }

          if(code == KeyEvent.VK_D){
               rightPressed = true;
          }
          if(code == KeyEvent.VK_P){
               if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
               }else if(gp.gameState == gp.pauseState){
                    gp.gameState = gp.playState;
               }
          }
     }

     @Override
     public void keyReleased(KeyEvent e) {
          int code = e.getKeyCode();

          if(code == KeyEvent.VK_W){ //if user pressed 'W'
               upPressed = false;
          }

          if(code == KeyEvent.VK_A){
               leftPressed = false;
          }

          if(code == KeyEvent.VK_S){
               downPressed = false;
          }

          if(code == KeyEvent.VK_D){
               rightPressed = false;
          }
     }
      
}
