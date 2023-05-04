package Buildings;

import GamePlay.Enum;

public class Tradecart implements Building {
    private static Tradecart tradecart;
    Enum.buildingType type;
    public Tradecart(){
        type = Enum.buildingType.Tradecart;
    }

    public static Tradecart getInstance(){
        if(tradecart == null){
            tradecart = new Tradecart();
        }
        return tradecart;
    }
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
