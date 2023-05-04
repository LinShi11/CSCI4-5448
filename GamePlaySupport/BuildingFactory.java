package GamePlaySupport;

import GamePlay.Enum;

public class BuildingFactory {

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
