import java.util.Random;

public class ChemicalWash implements WashingMethod {

	@Override
	public void wash(Vehicle vehicle) {
		/*
		 * Dirty Vehicle: 80% chance of Clean, 10% chance of Sparkling 
		 * o Clean Vehicle:
		 * 10% chance of Dirty, 20% chance of Sparkling 
		 * o In all cases, a 10% chance of
		 * Vehicle becoming Broken (if not already)
		 * 
		 */
		Random rand = new Random();
		if (vehicle.getCleanliness().equals("dirty")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 80) {
				vehicle.setCleanliness("clean");
			}else if (percent < 90) {
				vehicle.setCleanliness("sparkling");
			}
		}else if (vehicle.getCleanliness().equals("clean")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 10) {
				vehicle.setCleanliness("dirty");
			}else if (percent < 30) {
				vehicle.setCleanliness("sparkling");
			}
		}
		
		int percent = rand.nextInt(100);//0 to 99
		
		if (percent < 10) {
			if (!vehicle.getCondition().equals("broken")) {
				vehicle.setCondition("broken");
			}
		}		
	}

}
