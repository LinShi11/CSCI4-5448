import java.util.ArrayList;
import java.util.Random;

/**
 * The Mechanics class implements from Staff interface
 * It includes all the getters and setters for the variables that are included.
 * Additionally, it includes repair and fix function which is used to repair vehicles.
 */

public class Mechanics implements Staff {
    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    /**
     * Constructor for Mechanics. Everything is set to 0, dailySalary is 240
     * @param name: the name for the mechanics
     */
    public Mechanics(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 240;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
    }

    /**
     * Constructor for Interns to Mechanics. Set the previous total bonus, total pay, and totalDays worked.
     * @param name: the name for the machanics
     * @param days: how long this employee has worked as intern
     * @param bonus: how much they have made as bonus
     * @param pay: how much they have made as normal pay
     */
    public Mechanics(String name, int days, int bonus, int pay){
        setName(name);
        this.totalBonus = bonus;
        this.dailyBonus = 0;
        this.dailySalary = 240;
        this.totalPay = pay;
        this.status = "working";
        this.totalDaysWorked = days;
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
     * @param dailySalary: how much mechanics make daily
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
    public void setDailyBonus(int dailyBonus) {
        this.dailyBonus = dailyBonus;
    }

    /**
     * setter for total pay, adds the daily pay after every day
     */
    @Override
    public void setTotalPay() {
        this.totalPay+= this.dailySalary;
    }

    /**
     * setter for total bonus, adds the daily bonus after every day
     */
    @Override
    public void setTotalBonus() {
        this.totalBonus+= this.dailyBonus;
    }

    /**
     * setter for status
     * @param status: what the status of the mechanic is
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
     * @return name of the mechanics
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * getter for dailysalary
     * @return the daily salary for mechanics
     */
    @Override
    public int getDailySalary() {
        return this.dailySalary;
    }

    /**
     * getter for daily bonus
     * @return the daily bonus for mechanics
     */
    @Override
    public int getDailyBonus() {
        return this.dailyBonus;
    }

    /**
     * getter for total pay
     * @return the total pay for mechanic
     */
    @Override
    public int getTotalPay() {
        return this.totalPay;
    }

    /**
     * getter for total bonus
     * @return the total bonus for the mechanic
     */
    @Override
    public int getTotalBonus() {
        return this.totalBonus;
    }

    /**
     * getter for status
     * @return status of the mechanic
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * getter for number of days worked
     * @return the number of days worked for the mechanic
     */
    @Override
    public int getTotalDaysWorked() {
        return this.totalDaysWorked;
    }

    /**
     * repair function. Where the mechanic is fixing two cars.
     * Randomly select two fixable cars and try to fix them.
     * @param list: an arraylist of all the cars in the inventory
     */
    public ArrayList<String> repair(ArrayList<Vehicle> list){
        ArrayList<String> response = new ArrayList<>();
        ArrayList<Vehicle> repairing = new ArrayList<>();
        // count the number of fixable cars (not like new)
        int fixable = 0;
        for(Vehicle car: list){
            if(!car.getCondition().equals("like new")){
                repairing.add(car);
                fixable ++;
            }
        }
        //if the number is greater than 2, select only 2; otherwise, try to fix all of them
        int num = fixable;
        if(fixable > 2){
            num = 2;
        }

        // randomly choose the car to fix
        Random random = new Random();
        int carNum;
        for(int i = 0; i < num; i ++){
            carNum = random.nextInt(fixable);
            //call fix car function
            response.add(fixCar(repairing.get(carNum)));
        }
        return response;
    }

    /**
     * The fixcar function tries to fix the vehicle and determines whether it was successful
     * Drops cleanliness even if the fix was unsuccessful
     * @param car: the car we would like to fix
     */
    public String fixCar(Vehicle car){
        Random random = new Random();
        String previous = car.getCondition();
        int bonus = 0;
        int chance = random.nextInt(10);
        // 80% of success
        if(chance < 8){
            // from used to like new
            if(car.getCondition().equals("used")){
                //adds the daily bonus
                this.dailyBonus += car.getRepairBonus();
                bonus = car.getRepairBonus();

                //sets the condition and changes the saleprice
                car.setCondition("like new");
                car.setSalePrice(1.25);
            } else{
                //from broken to used
                this.dailyBonus += car.getRepairBonus();
                bonus = car.getRepairBonus();
                car.setCondition("used");
                car.setSalePrice(1.5);
            }
            //adjust the cost according to condition
            car.setCost();
        }
        //drops the cleanliness
        if(car.getCleanliness().equals("sparkling")){
            car.setCleanliness("clean");
        } else if (car.getCleanliness().equals("clean")){
            car.setCleanliness("dirty");
        }
        //make an announcement
        String response = (this.getName() + " fixed a " + previous +" "+ car.getType()
                +" ("+ car.getName() + ") and made it " + car.getCondition() + " (made $" + bonus + ")");
        System.out.println(response);
        return response;

    }
}
