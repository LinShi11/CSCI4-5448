public class Undercoating extends SaleDecorator{
    public Undercoating(Vehicle car){
        super(car);
    }

    public double getPercent() {
        return car.getPercent() + 0.05;
    }
}
