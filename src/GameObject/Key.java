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
public class Key extends Item {

    public Key(GamePanel gp) {
        super("Key");
        this.gp = gp;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/images/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
