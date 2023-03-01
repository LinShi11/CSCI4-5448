import java.util.Random;

public class WashCleanStrategy implements WashStrategy {

	@Override
	public String wash(Interns intern, Vehicle car) {
		
		Random random = new Random();
        int chance = random.nextInt(100);
        if(chance < 5){
            return "dirty";
        } else if(chance < 35){
        	intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(1));
            return "sparkling";
        } else{
            return "clean";
        }
	}
}
