import java.util.ArrayList;
import java.util.Random;

/**
 * FNCD holds the simulation for the program.
 */

public class FNCD {
    // final variable for number of employees(each type) and number of vehicles(each type)
    final int maxSize = 3;
    final int maxInventory = 4;
    int budget;
    int dailySales;
    int date;
    int simTime;
    int id;
    int inventoryId;
    ArrayList<Vehicle> inventory;
    ArrayList<PerformanceCar> performanceCarList;
    ArrayList<Cars> carsList;
    ArrayList<Pickups> pickupsList;
    ArrayList<Vehicle> soldCars;
    ArrayList<Staff> employee;
    ArrayList<Interns> internList;
    ArrayList<Mechanics> mechanicsList;
    ArrayList<Salesperson> salespeopleList;

    /**
     * Constructor for FNCD class
     * Initialize all variable that will be used in the simulation
     */
    public FNCD(){
        this.budget = 500000;
        this.simTime = 30;
        this.date = 1;
        this.id = 1;
        this.inventoryId = 1;

        // arraylist that will be used to hold information
        this.internList = new ArrayList<>();
        this.mechanicsList = new ArrayList<>();
        this.salespeopleList = new ArrayList<>();
        this.employee = new ArrayList<>();
        this.performanceCarList = new ArrayList<>();
        this.carsList = new ArrayList<>();
        this.pickupsList = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.soldCars = new ArrayList<>();

        // directly hire 3 interns, 3 mechanics, and 3 salesperson
        for(int i = 0; i < maxSize; i++){
            internList.add(new Interns("Intern_" + updateId()));
            mechanicsList.add(new Mechanics("Mechanics_"+ updateId()));
            salespeopleList.add(new Salesperson("Salesperson_" + updateId()));
        }
    }


    /**
     * Simulation function that simulate for 30 days.
     * Prints the final staff and inventory when simulation is completed
     */
    public void simulation(){

        while(date <= simTime){
            // check for sundays and call startDay()/endDay() if it is not sunday
            if(date % 7 != 0) {
                System.out.println("******FNCD Day " + this.date + "******");
                startDay();
                endDay();
            } else{
                System.out.println("******FNCD Day " + this.date + "******");
                System.out.println("We are closed on Sunday");
            }
            date++;
        }

        //end of simulation, prints the details
        System.out.println("\n******End of simulation******");
        System.out.println("Here is a list of all the staffs: ");
        printAllStaff();
        System.out.println("\nHere is a list of all the vehicles: ");
        printInventory();
    }

    /**
     * This function includes everything we need to do to start the day
     * hire interns, buy more vehicles, complete tasks (washing, repairing, selling), and checks to make sure we have enough money
     */
    public void startDay(){
        System.out.println("Opening... (Current budget $" + this.budget + ")");
        this.dailySales = 0;
        hire();
        setInventory();
        tasks();
        noMoney();
    }

    /**
     * hire function checks to make sure we have 3 interns.
     * If not, we will hire more interns
     */
    public void hire(){
        if(internList.size() != maxSize){
            // iterate maxSize(3) - currentSize so we can add more interns
            int tempLength = internList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                //updateId is a helper function that keeps track of number of staffs we have hired
                internList.add(new Interns("Intern_" + updateId()));
                System.out.println("Hired intern " + internList.get(internList.size()-1).getName());
            }
        }
    }

    /**
     * setInventory function checks each type of vehicles and buy more if necessary.
     * Three different arrayList are used so the checking process is easier.
     */
    public void setInventory(){
        int tempLength = performanceCarList.size();
        for(int i = 0; i < maxInventory - tempLength; i++ ){
            PerformanceCar car = new PerformanceCar(updateInventoryId());
            performanceCarList.add(car);
            // helper function to modify budge, inventory, and print. To make thing clearer
            setInventoryHelper(car);
        }
        tempLength = carsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            Cars car = new Cars(updateInventoryId());
            carsList.add(car);
            setInventoryHelper(car);
        }
        tempLength = pickupsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            Pickups car = new Pickups(updateInventoryId());
            pickupsList.add(car);
            setInventoryHelper(car);
        }
        System.out.println();
    }

    /**
     * Helper function for setInventory.
     * adjust the budget, adds the car to our inventory, and print the action.
     * @param car: the car that we have just bought.
     *
     */
    public void setInventoryHelper(Vehicle car){
        this.budget -= car.getCost();
        this.inventory.add(car);
        car.printAction();
    }

    /**
     * Tasks function keep track of the washing, repairing, and selling tasks
     * We also get the number of buyer for each day.
     */
    public void tasks(){
        System.out.println("Washing...");
        washing();
        System.out.println("\nRepairing...");
        repairing();

        //find the number of buyer for the day
        int buyer = numOfBuyer();
        System.out.println("\nWe have " + buyer + " Buyers today");
        System.out.println("Selling...");
        selling(buyer);
    }

    /**
     * washing function that iterate through each intern and ask them to wash two cars.
     */
    public void washing(){
        for (Interns emp: internList){
            emp.setDailyBonus(0);
            emp.wash(inventory);
        }
    }

    /**
     * repairing function that iterate through each mechanic and ask them to repair two cars.
     */
    public void repairing(){
        for(Mechanics emp: mechanicsList){
            emp.repair(inventory);
        }
    }

    /**
     * Selling function. Given the number of buyers, randomly assign a saleperson to the buyer.
     * Then compute whether they will buy the vehicle or not.
     * Finally, adjust budget, dailysales, and the arrayLists accordingly.
     * @param buyer: the number of buyer for the day
     */
    public void selling(int buyer){
        Random random = new Random();
        Salesperson representative;

        //iterate through number of buyers
        for(int i =0; i < buyer; i++){
            Buyer newBuyer = new Buyer();

            //randomly choose a salesperson
            representative = salespeopleList.get(random.nextInt(3));

            //call the sale function in SalePerson, the function will return the car the buyer was looking at
            Vehicle car = representative.sale(newBuyer, inventory);

            //look at the status of the car the saleperson recommended. If it is sold then update the variables
            if(car.getStatus().equals("sold")){
                soldCars.add(car); // add the soldcar list
                inventory.remove(car);
                this.budget += car.getSalePrice();
                this.dailySales += car.getSalePrice();

                // remove them from the appropriate arraylist
                if(car.getType().equals("performance car")){
                        performanceCarList.remove(car);
                } else if(car.getType().equals("car")){
                        carsList.remove(car);
                } else{
                        pickupsList.remove(car);
                }
            }
        }
    }

    /**
     * end of the day function.
     * Updates the employees, checks the budget, and determine whether anyone quit.
     */
    public void endDay(){
        System.out.println("\nClosing...");
        System.out.println("We made $" + this.dailySales+ " today");
        dailyUpdate();
        noMoney();
        System.out.println("\nQuitting");
        quit();
    }

    /**
     * dailyUpdate required for each employee, such as days worked, total pay, and bonus
     */
    public void dailyUpdate(){
        for(Interns staff: internList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
        }
        for(Mechanics staff: mechanicsList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
        }
        for(Salesperson staff: salespeopleList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
        }
    }

    /**
     * noMoney function that checks for budget and add more money if we have 0 or less
     */
    public void noMoney(){
        while(this.budget <= 0){
            this.budget += 250000;
            System.out.println("You ran out of money, so you borrowed $250,000 from the bank");
        }
    }

    /**
     * quit function that check if any staff has quit.
     * If intern quit, simply remove them.
     * If the mechanic or Salesperson quit, have one of the intern step up to take the job
     */
    public void quit(){
        Random random = new Random();
        // 10% chance of quitting
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);

            //add them to a list of past employees
            employee.add(internList.get(temp));
            internList.remove(temp);

            // display the information to the user using a helper function
            quitHelper("Intern");
        }
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(mechanicsList.get(temp));
            mechanicsList.remove(temp);
            quitHelper("Mechanics");

            // ask one of the intern to step up
            Interns steppedUp = internList.get(0);

            // extract their unique id and use Mechanics_<id> as the new name and pass in the number of days they have worked already
            String name = steppedUp.getName().split("_")[1];
            Mechanics newMechanics = new Mechanics("Mechanics_"+name, steppedUp.getTotalDaysWorked());

            //modify arraylists
            mechanicsList.add(newMechanics);
            internList.remove(0);

            // announce event
            System.out.println("Intern " + steppedUp.getName() + " has stepped up and took the mechanics job");
        }
        // same as mechanics
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(salespeopleList.get(temp));
            salespeopleList.remove(temp);
            quitHelper("Salesperson");

            Interns steppedUp = internList.get(0);
            String name = steppedUp.getName().split("_")[1];
            Salesperson newSalesperson = new Salesperson("Salesperson_"+name, steppedUp.getTotalDaysWorked());
            salespeopleList.add(newSalesperson);
            internList.remove(0);

            System.out.println("Intern " + steppedUp.getName() + " has stepped up and took the salesperson job");

        }
    }

    /**
     * Helper class for quit to announce the event
     * @param title: the title of the staff who has quit
     */
    public void quitHelper(String title){
        // change status
        employee.get(employee.size()-1).setStatus("quit");
        System.out.println(title + " " + employee.get(employee.size()-1).getName() + " has quit after working for " + employee.get(employee.size()-1).getTotalDaysWorked() + " days");
    }

    /**
     * The function finds the number of Buyers for the day
     * 0 - 5 buyers for weekdays, 2-8 buyers for weekends
     * @return the number of buyers for the day
     */
    public int numOfBuyer(){
        int num;
        Random random = new Random();
        // checks for friday and Saturday
        if(this.date % 7 == 5 || this.date % 7 == 6){
            System.out.println("it is the weekend (Friday/Saturday)");
            num = random.nextInt(9-2) + 2; // since max is exclusive, we make max = 9 to include 8 buyer
        }
        else{
            num = random.nextInt(6); // weekdays
        }
        return num;

    }

    /**
     * helper function to keep track the staff id such as 001
     * @return the id in string type and in 3 places.
     */
    public String updateId(){
        return String.format("%03d", id++); // return and update
    }

    /**
     * helper function to keep track of the inventory id as 00001
     * @return the id in string type and in 5 places
     */
    public String updateInventoryId(){
        return String.format("%05d", inventoryId++);
    }

    /**
     * The function print all employees using String.format to make it look nice
     */
    public void printAllStaff(){
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
        for (Staff emp: employee){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked()+ " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
    }

    /**
     * The function prints all inventory using String.format to make it look nice
     */
    public void printInventory(){
        System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", "Name", "Brand", "Cost", "Sale Price", "Condition", "Cleanliness", "Status"));
        for(Vehicle car: inventory){
            System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
        for(Vehicle car: soldCars){
            System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
    }

}

