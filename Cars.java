import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The car class includes the getter/setter for all variables,
 * The name is determined uniquely.
 * An example of encapsulation. Where the variables are private. If we would like to access the variables, we have to use the public getter and setters.
 */
public class Cars implements Vehicle{
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

    // min and max price
    private int min = 10000;
    private int max = 20000;
    // possible brands
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Honda", "Toyota", "Mazda", "Hyundai", "Kia", "Nissan", "Subaru", "Volkswagen"));

    /**
     * constructor for cars
     * @param id: the id for car
     */
    public Cars(String id){
        // randomly find the car price
        Random random = new Random();
        cost = random.nextInt(max - min) + min;

        // find condition
        condition = Vehicle.getPossibleConditions().get(random.nextInt(3));
        // set the cost, price
        setCost();
        salePrice = cost *2;
        int temp = random.nextInt(100);
        // set cleanliness
        if(temp < 5){
            cleanliness = Vehicle.getPossibleCleanliness().get(0);
        } else if (temp < 40){
            cleanliness = Vehicle.getPossibleCleanliness().get(1);
        } else{
            cleanliness = Vehicle.getPossibleCleanliness().get(2);
        }

        // set other variables
        status = "in stock";
        type = "car";
        setBrand();
        setName(id);
        repairBonus = (int)(min * .10);
        saleBonus = (int)(min * 0.08);
        washBonus = (int)(min * 0.01);
        percent = 1;
    }

    /**
     * setter for name is first three letter of the brand + _ + id
     * @param name: name of the car
     */
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }

    /**
     * setter for brand, randomly choose a brand
     */
    public void setBrand(){
        Random random = new Random();
        this.brand = brands.get(random.nextInt(brands.size()));
    }

    /**
     * setter for cost, set cost based on condition
     */
    public void setCost(){
        if(this.condition.equals("used")){
            this.cost *= 0.8;
        } else if(this.condition.equals("broken")){
            this.cost *= 0.5;
        }
    }

    /**
     * setter for saleprice based on fix
     * @param percentage: the percent modify the price by
     */
    
    public void setSalePrice(double percentage){
        this.salePrice *= percentage;
    }

    /**
     * setter for sale bonus
     * @param saleBonus: the new sale bonus
     */
    
    public void setSaleBonus(int saleBonus) {
        this.saleBonus = saleBonus;
    }

    /**
     * setter for repair bonus
     * @param repairBonus: new repairbonus
     */
    
    public void setRepairBonus(int repairBonus) {
        this.repairBonus = repairBonus;
    }

    /**
     * setter for wash bonus
     * @param washBonus: new wash bonus
     */
    
    public void setWashBonus(int washBonus) {
        this.washBonus = washBonus;
    }

    /**
     * setter for condition
     * @param condition: new condition
     */
    
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * setter for cleanliness
     * @param cleanliness: new cleanliness
     */
    
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }


    /**
     * setter for status
     * @param status new status
     */
    
    public void setStatus(String status){
        this.status = status;
    }


    /**
     * getter for name
     * @return name
     */
    
    public String getName() {
        return this.name;
    }

    /**
     * getter for brand
     * @return brand of the car
     */
    
    public String getBrand() {
        return brand;
    }

    /**
     * getter for sale bonus
     * @return sale bonus
     */
    
    public int getSaleBonus() {
        return this.saleBonus;
    }

    /**
     * getter for repair bonus
     * @return repair bonus
     */
    
    public int getRepairBonus() {
        return this.repairBonus;
    }

    /**
     * getter for wash bonus
     * @param level: 1 or 2; 1 is normal, 2 is double the bonus for dirty to sparkling
     * @return the wash bonus
     */
    
    public int getWashBonus(int level) {
        if(level == 1){
            return this.washBonus;
        } else{
            return this.washBonus *2;
        }
    }

    /**
     * getter for cost
     * @return cost
     */
    
    public int getCost() {
        return this.cost;
    }

    /**
     * getter for sale price
     * @return sale price
     */
    
    @Override
    public int getSalePrice() {
        return (int) (this.salePrice * this.percent);
    }

    /**
     * geter for condition
     * @return condition
     */
    
    public String getCondition() {
        return this.condition;
    }

    /**
     * getter for cleanliness
     * @return cleanliness
     */
    
    public String getCleanliness() {
        return this.cleanliness;
    }

    /**
     * getter for status
     * @return status
     */
    
    public String getStatus(){
        return this.status;
    }

    /**
     * getter for type
     * @return type
     */
    
    public String getType(){
        return this.type;
    }

    
    public void printAction() {
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }

    
    public double getPercent(){
        return this.percent;
    }
}
