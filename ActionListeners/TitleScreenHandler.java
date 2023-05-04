package ActionListeners;

import GamePlay.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class is the action listener for the login page
 */
public class TitleScreenHandler implements ActionListener {
    private Game game;
    private GameUI ui;
    public TitleScreenHandler(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }
    /**
     * this class will call the login based on new user button or returning user
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();

        // the new user
        if(Objects.equals(command, "newUser")){
            game.createData();
            ui.createAllChangablePanels();
            game.saveName(ui.getUserName().getText());
            ui.setEventAnnouncerPanel("Welcome to the game");
            ui.titleScreenHelper();
            ui.gamePlayScreen();
            ui.map();
        }
        // the returning user
        else if(Objects.equals(command, "returningUser")){
            // if the name exist
            if(game.checkUserName(ui.getUserName().getText())){
                game.loadData();
                ui.createAllChangablePanels();
                game.saveName(ui.getUserName().getText());
                ui.setEventAnnouncerPanel("Welcome to the game");
                ui.titleScreenHelper();
                ui.gamePlayScreen();
                ui.map();
            }
            // when the name does not exist, the text field will be updated to "No file stored"
            else{
                ui.setUserName("No File Stored");
            }
        }

    }
}