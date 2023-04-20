package project4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * The class vehicle contains all the variables, the getter and setter for the variables, it is uwsed as
 * part of the inheritance.
 */
public interface Vehicle {
    // possible condition and cleanliness
    static final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("like new", "used", "broken"));
    static final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    /**
     * setter for name
     * @param name: name of the car
     */
    void setName(String name);
    /**
     * setter for brand
     */
    void setBrand();
    /**
     * setter for cost, set cost based on condition
     */
    void setCost();
    /**
     * setter for saleprice based on fix
     * @param percentage: the percent modify the price by
     */
    void setSalePrice(double percentage);
    public double getPercent();
    /**
     * setter for sale bonus
     * @param saleBonus: the new sale bonus
     */
    public void setSaleBonus(int saleBonus);
    /**
     * setter for repair bonus
     * @param repairBonus: new repairbonus
     */
    public void setRepairBonus(int repairBonus);
    /**
     * setter for wash bonus
     * @param washBonus: new wash bonus
     */
    public void setWashBonus(int washBonus);
    /**
     * setter for condition
     * @param condition: new condition
     */
    public void setCondition(String condition);
    /**
     * setter for cleanliness
     * @param cleanliness: new cleanliness
     */
    public void setCleanliness(String cleanliness);

    /**
     * setter for status
     * @param status new status
     */
    public void setStatus(String status);

    /**
     * getter for name
     * @return name
     */
    public String getName();
    /**
     * getter for brand
     * @return brand of the car
     */
    public String getBrand();
    /**
     * getter for sale bonus
     * @return sale bonus
     */
    public int getSaleBonus();
    /**
     * getter for repair bonus
     * @return repair bonus
     */
    public int getRepairBonus();
    /**
     * getter for wash bonus
     * @param level: 1 or 2; 1 is normal, 2 is double the bonus for dirty to sparkling
     * @return the wash bonus
     */
    public int getWashBonus(int level);
    /**
     * getter for cost
     * @return cost
     */
    public int getCost();
    /**
     * getter for sale price
     * @return sale price
     */
    public int getSalePrice();
    /**
     * geter for condition
     * @return condition
     */
    public String getCondition();
    /**
     * getter for cleanliness
     * @return cleanliness
     */
    public String getCleanliness();
    /**
     * getter for status
     * @return status
     */
    public String getStatus();
    /**
     * getter for type
     * @return type
     */
    public Enum.VehicleType getType();

    /**
     * prints out the action of purchase
     */
    public void printAction(String name);

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
