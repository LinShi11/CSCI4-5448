public class Bucket implements Building{
    Enum.buildingType type;
    public Bucket(){
        type = Enum.buildingType.Bucket;
    }
    @Override
    public void getLimit() {

    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
