public class ExtendedWarranty extends SaleDecorator{

    public ExtendedWarranty(Vehicle car){
        super(car);
    }

    public double getPercent() {
        return car.getPercent() + 0.2;
    }
}
