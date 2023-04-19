import java.util.Random;

public class UserActions{
	
	private Random random = new Random();

	public UserActions(){

	}

	public int getFood() {
		return random.nextInt(11) + 5; //5-15 pounds
	}

	public int getMeat() {
		return random.nextInt(19) + 2; //2-20 pounds
	}

	public int getWater() {
		return random.nextInt(10) + 1; //1-10 gallons
	}

	public int getFur() {

		//0, 1, 2 for "sheep", "bear", "lynx"
		return random.nextInt(3);
	}

	public int getWood() {
		return random.nextInt(96) + 5; //5-100 pounds
	}

	public int getRock() {
		return random.nextInt(196) + 5; //5-200 pounds
	}

	public int getClothes() {
		return random.nextInt(9) + 2; //2-19 count
	}

}