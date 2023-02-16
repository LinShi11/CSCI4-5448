import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    private String type;
    private int min = 10000;
    private int max = 40000;

    final ArrayList<String> brands = new ArrayList<>(Arrays.asList("Ford", "GMC", "Chevrolet", "Ram", "Jeep"));

    public Pickups(String id){
        //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        // https://www.geeksforgeeks.org/how-to-set-precision-for-double-values-in-java/

        Random random = new Random();
        cost = random.nextInt(max - min) + min;
        condition = Vehicle.getPossibleConditions().get(random.nextInt(3));
        int temp = random.nextInt(100);
        if(temp < 5){
            cleanliness = Vehicle.getPossibleCleanliness().get(0);
        } else if (temp < 40){
            cleanliness = Vehicle.getPossibleCleanliness().get(1);
        } else{
            cleanliness = Vehicle.getPossibleCleanliness().get(2);
        }
        status = "in stock";
        type = "pickup";
        setBrand();
        setName(id);
        printAction();
    }
    @Override
    public void setName(String name) {
        this.name = this.getBrand().substring(0,3).toUpperCase() + "_" + name;
    }

    @Override
    public void setBrand() {
        Random random = new Random();
        this.brand = brands.get(random.nextInt(brands.size()));
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
    public int getWashBonus() {
        return this.washBonus;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getSalePrice() {
        this.salePrice = this.cost * 2;
        if(getCondition().equals("used")){
            this.salePrice *= 0.8;
        }
        else if (getCondition().equals("broken")){
            this.salePrice *= 0.5;
        }
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
    @Override
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Pickup " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }
}
