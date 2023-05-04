package GamePlaySupport;

import GamePlay.Enum;

import java.awt.*;

public class Helper {

    public static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    public static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    public static Font arrowFont = new Font("Times New Roman", Font.PLAIN, 12);
    public static Font announcerFont = new Font("Times New Roman", Font.PLAIN, 18);

    public static int getJobLimit(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                return 5;
            case Factory:
                return 10;
            case Bucket:
                return 1;
            case Trap:
                return 1;
            case Hut:
                return 5;
            case Blacksmith:
                return 3;
            case Mines:
                return 10;
            case Gold_Mine:
                return 3;
            default:
                return 0;
        }
    }
}
