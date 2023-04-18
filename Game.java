import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * citation: use https://www.ryisnow.online/2021/04/java-for-beginner-text-adventure-game.html as reference to jFrame start ups
 */
public class Game {
    JFrame window;
    Container con;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    Font arrowFont = new Font("Times New Roman", Font.PLAIN, 12);

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel, peoplePanel, numberPanel, arrowPanel;
    JLabel titleLabel;
    JButton startButton, hutButton, smokeHouseButton, minesButton, factoryButton, blacksmithButton, bucketButton, trapButton;
    JTextArea announcer, resources, buildings;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    HashMap<String, Integer> resourceMap;
    HashMap<String, Integer> buildingMap;

    HashMap<String, Integer> jobMap;
    ArrayList<JButton> peopleArrow;

    public Game(){
        window = new JFrame();
        window.setSize(1600, 1200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();

        resourceMap = new HashMap<>();
        resourceMap.put("Food", 0);
        resourceMap.put("Meat", 0);
        resourceMap.put("Wood", 0);
        resourceMap.put("Water", 0);
        resourceMap.put("Fur", 0);
        resourceMap.put("Rock", 0);
        resourceMap.put("Clothes", 0);

        buildingMap = new HashMap<>();
        buildingMap.put("Trap", 0);
        buildingMap.put("Bucket", 0);
        buildingMap.put("Smokehouse", 0);
        buildingMap.put("Factory", 0);
        buildingMap.put("Blacksmith", 0);
        buildingMap.put("Mines", 0);
        buildingMap.put("Tradecart", 0);

        jobMap = new HashMap<>();
        jobMap.put("Gather", 0);
        jobMap.put("Hunter", 0);
        jobMap.put("Trapper", 0);
        jobMap.put("Waterman", 0);
        jobMap.put("Tailor", 0);
        jobMap.put("Miner", 0);
        jobMap.put("Weaponsmith", 0);
        jobMap.put("Lumberjack", 0);
        jobMap.put("Cook", 0);
        jobMap.put("Repairer", 0);
        jobMap.put("Villager", 0);


        login();

        window.setVisible(true);

    }

    public void login(){
        titlePanel = new JPanel();
        titlePanel.setBounds(350,100,800,150);
        titlePanel.setBackground(Color.black);

        titleLabel = new JLabel("Defend Your Village");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 300, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("Start");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);

        con.add(titlePanel);
        con.add(startButtonPanel);

    }

    public void gamePlayScreen(){
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        eventAnnouncerPanel = new JPanel();
        eventAnnouncerPanel.setBounds(100, 100, 200, 1000);
        eventAnnouncerPanel.setBackground(Color.black);

        con.add(eventAnnouncerPanel);

        announcer = new JTextArea("This is the event Announcer");
        announcer.setBounds(100, 100, 200, 1000);
        textColorHelper(announcer);
        announcer.setLineWrap(true);
        announcer.setWrapStyleWord(true);

        eventAnnouncerPanel.add(announcer);

        buildingButtonPanel = new JPanel();
        buildingButtonPanel.setBounds(400, 200, 200, 350);
        buildingButtonPanel.setBackground(Color.black);
        buildingButtonPanel.setLayout(new GridLayout(7,1));
        con.add(buildingButtonPanel);

        hutButton = new JButton("Hut");
        buttonHelper(hutButton, "hut");
        smokeHouseButton = new JButton("SmokeHouse");
        buttonHelper(smokeHouseButton, "smokehouse");
        minesButton = new JButton("Mines");
        buttonHelper(minesButton, "mines");
        factoryButton = new JButton("Factory");
        buttonHelper(factoryButton, "factory");
        blacksmithButton = new JButton("Blacksmith");
        buttonHelper(blacksmithButton, "blacksmith");
        bucketButton = new JButton("Bucket");
        buttonHelper(bucketButton, "bucket");
        trapButton = new JButton("Trap");
        buttonHelper(trapButton, "trap");

        resourcesPanel = new JPanel();
        resourcesPanel.setBounds(1000, 100, 400, 300);
        resourcesPanel.setBackground(Color.BLACK);

        con.add(resourcesPanel);
        resources = new JTextArea();

        resources.setBounds(1000,100,400,300);

        resources.setText("Resources \t Amount\n");
        for(Map.Entry<String, Integer> elements: resourceMap.entrySet()){
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
        for(Map.Entry<String, Integer> elements: buildingMap.entrySet()){
            buildings.append(elements.getKey() + "\t" + elements.getValue() + "\n");
        }
        textColorHelper(buildings);
        buildingPanel.add(buildings);

        peoplePanel = new JPanel();
        peoplePanel.setBounds(650, 200, 200, 550);
        peoplePanel.setBackground(Color.black);
        peoplePanel.setLayout(new GridLayout(jobMap.size(),1));
        con.add(peoplePanel);

        numberPanel = new JPanel();
        numberPanel.setBounds(850, 200, 80, 550);
        numberPanel.setBackground(Color.black);
        numberPanel.setLayout(new GridLayout(jobMap.size(), 1));
        con.add(numberPanel);

        arrowPanel = new JPanel();
        arrowPanel.setBounds(930, 200, 50, 500);
        arrowPanel.setBackground(Color.black);
        arrowPanel.setLayout(new GridLayout((jobMap.size()-1)*2, 1 ));
        con.add(arrowPanel);

        JButton temp;
        for(Map.Entry<String, Integer> elements: jobMap.entrySet()){
            if(!elements.getKey().equals("Villager")) {
                temp = new JButton(elements.getKey());
                buttonAdd(temp, peoplePanel);
                temp = new JButton(elements.getValue().toString());
                buttonAdd(temp, numberPanel);
                temp = new JButton(">");
                arrowButtonAdd(temp, elements.getKey()+"_up");
                temp = new JButton("<");
                arrowButtonAdd(temp, elements.getKey()+"_down");
            }
        }
        buttonAdd(new JButton("Villager"), peoplePanel);
        buttonAdd(new JButton(jobMap.get("Villager").toString()), numberPanel);


    }

    public void arrowButtonAdd(JButton button, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(arrowFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand(command);
        arrowPanel.add(button);
    }

    public void buttonAdd(JButton button, JPanel panel){
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
        }
    }

    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String choice = event.getActionCommand();

            switch(choice){
                case "hut":
                    System.out.println("You create a hut");
                    break;
                case "smokehouse":
                    System.out.println("You create a smokehouse");
                    break;
                case "mines":
                    System.out.println("You create a mine");
                    break;
                case "factory":
                    System.out.println("You create a factory");
                    break;
                case "blacksmith":
                    System.out.println("You create a blacksmith");
                    break;
                case "bucket":
                    System.out.println("You create a bucket");
                    break;
                case "trap":
                    System.out.println("You create a trap");
                    break;
                case "void":
                    System.out.println("no action needed");
                    break;
                case "Hunter_up":
                    System.out.println("Added a hunter");
                    break;
                case "Hunter_down":
                    System.out.println("Deleted a hunter");
                    break;
                case "Miner_up":
                    System.out.println("Added a miner");
                    break;
                case "Miner_down":
                    System.out.println("Deleted a miner");
                    break;
                case "Lumberjack_up":
                    System.out.println("Added a lumberjack");
                    break;
                case "Lumberjack_down":
                    System.out.println("Deleted a lumberjack");
                    break;
                case "Weaponsmith_up":
                    System.out.println("Added a Weaponsmith");
                    break;
                case "Weaponsmith_down":
                    System.out.println("Deleted a Weaponsmith");
                    break;
                case "Repairer_up":
                    System.out.println("Added a Repairer");
                    break;
                case "Repairer_down":
                    System.out.println("Deleted a Repairer");
                    break;
                case "Cook_up":
                    System.out.println("Added a Cook");
                    break;
                case "Cook_down":
                    System.out.println("Deleted a Cook");
                    break;
                case "Waterman_up":
                    System.out.println("Added a Waterman");
                    break;
                case "Waterman_down":
                    System.out.println("Deleted a Waterman");
                    break;
                case "Tailor_up":
                    System.out.println("Added a Tailor");
                    break;
                case "Tailor_down":
                    System.out.println("Deleted a Tailor");
                    break;
                case "Gather_up":
                    System.out.println("Added a Gather");
                    break;
                case "Gather_down":
                    System.out.println("Deleted a Gather");
                    break;
                case "Trapper_up":
                    System.out.println("Added a Trapper");
                    break;
                case "Trapper_down":
                    System.out.println("Deleted a Trapper");
                    break;
                default:
                    System.out.println("I am not sure what you created");
            }
        }
    }
}
