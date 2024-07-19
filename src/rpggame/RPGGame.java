/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;    

import GameEntity.Player;
import GameObject.Armor;
import SkillSet.Skill;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author Tam
 */
public class RPGGame {
    public static ArrayList<Armor> armorList = new ArrayList<>();
    public static void loadItem() {
        try (BufferedReader br = new BufferedReader(new FileReader("Armor.txt"))) {
            String line;
            Armor currentArmor = null;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (line.endsWith(":")) {
                    // Identify the armor type
                    String armorType = line.substring(0, line.length() - 1).trim();
                    currentArmor = new Armor(armorType);
                    
                } else {
                    // Parse stats for the current armor
                    
                    String[] parts = line.split("\\s+");
                    String stat = parts[0];
                    int value = Integer.parseInt(parts[1]);

                    switch (stat) {
                        case "HP":
                            currentArmor.HP = value;
                            break;
                        case "AR":
                            currentArmor.AR = value;
                            break;
                        case "MR":
                            currentArmor.MR = value;
                            break;
                        case "SPD":
                            currentArmor.SPD = value;
                            armorList.add(new Armor(currentArmor.HP, currentArmor.MR, currentArmor.AR, currentArmor.SPD, currentArmor.name));
                            break;
                    }
                }
            }

            // Add armor objects to the list
            

            // Print the armor list
//            for (Armor armor : armorList) {
//                System.out.println("Name: " + armor.name);
//                System.out.println("HP: " + armor.HP);
//                System.out.println("AR: " + armor.AR);
//                System.out.println("MR: " + armor.MR);
//                System.out.println("SPD: " + armor.SPD);
//                System.out.println();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void setupGame() {
        GameEntity.Monster m = new GameEntity.Monster(100000, 600, 700, 13, "Space Bug");
        System.out.println(m);
        System.out.println();
        GameEntity.Player p = new GameEntity.Player();
        p.setATK(4000);
        p.setCritChance(60);
        p.setCritDMG(200);
        p.setHP(500);
        HashMap<Integer, Skill> skillSet = new HashMap<>();
        skillSet.put(1, new SkillSet.Skill_Fire("basic"));
        skillSet.put(2, new SkillSet.Skill_Fire("special"));
        skillSet.put(3, new SkillSet.Skill_Fire("ultimate"));
        p.setSkillSet(skillSet);
        System.out.println(p);
        System.out.println();
        System.out.println("Combat starts\n");
        TurnLogic.combat(p, m);
    }
    
    public static void main(String[] args) {
        System.out.println("Game starts\n");
//        setupGame();
//        GameEntity.Player p = new Player();
//        GachaLogic.gacha(100, p);
//        loadItem();
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("RPG Game");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();  
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread(); 
        gamePanel.setupGame();
    }   
}
