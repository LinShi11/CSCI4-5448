public class SatelliteRadio extends SaleDecorator{
    public SatelliteRadio(Vehicle car){
        super(car);
    }

    public double getPercent() {
        return car.getPercent() + 0.05;
    }
}