/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Tam
 */
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, rightPressed, leftPressed, shiftPressed;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } 
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        } 
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } 
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int code = ke.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } 
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        } 
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } 
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }
    
}
