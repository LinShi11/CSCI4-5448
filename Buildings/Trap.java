package Buildings;

import GamePlay.Enum;

/**
 * The trap class implements the building class
 */
public class Trap implements Building {

    Enum.buildingType type;
    public Trap(){
        type = Enum.buildingType.Trap;
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
