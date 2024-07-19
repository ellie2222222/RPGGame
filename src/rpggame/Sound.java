/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Tam
 */
public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    
    public Sound() {
        soundURL[0] = getClass().getResource("/resource/sounds/background.wav");
        soundURL[1] = getClass().getResource("/resource/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/resource/sounds/unlock.wav");
        soundURL[3] = getClass().getResource("/resource/sounds/fanfare.wav");
    }
    
    public void setFile(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
