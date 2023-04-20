package project3;

/**
 * This class is the satellite radion (part of the decorator pattern)
 */
public class SatelliteRadio extends SaleDecorator{
    public SatelliteRadio(Vehicle car){
        super(car);
    }

    /**
     * part of the wrapper for percentage
     * @return the 5%
     */
    public double getPercent() {
        return car.getPercent() + 0.05;
    }
}