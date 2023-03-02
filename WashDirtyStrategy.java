import java.util.Random;

public class WashDirtyStrategy implements WashStrategy {

	@Override
	public String wash(Interns intern, Vehicle car) { //concrete classes which implement the interface
                                                      //If the chemical method of washing is implemented, these are the chances below if a car can be clean, sparking, or dirty.


		Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 8){ 
        	intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(1)); // intern will get bonus based on the sparkilng out come of the car in this case, 10% sparkling
            return "clean";
        } else if (chance < 9){ 
        	intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(2)); //based on how well the intern washes the car, if the car is sparkling then intern is reareded bonus 20% chance car is sparkling
            return "sparkling";
        } else{
            return "dirty"; 
        }
        
	}

}
