import java.util.ArrayList;
import java.util.Random;

public class Mechanics implements Staff {
    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    public Mechanics(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 240;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
    }

    public Mechanics(String name, int days){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 240;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = days;
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

    public void repair(ArrayList<Vehicle> list){
        ArrayList<Vehicle> repairing = new ArrayList<>();
        int fixable = 0;
        for(Vehicle car: list){
            if(!car.getCondition().equals("like new")){
                repairing.add(car);
                fixable ++;
            }
        }
        int num = fixable;
        if(fixable > 2){
            num = 2;
        }
        Random random = new Random();
        int carNum;
        for(int i = 0; i < num; i ++){
            carNum = random.nextInt(fixable);
            fixCar(repairing.get(carNum));
        }
    }

    public void fixCar(Vehicle car){
        Random random = new Random();
        String previous = car.getCondition();
        int chance = random.nextInt(10);
        if(chance < 8){
            if(car.getCondition().equals("used")){
                this.dailyBonus += car.getRepairBonus();
                car.setCondition("like new");
                car.setSalePrice(1.25);
            } else{
                car.setCondition("used");
                car.setSalePrice(1.5);
            }
            car.setCost();
        }
        if(car.getCleanliness().equals("sparkling")){
            car.setCleanliness("clean");
        } else if (car.getCleanliness().equals("clean")){
            car.setCleanliness("dirty");
        }
        System.out.println(this.getName() + " fixed a " + previous +" "+ car.getType()
                +" ("+ car.getName() + ") and made it " + car.getCondition());

    }
}
