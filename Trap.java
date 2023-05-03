public class Trap implements Building{

    Enum.buildingType type;
    public Trap(){
        type = Enum.buildingType.Trap;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }


}
