import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game implements Subject{

    HashMap<Enum.resourceType, Integer> resourceMap;
    HashMap<Enum.buildingType, Integer> buildingMap;

    HashMap<Enum.jobType, Integer> jobMap;
    HashMap<Enum.magicItems, Integer> magicItemMap;
    HashMap<Enum.stats, Integer> statsMap;
    ArrayList<Enum.resourceType> cartResourceItemList;
    ArrayList<Enum.magicItems> magicItemsArrayList;
    ArrayList<Enum.resourceType> dailyAgenda;
    ArrayList<People> peopleArrayList;
    UserActions userActions;
    BuildingFactory buildingFactory = new BuildingFactory();
    JobFactory jobFactory = new JobFactory();
    ArrayList<Observer> registered = new ArrayList<>();

    public Game(){
        resourceMap = new HashMap<>();
        resourceMap.put(Enum.resourceType.food, 0);
        resourceMap.put(Enum.resourceType.meat, 0);
        resourceMap.put(Enum.resourceType.wood, 0);
        resourceMap.put(Enum.resourceType.water, 0);
        resourceMap.put(Enum.resourceType.fur, 0);
        resourceMap.put(Enum.resourceType.rock, 0);
        resourceMap.put(Enum.resourceType.clothes, 0);
        resourceMap.put(Enum.resourceType.gold, 0);

        buildingMap = new HashMap<>();
        buildingMap.put(Enum.buildingType.Trap, 0);
        buildingMap.put(Enum.buildingType.Bucket, 0);
        buildingMap.put(Enum.buildingType.Smokehouse, 0);
        buildingMap.put(Enum.buildingType.Factory, 0);
        buildingMap.put(Enum.buildingType.Blacksmith, 0);
        buildingMap.put(Enum.buildingType.Mines, 0);
        buildingMap.put(Enum.buildingType.Tradecart, 0);
        buildingMap.put(Enum.buildingType.Hut, 0);
        buildingMap.put(Enum.buildingType.Gold_Mine, 0);

        jobMap = new HashMap<>();
        jobMap.put(Enum.jobType.Gather, 0);
        jobMap.put(Enum.jobType.Hunter, 0);
        jobMap.put(Enum.jobType.Trapper, 0);
        jobMap.put(Enum.jobType.Waterman, 0);
        jobMap.put(Enum.jobType.Tailor, 0);
        jobMap.put(Enum.jobType.Miner, 0);
        jobMap.put(Enum.jobType.Weaponsmith, 0);
        jobMap.put(Enum.jobType.Lumberjack, 0);
        jobMap.put(Enum.jobType.Cook, 0);
        jobMap.put(Enum.jobType.Repairer, 0);
        jobMap.put(Enum.jobType.Villager, 0);
        jobMap.put(Enum.jobType.Gold_Miner, 0);

        magicItemMap = new HashMap<>();
        magicItemMap.put(Enum.magicItems.matches, 0);
        magicItemMap.put(Enum.magicItems.axe, 0);
        magicItemMap.put(Enum.magicItems.needle, 0);
        magicItemMap.put(Enum.magicItems.pickaxe, 0);
        magicItemMap.put(Enum.magicItems.bait, 0);
        magicItemMap.put(Enum.magicItems.storage, 0);
        magicItemMap.put(Enum.magicItems.metal, 0);
        magicItemMap.put(Enum.magicItems.bow, 0);
        magicItemMap.put(Enum.magicItems.sword, 0);
        magicItemMap.put(Enum.magicItems.gunpowder, 0);


        statsMap = new HashMap<>();
        statsMap.put(Enum.stats.health, 100);
        statsMap.put(Enum.stats.defense, 100);

        magicItemsArrayList = new ArrayList<>();
        dailyAgenda = new ArrayList<>();
        peopleArrayList = new ArrayList<>();

        for(int i = 0; i < 10; i ++){
            peopleArrayList.add(jobFactory.assignJob(Enum.jobType.Villager));
        }
        userActions = new UserActions();
        cartResourceItemList = new ArrayList<>();

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
                    System.out.println(magicItemMap.get(type));
                    break;
                }
            }
        } else{
            notifyObserver("Not enough gold (50)");
        }
    }

    public void setDailyItems(){
        Random random = new Random();
        cartResourceItemList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cartResourceItemList.add(findItem(random.nextInt(resourceMap.size())));
        }
    }

    private Enum.resourceType findItem(int randomValue){
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
            getVillagerCount();
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
            for (People p : peopleArrayList) {
                if (p.getType() == Enum.jobType.Villager) {
                    peopleArrayList.remove(p);
                    break;
                }
            }
            jobMap.put(Enum.jobType.Villager, (jobMap.get(Enum.jobType.Villager)) - 1);
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

        for(Enum.resourceType agenda: dailyAgenda){

            switch (agenda){
                case wood:
                    resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + userActions.getWood()));
                    notifyObserver("     Collected Wood");
                    break;
                case food:
                    resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + userActions.getFood()));
                    notifyObserver("     Collected Food");
                    break;
                case meat:
                    resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + userActions.getMeat()));
                    notifyObserver("     Collected Meat");
                    break;
                case rock:
                    resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + userActions.getRock()));
                    notifyObserver("     Collected Rock");
                    break;
                case water:
                    resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + userActions.getWater()));
                    notifyObserver("     Collected Water");
                    break;
                case clothes:
                    resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + userActions.getClothes()));
                    notifyObserver("     Collected Clothes");
                    break;
                case fur:
                    resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + userActions.getFur()));
                    notifyObserver("     Collected Fur");
                    break;
                case gold:
                    resourceMap.put(Enum.resourceType.gold, (resourceMap.get(Enum.resourceType.gold) + userActions.getGold()));
                    notifyObserver("     Collected Gold");
                    break;
                default:
                    System.out.println("nothing");
            }
        }
        notifyObserver("Tasks completed: ");
        int woodCheck = 0;
        int foodCheck = 0;
        int meatCheck = 0;
        int rockCheck = 0;
        int waterCheck = 0;
        int clothesCheck = 0;
        int furCheck = 0;
        int healthCheck = 0;
        int defenseCheck = 0;
        int goldCheck = 0;
        // daily resource update for villager jobs
        for(People person: peopleArrayList){
            woodCheck += person.getWood();
            foodCheck += person.getFood();
            meatCheck += person.getMeat();
            rockCheck += person.getRock();
            waterCheck += person.getWater();
            clothesCheck += person.getClothes();
            furCheck += person.getFur();
            healthCheck += person.getHealth();
            defenseCheck += person.getDefense();
            goldCheck += person.getGold();
        }
        resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + woodCheck));
        resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + foodCheck));
        resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + meatCheck));
        resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + rockCheck));
        resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + waterCheck));
        resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + clothesCheck));
        resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + furCheck));
        resourceMap.put(Enum.resourceType.gold, (resourceMap.get(Enum.resourceType.gold) + goldCheck));

        statsMap.put(Enum.stats.health, (statsMap.get(Enum.stats.health) + healthCheck));
        statsMap.put(Enum.stats.defense, (statsMap.get(Enum.stats.defense) + defenseCheck));

        notifyObserver("wood: " + woodCheck);
        notifyObserver("food: " + foodCheck);
        notifyObserver("meat: " + meatCheck);
        notifyObserver("rock: " + rockCheck);
        notifyObserver("water: " + waterCheck);
        notifyObserver("clothes: "+ clothesCheck);
        notifyObserver("fur: " + furCheck);
        notifyObserver("health: " + healthCheck);
        notifyObserver("defense: " + defenseCheck);
        notifyObserver("gold: " + goldCheck);
        if(woodCheck< 0 || foodCheck< 0 || meatCheck < 0||rockCheck< 0||waterCheck< 0||clothesCheck< 0||furCheck< 0||healthCheck< 0||defenseCheck< 0){
            notifyObserver("The villagers are unhappy, the village is not producing enough resources. If this continues, the villagers will start to leave.\n");
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

    public ArrayList<Enum.magicItems> getMagicItemsArrayList(){
        return magicItemsArrayList;
    }

    public void getVillagerCount(){
        jobMap.put(Enum.jobType.Villager, buildingMap.get(Enum.buildingType.Hut) * Helper.getJobLimit(Enum.buildingType.Hut));
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
