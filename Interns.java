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
        this.status = "Working";
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
                String previous = washing.get(carNum).getCleanliness();
                washing.get(carNum).setCleanliness(washDirty());
                System.out.println(this.getName() + " washed a " + previous +" "+ washing.get(carNum).getType()
                        +" ("+ washing.get(carNum).getName() + ") and made it " + washing.get(carNum).getCleanliness());
                washing.remove(carNum);
            }
        }else if (dirtyCars == 1 && cleanCars >= 1){
            System.out.println("Washing car " + washing.get(0).getName());
            washing.get(0).setCleanliness(washDirty());
            washing.remove(0);
            int carNum = random.nextInt(cleanCars);
            washing.get(carNum).setCleanliness(washClean());
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
                System.out.println("Washing car " + washing.get(carNum).getName());
                washing.get(carNum).setCleanliness(washClean());
                washing.remove(carNum);
            }
        }
        System.out.println(dirtyCars);
        System.out.println(cleanCars);

    }

    public String washClean(){
        Random random = new Random();
        int chance = random.nextInt(100);
        if(chance < 5){
            return "dirty";
        } else if(chance < 35){
            return "sparkling";
        } else{
            return "clean";
        }
    }

    public String washDirty(){
        Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 8){
            return "clean";
        } else if (chance < 9){
            return "sparkling";
        } else{
            return "dirty";
        }
    }
}

