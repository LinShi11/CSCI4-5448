package GamePlay;

public class Enum {

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

    public enum mapLocationType{
        village,
        action,
        tradecart
    }

    public enum stats{
        health,
        defense
    }
}
