import java.util.HashMap;
import java.util.Random;

public class GetFoodCommand implements CommandInterface {

	private Random random = new Random();
	private int value =  random.nextInt(3) + 1; //1-3 pounds
	
	@Override
	public void execute(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.food, (resourceMap.get(Enum.resourceType.food) - value));
	}

}
