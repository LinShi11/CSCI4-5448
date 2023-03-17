import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**	
 * FNCD holds the simulation for the program.
 */

public class FNCD{	

    // final variable for number of employees(each type) and number of vehicles(each type)
    final int maxSize = 3;
    final int maxInventory = 6;
    int budget;
    int dailySales;
    int date;
    int simTime;
    int id;
    int inventoryId;
    ArrayList<Vehicle> inventory;
    int performanceCarCount;
    int carsCount;
    int pickupsCount;
    ArrayList<Vehicle> soldCars;
    ArrayList<Staff> employee;
    ArrayList<Staff> currentEmployee;
    int internCount;
    int mechanicsCount;
    int salespeopleCount;

    int electricCarCount;
    int monsterTruckCount;
    int motorcycleCount;
    int staffDriverCount;

    // three new types
    int tractorCount;
    int vanCount;
    int craneCount;
    
    Queue<Command> commandQueue = new ConcurrentLinkedQueue<>();
    static final ArrayList<WashingMethod> washingMethods = new ArrayList<>(Arrays.asList(
    			new ChemicalWash(), new DetailedWash(), new ElbowGreasWash()
    		));
    Logger logger;
    Tracker tracker;
    int performanceCarWin;
    int pickupWin;
    int monsterTruckWin;
    int motorcycleWin;
    private int FNCDamount;
    private int employeeAmount;
    
    private String name; //Such as North or South
    private CyclicBarrier barrier;
    private boolean cmdInterface;
    
    //first sales person for asking name
    private Salesperson firstSalesperson = null;
    
    //first sales person for transaction
    private Salesperson secondSalesperson = null;
    // two factories
    private VehicleFactory vehicleFactory;
    private StaffFactory staffFactory;

    private ArrayList<Integer> count;
    private ArrayList<Integer> employeeMoney;
    private ArrayList<Integer> FNCDMoney;

    private int dailyEmployeePay;
    
    /**
     * Constructor for FNCD class
     * Initialize all variable that will be used in the simulation
     */
    public FNCD(){
    	this(null, null, false);
    }
    
    /**
     * Constructor for FNCD class
     * Initialize all variable that will be used in the simulation
     */
    public FNCD(String name, CyclicBarrier barrier, boolean cmdInterface){
    	this.name = name;
    	this.barrier = barrier;
    	this.cmdInterface = cmdInterface;
    	
        this.budget = 500000;
        this.simTime = 30;
        this.date = 1;
        this.id = 1;
        this.inventoryId = 1;

        // arraylist that will be used to hold information
        this.internCount = 3;
        this.mechanicsCount = 3;
        this.salespeopleCount = 3;
        this.staffDriverCount = 3;
        this.employee = new ArrayList<>();
        this.currentEmployee = new ArrayList<>();

        this.performanceCarCount = 0;
        this.carsCount = 0;
        this.pickupsCount = 0;
        this.electricCarCount = 0;
        this.monsterTruckCount = 0;
        this.motorcycleCount = 0;
        this.inventory = new ArrayList<>();
        this.soldCars = new ArrayList<>();
        
        this.tractorCount = 0;
        this.vanCount = 0;
        this.craneCount = 0;
        
        this.performanceCarWin = 0;
        this.motorcycleWin = 0;
        this.pickupWin = 0;
        this.monsterTruckWin = 0;
        Random random = new Random();
        this.FNCDamount = 0;
        this.employeeAmount = 0;

        vehicleFactory = new VehicleFactory();
        staffFactory = new StaffFactory();

        FNCDMoney = new ArrayList<>(Arrays.asList(0));
        employeeMoney = new ArrayList<>(Arrays.asList(0));
        count = new ArrayList<>(Arrays.asList(0));

        // directly hire 3 interns, 3 mechanics, and 3 salesperson + 3 drivers (staffs)
        for(int i = 0; i < maxSize; i++){
            currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Intern, name+"Intern_" + updateId(),
                washingMethods.get(random.nextInt(washingMethods.size()))));
            currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Mechanic,name+"Mechanics_"+ updateId(), null));
            currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Salesperson,name+"Salesperson_" + updateId(), null));
            currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Driver, name+"Driver_" + updateId(), null));
        }
    }

    /**
     * Simulation function that simulate for 30 days.
     * Prints the final staff and inventory when simulation is completed
     */
    public void simulation(){

    	boolean tempCmdInterface = cmdInterface;    	
    	cmdInterface = false;
    	
        while(date <= simTime){ 
        	
            // check for sundays and call startDay()/endDay() if it is not sunday
            if(date % 7 != 0) {
                logger = Logger.getInstance();
                tracker = Tracker.getInstance(FNCDamount, employeeAmount);
                System.out.println((name != null?name + ":":"") + "******FNCD Day " + this.date + "******");
                startDay();
                endDay();
            }
            // race day
            if (date % 7 == 0 || date % 7 == 3){
                System.out.println((name != null?name + ":":"") + "******FNCD Day " + this.date + ": Race day!!!!!!!******");
                race();
            }
            date++; 
            
            if (barrier != null) {
	            try {
					barrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
		}
        
        cmdInterface = tempCmdInterface;
        if (cmdInterface) {

			/**
			 * day 31 will start as normal until the beginning of sales at each FNCD. At
			 * that point, you will present a command line interface to allow a user to
			 * interact with the FNCD of their choosing (there will be no random customers
			 * for this day)
			 */
	        if (date % 7 == 0 || date % 7 == 3){
	            System.out.println((name != null?name + ":":"") + "******FNCD Day " + this.date + ": Race day!!!!!!!******");
	            race();
	        }

	        logger = Logger.getInstance();
	        tracker = Tracker.getInstance(FNCDamount, employeeAmount);
	        System.out.println((name != null?name + ":":"") + "******FNCD Day " + this.date + "******");
	        startDay();
	        endDay();
        }

        synchronized (washingMethods) {
        	
        	if (name != null) {
        		System.out.println("\n" + name + ":");
        	}
        	
        	//end of simulation, prints the details
            System.out.println("\n******End of simulation******");
            System.out.println("Here is a list of all the staffs: ");
            Helper.printAllStaff(currentEmployee, employee);
            System.out.println("\nHere is a list of all the vehicles: ");
            Helper.printInventory(inventory, soldCars);
            
        	washingMethods.notifyAll();
		}        
        
    }

    /**
     * This is the race function.
     * The function determines which type of car to race and generate a list of usable car. Then will add to the winCount based on the results.
     */
    public void race(){
        System.out.println("Racing......");
        Random random = new Random();
        // what type of car to use
        int car = random.nextInt(4);
        ArrayList<Vehicle> racing = new ArrayList<>();
        int winCount ;
        switch (car){
            // generate the usable car arraylist for each type of possible cars
            case 0:
                for(Vehicle vehicle: inventory){
                    if(vehicle.getType() == Enum.VehicleType.PerformanceCar && !vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                performanceCarWin += winCount;
                break;
            case 1:
                for(Vehicle vehicle: inventory){
                    if(vehicle.getType() == Enum.VehicleType.Pickups && !vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                pickupWin += winCount;
                break;
            case 2:
                for(Vehicle vehicle: inventory){
                    if(vehicle.getType() == Enum.VehicleType.MonsterTrucks && !vehicle.getCondition().equals("broken") ){
                        racing.add(vehicle);
                    }
                }
                winCount = raceHelper(racing);
                monsterTruckWin += winCount;
                break;
            case 3:
                for(Vehicle vehicle: inventory){
                    if(vehicle.getType() == Enum.VehicleType.Motorcycles && !vehicle.getCondition().equals("broken") ){
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

    /**
     * The function will look at all race-able cars, determine the placement and make appropriate actions.
     * @param racing: an arraylist of usable cars for racing
     * @return : the number of winner for FNCD racers
     */
    public int raceHelper(ArrayList<Vehicle> racing){
        int count = racing.size();
        int winCount = 0;
        Random random = new Random();

        // if we have no race-able cars
        if(count == 0){
            System.out.println(name + "FNCD will not be participating in the race. ");
            notifyLogger(name + "FNCD will not be participating in the race. ");
            return 0;
        }
        // if all the cars are race-able, we will only pick the first three
        else if (count > 3 ) {
            count = 3;
        }
        System.out.println(name + " FNCD is racing with " + racing.get(0).getType());
        System.out.println(name + " FNCD have " + count + " vehicles in the race. ");
        notifyLogger(name + "FNCD have " + count + " " + racing.get(0).getType()+ " in the race. ");
        ArrayList<Integer> placement = new ArrayList<>();
        int temp;
        // determine the placement, will continue to run if we are getting the same numbers
        for(int i = 0; i < count; i++){
            temp = random.nextInt(20);
            while(placement.contains(temp)){
                temp = random.nextInt(20);
            }
            placement.add(temp);
        }
        ArrayList<StaffDriver> staffDriverList = Helper.getAllDriver(currentEmployee);
        // look at the placement and determine action
        for(int j = 0; j < placement.size(); j++) {
            System.out.println(name + ": One of the vehicle got " + (placement.get(j)+1) + " place" );
            notifyLogger(name + ": One of the vehicle got " + (placement.get(j)+1) + " place");

            // if we won
            if (placement.get(j) == 0 || placement.get(j) == 1 || placement.get(j) == 2) {
                winCount ++;
                staffDriverList.get(j).setWinCount();
                staffDriverList.get(j).setDailyBonus(1000);
            }
            // if we lost
            else if (placement.get(j) == 19 ||placement.get(j) == 18 || placement.get(j) == 17){
                racing.get(j).setCondition("broken");
                boolean injury = staffDriverList.get(j).selfExam();
                if(injury){
                    System.out.println(staffDriverList.get(j).getName() + " got injured and quit FNCD");
                }
            }
        }
        // remove the drivers and update their bonus
        removeDriver();
        updateRaceBonus();
        return winCount;
    }

    /**
     * this function removes the drivers after the race
     */
    public void removeDriver(){
        int counter = 0;
        ArrayList<StaffDriver> staffDriverList = Helper.getAllDriver(currentEmployee);
        while(counter < staffDriverList.size()){
            if(staffDriverList.get(counter).isInjured()){
                StaffDriver driver = staffDriverList.get(counter);
                employee.add(driver);
                staffDriverList.remove(driver);
                currentEmployee.remove(driver);
                staffDriverCount--;
            } else{
                counter ++;
            }
        }
    }

    /**
     * this function updated the bonuses for the drivers who won
     */
    public void updateRaceBonus(){
        this.dailyEmployeePay= 0 ;
        this.dailySales = 0;
        ArrayList<StaffDriver> staffDriverList = Helper.getAllDriver(currentEmployee);
        for(StaffDriver driver: staffDriverList){
            driver.setTotalBonus();
            notifyLogger(driver.getName() + " made $" + driver.getDailyBonus());
            dailyEmployeePay += driver.getDailyBonus();
            driver.setDailyBonus(0);
        }
        employeeMoney.add(employeeMoney.get(employeeMoney.size()-1) + dailyEmployeePay);
        FNCDMoney.add((FNCDMoney.get(FNCDMoney.size()-1) + dailySales));
        count.add(count.get(count.size()-1));
    }

    /**
     * This function includes everything we need to do to start the day
     * hire interns, buy more vehicles, complete tasks (washing, repairing, selling), and checks to make sure we have enough money
     */
    public void startDay(){
        System.out.println(name + " opening... (Current budget $" + this.budget + ")");
        this.dailySales = 0;
        this.dailyEmployeePay = 0;
        hire();
        endOfEvents();
        setInventory();
        endOfEvents();
        tasks();
        endOfEvents();
        noMoney();
        endOfEvents();
    }

    /**
     * hire function checks to make sure we have 3 interns.
     * If not, we will hire more interns
     */
    public void hire(){
        Random random = new Random();
        if(internCount != maxSize){
            // iterate maxSize(3) - currentSize so we can add more interns
            int temp = internCount;
            for(int i = 0; i < maxSize-temp; i++){
                //updateId is a helper function that keeps track of number of staffs we have hired
                currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Intern, name+"Intern_" + updateId(), washingMethods.get(random.nextInt(washingMethods.size()))));
                System.out.println("Hired intern " + currentEmployee.get(currentEmployee.size()-1).getName());
                internCount++;
            }
        }

        if(staffDriverCount != maxSize){
            // iterate maxSize(3) - currentSize so we can add more driver
            int temp = staffDriverCount;
            for(int i = 0; i < maxSize-temp; i++){
                //updateId is a helper function that keeps track of number of staffs we have hired
                currentEmployee.add(staffFactory.hireStaff(Enum.StaffType.Driver,name+"Driver_" + updateId(), null));
                System.out.println("Hired driver " + currentEmployee.get(currentEmployee.size()-1).getName());
                staffDriverCount++;
            }
        }
    }

    /**
     * setInventory function checks each type of vehicles and buy more if necessary.
     * Three different arrayList are used so the checking process is easier.
     */
    public void setInventory(){
        int temp = performanceCarCount;
        for(int i = 0; i < maxInventory - temp; i++ ){

            // we are calling the factory instead of the performance car constructor itself.
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.PerformanceCar, updateInventoryId());
            if(car != null) {
                performanceCarCount++;
                // helper function to modify budge, inventory, and print. To make thing clearer
                setInventoryHelper(car);
            }
        }
        temp = carsCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Cars, updateInventoryId());
            if(car != null) {
                carsCount ++;
                setInventoryHelper(car);
            }
        }
        temp= pickupsCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Pickups, updateInventoryId());
            if(car != null) {
                pickupsCount++;
                setInventoryHelper(car);
            }
        }
        temp = electricCarCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.ElectricCars, updateInventoryId());
            if(car != null) {
                electricCarCount++;
                setInventoryHelper(car);
            }
        }
        temp = monsterTruckCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.MonsterTrucks, updateInventoryId());
            if(car != null) {
                monsterTruckCount++;
                setInventoryHelper(car);
            }
        }
        temp = motorcycleCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Motorcycles, updateInventoryId());
            if(car != null) {
                motorcycleCount++;
                setInventoryHelper(car);
            }
        }
        temp = tractorCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Tractor, updateInventoryId());
            if(car != null) {
                tractorCount++;
                setInventoryHelper(car);
            }
        }
        temp = vanCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Van, updateInventoryId());
            if(car != null) {
                vanCount++;
                setInventoryHelper(car);
            }
        }
        temp = craneCount;
        for(int i = 0; i < maxInventory - temp; i++){
            Vehicle car = vehicleFactory.buildVehicle(Enum.VehicleType.Crane, updateInventoryId());
            if(car != null) {
                craneCount++;
                setInventoryHelper(car);
            }
        }
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
        car.printAction(name);
    }

    /**
     * Tasks function keep track of the washing, repairing, and selling tasks
     * We also get the number of buyer for each day.
     */
    public void tasks(){
    	
    	Random rand = new Random();
        washing();
        endOfEvents();

        repairing();
        endOfEvents();
        if (cmdInterface) { //command interface
        	
        	//wait for process command
        	while (true) {
        		
        		Command command = null;
        		
        		synchronized (commandQueue) {
				
        			while (commandQueue.isEmpty()) {
        				try {
							commandQueue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
        			}
        			
        			if (!commandQueue.isEmpty()) {
        				
        				command = commandQueue.poll();
        			}
        			
        			commandQueue.notifyAll();
				}
        		
        		if (command != null) {
        		    ArrayList<Salesperson> salespeopleList = Helper.getAllSalesperson(currentEmployee);
        			if (command instanceof QuitCommand) {
        				break; //exit the while
        			}else if (command instanceof SalespersonNameCommand) {
        				
        				firstSalesperson = salespeopleList.get(rand.nextInt(salespeopleList.size()));
        				System.out.println("Salesperson's name: " + firstSalesperson.getName());
        				
        			}else if (command instanceof SecondSalespersonCommand) {
        				
        				secondSalesperson = salespeopleList.get(rand.nextInt(salespeopleList.size()));
        				while (secondSalesperson == firstSalesperson) {
        					secondSalesperson = salespeopleList.get(rand.nextInt(salespeopleList.size()));
        				}
        				
        				System.out.println("Second salesperson's name: " + secondSalesperson.getName());
        				
        			}else if (command instanceof CurrentStoreInventoryCommand) {
        				
        				Helper.printInventory(inventory);
        				
        			}else if (command instanceof BuyNormalInventoryItem) {
        				
        				BuyNormalInventoryItem buyCmd = (BuyNormalInventoryItem)command;
        				
        				if (secondSalesperson == null) {
        					System.out.println("Please choose the second salesperson");
        				}else {
        					selling(secondSalesperson, buyCmd.getChosenVehicle());
        				}
        				
        				
        			}else { //time, vehicle details command
        				command.execute();
        			}
        		}        		
        	}
        	
        }else {

	        //find the number of buyer for the day
	        int buyer = Helper.numOfBuyer(date);
	        System.out.println("\nWe have " + buyer + " Buyers today");
	        System.out.println("Selling..."+ name);
	        selling(buyer);
            endOfEvents();
        }
    }

    /**
     * washing function that iterate through each intern and ask them to wash two cars.
     */
    public void washing(){
        ArrayList<Interns> internList = Helper.getAllIntern(currentEmployee);
        System.out.println("Washing..." + name);
        for (Interns emp: internList){
            emp.setDailyBonus(0);
            emp.wash(inventory);
        }
    }

    /**
     * repairing function that iterate through each mechanic and ask them to repair two cars.
     */
    public void repairing(){
        ArrayList<Mechanics> mechanicsList = Helper.getAllMechanics(currentEmployee);
        System.out.println("Repairing..."+ name);
        for(Mechanics emp: mechanicsList){
            emp.setDailyBonus(0);
            ArrayList<String> response = emp.repair(inventory);
            notifyHelper(response);
        }
    }

    /**
     * Helper function that splits the arraylist and notifies logger
     * @param response: an arraylist of responses
     */
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
        int tempSale = 0;
        ArrayList<Salesperson> salespeopleList = Helper.getAllSalesperson(currentEmployee);
        for(Salesperson emp: salespeopleList){
            emp.setDailyBonus(0);
        }
        Random random = new Random();
        Salesperson representative;

        //iterate through number of buyers
        for(int i =0; i < buyer; i++){
            Buyer newBuyer = new Buyer();
            //randomly choose a salesperson
            representative = salespeopleList.get(random.nextInt(salespeopleCount));

            //call the sale function in SalePerson, the function will return the car the buyer was looking at
            Vehicle car = representative.sale(newBuyer, inventory, performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin);

            //look at the status of the car the saleperson recommended. If it is sold then update the variables
            String response;
            if(car.getStatus().equals("sold")){
                tempSale ++;
                response = representative.getName() + " sold a vehicle " + car.getName() + " for $" + (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));
                notifyLogger(response);
                notifyTracker(response);
//                FNCDamount += (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));

                soldCars.add(car); // add the soldcar list
                this.budget += (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));
                this.dailySales += (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));

                // remove them from the appropriate arraylist
                removeHelper(car);
            } else{
                notifyLogger(representative.getName() + " was not able to sell the vehicle " + car.getName());
            }
        }
        count.add(count.get(count.size()-1) + tempSale);
    }

    /**
     * selling in at day 31st
     * @param salesperson
     * @param car
     */
    public void selling(Salesperson salesperson, Vehicle car){
        Buyer newBuyer = new Buyer();

        car = salesperson.addDecorator(car);
        
        // determine whether they will buy the car
        salesperson.buying(newBuyer, car, performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin);
        
        //look at the status of the car the saleperson recommended. If it is sold then update the variables
        String response;
        if(car.getStatus().equals("sold")){
            response = salesperson.getName() + " sold a vehicle " + car.getName() + " for $" + (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));
            notifyLogger(response);
            notifyTracker(response);

            soldCars.add(car); // add the soldcar list
            this.budget += (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));
            this.dailySales += (int)(car.getSalePrice() * car.getPercent() * Salesperson.bonusHelper(car.getType(), performanceCarWin, pickupWin, monsterTruckWin, motorcycleWin));

            // remove them from the appropriate arraylist
            removeHelper(car);
        } else{
            notifyLogger(salesperson.getName() + " was not able to sell the vehicle " + car.getName());
        }
    }
    


    /**
     * end of the day function.
     * Updates the employees, checks the budget, and determine whether anyone quit.
     */
    public void endDay(){
        System.out.println("Closing...");
        System.out.println("We made $" + this.dailySales+ " today");
        dailyUpdate();
        endOfEvents();
        noMoney();
        endOfEvents();
        employeeAmount = tracker.getEmployeeAmount();
        FNCDamount = tracker.getFNCDAmount();
        employeeMoney.add(employeeMoney.get(employeeMoney.size()-1) + dailyEmployeePay);
        FNCDMoney.add((FNCDMoney.get(FNCDMoney.size()-1) + dailySales));
        System.out.println("Quitting");
        quit();
        endOfEvents();
        tracker.onComplete(date);
        logger.onComplete();
        endOfEvents();
    }

    /**
     * dailyUpdate required for each employee, such as days worked, total pay, and bonus
     */
    public void dailyUpdate(){
        for(Staff staff: currentEmployee){
            staff.setTotalDaysWorked();
            staff.setTotalPay();
            staff.setTotalBonus();
            notifyTracker("employee $" + staff.getDailyBonus());
            notifyTracker("employee $" + staff.getDailySalary());
            dailyEmployeePay += staff.getDailyBonus();
            dailyEmployeePay += staff.getDailySalary();
        }
    }

    /**
     * noMoney function that checks for budget and add more money if we have 0 or less
     */
    public void noMoney(){
        while(this.budget <= 0){
            System.out.println(name + " You currently have " + budget);
            this.budget += 250000;
            logger.onNext("You ran out of money, so you borrowed $250,000 from the bank", date, name);
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
            ArrayList<Interns> internList = Helper.getAllIntern(currentEmployee);
            int temp = random.nextInt(3);
            Staff quitter = internList.get(temp);
            //add them to a list of past employees
            employee.add(quitter);
            currentEmployee.remove(quitter);
            internCount--;

            // display the information to the user using a helper function
            quitHelper("Intern");
        }
        if(random.nextInt(10) == 0){
            ArrayList<Mechanics> mechanicsList = Helper.getAllMechanics(currentEmployee);
            int temp = random.nextInt(3);
            Staff quitter = mechanicsList.get(temp);
            employee.add(quitter);
            currentEmployee.remove(quitter);
            mechanicsCount--;
            quitHelper("Mechanics");

            // ask one of the intern to step up
            Interns steppedUp = Helper.getAllIntern(currentEmployee).get(0);

            // extract their unique id and use Mechanics_<id> as the new name and pass in the number of days they have worked already
            String tempId = steppedUp.getName().split("_")[1];
            Staff newMechanics = staffFactory.promotion(Enum.StaffType.Mechanic,name+"Mechanics_"+tempId, steppedUp.getTotalDaysWorked(), steppedUp.getTotalBonus(), steppedUp.getTotalPay());

            //modify arraylists
            currentEmployee.add(newMechanics);
            mechanicsCount ++;
            currentEmployee.remove(steppedUp);
            internCount--;

            // announce event
            System.out.println("Intern " + steppedUp.getName() + " has stepped up and took the mechanics job");
        }
        // same as mechanics
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            ArrayList<Salesperson> salespeopleList = Helper.getAllSalesperson(currentEmployee);
            Staff quitter = salespeopleList.get(temp);
            employee.add(quitter);
            currentEmployee.remove(quitter);
            salespeopleCount--;
            quitHelper("Salesperson");

            Interns steppedUp = Helper.getAllIntern(currentEmployee).get(0);

            String tempId = steppedUp.getName().split("_")[1];
            Staff newSalesperson = staffFactory.promotion(Enum.StaffType.Salesperson, name + "Salesperson_" + tempId, steppedUp.getTotalDaysWorked(), steppedUp.getTotalBonus(), steppedUp.getTotalPay());

            currentEmployee.add(newSalesperson);
            salespeopleCount++;
            currentEmployee.remove(steppedUp);
            internCount--;

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

    /**
     * calls the observers and give it the text (part of the observer pattern)
     * @param response: the text to send
     */
    public void notifyTracker(String response){
        tracker.onNext(response);
    }

    /**
     * calls the observer and give it the text (part of the observer pattern)
     * @param response: the text to send
     */
    public void notifyLogger(String response){
        logger.onNext(response, date, name);
    }

    /**
     * helper to remove the cars from the inventory
     * @param car: the car to remove
     */
    public void removeHelper(Vehicle car){
        for(Vehicle c: inventory){
            if(c.getName().equals(car.getName())){
                inventory.remove(c);
                break;
            }
        }
        if(car.getType() == Enum.VehicleType.PerformanceCar){
            performanceCarCount --;
        } else if(car.getType() == Enum.VehicleType.Cars){
            carsCount--;
        } else if(car.getType() == Enum.VehicleType.ElectricCars){
            electricCarCount --;
        } else if(car.getType() == Enum.VehicleType.MonsterTrucks){
            monsterTruckCount --;
        }else if(car.getType() == Enum.VehicleType.Motorcycles){
            motorcycleCount --;
        }else if(car.getType() == Enum.VehicleType.Pickups){
            pickupsCount --;
        }else if(car.getType() == Enum.VehicleType.Van){
            vanCount --;
        }else if(car.getType() == Enum.VehicleType.Crane){
            craneCount --;
        }else if(car.getType() == Enum.VehicleType.Tractor){
            tractorCount --;
        }
    }

	/**
	 * @return the commandQueue
	 */
	public Queue<Command> getCommandQueue() {
		return commandQueue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

    /**
     * return the vehicle
     * @param name: given the name of the vehicle
     * @return return the vehicle that we are looking for
     */
	public Vehicle getVehicle(String name) {
		for(Vehicle car: inventory){
			if (car.getName().equalsIgnoreCase(name)) {
				return car;
			}
		}
		return null;
	}

    /**
     * Helper function to draw graph, keep count of the number of sold cars
     * @return arraylist of sold cars from day 0
     */
    public ArrayList<Integer> getCount(){
        return count;
    }

    /**
     * Helper function to draw graph, keep count of the total money the employee made
     * @return employee salary + bonuses from day 0
     */
    public ArrayList<Integer> getEmployeeMoney() {
        if(count.size() < employeeMoney.size()){
            employeeMoney.remove(employeeMoney.size()-1);
        }
        return employeeMoney;
    }

    /**
     * Helper function to draw graph, keep count of the total money the FNCD made
     * @return the money the FNCD made from day 0, not including the money added when it runs out of money
     */
    public ArrayList<Integer> getFNCDMoney() {
        if(count.size() < FNCDMoney.size()){
            FNCDMoney.remove(FNCDMoney.size()-1);
        }
        return FNCDMoney;
    }

    /**
     * Helper function to use the barrier to prevent one thread from moving too fast.
     */
    public void endOfEvents(){
        if (barrier != null) {
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}