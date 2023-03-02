import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Interns implements Staff {
    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    public Interns(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 70;
        this.totalPay = 0;
        this.status = "working";
        this.totalDaysWorked = 0;
    }


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

    public String wash(ArrayList<Vehicle> inventory){
        String response = "";
        ArrayList<Vehicle> washing = new ArrayList<>();
        int dirtyCars = 0; //this function is the context for the stratgies
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

            WashStrategy washStrategy = new WashCleanStrategy(); //this is the new object that is being passed

            int carNum;
            for (int i = 0; i < 2; i ++) {
                carNum = random.nextInt(dirtyCars);
                dirtyCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washStrategy.wash(this, car));//this line will execute the strategy based on cleanliness of car
                response = (this.getName() + " washed a " + previous +" "+ car.getType() //this is the implementation of the strategy pattern, because the behavior of the class will change at runtime.
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness());
                System.out.println(response);
                washing.remove(carNum);
            }
        }else if (dirtyCars == 1 && cleanCars >= 1){

            WashStrategy washStrategy = new WashDirtyStrategy();

            Vehicle car = washing.get(0);
            String previous = car.getCleanliness();
            washing.get(0).setCleanliness(washStrategy.wash(this, car));
            response = (this.getName() + " washed a " + previous +" "+ car.getType()
                    +" ("+ car.getName() + ") and made it " + car.getCleanliness());
            System.out.println(response);
            washing.remove(0);
            int carNum = random.nextInt(cleanCars);
            car = washing.get(carNum);
            previous = car.getCleanliness();

            washStrategy = new WashCleanStrategy();

            washing.get(carNum).setCleanliness(washStrategy.wash(this, car));
            response = (this.getName() + " washed a " + previous +" "+ car.getType()
                    +" ("+ car.getName() + ") and made it " + car.getCleanliness());
            System.out.println(response);
        } else{
            int carNum;
            int temp;
            if(cleanCars >= 2){
                temp = 2;
            } else{
                temp = cleanCars;
            }

            WashStrategy washStrategy = new WashCleanStrategy();

            for(int i = 0; i < temp; i ++){
                carNum = random.nextInt(cleanCars);
                cleanCars--;
                Vehicle car = washing.get(carNum);
                String previous = car.getCleanliness();
                washing.get(carNum).setCleanliness(washStrategy.wash(this, car)); //execution of strategy based on state of the car
                response = (this.getName() + " washed a " + previous +" "+ car.getType()
                        +" ("+ car.getName() + ") and made it " + car.getCleanliness());
                System.out.println(response);
                washing.remove(carNum);
            }
        }
        return response;
    }
}