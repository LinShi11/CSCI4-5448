package People;

import GamePlay.Enum;

/**
 * The gather class implements the people class
 * The gather produces food and wood
 * The gather consumes water
 */
public class Gather implements People {
    Enum.jobType type;
    public Gather(){
        type = Enum.jobType.Gather;
    }
    @Override
    public int getFood() {
        return 1;
    }

    @Override
    public int getMeat() {
        return 0;
    }

    @Override
    public int getWater() {
        return -1;
    }

    @Override
    public int getFur() {
        return 0;
    }

    @Override
    public int getWood() {
        return 1;
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
