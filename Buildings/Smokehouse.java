package Buildings;

import GamePlay.Enum;

/**
 * The smokehouse class implements the building class
 */
public class Smokehouse implements Building {
    Enum.buildingType type;
    public Smokehouse(){
        type = Enum.buildingType.Smokehouse;
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
