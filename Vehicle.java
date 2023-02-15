import java.util.ArrayList;
import java.util.Arrays;


public interface Vehicle {
    final ArrayList<String> possibleConditions = new ArrayList<>(Arrays.asList("new", "used", "broken"));
    final ArrayList<String> possibleCleanliness = new ArrayList<>(Arrays.asList("sparkling", "clean", "dirty"));
    void setName(String name);
    void setSaleBonus(double saleBonus);
    void setRepairBonus(double repairBonus);
    void setWashBonus(double washBonus);
    void setSalePrice(double salePrice);
    void setCondition(String condition);
    void setCleanliness(String cleanliness);

    String getName();
    double getSaleBonus();
    double getRepairBonus();
    double getWashBonus();
    double getCost();
    double getSalePrice();
    String getCondition();
    String getCleanliness();

    static ArrayList<String> getPossibleConditions(){
        

        return possibleConditions;
    }


        
                
       
    
  

    static ArrayList<String> getPossibleCleanliness(){
        return possibleCleanliness;
    }



}
