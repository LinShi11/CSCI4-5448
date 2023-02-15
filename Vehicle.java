import java.util.ArrayList;
import java.util.Arrays;

public interface Vehicle {
    final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("new", "used", "broken"));
    final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    void setName(String name);
    void setSaleBonus(int saleBonus);
    void setRepairBonus(int repairBonus);
    void setWashBonus(int washBonus);
    void setSalePrice(int salePrice);
    void setCondition(String condition);
    void setCleanliness(String cleanliness);

    String getName();
    int getSaleBonus();
    int getRepairBonus();
    int getWashBonus();
    int getCost();
    int getSalePrice();
    String getCondition();
    String getCleanliness();

    static ArrayList<String> getPossibleConditions(){
        return possibleConditions;
    }

    static ArrayList<String> getPossibleCleanliness(){
        return possibleCleanliness;
    }



}
