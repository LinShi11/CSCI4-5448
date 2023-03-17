
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class tests for threading
 */
public class FNCDTest {
	FNCD north;
	FNCD south;
	@Before
	public void setUp(){
		CyclicBarrier barrier = new CyclicBarrier(2);

    	north = new FNCD("North", barrier, true);
    	south = new FNCD("South", barrier, true);

	}

	/**
	 * make sure both FNCDs are set up correctly
	 */
	@Test
	public void testThreadingStartEmployee(){
		Assertions.assertEquals(north.currentEmployee.size(), 12);
		Assertions.assertEquals(south.currentEmployee.size(), 12);
	}

	/**
	 * make sure both FNCD have ended correctly
	 */
	@Test
	public void testThreadEnd(){
		Thread northThread = new FNCDThead(north);
    	Thread southThread = new FNCDThead(south);

    	northThread.start();
    	southThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Assertions.assertTrue(north.currentEmployee.get(0).getTotalDaysWorked() > 1);
		Assertions.assertTrue(south.currentEmployee.get(0).getTotalDaysWorked() > 1);
	}

	/**
	 * both FNCD has sold some cars.
	 */
	@Test
	public void testSellingCars(){
		Thread northThread = new FNCDThead(north);
		Thread southThread = new FNCDThead(south);

		northThread.start();
		southThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Assertions.assertTrue(north.soldCars.size() > 0);
		Assertions.assertTrue(south.soldCars.size() > 0);
	}



//    /**
//     * test command line interface with choosing North, the issues Quit command
//     */
//    @Test
//    public void testCmdChooseNorthAndExit(){
//
//    	String buyer = "1\n1\n0\n";
//    	BufferedReader reader = new BufferedReader(new StringReader(buyer));
//
//    	CyclicBarrier barrier = new CyclicBarrier(2);
//
//    	FNCD north = new FNCD("North", barrier, true);
//    	FNCD south = new FNCD("South", barrier, true);
//
//    	Thread northThread = new FNCDThead(north);
//    	Thread southThread = new FNCDThead(south);
//
//    	northThread.start();
//    	southThread.start();
//
//    	try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//
//    	CommandLineInterface inf = new CommandLineInterface(
//    			 Arrays.asList(new FNCD[] {north, south}));
//
//    	try {
//			inf.execute(reader);
//		} catch (IOException e1) {
//			fail();
//		}
//
//    	try {
//			northThread.join();
//			southThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    }
//
//
//    /**
//     * test command line interface with choosing North, the issues Time, Quit command
//     */
//    @Test
//    public void testCmdChooseNorthTimeAndExit(){
//
//    	String buyer = "1\n1\n3\0n";
//    	BufferedReader reader = new BufferedReader(new StringReader(buyer));
//
//    	CyclicBarrier barrier = new CyclicBarrier(2);
//
//    	FNCD north = new FNCD("North", barrier, true);
//    	FNCD south = new FNCD("South", barrier, true);
//
//    	Thread northThread = new FNCDThead(north);
//    	Thread southThread = new FNCDThead(south);
//
//    	northThread.start();
//    	southThread.start();
//
//    	try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			fail();
//		}
//
//    	CommandLineInterface inf = new CommandLineInterface(
//    			 Arrays.asList(new FNCD[] {north, south}));
//
//    	try {
//			inf.execute(reader);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//    	//wait for complete
//    	try {
//			northThread.join();
//			southThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    }

}
