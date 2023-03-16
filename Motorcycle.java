import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The class implements the motorcycle. The only different is a unique engine size
 */
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
    private Enum.VehicleType type;

    /**
     * Motorcycles have an unique Engine Size rating in cubic centimeters (cc).
     * This value should be generated from a
     * truncated Normal Distribution with mean 700 std. dev. 300.
     * In no case can cc be less than 50.
     */
    private int engineSize;
    private double percent;

    private int min = 10000;
    private int max = 20000;
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Honda", "Lucid Motors", "Yamaha", "Suzuki", "Kawasaki", "BMW", "Ducati", "Triumph", "KTM"));

    public Motorcycle(String id){

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
        type = Enum.VehicleType.Motorcycles;
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
        percent = 1;
    }
    
    @Override
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }

    @Override
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
        return (int) (this.salePrice * this.percent);
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
        if(status.equals("sold")){
            return "sold";
        }
        return this.status + "(engine size: " + engineSize + ")";
    }
    
    @Override
    public Enum.VehicleType getType(){
        return this.type;
    }
    
    public double getPercent(){
        return this.percent;
    }
    
    @Override
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }
}
