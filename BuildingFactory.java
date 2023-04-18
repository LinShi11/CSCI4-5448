public class BuildingFactory {

    public Building constructBuilding(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                return new Smokehouse();
            case ClothesFactory:
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
                return new Tradecart();
            default:
                return null;
        }
    }
}
