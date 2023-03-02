import java.util.Random;

/**
 * elbow grease wash (part of strategy pattern)
 */
public class ElbowGreasWash implements WashingMethod {

	@Override
	public void wash(Vehicle vehicle) {
		/*
		 * Dirty Vehicle: 70% chance of Clean, 5% chance of Sparkling
		 * Clean Vehicle: 15% chance of Dirty, 15% chance of Sparkling
		 * In all cases, 10% chance of Vehicle becoming Like New (if not already) 
		 */
		Random rand = new Random();
		if (vehicle.getCleanliness().equals("dirty")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 70) {
				vehicle.setCleanliness("clean");
			}else if (percent < 75) {
				vehicle.setCleanliness("sparkling");
			}
		}else if (vehicle.getCleanliness().equals("clean")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 15) {
				vehicle.setCleanliness("dirty");
			}else if (percent < 30) {
				vehicle.setCleanliness("sparkling");
			}
		}
		
		int percent = rand.nextInt(100);//0 to 99
		
		if (percent < 10) {
			if (!vehicle.getCondition().equals("like new")) {
				vehicle.setCondition("like new");
			}
		}		
	}

}
