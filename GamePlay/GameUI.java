package GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import GameUIHelpers.*;
import ActionListeners.*;
import GamePlaySupport.*;

/**
 * citation: use https://www.ryisnow.online/2021/04/java-for-beginner-text-adventure-game.html as reference to jFrame start ups
 */
public class GameUI implements ObserverInterface {
    JFrame window;
    Container con;

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel, peoplePanel, numberPanel, arrowPanel, healthPanel, mapPanel,
            userActionPanel, dailyTaskPanel, nextDayPanel, cartItemsPanel, magicItemPanel, tradeCartInfoPanel, savePanel, alertPanel, alertButtonPanel;
    JLabel titleLabel;
    JTextField userName;
    JTextArea announcer, resources, buildings, health, dailyTaskInfo, tradeCartInfo, alertMessage;
    ActionListener tsHandler, choiceHandler, userActionHandler, mapNavigationHandler, villagerAssignmentHandler, deleteAgendaHandler, tradeCartItemHandler, nextDayHandler, saveGameHandler, continueAlertHandler;
    Game game;
    Enum.mapLocationType mapLocation;
    ArrayList<JButton> dailyTasksButtons;
    Logger logger;
    boolean newDay;
    GameUIHelper uiHelper;

    /**
     * Constructor for the game UI.
     * @param tempGame
     */
    public GameUI(Game tempGame){
        // setting the necessary parameters
        game = tempGame;
        uiHelper = new GameUIHelper();
        window = new JFrame();
        window.setSize(1600, 1200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();
        mapLocation = Enum.mapLocationType.village;
        newDay = true;
        logger = Logger.getInstance();

        choiceHandler = new BuildActionHandler(game, this);
        villagerAssignmentHandler = new VillagerAssignmentHandler(game, this);
        tradeCartItemHandler = new TradeCartItemHandler(game, this);
        nextDayHandler = new NextDayHandler(game, this);
        userActionHandler = new UserActionHandler(game, this);
        mapNavigationHandler = new MapNavigationHandler(game, this);
        deleteAgendaHandler = new DeleteAgendaHandler(game, this);
        continueAlertHandler = new ContinueAlertHandler(game, this);
        saveGameHandler = new SaveGameHandler(game, this);
        tsHandler = new TitleScreenHandler(game, this);

        // used to track whether UI update is needed for daily action buttons
        dailyTasksButtons = new ArrayList<>();

        // create the login page
        login();

        window.setVisible(true);
    }

    /**
     * Given many panels will be reused. It will be created at the start, and turned on and off throughout the game.
     */
    public void createAllChangablePanels(){

        // save panel: save button
        savePanel = new JPanel();
        savePanel.setBounds(1300, 500, 200, 100);
        uiHelper.setPanelHelper(savePanel, con);
        uiHelper.setButtonHelper(new JButton("Save"), Helper.normalFont, saveGameHandler, savePanel, null);

        // used as the navigation bars
        mapPanel = new JPanel();
        mapPanel.setBounds(400, 100, 600, 50);
        mapPanel.setLayout(new GridLayout(1, 3));

        uiHelper.setButtonHelper(new JButton("Village"), Helper.normalFont, mapNavigationHandler, mapPanel, "village");
        uiHelper.setButtonHelper(new JButton("User Action"), Helper.normalFont, mapNavigationHandler, mapPanel, "action");
        uiHelper.setButtonHelper(new JButton("Tradecart"), Helper.normalFont, mapNavigationHandler, mapPanel, "tradecart");
        uiHelper.setPanelHelper(mapPanel,con);

        // the user daily actions
        userActionPanel = new JPanel();
        userActionPanel.setBounds(400, 200, 200,350);
        uiHelper.setPanelHelper(userActionPanel,con);
        userActionPanel.setLayout(new GridLayout(8, 1));

        // a list of possible actions to choose from
        uiHelper.setButtonHelper(new JButton("Wood"), Helper.normalFont, userActionHandler, userActionPanel, "gatherWood");
        uiHelper.setButtonHelper(new JButton("Food"), Helper.normalFont, userActionHandler, userActionPanel, "gatherFood");
        uiHelper.setButtonHelper(new JButton("Meat"), Helper.normalFont, userActionHandler, userActionPanel, "gatherMeat");
        uiHelper.setButtonHelper(new JButton("Rock"), Helper.normalFont, userActionHandler, userActionPanel, "gatherRocks");
        uiHelper.setButtonHelper(new JButton("Water"), Helper.normalFont, userActionHandler, userActionPanel, "gatherWater");
        uiHelper.setButtonHelper(new JButton("Clothes"), Helper.normalFont, userActionHandler, userActionPanel, "gatherClothes");
        uiHelper.setButtonHelper(new JButton("Fur"), Helper.normalFont, userActionHandler, userActionPanel, "gatherFur");
        uiHelper.setButtonHelper(new JButton("Gold"), Helper.normalFont, userActionHandler, userActionPanel, "gatherGold");

        // the list of building buttons
        buildingButtonPanel = new JPanel();
        buildingButtonPanel.setBounds(400, 200, 200, 400);
        uiHelper.setPanelHelper(buildingButtonPanel,con);
        buildingButtonPanel.setLayout(new GridLayout(9,1));

        // a list of all the buildings possible
        uiHelper.setButtonHelper(new JButton("Hut"), Helper.normalFont, choiceHandler, buildingButtonPanel, "hut");
        uiHelper.setButtonHelper(new JButton("SmokeHouse"), Helper.normalFont, choiceHandler, buildingButtonPanel, "smokehouse");
        uiHelper.setButtonHelper(new JButton("Mines"), Helper.normalFont, choiceHandler, buildingButtonPanel, "mines");
        uiHelper.setButtonHelper(new JButton("Factory"), Helper.normalFont, choiceHandler, buildingButtonPanel, "factory");
        uiHelper.setButtonHelper(new JButton("Blacksmith"), Helper.normalFont, choiceHandler, buildingButtonPanel, "blacksmith");
        uiHelper.setButtonHelper(new JButton("Bucket"), Helper.normalFont, choiceHandler, buildingButtonPanel, "bucket");
        uiHelper.setButtonHelper(new JButton("Trap"), Helper.normalFont, choiceHandler, buildingButtonPanel, "trap");
        uiHelper.setButtonHelper(new JButton("Gold Mine"), Helper.normalFont, choiceHandler, buildingButtonPanel, "gold mine");
        uiHelper.setButtonHelper(new JButton("Tradecart"), Helper.normalFont, choiceHandler, buildingButtonPanel, "tradecart");

        // the list of people panel
        peoplePanel = new JPanel();
        peoplePanel.setBounds(650, 200, 200, 600);
        uiHelper.setPanelHelper(peoplePanel,con);
        peoplePanel.setLayout(new GridLayout(game.getJobMap().size(),1));

        // the list of current assignments for the people
        numberPanel = new JPanel();
        numberPanel.setBounds(850, 200, 80, 600);
        numberPanel.setLayout(new GridLayout(game.getJobMap().size(), 1));
        uiHelper.setPanelHelper(numberPanel,con);

        // the list of arrows right next to the people number to add/subtract people
        arrowPanel = new JPanel();
        arrowPanel.setBounds(930, 200, 50, 550);
        arrowPanel.setLayout(new GridLayout((game.getJobMap().size()-1)*2, 1 ));
        uiHelper.setPanelHelper(arrowPanel, con);

        // adding the arrows buttons, number button (only used so it has a table look), and people buttons
        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                uiHelper.setButtonHelper(new JButton(elements.getKey().toString()), Helper.normalFont, null, peoplePanel, null);
                uiHelper.setButtonHelper(new JButton(elements.getValue().toString()), Helper.normalFont, null, numberPanel, null);
                uiHelper.setButtonHelper(new JButton(">"), Helper.arrowFont, villagerAssignmentHandler, arrowPanel, elements.getKey().toString()+"_up");
                uiHelper.setButtonHelper(new JButton("<"), Helper.arrowFont, villagerAssignmentHandler, arrowPanel, elements.getKey().toString()+"_down");
            }
        }
        // make sure the villager is always at the end.
        uiHelper.setButtonHelper(new JButton("Villager"), Helper.normalFont, null, peoplePanel, null);
        uiHelper.setButtonHelper(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), Helper.normalFont, null, numberPanel, null);

        // the panel for current planned daily task
        dailyTaskPanel = new JPanel();
        dailyTaskPanel.setBounds(700, 200, 200, 500);
        dailyTaskInfo = new JTextArea("Daily TODO's ");
        dailyTaskInfo.setBounds(700, 200, 200, 500);
        uiHelper.setTextAreaHelper(dailyTaskInfo, Helper.normalFont, dailyTaskPanel);
        uiHelper.setPanelHelper(dailyTaskPanel, con);

        // the panel for the next day button
        nextDayPanel = new JPanel();
        nextDayPanel.setBounds(600, 900, 200, 100);

        // the next day button
        uiHelper.setButtonHelper(new JButton("Next Day"), Helper.normalFont, nextDayHandler, nextDayPanel, null);
        uiHelper.setPanelHelper(nextDayPanel, con);

        // panel to display message on tradecart
        tradeCartInfoPanel = new JPanel();
        tradeCartInfoPanel.setBounds(300, 200, 700, 100);
        tradeCartInfo = new JTextArea("Want anything? Resource: 5 gold. Magic item: 50 gold");
        tradeCartInfo.setBounds(300, 200, 700,  100);
        uiHelper.setTextAreaHelper(tradeCartInfo, Helper.normalFont, tradeCartInfoPanel);
        uiHelper.setPanelHelper(tradeCartInfoPanel, con);

        // panel to display a list of resources that they can buy
        cartItemsPanel = new JPanel();
        cartItemsPanel.setBounds(700, 300, 200, 500);
        uiHelper.setPanelHelper(cartItemsPanel, con);

        // panel to display a list of magic items that they can buy
        magicItemPanel = new JPanel();
        magicItemPanel.setBounds(300, 300, 200, 500);
        uiHelper.setPanelHelper(magicItemPanel, con);

        // the alert message panel
        alertPanel = new JPanel();
        alertPanel.setBounds(200, 200, 600, 600);
        uiHelper.setPanelHelper(alertPanel, con);
        setAlertMessage("PlaceHolder");
        uiHelper.setPanelHelper(alertPanel, con);

        // the continue button panel
        alertButtonPanel = new JPanel();
        alertButtonPanel.setBounds(600, 800, 300, 200);
        uiHelper.setPanelHelper(alertButtonPanel, con);
        uiHelper.setButtonHelper(new JButton("Continue"), Helper.normalFont, continueAlertHandler, alertButtonPanel, null);

    }

    /**
     * This class is called whenever the player navigates to the tradecart menu.
     * Every day, the item in the cart will be updated.
     */
    public void createTradecartItems(){
        // update the items on a new day
        if(newDay){
            game.setDailyItems();
            game.setDailyMagicItems();
        }
        // update the items to reflect the purchase and/or new day
        cartItemsPanel.removeAll();
        magicItemPanel.removeAll();

        // a list of resource item buttons
        ArrayList<Enum.resourceType> cartResourceItems = game.getCartResourceItemList();
        for(int i = 0; i < cartResourceItems.size(); i++){
            uiHelper.setButtonHelper(new JButton(cartResourceItems.get(i).toString()), Helper.normalFont, tradeCartItemHandler, cartItemsPanel, "delete_"+cartResourceItems.get(i).toString());
        }

        // a list of magic item buttons
        ArrayList<Enum.magicItems> magicItemsArrayList = game.getMagicItemList();
        for(int i = 0; i < magicItemsArrayList.size(); i++){
            uiHelper.setButtonHelper(new JButton(magicItemsArrayList.get(i).toString()), Helper.normalFont, tradeCartItemHandler, magicItemPanel, "delete_"+magicItemsArrayList.get(i).toString());
        }

        // setting the correct visibility
        cartItemsPanel.setVisible(true);
        tradeCartInfoPanel.setVisible(true);
        magicItemPanel.setVisible(true);
        newDay = false;
    }

    /**
     * This function repaints the UI for job assignment. The numbers for each job could change with each arrow click. Therefore, we are updating the number count.
     */
    public void jobRepaint(){

        // update the buttons in number panel
        numberPanel.removeAll();
        numberPanel.revalidate();
        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                uiHelper.setButtonHelper(new JButton(elements.getValue().toString()), Helper.normalFont, null, numberPanel, null);
            }
        }
        uiHelper.setButtonHelper(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), Helper.normalFont, null, numberPanel, null);
        numberPanel.repaint();
    }

    /**
     * This function repaints the daily planned tasks to be reflective
     */
    public void dailyTasksScreen(){
        dailyTaskPanel.setVisible(true);
        userActionPanel.setVisible(true);

        JButton temp;
        ArrayList<Enum.resourceType> agenda = game.getDailyAgenda();

        // check if there are buttons has been added/removed
        if(dailyTasksButtons.size() != agenda.size()) {
            for (Enum.resourceType resourceType : agenda) {
                temp = new JButton(resourceType.toString());
                dailyTasksButtons.add(temp);
                uiHelper.setButtonHelper(temp, Helper.normalFont, deleteAgendaHandler, dailyTaskPanel, "delete_" + resourceType.toString());
            }
        }
    }

    /**
     * This function removes the daily planned tasks and be reflective
     */
    public void removeTaskButtons(){
        while(dailyTasksButtons.size() > 0){
            dailyTaskPanel.remove(dailyTasksButtons.get(0));
            dailyTasksButtons.remove(0);
        }
    }

    /**
     * The home/login page.
     */
    public void login(){

        // The login page
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 300, 300, 300);
        uiHelper.setPanelHelper(startButtonPanel, con);

        // the textfield for them to add userName. Default: Username
        userName = new JTextField("Username", 10);
        userName.setBounds(400, 300, 300, 100);
        uiHelper.setTextFieldHelper(userName, Helper.normalFont, startButtonPanel);

        // add the buttons for new user and returning user
        uiHelper.setButtonHelper(new JButton("New User"), Helper.normalFont, tsHandler, startButtonPanel, "newUser");
        uiHelper.setButtonHelper(new JButton("Returning User"), Helper.normalFont, tsHandler, startButtonPanel, "returningUser");

        // title panel
        titlePanel = new JPanel();
        titlePanel.setBounds(350,100,800,150);

        // title label
        titleLabel = new JLabel("Defend Your Village");
        uiHelper.setPanelHelper(titlePanel, con);
        uiHelper.setLabelHelper(titleLabel, Helper.titleFont);
        titlePanel.add(titleLabel);

        startButtonPanel.setVisible(true);
        titlePanel.setVisible(true);
    }

    /**
     * This function writes to the event announcer
     * @param events: the list of events to include in string
     */
    public void setEventAnnouncerPanel(String events){
        eventAnnouncerPanel = new JPanel();
        eventAnnouncerPanel.setBounds(50, 100, 300, 1000);
        announcer = new JTextArea(events);
        announcer.setBounds(50, 100, 300, 1000);
        announcer.setEditable(false);
        announcer.setLineWrap(true);
        announcer.setWrapStyleWord(true);
        uiHelper.setTextAreaHelper(announcer, Helper.announcerFont, eventAnnouncerPanel);
        uiHelper.setPanelHelper(eventAnnouncerPanel, con);
    }

    /**
     * This class contains everything that needs to be updated no matter which map the player is on
     */
    public void gamePlayScreen(){

        // the resource panel that display all resource
        resourcesPanel = new JPanel();
        resourcesPanel.setBounds(1000, 100, 300, 300);
        uiHelper.setPanelHelper(resourcesPanel, con);
        resources = new JTextArea();
        resources.setBounds(1000,100,300,300);

        // prints all resource info
        resources.setText("Resources \t Amount\n");
        String tempString;
        for(Map.Entry<Enum.resourceType, Integer> elements: game.getResourceMap().entrySet()){
            tempString = elements.getKey().toString();
            resources.append(tempString.substring(0,1).toUpperCase() + tempString.substring(1) + "\t" + elements.getValue() + "\n");
        }
        uiHelper.setTextAreaHelper(resources, Helper.normalFont, resourcesPanel);

        // the building panel that displays all building
        buildingPanel = new JPanel();
        buildingPanel.setBounds(1000, 500, 300, 300);
        uiHelper.setPanelHelper(buildingPanel, con);
        buildings = new JTextArea();
        buildings.setBounds(1000,500,300,300);

        // prints all building info
        buildings.setText("Building\t Amount\n");
        for(Map.Entry<Enum.buildingType, Integer> elements: game.getBuildingMap().entrySet()){
            buildings.append(elements.getKey() + "\t" + elements.getValue() + "\n");
        }
        uiHelper.setTextAreaHelper(buildings, Helper.normalFont, buildingPanel);

        // the health panel that prints health status such as defense and health
        healthPanel = new JPanel();
        healthPanel.setBounds(1300, 100, 300, 300);
        uiHelper.setPanelHelper(healthPanel, con);

        // prints the info
        health = new JTextArea();
        health.setBounds(1300,100,300,300);
        for(Map.Entry<Enum.stats, Integer> elements: game.getStatsMap().entrySet()){
            tempString = elements.getKey().toString();
            health.append(tempString.substring(0,1).toUpperCase() + tempString.substring(1) + "\t" + elements.getValue() + "\n");
        }
        uiHelper.setTextAreaHelper(health, Helper.normalFont, healthPanel);

    }

    /**
     * This function includes all the updates that needs to happen daily, such as resource, health, and building
     */
    public void dailyRepaint(){
        // remove the components
        con.remove(resourcesPanel);
        con.remove(buildingPanel);
        con.remove(healthPanel);
        map();

        // recreate them
        gamePlayScreen();
        jobRepaint();
        con.revalidate();
        con.repaint();
    }

    /**
     * This function checks the location and calls the appropriate functions
     */
    public void map(){
        switch (mapLocation){
            case village:
                resetAllPanel();
                villageButtons();
                break;
            case action:
                resetAllPanel();
                dailyTasksScreen();
                break;
            case tradecart:
                resetAllPanel();
                createTradecartItems();
                break;
            default:
                System.out.println("The map navigation is incorrect");
        }
    }

    /**
     * This function stops all panels so it is quicker reset
     */
    public void resetAllPanel(){
        tradeCartInfoPanel.setVisible(false);
        buildingButtonPanel.setVisible(false);
        peoplePanel.setVisible(false);
        numberPanel.setVisible(false);
        arrowPanel.setVisible(false);
        userActionPanel.setVisible(false);
        dailyTaskPanel.setVisible(false);
        cartItemsPanel.setVisible(false);
        magicItemPanel.setVisible(false);
        alertPanel.setVisible(false);
        alertButtonPanel.setVisible(false);
        mapPanel.setVisible(true);
        nextDayPanel.setVisible(true);
        savePanel.setVisible(true);
    }

    /**
     * The panels that are needed for the home page
     */
    public void villageButtons(){
        buildingButtonPanel.setVisible(true);
        peoplePanel.setVisible(true);
        numberPanel.setVisible(true);
        arrowPanel.setVisible(true);
    }

    /**
     * This function is used for the alert messages after each day
     */
    public void alert(){
        // the message from the command pattern
        String message = game.randomEvents();
        resetAllPanel();

        // update the message
        setAlertMessage(message);

        // set the visibility of other panels
        mapPanel.setVisible(false);
        nextDayPanel.setVisible(false);
        savePanel.setVisible(false);
        alertPanel.setVisible(true);
        alertButtonPanel.setVisible(true);
    }

    /**
     * This function create the alert message after each day
     * @param message
     */
    public void setAlertMessage(String message){
        // remove previous alert message
        alertPanel.removeAll();
        alertMessage = new JTextArea(message);
        alertMessage.setEditable(false);
        alertMessage.setLineWrap(true);
        alertMessage.setWrapStyleWord(true);
        alertMessage.setBounds(200,200, 600, 600);
        uiHelper.setTextAreaHelper(alertMessage, Helper.normalFont, alertPanel);
    }

    /**
     * This is part of the observer pattern. After each notify, it will construct the string that we are displaying on the event announcer and call the appropriate methods
     * @param event
     */
    @Override
    public void update(String event) {
        // gets the list of events from logger
        ArrayList<String> events = logger.getEvents();
        StringBuilder publishingEvents = new StringBuilder();
        for(int i = events.size()-1; i >= 0; i--){
            publishingEvents.append(events.get(i) + "\n");
        }

        // recreate teh event announcer
        con.remove(eventAnnouncerPanel);
        setEventAnnouncerPanel(publishingEvents.toString());
        con.revalidate();
        con.repaint();
    }

    /**
     * helper function to interact with the action handler for map navigation
     * @param type
     */
    public void setMapLocation(Enum.mapLocationType type){
        mapLocation = type;
    }

    /**
     * helper function to repaint the daily task panel every time
     */
    public void dailyTaskPanelRepaint(){
        dailyTaskPanel.repaint();
    }

    /**
     * helper funciton to set the new day variable
     * @param bool
     */
    public void setNewDay(boolean bool){
        newDay = bool;
    }

    /**
     * helper funtion to get the username
     * @return
     */
    public JTextField getUserName() {
        return userName;
    }

    /**
     * Helper function to set the username
     * @param name
     */
    public void setUserName(String name){
        userName.setText(name);
    }

    /**
     * Helper function for title screen
     */
    public void titleScreenHelper(){
        con.remove(titlePanel);
        con.remove(startButtonPanel);
    }
}
