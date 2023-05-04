import java.util.HashMap;
import java.util.Random;

public class GetRockCommand implements CommandInterface {

	private Random random = new Random();
	private int value = random.nextInt(10) + 10; //10-20 pounds
	
	@Override
	public void execute(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.water, (resourceMap.get(Enum.resourceType.water) - value));
	}

}
