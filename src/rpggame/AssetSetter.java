/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameObject.Chest;
import GameObject.Door;
import GameObject.Key;
import java.awt.Graphics2D;

/**
 *
 * @author Tam
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
        gp.obj[0] = new Key(gp);
        gp.obj[0].worldX = gp.tileSize * 23;
        gp.obj[0].worldY = gp.tileSize * 7;
        
        gp.obj[1] = new Door(gp);
        gp.obj[1].worldX = gp.tileSize * 10;
        gp.obj[1].worldY = gp.tileSize * 11;
        
        gp.obj[2] = new Chest(gp);
        gp.obj[2].worldX = gp.tileSize * 10;
        gp.obj[2].worldY = gp.tileSize * 7;
        
//        gp.obj[3] = new Door();
//        gp.obj[3].worldX = gp.tileSize * 23;
//        gp.obj[3].worldY = gp.tileSize * 8;
    }
    
    public void setChest() {
        gp.obj[2].image = gp.obj[2].image2;
        gp.obj[2].worldX = gp.tileSize * 10;
        gp.obj[2].worldY = gp.tileSize * 7;
    }
}
