package Buildings;

import GamePlay.Enum;

/**
 * This is the blacksmith class, implementing from building
 */
public class Blacksmith implements Building {
    Enum.buildingType type;
    public Blacksmith(){
        type = Enum.buildingType.Blacksmith;
    }

    /**
     * returns the correct type
     * @return type blacksmith
     */
    @Override
    public Enum.buildingType getType() {
        return type;
    }

}
