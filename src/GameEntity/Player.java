/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import SkillSet.Skill;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import rpggame.GamePanel;
import rpggame.KeyHandler;
import rpggame.UtilityTool;

/**
 *
 * @author Tam
 */
public class Player extends Entity {

    public String name;
    public int HP;
    public int MR;
    public int AR;
    public int SPD;
    public int ATK;
    public double critChance;
    public double critDMG;
    public double atkSPD;
    public HashMap<String, Double> effect;
    public HashMap<Integer, Skill> skillSet;
    public ArrayList<Object> inventory;
    public int hasKey = 0;

    rpggame.GamePanel gp;
    rpggame.KeyHandler keyH;

    public final double screenX;
    public final double screenY;

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down_static";
    }

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        // Player collision's area
        solidArea = new Rectangle(16, 30, 16, 12);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
    }

    public void getImage() {
        up = setup("up");
        up1 = setup("up1");
        up2 = setup("up2");
        down = setup("down");
        down1 = setup("down1");
        down2 = setup("down2");
        left = setup("left");
        left1 = setup("left1");
        left2 = setup("left2");
        right = setup("right");
        right1 = setup("right1");
        right2 = setup("right2");
    }
    
    public BufferedImage setup(String objName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/player_character/" + objName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (gp.ui.gameFinished != true) {
            double speedValue = speed;
            if (keyH.shiftPressed == true) {
                speedValue = speed * 2;
            }

            if (keyH.upPressed == true || keyH.downPressed == true
                    || keyH.leftPressed == true || keyH.rightPressed == true) {

                if (keyH.upPressed == true) {
                    direction = "up";
                }
                if (keyH.downPressed == true) {
                    direction = "down";
                }
                if (keyH.leftPressed == true) {
                    direction = "left";
                }
                if (keyH.rightPressed == true) {
                    direction = "right";
                }

                collisionOn = false; // VARIABLE TO HANDLE COLLISION

                // CHECK TILE COLLISION
                gp.collisionChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gp.collisionChecker.checkObject(this, true);
                interactObject(objIndex);

                if (collisionOn == false) {
                    if (keyH.upPressed == true) {
                        if (keyH.rightPressed == true) {
                            worldY -= speedValue / sqrt(2);
                            worldX += speedValue / sqrt(2);
                        } else if (keyH.leftPressed == true) {
                            worldY -= speedValue / sqrt(2);
                            worldX -= speedValue / sqrt(2);
                        } else {
                            worldY -= speedValue;
                        }
                    } else if (keyH.downPressed == true) {
                        if (keyH.rightPressed == true) {
                            worldY += speedValue / sqrt(2);
                            worldX += speedValue / sqrt(2);
                        } else if (keyH.leftPressed == true) {
                            worldY += speedValue / sqrt(2);
                            worldX -= speedValue / sqrt(2);
                        } else {
                            worldY += speedValue;
                        }
                    } else if (keyH.leftPressed == true) {
                        if (keyH.upPressed == true) {
                            worldY -= speedValue / sqrt(2);
                            worldX -= speedValue / sqrt(2);
                        } else if (keyH.downPressed == true) {
                            worldY += speedValue / sqrt(2);
                            worldX -= speedValue / sqrt(2);
                        } else {
                            worldX -= speedValue;
                        }
                    } else if (keyH.rightPressed == true) {
                        if (keyH.upPressed == true) {
                            worldY -= speedValue / sqrt(2);
                            worldX += speedValue / sqrt(2);
                        } else if (keyH.downPressed == true) {
                            worldY += speedValue / sqrt(2);
                            worldX += speedValue / sqrt(2);
                        } else {
                            worldX += speedValue;
                        }
                    }
                }

                spriteCounter++;

                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            } else if (keyH.upPressed == false && keyH.downPressed == false
                    && keyH.leftPressed == false && keyH.rightPressed == false) {
                switch (direction) {
                    case "up":
                        direction = "up_static";
                        break;
                    case "down":
                        direction = "down_static";
                        break;
                    case "left":
                        direction = "left_static";
                        break;
                    case "right":
                        direction = "right_static";
                        break;
                }
            }
        }
    }

    public void interactObject(int objIndex) {
        if (objIndex != Integer.MAX_VALUE) {
            String objName = gp.obj[objIndex].name;
            switch (objName) {
                case "Key":
                    gp.playSoundFX(1);
                    hasKey++;
                    gp.obj[objIndex] = null;
                    gp.ui.showMessage("+1 Key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundFX(2);
                        gp.obj[objIndex] = null;
                        hasKey--;
                        gp.ui.showMessage("Door opened");
                    } else {
                        gp.ui.showMessage("No key to unlock");
                    }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.playSoundFX(3);
                    gp.assetSetter.setChest();
                    gp.stopMusic();
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    img = up1;
                }
                if (spriteNum == 2) {
                    img = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    img = down1;
                }
                if (spriteNum == 2) {
                    img = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    img = left1;
                }
                if (spriteNum == 2) {
                    img = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    img = right1;
                }
                if (spriteNum == 2) {
                    img = right2;
                }
                break;
            case "up_static":
                img = up;
                break;
            case "down_static":
                img = down;
                break;
            case "left_static":
                img = left;
                break;
            case "right_static":
                img = right;
                break;
        }
        
        AffineTransform transform = new AffineTransform();
        transform.translate(screenX, screenY);
        g2.drawImage(img, transform, null);
//        g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
//        g2.setColor(Color.WHITE);
//        g2.fillRect((int) screenX + solidArea.x, (int) screenY + solidArea.y, solidArea.width, solidArea.height);
//        g2.drawRect((int) screenX, (int) screenY, gp.tileSize, gp.tileSize);
    }

    @Override
    public String toString() {
        return "Name: " + name
                + "\nHP: " + HP
                + "\nATK: " + ATK
                + "\nMR: " + MR
                + "\nAR: " + AR
                + "\nSPD: " + SPD
                + "\nAttack speed: " + atkSPD
                + "\nCritical chance: " + critChance
                + "\nCritical damage: " + critDMG
                + "\nBonus effect: " + effect
                + "\nInventory: " + inventory;
    }

    public Player() {
        this.screenX = 0;
        this.screenY = 0;
        this.name = "Ellie";
    }

    public Player(String name, int HP, int MR, int AR, int ATK) {
        this.name = name;
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.ATK = ATK;
        this.screenX = 0;
        this.screenY = 0;
    }

    public Player(String name, int HP, int MR, int AR, int SPD, int ATK, double critChance, double critDMG, double atkSPD, HashMap<String, Double> effect, ArrayList<Object> inventory) {
        this.name = name;
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.SPD = SPD;
        this.ATK = ATK;
        this.critChance = critChance;
        this.critDMG = critDMG;
        this.atkSPD = atkSPD;
        this.effect = effect;
        this.inventory = inventory;
        this.screenX = 0;
        this.screenY = 0;
    }

    public ArrayList<Object> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Object> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMR() {
        return MR;
    }

    public void setMR(int MR) {
        this.MR = MR;
    }

    public int getAR() {
        return AR;
    }

    public void setAR(int AR) {
        this.AR = AR;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public double getCritDMG() {
        return critDMG;
    }

    public void setCritDMG(double critDMG) {
        this.critDMG = critDMG;
    }

    public double getAtkSPD() {
        return atkSPD;
    }

    public void setAtkSPD(double atkSPD) {
        this.atkSPD = atkSPD;
    }

    public HashMap<String, Double> getEffect() {
        return effect;
    }

    public void setEffect(HashMap<String, Double> effect) {
        this.effect = effect;
    }

    public HashMap<Integer, Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(HashMap<Integer, Skill> skillSet) {
        this.skillSet = skillSet;
    }
}
