public class Bucket implements Building{
    Enum.buildingType type;
    public Bucket(){
        type = Enum.buildingType.Bucket;
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
