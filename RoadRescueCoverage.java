public class RoadRescueCoverage extends SaleDecorator{
    public RoadRescueCoverage(Vehicle car){
        super(car);
    }
    public double getPercent() {
        return car.getPercent() + 0.02;
    }
}
