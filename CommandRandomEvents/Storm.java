package CommandRandomEvents;

import GamePlay.Game;

/**
 * Command Pattern: implements from command interface
 */
public class Storm implements Command {
    /**
     * The storm will lose health only
     * @param game: instance of game so updates could happen
     * @return: the message built from this action
     */
    @Override
    public String execute(Game game) {
        String message = "There was a storm last night. Luckily, you evacuated the villagers. You lost ";
        int health = RandomEventHelper.losingHealth(game, 10);
        message = message + health + " health. ";
        game.setHealth(health);
        return message;

    }
}
