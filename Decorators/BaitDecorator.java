package Decorators;

import People.People;

/**
 * Decorator pattern: the baits will increase meat production
 */
public class BaitDecorator extends MagicItemDecorator {

	public BaitDecorator(People person) {
		super(person);
	}

	public int getMeat() {
		return person.getMeat() + 1;
	}

}
