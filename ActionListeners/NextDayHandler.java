package ActionListeners;

import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the next day action listener
 */
public class NextDayHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public NextDayHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * calls the appropriate functions for a new day
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.dailyUpdate();
        ui.dailyRepaint();
        ui.setNewDay(true);
        ui.alert();
    }

}