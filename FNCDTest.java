//
//import static org.junit.Assert.fail;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.Arrays;
//import java.util.concurrent.CyclicBarrier;
//
//import org.junit.Test;
//
///**
// * This class tests for all the beginning values
// */
//public class FNCDTest {
//
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
//    	//wait for complete
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
//    	String buyer = "1\n1\n3\n0\n";
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
//
//}
