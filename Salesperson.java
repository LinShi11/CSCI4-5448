import java.util.ArrayList;
import java.util.Random;

/**
 * Salesperson class contains all getter and setter for the variables as well as the sale functions necessary
 */
public class Salesperson implements Staff{

    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;
    private Enum.StaffType type;

    /**
     * Constructor for Salesperson. Everything is set to 0, dailySalary is 200
     * @param name: the name for the mechanics
     */
    public Salesperson(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 200;
        this.totalPay = 0;
        this.status = "working";
        this.totalDaysWorked = 0;
        this.type = Enum.StaffType.Salesperson;
    }
    /**
     * Constructor for Interns to Salesperson. Set the previous total bonus, total pay, and totalDays worked.
     * @param name: the name for the salesperson
     * @param days: how long this employee has worked as intern
     * @param bonus: how much they have made as bonus
     * @param pay: how much they have made as normal pay
     */
    public Salesperson(String name, int days, int bonus, int pay){
        setName(name);
        this.totalBonus = bonus;
        this.dailyBonus = 0;
        this.dailySalary = 200;
        this.totalPay = pay;
        this.status = "working";
        this.totalDaysWorked = days;
        this.type = Enum.StaffType.Salesperson;
    }
    /**
     * setter for name
     * @param name: the name to change it to
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /**
     * setter for dailySalary
     * @param dailySalary: how much salesperson make daily
     */
    @Override
    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }
    /**
     * setter for daily bonus, used to reset daily-bonus
     * @param dailyBonus: reset it to 0 everyday
     */
    @Override
    public void setDailyBonus(int dailyBonus){
        this.dailyBonus = dailyBonus;
    }
    /**
     * setter for total pay, adds the daily pay after every day
     */
    @Override
    public void setTotalPay() {
        this.totalPay+=this.dailySalary;
    }
    /**
     * setter for total bonus, adds the daily bonus after every day
     */
    @Override
    public void setTotalBonus() {
        this.totalBonus+=this.dailyBonus;
    }
    /**
     * setter for status
     * @param status: what the status of the salesperson is
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * setter for total days worked, adds 1 after every day
     */
    @Override
    public void setTotalDaysWorked() {
        this.totalDaysWorked++;
    }
    /**
     * getter for name
     * @return name of the salesperson
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * getter for dailysalary
     * @return the daily salary for salesperson
     */
    @Override
    public int getDailySalary() {
        return this.dailySalary;
    }
    /**
     * getter for daily bonus
     * @return the daily bonus for salesperson
     */
    @Override
    public int getDailyBonus() {
        return this.dailyBonus;
    }
    /**
     * getter for total pay
     * @return the total pay for salesperson
     */
    @Override
    public int getTotalPay() {
        return this.totalPay;
    }
    /**
     * getter for total bonus
     * @return the total bonus for the salesperson
     */
    @Override
    public int getTotalBonus() {
        return this.totalBonus;
    }
    /**
     * getter for status
     * @return status of the salesperson
     */
    @Override
    public String getStatus() {
        return this.status;
    }
    /**
     * getter for number of days worked
     * @return the number of days worked for the salesperson
     */
    @Override
    public int getTotalDaysWorked() {
        return this.totalDaysWorked;
    }

    @Override
    public Enum.StaffType getType() {
        return type;
    }

    /**
     * The sale function determines the car that the salesperson will recommend to the buyer
     * @param buyer: the person interested to buy a car
     * @param inventory: the list of all vehicles in stock
     * @return the car salesperson recommended
     */
    public Vehicle sale(Buyer buyer, ArrayList<Vehicle> inventory, int perforamceWin, int pickupWin, int monsterWin, int motorWin){
        Vehicle car = null;
        int price = 0;
        // search through inventory for a non-broken vehicle that matches the type and is most expensive
        for(Vehicle temp: inventory){
            if(buyer.getVehicleType().equals(temp.getType()) && !temp.getCondition().equals("broken")){
                if(temp.getSalePrice() > price){
                    car = temp;
                    price = temp.getSalePrice();
                }
            }
        }
        // if such car does not exist, find the most expensive car
        if(car == null){
            car = mostExpensive(inventory);
        }
        // if there are still no cars, then we do not have any cars for the buyer
        if(car == null){
            System.out.println("There is no car available to the buyer. ");
        } else{
            car = addDecorator(car);
            // determine whether they will buy the car
            buying(buyer, car, perforamceWin, pickupWin, monsterWin, motorWin);
        }
        return car;

    }

    /**
     * Looks at each car and determine whether a package will be brought
     * @param car: the current car
     * @return the car after all the wrapping
     */
    public Vehicle addDecorator(Vehicle car){
        Random random = new Random();
        System.out.println("The original price of the car is: $" + car.getSalePrice());
        System.out.println("The buyer has selected the following plan: ");
        int warranty = random.nextInt(4);
        if(warranty < 1){
            car = new ExtendedWarranty(car);
            System.out.print("Extended Warranty, ");
        }
        int coating = random.nextInt(10);
        if(coating < 1){
            car = new Undercoating(car);
            System.out.print("Undercoating, ");
        }
        int road = random.nextInt(20);
        if(road < 1){
            car = new RoadRescueCoverage(car);
            System.out.print("Road Rescue Coverage, ");
        }
        int radio = random.nextInt(10);
        if(radio < 4){
            car = new SatelliteRadio(car);
            System.out.print("Satellite Radio");
        }
        System.out.print("The current sale price " + (car.getPercent()*100) + "% of the original price. Therefore the current sale price is $" + (int)(car.getSalePrice() * car.getPercent()) + "\n");
        return car;
    }

    /**
     * the function finds the most expensive car
     * @param inventory: a list of all the cars
     * @return the most expensive car that is not broken=
     */
    public Vehicle mostExpensive(ArrayList<Vehicle> inventory){
        Vehicle car = null;
        int price = 0;
        for(Vehicle temp: inventory){
            if(!temp.getCondition().equals("broken")){
                if(temp.getSalePrice() > price){
                    car = temp;
                    price = temp.getSalePrice();
                }
            }
        }
        return car;
    }

    /**
     * determines the probability of buying, announce it, and add bonus is necessary
     * @param buyer: the person who wants to buy a car
     * @param car: the car that we recommended
     */
    public void buying(Buyer buyer, Vehicle car, int perforamceWin, int pickupWin, int monsterWin, int motorWin){
        // start with their buying chance
        int buyingChance = buyer.getProbability(buyer.getBuyingChance());
        Random random = new Random();
        // -20% if it is not the type they want
        if(!car.getType().equals(buyer.getVehicleType())){
            buyingChance -= 20;
        }
        // add chances based on cleanliness
        if(car.getCleanliness().equals("sparkling")){
            buyingChance += 20;
        } else if (car.getCleanliness().equals("clean")){
            buyingChance += 10;
        }
        // if the chance is less than 0; then the buyer is not interested.
        if(buyingChance <= 0){
            System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                    this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                    + car.getType() + "(" + car.getName()+ "). Therefore, the buyer is not interested ");
        } else{
            // commute the chances
            int success = random.nextInt(100);
            // if success
            if(success < buyingChance){
                System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                        this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                        + car.getType() + "(" + car.getName()+ "). The buying probability was " + buyingChance+ ". And the transaction was successful for $" +
                        (int)(car.getSalePrice() * car.getPercent() * bonusHelper(car.getType(), perforamceWin, pickupWin, monsterWin, motorWin)) + " (making $"+ car.getSaleBonus() + ")");
                // add bonus and change status
                this.dailyBonus += car.getSaleBonus();
                car.setStatus("sold");
            } else{
                // make announcement that it was not successful
                System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                        this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                        + car.getType() + "(" + car.getName()+ "). The buying probability was " + buyingChance+ ". And the transaction was unsuccessful for $" +
                        (int)(car.getSalePrice() * car.getPercent()* bonusHelper(car.getType(), perforamceWin, pickupWin, monsterWin, motorWin)));
            }
        }
    }

    /**
     * A bonus helper that determines whether the 10% increase is necessary
     * @param type : the current type of the car
     * @param perforamceWin: the win for performance car
     * @param pickupWin: win for pickups
     * @param monsterWin: win for monster trucks
     * @param motorWin: win for motorcycles
     * @return 1 for no change or 1.1 for 10% increase
     */
    public static double bonusHelper(Enum.VehicleType type, int perforamceWin, int pickupWin, int monsterWin, int motorWin){
        if(type == Enum.VehicleType.PerformanceCar){
            if(perforamceWin >= 1){
                return 1;
            }else{
                return 1.1;
            }
        } else if (type == Enum.VehicleType.Pickups){
            if(pickupWin >= 1){
                return 1;
            } else{
                return 1.1;
            }
        } else if(type == Enum.VehicleType.MonsterTrucks){
            if(monsterWin >= 1){
                return 1;
            } else{
                return 1.1;
            }
        } else if (type == Enum.VehicleType.Motorcycles){
            if(motorWin >= 1){
                return 1;
            } else{
                return 1.1;
            }
        }
        return 1;
    }
}
