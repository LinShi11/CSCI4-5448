import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * citation: use https://www.ryisnow.online/2021/04/java-for-beginner-text-adventure-game.html as reference to jFrame start ups
 */
public class GameUI implements Observer{
    JFrame window;
    Container con;

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel, peoplePanel, numberPanel, arrowPanel, healthPanel, mapPanel,
            userActionPanel, dailyTaskPanel, nextDayPanel, cartItemsPanel, magicItemPanel, tradeCartInfoPanel, savePanel, alertPanel, alertButtonPanel;
    JLabel titleLabel;
    JTextField userName;
    JButton villageButton, actionButton, tradecartMenu;
    JTextArea announcer, resources, buildings, health, dailyTaskInfo, tradeCartInfo, alertMessage;
    ActionListener tsHandler = new TitleScreenHandler();
    ActionListener choiceHandler = new ChoiceHandler();
    ActionListener userActionHandler = new UserActionHandler();
    ActionListener mapNavigationHandler = new MapNavigationHandler();
    ActionListener villagerAssignmentHandler = new VillagerAssignmentHandler();
    ActionListener deleteAgendaHandler = new DeleteAgendaHandler();
    ActionListener tradeCartItemHandler = new TradeCartItemHandler();
    ActionListener nextDayHandler = new NextDayHandler();
    ActionListener saveGameHandler = new SaveGameHandler();
    ActionListener continueAlertHandler = new ContinueAlertHandler();
    Game game;
    Enum.mapLocationType mapLocation;
    ArrayList<JButton> dailyTasksButtons, tradeCartResourceButtons, tradeCartMagicButtons;
    Logger logger;
    boolean newDay;
    public GameUI(Game tempGame){
        game = tempGame;
        window = new JFrame();
        window.setSize(1600, 1200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();
        mapLocation = Enum.mapLocationType.village;
        newDay = true;

        dailyTasksButtons = new ArrayList<>();
        tradeCartMagicButtons = new ArrayList<>();
        tradeCartResourceButtons = new ArrayList<>();

        login();


        window.setVisible(true);
        logger = Logger.getInstance();
    }

    public void createAllChangablePanels(){

        savePanel = new JPanel();
        savePanel.setBounds(1300, 500, 200, 100);
        setPanelHelper(savePanel);
        setButtonHelper(new JButton("Save"), Helper.normalFont, saveGameHandler, savePanel, null);


        mapPanel = new JPanel();
        mapPanel.setBounds(400, 100, 600, 50);

        mapPanel.setLayout(new GridLayout(1, 3));

        villageButton = new JButton("Village");
        setButtonHelper(villageButton, Helper.normalFont, mapNavigationHandler, mapPanel, "village");
        actionButton = new JButton("User Action");
        setButtonHelper(actionButton, Helper.normalFont, mapNavigationHandler, mapPanel, "action");
        tradecartMenu = new JButton("Tradecart");
        setButtonHelper(tradecartMenu, Helper.normalFont, mapNavigationHandler, mapPanel, "tradecart");
        setPanelHelper(mapPanel);


        userActionPanel = new JPanel();
        userActionPanel.setBounds(400, 200, 200,350);
        setPanelHelper(userActionPanel);
        userActionPanel.setLayout(new GridLayout(8, 1));

        setButtonHelper(new JButton("Wood"), Helper.normalFont, userActionHandler, userActionPanel, "gatherWood");
        setButtonHelper(new JButton("Food"), Helper.normalFont, userActionHandler, userActionPanel, "gatherFood");
        setButtonHelper(new JButton("Meat"), Helper.normalFont, userActionHandler, userActionPanel, "gatherMeat");
        setButtonHelper(new JButton("Rock"), Helper.normalFont, userActionHandler, userActionPanel, "gatherRocks");
        setButtonHelper(new JButton("Water"), Helper.normalFont, userActionHandler, userActionPanel, "gatherWater");
        setButtonHelper(new JButton("Clothes"), Helper.normalFont, userActionHandler, userActionPanel, "gatherClothes");
        setButtonHelper(new JButton("Fur"), Helper.normalFont, userActionHandler, userActionPanel, "gatherFur");
        setButtonHelper(new JButton("Gold"), Helper.normalFont, userActionHandler, userActionPanel, "gatherGold");

        buildingButtonPanel = new JPanel();
        buildingButtonPanel.setBounds(400, 200, 200, 400);
        setPanelHelper(buildingButtonPanel);

        buildingButtonPanel.setLayout(new GridLayout(9,1));

        setButtonHelper(new JButton("Hut"), Helper.normalFont, choiceHandler, buildingButtonPanel, "hut");
        setButtonHelper(new JButton("SmokeHouse"), Helper.normalFont, choiceHandler, buildingButtonPanel, "smokehouse");
        setButtonHelper(new JButton("Mines"), Helper.normalFont, choiceHandler, buildingButtonPanel, "mines");
        setButtonHelper(new JButton("Factory"), Helper.normalFont, choiceHandler, buildingButtonPanel, "factory");
        setButtonHelper(new JButton("Blacksmith"), Helper.normalFont, choiceHandler, buildingButtonPanel, "blacksmith");
        setButtonHelper(new JButton("Bucket"), Helper.normalFont, choiceHandler, buildingButtonPanel, "bucket");
        setButtonHelper(new JButton("Trap"), Helper.normalFont, choiceHandler, buildingButtonPanel, "trap");
        setButtonHelper(new JButton("Gold Mine"), Helper.normalFont, choiceHandler, buildingButtonPanel, "gold mine");
        setButtonHelper(new JButton("Tradecart"), Helper.normalFont, choiceHandler, buildingButtonPanel, "tradecart");

        peoplePanel = new JPanel();
        peoplePanel.setBounds(650, 200, 200, 600);
        setPanelHelper(peoplePanel);
        peoplePanel.setLayout(new GridLayout(game.getJobMap().size(),1));

        numberPanel = new JPanel();
        numberPanel.setBounds(850, 200, 80, 600);
        numberPanel.setLayout(new GridLayout(game.getJobMap().size(), 1));
        setPanelHelper(numberPanel);

        arrowPanel = new JPanel();
        arrowPanel.setBounds(930, 200, 50, 550);
        arrowPanel.setLayout(new GridLayout((game.getJobMap().size()-1)*2, 1 ));
        setPanelHelper(arrowPanel);

        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                setButtonHelper(new JButton(elements.getKey().toString()), Helper.normalFont, null, peoplePanel, null);
                setButtonHelper(new JButton(elements.getValue().toString()), Helper.normalFont, null, numberPanel, null);
                setButtonHelper(new JButton(">"), Helper.arrowFont, villagerAssignmentHandler, arrowPanel, elements.getKey().toString()+"_up");
                setButtonHelper(new JButton("<"), Helper.arrowFont, villagerAssignmentHandler, arrowPanel, elements.getKey().toString()+"_down");
            }
        }
        setButtonHelper(new JButton("Villager"), Helper.normalFont, null, peoplePanel, null);
        setButtonHelper(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), Helper.normalFont, null, numberPanel, null);

        dailyTaskPanel = new JPanel();
        dailyTaskPanel.setBounds(700, 200, 200, 500);
        dailyTaskInfo = new JTextArea("Daily TODO's ");
        dailyTaskInfo.setBounds(700, 200, 200, 500);
        textColorHelper(dailyTaskInfo);

        dailyTaskPanel.add(dailyTaskInfo);
        setPanelHelper(dailyTaskPanel);

        nextDayPanel = new JPanel();
        nextDayPanel.setBounds(600, 900, 200, 100);

        setButtonHelper(new JButton("Next Day"), Helper.normalFont, nextDayHandler, nextDayPanel, null);
        setPanelHelper(nextDayPanel);

        tradeCartInfoPanel = new JPanel();
        tradeCartInfoPanel.setBounds(300, 200, 700, 100);
        tradeCartInfo = new JTextArea("Want anything? Resource: 5 gold. Magic item: 50 gold");
        tradeCartInfo.setBounds(300, 200, 700,  100);
        textColorHelper(tradeCartInfo);
        tradeCartInfoPanel.add(tradeCartInfo);
        setPanelHelper(tradeCartInfoPanel);


        cartItemsPanel = new JPanel();
        cartItemsPanel.setBounds(700, 300, 200, 500);
        setPanelHelper(cartItemsPanel);

        magicItemPanel = new JPanel();
        magicItemPanel.setBounds(300, 300, 200, 500);
        setPanelHelper(magicItemPanel);

        alertPanel = new JPanel();
        alertPanel.setBounds(200, 200, 600, 600);
        setPanelHelper(alertPanel);
        setAlertMessage("PlaceHolder");
        setPanelHelper(alertPanel);

        alertButtonPanel = new JPanel();
        alertButtonPanel.setBounds(600, 800, 300, 200);
        setPanelHelper(alertButtonPanel);
        setButtonHelper(new JButton("Continue"), Helper.normalFont, continueAlertHandler, alertButtonPanel, null);


    }

    public void createTradecartItems(){
        if(newDay){
            game.setDailyItems();
            game.setDailyMagicItems();
        }
        cartItemsPanel.removeAll();
        magicItemPanel.removeAll();

        ArrayList<Enum.resourceType> cartResourceItems = game.getCartResourceItemList();
        for(int i = 0; i < cartResourceItems.size(); i++){
            setButtonHelper(new JButton(cartResourceItems.get(i).toString()), Helper.normalFont, tradeCartItemHandler, cartItemsPanel, "delete_"+cartResourceItems.get(i).toString());
        }
        ArrayList<Enum.magicItems> magicItemsArrayList = game.getMagicItemList();
        for(int i = 0; i < magicItemsArrayList.size(); i++){
            setButtonHelper(new JButton(magicItemsArrayList.get(i).toString()), Helper.normalFont, tradeCartItemHandler, magicItemPanel, "delete_"+magicItemsArrayList.get(i).toString());
        }
        cartItemsPanel.setVisible(true);
        tradeCartInfoPanel.setVisible(true);
        magicItemPanel.setVisible(true);
        newDay = false;

    }

    public void jobRepaint(){
        numberPanel.removeAll();
        numberPanel.revalidate();
        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                setButtonHelper(new JButton(elements.getValue().toString()), Helper.normalFont, null, numberPanel, null);
            }
        }
        setButtonHelper(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), Helper.normalFont, null, numberPanel, null);
        numberPanel.repaint();
    }

    public void dailyTasksScreen(){
        dailyTaskPanel.setVisible(true);
        JButton temp;
        ArrayList<Enum.resourceType> agenda = game.getDailyAgenda();
        if(dailyTasksButtons.size() != agenda.size()) {
            for (int i = 0; i < agenda.size(); i++) {
                temp = new JButton(agenda.get(i).toString());
                dailyTasksButtons.add(temp);
                setButtonHelper(temp, Helper.normalFont, deleteAgendaHandler, dailyTaskPanel, "delete_" + agenda.get(i).toString());
            }
        }
    }

    public void removeTaskButtons(){
        while(dailyTasksButtons.size() > 0){
            dailyTaskPanel.remove(dailyTasksButtons.get(0));
            dailyTasksButtons.remove(0);
        }
    }
    public void login(){

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 300, 300, 300);
        setPanelHelper(startButtonPanel);

        userName = new JTextField("Username", 10);
        userName.setBounds(400, 300, 300, 100);
        userName.setFont(Helper.normalFont);
        userName.setBackground(Color.black);
        userName.setForeground(Color.white);
        startButtonPanel.add(userName);

        setButtonHelper(new JButton("New User"), Helper.normalFont, tsHandler, startButtonPanel, "newUser");
        setButtonHelper(new JButton("Returning User"), Helper.normalFont, tsHandler, startButtonPanel, "returningUser");


        titlePanel = new JPanel();
        titlePanel.setBounds(350,100,800,150);

        titleLabel = new JLabel("Defend Your Village");
        setPanelHelper(titlePanel);
        setLabelHelper(titleLabel, Helper.titleFont);
        titlePanel.add(titleLabel);

        startButtonPanel.setVisible(true);
        titlePanel.setVisible(true);


    }
    public void setEventAnnouncerPanel(String events){
        eventAnnouncerPanel = new JPanel();
        eventAnnouncerPanel.setBounds(50, 100, 300, 1000);
        announcer = new JTextArea(events);
        announcer.setBounds(50, 100, 300, 1000);
        setTextAreaColor(announcer, Helper.announcerFont);
        announcer.setFont(Helper.announcerFont);
        announcer.setEditable(false);
        announcer.setLineWrap(true);
        announcer.setWrapStyleWord(true);
        eventAnnouncerPanel.add(announcer);
        setPanelHelper(eventAnnouncerPanel);
    }
    public void gamePlayScreen(){

        resourcesPanel = new JPanel();
        resourcesPanel.setBounds(1000, 100, 300, 300);
        setPanelHelper(resourcesPanel);
        resources = new JTextArea();

        resources.setBounds(1000,100,300,300);

        resources.setText("Resources \t Amount\n");
        String tempString;
        for(Map.Entry<Enum.resourceType, Integer> elements: game.getResourceMap().entrySet()){
            tempString = elements.getKey().toString();
            resources.append(tempString.substring(0,1).toUpperCase() + tempString.substring(1) + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(resources);
        resourcesPanel.add(resources);

        buildingPanel = new JPanel();
        buildingPanel.setBounds(1000, 500, 300, 300);
        setPanelHelper(buildingPanel);

        buildings = new JTextArea();
        buildings.setBounds(1000,500,300,300);

        buildings.setText("Building\t Amount\n");
        for(Map.Entry<Enum.buildingType, Integer> elements: game.getBuildingMap().entrySet()){
            buildings.append(elements.getKey() + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(buildings);
        buildingPanel.add(buildings);

        healthPanel = new JPanel();
        healthPanel.setBounds(1300, 100, 300, 300);
        setPanelHelper(healthPanel);

        health = new JTextArea();
        health.setBounds(1300,100,300,300);
        for(Map.Entry<Enum.stats, Integer> elements: game.getStatsMap().entrySet()){
            tempString = elements.getKey().toString();
            health.append(tempString.substring(0,1).toUpperCase() + tempString.substring(1) + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(health);
        healthPanel.add(health);


    }

    public void dailyRepaint(){
        con.remove(resourcesPanel);
        con.remove(buildingPanel);
        con.remove(healthPanel);
        map();
        gamePlayScreen();
        jobRepaint();
        con.revalidate();
        con.repaint();
    }


    public void map(){
        switch (mapLocation){
            case village:
                stopAllButton();
                villageButtons();
                break;
            case action:
                stopAllButton();
                userActions();
                dailyTasksScreen();
                break;
            case tradecart:
                stopAllButton();
                createTradecartItems();
                break;
            default:
                System.out.println("The map navigation is incorrect");
        }
    }

    public void userActions(){
        userActionPanel.setVisible(true);
    }

    public void stopAllButton(){
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

    public void villageButtons(){
        buildingButtonPanel.setVisible(true);
        peoplePanel.setVisible(true);
        numberPanel.setVisible(true);
        arrowPanel.setVisible(true);
    }

    public void alert(){
        String message = game.randomEvents();
        stopAllButton();
        setAlertMessage(message);
        mapPanel.setVisible(false);
        nextDayPanel.setVisible(false);
        savePanel.setVisible(false);
        alertPanel.setVisible(true);
        alertButtonPanel.setVisible(true);
    }

    public void setAlertMessage(String message){
        alertPanel.removeAll();
        alertMessage = new JTextArea(message);
        alertMessage.setEditable(false);
        alertMessage.setLineWrap(true);
        alertMessage.setWrapStyleWord(true);
        alertMessage.setBounds(200,200, 600, 600);
        setTextAreaColor(alertMessage, Helper.normalFont);
        alertPanel.add(alertMessage);
    }

    public void textColorHelper(JTextArea area){
        setTextAreaColor(area, Helper.normalFont);
        area.setEditable(false);
    }

    @Override
    public void update(String event) {
        ArrayList<String> events = logger.getEvents();
        StringBuilder publishingEvents = new StringBuilder();
        for(int i = events.size()-1; i >= 0; i--){
            publishingEvents.append(events.get(i) + "\n");
        }
        con.remove(eventAnnouncerPanel);
        setEventAnnouncerPanel(publishingEvents.toString());
        con.revalidate();
        con.repaint();
    }

    public class ContinueAlertHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            stopAllButton();
            dailyRepaint();
            map();
        }
    }

    public class SaveGameHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                game.saveGame();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event){
            String command = event.getActionCommand();
            if(Objects.equals(command, "newUser")){
                game.createData();
                createAllChangablePanels();
                game.saveName(userName.getText());
                setEventAnnouncerPanel("Welcome to the game");
                con.remove(titlePanel);
                con.remove(startButtonPanel);
                gamePlayScreen();
                map();
            } else if(Objects.equals(command, "returningUser")){
                System.out.println("returning user");
                if(game.checkUserName(userName.getText())){
                    game.loadData();
                    createAllChangablePanels();
                    game.saveName(userName.getText());
                    setEventAnnouncerPanel("Welcome to the game");
                    con.remove(titlePanel);
                    con.remove(startButtonPanel);
                    gamePlayScreen();
                    map();
                } else{
                    userName.setText("No file stored");
                }
            }

        }
    }

    public class NextDayHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.dailyUpdate();
            dailyRepaint();
            newDay = true;
            alert();
        }

    }

    public class TradeCartItemHandler implements ActionListener{
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
            dailyRepaint();
            map();
        }
    }

    public class DeleteAgendaHandler implements  ActionListener{
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
            removeTaskButtons();
            dailyTaskPanel.repaint();
            map();
        }
    }
    public class MapNavigationHandler implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();
            switch (choice) {
                case "village":
                    mapLocation = Enum.mapLocationType.village;
                    map();
                    break;
                case "action":
                    mapLocation = Enum.mapLocationType.action;
                    map();
                    break;
                case "tradecart":
                    if(game.getBuildingMap().get(Enum.buildingType.Tradecart) != 0) {
                        mapLocation = Enum.mapLocationType.tradecart;
                        map();
                    } else{
                        game.notifyObserver("A tradecart must be build before you can use it");
                    }
                    break;
                default:
                    System.out.println("I am not sure what you created");
            }

        }
    }

    public class UserActionHandler implements ActionListener{
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
            removeTaskButtons();
            dailyTaskPanel.repaint();
            map();
        }
    }

    public class VillagerAssignmentHandler implements ActionListener{
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
            jobRepaint();
        }
    }

    public class ChoiceHandler implements ActionListener{
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
            dailyRepaint();
        }
    }

    public void setPanelHelper(JPanel panel){
        panel.setBackground(Color.black);
        panel.setForeground(Color.white);
        con.add(panel);
    }

    public void setLabelHelper(JLabel label, Font font){
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(font);
    }

    public void setButtonHelper(JButton button, Font font, ActionListener actionListener, JPanel panel, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(font);
        if(command != null) {
            button.setActionCommand(command);
        }
        if(actionListener != null) {
            button.addActionListener(actionListener);
        }
        panel.add(button);
    }

    public void setTextAreaColor(JTextArea textArea, Font font){
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setFont(font);
    }
}
