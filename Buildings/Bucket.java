package Buildings;

import GamePlay.Enum;

public class Bucket implements Building {
    Enum.buildingType type;
    public Bucket(){
        type = Enum.buildingType.Bucket;
    }

    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
