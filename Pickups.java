import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The pickup class includes the getter/setter for all variables,
 * The name is determined uniquely.
 * This is an example of abstraction. As pickup extends from vehicle, we can overwrite many of the functions.
 */
public class Pickups implements Vehicle{
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

    private double percent;
    // min and max cost
    private int min = 10000;
    private int max = 40000;
    // possible brands
    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Ford", "GMC", "Chevrolet", "Ram", "Jeep"));
    /**
     * constructor for pickups
     * @param id: the id for car
     */
    public Pickups(String id){
        // randomly assign variables
        Random random = new Random();
        cost = random.nextInt(max - min) + min;
        condition = Vehicle.getPossibleConditions().get(random.nextInt(3));
        setCost();
        salePrice = cost * 2;
        int temp = random.nextInt(100);
        if(temp < 5){
            cleanliness = Vehicle.getPossibleCleanliness().get(0);
        } else if (temp < 40){
            cleanliness = Vehicle.getPossibleCleanliness().get(1);
        } else{
            cleanliness = Vehicle.getPossibleCleanliness().get(2);
        }

        // assign other variables
        status = "in stock";
        type = Enum.VehicleType.Pickups;
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

    @Override
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }
    /**
     * setter for brand, randomly choose a brand
     */

    @Override
    public void setBrand() {
        Random random = new Random();
        this.brand = brands.get(random.nextInt(brands.size()));
    }
    /**
     * setter for cost, set cost based on condition
     */

    @Override
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

    @Override
    public void setSalePrice(double percentage){
        this.salePrice *= percentage;
    }
    /**
     * setter for sale bonus
     * @param saleBonus: the new sale bonus
     */

    @Override
    public void setSaleBonus(int saleBonus) {
        this.saleBonus = saleBonus;
    }

    /**
     * setter for repair bonus
     * @param repairBonus: new repairbonus
     */

    @Override
    public void setRepairBonus(int repairBonus) {
        this.repairBonus = repairBonus;
    }
    /**
     * setter for wash bonus
     * @param washBonus: new wash bonus
     */

    @Override
    public void setWashBonus(int washBonus) {
        this.washBonus = washBonus;
    }
    /**
     * setter for condition
     * @param condition: new condition
     */

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }
    /**
     * setter for cleanliness
     * @param cleanliness: new cleanliness
     */

    @Override
    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }

    /**
     * setter for status
     * @param status new status
     */

    @Override
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * getter for name
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * getter for brand
     * @return brand of the car
     */

    @Override
    public String getBrand() {
        return brand;
    }
    /**
     * getter for sale bonus
     * @return sale bonus
     */

    @Override
    public int getSaleBonus() {
        System.out.println(saleBonus);
        return this.saleBonus;
    }
    /**
     * getter for repair bonus
     * @return repair bonus
     */

    @Override
    public int getRepairBonus() {
        return this.repairBonus;
    }
    /**
     * getter for wash bonus
     * @param level: 1 or 2; 1 is normal, 2 is double the bonus for dirty to sparkling
     * @return the wash bonus
     */

    @Override
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

    @Override
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

    @Override
    public String getCondition() {
        return this.condition;
    }
    /**
     * getter for cleanliness
     * @return cleanliness
     */

    @Override
    public String getCleanliness() {
        return this.cleanliness;
    }
    /**
     * getter for status
     * @return status
     */

    @Override
    public String getStatus(){
        return this.status;
    }
    /**
     * getter for type
     * @return type
     */

    @Override
    public Enum.VehicleType getType(){
        return this.type;
    }


    @Override
    public void printAction(String name) {
        System.out.println(name + " Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }


    public double getPercent(){
        return this.percent;
    }
}
