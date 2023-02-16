import java.util.ArrayList;
import java.util.Random;

public class FNCD {
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
            PerformanceCar car = new PerformanceCar(updateInventoryId());
            performanceCarList.add(car);
            this.budget -= car.getCost();
            this.inventory.add(car);
        }
        tempLength = carsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            Cars car = new Cars(updateInventoryId());
            carsList.add(car);
            this.budget -= car.getCost();
            this.inventory.add(car);
        }
        tempLength = pickupsList.size();
        for(int i = 0; i < maxInventory - tempLength; i++){
            Pickups car = new Pickups(updateInventoryId());
            pickupsList.add(car);
            this.budget -= car.getCost();
            this.inventory.add(car);
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
        tasks();
        noMoney();
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

    public void tasks(){
        for (Interns emp: internList){
            System.out.println(emp.getName() + " is washing cars");
//            printInventory();
            emp.wash(inventory);
//            printInventory();
        }
        for(Mechanics emp: mechanicsList){
            System.out.println(emp.getName() + " is repairing cars");
//            printInventory();
            emp.repair(inventory);
//            printInventory();
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

