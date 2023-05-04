package Buildings;

import GamePlay.Enum;

/**
 * The class implements building
 */
public class Gold_Mine implements Building{
    Enum.buildingType type;

    public Gold_Mine(){
        type = Enum.buildingType.Gold_Mine;
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
