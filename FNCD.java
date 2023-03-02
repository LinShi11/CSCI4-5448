import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * FNCD holds the simulation for the program.
 */

public class FNCD{
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

    ArrayList<ElectricCar> electricCarList;
    ArrayList<MonsterTruck> monsterTruckList;
    ArrayList<Motorcycle> motorcycleList;
    ArrayList<StaffDriver> staffDriverList;
    Observer observer;
    Logger logger;
    Tracker tracker;
    int performanceCarWin;
    int pickupWin;
    int monsterTruckWin;
    int motorcycleWin;
    private int FNCDamount = 0;
    private int employeeAmount = 0;
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
        this.staffDriverList = new ArrayList<>();
        this.employee = new ArrayList<>();

        this.performanceCarList = new ArrayList<>();
        this.carsList = new ArrayList<>();
        this.pickupsList = new ArrayList<>();
        this.electricCarList = new ArrayList<>();
        this.monsterTruckList = new ArrayList<>();
        this.motorcycleList = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.soldCars = new ArrayList<>();

        this.performanceCarWin = 0;
        this.motorcycleWin = 0;
        this.pickupWin = 0;
        this.monsterTruckWin = 0;

        // directly hire 3 interns, 3 mechanics, and 3 salesperson + 3 drivers (staffs)
        for(int i = 0; i < maxSize; i++){
            internList.add(new Interns("Intern_" + updateId()));
            mechanicsList.add(new Mechanics("Mechanics_"+ updateId()));
            salespeopleList.add(new Salesperson("Salesperson_" + updateId()));
            staffDriverList.add(new StaffDriver("Driver_" + updateId()));
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
                observer = new Observer();
                logger = new Logger();
                tracker = new Tracker(FNCDamount, employeeAmount);
                System.out.println("******FNCD Day " + this.date + "******");
                startDay();
                endDay();
            }
            if (date % 7 == 0 || date % 7 == 3){
                System.out.println("******FNCD Day " + this.date + ": Race day!!!!!!!******");
                race();
            }
            date++;
        }

        //end of simulation, prints the details
        System.out.println("\n******End of simulation******");
        System.out.println("Here is a list of all the staffs: ");
        Helper.printAllStaff(internList, mechanicsList, salespeopleList, employee, staffDriverList);
        System.out.println("\nHere is a list of all the vehicles: ");
        Helper.printInventory(inventory, soldCars);
    }

    public void race(){
        System.out.println("Racing......");
        Random random = new Random();
        int car = random.nextInt(4);
        ArrayList<Vehicle> racing = new ArrayList<>();
        int winCount ;
        switch (car){
            case 0:
                for(PerformanceCar vehicle: performanceCarList){
                    if(!vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                performanceCarWin += winCount;
                break;
            case 1:
                for(Pickups vehicle: pickupsList){
                    if(!vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                pickupWin += winCount;
                break;
            case 2:
                for(MonsterTruck vehicle: monsterTruckList){
                    if(!vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                monsterTruckWin += winCount;
                break;
            case 3:
                for(Motorcycle vehicle: motorcycleList){
                    if(!vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                motorcycleWin += winCount;
                break;
            default:
                System.out.println("error");
        }

    }

    public int raceHelper(ArrayList<Vehicle> racing){
        int count = racing.size();
        int winCount = 0;
        Random random = new Random();
        if(count == 0){
            System.out.println("FNCD will not be participating in the race. ");
            notifyLogger("FNCD will not be participating in the race. ");
            return 0;
        } else if (count > 3 ) {
            count = 3;
        }
        System.out.println("FNCD is racing with " + racing.get(0).getType());
        System.out.println("FNCD have " + count + " vehicles in the race. ");
        notifyLogger("FNCD have " + count + " " + racing.get(0).getType()+ " in the race. ");
        ArrayList<Integer> placement = new ArrayList<>();
        int temp;
        for(int i = 0; i < count; i++){
            temp = random.nextInt(20);
            while(placement.contains(temp)){
                temp = random.nextInt(20);
            }
            placement.add(temp);
        }
        for(int j = 0; j < placement.size(); j++) {
            System.out.println("One of the vehicle got " + (placement.get(j)+1) + " place" );
            notifyLogger("One of the vehicle got " + (placement.get(j)+1) + " place");
            if (placement.get(j) == 0 || placement.get(j) == 1 || placement.get(j) == 2) {
                winCount ++;
                staffDriverList.get(j).setWinCount();
                staffDriverList.get(j).setDailyBonus(1000);
            } else if (placement.get(j) == 19 ||placement.get(j) == 18 || placement.get(j) == 17){
                racing.get(j).setCondition("broken");
                boolean injury = staffDriverList.get(j).selfExam();
                if(injury){
                    System.out.println(staffDriverList.get(j).getName() + " got injured and quit FNCD");
                }
            }
        }
        removeDriver();
        updateRaceBonus();
        return winCount;
    }

    public void removeDriver(){
        int counter = 0;
        while(counter < staffDriverList.size()){
            if(staffDriverList.get(counter).isInjured()){
                employee.add(staffDriverList.get(counter));
                staffDriverList.remove(counter);
            } else{
                counter ++;
            }
        }
    }

    public void updateRaceBonus(){
        for(StaffDriver driver: staffDriverList){
            driver.setTotalBonus();
            notifyLogger(driver.getName() + " made $" + driver.getDailyBonus());
            notifyTracker("employee $"+ driver.getDailyBonus());
            driver.setDailyBonus(0);
        }
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

        if(staffDriverList.size() != maxSize){
            // iterate maxSize(3) - currentSize so we can add more driver
            int tempLength = staffDriverList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                //updateId is a helper function that keeps track of number of staffs we have hired
                staffDriverList.add(new StaffDriver("Driver_" + updateId()));
                System.out.println("Hired driver " + staffDriverList.get(staffDriverList.size()-1).getName());
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
        tempLength = electricCarList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            ElectricCar car = new ElectricCar(updateInventoryId());
            electricCarList.add(car);
            setInventoryHelper(car);
        }
        tempLength = monsterTruckList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            MonsterTruck car = new MonsterTruck(updateInventoryId());
            monsterTruckList.add(car);
            setInventoryHelper(car);
        }
        tempLength = motorcycleList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            Motorcycle motorcycle = new Motorcycle(updateInventoryId());
            motorcycleList.add(motorcycle);
            setInventoryHelper(motorcycle);
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
        int buyer = Helper.numOfBuyer(date);
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
            ArrayList<String> response = emp.wash(inventory);
            notifyHelper(response);
        }
    }

    /**
     * repairing function that iterate through each mechanic and ask them to repair two cars.
     */
    public void repairing(){
        for(Mechanics emp: mechanicsList){
            emp.setDailyBonus(0);
            ArrayList<String> response = emp.repair(inventory);
            notifyHelper(response);
        }
    }

    public void notifyHelper(ArrayList<String> response){
        for(int i = 0; i < response.size(); i++){
            notifyLogger(response.get(i));
        }
    }

    /**
     * Selling function. Given the number of buyers, randomly assign a saleperson to the buyer.
     * Then compute whether they will buy the vehicle or not.
     * Finally, adjust budget, dailysales, and the arrayLists accordingly.
     * @param buyer: the number of buyer for the day
     */
    public void selling(int buyer){
        for(Salesperson emp: salespeopleList){
            emp.setDailyBonus(0);
        }
        Random random = new Random();
        Salesperson representative;

        //iterate through number of buyers
        for(int i =0; i < buyer; i++){
            Buyer newBuyer = new Buyer();

            //randomly choose a salesperson
            representative = salespeopleList.get(random.nextInt(3));

            //call the sale function in SalePerson, the function will return the car the buyer was looking at
            Vehicle car = representative.sale(newBuyer, inventory, performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin);

            //look at the status of the car the saleperson recommended. If it is sold then update the variables
            String response;
            if(car.getStatus().equals("sold")){
                response = representative.getName() + " sold a vehicle " + car.getName() + " for $" + (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));
                notifyLogger(response);
                notifyTracker(response);

                soldCars.add(car); // add the soldcar list
                this.budget += car.getSalePrice();
                this.dailySales += car.getSalePrice();

                // remove them from the appropriate arraylist
                removeHelper(car);
            } else{
                notifyLogger(representative.getName() + " was not able to sell the vehicle " + car.getName());
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
        FNCDamount = tracker.getFNCDAmount();
        employeeAmount = tracker.getEmployeeAmount();
        tracker.onComplete(date);
        logger.onComplete();
    }

    /**
     * dailyUpdate required for each employee, such as days worked, total pay, and bonus
     */
    public void dailyUpdate(){
        for(Interns staff: internList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
            notifyTracker("employee $" + staff.getDailyBonus());
            notifyTracker("employee $" + staff.getDailySalary());
        }
        for(Mechanics staff: mechanicsList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
            notifyTracker("employee $" + staff.getDailyBonus());
            notifyTracker("employee $" + staff.getDailySalary());
        }
        for(Salesperson staff: salespeopleList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
            notifyTracker("employee $" + staff.getDailyBonus());
            notifyTracker("employee $" + staff.getDailySalary());
        }
        for(StaffDriver staff: staffDriverList){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
            notifyTracker("employee $" + staff.getDailyBonus());
            notifyTracker("employee $" + staff.getDailySalary());
        }
    }

    /**
     * noMoney function that checks for budget and add more money if we have 0 or less
     */
    public void noMoney(){
        while(this.budget <= 0){
            this.budget += 250000;
            logger.onNext("You ran out of money, so you borrowed $250,000 from the bank");
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
            Mechanics newMechanics = new Mechanics("Mechanics_"+name, steppedUp.getTotalDaysWorked(), steppedUp.getTotalBonus(), steppedUp.getTotalPay());

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
            Salesperson newSalesperson = new Salesperson("Salesperson_"+name, steppedUp.getTotalDaysWorked(), steppedUp.getTotalBonus(), steppedUp.getTotalPay());
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

    public void notifyTracker(String response){
        tracker.onNext(response);
    }

    public void notifyLogger(String response){
        logger.onNext(response, date);
    }

    public void removeHelper(Vehicle car){
        for(Vehicle c: inventory){
            if(c.getName().equals(car.getName())){
                inventory.remove(c);
                break;
            }
        }
        if(car.getType().equals("performance car")){
            for(Vehicle c: performanceCarList){
                if(c.getName().equals(car.getName())){
                    performanceCarList.remove(c);
                    break;
                }
            }
        } else if(car.getType().equals("car")){
            for(Vehicle c: carsList){
                if(c.getName().equals(car.getName())){
                    carsList.remove(c);
                    break;
                }
            }
        } else if(car.getType().equals("electric car")){
            for(Vehicle c: electricCarList){
                if(c.getName().equals(car.getName())){
                    electricCarList.remove(c);
                    break;
                }
            }
        } else if(car.getType().equals("monster truck")){
            for(Vehicle c: monsterTruckList){
                if(c.getName().equals(car.getName())){
                    monsterTruckList.remove(c);
                    break;
                }
            }
        }else if(car.getType().equals("motorcycle")){
            for(Vehicle c: motorcycleList){
                if(c.getName().equals(car.getName())){
                    motorcycleList.remove(c);
                    break;
                }
            }
        }else{
            for(Vehicle c: pickupsList){
                if(c.getName().equals(car.getName())){
                    pickupsList.remove(c);
                    break;
                }
            }
        }
    }

}