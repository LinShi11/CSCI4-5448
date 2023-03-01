import java.util.Random;

public class WashDirtyStrategy implements WashStrategy {

	@Override
	public String wash(Interns intern, Vehicle car) {

		Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 8){
        	intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(1));
            return "clean";
        } else if (chance < 9){
        	intern.setDailyBonus(intern.getDailyBonus() + car.getWashBonus(2));
            return "sparkling";
        } else{
            return "dirty";
        }
        
	}

}
