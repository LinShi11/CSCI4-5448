package project3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class is for intern that implements from Staff
 */
public class Interns implements Staff {
    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    /**
     * The washing method is different for every intern
     */
    private WashingMethod washingMethod;
    
    public Interns(String name, WashingMethod washingMethod){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 70;
        this.totalPay = 0;
        this.status = "working";
        this.totalDaysWorked = 0;
        this.washingMethod = washingMethod;
    }

    /**
     * These are the getter and setter for every variable
     *
     */

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }

    @Override
    public void setDailyBonus(int dailyBonus) {
        this.dailyBonus = dailyBonus;
    }

    @Override
    public void setTotalPay() {
        this.totalPay+= this.dailySalary;
    }

    @Override
    public void setTotalBonus() {
        this.totalBonus+= this.dailyBonus;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setTotalDaysWorked() {
        this.totalDaysWorked++;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDailySalary() {
        return this.dailySalary;
    }

    @Override
    public int getDailyBonus() {
        return this.dailyBonus;
    }

    @Override
    public int getTotalPay() {
        return this.totalPay;
    }

    @Override
    public int getTotalBonus() {
        return this.totalBonus;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public int getTotalDaysWorked() {
        return this.totalDaysWorked;
    }

    /**
     * wash function that does the washing 
     * @param inventory
     */
    public void wash(ArrayList<Vehicle> inventory){
        ArrayList<Vehicle> washing = new ArrayList<>();
        int dirtyCars = 0;
        int cleanCars = 0;
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
        if(dirtyCars >= 2){
            int carNum;
            for (int i = 0; i < 2; i ++) {
                carNum = random.nextInt(dirtyCars);
                dirtyCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washDirty(car));
                
                
                System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness());
                washing.remove(carNum);
            }
        }else if (dirtyCars == 1 && cleanCars >= 1){
            Vehicle car = washing.get(0);
            String previous = car.getCleanliness();
            washing.get(0).setCleanliness(washDirty(car));
            System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                    +" ("+ car.getName() + ") and made it " + car.getCleanliness());
            washing.remove(0);
            int carNum = random.nextInt(cleanCars);
            car = washing.get(carNum);
            previous = car.getCleanliness();
            washing.get(carNum).setCleanliness(washClean(car));
            System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                    +" ("+ car.getName() + ") and made it " + car.getCleanliness());
        } else{
            int carNum;
            int temp;
            if(cleanCars >= 2){
                temp = 2;
            } else{
                temp = cleanCars;
            }
            for(int i = 0; i < temp; i ++){
                carNum = random.nextInt(cleanCars);
                cleanCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washClean(car));
                System.out.println(this.getName() + " washed a " + previous +" "+ car.getType()
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness());
                washing.remove(carNum);
            }
        }

    }

    public String washClean(Vehicle car){
//        Random random = new Random();
//        int chance = random.nextInt(100);
//        if(chance < 5){
//            return "dirty";
//        } else if(chance < 35){
//            this.dailyBonus += car.getWashBonus(1);
//            return "sparkling";
//        } else{
//            return "clean";
//        }
    	
    	washingMethod.wash(car);
    	return car.getCleanliness();
    }

    public String washDirty(Vehicle car){
//        Random random = new Random();
//        int chance = random.nextInt(10);
//        if (chance < 8){
//            this.dailyBonus += car.getWashBonus(1);
//            return "clean";
//        } else if (chance < 9){
//            this.dailyBonus += car.getWashBonus(2);
//            return "sparkling";
//        } else{
//            return "dirty";
//        }
    	washingMethod.wash(car);
    	return car.getCleanliness();
    }
}

