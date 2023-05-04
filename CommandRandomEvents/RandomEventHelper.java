package CommandRandomEvents;

import GamePlay.Enum;
import GamePlay.Game;

import java.util.Random;

/**
 * The class is used as the helper class for random events
 */
public class RandomEventHelper {

    static Random random = new Random();

    /**
     * losing random resource
     * @param type: the type of resource
     * @param game: the game instance and remove the resource
     * @return: the random value that we are removing
     */
    public static int looting(Enum.resourceType type, Game game){

        int count;
        if(game.getResourceMap().get(type) > 3){
            count = random.nextInt(game.getResourceMap().get(type)/3);
        } else{
            count = 0;
        }
        game.setResource(type, count);
        return count;
    }

    /**
     * finds the attackStrength
     * @param probability: the probability of being attacked
     * @param range: the range of possible attackstrength
     * @param defense: current defense score
     * @return
     */
    public static int attacked(int probability, int range, int defense){
        int attackStrength;

        if(defense < probability){
            attackStrength = random.nextInt(defense);
        } else{
            attackStrength = random.nextInt(range) + defense - probability;
        }
        return attackStrength;
    }

    /**
     * find the random people count that the village lost
     * @param game: game instance
     * @return the number of people the village lost
     */
    public static int losingPeople(Game game){
        if(game.getPeopleArrayList().size()/10 == 0){
            return 0;
        } else {
            int count = random.nextInt((int) (game.getPeopleArrayList().size() / 10));
            game.lostVillager(count);
            return count;
        }
    }

    /**
     * find teh random health that the village lost
     * @param game: game instance
     * @param limit: the range for the health removed
     * @return the random health
     */
    public static int losingHealth(Game game, int limit){
        if (game.getStats().get(Enum.stats.health) < limit){
            return random.nextInt(game.getStats().get(Enum.stats.health));
        } else{
            return random.nextInt(limit);
        }
    }

    /**
     * does not randomly generate the resource that we will lose, but randomly generate the amount that is lost
     * @param game: game instance
     * @param type: the type of resource
     * @return: the random amount that is lost
     */
    public static int loseResources(Game game, Enum.resourceType type){
        int resourceCount = game.getResourceMap().get(type);
        if(resourceCount == 0){
            return -1;
        } else {
            int count = random.nextInt(game.getResourceMap().get(type));
            game.setResource(type, count);
            return count;
        }
    }

}
