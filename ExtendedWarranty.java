/**
 * This class is the extended Warranty (part of the decorator pattern)
 */
public class ExtendedWarranty extends SaleDecorator{

    public ExtendedWarranty(Vehicle car){
        super(car);
    }

    /**
     * part of the wrapper for percentage
     * @return the 20%
     */
    public double getPercent() {
        return car.getPercent() + 0.2;
    }
}
