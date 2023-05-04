package People;

import GamePlay.Enum;

/**
 * The gold miner class implements people class
 * The gold miner produces gold
 * The gold miner consumes food, water, and wood
 */
public class Gold_miner implements People {

    Enum.jobType type;
    public Gold_miner(){ type = Enum.jobType.Gold_Miner;}

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
        return 0;
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
        return 3;
    }

    @Override
    public Enum.jobType getType() {
        return type;
    }
}
