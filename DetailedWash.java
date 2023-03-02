import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


import java.util.Random;

public class DetailedWash implements WashingMethod {

	@Override
	public void wash(Vehicle vehicle) {
		/*
		 * Dirty Vehicle: 60% chance of Clean, 20% chance of Sparkling 
		 * Clean Vehicle: 5% chance of Dirty, 40% chance of Sparkling
		 * No special effects as above methods 
		 */
		Random rand = new Random();
		if (vehicle.getCleanliness().equals("dirty")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 60) {
				vehicle.setCleanliness("clean");
			}else if (percent < 80) {
				vehicle.setCleanliness("sparkling");
			}
			
			return;
		}else if (vehicle.getCleanliness().equals("clean")) {
			
			int percent = rand.nextInt(100);//0 to 99
			
			if (percent < 5) {
				vehicle.setCleanliness("dirty");
			}else if (percent < 45) {
				vehicle.setCleanliness("sparkling");
			}
			
			return;
		}
	}

}