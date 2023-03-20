package project4;

/**
 * This class is the road rescue coverage (part of the decorator pattern)
 */
public class RoadRescueCoverage extends SaleDecorator{
    public RoadRescueCoverage(Vehicle car){
        super(car);
    }

    /**
     * part of the wrapper for percentage
     * @return the 2%
     */
    public double getPercent() {
        return car.getPercent() + 0.02;
    }
}
