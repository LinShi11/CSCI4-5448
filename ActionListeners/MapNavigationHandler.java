package ActionListeners;

import GamePlay.Enum;
import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the navigation bar handler
 */
public class MapNavigationHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public MapNavigationHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * This class sets the location and calls map
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String choice = event.getActionCommand();
        switch (choice) {
            case "village":
                ui.setMapLocation(Enum.mapLocationType.village);
                ui.map();
                break;
            case "action":
                ui.setMapLocation(Enum.mapLocationType.action);
                ui.map();
                break;
            case "tradecart":
                if(game.getBuildingMap().get(Enum.buildingType.Tradecart) != 0) {
                    ui.setMapLocation(Enum.mapLocationType.tradecart);
                    ui.map();
                }
                // you can only navigate to the tradecart if it has been built
                else{
                    game.notifyObserver("A tradecart must be build before you can use it");
                }
                break;
            default:
                System.out.println("I am not sure what you created");
        }

    }
}
