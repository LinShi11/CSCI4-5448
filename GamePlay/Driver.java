package GamePlay;

import ObserverPattern.Logger;

/**
 * The driver class
 */
public class Driver {

    /**
     * main function: creates game, and gameUI, also uses the logger, and observer pattern
     * @param args
     */
    public static void main(String[] args){
        Game game = new Game();
        GameUI gameUI = new GameUI(game);
        Logger logger = Logger.getInstance();
        game.register(logger);
        game.register(gameUI);
    }
}
