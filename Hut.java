public class Hut implements Building{
    Enum.buildingType type;
    public Hut(){
        type = Enum.buildingType.Hut;
    }
    @Override
    public void getLimit() {

    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
