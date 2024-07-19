/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameEntity.Monster;
import GameEntity.Player;
import SkillSet.Skill;
import SkillSet.Skill_Fire;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Tam
 */
public class Mechanic {
    
    public static boolean isCrit(double critChance) {
        Random random = new Random();
        double randomValue = random.nextDouble();
        if (randomValue < critChance / 100) {
            return true;
        }
        return false;
    }
    
    public static void dealDMG(Player player, Monster monster, String turn, int skill) {
        boolean crit = isCrit(player.critChance);
        double totalPlayerDMG = 0;

        if (skill == 0) {
            if (crit == true) {
                totalPlayerDMG = player.ATK * player.critDMG / 100;
            } else {
                totalPlayerDMG = player.ATK;
            }
        } else {
            HashMap<Integer, Skill> skillSet = player.getSkillSet();
            double skillDMG = 0;
            if (skillSet.get(skill) instanceof Skill_Fire) {
                Skill_Fire playerSkill = (Skill_Fire) skillSet.get(skill);;
                skillDMG = playerSkill.dmgRate;
            }
            
            if (crit == true) {
                totalPlayerDMG = (skillDMG * player.ATK / 100) * (player.critDMG / 100);
            } else {
                totalPlayerDMG = (int) (skillDMG * player.ATK / 100);
            } 
        }
                
         
        
        if (turn.equals("player")) {
            if (monster.HP <= totalPlayerDMG) {
                monster.setHP(0);
            } else {
                monster.setHP(monster.HP - (int) totalPlayerDMG);
            }
        } else if (turn.equals("monster")) {
            if (player.HP <= monster.ATK) {
                player.setHP(0);
            } else {
                player.setHP(player.HP - monster.ATK);
            }
        } else {
            System.out.println("Unknown turn");
        }
//        System.out.println(monster.name + " took " + player.ATK + " dmg");
    }
}
