public class StaffFactory {

    public Staff hireStaff(Enum.StaffType type, String id, WashingMethod washingMethod){
        switch (type){
            case Intern :
                return new Interns(id, washingMethod);
            case Mechanic:
                return new Mechanics(id);
            case Salesperson:
                return new Salesperson(id);
            case Driver:
                return new StaffDriver(id);
            default:
                return null;
        }
    }

    public Staff promotion(Enum.StaffType type, String id, int days, int bonus, int pay){
        switch (type){
            case Mechanic:
                return new Mechanics(id, days, bonus, pay);
            case Salesperson:
                return new Salesperson(id, days, bonus, pay);
            default:
                return null;
        }
    }
}
