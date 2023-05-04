package Decorators;

import People.People;

/**
 * Decorator pattern: the sewing machine will increase clothes production
 */
public class SewingDecorator extends MagicItemDecorator {

	public SewingDecorator(People person) {
		super(person);
	}

	public int getClothes() {
		return person.getClothes() + 1;
	}

}
