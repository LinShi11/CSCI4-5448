package project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * The class vehicle contains all the variables, the getter and setter for the variables, it is uwsed as
 * part of the inheritance.
 */
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

    // possible condition and cleanliness
    static final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("like new", "used", "broken"));
    static final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    /**
     * setter for name
     * @param name: name of the car
     */
    public void setName(String name){
        this.name =name;
    }
    /**
     * setter for brand
     */
    public void setBrand(){
        this.brand = null;
    }
    /**
     * setter for cost, set cost based on condition
     */
    public void setCost(){
        this.cost = 0;
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
    public void setSaleBonus(int saleBonus){
        this.saleBonus = saleBonus;
    }
    /**
     * setter for repair bonus
     * @param repairBonus: new repairbonus
     */
    public void setRepairBonus(int repairBonus){
        this.repairBonus = repairBonus;
    }
    /**
     * setter for wash bonus
     * @param washBonus: new wash bonus
     */
    public void setWashBonus(int washBonus){
        this.washBonus = washBonus;
    }
    /**
     * setter for condition
     * @param condition: new condition
     */
    public void setCondition(String condition){
        this.condition = condition;
    }
    /**
     * setter for cleanliness
     * @param cleanliness: new cleanliness
     */
    public void setCleanliness(String cleanliness){
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
    public String getName(){
        return this.name;
    }
    /**
     * getter for brand
     * @return brand of the car
     */
    public String getBrand(){
        return this.brand;
    }
    /**
     * getter for sale bonus
     * @return sale bonus
     */
    public int getSaleBonus(){
        return this.saleBonus;
    }
    /**
     * getter for repair bonus
     * @return repair bonus
     */
    public int getRepairBonus(){
        return this.repairBonus;
    }
    /**
     * getter for wash bonus
     * @param level: 1 or 2; 1 is normal, 2 is double the bonus for dirty to sparkling
     * @return the wash bonus
     */
    public int getWashBonus(int level){
        return level*washBonus;
    }
    /**
     * getter for cost
     * @return cost
     */
    public int getCost(){
        return this.cost;
    }
    /**
     * getter for sale price
     * @return sale price
     */
    public int getSalePrice(){
        return this.salePrice;
    }
    /**
     * geter for condition
     * @return condition
     */
    public String getCondition(){
        return this.condition;
    }
    /**
     * getter for cleanliness
     * @return cleanliness
     */
    public String getCleanliness(){
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

    /**
     * prints out the action of purchase
     */
    public void printAction(){
        System.out.println("Purchased a " + getCondition() + ", " + getCleanliness() + " Car " + getBrand() + ", (" + getName() + ") for $" + getCost());
    }

    /**
     * getter for the arraylist of condition
     * @return ArrayList of all possible conditions
     */
    public static ArrayList<String> getPossibleConditions(){
        return possibleConditions;
    }

    /**
     * getter for the arrayList of cleanliness
     * @return arrayList of all possible cleanliness
     */
    public static ArrayList<String> getPossibleCleanliness(){
        return possibleCleanliness;
    }

}
