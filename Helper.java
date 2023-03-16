import java.util.ArrayList;
import java.util.Random;

/**
 * This class includes some of the helper functions used in FNCD
 */
public class Helper {

    /**
     * The function prints all inventory using String.format to make it look nice
     */
    public static void printInventory(ArrayList<Vehicle> inventory, ArrayList<Vehicle> soldCars){
        System.out.println(String.format("%35s %30s %20s %20s %20s %20s %30s", "Name", "Brand", "Cost", "Sale Price", "Condition", "Cleanliness", "Status"));
        for(Vehicle car: inventory){
            System.out.println(String.format("%35s %30s %20s %20s %20s %20s %30s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
        for(Vehicle car: soldCars){
            System.out.println(String.format("%35s %30s %20s %20s %20s %20s %30s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
    }

    /**
     * The function prints all inventory using String.format to make it look nice
     */
    public static void printInventory(ArrayList<Vehicle> inventory){
        System.out.println(String.format("%35s %30s %20s %20s %20s %20s %30s", "Name", "Brand", "Cost", "Sale Price", "Condition", "Cleanliness", "Status"));
        for(Vehicle car: inventory){
            System.out.println(String.format("%35s %30s %20s %20s %20s %20s %30s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
    }

    /**
     * The function print vehicle
     */
    public static void printVehicle(Vehicle vehicle){
    	
    	 System.out.println("Name: " + vehicle.getName());
    	 System.out.println("Brand: " + vehicle.getBrand());
    	 System.out.println("Cost: " + vehicle.getCost());
    	 System.out.println("Sale Price: " + vehicle.getSalePrice());
    	 System.out.println("Condition: " + vehicle.getCondition());
    	 System.out.println("Cleanliness: " + vehicle.getCleanliness());
    	 System.out.println("Status: " + vehicle.getStatus());
    }

    /**
     * The function print all employees using String.format to make it look nice
     */
    public static void printAllStaff(ArrayList<Interns> internList, ArrayList<Mechanics> mechanicsList, ArrayList<Salesperson> salespeopleList, ArrayList<Staff> employee, ArrayList<StaffDriver> staffDriverList){
        System.out.println(String.format("%20s %20s %20s %20s %15s", "Name", "Total Days Worked", "Total Normal Pay", "Total Bonus Pay", "Status"));
        for (Interns emp: internList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Mechanics emp: mechanicsList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Salesperson emp: salespeopleList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (StaffDriver emp: staffDriverList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Staff emp: employee){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked()+ " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
    }


    /**
     * The function finds the number of Buyers for the day
     * 0 - 5 buyers for weekdays, 2-8 buyers for weekends
     * @return the number of buyers for the day
     */
    public static int numOfBuyer(int date){
        int num;
        Random random = new Random();
        // checks for friday and Saturday
        if(date % 7 == 5 || date % 7 == 6){
            System.out.println("it is the weekend (Friday/Saturday)");
            num = random.nextInt(9-2) + 2; // since max is exclusive, we make max = 9 to include 8 buyer
        }
        else{
            num = random.nextInt(6); // weekdays
        }
        return num;

    }

}
