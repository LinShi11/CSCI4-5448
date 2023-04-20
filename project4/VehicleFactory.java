package project4;

/**
 * This class is part of the Factory pattern for Vehicles. We are calling different vehicle constructors based on the type.
 */
public class VehicleFactory {

    /**
     * part of the factory pattern
     * @param type: type of vehicle to create
     * @param id: the name of the vehicle
     * @return the newly created vehicle
     */
    public Vehicle buildVehicle(Enum.VehicleType type, String id){
        switch (type){
            case PerformanceCar:
                return new PerformanceCar(id);
            case Pickups:
                return new Pickups(id);
            case Cars:
                return new Cars(id);
            case ElectricCars:
                return new ElectricCar(id);
            case MonsterTrucks:
                return new MonsterTruck(id);
            case Motorcycles:
                return new Motorcycle(id);
            case Tractor:
                return new Tractor(id);
            case Van:
                return new Van(id);
            case Crane:
                return new Crane(id);
            default:
                return null;
        }
    }
}
