package Buildings;

import GamePlay.Enum;

/**
 * The hut class implements the building class
 */
public class Hut implements Building{
    Enum.buildingType type;
    public Hut(){
        type = Enum.buildingType.Hut;
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
