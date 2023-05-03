public class Helper {

    public static int getJobLimit(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                return 5;
            case Factory:
                return 5;
            case Bucket:
                return 5;
            case Trap:
                return 5;
            case Hut:
                return 5;
            case Blacksmith:
                return 5;
            case Mines:
                return 5;
            default:
                return 0;
        }
    }
}
