package ActionListeners;

import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the action listener for the continue button for alert messages
 */
public class ContinueAlertHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public ContinueAlertHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * this function will reset everything and remove the alert message
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ui.resetAllPanel();
        ui.dailyRepaint();
        ui.map();
    }
}
