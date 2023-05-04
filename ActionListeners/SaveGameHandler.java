package ActionListeners;

import GamePlay.Game;
import GamePlay.GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * This class is the action listener for the save game button
 */
public class SaveGameHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public SaveGameHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * this function calls the saveGame option in the actual game play
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            game.saveGame();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
