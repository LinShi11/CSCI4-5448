import java.util.HashMap;
import java.util.Random;

public class GetClothesCommand implements CommandInterface {

	private Random random = new Random();
	private int value =  random.nextInt(3) + 1; //1-3 count
	
	@Override
	public void execute(Game game) {
		
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) + value));
	}

	@Override
	public void undo(Game game) {
		HashMap<Enum.resourceType, Integer> resourceMap = game.getResourceMap();
		resourceMap.put(Enum.resourceType.clothes, (resourceMap.get(Enum.resourceType.clothes) - value));
	}

}
