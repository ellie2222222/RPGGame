/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tam
 */
public class Entity {
    public double worldX;
    public double worldY;
    public double speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage up, down, left, right;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    // Entity collision's area
    public Rectangle solidArea;
    public boolean collisionOn = false;
    
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
}
