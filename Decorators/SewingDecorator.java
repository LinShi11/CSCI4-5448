package Decorators;

import People.People;

public class SewingDecorator extends MagicItemDecorator {

	public SewingDecorator(People person) {
		super(person);
	}

	public int getClothes() {
		return person.getClothes() + 1;
	}

}
