package Decorators;

import People.People;

/**
 * Decorator Pattern: the axe will give bonus to the wood only
 */
public class AxeDecorator extends MagicItemDecorator {

	public AxeDecorator(People person) {
		super(person);
	}

	public int getWood() {
		return person.getWood() + 1;
	}

}
