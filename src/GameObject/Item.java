/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import rpggame.GamePanel;
import rpggame.UtilityTool;

/**
 *
 * @author Tam
 */
public class Item implements Serializable{
    public String name;
    public String description = "";
    
    public BufferedImage image;
    public BufferedImage image2;
    public boolean collision = false;
    public int worldX, worldY;
    
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    
    GamePanel gp;
    UtilityTool uTool = new UtilityTool();
    
    public void draw(Graphics2D g2, GamePanel gp) {
        double screenX = worldX - gp.player.worldX + gp.player.screenX;
        double screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            AffineTransform transform = new AffineTransform();
            transform.translate(screenX, screenY);
            g2.drawImage(image, transform, null);
        }
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
