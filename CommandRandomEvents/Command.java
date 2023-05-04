package CommandRandomEvents;

import GamePlay.Game;

/**
 * Command pattern interface. Contains the execute function
 */
public interface Command {
    /**
     * The execute function that runs the action
     * @param game: instance of game so updates could happen
     * @return: the message built from the action
     */
    String execute(Game game);
}
