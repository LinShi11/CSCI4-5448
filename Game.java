import org.junit.runner.manipulation.Ordering;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    HashMap<Enum.resourceType, Integer> resourceMap;
    HashMap<Enum.buildingType, Integer> buildingMap;

    HashMap<Enum.jobType, Integer> jobMap;
    ArrayList<Enum.magicItems> magicItemsArrayList;
    ArrayList<Enum.resourceType> dailyAgenda;
    HashMap<Enum.jobType, Integer> jobLimit;
    ArrayList<People> peopleArrayList;
    int totalMagicItemCount = 10;
    UserActions userActions;
    BuildingFactory buildingFactory = new BuildingFactory();
    JobFactory jobFactory = new JobFactory();

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



        magicItemsArrayList = new ArrayList<>();
        dailyAgenda = new ArrayList<>();
        peopleArrayList = new ArrayList<>();

        for(int i = 0; i < 10; i ++){
            peopleArrayList.add(jobFactory.assignJob(Enum.jobType.Villager));
        }
        userActions = new UserActions();

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
        if(building.getType() == Enum.buildingType.Tradecart){
            buildingMap.put(Enum.buildingType.Tradecart, 1);
        } else {
            buildingMap.put(building.getType(), (buildingMap.get(building.getType()) + 1));
        }
        getVillagerCount();
    }
    public void assignJobs(Enum.jobType type){
        if(jobMap.get(Enum.jobType.Villager) > 0) {
            People person = jobFactory.assignJob(type);
            peopleArrayList.add(person);
            jobMap.put(person.getType(), (jobMap.get(person.getType()) + 1));
            for(People p: peopleArrayList){
                if(p.getType() == Enum.jobType.Villager){
                    peopleArrayList.remove(p);
                    break;
                }
            }
            jobMap.put(Enum.jobType.Villager, (jobMap.get(Enum.jobType.Villager)) - 1);
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

        }
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
        for(People person: peopleArrayList){
            resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + person.getWood()));
            resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + person.getFood()));
            resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + person.getMeat()));
            resourceMap.put(Enum.resourceType.rock, (resourceMap.get(Enum.resourceType.rock) + person.getRock()));
            resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + person.getWater()));
            resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + person.getClothes()));
            resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + person.getFur()));
        }
        System.out.println(resourceMap.get(Enum.resourceType.wood));
    }

    public HashMap<Enum.buildingType, Integer> getBuildingMap(){
        return buildingMap;
    }

    public HashMap<Enum.jobType, Integer> getJobMap(){
        return jobMap;
    }

    public ArrayList<Enum.magicItems> getMagicItemsArrayList(){
        return magicItemsArrayList;
    }

    public void getVillagerCount(){
        jobMap.put(Enum.jobType.Villager, buildingMap.get(Enum.buildingType.Hut) * Helper.getLimit(Enum.buildingType.Hut));
    }

    public void setJobLimit(){
        jobLimit.put(Enum.jobType.Trapper, buildingMap.get(Enum.buildingType.Trap) * Helper.getLimit(Enum.buildingType.Trap));
        jobLimit.put(Enum.jobType.Waterman, buildingMap.get(Enum.buildingType.Bucket) * Helper.getLimit(Enum.buildingType.Bucket));
        jobLimit.put(Enum.jobType.Weaponsmith, buildingMap.get(Enum.buildingType.Blacksmith) * Helper.getLimit(Enum.buildingType.Blacksmith));
        jobLimit.put(Enum.jobType.Cook, buildingMap.get(Enum.buildingType.Smokehouse) * Helper.getLimit(Enum.buildingType.Smokehouse));
        jobLimit.put(Enum.jobType.Tailor, buildingMap.get(Enum.buildingType.Factory) * Helper.getLimit(Enum.buildingType.Factory));
        jobLimit.put(Enum.jobType.Miner, buildingMap.get(Enum.buildingType.Mines) * Helper.getLimit(Enum.buildingType.Mines));
    }
}
