import java.util.*;
import java.util.Random;
public class Buyer {
    private String buyingChance;
    private String vehicleType;
    final ArrayList<String> buying = new ArrayList<>(Arrays.asList("just looking", "wants one", "needs one"));
    final Map<String, Integer> probability = Map.of("just looking", 10, "wants one", 40, "needs one", 70);

    final ArrayList<String> types = new ArrayList<>(Arrays.asList("performance car", "car", "pickup"));

    public Buyer(){
        Random random = new Random();
        this.buyingChance = buying.get(random.nextInt(3));
        this.vehicleType = types.get(random.nextInt(3));
    }

    public String getBuyingChance() {
        return buyingChance;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getProbability(String desire){
        return probability.get(desire);
    }
}

