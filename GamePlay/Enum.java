package GamePlay;

/**
 * A class of enum that is used throughout the program
 */
public class Enum {

    /**
     * list of possible buildings
     */
    public enum buildingType{
        Smokehouse,
        Factory,
        Bucket,
        Trap,
        Hut,
        Blacksmith,
        Mines,
        Gold_Mine,
        Tradecart
    }

    /**
     * list of possible resources
     */
    public enum resourceType{
        wood,
        food,
        meat,
        fur,
        rock,
        water,
        clothes,
        gold
    }

    /**
     * list of possible jobs
     */
    public enum jobType{
        Gather,
        Hunter,
        Trapper,
        Waterman,
        Tailor,
        Miner,
        Weaponsmith,
        Villager,
        Lumberjack,
        Repairer,
        Cook,
        Gold_Miner
    }

    /**
     * list of possible magic items
     */
    public enum magicItems{
        matches, // get more food
        axe, // get more wood
        needle, // get more clothes
        pickaxe, // get more rocks and gold
        bait, // get more meat
        storage, // get more water
        metal, // get more health
        bow, // get more meat and fur
        sword, // get more defense
        gunpowder // get more meat/ fur/ defense
    }

    /**
     * list of possible map location
     */
    public enum mapLocationType{
        village,
        action,
        tradecart
    }

    /**
     * list of stats
     */
    public enum stats{
        health,
        defense
    }
}
