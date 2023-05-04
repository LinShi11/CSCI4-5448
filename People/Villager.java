package People;

import GamePlay.Enum;

/**
 * The villager class implements people class
 * The villager does not have another assignment, so they find their own resources
 */
public class Villager implements People{
    Enum.jobType type;
    public Villager(){
        type = Enum.jobType.Villager;
    }
    @Override
    public int getFood() {
        return 0;
    }

    @Override
    public int getMeat() {
        return 0;
    }

    @Override
    public int getWater() {
        return 0;
    }

    @Override
    public int getFur() {
        return 0;
    }

    @Override
    public int getWood() {
        return 0;
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