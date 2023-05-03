public class GunpowderDecorator extends MagicItemDecorator{
    public GunpowderDecorator(People person) {
        super(person);
    }

    public int getMeat(){
        return person.getMeat() + 5;
    }

    public int getFur(){
        return person.getFur() + 2;
    }

    public int getDefense() {
        return person.getDefense() + 4;
    }
}
