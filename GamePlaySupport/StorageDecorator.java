package GamePlaySupport;

public class StorageDecorator extends MagicItemDecorator {

	public StorageDecorator(People person) {
		super(person);
	}

	public int getWater() {
		return person.getWater() + 4;
	}

}
