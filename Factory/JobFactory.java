package Factory;

import GamePlay.Enum;
import People.*;

/**
 * Factory pattern for jobs
 */
public class JobFactory {
    /**
     * assign villagers to a job according to the type
     * @param type: the type fo job
     * @return an instance of a person
     */
    public People assignJob(Enum.jobType type){
        switch (type){
            case Cook:
                return new Cook();
            case Gather:
                return new Gather();
            case Hunter:
                return new Hunter();
            case Miner:
                return new Miner();
            case Tailor:
                return new Tailor();
            case Trapper:
                return new Trapper();
            case Repairer:
                return new Repairer();
            case Waterman:
                return new Waterman();
            case Lumberjack:
                return new Lumberjack();
            case Weaponsmith:
                return new Weaponsmith();
            case Villager:
                return new Villager();
            case Gold_Miner:
                return new Gold_miner();
            default:
                return null;
        }
    }
}
