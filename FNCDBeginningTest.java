
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
        Assertions.assertEquals(Helper.getAllIntern(fncd.currentEmployee).size(), 3);
        Assertions.assertEquals(Helper.getAllSalesperson(fncd.currentEmployee).size(), 3);
        Assertions.assertEquals(Helper.getAllMechanics(fncd.currentEmployee).size(), 3);
        Assertions.assertEquals(Helper.getAllDriver(fncd.currentEmployee).size(), 3);
    }

    @Test
    public void budget(){
        Assertions.assertEquals(500000, fncd.budget);
    }

    /**
     * the inventory is correct
     */
    @Test
    public void inventory(){
        fncd.setInventory();
        Assertions.assertEquals(fncd.inventory.size(), 54);
    }

    /**
     * the initial pay is 0 for everyone
     */
    @Test
    public void initialPayisCorrect(){
        for (Staff emp: fncd.currentEmployee){
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