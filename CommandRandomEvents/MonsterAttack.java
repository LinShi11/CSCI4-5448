package CommandRandomEvents;

import GamePlay.Enum;
import GamePlay.Game;
import GamePlaySupport.Helper;

import java.util.Random;

/**
 * Command pattern: implements the command pattern.
 * The class simulates a monster attack: might loss resource, people, and health
 */
public class MonsterAttack implements Command {
    private Random random = new Random();

    /**
     * The execute for monster fight
     * @param game: instance of game so updates could happen
     * @return: the message built from this action
     */
    @Override
    public String execute(Game game) {
        String message = "Oh no. A Monster has attacked over the night. ";
        int resource = random.nextInt(Enum.resourceType.values().length);
        Enum.resourceType type = Helper.findItem(resource);

        int count = RandomEventHelper.looting(type, game);
        message = message + "You lost " + count + " " + type.toString() + ". ";

        int defense = game.getStats().get(Enum.stats.defense);
        int attackStrength = RandomEventHelper.attacked(20, 30 , defense);
        if(attackStrength > defense){
            int villagerCount = RandomEventHelper.losingPeople(game);
            if(villagerCount == 0){
                message = message + "Additionally, the villagers were being attacked. Although some were injured, no one has died. ";
            } else {
                message = message + "Additionally, the monsters have killed " + villagerCount + " villagers. Make sure to upgrade your defense. ";
            }
            game.setHealth(attackStrength - defense);
        }


        return message;

    }
}
