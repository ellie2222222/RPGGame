/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SkillSet;

/**
 *
 * @author Tam
 */
public class Skill_Fire extends Skill {
    public double dmgRate;
    public double burnRate;
    
    public Skill_Fire(String type) {
        super();
        if (type.equals("basic")) {
            setDescription("Deal Fire DMG to enemy equals to 70% of character's ATK, and burn enemy dealing DMG equal to 1% of their max HP for 1 turn");
            this.dmgRate = 70;
            this.burnRate = 1;
        } else if (type.equals("special")) {
            setDescription("Deal Fire DMG to enemy equals to 150% of character's ATK, and burn enemy dealing DMG equal to 2% of their max HP for 4 turn");
            this.dmgRate = 150;
            this.burnRate = 2;
        } else if (type.equals("ultimate")) {
            setDescription("Deal Fire DMG to enemy equals to 600% of character's ATK, and burn enemy dealing DMG equal to 5% of their max HP for 7 turn");
            this.dmgRate = 600;
            this.burnRate = 5;
        }
    }


    public double getDmgRate() {
        return dmgRate;
    }

    public void setDmgRate(double dmgRate) {
        this.dmgRate = dmgRate;
    }

    public double getBurnRate() {
        return burnRate;
    }

    public void setBurnRate(double burnRate) {
        this.burnRate = burnRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
