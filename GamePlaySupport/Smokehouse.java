package GamePlaySupport;

import GamePlay.Enum;
import GamePlaySupport.Building;

public class Smokehouse implements Building {
    Enum.buildingType type;
    public Smokehouse(){
        type = Enum.buildingType.Smokehouse;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
