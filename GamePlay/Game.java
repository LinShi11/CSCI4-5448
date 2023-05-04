package GamePlay;

import Buildings.Building;
import Decorators.*;
import Factory.BuildingFactory;
import Factory.JobFactory;
import GamePlaySupport.*;
import ObserverPattern.ObserverInterface;
import ObserverPattern.Subject;
import People.People;
import CommandRandomEvents.RandomEvents;

import java.io.*;
import java.util.*;

/**
 * This class contains all the logic for the actual game implementation, it contains where all data are stored.
 */
public class Game implements Subject {

    HashMap<Enum.resourceType, Integer> resourceMap;
    HashMap<Enum.buildingType, Integer> buildingMap;

    HashMap<Enum.jobType, Integer> jobMap;
    HashMap<Enum.magicItems, Integer> magicItemMap;
    HashMap<Enum.stats, Integer> statsMap;
    ArrayList<Enum.resourceType> cartResourceItemList;
    ArrayList<Enum.magicItems> magicItemsArrayList;
    ArrayList<MagicItemDecorator> magicItemDecoratorArrayList;
    ArrayList<Enum.resourceType> dailyAgenda;
    ArrayList<People> peopleArrayList;
    UserActions userActions;
    BuildingFactory buildingFactory = new BuildingFactory();
    JobFactory jobFactory = new JobFactory();
    ArrayList<ObserverInterface> registered = new ArrayList<>();
    String userName = "";

    boolean unhappy;
    RandomEvents randomevents;

    /**
     * Constructor and to initialize the variables
     */
    public Game(){
        // map used to store the data
        resourceMap = new HashMap<>();
        buildingMap = new HashMap<>();
        jobMap = new HashMap<>();
        magicItemMap = new HashMap<>();
        statsMap = new HashMap<>();

        // arraylist of data
        dailyAgenda = new ArrayList<>();
        cartResourceItemList = new ArrayList<>();
        magicItemsArrayList = new ArrayList<>();

        // stores all villager and magic item
        peopleArrayList = new ArrayList<>();
        magicItemDecoratorArrayList = new ArrayList<>();

        userActions = new UserActions();
        unhappy = false;
        randomevents = new RandomEvents(this);
    }

    /**
     * Save the user name
     * @param name: the useranem to save
     */
    public void saveName(String name){
        userName = name;
    }

    /**
     * check if this username exist
     * @param username: the username to check
     * @return: true if the username exist; false otherwise
     */
    public boolean checkUserName(String username){
        // uses bufferreader to read through the stored game data
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("GamePlay/gameStats.txt"));
            String line = reader.readLine();

            while(line != null){
                System.out.println(line.split(",")[0]);
                if(Objects.equals(line.split(",")[0], username)){
                    userName = username;
                    return true;
                }
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * called when they are a new user, put 0 for everything, 100 for health and defense
     */
    public void createData(){
        for(Enum.resourceType resourceType: Enum.resourceType.values()){
            resourceMap.put(resourceType, 0);
        }
        for(Enum.buildingType buildingType: Enum.buildingType.values()){
            buildingMap.put(buildingType, 0);
        }
        for(Enum.jobType jobType: Enum.jobType.values()){
            jobMap.put(jobType, 0);
        }
        for(Enum.magicItems magicItems: Enum.magicItems.values()){
            magicItemMap.put(magicItems, 0);
        }
        for(Enum.stats stats: Enum.stats.values()){
            statsMap.put(stats, 100);
        }
    }

    /**
     * called when the username exist
     */
    public void loadData(){
        // find the line that we are reading
        BufferedReader reader;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader("GamePlay/gameStats.txt"));
            line = reader.readLine();

            while(line != null){
                System.out.println(line.split(",")[0]);
                if(Objects.equals(line.split(",")[0], userName)){
                    break;
                }
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        // split all data into an array
        String[] tempStats = line.split(",");
        int location = 1;
        int count;

        // the first set of data is the stored resource count
        for(int i = location; i < location+ Enum.resourceType.values().length; i++){
            Enum.resourceType resource = Enum.resourceType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            resourceMap.put(resource, count);
        }
        location += Enum.resourceType.values().length;

        // the next set is the building count
        for(int i = location; i < location + Enum.buildingType.values().length; i++){
            System.out.println(tempStats[i]);
            Enum.buildingType building = Enum.buildingType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            buildingMap.put(building, count);
        }
        location+= Enum.buildingType.values().length;

        // the next set is the job assignments
        for(int i = location; i < location+ Enum.jobType.values().length; i++){
            Enum.jobType job = Enum.jobType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            jobMap.put(job, count);
        }
        location+= Enum.jobType.values().length;

        // the next set is the stats: defense and health
        for(int i = location; i< location+ Enum.stats.values().length; i++){
            Enum.stats stats = Enum.stats.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            statsMap.put(stats, count);
        }

        // recreate all villager object, will need that for the game
        for(Map.Entry<Enum.jobType, Integer> elements: jobMap.entrySet()){
            for(int i = 0; i < elements.getValue(); i++) {
                loadPeopleArray(elements.getKey());
            }
        }
    }

    /**
     * getter for stats hashmap
     * @return: the stats hashmap
     */
    public HashMap<Enum.stats, Integer> getStats(){
        return statsMap;
    }

    /**
     * set the health
     * @param health: the health to decrease
     */
    public void setHealth(int health){
        int afterHealth = statsMap.get(Enum.stats.health) - health;
        if(afterHealth <= 0){
            notifyObserver("You have lost the game, the game will be restarted");
            createData();
        } else {
            statsMap.put(Enum.stats.health, afterHealth);
        }
    }



    /**
     * factory for the command pattern
     * @return: the string based on the command pattern
     */
    public String randomEvents(){
        Random random = new Random();
        int choice = random.nextInt(4);
        System.out.println(choice);
        switch (choice){
            case 0:
                return randomevents.monsterAttack();
            case 1:
                return randomevents.peopleLeaving(unhappy);
            case 2:
                return randomevents.smallStorm();
            case 3:
                return randomevents.robbed();
            default:
                return "No event has occurred";
        }
    }

    /**
     * helper function to create the villager object and add it to a list
     * @param type
     */
    public void loadPeopleArray(Enum.jobType type){
        People person = jobFactory.assignJob(type);
        peopleArrayList.add(person);
    }

    /**
     * attempts to save the game
     * @throws FileNotFoundException: if the file is not found
     */
    public void saveGame() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("GamePlay/gameStats.txt"));

        // reads the current GamePlay/gamestats.txt and will add the line if the username do not match
        StringBuffer buffer = new StringBuffer();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if(!Objects.equals(line.split(",")[0], userName)){
                buffer.append(line + System.lineSeparator());
            }
        }

        // convert the file stats to string
        String fileContents = buffer.toString();
        scanner.close();

        // build our string that we wish to add
        StringBuilder saveStats = new StringBuilder(userName);
        for(Map.Entry<Enum.resourceType, Integer> elements: resourceMap.entrySet()){
            saveStats = new StringBuilder(saveStats + "," + elements.getKey().toString() + ":" + elements.getValue().toString());
        }
        for(Map.Entry<Enum.buildingType, Integer> elements: buildingMap.entrySet()){
            saveStats = new StringBuilder(saveStats + "," + elements.getKey().toString() + ":" + elements.getValue().toString());
        }
        for(Map.Entry<Enum.jobType, Integer> elements: jobMap.entrySet()){
            saveStats = new StringBuilder(saveStats + "," + elements.getKey().toString() + ":" + elements.getValue().toString());
        }
        for(Map.Entry<Enum.stats, Integer> elements: statsMap.entrySet()){
            saveStats = new StringBuilder(saveStats + "," + elements.getKey().toString() + ":" + elements.getValue().toString());
        }

        // add the new line
        fileContents += saveStats;

        // rewrite all the data back to the file
        try {
            FileWriter writer = new FileWriter("GamePlay/gameStats.txt");
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The function deletes the daily tasks from the list
     * @param type: what task they want to delete
     * @return: true if success, false otherwise
     */
    public boolean deleteDailyAgenda(Enum.resourceType type){
        for(int i = 0; i < dailyAgenda.size(); i++){
            if(dailyAgenda.get(i) == type){
                dailyAgenda.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * add the daily tasks to the list
     * @param type: the task to add
     */
    public void setDailyAgenda(Enum.resourceType type) {
        if(dailyAgenda.size() < 3){
            dailyAgenda.add(type);
        }
    }

    /**
     * delete/"purchase" the resource item from the tradecart
     * @param type: the one to delete
     */
    public void deleteCartResourceItem(Enum.resourceType type){
        if(resourceMap.get(Enum.resourceType.gold) >= 5) {
            for (int i = 0; i < cartResourceItemList.size(); i++) {
                if (cartResourceItemList.get(i) == type) {
                    cartResourceItemList.remove(i);
                    resourceMap.put(type, resourceMap.get(type) + 1);
                    break;
                }
            }
        } else{
            notifyObserver("Not enough gold (5)");
        }
    }

    /**
     * delete/"purchase" the magic item from tradecart
     * @param type: the magic item to buy
     */
    public void deleteCartMagicItem(Enum.magicItems type){
        if(resourceMap.get(Enum.resourceType.gold) >= 50) {
            for (int i = 0; i < magicItemsArrayList.size(); i++) {
                if (magicItemsArrayList.get(i) == type) {
                    magicItemsArrayList.remove(i);
                    magicItemMap.put(type, magicItemMap.get(type) + 1);
                    setDecorator(type);
                    break;
                }
            }
        } else{
            notifyObserver("Not enough gold (50)");
        }
    }

    /**
     * decorator pattern: adds all decorator to all the villagers, gives them a boost and bonus. Note: the magic item is only usable during the sessin, once you quit, the buff does not stay
     * @param type: the magic item to add
     */
    public void setDecorator(Enum.magicItems type){
        switch (type){
            case matches:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new MatchesDecorator(peopleArrayList.get(i)));
                }
                break;
            case axe:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new AxeDecorator(peopleArrayList.get(i)));
                }
                break;
            case needle:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new SewingDecorator(peopleArrayList.get(i)));
                }
                break;
            case pickaxe:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new PickaxeDecorator(peopleArrayList.get(i)));
                }
                break;
            case bait:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new BaitDecorator(peopleArrayList.get(i)));
                }
                break;
            case storage:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new StorageDecorator(peopleArrayList.get(i)));
                }
                break;
            case metal:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new MetalDecorator(peopleArrayList.get(i)));
                }
                break;
            case bow:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new BowDecorator(peopleArrayList.get(i)));
                }
                break;
            case sword:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new SwordDecorator(peopleArrayList.get(i)));
                }
                break;
            case gunpowder:
                for(int i = 0; i < peopleArrayList.size(); i++){
                    peopleArrayList.set(i, new GunpowderDecorator(peopleArrayList.get(i)));
                }
                break;
            default:
                System.out.println("some error has occured");
        }
    }

    /**
     * the function resets the tradecart item, calls every new day
     */
    public void setDailyItems(){
        Random random = new Random();
        cartResourceItemList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cartResourceItemList.add(Helper.findItem(random.nextInt(resourceMap.size())));
        }
    }

    /**
     * The function resets tradecart item, calls every new day
     */
    public void setDailyMagicItems(){
        Random random = new Random();
        magicItemsArrayList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            magicItemsArrayList.add(Helper.findMagicItem(random.nextInt(magicItemMap.size())));
        }
    }

    /**
     * getting for daily tasks
     * @return: arralist of daily task
     */
    public ArrayList<Enum.resourceType> getDailyAgenda(){
        return dailyAgenda;
    }

    /**
     * getter for resource tradecart
     * @return: arraylist of resource offered in tradecart
     */
    public ArrayList<Enum.resourceType> getCartResourceItemList(){return cartResourceItemList;}

    /**
     * getter for magic item tradecart
     * @return: arraylist of magic item offered in tradecart
     */
    public ArrayList<Enum.magicItems> getMagicItemList(){
        return magicItemsArrayList;
    }

    /**
     * getter for resource hashmap
     * @return: the resource hashmap
     */
    public HashMap<Enum.resourceType, Integer> getResourceMap(){
        return resourceMap;
    }

    /**
     * the function adds the building
     * @param type: the type of building to add
     */
    public void addBuildings(Enum.buildingType type){
        // check if we have enough resources
        if(possibleBuild(type)) {

            // create the instance using factory
            Building building = buildingFactory.constructBuilding(type);

            // check singleton
            if (building.getType() == Enum.buildingType.Tradecart) {
                buildingMap.put(Enum.buildingType.Tradecart, 1);
            } else {
                buildingMap.put(building.getType(), (buildingMap.get(building.getType()) + 1));
            }

            // modify villager count after a hut is constructed
            if(type == Enum.buildingType.Hut){
                for(int i = 0; i < Helper.getJobLimit(Enum.buildingType.Hut); i++) {
                    assignJobs(Enum.jobType.Villager);
                }
            }
            notifyObserver("Constructed a " + type.toString());
        }else {
            notifyObserver("Cannot construct a " + type.toString() + ": ");
        }
    }

    /**
     * the function assigns the villagers
     * @param type: the type of job to assign
     */
    public void assignJobs(Enum.jobType type){
        // check if we can assign the job
        if(possibleJob(type)) {

            // create instance of person
            People person = jobFactory.assignJob(type);
            peopleArrayList.add(person);
            jobMap.put(person.getType(), (jobMap.get(person.getType()) + 1));

            // if it is not villager, that mean we need to remove a villager
            if(type != Enum.jobType.Villager) {
                for (People p : peopleArrayList) {
                    if (p.getType() == Enum.jobType.Villager) {
                        peopleArrayList.remove(p);
                        break;
                    }
                }
                jobMap.put(Enum.jobType.Villager, (jobMap.get(Enum.jobType.Villager)) - 1);
            }
            notifyObserver("Assigned a new " + type.toString());
        } else{
            notifyObserver("Cannot assign a " + type.toString());
        }
    }

    /**
     * the function removes the villager from the job
     * @param type: the job to remove
     */
    public void removeJobs(Enum.jobType type){
        // make sure it is removable and remove
        if(jobMap.get(type) > 0){
            jobMap.put(type, (jobMap.get(type)-1));
            for(People person: peopleArrayList){
                if(person.getType() == type){
                    peopleArrayList.remove(person);
                    break;
                }
            }
            People person = jobFactory.assignJob(Enum.jobType.Villager);
            peopleArrayList.add(person);
            jobMap.put(Enum.jobType.Villager, (jobMap.get(Enum.jobType.Villager)) + 1);
            notifyObserver("Removed a "+ type.toString());
        } else{
            notifyObserver("Cannot remove a " + type.toString());
        }
    }

    /**
     * the daily update for resources
     */
    public void dailyUpdate(){
        // daily resource update for the user actions
        ArrayList<Integer> villagerUpdate = new ArrayList<>();
        for(int i = 0; i < Enum.resourceType.values().length+ Enum.stats.values().length; i++){
            villagerUpdate.add(0);
        }

        // add the daily update from daily tasks
        for(Enum.resourceType agenda: dailyAgenda){

            switch (agenda){
                case wood:
                    resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + userActions.getWood()));
                    villagerUpdate.set(0, villagerUpdate.get(0) + userActions.getWood());
                    notifyObserver("     Collected Wood");
                    break;
                case food:
                    resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + userActions.getFood()));
                    villagerUpdate.set(1, villagerUpdate.get(1) + userActions.getFood());
                    notifyObserver("     Collected Food");
                    break;
                case meat:
                    resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + userActions.getMeat()));
                    villagerUpdate.set(2, villagerUpdate.get(2) + userActions.getMeat());
                    notifyObserver("     Collected Meat");
                    break;
                case rock:
                    resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + userActions.getRock()));
                    villagerUpdate.set(3, villagerUpdate.get(3) + userActions.getRock());
                    notifyObserver("     Collected Rock");
                    break;
                case water:
                    resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + userActions.getWater()));
                    villagerUpdate.set(4, villagerUpdate.get(4) + userActions.getWater());
                    notifyObserver("     Collected Water");
                    break;
                case clothes:
                    resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + userActions.getClothes()));
                    villagerUpdate.set(5, villagerUpdate.get(5) + userActions.getClothes());
                    notifyObserver("     Collected Clothes");
                    break;
                case fur:
                    resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + userActions.getFur()));
                    villagerUpdate.set(6, villagerUpdate.get(6) + userActions.getFur());
                    notifyObserver("     Collected Fur");
                    break;
                case gold:
                    resourceMap.put(Enum.resourceType.gold, (resourceMap.get(Enum.resourceType.gold) + userActions.getGold()));
                    villagerUpdate.set(7, villagerUpdate.get(7) + userActions.getGold());
                    notifyObserver("     Collected Gold");
                    break;
                default:
                    System.out.println("nothing");
            }
        }
        notifyObserver("Tasks completed: ");


        // daily resource update for villager jobs
        for(People person: peopleArrayList){
            villagerUpdate.set(0, villagerUpdate.get(0) + person.getWood());
            villagerUpdate.set(1, villagerUpdate.get(1) + person.getFood());
            villagerUpdate.set(2, villagerUpdate.get(2) + person.getMeat());
            villagerUpdate.set(3, villagerUpdate.get(3) + person.getRock());
            villagerUpdate.set(4, villagerUpdate.get(4) + person.getWater());
            villagerUpdate.set(5, villagerUpdate.get(5) + person.getClothes());
            villagerUpdate.set(6, villagerUpdate.get(6) + person.getFur());
            villagerUpdate.set(7, villagerUpdate.get(7) + person.getGold());
            villagerUpdate.set(8, villagerUpdate.get(8) + person.getHealth());
            villagerUpdate.set(9, villagerUpdate.get(9) + person.getDefense());
        }
        resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + ((villagerUpdate.get(0) < 0) ? 0 : villagerUpdate.get(0))));
        resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + ((villagerUpdate.get(1) < 0) ? 0 : villagerUpdate.get(1))));
        resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + ((villagerUpdate.get(2) < 0) ? 0 : villagerUpdate.get(2))));
        resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + ((villagerUpdate.get(3) < 0) ? 0 : villagerUpdate.get(3))));
        resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + ((villagerUpdate.get(4) < 0) ? 0 : villagerUpdate.get(4))));
        resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + ((villagerUpdate.get(5) < 0) ? 0 : villagerUpdate.get(5))));
        resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + ((villagerUpdate.get(6) < 0) ? 0 : villagerUpdate.get(6))));
        resourceMap.put(Enum.resourceType.gold, (resourceMap.get(Enum.resourceType.gold) + ((villagerUpdate.get(7) < 0) ? 0 : villagerUpdate.get(7))));

        statsMap.put(Enum.stats.health, (statsMap.get(Enum.stats.health) + ((villagerUpdate.get(8) < 0) ? 0 : villagerUpdate.get(8))));
        statsMap.put(Enum.stats.defense, (statsMap.get(Enum.stats.defense) + ((villagerUpdate.get(9) < 0) ? 0 : villagerUpdate.get(9))));

        // add it to the event announcer
        notifyObserver("wood: " + villagerUpdate.get(0));
        notifyObserver("food: " + villagerUpdate.get(1));
        notifyObserver("meat: " + villagerUpdate.get(2));
        notifyObserver("rock: " + villagerUpdate.get(3));
        notifyObserver("water: " + villagerUpdate.get(4));
        notifyObserver("clothes: "+ villagerUpdate.get(5));
        notifyObserver("fur: " + villagerUpdate.get(6));
        notifyObserver("gold: " + villagerUpdate.get(7));
        notifyObserver("health: " + villagerUpdate.get(8));
        notifyObserver("defense: " + villagerUpdate.get(9));

        // check if the villagers had to work overtime to get all the resources
        unhappy = false;
        for(int i = 0; i < villagerUpdate.size(); i++){
            if(villagerUpdate.get(i) < 0){
                notifyObserver("The villagers are unhappy, the village is not producing enough resources. If this continues, the villagers will start to leave.\n");
                unhappy = true;
                break;
            }
        }
    }

    /**
     * getter for building hashmap
     * @return building hashmap
     */
    public HashMap<Enum.buildingType, Integer> getBuildingMap(){
        return buildingMap;
    }

    /**
     * getter for job hashmap
     * @return job hashmap
     */
    public HashMap<Enum.jobType, Integer> getJobMap(){
        return jobMap;
    }

    /**
     * getter for stats map
     * @return stats hashmap
     */
    public HashMap<Enum.stats, Integer> getStatsMap(){
        return statsMap;
    }

    /**
     * getter for people arraylist
     * @return arraylist of all the people
     */
    public ArrayList<People> getPeopleArrayList(){
        return peopleArrayList;
    }

    /**
     * part of the random event: when we lose villagers
     * @param count: the number of villagers to remove
     */
    public void lostVillager(int count){
        Random random = new Random();
        System.out.println(peopleArrayList.size());
        for(int i = 0; i< count; i++){
            int randomDelete = random.nextInt(peopleArrayList.size());
            People villager = peopleArrayList.get(randomDelete);
            jobMap.put(villager.getType(), jobMap.get(villager.getType())-1);
            peopleArrayList.remove(villager);
        }
    }

    /**
     * setter for resource update
     * @param type: the resource type to update
     * @param usedCount: the count to remove
     */
    public void setResource(Enum.resourceType type, int usedCount){
        resourceMap.put(type, resourceMap.get(type)-usedCount);
    }

    /**
     * check for possible build
     * @param type: the type of building to add
     * @return: true if possible; false otherwise
     */
    public boolean possibleBuild(Enum.buildingType type){
        switch (type){
            case Smokehouse:
                if(resourceMap.get(Enum.resourceType.wood) >= 5 && resourceMap.get(Enum.resourceType.meat) >= 5){
                    setResource(Enum.resourceType.wood, 5);
                    setResource(Enum.resourceType.meat, 5);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Factory:
                if(resourceMap.get(Enum.resourceType.wood) >= 5 && resourceMap.get(Enum.resourceType.fur) >= 5){
                    setResource(Enum.resourceType.wood, 5);
                    setResource(Enum.resourceType.fur, 5);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Bucket:
                if(resourceMap.get(Enum.resourceType.wood) >= 2){
                    setResource(Enum.resourceType.wood, 2);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Trap:
                if(resourceMap.get(Enum.resourceType.wood) >= 5){
                    setResource(Enum.resourceType.wood, 5);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Hut:
                if(resourceMap.get(Enum.resourceType.wood) >= 10){
                    setResource(Enum.resourceType.wood, 10);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Blacksmith:
                if(resourceMap.get(Enum.resourceType.wood) >= 5 && resourceMap.get(Enum.resourceType.rock) >= 5){
                    setResource(Enum.resourceType.wood, 5);
                    setResource(Enum.resourceType.rock, 5);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Mines:
                if(resourceMap.get(Enum.resourceType.rock) >= 5){
                    setResource(Enum.resourceType.rock, 5);
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Tradecart:
                if(resourceMap.get(Enum.resourceType.wood) >= 5){
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            case Gold_Mine:
                if(resourceMap.get(Enum.resourceType.wood) >= 5 && resourceMap.get(Enum.resourceType.rock) >= 5){
                    return true;
                }
                notifyObserver("     Not enough resources");
                return false;
            default:
                return false;
        }
    }

    /**
     * helper function to check if the job is possible
     * @param type: the type of job to add
     * @return: true if it is possible; false otherwise
     */
    public boolean possibleJob(Enum.jobType type){
        if(type != Enum.jobType.Villager) {
            if (jobMap.get(Enum.jobType.Villager) <= 0) {
                notifyObserver("     Not enough villagers");
                return false;
            }
        }
        switch (type){
            case Cook:
                if(buildingMap.get(Enum.buildingType.Smokehouse) * Helper.getJobLimit(Enum.buildingType.Smokehouse) > jobMap.get(Enum.jobType.Cook)){
                    return true;
                }
                notifyObserver("     Not enough smokehouse");
                return false;
            case Gather, Villager, Lumberjack, Repairer, Hunter:
                return true;
            case Miner:
                if(buildingMap.get(Enum.buildingType.Mines) * Helper.getJobLimit(Enum.buildingType.Mines) > jobMap.get(Enum.jobType.Miner)){
                    return true;
                }
                notifyObserver("     Not enough mines");
                return false;
            case Tailor:
                if(buildingMap.get(Enum.buildingType.Factory) * Helper.getJobLimit(Enum.buildingType.Factory) > jobMap.get(Enum.jobType.Tailor)){
                    return true;
                }
                notifyObserver("     Not enough factory");
                return false;
            case Trapper:
                if(buildingMap.get(Enum.buildingType.Trap) * Helper.getJobLimit(Enum.buildingType.Trap) > jobMap.get(Enum.jobType.Trapper)){
                    return true;
                }
                notifyObserver("     Not enough trap");
                return false;
            case Waterman:
                if(buildingMap.get(Enum.buildingType.Bucket) * Helper.getJobLimit(Enum.buildingType.Bucket) > jobMap.get(Enum.jobType.Waterman)){
                    return true;
                }
                notifyObserver("     Not enough bucket");
                return false;
            case Weaponsmith:
                if(buildingMap.get(Enum.buildingType.Blacksmith) * Helper.getJobLimit(Enum.buildingType.Blacksmith) > jobMap.get(Enum.jobType.Weaponsmith)){
                    return true;
                }
                notifyObserver("     Not enough blacksmith");
                return false;
            case Gold_Miner:
                if(buildingMap.get(Enum.buildingType.Gold_Mine) * Helper.getJobLimit(Enum.buildingType.Gold_Mine) > jobMap.get(Enum.jobType.Gold_Miner)){
                    return true;
                }
                notifyObserver("     Not enough gold mines");
                return false;
            default:
                return false;
        }

    }

    /**
     * Observer pattern: register the objects
     * @param obj: the object to  add
     */
    @Override
    public void register(ObserverInterface obj) {
        registered.add(obj);
    }

    /**
     * Observer pattern: unregister the object
     * @param obj: the object to delete
     */
    @Override
    public void unregister(ObserverInterface obj) {
        registered.remove(obj);
    }

    /**
     * Observer pattern: notify all observers
     * @param event: the message to send them
     */
    @Override
    public void notifyObserver(String event) {
        for(ObserverInterface obs: registered){
            obs.update(event);
        }
    }
}
