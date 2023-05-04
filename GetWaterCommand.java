import java.util.HashMap;
import java.util.Random;

public class GetWaterCommand implements CommandInterface {

	private Random random = new Random();
	private int value = random.nextInt(5) + 1; //1-5 gallons
	
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
