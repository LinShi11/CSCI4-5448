package Decorators;

import People.People;

/**
 * Decorator pattern: the bow will increasee meat and fur production
 */
public class BowDecorator extends MagicItemDecorator {

	public BowDecorator(People person) {
		super(person);
	}

	public int getMeat() {
		return person.getMeat() + 4;
	}

	public int getFur() {
		return person.getFur() + 2;
	}

}
