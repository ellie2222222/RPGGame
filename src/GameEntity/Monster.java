/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import java.util.HashMap;

/**
 *
 * @author Tam
 */
public class Monster extends NPC {
    public int HP;
    public int MR;
    public int AR;
    public int SPD;
    public int ATK;
    public double critChance;
    public double critDMG;
    public double atkSPD;
    public HashMap<String, Double> effect;
    
    @Override
    public String toString() {
        return "Name: " + name +
                "\nHP: " + HP +
                "\nATK: " + ATK +
                "\nMR: " + MR + 
                "\nAR: " + AR +
                "\nSPD: " + SPD +
                "\nAttack speed: " + atkSPD +
                "\nCritical chance: " + critChance + 
                "\nCritical damage: " + critDMG +
                "\nBonus effect: " + effect;
    }

    public Monster(String name, String description) {
        super(name, description);
    }

    public Monster(int HP, int MR, int AR, int ATK, String name) {
        super(name);
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.ATK = ATK;
    }

    public Monster(int HP, int MR, int AR, int SPD, int ATK, double critChance, double critDMG, double atkSPD, HashMap<String, Double> effect, String name, String description) {
        super(name, description);
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.SPD = SPD;
        this.ATK = ATK;
        this.critChance = critChance;
        this.critDMG = critDMG;
        this.atkSPD = atkSPD;
        this.effect = effect;
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
}
