public class Hut implements Building{
    Enum.buildingType type;
    public Hut(){
        type = Enum.buildingType.Hut;
    }
//    @Override
//    public int getLimit() {
//        return 5;
//    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
