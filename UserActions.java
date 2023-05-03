import java.util.Random;

public class UserActions{
	
	private Random random = new Random();

	public UserActions(){

	}

	public int getFood() {
		return random.nextInt(3) + 1; //1-3 pounds
	}

	public int getMeat() {
		return random.nextInt(5) + 1; //1-5 pounds
	}

	public int getWater() {
		return random.nextInt(5) + 1; //1-5 gallons
	}

	public int getFur() {
		return random.nextInt(3) + 1; // 1-3
	}

	public int getWood() {
		return random.nextInt(5) + 5; //5-10 pounds
	}

	public int getRock() {
		return random.nextInt(5) + 5; //5-10 pounds
	}

	public int getClothes() {
		return random.nextInt(3) + 1; //1-3 count
	}

}