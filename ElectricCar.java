import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElectricCar extends Vehicle{
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

        if (condition.contains("new")) {
            range += 100;
        }
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
        return this.status + "(range: "  + range + ")";
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