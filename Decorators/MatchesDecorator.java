package Decorators;

import People.People;

/**
 * Decorator pattern: matches will increase food production
 */
public class MatchesDecorator extends MagicItemDecorator {

	public MatchesDecorator(People person) {
		super(person);
	}

	public int getFood() {
		return person.getFood() + 1;
	}

}
