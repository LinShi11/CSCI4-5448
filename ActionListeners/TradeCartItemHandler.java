package ActionListeners;

import GamePlay.Enum;
import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles all tradecart item actions
 */
public class TradeCartItemHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public TradeCartItemHandler(Game game, GameUI ui) {
        this.game = game;
        this.ui = ui;
    }
    /**
     * tries to deletes the resource if the button is clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String choice = e.getActionCommand();
        switch (choice){
            case "delete_wood":
                game.deleteCartResourceItem(Enum.resourceType.wood);
                break;
            case "delete_food":
                game.deleteCartResourceItem(Enum.resourceType.food);
                break;
            case "delete_meat":
                game.deleteCartResourceItem(Enum.resourceType.meat);
                break;
            case "delete_fur":
                game.deleteCartResourceItem(Enum.resourceType.fur);
                break;
            case "delete_rock":
                game.deleteCartResourceItem(Enum.resourceType.rock);
                break;
            case "delete_water":
                game.deleteCartResourceItem(Enum.resourceType.water);
                break;
            case "delete_clothes":
                game.deleteCartResourceItem(Enum.resourceType.clothes);
                break;
            case "delete_gold":
                game.deleteCartResourceItem(Enum.resourceType.gold);
                break;
            case "delete_matches":
                game.deleteCartMagicItem(Enum.magicItems.matches);
                break;
            case "delete_axe":
                game.deleteCartMagicItem(Enum.magicItems.axe);
                break;
            case "delete_needle":
                game.deleteCartMagicItem(Enum.magicItems.needle);
                break;
            case "delete_pickaxe":
                game.deleteCartMagicItem(Enum.magicItems.pickaxe);
                break;
            case "delete_bait":
                game.deleteCartMagicItem(Enum.magicItems.bait);
                break;
            case "delete_storage":
                game.deleteCartMagicItem(Enum.magicItems.storage);
                break;
            case "delete_metal":
                game.deleteCartMagicItem(Enum.magicItems.metal);
                break;
            case "delete_bow":
                game.deleteCartMagicItem(Enum.magicItems.bow);
                break;
            case "delete_sword":
                game.deleteCartMagicItem(Enum.magicItems.sword);
                break;
            case "delete_gunpowder":
                game.deleteCartMagicItem(Enum.magicItems.gunpowder);
                break;
            default:
                System.out.println(choice);
        }
        ui.dailyRepaint();
        ui.map();
    }
}
