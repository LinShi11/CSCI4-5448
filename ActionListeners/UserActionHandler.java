package ActionListeners;

import GamePlay.Enum;
import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class adds the buttons to the daily tasks
 */
public class UserActionHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public UserActionHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * tries to add the buttons to the list of daily tasks
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String choice = event.getActionCommand();
        switch (choice){
            case "gatherWood":
                game.setDailyAgenda(Enum.resourceType.wood);
                break;
            case "gatherFood":
                game.setDailyAgenda(Enum.resourceType.food);
                break;
            case "gatherMeat":
                game.setDailyAgenda(Enum.resourceType.meat);
                break;
            case "gatherRocks":
                game.setDailyAgenda(Enum.resourceType.rock);
                break;
            case "gatherWater":
                game.setDailyAgenda(Enum.resourceType.water);
                break;
            case "gatherClothes":
                game.setDailyAgenda(Enum.resourceType.clothes);
                break;
            case "gatherFur":
                game.setDailyAgenda(Enum.resourceType.fur);
                break;
            case "gatherGold":
                game.setDailyAgenda(Enum.resourceType.gold);
                break;
            default:
                System.out.println("I am not sure what you created");
        }
        ui.removeTaskButtons();
        ui.dailyTaskPanelRepaint();
        ui.map();
    }
}