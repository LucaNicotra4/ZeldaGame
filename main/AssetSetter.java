package main;

import object.OBJ_Crate;

public class AssetSetter {
     GamePanel gp;

     public AssetSetter(GamePanel gp){
          this.gp = gp;
     }

     public void setObject(){
          OBJ_Crate linksHouse = new OBJ_Crate();
          linksHouse.worldX = 1104;
          linksHouse.worldY = 1248;
          gp.obj[0] = linksHouse;
     }
}
