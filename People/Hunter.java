package People;

import GamePlay.Enum;

/**
 * The hunter class implements people class
 * The hunter produces meat and fur
 * The hunter consumes food, water, and wood
 */
public class Hunter implements People {
    Enum.jobType type;
    public Hunter(){
        type = Enum.jobType.Hunter;
    }
    @Override
    public int getFood() {
        return -1;
    }

    @Override
    public int getMeat() {
        return 5;
    }

    @Override
    public int getWater() {
        return -1;
    }

    @Override
    public int getFur() {
        return 3;
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
