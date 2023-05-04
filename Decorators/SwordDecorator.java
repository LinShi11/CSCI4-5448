package Decorators;

import People.People;

/**
 * Decorator pattern: the sword will increase the defense stats
 */
public class SwordDecorator extends MagicItemDecorator {

    public SwordDecorator(People person) {
        super(person);
    }

    public int getDefense(){
        return person.getDefense() + 1;
    }
}
