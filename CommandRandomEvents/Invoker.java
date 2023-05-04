package CommandRandomEvents;

import GamePlay.Game;

/**
 * Command pattern: invoker class that invokes the action
 */
public class Invoker {

    // the command used
    private Command command;

    /**
     * set the command that will be executed next
     * @param command: command type
     */
    public void setCommand(Command command){
        this.command = command;
    }

    /**
     * executes the action
     * @param game: the game instance
     * @return: the message built from the execution
     */
    public String execute(Game game){
        return command.execute(game);
    }
}
