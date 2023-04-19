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
public class GameUI {
    JFrame window;
    Container con;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    Font arrowFont = new Font("Times New Roman", Font.PLAIN, 12);

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel, peoplePanel, numberPanel, arrowPanel, healthPanel, mapPanel, userActionPanel, dailyTaskPanel, nextDayPanel, cartItemsPanel, magicItemPanel;
    JLabel titleLabel;
    JButton villageButton, actionButton, tradecartMenu;
    JTextArea announcer, resources, buildings, health, dailyTaskInfo, magicItems;
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    UserActionHandler userActionHandler = new UserActionHandler();
    MapNavigationHandler mapNavigationHandler = new MapNavigationHandler();
    VillagerAssignmentHandler villagerAssignmentHandler = new VillagerAssignmentHandler();
    DeleteAgendaHandler deleteAgendaHandler = new DeleteAgendaHandler();
    NextDayHandler nextDayHandler = new NextDayHandler();
    Game game;
    Enum.mapLocationType mapLocation;
    ArrayList<JButton> dailyTasksButtons;

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

        createAllChangablePanels();
        stopAllButton();
        login();
        window.setVisible(true);

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
        temp = new JButton("Start");
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
        userActionPanel.setLayout(new GridLayout(7, 1));

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
        con.add(userActionPanel);

        buildingButtonPanel = new JPanel();
        buildingButtonPanel.setBounds(400, 200, 200, 400);
        buildingButtonPanel.setBackground(Color.black);
        buildingButtonPanel.setLayout(new GridLayout(8,1));
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
        temp = new JButton("Tradecart");
        buttonHelper(temp, "tradecart");

        peoplePanel = new JPanel();
        peoplePanel.setBounds(650, 200, 200, 550);
        peoplePanel.setBackground(Color.black);
        peoplePanel.setLayout(new GridLayout(game.getJobMap().size(),1));
        con.add(peoplePanel);

        numberPanel = new JPanel();
        numberPanel.setBounds(850, 200, 80, 550);
        numberPanel.setBackground(Color.black);
        numberPanel.setLayout(new GridLayout(game.getJobMap().size(), 1));
        con.add(numberPanel);

        arrowPanel = new JPanel();
        arrowPanel.setBounds(930, 200, 50, 500);
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

    }

    public void createTradecartItems(boolean newDay){
        // skip for rn
    }

    public void jobRepaint(){
        numberPanel.removeAll();
        numberPanel.revalidate();
        System.out.println(numberPanel.getComponentCount());
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

    public void gamePlayScreen(){

        eventAnnouncerPanel = new JPanel();
        eventAnnouncerPanel.setBounds(100, 100, 200, 1000);
        eventAnnouncerPanel.setBackground(Color.black);
        announcer = new JTextArea("This is the event Announcer");
        announcer.setBounds(100, 100, 200, 1000);
        textColorHelper(announcer);
        announcer.setLineWrap(true);
        announcer.setWrapStyleWord(true);
        eventAnnouncerPanel.add(announcer);
        con.add(eventAnnouncerPanel);

        resourcesPanel = new JPanel();
        resourcesPanel.setBounds(1000, 100, 400, 300);
        resourcesPanel.setBackground(Color.BLACK);

        con.add(resourcesPanel);
        resources = new JTextArea();

        resources.setBounds(1000,100,400,300);

        resources.setText("Resources \t Amount\n");
        for(Map.Entry<Enum.resourceType, Integer> elements: game.getResourceMap().entrySet()){
            resources.append(elements.getKey() + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(resources);
        resourcesPanel.add(resources);

        buildingPanel = new JPanel();
        buildingPanel.setBounds(1000, 500, 400, 300);
        buildingPanel.setBackground(Color.black);

        con.add(buildingPanel);

        buildings = new JTextArea();
        buildings.setBounds(1000,500,400,300);

        buildings.setText("Building\t Amount\n");
        for(Map.Entry<Enum.buildingType, Integer> elements: game.getBuildingMap().entrySet()){
            buildings.append(elements.getKey() + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(buildings);
        buildingPanel.add(buildings);

        healthPanel = new JPanel();
        healthPanel.setBounds(1000, 900, 400, 200);
        healthPanel.setBackground(Color.black);

        con.add(healthPanel);

        health = new JTextArea();
        health.setBounds(1000,900,400,200);

        health.append("Health\t 100\n");
        health.append("Defense\t 100\n");
        textColorHelper(health);
        healthPanel.add(health);


    }

    public void dailyRepaint(){
        eventAnnouncerPanel.removeAll();
        resourcesPanel.removeAll();
        buildingPanel.removeAll();
        healthPanel.removeAll();
        con.repaint();
        gamePlayScreen();
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
                createTradecartItems(true);
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
        startButtonPanel.setVisible(false);
        buildingButtonPanel.setVisible(false);
        peoplePanel.setVisible(false);
        numberPanel.setVisible(false);
        arrowPanel.setVisible(false);
        userActionPanel.setVisible(false);
        dailyTaskPanel.setVisible(false);
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

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event){
            gamePlayScreen();
            map();
        }
    }

    public class NextDayHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            game.dailyUpdate();
            dailyRepaint();

            System.out.println("Next day");
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
                    System.out.println("tradecart map");
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
                    System.out.println("You are gathering wood");
                    game.setDailyAgenda(Enum.resourceType.wood);
                    break;
                case "gatherFood":
                    System.out.println("You are gathering food");
                    game.setDailyAgenda(Enum.resourceType.food);
                    break;
                case "gatherMeat":
                    System.out.println("You are gathering meat");
                    game.setDailyAgenda(Enum.resourceType.meat);
                    break;
                case "gatherRocks":
                    System.out.println("You are gathering rocks");
                    game.setDailyAgenda(Enum.resourceType.rock);
                    break;
                case "gatherWater":
                    System.out.println("You are gathering water");
                    game.setDailyAgenda(Enum.resourceType.water);
                    break;
                case "gatherClothes":
                    System.out.println("You are gathering Clothes");
                    game.setDailyAgenda(Enum.resourceType.clothes);
                    break;
                case "gatherFur":
                    System.out.println("You are gathering Fur");
                    game.setDailyAgenda(Enum.resourceType.fur);
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
                    System.out.println("Added a hunter");
                    game.assignJobs(Enum.jobType.Hunter);
                    break;
                case "Hunter_down":
                    System.out.println("Deleted a hunter");
                    game.removeJobs(Enum.jobType.Hunter);
                    break;
                case "Miner_up":
                    System.out.println("Added a miner");
                    game.assignJobs(Enum.jobType.Miner);
                    break;
                case "Miner_down":
                    System.out.println("Deleted a miner");
                    game.removeJobs(Enum.jobType.Miner);
                    break;
                case "Lumberjack_up":
                    System.out.println("Added a lumberjack");
                    game.assignJobs(Enum.jobType.Lumberjack);
                    break;
                case "Lumberjack_down":
                    System.out.println("Deleted a lumberjack");
                    game.removeJobs(Enum.jobType.Lumberjack);
                    break;
                case "Weaponsmith_up":
                    System.out.println("Added a Weaponsmith");
                    game.assignJobs(Enum.jobType.Weaponsmith);
                    break;
                case "Weaponsmith_down":
                    System.out.println("Deleted a Weaponsmith");
                    game.removeJobs(Enum.jobType.Weaponsmith);
                    break;
                case "Repairer_up":
                    System.out.println("Added a Repairer");
                    game.assignJobs(Enum.jobType.Repairer);
                    break;
                case "Repairer_down":
                    System.out.println("Deleted a Repairer");
                    game.removeJobs(Enum.jobType.Repairer);
                    break;
                case "Cook_up":
                    System.out.println("Added a Cook");
                    game.assignJobs(Enum.jobType.Cook);
                    break;
                case "Cook_down":
                    System.out.println("Deleted a Cook");
                    game.removeJobs(Enum.jobType.Cook);
                    break;
                case "Waterman_up":
                    System.out.println("Added a Waterman");
                    game.assignJobs(Enum.jobType.Waterman);
                    break;
                case "Waterman_down":
                    System.out.println("Deleted a Waterman");
                    game.removeJobs(Enum.jobType.Waterman);
                    break;
                case "Tailor_up":
                    System.out.println("Added a Tailor");
                    game.assignJobs(Enum.jobType.Tailor);
                    break;
                case "Tailor_down":
                    System.out.println("Deleted a Tailor");
                    game.removeJobs(Enum.jobType.Tailor);
                    break;
                case "Gather_up":
                    System.out.println("Added a Gather");
                    game.assignJobs(Enum.jobType.Gather);
                    break;
                case "Gather_down":
                    System.out.println("Deleted a Gather");
                    game.removeJobs(Enum.jobType.Gather);
                    break;
                case "Trapper_up":
                    System.out.println("Added a Trapper");
                    game.assignJobs(Enum.jobType.Trapper);
                    break;
                case "Trapper_down":
                    System.out.println("Deleted a Trapper");
                    game.removeJobs(Enum.jobType.Trapper);
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
                default:
                    System.out.println("I am not sure what you created");
            }
            dailyRepaint();
        }
    }
}
