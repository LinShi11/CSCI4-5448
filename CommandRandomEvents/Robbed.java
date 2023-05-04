package CommandRandomEvents;

import GamePlay.Enum;
import GamePlay.Game;

public class Robbed implements Command {

    @Override
    public String execute(Game game) {
        String message = "You were visited by thieves over night. ";
        int foodCount = RandomEventHelper.loseResources(game, Enum.resourceType.food);
        if(foodCount == -1){
            message = message + "The thieves could not find any food, and wonders to himself: How does a village have no food? ";
        } else {
            message = message + "They took " + foodCount + " food. ";
        }
        int goldCount = RandomEventHelper.loseResources(game, Enum.resourceType.gold);
        if(goldCount == -1){
            message = message + "The thieves looked everywhere and could not find gold. They thought that gold was well hidden. Little did they know....";
        } else {
            message = message + " Additionally, they took " + goldCount + " gold. ";
        }
        return message;

    }
}
