package Buildings;

import GamePlay.Enum;

public class Gold_Mine implements Building{
    Enum.buildingType type;

    public Gold_Mine(){
        type = Enum.buildingType.Gold_Mine;
    }
    @Override
    public Enum.buildingType getType() {
        return type;
    }
}
