import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElectricCar implements Vehicle{
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
    private double percent;
    private int winCount;

    /*
     * Electric Cars have a unique Range attribute,
     * initially set randomly from 60 to 400 miles.
     * If an Electric Car arrives  as Like New or is repaired to Like New,
     * Range increases by 100 miles
     */
    private int range;

    private int min = 10000;
    private int max = 20000;
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Tesla", "Lucid Motors", "Zoox", "Aptiv", "Rivian", "NIO", "Nissan", "Volvo"));
    

    public ElectricCar(String id){
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
        setName(id);
        repairBonus = (int)(min * .10);
        saleBonus = (int)(min * 0.08);
        washBonus = (int)(min * 0.01);

        range = random.nextInt(341) + 60;
        if (condition.contains("new")) {
            range += 100;
        }
        percent = 1;
    }
    
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }

    public void setBrand(){
        Random random = new Random();
        this.brand = brands.get(random.nextInt(brands.size()));
    }
    
    public void setCost(){
        if(this.condition.equals("used")){
            this.cost *= 0.8;
        } else if(this.condition.equals("broken")){
            this.cost *= 0.5;
        }
    }
    
    public void setSalePrice(double percentage){
        this.salePrice *= percentage;
    }

    
    public void setSaleBonus(int saleBonus) {
        this.saleBonus = saleBonus;
    }

    
    public void setRepairBonus(int repairBonus) {
        this.repairBonus = repairBonus;
    }

    
    public void setWashBonus(int washBonus) {
        this.washBonus = washBonus;
    }


    
    public void setCondition(String condition) {
        this.condition = condition;

        if (condition.contains("new")) {
            range += 100;
        }
    }

    
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }


    public void setStatus(String status){
        this.status = status;
    }

    
    public void setWinCount() {
        winCount = 0;
    }

    
    public double getPercent(){
        return percent;
    }
    
    public String getName() {
        return this.name;
    }

    
    public String getBrand() {
        return brand;
    }

    
    public int getSaleBonus() {
        return this.saleBonus;
    }

    
    public int getRepairBonus() {
        return this.repairBonus;
    }

    
    public int getWashBonus(int level) {
        if(level == 1){
            return this.washBonus;
        } else{
            return this.washBonus *2;
        }
    }

    
    public int getCost() {
        return this.cost;
    }

    
    public int getSalePrice() {
        return (int) (this.salePrice * this.percent);
    }

    
    public String getCondition() {
        return this.condition;
    }

    
    public String getCleanliness() {
        return this.cleanliness;
    }
    
    public String getStatus(){
        return this.status + "(range: "  + range + ")";
    }
    
    public String getType(){
        return this.type;
    }
    
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }
}
