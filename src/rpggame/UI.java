/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameObject.Key;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tam
 */
public class UI {
    GamePanel gp;
    Font arial_40;
    Font arial_70B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String msg = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_70B = new Font("Arial", Font.BOLD, 70);
        Key key = new Key(gp);
        keyImage = key.image;
    }
    
    public void showMessage(String msg) {
        this.msg = msg;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2) {
        if (gameFinished == true) {
            messageCounter++;
            if (messageCounter > 90) {
                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);
                String text = "You found the treasure!";
                int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                int x = gp.screenWidth / 2 - textLength / 2;
                int y = gp.screenHeight / 2 + gp.tileSize * 2;
                g2.drawString(text, x, y);

                g2.setFont(arial_70B);
                g2.setColor(Color.YELLOW);
                text = "CONGRATULATIONS";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLength / 2;
                y = gp.screenHeight / 2 - gp.tileSize * 2;
                g2.drawString(text, x, y);
            }
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" - " + gp.player.hasKey, 70, 65);

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(msg, gp.screenWidth / 2 + gp.tileSize, gp.screenHeight / 2 - gp.tileSize);

                messageCounter ++;
                if (messageCounter > 120) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}
