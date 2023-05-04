import java.util.HashMap;
import java.util.Random;

public class GetMeatCommand implements CommandInterface {

	private Random random = new Random();
	private int value =  random.nextInt(5) + 5; //5-10 pounds
	
	@Override
	public void execute(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.meat, (resourceMap.get(Enum.resourceType.meat) - value));
	}

}
