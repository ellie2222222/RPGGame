/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.util.HashMap;

/**
 *
 * @author Tam
 */
public class Weapon extends Item {
    public int ATK;
    public double critChance;
    public double critDMG;
    public double atkSPD;
    public String element;
    public String pieceSet;
    public HashMap<String, Double> effect;

    public Weapon(String name) {
        super(name);
    }

    public Weapon(int ATK, double critChance, double critDMG, double atkSPD, String element, String pieceSet, HashMap<String, Double> effect, String name) {
        super(name);
        this.ATK = ATK;
        this.critChance = critChance;
        this.critDMG = critDMG;
        this.atkSPD = atkSPD;
        this.element = element;
        this.pieceSet = pieceSet;
        this.effect = effect;
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

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getPieceSet() {
        return pieceSet;
    }

    public void setPieceSet(String pieceSet) {
        this.pieceSet = pieceSet;
    }

    public HashMap<String, Double> getEffect() {
        return effect;
    }

    public void setEffect(HashMap<String, Double> effect) {
        this.effect = effect;
    }
}
