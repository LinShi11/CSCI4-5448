public class Blacksmith implements Building{
    Enum.buildingType type;
    public Blacksmith(){
        type = Enum.buildingType.Blacksmith;
    }
    @Override
    public int getLimit() {
        return 2;
    }
    @Override
    public Enum.buildingType getType() {
        return type;
    }

}
