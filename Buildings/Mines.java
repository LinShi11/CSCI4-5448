package Buildings;

import GamePlay.Enum;
import Buildings.Building;

public class Mines implements Building {
    Enum.buildingType type;
    public Mines(){
        type = Enum.buildingType.Mines;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
