public class VehicleFactory {

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