package CommandRandomEvents;

import GamePlay.Game;

import java.util.Random;

public class PeopleLeaving implements Command {

    private Random random = new Random();
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
