/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import rpggame.GamePanel;
import rpggame.UtilityTool;

/**
 *
 * @author Tam
 */
public class TileManager {
    rpggame.GamePanel gp;
    public Tile[] tile;
    public int mapTile[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTile = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/resource/maps/world01.txt");
    }
    
    public void getTileImage() {
        setup(0, "grass01", false);
        setup(1, "wall", true);
        setup(2, "water01", true);
        setup(3, "floor01", false);
        setup(4, "tree", true);
        setup(5, "road00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);
        // PLACEHOLDER

        // TILES LOAD IN
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "hut", false);
        setup(43, "floor01", false);
        setup(44, "table01", true);
    }
    
    public void setup(int index, String objName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resource/images/tiles/" + objName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath) {
        System.out.println("Loading map...");
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                System.out.println(line);
                while (col < gp.maxWorldCol) {
                    String[] nums = line.split("\\s+");
                    int num = Integer.parseInt(nums[col]);
                    mapTile[col][row] = num;
                    col ++;
                }   
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();
            System.out.println("Map loaded successfully");
        } catch (Exception e) {
            System.out.println("Map not found or encountered errors");
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTile[worldCol][worldRow];
            
            double worldX = worldCol * gp.tileSize;
            double worldY = worldRow * gp.tileSize;
            double screenX = (worldX - gp.player.worldX + gp.player.screenX);
            double screenY = (worldY - gp.player.worldY + gp.player.screenY);
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                AffineTransform transform = new AffineTransform();
                transform.translate(screenX, screenY);
                g2.drawImage(tile[tileNum].image, transform, null);
                g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
//                g2.setColor(Color.RED);
//                g2.drawRect((int) screenX, (int) screenY, 48, 48);
            }
            
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldRow ++;
                worldCol = 0;
            }
        }
    }
}
