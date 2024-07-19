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
public class Armor extends Item {
    public int HP;
    public int MR;
    public int AR;
    public int SPD;
    public String element;
    public String pieceSet;
    public HashMap<String, Double> effect;
    public String resistance;
    public boolean wearable;

    public Armor(String name) {
        super(name);
    }

    public Armor(int HP, int MR, int AR, int SPD, String name) {
        super(name);
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.SPD = SPD;
    }

    public Armor(int HP, int MR, int AR, int SPD, String element, String pieceSet, HashMap<String, Double> effect, String resistance, boolean wearable, String name) {
        super(name);
        this.HP = HP;
        this.MR = MR;
        this.AR = AR;
        this.SPD = SPD;
        this.element = element;
        this.pieceSet = pieceSet;
        this.effect = effect;
        this.resistance = resistance;
        this.wearable = wearable;
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
    
    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public boolean isWearable() {
        return wearable;
    }

    public void setWearable(boolean wearable) {
        this.wearable = wearable;
    }    
}
