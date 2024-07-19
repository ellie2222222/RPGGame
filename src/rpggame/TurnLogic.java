/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import GameEntity.Monster;
import GameEntity.Player;
import java.util.Scanner;

/**
 *
 * @author Tam
 */
public class TurnLogic {
    public static void combat(Player player, Monster monster) {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while (choice >= 0 && choice < 4) {
            System.out.print("> ");
            choice = sc.nextInt();
            switch(choice) {
                case 0:
                    System.out.println("You use Basic ATK");
                    break;
                case 1:
                    System.out.println("You use Basic Skill");
                    break;
                case 2:
                    System.out.println("You use Special Skill");
                   break;
                case 3:
                    System.out.println("You use Ultimate Skill");
                    break;
                default:
                    System.out.println("End turn");
                    break;
            }
            Mechanic.dealDMG(player, monster, "player", choice);
            System.out.println("Monster's HP: " + monster.HP);
            Mechanic.dealDMG(player, monster, "monster", 0);
            System.out.println("Player's HP: " + player.HP);
            if (player.HP == 0 || monster.HP == 0) {
                break;
            }
        }
    }
}
