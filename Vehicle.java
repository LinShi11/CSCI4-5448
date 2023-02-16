import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Vehicle {

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
    static final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("like new", "used", "broken"));
    static final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    public void setName(String name){
        this.name =name;
    }
    public void setBrand(){
        this.brand = null;
    }
    public void setCost(){
        this.cost = 0;
    }
    public void setSalePrice(double percentage){
        this.salePrice *= percentage;
    }
    public void setSaleBonus(int saleBonus){
        this.saleBonus = saleBonus;
    }
    public void setRepairBonus(int repairBonus){
        this.repairBonus = repairBonus;
    }
    public void setWashBonus(int washBonus){
        this.washBonus = washBonus;
    }
    public void setCondition(String condition){
        this.condition = condition;
    }
    public void setCleanliness(String cleanliness){
        this.cleanliness = cleanliness;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getName(){
        return this.name;
    }
    public String getBrand(){
        return this.brand;
    }
    public int getSaleBonus(){
        return this.saleBonus;
    }
    public int getRepairBonus(){
        return this.repairBonus;
    }
    public int getWashBonus(){
        return this.washBonus;
    }
    public int getCost(){
        return this.cost;
    }
    public int getSalePrice(){
        return this.salePrice;
    }
    public String getCondition(){
        return this.condition;
    }
    public String getCleanliness(){
        return this.cleanliness;
    }
    public String getStatus(){
        return this.status;
    }
    public String getType(){
        return this.type;
    }
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }

    public static ArrayList<String> getPossibleConditions(){
        return possibleConditions;
    }

    public static ArrayList<String> getPossibleCleanliness(){
        return possibleCleanliness;
    }

}
