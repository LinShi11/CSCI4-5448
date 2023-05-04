package ActionListeners;

import GamePlay.Enum;
import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the used to update the daily tasks and be reflective
 */
public class DeleteAgendaHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public DeleteAgendaHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * delete the task from the list once the button is clicked.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch(choice){
            case "delete_wood":
                game.deleteDailyAgenda(Enum.resourceType.wood);
                break;
            case "delete_food":
                game.deleteDailyAgenda(Enum.resourceType.food);
                break;
            case "delete_meat":
                game.deleteDailyAgenda(Enum.resourceType.meat);
                break;
            case "delete_rock":
                game.deleteDailyAgenda(Enum.resourceType.rock);
                break;
            case "delete_water":
                game.deleteDailyAgenda(Enum.resourceType.water);
                break;
            case "delete_clothes":
                game.deleteDailyAgenda(Enum.resourceType.clothes);
                break;
            case "delete_fur":
                game.deleteDailyAgenda(Enum.resourceType.fur);
                break;
            case "delete_gold":
                game.deleteDailyAgenda(Enum.resourceType.gold);
                break;
            default:
                System.out.println("I am not sure what you are trying to delete");
        }
        ui.removeTaskButtons();
        ui.dailyTaskPanelRepaint();
        ui.map();
    }
}
