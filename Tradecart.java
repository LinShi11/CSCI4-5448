public class Tradecart implements Building{
    Tradecart tradecart;
    Enum.buildingType type;
    public Tradecart(){
        type = Enum.buildingType.Tradecart;
    }
    @Override
    public void getLimit() {

    }
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
