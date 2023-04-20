package project4;

/**
 * This class is the undercoating (part of the decorator pattern)
 */
public class Undercoating extends SaleDecorator{
    public Undercoating(Vehicle car){
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
