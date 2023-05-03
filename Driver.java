public class Driver {

    public static void main(String[] args){
        Game game = new Game();
        GameUI  gameUI = new GameUI(game);
        Logger logger = Logger.getInstance();
        game.register(logger);
        game.register(gameUI);
    }
}
