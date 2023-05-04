package GamePlaySupport;

public class MatchesDecorator extends MagicItemDecorator {

	public MatchesDecorator(People person) {
		super(person);
	}

	public int getFood() {
		return person.getFood() + 1;
	}

}
