//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//
///**
// * This class tests for the end of simulation
// */
//public class FNCDEndTest {
//    FNCD fncd;
//    @Before
//    public void setUp(){
//        fncd = new FNCD();
//        fncd.simulation();
//    }
//
//    /**
//     * all employee is employee arraylist have all quit
//     */
//    @Test
//    public void quitEmployees(){
//        for(Staff employee: fncd.employee){
//            Assertions.assertEquals(employee.getStatus(), "quit");
//        }
//    }
//
//    /**
//     * all cars in sold list have been sold
//     */
//    @Test
//    public void soldVehicles(){
//        for(Vehicle cars: fncd.soldCars){
//            Assertions.assertEquals(cars.getStatus(), "sold");
//        }
//    }
//
//    /**
//     * all cars in current inventory are in stock
//     */
//    @Test
//    public void currentInventory(){
//        for(Vehicle cars: fncd.inventory){
//            Assertions.assertTrue(cars.getStatus().contains("in stock"));
//        }
//    }
//
//    /**
//     * all current employees are working status
//     */
//    @Test
//    public void currentEmployees(){
//        for (Interns emp: fncd.internList){
//            Assertions.assertEquals(emp.getStatus(), "working");
//        }
//        for (Mechanics emp: fncd.mechanicsList){
//            Assertions.assertEquals(emp.getStatus(), "working");
//        }
//        for (Salesperson emp: fncd.salespeopleList){
//            Assertions.assertEquals(emp.getStatus(), "working");
//        }
//        for (StaffDriver emp: fncd.staffDriverList){
//            Assertions.assertEquals(emp.getStatus(), "working");
//        }
//    }
//
//    /**
//     * We are paying the employees (just not the driver)
//     */
//    @Test
//    public void weArePayingThem(){
//        for (Interns emp: fncd.internList){
//            Assertions.assertTrue(emp.getTotalPay()>0);
//        }
//        for (Mechanics emp: fncd.mechanicsList){
//            Assertions.assertTrue(emp.getTotalPay()>0);
//        }
//        for (Salesperson emp: fncd.salespeopleList){
//            Assertions.assertTrue(emp.getTotalPay()>0);
//        }
//    }
//
//    /**
//     * we are paying driver based on win
//     */
//    @Test
//    public void wePayDriverIfTheyWin(){
//        for (StaffDriver emp: fncd.staffDriverList){
//            if(emp.getWinCount() >=1){
//                Assertions.assertTrue(emp.getTotalBonus()>0);
//            }
//        }
//    }
//
//    /**
//     * We are counting each day for the work they did
//     */
//    @Test
//    public void allWorkingDaysAreAccountedFor(){
//        for (Interns emp: fncd.internList){
//            Assertions.assertTrue(emp.getTotalDaysWorked()>0);
//        }
//        for (Mechanics emp: fncd.mechanicsList){
//            Assertions.assertTrue(emp.getTotalDaysWorked()>0);
//        }
//        for (Salesperson emp: fncd.salespeopleList){
//            Assertions.assertTrue(emp.getTotalDaysWorked()>0);
//        }
//        for (StaffDriver emp: fncd.staffDriverList){
//            Assertions.assertTrue(emp.getTotalDaysWorked()>0);
//        }
//        for(Staff emp: fncd.employee){
//            Assertions.assertTrue(emp.getTotalDaysWorked()>0);
//        }
//    }
//}