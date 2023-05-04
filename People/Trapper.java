package People;

import GamePlay.Enum;

/**
 * The trapper class implements people class
 * trapper produces meat, fur
 * trapper consumes food, water, and wood
 */
public class Trapper implements People{
    Enum.jobType type;
    public Trapper(){
        type = Enum.jobType.Trapper;
    }
    @Override
    public int getFood() {
        return -1;
    }

    @Override
    public int getMeat() {
        return 3;
    }

    @Override
    public int getWater() {
        return -1;
    }

    @Override
    public int getFur() {
        return 1;
    }

    @Override
    public int getWood() {
        return -1;
    }

    @Override
    public int getRock() {
        return 0;
    }

    @Override
    public int getClothes() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public Enum.jobType getType() {
        return type;
    }
}
