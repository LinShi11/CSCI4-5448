import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    HashMap<String, Integer> resourceMap;
    HashMap<String, Integer> buildingMap;

    HashMap<String, Integer> jobMap;
    ArrayList<Enum.magicItems> magicItemsArrayList;
    int totalMagicItemCount = 10;

    public Game(){
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

        magicItemsArrayList = new ArrayList<>();
    }
    public int getTotalMagicItemCount(){
        return totalMagicItemCount;
    }

    public HashMap<String, Integer> getResourceMap(){
        return resourceMap;
    }

    public HashMap<String, Integer> getBuildingMap(){
        return buildingMap;
    }

    public HashMap<String, Integer> getJobMap(){
        return jobMap;
    }

    public ArrayList<Enum.magicItems> getMagicItemsArrayList(){
        return magicItemsArrayList;
    }
}
