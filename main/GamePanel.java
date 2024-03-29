package main;


import javax.swing.JPanel;

import entity.NPC_1;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
     //SCREEN SETTINGS
     final int originalTileSize = 16; // 16x16 tile
     final int scale = 3;

     //SCREEN DIMENSIONS
     public final int tileSize = originalTileSize * scale;
     public final int maxScreenCol = 16;
     public final int maxScreenRow = 12;
     public final int screenWidth = tileSize * maxScreenCol; // 768px
     public final int screenHeight = tileSize * maxScreenRow; // 576px

     //WORLD SETTINGS
     public final int maxWorldCol = 50;
     public final int maxWorldRow = 50;
     public final int worldWidth = tileSize * maxWorldCol;
     public final int worldHeight = tileSize * maxWorldRow;

     //FPS
     int FPS = 60;
     boolean paused;

     //Initiallizing game components
     TileManager tileM = new TileManager(this); //Responsible for drawing tiles to screen
     public KeyHandler keyH = new KeyHandler(this); //Responsible for handling user keystrokes
     Thread gameThread; //Main thread
     public CollisionChecker cChecker = new CollisionChecker(this); //Responsible for handling collisions between entities and objects or tiles
     public AssetSetter aSetter = new AssetSetter(this);
     public UI ui = new UI(this); //User Interface class
     public Player player = new Player(this, keyH); //Main character, played by user
     public SuperObject obj[] = new SuperObject[10]; 

     //Default player settings
     int playerX = 100;
     int playerY = 100;
     int playerSpeed = 4;

     //NPC
     NPC_1 npc = new NPC_1(this); //old man

     //Game State
     public int gameState;
     public final int playState = 1;
     public final int pauseState = 2;
     
     public GamePanel(){
          this.setPreferredSize(new Dimension(screenWidth, screenHeight));
          this.setBackground(Color.black);
          this.setDoubleBuffered(true);
          this.addKeyListener(keyH); //allows gamePanel to recognize key input
          this.setFocusable(true); //gamePanel can be "focused" to receive key input
     }

     public void setUpGame(){
          aSetter.setObject();
          gameState = playState;
     }

     public void startGameThread(){
          gameThread = new Thread(this); //passing the gamePanel class to the Thread's constructor
          gameThread.start(); //calls run method
     }

     @Override
     public void run() {
          while(gameThread != null){
               double drawInterval = 1000000000 / FPS;
               double nextDrawTime = System.nanoTime() + drawInterval;

               // 1 UPDATE: update info like player positions
               update();
               // 2 DRAW: draw the screen with the updated info
               repaint(); //calls paintComponent() method

               try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime / 1E6;

                    if(remainingTime < 0){
                         remainingTime = 0;
                    }

                    Thread.sleep((long)remainingTime); //thread "pauses" until next draw time

                    nextDrawTime += drawInterval;
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
          }    
     }

     public void update(){
          if(gameState == playState){
               player.update();
               npc.update();
          }
          if(gameState == pauseState){
               //nothing
          }
     }

     public void paintComponent(Graphics g){
          super.paintComponent(g);

          Graphics2D g2 = (Graphics2D)g;

          //TILES
          tileM.draw(g2);

          //OBJECT
          for(int i = 0; i < obj.length; i++){
               if(obj[i] != null){
                    obj[i].draw(g2, this);
               }
          }

          //PLAYER
          player.draw(g2);
          npc.draw(g2);

          //UI
          ui.draw(g2);

          g2.dispose();
     }
}

