import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    HashMap<Enum.resourceType, Integer> resourceMap;
    HashMap<Enum.buildingType, Integer> buildingMap;

    HashMap<String, Integer> jobMap;
    ArrayList<Enum.magicItems> magicItemsArrayList;
    ArrayList<Enum.resourceType> dailyAgenda;
    int totalMagicItemCount = 10;
    UserActions userActions;
    BuildingFactory buildingFactory;

    public Game(){
        resourceMap = new HashMap<>();
        resourceMap.put(Enum.resourceType.food, 0);
        resourceMap.put(Enum.resourceType.meat, 0);
        resourceMap.put(Enum.resourceType.wood, 0);
        resourceMap.put(Enum.resourceType.water, 0);
        resourceMap.put(Enum.resourceType.fur, 0);
        resourceMap.put(Enum.resourceType.rock, 0);
        resourceMap.put(Enum.resourceType.clothes, 0);

        buildingMap = new HashMap<>();
        buildingMap.put(Enum.buildingType.Trap, 0);
        buildingMap.put(Enum.buildingType.Bucket, 0);
        buildingMap.put(Enum.buildingType.Smokehouse, 0);
        buildingMap.put(Enum.buildingType.Factory, 0);
        buildingMap.put(Enum.buildingType.Blacksmith, 0);
        buildingMap.put(Enum.buildingType.Mines, 0);
        buildingMap.put(Enum.buildingType.Tradecart, 0);
        buildingMap.put(Enum.buildingType.Hut, 0);

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

        magicItemsArrayList = new ArrayList<>();
        dailyAgenda = new ArrayList<>();

        userActions = new UserActions();

        buildingFactory = new BuildingFactory();

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

    public ArrayList<Enum.resourceType> getDailyAgenda(){
        return dailyAgenda;
    }

    public int getTotalMagicItemCount(){
        return totalMagicItemCount;
    }

    public HashMap<Enum.resourceType, Integer> getResourceMap(){
        return resourceMap;
    }
    public void addBuildings(Enum.buildingType type){
        Building building = buildingFactory.constructBuilding(type);
        buildingMap.put(building.getType(), (buildingMap.get(building.getType()) + 1));
    }

    public void dailyUpdate(){
        for(Enum.resourceType agenda: dailyAgenda){
            switch (agenda){
                case wood:
                    resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + userActions.getWood()));
                    break;
                case food:
                    resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + userActions.getFood()));
                    break;
                case meat:
                    resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + userActions.getMeat()));
                    break;
                case rock:
                    resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + userActions.getRock()));
                    break;
                case water:
                    resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + userActions.getWater()));
                    break;
                case clothes:
                    resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + userActions.getClothes()));
                    break;
                case fur:
                    resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + userActions.getFur()));
                    break;
                default:
                    System.out.println("nothing");
            }
        }
    }

    public HashMap<Enum.buildingType, Integer> getBuildingMap(){
        return buildingMap;
    }

    public HashMap<String, Integer> getJobMap(){
        return jobMap;
    }

    public ArrayList<Enum.magicItems> getMagicItemsArrayList(){
        return magicItemsArrayList;
    }
}
