package Buildings;

import GamePlay.Enum;
import Buildings.Building;

/**
 * The mines class implements the building class
 */
public class Mines implements Building {
    Enum.buildingType type;
    public Mines(){
        type = Enum.buildingType.Mines;
    }
    /**
     * getter for type
     * @return the corresponding type
     */
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
