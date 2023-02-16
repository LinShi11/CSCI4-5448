import java.util.ArrayList;
import java.util.Random;

public class Salesperson implements Staff{

    private String name;
    private int dailySalary;
    private int dailyBonus;
    private int totalPay;
    private int totalBonus;
    private String status;
    private int totalDaysWorked;

    public Salesperson(String name){
        setName(name);
        this.totalBonus = 0;
        this.dailyBonus = 0;
        this.dailySalary = 200;
        this.totalPay = 0;
        this.status = "Working";
        this.totalDaysWorked = 0;
    }

    public Salesperson(String name, int days, int bonus, int pay){
        setName(name);
        this.totalBonus = bonus;
        this.dailyBonus = 0;
        this.dailySalary = 200;
        this.totalPay = pay;
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
    public void setDailyBonus(int dailyBonus){
        this.dailyBonus = dailyBonus;
    }

    @Override
    public void setTotalPay() {
        this.totalPay+=this.dailySalary;
    }

    @Override
    public void setTotalBonus() {
        this.totalBonus+=this.dailyBonus;
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

    public Vehicle sale(Buyer buyer, ArrayList<Vehicle> inventory){
        Vehicle car = null;
        int price = 0;
        for(Vehicle temp: inventory){
            if(buyer.getVehicleType().equals(temp.getType()) && !temp.getCondition().equals("broken")){
                if(temp.getSalePrice() > price){
                    car = temp;
                    price = temp.getSalePrice();
                }
            }
        }
        if(car == null){
            car = mostExpensive(inventory);
        }
        if(car == null){
            System.out.println("There is no car available to the buyer. ");
        } else{
            buying(buyer, car);
        }
        return car;

    }

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

    public void buying(Buyer buyer, Vehicle car){
        int buyingChance = buyer.getProbability(buyer.getBuyingChance());
        Random random = new Random();
        if(!car.getType().equals(buyer.getVehicleType())){
            buyingChance -= 20;
        }
        if(car.getCleanliness().equals("sparkling")){
            buyingChance += 20;
        } else if (car.getCleanliness().equals("clean")){
            buyingChance += 10;
        }
        if(buyingChance <= 0){
            System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                    this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                    + car.getType() + "(" + car.getName()+ "). Therefore, the buyer is not interested ");
        } else{
            int success = random.nextInt(100);
            if(success < buyingChance){
                System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                        this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                        + car.getType() + "(" + car.getName()+ "). The buying probability was " + buyingChance+ ". And the transaction was successful for $" +
                        car.getSalePrice());

                this.dailyBonus += car.getSaleBonus();
                car.setStatus("sold");
            } else{
                System.out.println("Buyer " + buyer.getBuyingChance() + " "+ buyer.getVehicleType()+ " " +
                        this.getName() + " suggested a " + car.getCleanliness() + ", " + car.getCondition() + " "
                        + car.getType() + "(" + car.getName()+ "). The buying probability was " + buyingChance+ ". And the transaction was unsuccessful for $" +
                        car.getSalePrice());
            }
        }
    }
}
