package GamePlaySupport;

import GamePlay.Enum;

public class ClothesFactory implements Building{
    Enum.buildingType type;
    public ClothesFactory(){
        type = Enum.buildingType.Factory;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
