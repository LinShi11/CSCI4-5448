package CommandRandomEvents;

import GamePlay.Game;

import java.util.Random;

/**
 * command pattern: people leaving when they are unhappy
 */
public class PeopleLeaving implements Command {
    /**
     * people leaving will happen if the villagers are unhappy
     * @param game: instance of game so updates could happen
     * @return: message built from the action
     */
    @Override
    public String execute(Game game) {
        String message = "A lot of villager have been unhappy lately. ";
        int villagerCount = RandomEventHelper.losingPeople(game);
        if(villagerCount == 0){
            message = message + "However, they decided to stick around to see if things will improve. ";
        } else{
            message = message + "A total of " + villagerCount + " villagers decided to leave the village. ";
        }
        message = message + "Make sure you are providing enough resources for them. ";
        return message;
    }
}
