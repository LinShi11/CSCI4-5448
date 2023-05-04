package Decorators;

import People.People;

public class MetalDecorator extends MagicItemDecorator {
    public MetalDecorator(People person) {
        super(person);
    }

    public int getHealth(){
        return person.getHealth() + 1;
    }
}
