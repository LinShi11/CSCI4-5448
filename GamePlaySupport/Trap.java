package GamePlaySupport;

import GamePlay.Enum;
import GamePlaySupport.Building;

public class Trap implements Building {

    Enum.buildingType type;
    public Trap(){
        type = Enum.buildingType.Trap;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }


}
