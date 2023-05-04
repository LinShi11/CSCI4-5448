package Decorators;

import People.People;

/**
 * Decorator pattern: the storage will increase the water production
 */
public class StorageDecorator extends MagicItemDecorator {

	public StorageDecorator(People person) {
		super(person);
	}

	public int getWater() {
		return person.getWater() + 4;
	}

}
