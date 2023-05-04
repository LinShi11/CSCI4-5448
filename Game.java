import java.io.*;
import java.util.*;

public class Game implements Subject{

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
    ArrayList<Observer> registered = new ArrayList<>();
    String userName = "";
    Invoker invoker = new Invoker();
    boolean unhappy;

    public Game(){
        resourceMap = new HashMap<>();
        buildingMap = new HashMap<>();
        jobMap = new HashMap<>();
        magicItemMap = new HashMap<>();
        statsMap = new HashMap<>();

        dailyAgenda = new ArrayList<>();
        cartResourceItemList = new ArrayList<>();
        magicItemsArrayList = new ArrayList<>();

        peopleArrayList = new ArrayList<>();
        magicItemDecoratorArrayList = new ArrayList<>();

        userActions = new UserActions();
        unhappy = false;
    }
    public void saveName(String name){
        userName = name;
    }

    public boolean checkUserName(String username){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("gameStats.txt"));
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

    public void loadData(){
        BufferedReader reader;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader("gameStats.txt"));
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
        String[] tempStats = line.split(",");
        int location = 1;
        int count;
        for(int i = location; i < location+Enum.resourceType.values().length; i++){
            Enum.resourceType resource = Enum.resourceType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            resourceMap.put(resource, count);
        }
        location += Enum.resourceType.values().length;
        for(int i = location; i < location + Enum.buildingType.values().length; i++){
            Enum.buildingType building = Enum.buildingType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            buildingMap.put(building, count);
        }

        location+= Enum.buildingType.values().length;
        for(int i = location; i < location+Enum.jobType.values().length; i++){
            Enum.jobType job = Enum.jobType.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            jobMap.put(job, count);
        }
        location+=Enum.jobType.values().length;
        for(int i = location; i< location+Enum.stats.values().length; i++){
            Enum.stats stats = Enum.stats.valueOf(tempStats[i].split(":")[0]);
            count = Integer.parseInt(tempStats[i].split(":")[1]);
            statsMap.put(stats, count);
        }
        for(Map.Entry<Enum.jobType, Integer> elements: jobMap.entrySet()){
            for(int i = 0; i < elements.getValue(); i++) {
                loadPeopleArray(elements.getKey());
            }
        }
    }

    public HashMap<Enum.stats, Integer> getStats(){
        return statsMap;
    }

    public void setHealth(int health){
        int afterHealth = statsMap.get(Enum.stats.health) - health;
        if(afterHealth <= 0){
            notifyObserver("You have lost the game, the game will be restarted");
            createData();
        } else {
            statsMap.put(Enum.stats.health, afterHealth);
        }
    }

    public String randomEvents(){
        Random random = new Random();
        int choice = random.nextInt(4);
        System.out.println(choice);
        switch (choice){
            case 0:
                return monsterAttack();
            case 1:
                return peopleLeaving();
            case 2:
                return smallStorm();
            case 3:
                return robbed();
            default:
                return "No event has occurred";
        }
    }

    public String robbed(){
        invoker.setCommand(new Robbed());
        return invoker.execute(this);
    }

    public String monsterAttack(){
        invoker.setCommand(new MonsterAttack());
        return invoker.execute(this);
    }

    public String peopleLeaving(){
        if(unhappy) {
            invoker.setCommand(new PeopleLeaving());
            return invoker.execute(this);
        }else{
            return "No event has occurred";
        }
    }

    public String smallStorm(){
        invoker.setCommand(new Storm());
        return invoker.execute(this);
    }

    public void loadPeopleArray(Enum.jobType type){
        People person = jobFactory.assignJob(type);
        peopleArrayList.add(person);
    }


    public void saveGame() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("gameStats.txt"));
        StringBuffer buffer = new StringBuffer();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            if(!Objects.equals(line.split(",")[0], userName)){
                buffer.append(line + System.lineSeparator());
            }
        }
        String fileContents = buffer.toString();
        scanner.close();
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
        fileContents += saveStats;
        System.out.println(fileContents);
        try {
            FileWriter writer = new FileWriter("gameStats.txt");
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteDailyAgenda(Enum.resourceType type){
        for(int i = 0; i < dailyAgenda.size(); i++){
            if(dailyAgenda.get(i) == type){
                dailyAgenda.remove(i);
                return true;
            }
        }
        return false;
    }

    public void setDailyAgenda(Enum.resourceType type) {
        if(dailyAgenda.size() < 3){
            dailyAgenda.add(type);
        }
    }

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

    public void setDailyItems(){
        Random random = new Random();
        cartResourceItemList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cartResourceItemList.add(findItem(random.nextInt(resourceMap.size())));
        }
    }

    public Enum.resourceType findItem(int randomValue){
        switch (randomValue){
            case 0:
                return Enum.resourceType.wood;
            case 1:
                return Enum.resourceType.food;
            case 2:
                return Enum.resourceType.meat;
            case 3:
                return Enum.resourceType.fur;
            case 4:
                return Enum.resourceType.rock;
            case 5:
                return Enum.resourceType.water;
            case 6:
                return Enum.resourceType.clothes;
            case 7:
                return Enum.resourceType.gold;
            default:
                return null;
        }
    }

    public void setDailyMagicItems(){
        Random random = new Random();
        magicItemsArrayList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            magicItemsArrayList.add(findMagicItem(random.nextInt(magicItemMap.size())));
        }
    }

    public Enum.magicItems findMagicItem(int randomValue){
        switch (randomValue){
            case 0:
                return Enum.magicItems.matches;
            case 1:
                return Enum.magicItems.axe;
            case 2:
                return Enum.magicItems.needle;
            case 3:
                return Enum.magicItems.pickaxe;
            case 4:
                return Enum.magicItems.bait;
            case 5:
                return Enum.magicItems.storage;
            case 6:
                return Enum.magicItems.metal;
            case 7:
                return Enum.magicItems.bow;
            case 8:
                return Enum.magicItems.sword;
            case 9:
                return Enum.magicItems.gunpowder;
            default:
                return null;
        }
    }

    public ArrayList<Enum.resourceType> getDailyAgenda(){
        return dailyAgenda;
    }
    public ArrayList<Enum.resourceType> getCartResourceItemList(){return cartResourceItemList;}

    public ArrayList<Enum.magicItems> getMagicItemList(){
        return magicItemsArrayList;
    }

    public HashMap<Enum.resourceType, Integer> getResourceMap(){
        return resourceMap;
    }
    public void addBuildings(Enum.buildingType type){
        if(possibleBuild(type)) {
            Building building = buildingFactory.constructBuilding(type);
            if (building.getType() == Enum.buildingType.Tradecart) {
                buildingMap.put(Enum.buildingType.Tradecart, 1);
            } else {
                buildingMap.put(building.getType(), (buildingMap.get(building.getType()) + 1));
            }
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
    public void assignJobs(Enum.jobType type){
        if(possibleJob(type)) {
            People person = jobFactory.assignJob(type);
            peopleArrayList.add(person);
            jobMap.put(person.getType(), (jobMap.get(person.getType()) + 1));
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

    public void removeJobs(Enum.jobType type){
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


    public void dailyUpdate(){
        // daily resource update for the user actions
        ArrayList<Integer> villagerUpdate = new ArrayList<>();
        for(int i = 0; i < Enum.resourceType.values().length+ Enum.stats.values().length; i++){
            villagerUpdate.add(0);
        }

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
        unhappy = false;
        for(int i = 0; i < villagerUpdate.size(); i++){
            if(villagerUpdate.get(i) < 0){
                notifyObserver("The villagers are unhappy, the village is not producing enough resources. If this continues, the villagers will start to leave.\n");
                unhappy = true;
                break;
            }
        }
    }

    public HashMap<Enum.buildingType, Integer> getBuildingMap(){
        return buildingMap;
    }

    public HashMap<Enum.jobType, Integer> getJobMap(){
        return jobMap;
    }

    public HashMap<Enum.stats, Integer> getStatsMap(){
        return statsMap;
    }

    public ArrayList<People> getPeopleArrayList(){
        return peopleArrayList;
    }

    public void lostVillager(int count){
        Random random = new Random();
        System.out.println(peopleArrayList.size());
        for(int i = 0; i< count; i++){
            int randomDelete = random.nextInt(peopleArrayList.size());
            People villager = peopleArrayList.get(randomDelete);
            jobMap.put(villager.getType(), jobMap.get(villager.getType())-1);
            peopleArrayList.remove(villager);
        }
        System.out.println(peopleArrayList.size());
    }

    public void getVillagerCount(){
        int villagerCount = buildingMap.get(Enum.buildingType.Hut) * Helper.getJobLimit(Enum.buildingType.Hut);
        for(Map.Entry<Enum.jobType, Integer> elements: jobMap.entrySet()){
            if(elements.getKey()!= Enum.jobType.Villager){
                villagerCount -= elements.getValue();
            }
        }
        jobMap.put(Enum.jobType.Villager, villagerCount);
    }

    public void setResource(Enum.resourceType type, int usedCount){
        resourceMap.put(type, resourceMap.get(type)-usedCount);
    }

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

    public boolean possibleJob(Enum.jobType type){
        if(jobMap.get(Enum.jobType.Villager) <= 0){
            notifyObserver("     Not enough villagers");
            return false;
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
    @Override
    public void register(Observer obj) {
        registered.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        registered.remove(obj);
    }

    @Override
    public void notifyObserver(String event) {
        for(Observer obs: registered){
            obs.update(event);
        }
    }
}
