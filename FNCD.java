import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class FNCD {
    final int maxSize = 3;

    final int maxInventory = 4;
    int budget;
    int dailySales;
    Calendar date;
    int simTime;
    int id;
    int inventoryId;
    int internBonus = 25; 
    int mechanicBonus = 50;
    int salesBonus = 100;
    ArrayList<Vehicle> inventory;
    ArrayList<PerformanceCar> performanceCarList;
    ArrayList<Cars> carsList;
    ArrayList<Pickups> pickupsList;
    ArrayList<Staff> employee;


    ArrayList<Interns> internList;
    ArrayList<Mechanics> mechanicsList;
    ArrayList<Salesperson> salespeopleList;

    public FNCD(){
        this.budget = 500000;
        this.simTime = 30;
        this.date = 1;
        this.id = 1;
        this.inventoryId = 1;
        this.internList = new ArrayList<>();
        this.mechanicsList = new ArrayList<>();
        this.salespeopleList = new ArrayList<>();
        this.employee = new ArrayList<>();
        this.performanceCarList = new ArrayList<>();
        this.carsList = new ArrayList<>();
        this.pickupsList = new ArrayList<>();
        this.inventory = new ArrayList<>();
        for(int i = 0; i < maxSize; i++){
            internList.add(new Interns("Intern_" + updateId()));
            mechanicsList.add(new Mechanics("Mechanics_"+ updateId()));
            salespeopleList.add(new Salesperson("Salesperson_" + updateId()));
        }
    }

    public String updateId(){
        return String.format("%03d", id++);
    }

    public String updateInventoryId(){
        return String.format("%05d", inventoryId++);
    }

    public void setInventory(){
        int tempLength = performanceCarList.size();
        for(int i = 0; i < maxInventory - tempLength; i++ ){
            performanceCarList.add(new PerformanceCar(updateInventoryId()));
            this.budget -= performanceCarList.get(performanceCarList.size()-1).getCost();
        }
        tempLength = carsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            carsList.add(new Cars(updateInventoryId()));
            this.budget -= carsList.get(carsList.size()-1).getCost();
        }
        tempLength = pickupsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            pickupsList.add(new Pickups(updateInventoryId()));
            this.budget -= pickupsList.get(pickupsList.size()-1).getCost();
        }
    }
    public void simulation(){
        while(date <= simTime){
            if(date % 7 != 0) {
                System.out.println("FNCD Day " + this.date);
                startDay();
                System.out.println(date);
                endDay();
                printStaff();
            } else{
                System.out.println("We are closed on Sunday");
            }
            date++;
        }
        printAllStaff();
        printInventory();

    }
    public void startDay(){
        System.out.println("Opening... (Current budget $" + this.budget + ")");
        int buyer = numOfBuyer();
        System.out.println("We have " + buyer + " Buyers");
        for(int i =0; i < buyer; i++){
            Buyer newBuyer = new Buyer();
            System.out.println("New buyer " + (i+1) + " " + newBuyer.getBuyingChance() + " " + newBuyer.getVehicleType() + " with a probability of " + newBuyer.getProbability(newBuyer.getBuyingChance()));
        }
        hire();
        setInventory();
        staffOperation();
        noMoney();
    }

    public void staffOperation() {
        
        int index = 0;
        for (Staff staff: this.employee) {
            //Interns - Washing – Every working day, the Interns will wash Vehicles
            if (staff instanceof Interns) {
                Interns intern = (Interns)staff;	
                //Each Intern can wash two Vehicles per day
                for (int j = 0; j < 2; j++) {
                    while (index < this.inventory.size() && 
                        this.inventory.get(index).getCleanliness() != "sparkling") {						
                        index++;
                    }
                    //found the dirty or clean vehicle
                    if (index < this.inventory.size()) {
                        intern.setDailyBonus(internBonus);
                        index++;
                    }
                }
            } else if (staff instanceof Mechanics) {
                //Each mechanic can repair two Vehicles per day
                index = 0;
                Mechanics mechanic = (Mechanics)staff;
                //Each mechanic can repair two Vehicles per day
                for (int j = 0; j < 2; j++) {
                    while (index < this.inventory.size() && 
                    this.inventory.get(index).getCondition() != "new") {						
                        index++;
                    }
                    //found the dirty or clean vehicle
                    if (index < this.inventory.size()) {
                        mechanic.setDailyBonus(mechanicBonus);
                        index++;
                    }
                }
            } else if (staff instanceof Salesperson) {
                //Selling – Every working day, 0 to 5 Buyers will arrive to buy a 
			    //Vehicle (2-8 Buyers on Friday/Saturday) from a Salesperson
                Salesperson salesperson = (Salesperson)staff;
			    int numBuyers = 0;
                if (this.getDate().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ||
                    this.getDate().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    numBuyers = (int)(Math.random() * 7 + 2);
                }else {
                    numBuyers = (int)(Math.random() * 6);
                }
                salesperson.setDailyBonus(salesBonus);
            }			
        }
    }
    public Calendar getDate() {
        return date;
    }
 
 
    public void hire(){
        if(internList.size() != 3){
            int tempLength = internList.size();
            for(int i = 0; i < maxSize-tempLength; i++){
                internList.add(new Interns("Intern_" + updateId()));
                System.out.println("hired new intern " + internList.get(internList.size()-1).getName());
            }
        }
    }

    public void endDay(){
        dailyUpdate();
        noMoney();
        quit();
    }

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

    public void quit(){
        Random random = new Random();
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(internList.get(temp));
            internList.remove(temp);

            System.out.println("Intern " + employee.get(employee.size()-1).getName() + " has quit");
            System.out.println(employee.get(employee.size()-1).getName() + " has worked " + employee.get(employee.size()-1).getTotalDaysWorked());
            employee.get(employee.size()-1).setStatus("quit");
        }
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(mechanicsList.get(temp));
            mechanicsList.remove(temp);
            System.out.println("Mechanics "+ employee.get(employee.size()-1).getName() + " has quit");
            System.out.println(employee.get(employee.size()-1).getName() + " has worked " + employee.get(employee.size()-1).getTotalDaysWorked());
            employee.get(employee.size()-1).setStatus("quit");

            Interns steppedUp = internList.get(0);
            String name = steppedUp.getName().split("_")[1];
            Mechanics newMechanics = new Mechanics("Mechanics_"+name, steppedUp.getTotalDaysWorked());
            mechanicsList.add(newMechanics);
            internList.remove(0);

            System.out.println("Intern " + steppedUp.getName() + " has stepped up and took the mechanics job");


        }
        if(random.nextInt(10) == 0){
            int temp = random.nextInt(3);
            employee.add(salespeopleList.get(temp));
            salespeopleList.remove(temp);
            System.out.println("Salesperson " + employee.get(employee.size()-1).getName() + " has quit");
            System.out.println(employee.get(employee.size()-1).getName() + " has worked " + employee.get(employee.size()-1).getTotalDaysWorked());
            employee.get(employee.size()-1).setStatus("quit");

            Interns steppedUp = internList.get(0);
            String name = steppedUp.getName().split("_")[1];
            Salesperson newSalesperson = new Salesperson("Salesperson_"+name, steppedUp.getTotalDaysWorked());
            salespeopleList.add(newSalesperson);
            internList.remove(0);

            System.out.println("Intern " + steppedUp.getName() + " has stepped up and took the salesperson job");

        }
    }

    private void printStaff(){
        for (Interns interns : internList) {
            System.out.print(interns.getName() + " ");
        }
        System.out.println();
        for (Salesperson salesperson : salespeopleList) {
            System.out.print(salesperson.getName() + " ");
        }
        System.out.println();
        for (Mechanics mechanics : mechanicsList) {
            System.out.print(mechanics.getName() + " ");
        }
        System.out.println();
    }

    public void noMoney(){
        if(this.budget <= 0){
            this.budget += 250000;
            System.out.println("You ran out of money, so you borrowed $250,000 from the bank");
        }

    }

    public int numOfBuyer(){
        int num;
        Random random = new Random();
        if(this.date % 7 == 5 || this.date % 7 == 6){
            System.out.println("it is the weekend (Friday/Saturday)");
            num = random.nextInt(9-2) + 2;
        }
        else{
            System.out.println("It is the weekdays");
            num = random.nextInt(6);
        }
        return num;

    }

    public void printAllStaff(){
        System.out.println(String.format("%20s %20s %20s %20s %15s", "Name", "Total Days Worked", "Total Normal Pay", "Total Bonus Pay", "Status"));
        for (Staff emp: employee){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked()+ " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Interns emp: internList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Mechanics emp: mechanicsList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
        for (Salesperson emp: salespeopleList){
            System.out.println(String.format("%20s %20s %20s %20s %15s", emp.getName(), emp.getTotalDaysWorked() + " days", "$" + emp.getTotalPay(), "$" + emp.getTotalBonus(), emp.getStatus()));
        }
    }

    public void printInventory(){
        System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", "Name", "Brand", "Cost", "Sale Price", "Condition", "Cleanliness", "Status"));
        for(PerformanceCar car: performanceCarList){
            System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
        for(Cars car: carsList){
            System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
        for(Pickups car: pickupsList){
            System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s", car.getName(), car.getBrand(), car.getCost(), car.getSalePrice(), car.getCondition(), car.getCleanliness(), car.getStatus()));
        }
    }

}

