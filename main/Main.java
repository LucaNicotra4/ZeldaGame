/**
 * Personal Legend of Zelda game
 * @author Luca Nicotra
 * with help from youtube: RyiSnow
 */
package main;


import javax.swing.JFrame;

public class Main{
    public static void main(String[] args){
          
          //Establishing window
          JFrame window = new JFrame();
          window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          window.setResizable(false);
          window.setTitle("GAME");

          //Creating gamePanel and adding to window
          GamePanel gamePanel = new GamePanel();
          window.add(gamePanel);
          window.pack();

          window.setLocationRelativeTo(null);
          window.setVisible(true);

          //Starting game
          gamePanel.setUpGame();
          gamePanel.startGameThread();
     } 
}
