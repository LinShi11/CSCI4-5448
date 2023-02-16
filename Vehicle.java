import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public interface Vehicle {
    final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("like new", "used", "broken"));
    final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    void setName(String name);
    void setBrand();
    void setSaleBonus(int saleBonus);
    void setRepairBonus(int repairBonus);
    void setWashBonus(int washBonus);
    void setCondition(String condition);
    void setCleanliness(String cleanliness);
    void setStatus(String status);
    String getName();
    String getBrand();
    int getSaleBonus();
    int getRepairBonus();
    int getWashBonus();
    int getCost();
    int getSalePrice();
    String getCondition();
    String getCleanliness();
    String getStatus();
    void printAction();

    static ArrayList<String> getPossibleConditions(){
        return possibleConditions;
    }

    static ArrayList<String> getPossibleCleanliness(){
        return possibleCleanliness;
    }

}
