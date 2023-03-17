/**
 * This class is part of the Factory Pattern for Staff.
 * When we first hire an employee, they will go through hireStaff
 * Then when someone quits and the Intern is promoted, we will go through promotion
 */
public class StaffFactory {

    /**
     * hire the staff by using factory pattern
     * @param type: determine what kind of staff they are
     * @param id: their name
     * @param washingMethod: washing method, null for all non-interns
     * @return the staff created.
     */
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

    /**
     * This function uses factory pattern to promote an intern to either mechanics or salesperson
     * @param type: their new job
     * @param id: their name
     * @param days: how many days they have worked here
     * @param bonus: their previous bonus
     * @param pay: their previous pay
     * @return the staff after promotion
     */
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
