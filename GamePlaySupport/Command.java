package GamePlaySupport;

import GamePlay.Game;

public interface Command {
    String execute(Game game);
}
