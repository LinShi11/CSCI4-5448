import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

public class MonsterTruck extends Vehicle{
    private String name;
    private int saleBonus;
    private int repairBonus;
    private int washBonus;
    private int cost;
    private int salePrice;
    private String condition;
    private String cleanliness;

    private String brand;
    private String status;
    private String type;

    private int min = 10000;
    private int max = 20000;
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Monster Jam Steel Titans 2", "Grave Digger", "Son-uva Digger"));

    final static ArrayList<String> names = new ArrayList<>(Arrays.asList(
    "Air Force Afterburner", "Avenger", 
    "Bad News Travels Fast", "Batman", 
    "Backwards Bob", "Bear Foot (1979)", 
    "Bear Foot (F-150)", "Bear Foot (2xtreme)", 
    "Bear Foot (Silverado)", "Bear Foot USA", 
    "Bigfoot", "Black Stallion", 
    "Blacksmith", "Blue Thunder", 
    "Bounty Hunter", "Brutus", 
    "Bulldozer", "Captain's Curse", 
    "Cyborg", "El Toro Loco", 
    "Grave Digger", "Grinder", 
    "Gunslinger", "Jurassic Attack", 
    "King Krunch", "Lucas Oil Crusader", 
    "Madusa", "Maximum Destruction (Max-D)", 
    "Mohawk Warrior", "Monster Mutt", 
    "Monster Mutt Dalmatian", "Predator", 
    "Shell Camino", "Raminator", 
    "Snake Bite", "Stone Crusher", 
    "Sudden Impact", "Swamp Thing", 
    "The Destroyer", "The Felon", 
    "USA-1", "War Wizard", "WCW Nitro Machine", 
    "Zombie"));
    
    private static Hashtable<String, Integer> usedNames = new Hashtable<>();
    
    public MonsterTruck(String id){
        //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        // https://www.geeksforgeeks.org/how-to-set-precision-for-double-values-in-java/

        Random random = new Random();
        cost = random.nextInt(max - min) + min;
        condition = Vehicle.getPossibleConditions().get(random.nextInt(3));
        setCost();
        salePrice = cost *2;
        int temp = random.nextInt(100);
        if(temp < 5){
            cleanliness = Vehicle.getPossibleCleanliness().get(0);
        } else if (temp < 40){
            cleanliness = Vehicle.getPossibleCleanliness().get(1);
        } else{
            cleanliness = Vehicle.getPossibleCleanliness().get(2);
        }
        status = "in stock";
        type = "car";
        setBrand();
        
        if (id == null || id.equals("") || names.contains(id)) {//id is not provided, get from the list
        	
        	id = names.get(random.nextInt(names.size()));        	
        }
        
        if (names.contains(id)) {
        	
        	if (usedNames.containsKey(id)) { //name is used
        		int nextNum = usedNames.get(id) + 1; 
        		usedNames.put(id, nextNum);//increase by 1
        		
 	        	id = id + " " + nextNum; 	        	
 	        }else {
 	        	usedNames.put(id, 1); //first used
 	        }        	
        }
        
        setName(id);
        
        repairBonus = (int)(min * .10);
        saleBonus = (int)(min * 0.08);
        washBonus = (int)(min * 0.01);

        
    }
    @Override
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }

    public void setBrand(){
        Random random = new Random();
        this.brand = brands.get(random.nextInt(brands.size()));
    }
    @Override
    public void setCost(){
        if(this.condition.equals("used")){
            this.cost *= 0.8;
        } else if(this.condition.equals("broken")){
            this.cost *= 0.5;
        }
    }
    @Override
    public void setSalePrice(double percentage){
        this.salePrice *= percentage;
    }

    @Override
    public void setSaleBonus(int saleBonus) {
        this.saleBonus = saleBonus;
    }

    @Override
    public void setRepairBonus(int repairBonus) {
        this.repairBonus = repairBonus;
    }

    @Override
    public void setWashBonus(int washBonus) {
        this.washBonus = washBonus;
    }


    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }
    @Override
    public void setStatus(String status){
        this.status = status;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public int getSaleBonus() {
        return this.saleBonus;
    }

    @Override
    public int getRepairBonus() {
        return this.repairBonus;
    }

    @Override
    public int getWashBonus(int level) {
        if(level == 1){
            return this.washBonus;
        } else{
            return this.washBonus *2;
        }
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getSalePrice() {
        return this.salePrice;
    }

    @Override
    public String getCondition() {
        return this.condition;
    }

    @Override
    public String getCleanliness() {
        return this.cleanliness;
    }
    @Override
    public String getStatus(){
        return this.status;
    }
    @Override
    public String getType(){
        return this.type;
    }
//    @Override
//    public void printAction(){
//        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
//    }
}
