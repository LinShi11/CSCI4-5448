package GamePlaySupport;

import GamePlay.Enum;
import GamePlay.Game;

import java.util.Random;

public class RandomEventHelper {

    static Random random = new Random();
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

    public static int attacked(int probability, int range, int defense){
        int attackStrength;

        if(defense < probability){
            attackStrength = random.nextInt(defense);
        } else{
            attackStrength = random.nextInt(range) + defense - probability;
        }
        return attackStrength;
    }

    public static int losingPeople(Game game){
        if(game.getPeopleArrayList().size()/10 == 0){
            return 0;
        } else {
            int count = random.nextInt((int) (game.getPeopleArrayList().size() / 10));
            game.lostVillager(count);
            return count;
        }
    }

    public static int losingHealth(Game game, int limit){
        if (game.getStats().get(Enum.stats.health) < limit){
            return random.nextInt(game.getStats().get(Enum.stats.health));
        } else{
            return random.nextInt(limit);
        }
    }

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
