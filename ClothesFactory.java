public class ClothesFactory implements Building{
    Enum.buildingType type;
    public ClothesFactory(){
        type = Enum.buildingType.Factory;
    }
    @Override
    public int getLimit() {
        return 3;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
