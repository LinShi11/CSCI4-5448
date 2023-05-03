public class Enum {

    enum buildingType{
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
    enum resourceType{
        wood,
        food,
        meat,
        fur,
        rock,
        water,
        clothes,
        gold
    }

    enum jobType{
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

    enum magicItems{
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

    enum mapLocationType{
        village,
        action,
        tradecart
    }

    enum stats{
        health,
        defense
    }
}
