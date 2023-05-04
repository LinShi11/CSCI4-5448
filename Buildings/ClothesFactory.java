package Buildings;

import GamePlay.Enum;

/**
 * This class, clothesfactory implements building
 */
public class ClothesFactory implements Building{
    Enum.buildingType type;
    public ClothesFactory(){
        type = Enum.buildingType.Factory;
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
