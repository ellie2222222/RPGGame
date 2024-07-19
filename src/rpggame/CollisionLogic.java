/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameEntity.Entity;

/**
 *
 * @author Tam
 */
public class CollisionLogic {
    GamePanel gp;

    public CollisionLogic(GamePanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(Entity entity) {
        // Entity collision's area edge coordinate
        double entityLeftWorldX = entity.worldX + entity.solidArea.x;
        double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        double entityTopWorldY = entity.worldY + entity.solidArea.y;
        double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        // Entity collision's area edge index location map
        int entityLeftCol = (int) (entityLeftWorldX / gp.tileSize);
        int entityRightCol = (int) (entityRightWorldX / gp.tileSize);
        int entityTopRow = (int) (entityTopWorldY / gp.tileSize);
        int entityBottomRow = (int) (entityBottomWorldY / gp.tileSize);
        
        // left, right according to entity's direction
        int leftCorner, rightCorner;
        
        switch(entity.direction) {
            case "up":
                entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize);
                leftCorner = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                rightCorner = gp.tileM.mapTile[entityRightCol][entityTopRow];
                if (gp.tileM.tile[leftCorner].collision == true || gp.tileM.tile[rightCorner].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize);
                leftCorner = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                rightCorner = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[leftCorner].collision == true || gp.tileM.tile[rightCorner].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
                leftCorner = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                rightCorner = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                if (gp.tileM.tile[leftCorner].collision == true || gp.tileM.tile[rightCorner].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
                leftCorner = gp.tileM.mapTile[entityRightCol][entityTopRow];
                rightCorner = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[leftCorner].collision == true || gp.tileM.tile[rightCorner].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    
    
    public int checkObject(Entity entity, boolean player) {
        int index = Integer.MAX_VALUE;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                
                // Get entity's solid area position
                entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
                entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
                
                // Get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision = true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
