/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameObject.Item;
import GameTile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Tam
 */
public class GamePanel extends JPanel implements Runnable {
    
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    // KEY LISTENER
    public KeyHandler keyH = new KeyHandler(this);
    
    // UI
    public UI ui = new UI(this);
    
    // GAME THREAD
    Thread gameThread;
    
    // GAME SOUNDFX
    Sound music = new Sound();
    Sound soundFX = new Sound();
    
    // PLAYER INITIALIZATION
    public GameEntity.Player player = new GameEntity.Player(this, keyH);
    
    // TILE INITIALIZATION
    public TileManager tileM = new TileManager(this);
    
    // COLLISION DETECCTION
    public CollisionLogic collisionChecker = new CollisionLogic(this);
    
    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 0;
    
    // GAME OBJECT INITIALIZATION
    public AssetSetter assetSetter = new AssetSetter(this);
    public Item obj[] = new Item[10];
    
    // GAME FPS
    private final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //render
        this.addKeyListener(keyH);
        this.setFocusable(true); // game panel can be focused to receive key input
    }
    
    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
        this.gameState = playState;
    }
    
    public void startGameThread() {
        gameThread = new Thread(this); // this means this class
        gameThread.start();
    }

    public void run2() {
        double drawInterval = 1000000000 / FPS; // time to draw for each loop
        double nextDrawTime = System.nanoTime() + drawInterval; // total time for each loop
                
        while(gameThread != null) {
//            System.out.println("Game loop is running");
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if (timer >= 1000000000) {
//                System.out.println("FPS " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        if (gameState == playState) {
            player.update();
        } else {
            // do nothing
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // TIME
        double drawStart = System.nanoTime();
        
        // TILE
        tileM.draw(g2);
       
        // OBJECT
        for (Item obj1 : obj) {
            if (obj1 != null) {
                obj1.draw(g2, this);
            }   
        }
        
        // PLAYER
        player.draw(g2);
        
        // UI 
        ui.draw(g2);
        
        // TIME
        double drawEnd = System.nanoTime();
        System.out.println("Time: " + (drawEnd - drawStart) + " nanoseconds / " + ((drawEnd - drawStart) / 1000000) + " ms");
        
        // RELEASE RESOURCE
        g2.dispose();
    }
    
    public void playMusic(int index) {
        music.setFile(index);
        music.play();
        music.loop();
    }
    
    public void stopMusic() {
        music.stop();
    }
    
    public void playSoundFX(int index) {
        soundFX.setFile(index);
        soundFX.play();
    }
}
