public class ClothesFactory implements Building{
    Enum.buildingType type;
    public ClothesFactory(){
        type = Enum.buildingType.Factory;
    }
    @Override
    public void getLimit() {

    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
