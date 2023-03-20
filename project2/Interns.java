package project2;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Intern class includes all the getters and setter for all the variables as well as the wash functions required.
 */
public class Interns implements Staff {
    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int announcementBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    /**
     * constructor for intern. Since intern is always new to us, the variables will be 0.
     * @param name
     */
    public Interns(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 70;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
        this.announcementBonus = 0;
    }

    /**
     * Setter for name
     * @param name: the name that we want to set to
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for daily salary
     * @param dailySalary: the new daily salary
     */
    @Override
    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }

    /**
     * setter for daily bonus, used for resetting daily bonus
     * @param dailyBonus: the new daily bonus
     */
    @Override
    public void setDailyBonus(int dailyBonus) {
        this.dailyBonus = dailyBonus;
    }

    /**
     * setter for total pay. We will add the daily pay after every day
     */
    @Override
    public void setTotalPay() {
        this.totalPay+= this.dailySalary;
    }

    /**
     * setter for total bonus. We will add the daily bonus after every day
     */
    @Override
    public void setTotalBonus() {
        this.totalBonus+= this.dailyBonus;
    }

    /**
     * setter for status
     * @param status: the new status
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * setter for total days worked. We will add 1 after every day
     */
    @Override
    public void setTotalDaysWorked() {
        this.totalDaysWorked++;
    }

    /**
     * getter for name
     * @return the name of the intern
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * getter for daily salary
     * @return the daily salary of the intern
     */
    @Override
    public int getDailySalary() {
        return this.dailySalary;
    }

    /**
     * getter for daily bonus
     * @return the daily bonus of the intern
     */
    @Override
    public int getDailyBonus() {
        return this.dailyBonus;
    }

    /**
     * getter for total pay
     * @return the total pay for the intern
     */
    @Override
    public int getTotalPay() {
        return this.totalPay;
    }

    /**
     * getter for total bonus
     * @return the total bonus for the intern
     */
    @Override
    public int getTotalBonus() {
        return this.totalBonus;
    }

    /**
     * getter for status
     * @return the current status of the intern
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * getter for total days worked
     * @return the number of days the intern has worked
     */
    @Override
    public int getTotalDaysWorked() {
        return this.totalDaysWorked;
    }

    /**
     * wash function determine which cars we would like to wash and announce it.
     * We will determine if the wash was successful and add bonus if necessary
     * @param inventory: the list of all the vehicles
     */
    public void wash(ArrayList<Vehicle> inventory){
        ArrayList<Vehicle> washing = new ArrayList<>();
        int dirtyCars = 0;
        int cleanCars = 0;
        // count the number of dirty car and clean cars
        for(Vehicle car : inventory){
            if(car.getCleanliness().equals("dirty")){
                washing.add(0, car);
                dirtyCars += 1;
            } else if (car.getCleanliness().equals("clean")){
                washing.add(car);
                cleanCars += 1;
            }
        }
        Random random = new Random();
        int carNum;
        // if we have at least two dirty cars, we will wash the dirty cars first
        if(dirtyCars >= 2){
            for (int i = 0; i < 2; i ++) {

                //randomly choose a car
                carNum = random.nextInt(dirtyCars);
                dirtyCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();

                // wash it and set cleanliness
                car.setCleanliness(washDirty(car));

                //announcement on the action
                System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness()+ " (making $" + this.announcementBonus+ "). ");
                // make sure we only wash it once
                washing.remove(carNum);
            }
        }
        // if there is one dirty car, wash it announce it, and remove it
        else if (dirtyCars == 1){
            Vehicle car = washing.get(0);
            String previous = car.getCleanliness();
            car.setCleanliness(washDirty(car));
            System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                    +" ("+ car.getName() + ") and made it " + car.getCleanliness()+ " (making $" + this.announcementBonus+ "). ");
            washing.remove(0);

            //we can still wash another car from clean
            if(cleanCars >= 1) {
                carNum = random.nextInt(cleanCars);
                car = washing.get(carNum);
                previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washClean(car));
                System.out.println(this.getName() + " washed a " + previous + " " + car.getType()
                        + " (" + car.getName() + ") and made it " + car.getCleanliness()+ " (making $" + this.announcementBonus+ "). ");
            }
        }
        // there are no dirty cars, we will count the number of clean cars we can wash
        else{
            // 2 if |clean cars| > 2. Otherwise temp = |clean cars|
            int temp;
            if(cleanCars >= 2){
                temp = 2;
            } else{
                temp = cleanCars;
            }
            // wash the possible cars and make the announcement
            for(int i = 0; i < temp; i ++){
                carNum = random.nextInt(cleanCars);
                cleanCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washClean(car));
                System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness() + " (making $" + this.announcementBonus+ "). ");
                washing.remove(carNum);
            }
        }

    }

    /**
     * washing clean cars and add bonus
     * @param car: the car we would like to wash
     * @return: what the final cleanliness is
     */
    public String washClean(Vehicle car){
        Random random = new Random();
        int chance = random.nextInt(100);
        // 5% chance of making it dirty
        if(chance < 5){
            this.announcementBonus = 0;
            return "dirty";
        }
        // 30% chance of making it sparkling
        else if(chance < 35){
            // add bonus
            this.dailyBonus += car.getWashBonus(1);
            this.announcementBonus = car.getWashBonus(1);
            return "sparkling";
        }
        // stays the same
        else{
            this.announcementBonus = 0;
            return "clean";
        }
    }

    /**
     * washing dirty cars and add bonus
     * @param car: the car we would like to wash
     * @return: what the final cleanliness is
     */
    public String washDirty(Vehicle car){
        Random random = new Random();
        int chance = random.nextInt(10);
        // 80% chance of making it clean
        if (chance < 8){
            // add bonus
            this.dailyBonus += car.getWashBonus(1);
            this.announcementBonus = car.getWashBonus(1);
            return "clean";
        }
        //10 % chance of making it sparkling
        else if (chance < 9){
            // double the bonus
            this.dailyBonus += car.getWashBonus(2);
            this.announcementBonus = car.getWashBonus(2);
            return "sparkling";
        }
        // stays the same
        else{
            this.announcementBonus = 0;
            return "dirty";
        }
    }
}

