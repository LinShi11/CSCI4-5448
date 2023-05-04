import java.util.HashMap;
import java.util.Random;

public class GetFurCommand implements CommandInterface {

	private Random random = new Random();
	private int value =  random.nextInt(3) + 1; // 1-3
	
	@Override
	public void execute(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.fur, (resourceMap.get(Enum.resourceType.fur) - value));
	}

}
