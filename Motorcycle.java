import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Motorcycle implements Vehicle{
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

    /*
     * Motorcycles have an unique Engine Size rating in cubic centimeters (cc).
     * This value should be generated from a
     * truncated Normal Distribution with mean 700 std. dev. 300.
     * In no case can cc be less than 50.
     */
    private int engineSize;
    private int winCount;
    private double percent;

    private int min = 10000;
    private int max = 20000;
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Honda", "Lucid Motors", "Yamaha", "Suzuki", "Kawasaki", "BMW", "Ducati", "Triumph", "KTM"));

    public Motorcycle(String id){
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

        //https://www.javamex.com/tutorials/random_numbers/gaussian_distribution_2.shtml
        engineSize = (int)(random.nextGaussian() * 300 + 700);
        if (engineSize < 50) { //if the engine size is less than 50 it is equal to 50
            engineSize = 50;
        }

        winCount = 0;
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
    }

    
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setWinCount(){
        winCount++;
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
        if(winCount >= 1){
            salePrice *= 1.1;
            System.out.println("FNCD has at least one win with this type of vehicle");
        }
        return (int) (this.salePrice * this.percent);
    }

    
    public String getCondition() {
        return this.condition;
    }

    
    public String getCleanliness() {
        return this.cleanliness;
    }
    
    public String getStatus(){
        return this.status + "(engine size: " + engineSize + ")";
    }
    
    public String getType(){
        return this.type;
    }
    
    public double getPercent(){
        return this.percent;
    }
    
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }
}
