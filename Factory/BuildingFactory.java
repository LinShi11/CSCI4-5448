package Factory;

import Buildings.*;
import GamePlay.Enum;
import Buildings.Mines;

/**
 * Factory pattern for building.
 */
public class BuildingFactory {

    /**
     * construct the building according to the type
     * Note: Tradecart is singleton; thus, getInstance is called
     * @param type: type of building
     * @return building instance
     */
    public Building constructBuilding(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                return new Smokehouse();
            case Factory:
                return new ClothesFactory();
            case Bucket:
                return new Bucket();
            case Trap:
                return new Trap();
            case Hut:
                return new Hut();
            case Blacksmith:
                return new Blacksmith();
            case Mines:
                return new Mines();
            case Tradecart:
                return Tradecart.getInstance();
            case Gold_Mine:
                return new Gold_Mine();
            default:
                return null;
        }
    }
}
