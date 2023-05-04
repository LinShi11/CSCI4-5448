package ActionListeners;

import GamePlay.Enum;
import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class assigns and removes villagers from jobs
 */
public class VillagerAssignmentHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public VillagerAssignmentHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * This function tries to add/remove villagers from job
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String choice = event.getActionCommand();
        switch(choice){
            case "Hunter_up":
                game.assignJobs(Enum.jobType.Hunter);
                break;
            case "Hunter_down":
                game.removeJobs(Enum.jobType.Hunter);
                break;
            case "Miner_up":
                game.assignJobs(Enum.jobType.Miner);
                break;
            case "Miner_down":
                game.removeJobs(Enum.jobType.Miner);
                break;
            case "Lumberjack_up":
                game.assignJobs(Enum.jobType.Lumberjack);
                break;
            case "Lumberjack_down":
                game.removeJobs(Enum.jobType.Lumberjack);
                break;
            case "Weaponsmith_up":
                game.assignJobs(Enum.jobType.Weaponsmith);
                break;
            case "Weaponsmith_down":
                game.removeJobs(Enum.jobType.Weaponsmith);
                break;
            case "Repairer_up":
                game.assignJobs(Enum.jobType.Repairer);
                break;
            case "Repairer_down":
                game.removeJobs(Enum.jobType.Repairer);
                break;
            case "Cook_up":
                game.assignJobs(Enum.jobType.Cook);
                break;
            case "Cook_down":
                game.removeJobs(Enum.jobType.Cook);
                break;
            case "Waterman_up":
                game.assignJobs(Enum.jobType.Waterman);
                break;
            case "Waterman_down":
                game.removeJobs(Enum.jobType.Waterman);
                break;
            case "Tailor_up":
                game.assignJobs(Enum.jobType.Tailor);
                break;
            case "Tailor_down":
                game.removeJobs(Enum.jobType.Tailor);
                break;
            case "Gather_up":
                game.assignJobs(Enum.jobType.Gather);
                break;
            case "Gather_down":
                game.removeJobs(Enum.jobType.Gather);
                break;
            case "Trapper_up":
                game.assignJobs(Enum.jobType.Trapper);
                break;
            case "Trapper_down":
                game.removeJobs(Enum.jobType.Trapper);
                break;
            case "Gold_Miner_up":
                game.assignJobs(Enum.jobType.Gold_Miner);
                break;
            case "Gold_Miner_down":
                game.removeJobs(Enum.jobType.Gold_Miner);
                break;
            default:
                System.out.println("I am not sure what you created");
        }
        ui.jobRepaint();
    }
}
