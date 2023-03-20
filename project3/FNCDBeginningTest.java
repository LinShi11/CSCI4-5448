package project3;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class tests for all the beginning values
 */
public class FNCDBeginningTest {
    FNCD fncd;
    @Before
    public void setUp(){
        fncd = new FNCD();
    }

    /**
     * the employee is correct
     */
    @Test
    public void initial(){
        Assertions.assertEquals(fncd.internList.size(), 3);
        Assertions.assertEquals(fncd.mechanicsList.size(), 3);
        Assertions.assertEquals(fncd.salespeopleList.size(), 3);
        Assertions.assertEquals(fncd.staffDriverList.size(), 3);
    }

    /**
     * the inventory is correct
     */
    @Test
    public void inventory(){
        fncd.setInventory();
        Assertions.assertEquals(fncd.performanceCarList.size(), 4);
        Assertions.assertEquals(fncd.carsList.size(), 4);
        Assertions.assertEquals(fncd.pickupsList.size(), 4);
        Assertions.assertEquals(fncd.electricCarList.size(), 4);
        Assertions.assertEquals(fncd.motorcycleList.size(), 4);
        Assertions.assertEquals(fncd.monsterTruckList.size(), 4);
    }

    /**
     * the initial pay is 0 for everyone
     */
    @Test
    public void initialPayisCorrect(){
        for (Interns emp: fncd.internList){
            Assertions.assertTrue(emp.getTotalPay()==0);
        }
        for (Mechanics emp: fncd.mechanicsList){
            Assertions.assertTrue(emp.getTotalPay()==0);
        }
        for (Salesperson emp: fncd.salespeopleList){
            Assertions.assertTrue(emp.getTotalPay()==0);
        }
    }

    /**
     * The car sale price is not 0
     */
    @Test
    public void carPriceIsCorrect(){
        for(Vehicle car: fncd.inventory){
            Assertions.assertTrue(car.getSalePrice()!=0);
        }
    }
}