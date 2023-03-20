package project4;

import java.util.*;
import java.util.Random;

/**
 * The buyer class is used for the Buyers. The types of vehicle, how badly they want the vehicle are all included in this class
 */
public class Buyer {
    private String buyingChance;
    private String vehicleType;
    //all the possible buying status in both arrayList and key-value pairs
    final ArrayList<String> buying = new ArrayList<>(Arrays.asList("just looking", "wants one", "needs one"));
    final Map<String, Integer> probability = Map.of("just looking", 10, "wants one", 40, "needs one", 70);

    // the different vehicle types
    final ArrayList<String> types = new ArrayList<>(Arrays.asList("performance car", "car", "pickup", "electric car", "motorcycle", "monster truck"));

    /**
     * constructor that determines at random what type of car the buyer would like and how much they want it.
     */
    public Buyer(){
        Random random = new Random();
        this.buyingChance = buying.get(random.nextInt(3));
        this.vehicleType = types.get(random.nextInt(6));
    }

    /**
     * getter for buying chance
     * @return the buying chance as a string
     */
    public String getBuyingChance() {
        return buyingChance;
    }

    /**
     * getter for vehicle type
     * @return the vehicle type as a string
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * finds the probability given the buying chance
     * @param desire: buying chance as a string (key)
     * @return the value according to buying chance using the map 
     */
    public int getProbability(String desire){
        return probability.get(desire);
    }
}

