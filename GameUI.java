import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * citation: use https://www.ryisnow.online/2021/04/java-for-beginner-text-adventure-game.html as reference to jFrame start ups
 */
public class GameUI implements Observer{
    JFrame window;
    Container con;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    Font arrowFont = new Font("Times New Roman", Font.PLAIN, 12);
    Font annoucerFont = new Font("Times New Roman", Font.PLAIN, 18);

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel, peoplePanel, numberPanel, arrowPanel, healthPanel, mapPanel,
            userActionPanel, dailyTaskPanel, nextDayPanel, cartItemsPanel, magicItemPanel, tradeCartInfoPanel;
    JLabel titleLabel;
    JButton villageButton, actionButton, tradecartMenu;
    JTextArea announcer, resources, buildings, health, dailyTaskInfo, tradeCartInfo;
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    UserActionHandler userActionHandler = new UserActionHandler();
    MapNavigationHandler mapNavigationHandler = new MapNavigationHandler();
    VillagerAssignmentHandler villagerAssignmentHandler = new VillagerAssignmentHandler();
    DeleteAgendaHandler deleteAgendaHandler = new DeleteAgendaHandler();
    TradeCartItemHandler tradeCartItemHandler = new TradeCartItemHandler();
    NextDayHandler nextDayHandler = new NextDayHandler();
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
        dailyTasksButtons = new ArrayList<>();
        tradeCartMagicButtons = new ArrayList<>();
        tradeCartResourceButtons = new ArrayList<>();

        createAllChangablePanels();
        stopAllButton();
        login();
        window.setVisible(true);
        logger = Logger.getInstance();
        newDay = true;

    }

    public void createAllChangablePanels(){
        titlePanel = new JPanel();
        titlePanel.setBounds(350,100,800,150);
        titlePanel.setBackground(Color.black);

        titleLabel = new JLabel("Defend Your Village");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 300, 200, 100);
        startButtonPanel.setBackground(Color.black);
        JButton temp;
        temp = new JButton("New User");
        temp.setBackground(Color.black);
        temp.setForeground(Color.white);
        temp.setFont(normalFont);
        temp.addActionListener(tsHandler);
        temp.setFocusPainted(false);

        titlePanel.add(titleLabel);
        startButtonPanel.add(temp);

        con.add(titlePanel);
        con.add(startButtonPanel);

        mapPanel = new JPanel();
        mapPanel.setBounds(400, 100, 600, 50);
        mapPanel.setBackground(Color.black);
        mapPanel.setLayout(new GridLayout(1, 3));

        villageButton = new JButton("Village");
        mapButtonAdd(villageButton, "village");
        actionButton = new JButton("User Action");
        mapButtonAdd(actionButton, "action");
        tradecartMenu = new JButton("Tradecart");
        mapButtonAdd(tradecartMenu, "tradecart");

        con.add(mapPanel);

        userActionPanel = new JPanel();
        userActionPanel.setBounds(400, 200, 200,350);
        userActionPanel.setBackground(Color.black);
        userActionPanel.setLayout(new GridLayout(8, 1));

        temp = new JButton("Wood");
        userActionButtons(temp, "gatherWood");
        temp = new JButton("Food");
        userActionButtons(temp, "gatherFood");
        temp = new JButton("Meat");
        userActionButtons(temp, "gatherMeat");
        temp = new JButton("Rock");
        userActionButtons(temp, "gatherRocks");
        temp = new JButton("Water");
        userActionButtons(temp, "gatherWater");
        temp = new JButton("Clothes");
        userActionButtons(temp, "gatherClothes");
        temp = new JButton("Fur");
        userActionButtons(temp, "gatherFur");
        temp = new JButton("Gold");
        userActionButtons(temp, "gatherGold");
        con.add(userActionPanel);

        buildingButtonPanel = new JPanel();
        buildingButtonPanel.setBounds(400, 200, 200, 400);
        buildingButtonPanel.setBackground(Color.black);
        buildingButtonPanel.setLayout(new GridLayout(9,1));
        con.add(buildingButtonPanel);

        temp = new JButton("Hut");
        buttonHelper(temp, "hut");
        temp = new JButton("SmokeHouse");
        buttonHelper(temp, "smokehouse");
        temp = new JButton("Mines");
        buttonHelper(temp, "mines");
        temp = new JButton("Factory");
        buttonHelper(temp, "factory");
        temp = new JButton("Blacksmith");
        buttonHelper(temp, "blacksmith");
        temp = new JButton("Bucket");
        buttonHelper(temp, "bucket");
        temp = new JButton("Trap");
        buttonHelper(temp, "trap");
        temp = new JButton("Gold Mine");
        buttonHelper(temp, "gold mine");
        temp = new JButton("Tradecart");
        buttonHelper(temp, "tradecart");

        peoplePanel = new JPanel();
        peoplePanel.setBounds(650, 200, 200, 600);
        peoplePanel.setBackground(Color.black);
        peoplePanel.setLayout(new GridLayout(game.getJobMap().size(),1));
        con.add(peoplePanel);

        numberPanel = new JPanel();
        numberPanel.setBounds(850, 200, 80, 600);
        numberPanel.setBackground(Color.black);
        numberPanel.setLayout(new GridLayout(game.getJobMap().size(), 1));
        con.add(numberPanel);

        arrowPanel = new JPanel();
        arrowPanel.setBounds(930, 200, 50, 550);
        arrowPanel.setBackground(Color.black);
        arrowPanel.setLayout(new GridLayout((game.getJobMap().size()-1)*2, 1 ));
        con.add(arrowPanel);

        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                temp = new JButton(elements.getKey().toString());
                noActionButtonAdd(temp, peoplePanel);
                temp = new JButton(elements.getValue().toString());
                noActionButtonAdd(temp, numberPanel);
                temp = new JButton(">");
                arrowButtonAdd(temp, elements.getKey().toString()+"_up");
                temp = new JButton("<");
                arrowButtonAdd(temp, elements.getKey().toString()+"_down");
            }
        }
        noActionButtonAdd(new JButton("Villager"), peoplePanel);
        noActionButtonAdd(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), numberPanel);

        dailyTaskPanel = new JPanel();
        dailyTaskPanel.setBackground(Color.black);
        dailyTaskPanel.setBounds(700, 200, 200, 500);
        dailyTaskInfo = new JTextArea("Daily TODO's ");
        dailyTaskInfo.setBounds(700, 200, 200, 500);
        textColorHelper(dailyTaskInfo);

        dailyTaskPanel.add(dailyTaskInfo);
        con.add(dailyTaskPanel);

        nextDayPanel = new JPanel();
        nextDayPanel.setBounds(600, 900, 200, 100);
        nextDayPanel.setBackground(Color.black);

        temp = new JButton("Next Day");
        temp.setBackground(Color.black);
        temp.setForeground(Color.white);
        temp.setFont(normalFont);
        temp.addActionListener(nextDayHandler);
        temp.setFocusPainted(false);
        nextDayPanel.add(temp);

        con.add(nextDayPanel);

        tradeCartInfoPanel = new JPanel();
        tradeCartInfoPanel.setBackground(Color.black);
        tradeCartInfoPanel.setBounds(300, 200, 700, 100);
        tradeCartInfo = new JTextArea("Want anything? Resource: 1 gold. Magic item: 50 gold");
        tradeCartInfo.setBounds(300, 200, 700,  100);
        textColorHelper(tradeCartInfo);
        tradeCartInfoPanel.add(tradeCartInfo);

        con.add(tradeCartInfoPanel);


        cartItemsPanel = new JPanel();
        cartItemsPanel.setBackground(Color.black);
        cartItemsPanel.setBounds(700, 300, 200, 500);
        con.add(cartItemsPanel);

        magicItemPanel = new JPanel();
        magicItemPanel.setBackground(Color.black);
        magicItemPanel.setBounds(300, 300, 200, 500);
        con.add(magicItemPanel);


    }

    public void createTradecartItems(){
        if(newDay){
            game.setDailyItems();
            game.setDailyMagicItems();
        }
        cartItemsPanel.removeAll();
        magicItemPanel.removeAll();

        JButton temp;
        ArrayList<Enum.resourceType> cartResourceItems = game.getCartResourceItemList();
        for(int i = 0; i < cartResourceItems.size(); i++){
            temp = new JButton(cartResourceItems.get(i).toString());
            addTradeCartItem(temp, "delete_"+cartResourceItems.get(i).toString(), cartItemsPanel);
        }
        ArrayList<Enum.magicItems> magicItemsArrayList = game.getMagicItemList();
        for(int i = 0; i < magicItemsArrayList.size(); i++){
            temp = new JButton(magicItemsArrayList.get(i).toString());
            addTradeCartItem(temp, "delete_"+magicItemsArrayList.get(i).toString(), magicItemPanel);
        }
        cartItemsPanel.setVisible(true);
        tradeCartInfoPanel.setVisible(true);
        magicItemPanel.setVisible(true);
        newDay = false;

    }

    public void addTradeCartItem(JButton button, String command, JPanel panel){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(tradeCartItemHandler);
        button.setActionCommand(command);
        panel.add(button);
    }

    public void jobRepaint(){
        numberPanel.removeAll();
        numberPanel.revalidate();
        JButton temp;
        for(Map.Entry<Enum.jobType, Integer> elements: game.getJobMap().entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager) {
                temp = new JButton(elements.getValue().toString());
                noActionButtonAdd(temp, numberPanel);
            }
        }
        noActionButtonAdd(new JButton(game.getJobMap().get(Enum.jobType.Villager).toString()), numberPanel);
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
                addDailyTasksButton(temp, "delete_" + agenda.get(i).toString());
            }
        }
    }

    public void removeTaskButtons(){
        while(dailyTasksButtons.size() > 0){
            dailyTaskPanel.remove(dailyTasksButtons.get(0));
            dailyTasksButtons.remove(0);
        }
    }

    public void addDailyTasksButton(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(deleteAgendaHandler);
        button.setActionCommand(command);
        dailyTaskPanel.add(button);
    }
    public void login(){
        mapPanel.setVisible(false);
        nextDayPanel.setVisible(false);
        startButtonPanel.setVisible(true);
        titlePanel.setVisible(true);
    }

    public void setEventAnnouncerPanel(String events){
        eventAnnouncerPanel = new JPanel();
        eventAnnouncerPanel.setBounds(50, 100, 300, 1000);
        eventAnnouncerPanel.setBackground(Color.black);
        announcer = new JTextArea(events);
        announcer.setBounds(50, 100, 300, 1000);
        announcer.setForeground(Color.white);
        announcer.setBackground(Color.black);
        announcer.setFont(annoucerFont);
        announcer.setEditable(false);
        announcer.setLineWrap(true);
        announcer.setWrapStyleWord(true);
        eventAnnouncerPanel.add(announcer);
        con.add(eventAnnouncerPanel);
    }
    public void gamePlayScreen(){

        resourcesPanel = new JPanel();
        resourcesPanel.setBounds(1000, 100, 300, 300);
        resourcesPanel.setBackground(Color.BLACK);

        con.add(resourcesPanel);
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
        buildingPanel.setBackground(Color.black);

        con.add(buildingPanel);

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
        healthPanel.setBackground(Color.black);

        con.add(healthPanel);

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
        titlePanel.setVisible(false);
        tradeCartInfoPanel.setVisible(false);
        startButtonPanel.setVisible(false);
        buildingButtonPanel.setVisible(false);
        peoplePanel.setVisible(false);
        numberPanel.setVisible(false);
        arrowPanel.setVisible(false);
        userActionPanel.setVisible(false);
        dailyTaskPanel.setVisible(false);
        cartItemsPanel.setVisible(false);
        magicItemPanel.setVisible(false);
        mapPanel.setVisible(true);
        nextDayPanel.setVisible(true);
    }

    public void villageButtons(){
        buildingButtonPanel.setVisible(true);
        peoplePanel.setVisible(true);
        numberPanel.setVisible(true);
        arrowPanel.setVisible(true);
    }

    public void userActionButtons(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(userActionHandler);
        button.setActionCommand(command);
        userActionPanel.add(button);
    }

    public void mapButtonAdd(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(mapNavigationHandler);
        button.setActionCommand(command);
        mapPanel.add(button);
    }

    public void arrowButtonAdd(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(arrowFont);
        button.setFocusPainted(false);
        button.addActionListener(villagerAssignmentHandler);
        button.setActionCommand(command);
        arrowPanel.add(button);
    }

    public void noActionButtonAdd(JButton button, JPanel panel){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        panel.add(button);
    }

    public void textColorHelper(JTextArea area){
        area.setForeground(Color.white);
        area.setBackground(Color.black);
        area.setFont(normalFont);
        area.setEditable(false);

    }

    public void buttonHelper(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand(command);
        buildingButtonPanel.add(button);
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

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event){
            setEventAnnouncerPanel("Welcome to the game");
            gamePlayScreen();
            map();
        }
    }

    public class NextDayHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.dailyUpdate();
            dailyRepaint();
            newDay = true;
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
                    mapLocation = Enum.mapLocationType.tradecart;
                    map();
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
}
