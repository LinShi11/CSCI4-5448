public class Trap implements Building{

    Enum.buildingType type;
    public Trap(){
        type = Enum.buildingType.Trap;
    }
//    @Override
//    public int getLimit() {
//        return 1;
//    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }


}
