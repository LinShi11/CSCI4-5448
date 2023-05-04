package Buildings;

import GamePlay.Enum;

/**
 * This Bucket class implements building
 */
public class Bucket implements Building {
    Enum.buildingType type;
    public Bucket(){
        type = Enum.buildingType.Bucket;
    }

    /**
     * returns the type of this class
     * @return type bucket
     */
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
