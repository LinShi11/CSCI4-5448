package People;

import GamePlay.Enum;

/**
 * The Tailor implements people class
 * tailor produces clothes
 * tailor consumes food, water, fur, and wood
 */
public class Tailor implements People{
    Enum.jobType type;
    public Tailor(){
        type = Enum.jobType.Tailor;
    }
    @Override
    public int getFood() {
        return -1;
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
        return -1;
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
        return 1;
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