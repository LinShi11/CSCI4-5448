package GamePlaySupport;

import GamePlay.Enum;

import java.awt.*;

public class Helper {
    // a list of possible fonts
    public static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    public static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    public static Font arrowFont = new Font("Times New Roman", Font.PLAIN, 12);
    public static Font announcerFont = new Font("Times New Roman", Font.PLAIN, 18);

    /**
     * helper to find the limit for each type of building
     * @param type: the type of building
     * @return: the limit associated with that building
     */
    public static int getJobLimit(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                return 5;
            case Factory:
                return 10;
            case Bucket:
                return 1;
            case Trap:
                return 1;
            case Hut:
                return 5;
            case Blacksmith:
                return 3;
            case Mines:
                return 10;
            case Gold_Mine:
                return 3;
            default:
                return 0;
        }
    }

    /**
     * helper function that generates resource
     * @param randomValue: the random value
     * @return: the corresponding resource
     */
    public static Enum.resourceType findItem(int randomValue){
        switch (randomValue){
            case 0:
                return Enum.resourceType.wood;
            case 1:
                return Enum.resourceType.food;
            case 2:
                return Enum.resourceType.meat;
            case 3:
                return Enum.resourceType.fur;
            case 4:
                return Enum.resourceType.rock;
            case 5:
                return Enum.resourceType.water;
            case 6:
                return Enum.resourceType.clothes;
            case 7:
                return Enum.resourceType.gold;
            default:
                return null;
        }
    }

    /**
     * helper function that generates magic item
     * @param randomValue: the random value
     * @return: the corresponding magic item
     */
    public static Enum.magicItems findMagicItem(int randomValue){
        switch (randomValue){
            case 0:
                return Enum.magicItems.matches;
            case 1:
                return Enum.magicItems.axe;
            case 2:
                return Enum.magicItems.needle;
            case 3:
                return Enum.magicItems.pickaxe;
            case 4:
                return Enum.magicItems.bait;
            case 5:
                return Enum.magicItems.storage;
            case 6:
                return Enum.magicItems.metal;
            case 7:
                return Enum.magicItems.bow;
            case 8:
                return Enum.magicItems.sword;
            case 9:
                return Enum.magicItems.gunpowder;
            default:
                return null;
        }
    }
}
