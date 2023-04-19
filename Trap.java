public class Trap implements Building{

    Enum.buildingType type;
    public Trap(){
        type = Enum.buildingType.Trap;
    }
    @Override
    public void getLimit() {

    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }


}
