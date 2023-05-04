import java.util.HashMap;
import java.util.Random;

public class GetWoodCommand implements CommandInterface {

	private Random random = new Random();
	private int value =  random.nextInt(10) + 5; //5-15 pounds
	
	@Override
	public void execute(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();		
		resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();		
		resourceMap.put(Enum.resourceType.wood, (resourceMap.get(Enum.resourceType.wood) - value));
	}

}
