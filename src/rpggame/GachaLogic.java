package rpggame;

import java.util.Random;

public class GachaLogic {

    // Helper method to select an item based on probabilities
    public static String randomRarity(double randomValue) {
        String[] rarity = {"Common", "Uncommon", "Rare", "Epic", "Legendary", "*Mythical*", "**Divine**", "***Primordial***"};
        double[] rarityProbabilities = {0.3, 0.25, 0.15, 0.12, 0.1, 0.07, 0.009, 0.001};
        double cumulativeProbability = 0.0;
        // Iterate through items and check if the random value falls within the cumulative probability
        int i;
        for (i = 0; i < rarity.length; i++) {
            cumulativeProbability += rarityProbabilities[i];
            
            if (randomValue < cumulativeProbability) {
                break;
            }
        }
        return rarity[i];
    }
    
    public static String randomPart(double randomValue) {
        String[] part = {"Helmet", "Chestplate", "Leggings", "Boots"}; 
        double[] partProbabilities = {0.25, 0.25, 0.25, 0.25};  
        double cumulativeProbability = 0.0;
        // Iterate through items and check if the random value falls within the cumulative probability
        int i;
        for (i = 0; i < part.length; i++) {
            cumulativeProbability += partProbabilities[i];
            
            if (randomValue < cumulativeProbability) {
                break;
            }
        }
        return part[i];
    }
    
    public static String randomWeapon(double randomValue) {
        String[] weapon = {"Sword", "Bow", "Scepter", "Lance"}; 
        double[] weaponProbabilities = {0.25, 0.25, 0.25, 0.25};  
        double cumulativeProbability = 0.0;
        // Iterate through items and check if the random value falls within the cumulative probability
        int i;
        for (i = 0; i < weapon.length; i++) {
            cumulativeProbability += weaponProbabilities[i];
            
            if (randomValue < cumulativeProbability) {
                break;
            }
        }
        return weapon[i];
    }
    
    public static String randomElement(double randomValue) {
        String[] element = {"Fire", "Water", "Earth", "Wind", "Ice", "Lightning", "Nature", "Void", "Cosmic", "Light"};
        double[] elementProbabilities = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        double cumulativeProbability = 0.0;
        // Iterate through items and check if the random value falls within the cumulative probability
        int i;
        for (i = 0; i < element.length; i++) {
            cumulativeProbability += elementProbabilities[i];
            
            if (randomValue < cumulativeProbability) {
                break;
            }
        }
        return element[i];
    }
    
    public static String randomItem(String item1, String item2, double randomValue) {
        String[] item = {item1, item2}; 
        double[] itemProbabilities = {0.5, 0.5};  
        double cumulativeProbability = 0.0;
        // Iterate through items and check if the random value falls within the cumulative probability
        int i;
        for (i = 0; i < item.length; i++) {
            cumulativeProbability += itemProbabilities[i];
            
            if (randomValue < cumulativeProbability) {
                break;
            }
        }
        return item[i];
    }
    
    public static void gacha(int num, GameEntity.Player player) {
        Random random = new Random();

        // Gacha n times
        for (int i = 1; i <= num; i++) {
            double randomValue = random.nextDouble();
            String randomSelectedRarity = GachaLogic.randomRarity(randomValue);
            randomValue = random.nextDouble();
            String randomSelectedPart = GachaLogic.randomPart(randomValue);
            randomValue = random.nextDouble();
            String randomSelectedWeapon = GachaLogic.randomWeapon(randomValue);
            randomValue = random.nextDouble();
            String randomSelectedElement = GachaLogic.randomElement(randomValue);
            randomValue = random.nextDouble();
            String randomSelectedItem = GachaLogic.randomItem(randomSelectedPart, randomSelectedWeapon, randomValue);
            System.out.println("Pull #" + i + ": You got " + randomSelectedRarity + " " + randomSelectedElement + " " + randomSelectedItem);
            
            
//            player.getInventory().add("")
        }
    }
}
