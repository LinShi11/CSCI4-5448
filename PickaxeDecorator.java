public class PickaxeDecorator extends MagicItemDecorator{

	public PickaxeDecorator(People person) {
		super(person);
	}

	public int getRock() {
		return person.getRock() + 2;
	}

	public int getGold(){
		return person.getGold() + 1;
	}

}
