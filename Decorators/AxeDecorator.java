package Decorators;

import People.People;

public class AxeDecorator extends MagicItemDecorator {

	public AxeDecorator(People person) {
		super(person);
	}

	public int getWood() {
		return person.getWood() + 1;
	}

}
