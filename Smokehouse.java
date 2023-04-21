public class Smokehouse implements Building{
    Enum.buildingType type;
    public Smokehouse(){
        type = Enum.buildingType.Smokehouse;
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
