package GamePlaySupport;

public class SwordDecorator extends MagicItemDecorator {

    public SwordDecorator(People person) {
        super(person);
    }

    public int getDefense(){
        return person.getDefense() + 1;
    }
}
