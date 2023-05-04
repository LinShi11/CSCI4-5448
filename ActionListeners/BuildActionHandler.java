package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GamePlay.*;
import GamePlay.Enum;

/**
 * This class tries to construct the building based on buttons clicked
 */
public class BuildActionHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public BuildActionHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * This class tries to construct buildings
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String choice = event.getActionCommand();

        switch(choice){
            case "hut":
                game.addBuildings(Enum.buildingType.Hut);
                break;
            case "smokehouse":
                game.addBuildings(Enum.buildingType.Smokehouse);
                break;
            case "mines":
                game.addBuildings(Enum.buildingType.Mines);
                break;
            case "factory":
                game.addBuildings(Enum.buildingType.Factory);
                break;
            case "blacksmith":
                game.addBuildings(Enum.buildingType.Blacksmith);
                break;
            case "bucket":
                game.addBuildings(Enum.buildingType.Bucket);
                break;
            case "trap":
                game.addBuildings(Enum.buildingType.Trap);
                break;
            case "tradecart":
                game.addBuildings(Enum.buildingType.Tradecart);
                break;
            case "gold mine":
                game.addBuildings(Enum.buildingType.Gold_Mine);
                break;
            default:
                System.out.println("I am not sure what you created");
        }
        ui.dailyRepaint();
    }
}
