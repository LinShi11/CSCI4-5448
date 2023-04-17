import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    JPanel titlePanel, startButtonPanel, eventAnnouncerPanel, buildingButtonPanel, resourcesPanel, buildingPanel;
    JLabel titleLabel;
    JButton startButton, hutButton, smokeHouseButton, minesButton, factoryButton, blacksmithButton, bucketButton, trapButton;
    JTextArea announcer, resources, buildings;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    HashMap<String, Integer> resourceMap;
    HashMap<String, Integer> buildingMap;

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
        buildingButtonPanel.setBounds(400, 200, 200, 400);
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
                default:
                    System.out.println("I am not sure what you created");
            }
        }
    }
}
