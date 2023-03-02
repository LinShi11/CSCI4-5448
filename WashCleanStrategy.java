import java.util.Random;

public class WashCleanStrategy implements WashStrategy {

    @Override
    public String wash(Interns intern, Vehicle car) {

        Random random = new Random(); //this is the randomized chance
        int chance = random.nextInt(100);
        if(chance < 5){
            return "dirty";
        } else if(chance < 35){
            intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(1)); // intern will get bonus based on how sparlking car is in the outcome
            return "sparkling";
        } else{
            return "clean";
        }
    }
}