/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.io.IOException;
import javax.imageio.ImageIO;
import rpggame.GamePanel;

/**
 *
 * @author Tam
 */
public class Chest extends Item {
    
    public Chest(GamePanel gp) {
        super("Chest");
        this.gp = gp;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/images/objects/chest.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = ImageIO.read(getClass().getResourceAsStream("/resource/images/objects/chest_opened.png"));
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
