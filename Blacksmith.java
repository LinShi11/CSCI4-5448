public class Blacksmith implements Building{
    Enum.buildingType type;
    public Blacksmith(){
        type = Enum.buildingType.Blacksmith;
    }
    @Override
    public void getLimit() {

    }
    @Override
    public Enum.buildingType getType() {
        return type;
    }

}
