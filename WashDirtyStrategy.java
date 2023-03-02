import java.util.Random;

public class WashDirtyStrategy implements WashStrategy {

    @Override
    public String wash(Interns intern, Vehicle car) { 

        Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 8){
            intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(1)); // intern will get bonus based on the sparkilng out come of the car in this case, 10% sparkling 
            return "clean";
        } else if (chance < 9){
            intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(2)); //intern will get bonus based on the sparkling outcome of the car in this case 20% sparlking
            return "sparkling";
        } else{
            return "dirty";
        }

    }

}