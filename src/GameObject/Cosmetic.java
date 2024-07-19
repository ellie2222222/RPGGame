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
public class Cosmetic extends Item {
    public HashMap<String, Double> stats;
    public String pieceSet;

    public Cosmetic(String name) {
        super(name);
    }

    public Cosmetic(HashMap<String, Double> stats, String pieceSet, String name) {
        super(name);
        this.stats = stats;
        this.pieceSet = pieceSet;
    }

    public HashMap<String, Double> getStats() {
        return stats;
    }

    public void setStats(HashMap<String, Double> stats) {
        this.stats = stats;
    }

    public String getPieceSet() {
        return pieceSet;
    }

    public void setPieceSet(String pieceSet) {
        this.pieceSet = pieceSet;
    }
}
